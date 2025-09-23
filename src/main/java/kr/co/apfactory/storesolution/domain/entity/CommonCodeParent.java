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
@Table(name = "tb_common_code_parent")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CommonCodeParent {
    @Id
    @Column(length = 4, nullable = false)
    @Comment("부모 공통 코드 값")
    private String parentCodeValue;

    @Column(columnDefinition = "boolean default false", nullable = false)
    @Comment("삭제 여부")
    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("코드 부위")
    private CodePart part;

    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("코드 타입")
    private CodeType type;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 한국어")
    private String codeNameKo;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 영어")
    private String codeNameEn;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 중국어")
    private String codeNameCn;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 일본어")
    private String codeNameJp;

    @Column(length = 120, nullable = false)
    @Comment("코드명 - 인도네시아어")
    private String codeNameIn;
}
