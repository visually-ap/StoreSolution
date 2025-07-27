package kr.co.apfactory.storesolution.domain.dto.request;

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
    private LocalDate consultingDate;
    private String consultingHour;
    private String consultingMinute;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reservationDatetime;

    private String memo;
}
