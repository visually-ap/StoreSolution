package kr.co.apfactory.storesolution.domain.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReqReservationDTO {
    private Long reservationIdModal;
    private Long consultingMangerIdModal;
    private Boolean isAlldayModal = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationModalConsultingDate;
    private String consultingHourModal;
    private String consultingMinuteModal;
    private Integer reservationModalType;
    private String reservationModalMemo;
    private Integer reservationModalContract;

    private LocalDateTime consultingDatetimeFrom;
    private LocalDateTime consultingDatetimeTo;

    public void updateConsultingDatetime(Integer consultingTime) {
        int hour = parseOrDefault(this.consultingHourModal, 0);
        int minute = parseOrDefault(this.consultingMinuteModal, 0);

        this.consultingDatetimeFrom = reservationModalConsultingDate.atTime(hour, minute);
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
