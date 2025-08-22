package kr.co.apfactory.storesolution.global.file.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.global.file.domain.dto.DownloadFileDTO;
import kr.co.apfactory.storesolution.global.file.domain.dto.UploadFilesDTO;
import kr.co.apfactory.storesolution.global.file.domain.dto.UploadResultDTO;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachMasterRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.StoreFileAttachMasterRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.StoreFileAttachRepository;
import kr.co.apfactory.storesolution.global.file.util.FileManager;
import kr.co.apfactory.storesolution.global.file.util.FileSortManager;
import kr.co.apfactory.storesolution.global.object.AlterObject;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {
    private final Environment environment;

    private final FileManager fileManager;

    private final FileSortManager fileSortManager;

    private final AlterObject alterObject;

    private final FileAttachRepository fileAttachRepository;

    private final FileAttachMasterRepository fileAttachMasterRepository;

    private final StoreFileAttachRepository storeFileAttachRepository;

    private final StoreFileAttachMasterRepository storeFileAttachMasterRepository;

    public ResponseDTO uploadSettingImages(UploadFilesDTO uploadFilesDTO) throws Exception {
        if (uploadFilesDTO.getFiles() != null && !uploadFilesDTO.getFiles().isEmpty()) {
            Long storeId = LoginUser.getDetails().getStoreId();
            Store store = Store.builder().id(storeId).build();

            StoreFileAttachMaster storeFileAttachMaster = storeFileAttachMasterRepository.findByStoreAndFileType(store, uploadFilesDTO.getFileType());
            if (storeFileAttachMaster == null) {
                // 최초 등록이므로 파일 마스터 빌드
                storeFileAttachMaster = StoreFileAttachMaster.builder()
                        .store(store)
                        .fileType(uploadFilesDTO.getFileType())
                        .build();
                storeFileAttachMasterRepository.save(storeFileAttachMaster);
            } else {
                List<StoreFileAttach> fileAttachList = storeFileAttachRepository.findAllByStoreFileAttachMaster(storeFileAttachMaster);
                for (StoreFileAttach fileAttach : fileAttachList) {
                    fileManager.deleteFile(fileAttach.getUploadedFullPath(environment.getProperty("store.upload.path")));
                }
                storeFileAttachRepository.deleteAllByStoreFileAttachMaster(storeFileAttachMaster);
            }

            // 파일명 오름차순 정렬
            uploadFilesDTO.setFiles(fileSortManager.sortFilesByName(uploadFilesDTO.getFiles()));

            List<StoreFileAttach> storeFileAttachList = new ArrayList<>();
            for (MultipartFile file : uploadFilesDTO.getFiles()) {
                // 파일 업로드
                UploadResultDTO uploadResultDTO = fileManager.uploadFile(file, environment.getProperty("store.upload.path"));

                // 파일 정보 빌드
                storeFileAttachList.add(
                        StoreFileAttach.builder()
                                .storeFileAttachMaster(storeFileAttachMaster)
                                .originalFileName(uploadResultDTO.getOriginalFileName())
                                .savedPathFile(uploadResultDTO.getSavedPathFile())
                                .build()
                );
            }

            if (!storeFileAttachList.isEmpty()) {
                storeFileAttachRepository.saveAll(storeFileAttachList);
            }

            return ResponseDTO.builder()
                    .message("저장하였습니다.")
                    .isSuccess(true)
                    .build();
        } else {
            return ResponseDTO.builder()
                    .message("파일 업로드에 실패하였습니다.\n선택된 파일이 존재하지 않습니다.")
                    .isSuccess(false)
                    .build();
        }
    }

    public void downloadStoreImageFile(HttpServletResponse response, Long id, boolean isImage, String path) {
        StoreFileAttach storeFileAttach = storeFileAttachRepository.findById(id).orElseThrow(IllegalAccessError::new);

        // 파일 정보 빌드
        DownloadFileDTO dto = DownloadFileDTO.builder()
                .uploadedPathFile(storeFileAttach.getUploadedFullPath(path))
                .filename(storeFileAttach.getOriginalFileName())
                .isImage(isImage)
                .build();

        // 파일 다운로드
        fileManager.downloadFile(response, dto);
    }

    public List<FileDTO> getStoreImageList(Long id) {
        StoreFileAttachMaster storeFileAttachMaster = storeFileAttachMasterRepository.findById(id).orElseThrow(IllegalAccessError::new);
        if (storeFileAttachMaster == null) {
            return null;
        }

        List<StoreFileAttach> storeFileAttachList = storeFileAttachRepository.findAllByStoreFileAttachMaster(StoreFileAttachMaster.builder().id(id).build());
        return storeFileAttachList.stream()
                .map(alterObject::toFileDTO)
                .collect(Collectors.toList());
    }

    public List<FileDTO> getAboutImageList() {
        Store store = Store.builder().id(LoginUser.getDetails().getStoreId()).build();

        StoreFileAttachMaster storeFileAttachMaster = storeFileAttachMasterRepository.findByStoreAndFileType(store, 3);
        if (storeFileAttachMaster == null) {
            return null;
        }

        List<StoreFileAttach> storeFileAttachList = storeFileAttachRepository.findAllByStoreFileAttachMaster(storeFileAttachMaster);

        List<FileDTO> fileList = new ArrayList<>();
        for (StoreFileAttach file : storeFileAttachList) {
            fileList.add(FileDTO.builder()
                    .fileId(file.getId())
                    .fileMasterId(file.getStoreFileAttachMaster() != null ? file.getStoreFileAttachMaster().getId() : null)
                    .originalFileName(file.getOriginalFileName())
                    .savedPathFile(file.getSavedPathFile())
                    .build());
        }
        return fileList;
    }
}
