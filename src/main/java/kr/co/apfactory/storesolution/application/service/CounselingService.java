package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqDesignSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqFabricSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqSizeSaveDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.entity.util.MapStructMapper;
import kr.co.apfactory.storesolution.domain.repository.*;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

import static kr.co.apfactory.storesolution.domain.entity.CounselingCoat.DOUBLE_RAGLAN;
import static kr.co.apfactory.storesolution.domain.entity.CounselingCoat.SINGLE_RAGLAN;
import static kr.co.apfactory.storesolution.domain.entity.CounselingJacket.CAVAN_JACKET;

@Service
@RequiredArgsConstructor
public class CounselingService {
    private final ReservationRepository reservationRepository;
    private final CounselingCommonRepository counselingCommonRepository;
    private final CounselingJacketRepository counselingJacketRepository;
    private final CounselingPantsRepository counselingPantsRepository;
    private final CounselingVestRepository counselingVestRepository;
    private final CounselingCoatRepository counselingCoatRepository;
    private final TempOrderCommonRepository tempOrderCommonRepository;
    private final TempOrderBasisRepository tempOrderBasisRepository;
    private final TempOrderDsJacketRepository tempOrderDsJacketRepository;
    private final TempOrderDsPantsRepository tempOrderDsPantsRepository;
    private final TempOrderDsVestRepository tempOrderDsVestRepository;
    private final TempOrderDsCoatRepository tempOrderDsCoatRepository;
    private final MapStructMapper mapStructMapper;

    public ResCounselingDTO getCounselingDetail(Long reservationId) {
        ResCounselingDTO resCounselingDTO = counselingCommonRepository.selectCounselingDetail(LoginUser.getDetails().getStoreId(), reservationId);
        if (resCounselingDTO == null) {
            return null;
        }

        if (resCounselingDTO.getCounselingCommonId() == null) {
            CounselingCommon counselingCommon = CounselingCommon.builder()
                    .store(Store.builder().id(LoginUser.getDetails().getStoreId()).build())
                    .reservation(Reservation.builder().id(resCounselingDTO.getReservationId()).build())
                    .build();
            counselingCommonRepository.save(counselingCommon);
            counselingJacketRepository.save(CounselingJacket.builder()
                            .counselingCommon(counselingCommon)
                    .build()
            );
            counselingPantsRepository.save(CounselingPants.builder()
                            .counselingCommon(counselingCommon)
                    .build()
            );
            counselingVestRepository.save(CounselingVest.builder()
                            .counselingCommon(counselingCommon)
                    .build()
            );
            counselingCoatRepository.save(CounselingCoat.builder()
                            .counselingCommon(counselingCommon)
                    .build()
            );
        }

        return resCounselingDTO;
    }

