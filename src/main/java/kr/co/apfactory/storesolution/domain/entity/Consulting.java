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

@Entity
@Table(name = "tb_consulting")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class Consulting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("상담 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("예약 시퀀스")
    private Reservation reservation;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("상담 진행 담당")
    private User consultingManager;

    @Column(columnDefinition = "datetime")
    @Comment("상담예약 시작 일시")
    private LocalDate consultingDatetimeFrom;

    @Column(columnDefinition = "datetime")
    @Comment("상담예약 종료 일시")
    private LocalDate consultingDatetimeTo;

    @Column(columnDefinition = "text")
    @Comment("상담 내용")
    private LocalDate consultingContents;
}
