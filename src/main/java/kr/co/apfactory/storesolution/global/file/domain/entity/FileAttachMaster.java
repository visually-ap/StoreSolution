package kr.co.apfactory.storesolution.global.file.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_file_attach_master")
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class FileAttachMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
