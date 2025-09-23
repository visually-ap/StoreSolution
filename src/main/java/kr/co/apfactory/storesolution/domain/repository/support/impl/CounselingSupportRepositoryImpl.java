package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


// querydsl 사용
@RequiredArgsConstructor
public class CounselingSupportRepositoryImpl implements CounselingSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public ResCounselingDTO selectCounselingDetail(Long storeId, Long reservationId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;
        QCounselingCommon counselingCommon = QCounselingCommon.counselingCommon;

        ResCounselingDTO result = queryFactory.select(
                        Projections.fields(
                                ResCounselingDTO.class
                                , customer.id.as("customerId")
                                , reservation.id.as("reservationId")
                                , counselingCommon.id.as("counselingCommonId")
                                , customer.name1.as("name")
                                , customer.mobile1.as("mobile")
                                , counselingCommon.workType.id.as("workType")
                                , counselingCommon.factory
                                , counselingCommon.jacket
                                , counselingCommon.pants
                                , counselingCommon.vest
                                , counselingCommon.coat
                                , counselingCommon.fabricCompanyJacket
                                , counselingCommon.fabricPatternJacket
                                , counselingCommon.fabricColorJacket
                                , counselingCommon.fabricCompanyPants
                                , counselingCommon.fabricPatternPants
                                , counselingCommon.fabricColorPants
                                , counselingCommon.fabricCompanyVest
                                , counselingCommon.fabricPatternVest
                                , counselingCommon.fabricColorVest
                                , counselingCommon.fabricCompanyCoat
                                , counselingCommon.fabricPatternCoat
                                , counselingCommon.fabricColorCoat
                                , counselingCommon.allSameFabric
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(customer.eq(reservation.customer))
                .leftJoin(counselingCommon).on(
                        reservation.eq(counselingCommon.reservation)
                                .and(counselingCommon.canceled.isFalse())
                                .and(counselingCommon.store.id.eq(storeId))
                )
                .where(
                        customer.deleted.eq(false)
                                .and(reservation.id.eq(reservationId))
                )
                .orderBy()
                .fetchOne();

        return result;
    }

    @Override
    public ResDesignJacketDTO selectCounselingJacketDesignDetail(Long counselingId) {
        QCounselingJacket counselingJacket = QCounselingJacket.counselingJacket;

        ResDesignJacketDTO result = queryFactory.select(
                        Projections.fields(
                                ResDesignJacketDTO.class
                                , counselingJacket.id
                                , counselingJacket.jacketStyle.id.as("jacketStyle")
                                , counselingJacket.jacketButtonCount.id.as("jacketButtonCount")
                                , counselingJacket.jacketChestPocket.id.as("jacketChestPocket")
                                , counselingJacket.jacketShoulder.id.as("jacketShoulder")
                                , counselingJacket.jacketFrontPocket.id.as("jacketFrontPocket")
                                , counselingJacket.jacketLeftPad.id.as("jacketLeftPad")
                                , counselingJacket.jacketBackSlit.id.as("jacketBackSlit")
                                , counselingJacket.jacketRightPad.id.as("jacketRightPad")
                                , counselingJacket.jacketSleeveButton.id.as("jacketSleeveButton")
                                , counselingJacket.jacketLapel.id.as("jacketLapel")
                                , counselingJacket.jacketSleeve.id.as("jacketSleeve")
                                , counselingJacket.jacketTuxedo.id.as("jacketTuxedo")
                                , counselingJacket.jacketAmf.id.as("jacketAmf")
                                , counselingJacket.jacketLapelQ.id.as("jacketLapelQ")
                                , counselingJacket.jacketLining.id.as("jacketLining")
                                , counselingJacket.jacketCheckFabric.id.as("jacketCheckFabric")
                                , counselingJacket.jacketAddOption.id.as("jacketAddOption")
                        )
                )
                .from(counselingJacket)
                .where(
                        counselingJacket.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();

        return result;
    }

    @Override
    public ResDesignPantsDTO selectCounselingPantsDesignDetail(Long counselingId) {
        QCounselingPants counselingPants = QCounselingPants.counselingPants;

        ResDesignPantsDTO result = queryFactory.select(
                        Projections.fields(
                                ResDesignPantsDTO.class
                                , counselingPants.id
                                , counselingPants.pantsPattern.id.as("pantsPattern")
                                , counselingPants.pantsBeltLoop.id.as("pantsBeltLoop")
                                , counselingPants.pantsPocket.id.as("pantsPocket")
                                , counselingPants.pantsHemShape.id.as("pantsHemShape")
                                , counselingPants.pantsHemThickness.id.as("pantsHemThickness")
                                , counselingPants.pantsFlap.id.as("pantsFlap")
                                , counselingPants.pantsFlapLength.id.as("pantsFlapLength")
                                , counselingPants.pantsCheckFabric.id.as("pantsCheckFabric")
                                , counselingPants.pantsAddOption1.id.as("pantsAddOption1")
                                , counselingPants.pantsAddOption2.id.as("pantsAddOption2")
                                , counselingPants.pantsAddOption3.id.as("pantsAddOption3")
                        )
                )
                .from(counselingPants)
                .where(
                        counselingPants.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();
        return result;
    }

    @Override
    public ResDesignVestDTO selectCounselingVestDesignDetail(Long counselingId) {
        QCounselingVest counselingVest = QCounselingVest.counselingVest;

        ResDesignVestDTO result = queryFactory.select(
                        Projections.fields(
                                ResDesignVestDTO.class
                                , counselingVest.id
                                , counselingVest.vestPattern.id.as("vestPattern")
                                , counselingVest.vestBack.id.as("vestBack")
                                , counselingVest.vestFrontPocket.id.as("vestFrontPocket")
                                , counselingVest.vestChestPocket.id.as("vestChestPocket")
                                , counselingVest.vestLapel.id.as("vestLapel")
                                , counselingVest.vestAmf.id.as("vestAmf")
                        )
                )
                .from(counselingVest)
                .where(
                        counselingVest.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();
        return result;
    }

    @Override
    public ResDesignCoatDTO selectCounselingCoatDesignDetail(Long counselingId) {
        QCounselingCoat counselingCoat = QCounselingCoat.counselingCoat;

        ResDesignCoatDTO result = queryFactory.select(
                        Projections.fields(
                                ResDesignCoatDTO.class
                                , counselingCoat.id
                                , counselingCoat.coatStyle.id.as("coatStyle")
                                , counselingCoat.coatButtonCount.id.as("coatButtonCount")
                                , counselingCoat.coatChestPocket.id.as("coatChestPocket")
                                , counselingCoat.coatShoulder.id.as("coatShoulder")
                                , counselingCoat.coatFrontPocket.id.as("coatFrontPocket")
                                , counselingCoat.coatLeftPad.id.as("coatLeftPad")
                                , counselingCoat.coatBackSlit.id.as("coatBackSlit")
                                , counselingCoat.coatRightPad.id.as("coatRightPad")
                                , counselingCoat.coatSleeveButton.id.as("coatSleeveButton")
                                , counselingCoat.coatLapel.id.as("coatLapel")
                                , counselingCoat.coatSleeve.id.as("coatSleeve")
                                , counselingCoat.coatAmf.id.as("coatAmf")
                                , counselingCoat.coatLapelQ.id.as("coatLapelQ")
                                , counselingCoat.coatLining.id.as("coatLining")
                                , counselingCoat.coatCheckFabric.id.as("coatCheckFabric")
                                , counselingCoat.coatAddOption1.id.as("coatAddOption1")
                                , counselingCoat.coatAddOption2.id.as("coatAddOption2")
                        )
                )
                .from(counselingCoat)
                .where(
                        counselingCoat.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();

        return result;
    }

    @Override
    public ResSizeJacketDTO selectCounselingJacketSizeDetail(Long counselingId) {
        QCounselingJacket counselingJacket = QCounselingJacket.counselingJacket;

        ResSizeJacketDTO result = queryFactory.select(
                        Projections.fields(
                                ResSizeJacketDTO.class
                                , counselingJacket.id
                                , counselingJacket.jacketPattern.id.as("jacketPattern")
                                , counselingJacket.jacketGauge.id.as("jacketGauge")
                                , counselingJacket.jacketSizeFrontRound
                                , counselingJacket.jacketSizeBackRound
                                , counselingJacket.jacketSizeShoulderSlope
                                , counselingJacket.jacketSizeBackNeckline
                                , counselingJacket.jacketSizeArmHole
                                , counselingJacket.jacketSizeBackNeckPointMove
                                , counselingJacket.jacketSizeUpperArm
                                , counselingJacket.jacketSizeMiddleArm
                                , counselingJacket.jacketSizeLowerArm
                                , counselingJacket.jacketSizeFrontLength
                                , counselingJacket.jacketSizeBackCenterMove
                                , counselingJacket.jacketSizeBackCenterShorten
                                , counselingJacket.jacketSizeFirstButton
                                , counselingJacket.jacketSizeNeckRound
                                , counselingJacket.jacketSizeHemLine
                                , counselingJacket.jacketSizeChestLine
                                , counselingJacket.jacketSizeRealInchChest
                                , counselingJacket.jacketSizeRealCentiChest
                                , counselingJacket.jacketSizeEditChest
                                , counselingJacket.jacketSizeRealInchChestWidth
                                , counselingJacket.jacketSizeRealCentiChestWidth
                                , counselingJacket.jacketSizeEditChestWidth
                                , counselingJacket.jacketSizeRealInchUpperSideBody
                                , counselingJacket.jacketSizeRealCentiUpperSideBody
                                , counselingJacket.jacketSizeEditUpperSideBody
                                , counselingJacket.jacketSizeRealInchBackWidth
                                , counselingJacket.jacketSizeRealCentiBackWidth
                                , counselingJacket.jacketSizeEditBackWidth
                                , counselingJacket.jacketSizeRealInchWaist
                                , counselingJacket.jacketSizeRealCentiWaist
                                , counselingJacket.jacketSizeEditWaist
                                , counselingJacket.jacketSizeRealInchWaistFront
                                , counselingJacket.jacketSizeRealCentiWaistFront
                                , counselingJacket.jacketSizeEditWaistFront
                                , counselingJacket.jacketSizeRealInchMiddleSideBody
                                , counselingJacket.jacketSizeRealCentiMiddleSideBody
                                , counselingJacket.jacketSizeEditMiddleSideBody
                                , counselingJacket.jacketSizeRealInchWaistBack
                                , counselingJacket.jacketSizeRealCentiWaistBack
                                , counselingJacket.jacketSizeEditWaistBack
                                , counselingJacket.jacketSizeRealInchShoulder
                                , counselingJacket.jacketSizeRealCentiShoulder
                                , counselingJacket.jacketSizeEditShoulder
                                , counselingJacket.jacketSizeRealInchLeftSleeve
                                , counselingJacket.jacketSizeRealCentiLeftSleeve
                                , counselingJacket.jacketSizeEditLeftSleeve
                                , counselingJacket.jacketSizeRealInchRightSleeve
                                , counselingJacket.jacketSizeRealCentiRightSleeve
                                , counselingJacket.jacketSizeEditRightSleeve
                                , counselingJacket.jacketSizeRealInchBackLength
                                , counselingJacket.jacketSizeRealCentiBackLength
                                , counselingJacket.jacketSizeEditBackLength
                                , counselingJacket.jacketSizeRealInchFrontLength
                                , counselingJacket.jacketSizeRealCentiFrontLength
                                , counselingJacket.jacketSizeEditFrontLength
                        )
                )
                .from(counselingJacket)
                .where(
                        counselingJacket.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();

        return result;
    }

    @Override
    public ResSizePantsDTO selectCounselingPantsSizeDetail(Long counselingId) {
        QCounselingPants counselingPants = QCounselingPants.counselingPants;

        ResSizePantsDTO result = queryFactory.select(
                        Projections.fields(
                                ResSizePantsDTO.class
                                , counselingPants.id
                                , counselingPants.pantsPattern.id.as("pantsPattern")
                                , counselingPants.pantsGauge.id.as("pantsGauge")
                                , counselingPants.pantsFrontRound.as("pantsFrontRound")
                                , counselingPants.pantsBackRound.as("pantsBackRound")
                                , counselingPants.pantsO.as("pantsO")
                                , counselingPants.pantsSizeRealInchWaist.as("pantsSizeRealInchWaist")
                                , counselingPants.pantsSizeRealCentiWaist.as("pantsSizeRealCentiWaist")
                                , counselingPants.pantsSizeEditWaist.as("pantsSizeEditWaist")
                                , counselingPants.pantsSizeEditDetailFrontWaist.as("pantsSizeEditDetailFrontWaist")
                                , counselingPants.pantsSizeEditDetailBackWaist.as("pantsSizeEditDetailBackWaist")
                                , counselingPants.pantsSizeRealInchHip.as("pantsSizeRealInchHip")
                                , counselingPants.pantsSizeRealCentiHip.as("pantsSizeRealCentiHip")
                                , counselingPants.pantsSizeEditHip.as("pantsSizeEditHip")
                                , counselingPants.pantsSizeEditDetailFrontHip.as("pantsSizeEditDetailFrontHip")
                                , counselingPants.pantsSizeEditDetailBackHip.as("pantsSizeEditDetailBackHip")
                                , counselingPants.pantsSizeRealInchThigh.as("pantsSizeRealInchThigh")
                                , counselingPants.pantsSizeRealCentiThigh.as("pantsSizeRealCentiThigh")
                                , counselingPants.pantsSizeEditThigh.as("pantsSizeEditThigh")
                                , counselingPants.pantsSizeEditDetailFrontThigh.as("pantsSizeEditDetailFrontThigh")
                                , counselingPants.pantsSizeEditDetailBackThigh.as("pantsSizeEditDetailBackThigh")
                                , counselingPants.pantsSizeRealInchRise.as("pantsSizeRealInchRise")
                                , counselingPants.pantsSizeRealCentiRise.as("pantsSizeRealCentiRise")
                                , counselingPants.pantsSizeEditRise.as("pantsSizeEditRise")
                                , counselingPants.pantsSizeDetailFrontRise.as("pantsSizeDetailFrontRise")
                                , counselingPants.pantsSizeDetailBackRise.as("pantsSizeDetailBackRise")
                                , counselingPants.pantsSizeRealInchInseam.as("pantsSizeRealInchInseam")
                                , counselingPants.pantsSizeRealCentiInseam.as("pantsSizeRealCentiInseam")
                                , counselingPants.pantsSizeEditInseam.as("pantsSizeEditInseam")
                                , counselingPants.pantsSizeDetailFrontInseam.as("pantsSizeDetailFrontInseam")
                                , counselingPants.pantsSizeDetailBackInseam.as("pantsSizeDetailBackInseam")
                                , counselingPants.pantsSizeRealInchNee.as("pantsSizeRealInchNee")
                                , counselingPants.pantsSizeRealCentiNee.as("pantsSizeRealCentiNee")
                                , counselingPants.pantsSizeEditNee.as("pantsSizeEditNee")
                                , counselingPants.pantsSizeEditDetailFrontNee.as("pantsSizeEditDetailFrontNee")
                                , counselingPants.pantsSizeEditDetailBackNee.as("pantsSizeEditDetailBackNee")
                                , counselingPants.pantsSizeRealInchOpening.as("pantsSizeRealInchOpening")
                                , counselingPants.pantsSizeRealCentiOpening.as("pantsSizeRealCentiOpening")
                                , counselingPants.pantsSizeEditOpening.as("pantsSizeEditOpening")
                                , counselingPants.pantsSizeEditDetailFrontOpening.as("pantsSizeEditDetailFrontOpening")
                                , counselingPants.pantsSizeEditDetailBackOpening.as("pantsSizeEditDetailBackOpening")
                                , counselingPants.pantsSizeRealInchGarmentLength.as("pantsSizeRealInchGarmentLength")
                                , counselingPants.pantsSizeRealCentiGarmentLength.as("pantsSizeRealCentiGarmentLength")
                                , counselingPants.pantsSizeEditGarmentLength.as("pantsSizeEditGarmentLength")
                        )
                )
                .from(counselingPants)
                .where(
                        counselingPants.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();
        return result;
    }

    @Override
    public ResSizeVestDTO selectCounselingVestSizeDetail(Long counselingId) {
        QCounselingVest counselingVest = QCounselingVest.counselingVest;

        ResSizeVestDTO result = queryFactory.select(
                        Projections.fields(
                                ResSizeVestDTO.class
                                , counselingVest.id
                                , counselingVest.vestPattern.id.as("vestPattern")
                                , counselingVest.vestGauge.id.as("vestGauge")
                                , counselingVest.vestSizeRealInchBackLength
                                , counselingVest.vestSizeRealCentiBackLength
                                , counselingVest.vestSizeEditBackLength
                                , counselingVest.vestSizeRealInchChest
                                , counselingVest.vestSizeRealCentiChest
                                , counselingVest.vestSizeEditChest
                                , counselingVest.vestSizeRealInchWaist
                                , counselingVest.vestSizeRealCentiWaist
                                , counselingVest.vestSizeEditWaist
                                , counselingVest.vestSizeFrontRound.coalesce(new BigDecimal(0)).as("vestSizeFrontRound")
                                , counselingVest.vestSizeBackRound.coalesce(new BigDecimal(0)).as("vestSizeBackRound")
                                , counselingVest.vestSizeShoulderSlope.coalesce(new BigDecimal(0)).as("vestSizeShoulderSlope")
                                , counselingVest.vestSizeBackNeckline.coalesce(new BigDecimal(0)).as("vestSizeBackNeckline")
                                , counselingVest.vestSizeBackNeckPointMove.coalesce(new BigDecimal(0)).as("vestSizeBackNeckPointMove")
                                , counselingVest.vestSizeNeckRound.coalesce(new BigDecimal(0)).as("vestSizeNeckRound")
                                , counselingVest.vestSizeChestLine.coalesce(new BigDecimal(0)).as("vestSizeChestLine")
                                , counselingVest.vestSizeReverseZone.coalesce(new BigDecimal(0)).as("vestSizeReverseZone")
                                , counselingVest.vestSizeFrontLength.coalesce(new BigDecimal(0)).as("vestSizeFrontLength")
                                , counselingVest.vestSizeFirstButton.coalesce(new BigDecimal(0)).as("vestSizeFirstButton")
                                , counselingVest.vestSizeArmHole.coalesce(new BigDecimal(0)).as("vestSizeArmHole")
                        )
                )
                .from(counselingVest)
                .where(
                        counselingVest.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();
        return result;
    }

    @Override
    public ResSizeCoatDTO selectCounselingCoatSizeDetail(Long counselingId) {
        QCounselingCoat counselingCoat = QCounselingCoat.counselingCoat;

        ResSizeCoatDTO result = queryFactory.select(
                        Projections.fields(
                                ResSizeCoatDTO.class
                                , counselingCoat.id
                                , counselingCoat.coatPattern.id.as("coatPattern")
                                , counselingCoat.coatGauge.id.as("coatGauge")
                                , counselingCoat.coatSizeFrontRound
                                , counselingCoat.coatSizeBackRound
                                , counselingCoat.coatSizeShoulderSlope
                                , counselingCoat.coatSizeBackNeckline
                                , counselingCoat.coatSizeArmHole
                                , counselingCoat.coatSizeBackNeckPointMove
                                , counselingCoat.coatSizeUpperArm
                                , counselingCoat.coatSizeMiddleArm
                                , counselingCoat.coatSizeLowerArm
                                , counselingCoat.coatSizeFrontLength
                                , counselingCoat.coatSizeBackCenterMove
                                , counselingCoat.coatSizeBackCenterShorten
                                , counselingCoat.coatSizeFirstButton
                                , counselingCoat.coatSizeNeckRound
                                , counselingCoat.coatSizeHemLine
                                , counselingCoat.coatSizeChestLine
                                , counselingCoat.coatSizeRealInchChest
                                , counselingCoat.coatSizeRealCentiChest
                                , counselingCoat.coatSizeEditChest
                                , counselingCoat.coatSizeRealInchChestWidth
                                , counselingCoat.coatSizeRealCentiChestWidth
                                , counselingCoat.coatSizeEditChestWidth
                                , counselingCoat.coatSizeRealInchUpperSideBody
                                , counselingCoat.coatSizeRealCentiUpperSideBody
                                , counselingCoat.coatSizeEditUpperSideBody
                                , counselingCoat.coatSizeRealInchBackWidth
                                , counselingCoat.coatSizeRealCentiBackWidth
                                , counselingCoat.coatSizeEditBackWidth
                                , counselingCoat.coatSizeRealInchWaist
                                , counselingCoat.coatSizeRealCentiWaist
                                , counselingCoat.coatSizeEditWaist
                                , counselingCoat.coatSizeRealInchWaistFront
                                , counselingCoat.coatSizeRealCentiWaistFront
                                , counselingCoat.coatSizeEditWaistFront
                                , counselingCoat.coatSizeRealInchMiddleSideBody
                                , counselingCoat.coatSizeRealCentiMiddleSideBody
                                , counselingCoat.coatSizeEditMiddleSideBody
                                , counselingCoat.coatSizeRealInchWaistBack
                                , counselingCoat.coatSizeRealCentiWaistBack
                                , counselingCoat.coatSizeEditWaistBack
                                , counselingCoat.coatSizeRealInchShoulder
                                , counselingCoat.coatSizeRealCentiShoulder
                                , counselingCoat.coatSizeEditShoulder
                                , counselingCoat.coatSizeRealInchLeftSleeve
                                , counselingCoat.coatSizeRealCentiLeftSleeve
                                , counselingCoat.coatSizeEditLeftSleeve
                                , counselingCoat.coatSizeRealInchRightSleeve
                                , counselingCoat.coatSizeRealCentiRightSleeve
                                , counselingCoat.coatSizeEditRightSleeve
                                , counselingCoat.coatSizeRealInchBackLength
                                , counselingCoat.coatSizeRealCentiBackLength
                                , counselingCoat.coatSizeEditBackLength
                                , counselingCoat.coatSizeRealInchFrontLength
                                , counselingCoat.coatSizeRealCentiFrontLength
                                , counselingCoat.coatSizeEditFrontLength
                        )
                )
                .from(counselingCoat)
                .where(
                        counselingCoat.counselingCommon.id.eq(counselingId)
                )
                .fetchOne();

        return result;
    }
}
