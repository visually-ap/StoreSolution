package kr.co.apfactory.storesolution.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_code_part")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CodePart {
    @Id
    @Comment("코드 부위 시퀀스")
    private Long id;

    @Column(length = 120, nullable = false)
    @Comment("부위 - 한국어")
    private String partNameKo;

    @Column(length = 120, nullable = false)
    @Comment("부위 - 영어")
    private String partNameEn;

    @Column(length = 120, nullable = false)
    @Comment("부위 - 중국어")
    private String partNameCn;

    @Column(length = 120, nullable = false)
    @Comment("부위 - 일본어")
    private String partNameJp;

    @Column(length = 120, nullable = false)
    @Comment("부위 - 인도네시아어")
    private String partNameIn;
}
