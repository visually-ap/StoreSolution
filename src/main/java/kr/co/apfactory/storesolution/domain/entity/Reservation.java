package kr.co.apfactory.storesolution.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_reservation")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("예약 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("주문 상담 시퀀스")
    private Customer customer;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("종일 스케줄")
    private Boolean allDay;

    @Column(columnDefinition = "tinyint", nullable = false)
    @Comment("예약 종류 (1:상담)")
    private Integer type;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("예약담당")
    private User reservationManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("상담담당")
    private User consultingManager;

    @Column(columnDefinition = "date", nullable = false)
    @Comment("상담예약일")
    private LocalDate consultingDate;

    @Column(columnDefinition = "datetime")
    @Comment("상담예약 시작 일시")
    private LocalDateTime consultingDatetimeFrom;

    @Column(columnDefinition = "datetime")
    @Comment("상담예약 종료 일시")
    private LocalDateTime consultingDatetimeTo;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("상담 완료 여부")
    private Boolean completed;

    public void updateCustomer(Customer customer) {
        this.customer = customer;
    }

    public void updateReservationManager(User reservationManager) {
        this.reservationManager = reservationManager;
    }

    public void updateConsultingManager(User consultingManager) {
        this.consultingManager = consultingManager;
    }

    public void updateConsultingDatetime(String consultingHour, String consultingMinute, Integer consultingTime) {
        int hour = parseOrDefault(consultingHour, 0);
        int minute = parseOrDefault(consultingMinute, 0);

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

    public void updateConsultingDatetimeFrom(LocalDateTime consultingDatetimeFrom) {
        this.consultingDatetimeFrom = consultingDatetimeFrom;
    }

    public void updateConsultingDatetimeTo(LocalDateTime consultingDatetimeTo) {
        this.consultingDatetimeTo = consultingDatetimeTo;
    }
}
