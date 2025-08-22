package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResSiteImageDTO;
import kr.co.apfactory.storesolution.domain.entity.SiteEnvSetting;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.repository.SiteEnvSettingRepository;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttach;
import kr.co.apfactory.storesolution.global.file.domain.entity.StoreFileAttachMaster;
import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.StoreFileAttachMasterRepository;
import kr.co.apfactory.storesolution.global.file.domain.repository.StoreFileAttachRepository;
import kr.co.apfactory.storesolution.global.object.AlterObject;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final AlterObject alterObject;

    private final SiteEnvSettingRepository siteEnvSettingRepository;
    private final FileAttachRepository fileAttachRepository;
    private final StoreFileAttachRepository storeFileAttachRepository;
    private final StoreFileAttachMasterRepository storeFileAttachMasterRepository;

    public ResEnvironmentUpdateDTO getSiteEnvironment() {
        Store store = Store.builder().id(LoginUser.getDetails().getStoreId()).build();
        SiteEnvSetting siteEnvSetting = siteEnvSettingRepository.findByStore(store);
        if (siteEnvSetting == null) {
            // 기본값을 Res DTO로 가져온 뒤
            siteEnvSetting = siteEnvSettingRepository.findByStoreIsNull();
            ResEnvironmentUpdateDTO defaultDto = siteEnvSetting.toResEnvironmentUpdateDTO();

            // 새로운 매장용 설정 생성
            SiteEnvSetting newSetting = defaultDto.toSiteEnvSettingEntity();
            newSetting.updateStore(store);
            siteEnvSettingRepository.save(newSetting);
        }
        return siteEnvSetting.toResEnvironmentUpdateDTO();
    }

    /**
     * 환경설정 저장 (기존 설정 삭제 후 재저장)
     */
    public void updateEnvironment(ReqEnvironmentUpdateDTO dto) {
        Long storeId = LoginUser.getDetails().getStoreId();
        Store store = Store.builder().id(storeId).build();

        // 기존 환경설정 삭제
        siteEnvSettingRepository.deleteByStore(store);

        // 새로운 환경설정 저장
        SiteEnvSetting siteEnvSetting = dto.toSiteEnvSettingEntity();
        siteEnvSetting.updateStore(store);

        siteEnvSettingRepository.save(siteEnvSetting);
    }

    /**
     * 현재 로그인한 사용자의 매장에 설정된 이미지 정보를 조회합니다.
     *
     * @return 홈 이미지 목록, 로고 이미지, 컨설팅 이미지 목록이 포함된 ResSiteImageDTO
     */
    @Transactional
    public ResSiteImageDTO getSiteImages() {
        Long storeId = LoginUser.getDetails().getStoreId();
        Store store = Store.builder().id(storeId).build();

        ResSiteImageDTO resSiteImageDTO = ResSiteImageDTO.builder().build();

        // 홈 이미지 목록 조회 (null 방어 처리 포함)
        List<StoreFileAttachMaster> storeFileAttachMasterList = storeFileAttachMasterRepository.findAllByStore(store);
        for (StoreFileAttachMaster storeFileAttachMaster: storeFileAttachMasterList) {
            List<StoreFileAttach> storeFileAttachList = storeFileAttachRepository.findAllByStoreFileAttachMaster(storeFileAttachMaster);

            if (storeFileAttachMaster.getFileType() == 1) {
                // 홈 이미지
                resSiteImageDTO.setHomeImageList(
                        storeFileAttachList.stream()
                        .map(alterObject::toFileDTO)
                                .collect(Collectors.toList())
                );
            } else if (storeFileAttachMaster.getFileType() == 2) {
                // 로고 이미지
                resSiteImageDTO.setLogoImage(alterObject.toFileDTO(storeFileAttachList.get(0)));
            } else if (storeFileAttachMaster.getFileType() == 3) {
                // 상담판 이미지
                resSiteImageDTO.setConsultingImageList(
                        storeFileAttachList.stream()
                        .map(alterObject::toFileDTO)
                                .collect(Collectors.toList())
                );
            }
        }

        return resSiteImageDTO;
    }

    public FileDTO getLogoImage() {
        Long storeId = LoginUser.getDetails().getStoreId();
        Store store = Store.builder().id(storeId).build();

        StoreFileAttachMaster storeFileAttachMaster = storeFileAttachMasterRepository.findByStoreAndFileType(store, 2);
        if (storeFileAttachMaster == null) {
            return null;
        }
        StoreFileAttach storeFileAttach = storeFileAttachRepository.findByStoreFileAttachMaster(storeFileAttachMaster);
        if  (storeFileAttach == null) {
            return null;
        }

        return FileDTO.builder()
                .fileId(storeFileAttach.getId())
                .fileMasterId(storeFileAttachMaster.getId())
                .originalFileName(storeFileAttach.getOriginalFileName())
                .savedPathFile(storeFileAttach.getSavedPathFile())
                .build();
    }

    public List<FileDTO> getMainImageList() {
        Long storeId = LoginUser.getDetails().getStoreId();

        List<FileDTO> fileList = storeFileAttachRepository.selectSiteImageList(storeId, 1);

        return fileList;
    }
}
