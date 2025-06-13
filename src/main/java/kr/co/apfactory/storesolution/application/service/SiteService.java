package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.FileDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEnvironmentUpdateDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResSiteImageDTO;
import kr.co.apfactory.storesolution.domain.entity.SiteEnvSetting;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.repository.SiteEnvSettingRepository;
import kr.co.apfactory.storesolution.global.file.domain.entity.FileAttach;
import kr.co.apfactory.storesolution.global.file.domain.repository.FileAttachRepository;
import kr.co.apfactory.storesolution.global.object.AlterObject;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiteService {
    private final SiteEnvSettingRepository siteEnvSettingRepository;
    private final FileAttachRepository fileAttachRepository;
    private final AlterObject alterObject;

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

        // 매장에 해당하는 환경설정 조회
        SiteEnvSetting setting = siteEnvSettingRepository.findByStore(store);
        // setting이 존재하지 않으면 빈 객체 반환
        if (setting == null) {
            return ResSiteImageDTO.builder()
                    .homeImageList(Collections.emptyList())
                    .logoImage(null)
                    .consultingImageList(Collections.emptyList())
                    .build();
        }

        // 홈 이미지 목록 조회 (null 방어 처리 포함)
        List<FileAttach> homeImageList = new ArrayList<>();
        if (setting.getHomeImage() != null) {
            homeImageList = fileAttachRepository.findAllByFileAttachMaster(setting.getHomeImage());
        }

        // 로고 이미지 변환 (단건이므로 null 체크 후 DTO 변환)
        FileDTO logoImageDto = setting.getLogoImage() != null ? alterObject.toFileDTO(setting.getLogoImage()) : null;

        // 컨설팅 이미지 목록 조회 (null 방어 처리 포함)
        List<FileAttach> consultingImageList = new ArrayList<>();
        if (setting.getConsultingImage() != null) {
            consultingImageList = fileAttachRepository.findAllByFileAttachMaster(setting.getConsultingImage());
        }

        // DTO 생성 및 반환
        return ResSiteImageDTO.builder()
                .homeImageList(homeImageList.stream()
                        .map(alterObject::toFileDTO)
                        .collect(Collectors.toList()))
                .logoImage(logoImageDto)
                .consultingImageList(consultingImageList.stream()
                        .map(alterObject::toFileDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
