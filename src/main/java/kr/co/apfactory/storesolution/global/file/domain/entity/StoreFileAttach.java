package kr.co.apfactory.storesolution.global.file.domain.entity;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_store_file_attach")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@DynamicUpdate
@DynamicInsert
@Getter
@Setter
public class StoreFileAttach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private StoreFileAttachMaster storeFileAttachMaster;

    @Column(length = 400, nullable = false)
    private String originalFileName;

    @Column(length = 1000, nullable = false)
    private String savedPathFile;

    @Column(columnDefinition = "int default 0", nullable = false)
    private Integer downloadCount;

    public String getUploadedFullPath(String uploadPath) {
        return uploadPath + this.getSavedPathFile();
    }

    public void increaseDownloadCount() {
        this.downloadCount++;
    }
}
