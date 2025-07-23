package kr.co.apfactory.storesolution.global.file.domain.entity;

import kr.co.apfactory.storesolution.domain.entity.Store;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_store_file_attach_master")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class StoreFileAttachMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;

    @Column(columnDefinition = "tinyint")
    @Comment("파일 타입 (1:메인홈, 2:로고, 3:상담판")
    private Integer fileType;
}
