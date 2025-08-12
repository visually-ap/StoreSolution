package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReqReservationUpdateDTO {
    private Long orderConsultingId;

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
}
