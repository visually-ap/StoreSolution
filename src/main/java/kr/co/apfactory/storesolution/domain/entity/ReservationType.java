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

@Entity
@Table(name = "tb_reservation_type")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class ReservationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("타입 정의 시퀀스")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("매장 시퀀스")
    private Store store;

    @Column(length = 10, nullable = false)
    @Comment("예약 타입명")
    private String typeName;

    @Column(length = 10, nullable = false)
    @Comment("예약항목 색상")
    private String typeColor;

    @Column(columnDefinition = "boolean default 0", nullable = false)
    @Comment("예약 타입별 시간")
    private Integer typeMinute;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("카운셀링 목록 등록 여부")
    private Boolean counselingList;

    @Column(columnDefinition = "tinyint default 99", nullable = false)
    @Comment("노출 순서")
    private Integer sequence;
}
