package kr.co.apfactory.storesolution.domain.entity;

import kr.co.apfactory.storesolution.domain.dto.request.ReqCustomerReservationDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqReservationUpdateDTO;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
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
    @Comment("예약 종류 (1:맞춤예약, 2~6:매장 커스텀)")
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

    @OneToOne(fetch = FetchType.LAZY)
    @Comment("상담 완료 직원")
    private User completedUser;

    @Column(columnDefinition = "tinyint default 0", nullable = false)
    @Comment("계약 여부 (0:미해당, 1:계약, 2:투어, 3:취소)")
    private Integer contract;

    @Column(length = 1000)
    @Comment("비고")
    private String memo;

    public void completeCounseling(User user) {
        this.completed = true;
        this.completedUser = user;
    }

    public void updateReservation(ReqReservationUpdateDTO dto) {
        this.reservationManager = User.builder().id(dto.getReservationManager()).build();
        this.consultingManager = User.builder().id(dto.getConsultingManager()).build();
        this.allDay = dto.getIsAllday();
        this.consultingDate = dto.getConsultingDate();
        this.type = dto.getType();

        if (!dto.getIsAllday()) {
            this.consultingDatetimeFrom = dto.getConsultingDatetimeFrom();
            this.consultingDatetimeTo = dto.getConsultingDatetimeTo();
        } else {
            this.consultingDatetimeFrom = null;
            this.consultingDatetimeTo = null;
        }
    }

    public void updateReservation(ReqCustomerReservationDTO dto) {
        this.reservationManager = User.builder().id(LoginUser.getDetails().getId()).build();
        this.consultingManager = User.builder().id(dto.getConsultingManager()).build();
        this.allDay = dto.getIsAllday();
        this.consultingDate = dto.getConsultingDate();
        this.type = dto.getReservationType();

        if (!dto.getIsAllday()) {
            this.consultingDatetimeFrom = dto.getConsultingDatetimeFrom();
            this.consultingDatetimeTo = dto.getConsultingDatetimeTo();
        } else {
            this.consultingDatetimeFrom = null;
            this.consultingDatetimeTo = null;
        }

        this.contract = dto.getReservationContract();
        this.memo = dto.getReservationMemo();
    }

    public void updateMemo(String memo) {
        this.memo = memo;
    }
}
