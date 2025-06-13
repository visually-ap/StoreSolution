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
import java.math.BigDecimal;

@Entity
@Table(name = "tb_store")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class Store extends BaseEntity {    // 매장 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("매장 시퀀스")
    private Long id;

    @Column(length = 200, nullable = false)
    @Comment("주소")
    private String address;

    @Column(length = 200, nullable = false)
    @Comment("상세주소")
    private String addressDetail;

    @Column(length = 50, nullable = false)
    @Comment("사업자 등록번호")
    private String businessRegistrationNumber;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부 (true : 삭제, false : 미삭제)")
    private Boolean deleted;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("포튼 매장 여부 (true : 포튼매장, false : 포튼 외 매장)")
    private Boolean fotton;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("패턴 파일 첨부 가능 여부 (true : 가능, false : 불가능)")
    private Boolean patternFile;

    @Column(columnDefinition = "decimal(10,0) default 0", nullable = false)
    @Comment("보유 마일리지")
    private BigDecimal mileage;

    @Column(length = 200, nullable = false)
    @Comment("회원명")
    private String name;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("구독 중 여부 (true : 구독 중, false : 미 구독)")
    private Boolean subscribed;

    @Column(length = 50, nullable = false)
    @Comment("우편번호")
    private String zipcode;

    @Column(length = 50)
    @Comment("사업자명")
    private String buyerCompanyName;

    @Column(length = 50)
    @Comment("대표자명")
    private String buyerCeoName;

    @Column(length = 50)
    @Comment("업종")
    private String buyerBusinessType;

    @Column(length = 50)
    @Comment("업태")
    private String buyerBusinessItem;

    @Column(length = 200)
    @Comment("이메일")
    private String buyerEmail;
}
