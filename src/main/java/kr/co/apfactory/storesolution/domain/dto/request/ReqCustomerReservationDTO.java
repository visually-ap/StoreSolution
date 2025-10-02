package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.Reservation;
import kr.co.apfactory.storesolution.domain.entity.User;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReqCustomerReservationDTO {
    private Long customerId;
    private Long reservationId;
    private Long consultingManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate consultingDate;
    private String consultingHour;
    private String consultingMinute;

    private Integer reservationType;

    private LocalDateTime consultingDatetimeFrom;
    private LocalDateTime consultingDatetimeTo;

    private Boolean isAllday = false;

    private Integer reservationContract;
    private String reservationMemo;

    public Reservation toReservationEntity() {
        return Reservation.builder()
                .customer(Customer.builder().id(customerId).build())
                .reservationManager(User.builder().id(LoginUser.getDetails().getId()).build())
                .consultingManager(User.builder().id(this.getConsultingManager()).build())
                .allDay(this.isAllday)
                .type(this.reservationType)
                .consultingDate(this.consultingDate)
                .consultingDatetimeFrom(this.isAllday ? null : this.consultingDatetimeFrom)
                .consultingDatetimeTo(this.isAllday ? null : this.consultingDatetimeTo)
                .contract(this.reservationContract)
                .memo(this.reservationMemo)
                .build();
    }

    public void updateConsultingDatetime(Integer consultingTime) {
        int hour = parseOrDefault(this.consultingHour, 0);
        int minute = parseOrDefault(this.consultingMinute, 0);

        this.consultingDatetimeFrom = consultingDate.atTime(hour, minute);
        this.consultingDatetimeTo = consultingDatetimeFrom.plusMinutes(consultingTime);
    }

    // 유틸: 문자열을 정수로 파싱하되 실패 시 기본값 반환
    private int parseOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            return defaultValue;
        }
    }
}
