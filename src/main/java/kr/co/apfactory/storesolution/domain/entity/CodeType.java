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
@Table(name = "tb_code_type")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
public class CodeType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("타입 정의 시퀀스")
    private Long id;

    @Column(length = 120, nullable = false)
    @Comment("타입 - 한국어")
    private String typeNameKo;

    @Column(length = 120, nullable = false)
    @Comment("타입 - 영어")
    private String typeNameEn;

    @Column(length = 120, nullable = false)
    @Comment("타입 - 중국어")
    private String typeNameCn;

    @Column(length = 120, nullable = false)
    @Comment("타입 - 일본어")
    private String typeNameJp;

    @Column(length = 120, nullable = false)
    @Comment("타입 - 인도네시아어")
    private String typeNameIn;

    @Column(length = 200)
    @Comment("설명")
    private String description;
}
