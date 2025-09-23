package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.Reservation;
import kr.co.apfactory.storesolution.domain.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReqReservationRegisterDTO {
    private String name1;
    private String mobile1;
    private String name2;
    private String mobile2;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate photoDate;
    private String photoPlace;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;
    private String weddingPlace;

    private Long reservationManager;
    private Long consultingManager;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate consultingDate;
    private String consultingHour;
    private String consultingMinute;

    private String memo;

    private Integer type;

    private LocalDateTime consultingDatetimeFrom;
    private LocalDateTime consultingDatetimeTo;

    private Boolean isAllday = false;

    public Customer toCustomerEntity() {
        return Customer.builder()
                .name1(this.name1)
                .mobile1(this.mobile1)
                .name2(this.name2)
                .mobile2(this.mobile2)
                .photoDate(this.photoDate)
                .photoPlace(this.photoPlace)
                .weddingDate(this.weddingDate)
                .weddingPlace(this.weddingPlace)
                .memo(this.memo)
                .build();
    }

    public Reservation toReservationEntity(Customer customer) {
        return Reservation.builder()
                .customer(customer)
                .reservationManager(User.builder().id(this.getReservationManager()).build())
                .consultingManager(User.builder().id(this.getConsultingManager()).build())
                .allDay(this.isAllday)
                .type(this.type)
                .consultingDate(this.consultingDate)
                .consultingDatetimeFrom(this.isAllday ? null : this.consultingDatetimeFrom)
                .consultingDatetimeTo(this.isAllday ? null : this.consultingDatetimeTo)
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