    public ResponseDTO updateFabricData(ReqFabricSaveDTO reqFabricSaveDTO) {
        CounselingCommon counselingCommon = counselingCommonRepository.findByStoreIdAndReservationId(LoginUser.getDetails().getStoreId(), reqFabricSaveDTO.getReservationId());
        if (counselingCommon == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        Reservation reservation = counselingCommon.getReservation();
        if (reservation.getCompleted()) {
            return ResponseDTO.builder()
                    .message("이미 완료 처리한 상담입니다.\n목록으로 돌아갑니다.")
                    .build();
        }

        counselingCommon.updateFabricData(reqFabricSaveDTO);

        counselingCommonRepository.save(counselingCommon);

        return ResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public ResDesignJacketDTO getCounselingJacketDesignDetail(Long counselingId) {
        return counselingJacketRepository.selectCounselingJacketDesignDetail(counselingId);
    }

    public ResDesignPantsDTO getCounselingPantsDesignDetail(Long counselingId) {
        return counselingPantsRepository.selectCounselingPantsDesignDetail(counselingId);
    }

    public ResDesignVestDTO getCounselingVestDesignDetail(Long counselingId) {
        return counselingVestRepository.selectCounselingVestDesignDetail(counselingId);
    }

    public ResDesignCoatDTO getCounselingCoatDesignDetail(Long counselingId) {
        return counselingCoatRepository.selectCounselingCoatDesignDetail(counselingId);
    }

    public ResponseDTO updateDesignData(ReqDesignSaveDTO reqDesignSaveDTO) {
        CounselingCommon counselingCommon = counselingCommonRepository.findByStoreIdAndReservationId(LoginUser.getDetails().getStoreId(), reqDesignSaveDTO.getReservationId());
        if (counselingCommon == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        Reservation reservation = counselingCommon.getReservation();
        if (reservation.getCompleted()) {
            return ResponseDTO.builder()
                    .message("이미 완료 처리한 상담입니다.\n목록으로 돌아갑니다.")
                    .build();
        }

        CounselingJacket jacket = counselingJacketRepository.findByCounselingCommonId(counselingCommon.getId());
        jacket.updateDesignData(reqDesignSaveDTO);
        counselingJacketRepository.save(jacket);

        CounselingPants pants = counselingPantsRepository.findByCounselingCommonId(counselingCommon.getId());
        pants.updateDesignData(reqDesignSaveDTO);
        counselingPantsRepository.save(pants);

        CounselingVest vest = counselingVestRepository.findByCounselingCommonId(counselingCommon.getId());
        vest.updateDesignData(reqDesignSaveDTO);
        counselingVestRepository.save(vest);

        CounselingCoat coat = counselingCoatRepository.findByCounselingCommonId(counselingCommon.getId());
        coat.updateDesignData(reqDesignSaveDTO);
        counselingCoatRepository.save(coat);

        return ResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public ResSizeJacketDTO getCounselingJacketSizeDetail(Long counselingId) {
        return counselingJacketRepository.selectCounselingJacketSizeDetail(counselingId);
    }

    public ResSizePantsDTO getCounselingPantsSizeDetail(Long counselingId) {
        return counselingPantsRepository.selectCounselingPantsSizeDetail(counselingId);
    }

    public ResSizeVestDTO getCounselingVestSizeDetail(Long counselingId) {
        return counselingVestRepository.selectCounselingVestSizeDetail(counselingId);
    }

    public ResSizeCoatDTO getCounselingCoatSizeDetail(Long counselingId) {
        return counselingCoatRepository.selectCounselingCoatSizeDetail(counselingId);
    }

    public ResponseDTO updateSizeData(ReqSizeSaveDTO reqSizeSaveDTO) {
        CounselingCommon counselingCommon = counselingCommonRepository.findByStoreIdAndReservationId(LoginUser.getDetails().getStoreId(), reqSizeSaveDTO.getReservationId());
        if (counselingCommon == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        Reservation reservation = counselingCommon.getReservation();
        if (reservation.getCompleted()) {
            return ResponseDTO.builder()
                    .message("이미 완료 처리한 상담입니다.\n목록으로 돌아갑니다.")
                    .build();
        }

        // 상의
        CounselingJacket jacket = counselingJacketRepository.findByCounselingCommonId(counselingCommon.getId());
        Long beforeJacketPatternId = -1L;
        if (jacket.getJacketPattern() != null) {
            beforeJacketPatternId = jacket.getJacketPattern().getId();
        }

        jacket.updateSizeData(reqSizeSaveDTO);
        if (jacket.getJacketPattern() != null && jacket.getJacketPattern().getId().equals(CAVAN_JACKET)) {
            jacket.setCavanDesignOption();
        } else {
            if (beforeJacketPatternId.equals(CAVAN_JACKET)) {
                jacket.initDesignOptionFromCavan();
            }
        }
        counselingJacketRepository.save(jacket);

        // 하의
        CounselingPants pants = counselingPantsRepository.findByCounselingCommonId(counselingCommon.getId());
        pants.updateSizeData(reqSizeSaveDTO);
        counselingPantsRepository.save(pants);

        // 조끼
        CounselingVest vest = counselingVestRepository.findByCounselingCommonId(counselingCommon.getId());
        vest.updateSizeData(reqSizeSaveDTO);
        counselingVestRepository.save(vest);

        // 코트
        CounselingCoat coat = counselingCoatRepository.findByCounselingCommonId(counselingCommon.getId());
        Long beforeCoatPatternId = -1L;
        if (coat.getCoatPattern() != null) {
            beforeCoatPatternId = coat.getCoatPattern().getId();
        }

        coat.updateSizeData(reqSizeSaveDTO);
        if (jacket.getJacketPattern() != null && coat.getCoatPattern().getId().equals(SINGLE_RAGLAN)) {
            coat.setSingleRaglanDesignOption();
        } else if (jacket.getJacketPattern() != null && coat.getCoatPattern().getId().equals(DOUBLE_RAGLAN)) {
            coat.setDoubleRaglanDesignOption();
        } else {
            if (beforeCoatPatternId.equals(SINGLE_RAGLAN) || beforeCoatPatternId.equals(DOUBLE_RAGLAN)) {
                coat.initDesignOptionFromRaglan();
            }
        }
        counselingCoatRepository.save(coat);

        return ResponseDTO.builder()
                .isSuccess(true)
                .build();
    }

    public ResponseDTO completeCounseling(Long reservationId) {
        CounselingCommon counselingCommon = counselingCommonRepository.findByStoreIdAndReservationId(LoginUser.getDetails().getStoreId(), reservationId);
        if (counselingCommon == null) {
            return ResponseDTO.builder()
                    .message("잘못된 접근입니다.")
                    .build();
        }

        if (!counselingCommon.getJacket() && !counselingCommon.getPants()
                && !counselingCommon.getVest() && !counselingCommon.getCoat()) {
            return ResponseDTO.builder()
                    .message("완료 처리를 하려면 품목을 하나 이상 선택하여야 합니다.")
                    .build();
        }

        if (counselingCommon.getJacket()) {
            CounselingJacket jacket = counselingJacketRepository.findByCounselingCommonId(counselingCommon.getId());
            if (jacket.getJacketPattern() == null || jacket.getJacketGauge() == null) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 패턴과 게이지복을 모두 입력하여야 합니다.")
                        .build();
            }

            if (StringUtils.isEmpty(counselingCommon.getFabricCompanyJacket())
                    || StringUtils.isEmpty(counselingCommon.getFabricPatternJacket())
                    || StringUtils.isEmpty(counselingCommon.getFabricColorJacket())) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 원단 정보를 모두 입력하여야 합니다.")
                        .build();
            }
        }

        if (counselingCommon.getPants()) {
            CounselingPants pants = counselingPantsRepository.findByCounselingCommonId(counselingCommon.getId());
            if (pants.getPantsPattern() == null || pants.getPantsGauge() == null) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 패턴과 게이지복을 모두 입력하여야 합니다.")
                        .build();
            }

            if (StringUtils.isEmpty(counselingCommon.getFabricCompanyPants())
                    || StringUtils.isEmpty(counselingCommon.getFabricPatternPants())
                    || StringUtils.isEmpty(counselingCommon.getFabricColorPants())) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 원단 정보를 모두 입력하여야 합니다.")
                        .build();
            }
        }

        if (counselingCommon.getVest()) {
            CounselingVest vest = counselingVestRepository.findByCounselingCommonId(counselingCommon.getId());
            if (vest.getVestPattern() == null || vest.getVestGauge() == null) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 패턴과 게이지복을 모두 입력하여야 합니다.")
                        .build();
            }

            if (StringUtils.isEmpty(counselingCommon.getFabricCompanyVest())
                    || StringUtils.isEmpty(counselingCommon.getFabricPatternVest())
                    || StringUtils.isEmpty(counselingCommon.getFabricColorVest())) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 원단 정보를 모두 입력하여야 합니다.")
                        .build();
            }
        }

