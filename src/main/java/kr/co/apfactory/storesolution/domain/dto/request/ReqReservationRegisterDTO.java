package kr.co.apfactory.storesolution.domain.dto.request;

import kr.co.apfactory.storesolution.domain.entity.Customer;
import kr.co.apfactory.storesolution.domain.entity.Reservation;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

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

    public Reservation toReservationEntity(int type) {
        return Reservation.builder()
                .allDay(isAllDay())
                .consultingDate(consultingDate)
                .type(type)
                .build();
    }

    public boolean isAllDay() {
        return StringUtils.isEmpty(this.consultingHour) ? true : false;
    }
}
