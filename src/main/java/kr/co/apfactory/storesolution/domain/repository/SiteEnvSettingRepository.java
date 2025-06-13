package kr.co.apfactory.storesolution.domain.repository;

import kr.co.apfactory.storesolution.domain.entity.SiteEnvSetting;
import kr.co.apfactory.storesolution.domain.entity.Store;
import kr.co.apfactory.storesolution.domain.repository.support.EnvironmentSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.support.StoreSupportRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// jpa 사용
@Repository
public interface SiteEnvSettingRepository extends JpaRepository<SiteEnvSetting, Long>, EnvironmentSupportRepository {
    SiteEnvSetting findByStore(Store store);

    SiteEnvSetting findByStoreIsNull();

    void deleteByStore(Store store);
}