        if (counselingCommon.getCoat()) {
            CounselingCoat coat = counselingCoatRepository.findByCounselingCommonId(counselingCommon.getId());
            if (coat.getCoatPattern() == null || coat.getCoatGauge() == null) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 패턴과 게이지복을 모두 입력하여야 합니다.")
                        .build();
            }

            if (StringUtils.isEmpty(counselingCommon.getFabricCompanyCoat())
                    || StringUtils.isEmpty(counselingCommon.getFabricPatternCoat())
                    || StringUtils.isEmpty(counselingCommon.getFabricColorCoat())) {
                return ResponseDTO.builder()
                        .message("완료 처리를 하려면 선택한 품목의 원단 정보를 모두 입력하여야 합니다.")
                        .build();
            }
        }

        Reservation reservation = counselingCommon.getReservation();
        if (reservation.getCompleted()) {
            return ResponseDTO.builder()
                    .message("이미 상담완료 하였습니다.\n목록으로 돌아갑니다.")
                    .build();
        }

        reservation.completeCounseling(User.builder().id(LoginUser.getDetails().getId()).build());
        reservationRepository.save(reservation);

        // 기타 처리 제외
//        if (counselingCommon.getFactory() == 3) {
//            return ResponseDTO.builder()
//                    .isSuccess(true)
//                    .message("상담완료 처리를 하였습니다.")
//                    .build();
//        }

        Customer customer = reservation.getCustomer();
        User pic = reservation.getConsultingManager();

        // ✅ 원단이 모두 동일하거나 단일 품목이면 기존 방식으로 1건 생성
        if (counselingCommon.getAllSameFabric()
                || (counselingCommon.getJacket() && !counselingCommon.getPants() && !counselingCommon.getVest())
                || (!counselingCommon.getJacket() && counselingCommon.getPants() && !counselingCommon.getVest())
                || (!counselingCommon.getJacket() && !counselingCommon.getPants() && counselingCommon.getVest())
                || (counselingCommon.getCoat())
        ) {
            TempOrderCommon orderCommon = createTempOrderCommon(counselingCommon, customer);
            tempOrderCommonRepository.save(orderCommon);

            TempOrderBasis orderBasis = createTempOrderBasis(orderCommon, counselingCommon, pic);
            tempOrderBasisRepository.save(orderBasis);

            saveDetailByParts(orderCommon, counselingCommon);
        } else {
            // ✅ 부위별 원단이 다르면 분리 저장
            Map<String, List<String>> fabricGroups = groupByFabric(counselingCommon);

            for (Map.Entry<String, List<String>> entry : fabricGroups.entrySet()) {
                List<String> parts = entry.getValue();

                TempOrderCommon orderCommon = TempOrderCommon.builder()
                        .store(counselingCommon.getStore())
                        .orderParts(parts.contains("coat") ? 2 : 1)
                        .nation(counselingCommon.getFactory())
                        .workType(counselingCommon.getWorkType())
                        .paymentsRequest(CodeType.builder().id(10L).build())
                        .customerName(customer.getName1())
                        .customerMobile(customer.getMobile1())
                        .jacket(parts.contains("jacket"))
                        .pants(parts.contains("pants"))
                        .vest(parts.contains("vest"))
                        .coat(parts.contains("coat"))
                        .counselingCommon(counselingCommon)
                        .build();

                tempOrderCommonRepository.save(orderCommon);

                // 원단 정보는 키 분리
                String[] keyParts = entry.getKey().split("_");
                TempOrderBasis orderBasis = TempOrderBasis.builder()
                        .orderCommon(orderCommon)
                        .picName(pic.getName())
                        .measurePic(pic.getName())
                        .fabricCompany(keyParts[0])
                        .fabricPattern(keyParts[1])
                        .fabricColor(keyParts[2])
                        .build();
                tempOrderBasisRepository.save(orderBasis);

                // ✅ 부위별 세부 저장
                if (parts.contains("jacket")) {
                    setOrderDsJacketData(orderCommon, counselingCommon.getId());
                }
                if (parts.contains("pants")) {
                    setOrderDsPantsData(orderCommon, counselingCommon.getId());
                }
                if (parts.contains("vest")) {
                    setOrderDsVestData(orderCommon, counselingCommon.getId());
                }
                if (parts.contains("coat")) {
                    setOrderDsCoatData(orderCommon, counselingCommon.getId());
                }
            }
        }

        return ResponseDTO.builder()
                .isSuccess(true)
                .message("상담완료 처리와 데이터 전송을 완료하였습니다.")
                .build();
    }

    // 원단 Key: 회사_품번_색상
    private String makeFabricKey(String company, String pattern, String color) {
        if (company == null || pattern == null || color == null) {
            return UUID.randomUUID().toString();
        }
        return company + "_" + pattern + "_" + color;
    }

    // CounselingCommon → 그룹핑
    private Map<String, List<String>> groupByFabric(CounselingCommon cc) {
        Map<String, List<String>> fabricGroups = new LinkedHashMap<>();

        if (Boolean.TRUE.equals(cc.getJacket())) {
            String key = makeFabricKey(cc.getFabricCompanyJacket(), cc.getFabricPatternJacket(), cc.getFabricColorJacket());
            fabricGroups.computeIfAbsent(key, k -> new ArrayList<>()).add("jacket");
        }
        if (Boolean.TRUE.equals(cc.getPants())) {
            String key = makeFabricKey(cc.getFabricCompanyPants(), cc.getFabricPatternPants(), cc.getFabricColorPants());
            fabricGroups.computeIfAbsent(key, k -> new ArrayList<>()).add("pants");
        }
        if (Boolean.TRUE.equals(cc.getVest())) {
            String key = makeFabricKey(cc.getFabricCompanyVest(), cc.getFabricPatternVest(), cc.getFabricColorVest());
            fabricGroups.computeIfAbsent(key, k -> new ArrayList<>()).add("vest");
        }
        if (Boolean.TRUE.equals(cc.getCoat())) {
            String key = makeFabricKey(cc.getFabricCompanyCoat(), cc.getFabricPatternCoat(), cc.getFabricColorCoat());
            fabricGroups.computeIfAbsent(key, k -> new ArrayList<>()).add("coat");
        }

        return fabricGroups;
    }

    // 공통 생성
    private TempOrderCommon createTempOrderCommon(CounselingCommon cc, Customer customer) {
        return TempOrderCommon.builder()
                .store(Store.builder().id(LoginUser.getDetails().getStoreId()).build())
                .orderParts(cc.getCoat() ? 2 : 1)
                .nation(cc.getFactory())
                .workType(cc.getWorkType())
                .paymentsRequest(CodeType.builder().id(10L).build())
                .customerName(customer.getName1())
                .customerMobile(customer.getMobile1())
                .jacket(cc.getJacket())
                .pants(cc.getPants())
                .vest(cc.getVest())
                .coat(cc.getCoat())
                .counselingCommon(cc)
                .build();
    }

    // Basis 생성
    private TempOrderBasis createTempOrderBasis(TempOrderCommon orderCommon, CounselingCommon cc, User pic) {
        return TempOrderBasis.builder()
                .orderCommon(orderCommon)
                .picName(pic.getName())
                .measurePic(pic.getName())
                .fabricCompany(cc.getFabricCompanyFromCounseling())
                .fabricPattern(cc.getFabricPatternFromCounseling())
                .fabricColor(cc.getFabricColorFromCounseling())
                .build();
    }

    // 세부 저장 분기
    private void saveDetailByParts(TempOrderCommon orderCommon, CounselingCommon cc) {
        if (cc.getJacket()) {
            setOrderDsJacketData(orderCommon, cc.getId());
        }
        if (cc.getPants()) {
            setOrderDsPantsData(orderCommon, cc.getId());
        }
        if (cc.getVest()) {
            setOrderDsVestData(orderCommon, cc.getId());
        }
        if (cc.getCoat()) {
            setOrderDsCoatData(orderCommon, cc.getId());
        }
    }


