package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.response.*;

public interface CounselingSupportRepository {
    ResCounselingDTO selectCounselingDetail(Long storeId, Long reservationId);
    ResDesignJacketDTO selectCounselingJacketDesignDetail(Long counselingId);
    ResDesignPantsDTO selectCounselingPantsDesignDetail(Long counselingId);
    ResDesignVestDTO selectCounselingVestDesignDetail(Long counselingId);
    ResDesignCoatDTO selectCounselingCoatDesignDetail(Long counselingId);
    ResSizeJacketDTO selectCounselingJacketSizeDetail(Long counselingId);
    ResSizePantsDTO selectCounselingPantsSizeDetail(Long counselingId);
    ResSizeVestDTO selectCounselingVestSizeDetail(Long counselingId);
    ResSizeCoatDTO selectCounselingCoatSizeDetail(Long counselingId);
}