//    public ResponseDTO completeCounseling(Long reservationId) {
//        CounselingCommon counselingCommon = counselingCommonRepository.findByStoreIdAndReservationId(LoginUser.getDetails().getStoreId(), reservationId);
//        if (counselingCommon == null) {
//            return ResponseDTO.builder()
//                    .message("잘못된 접근입니다.")
//                    .build();
//        }
//
//        if (!counselingCommon.getJacket() && !counselingCommon.getPants()
//                && !counselingCommon.getVest() && !counselingCommon.getCoat()) {
//            return ResponseDTO.builder()
//                    .message("완료 처리를 하려면 품목을 하나 이상 선택하여야 합니다.")
//                    .build();
//        }
//
//        Reservation reservation = counselingCommon.getReservation();
//        reservation.completeCounseling(User.builder().id(LoginUser.getDetails().getId()).build());
//        reservationRepository.save(reservation);
//
//        if (counselingCommon.getFactory() == 3) {
//            return ResponseDTO.builder()
//                    .isSuccess(true)
//                    .message("상담완료 처리를 하였습니다.")
//                    .build();
//        }
//
//        Customer customer = reservation.getCustomer();
//        User pic = reservation.getConsultingManager();
//
//        // 여기서부터 temp order
//        if (counselingCommon.getAllSameFabric()
//                || (counselingCommon.getJacket() && !counselingCommon.getPants() && !counselingCommon.getVest())
//                || (!counselingCommon.getJacket() && counselingCommon.getPants() && !counselingCommon.getVest())
//                || (!counselingCommon.getJacket() && !counselingCommon.getPants() && counselingCommon.getVest())
//                || (counselingCommon.getCoat())
//        ) {
//            // 주문 공통 저장
//            TempOrderCommon orderCommon = TempOrderCommon.builder()
//                    .store(Store.builder().id(LoginUser.getDetails().getStoreId()).build())
//                    .orderParts(counselingCommon.getCoat() ? 2 : 1)
//                    .nation(counselingCommon.getFactory())
//                    .workType(counselingCommon.getWorkType())
//                    .paymentsRequest(CodeType.builder().id(10L).build())
//                    .customerName(customer.getName1())
//                    .customerMobile(customer.getMobile1())
//                    .jacket(counselingCommon.getJacket())
//                    .pants(counselingCommon.getPants())
//                    .vest(counselingCommon.getVest())
//                    .coat(counselingCommon.getCoat())
//                    .build();
//
//            tempOrderCommonRepository.save(orderCommon);
//
//            // 주문 기본 저장
//            TempOrderBasis orderBasis = TempOrderBasis.builder()
//                    .orderCommon(orderCommon)
//                    .picName(pic.getName())
//                    .measurePic(pic.getName())
//                    .fabricCompany(counselingCommon.getFabricCompanyFromCounseling())
//                    .fabricPattern(counselingCommon.getFabricPatternFromCounseling())
//                    .fabricColor(counselingCommon.getFabricColorFromCounseling())
//                    .build();
//            tempOrderBasisRepository.save(orderBasis);
//
//            if (counselingCommon.getJacket()) {
//                setOrderDsJacketData(orderCommon, counselingCommon.getId());
//            }
//
//            if (counselingCommon.getPants()) {
//                setOrderDsPantsData(orderCommon, counselingCommon.getId());
//            }
//
//            if (counselingCommon.getVest()) {
//                setOrderDsVestData(orderCommon, counselingCommon.getId());
//            }
//
//            if (counselingCommon.getCoat()) {
//                setOrderDsCoatData(orderCommon, counselingCommon.getId());
//            }
//        } else {
//            // 자켓만 주문
//            if (counselingCommon.getJacket() && !counselingCommon.getPants() && !counselingCommon.getVest()) {
//
//            }
//        }
//
//
//
//        return ResponseDTO.builder()
//                .isSuccess(true)
//                .message("상담완료 처리와 데이터 전송을 완료하였습니다.")
//                .build();
//    }
//
    private void setOrderDsJacketData(TempOrderCommon orderCommon, Long counselingCommonId) {
        CounselingJacket jacket = counselingJacketRepository.findByCounselingCommonId(counselingCommonId);
        TempOrderDsJacket tempJacket = mapStructMapper.toTempOrderDsJacket(jacket);
        tempJacket.updateOrderCommon(orderCommon);
        tempOrderDsJacketRepository.save(tempJacket);
    }

    private void setOrderDsPantsData(TempOrderCommon orderCommon, Long counselingCommonId) {
        CounselingPants pants = counselingPantsRepository.findByCounselingCommonId(counselingCommonId);
        TempOrderDsPants tempPants = mapStructMapper.toTempOrderDsPants(pants);
        tempPants.updateOrderCommon(orderCommon);
        tempOrderDsPantsRepository.save(tempPants);
    }

    private void setOrderDsVestData(TempOrderCommon orderCommon, Long counselingCommonId) {
        CounselingVest vest = counselingVestRepository.findByCounselingCommonId(counselingCommonId);
        TempOrderDsVest tempVest = mapStructMapper.toTempOrderDsVest(vest);
        tempVest.updateOrderCommon(orderCommon);
        tempOrderDsVestRepository.save(tempVest);
    }

    private void setOrderDsCoatData(TempOrderCommon orderCommon, Long counselingCommonId) {
        CounselingCoat coat = counselingCoatRepository.findByCounselingCommonId(counselingCommonId);
        TempOrderDsCoat tempCoat = mapStructMapper.toTempOrderDsCoat(coat);
        tempCoat.updateOrderCommon(orderCommon);
        tempOrderDsCoatRepository.save(tempCoat);
    }
}
