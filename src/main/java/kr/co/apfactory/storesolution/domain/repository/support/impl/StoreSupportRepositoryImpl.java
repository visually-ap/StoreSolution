package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.StoreSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


// querydsl 사용
@RequiredArgsConstructor
public class StoreSupportRepositoryImpl implements StoreSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public ResStoreInfoDTO selectStoreInfo(Long userId) {
        QUser user = QUser.user;
        QStore store = QStore.store;

        ResStoreInfoDTO result = queryFactory.select(
                        Projections.fields(
                                ResStoreInfoDTO.class
                                , store.name.as("storeName")
                                , store.businessRegistrationNumber
                                , user.name.as("ceoName")
                                , Expressions.stringTemplate("concat({0}, ' ', {1})", store.address, store.addressDetail).as("address")
                                , store.buyerCompanyName
                                , store.buyerCeoName
                                , store.buyerBusinessType
                                , store.buyerBusinessItem
                                , store.buyerEmail
                        )
                )
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .where(
                        user.deleted.eq(false)
                                .and(user.id.eq(userId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public ResEmployeeDetailDTO selectEmployeeDetail(String lang, Long userId, Long storeId) {
        QUser user = QUser.user;
        QCodeType position = QCodeType.codeType;

        ResEmployeeDetailDTO result = queryFactory
                .select(
                        Projections.fields(
                                ResEmployeeDetailDTO.class
                                , user.id.as("userId")
                                , user.loginId
                                , user.name
                                , user.mobileNumber
                                , position.id.as("position")
                                , filterManager.getLanguageStringPath(lang, position, "typeName").coalesce("").as("positionName")
                                , user.enabled
                                , user.insertDatetime
                        )
                )
                .from(user)
                .leftJoin(user.position, position)
                .where(
                        user.deleted.isFalse()
                                .and(user.store.id.eq(storeId))
                                .and(user.id.eq(userId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResEmployeeListDTO> selectEmployeeList(Long storeId) {
        QUser user = QUser.user;
        QStore store = QStore.store;

        List<ResEmployeeListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResEmployeeListDTO.class
                                , user.id.as("userId")
                                , user.name
                        )
                )
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .where(
                        user.deleted.eq(false)
                                .and(user.store.id.eq(storeId))
                )
                .orderBy(user.name.desc())
                .fetch();

        return results;
    }

    @Override
    public List<ResEmployeeScheduleDTO> selectEmployeeScheduleList(Long storeId, LocalDate date) {
        QUser user = QUser.user;
        QStore store = QStore.store;
        QReservation reservation = QReservation.reservation;
        QCustomer customer = QCustomer.customer;
        QSiteEnvSetting siteEnvSetting = QSiteEnvSetting.siteEnvSetting;

        List<ResEmployeeScheduleDTO> results = queryFactory
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .innerJoin(siteEnvSetting).on(store.eq(siteEnvSetting.store))
                .leftJoin(reservation).on(user.eq(reservation.consultingManager).and(reservation.deleted.isFalse()).and(reservation.consultingDate.eq(date)))
                .leftJoin(customer).on(reservation.customer.eq(customer))
                .where(
                        user.deleted.eq(false)
                                .and(user.store.id.eq(storeId))
                )
                .orderBy(user.name.asc(), reservation.consultingDatetimeFrom.asc())
                .transform(
                        GroupBy.groupBy(user.id).list(
                                Projections.fields(
                                        ResEmployeeScheduleDTO.class
                                        , user.id.as("userId")
                                        , user.name
                                        , GroupBy.list(
                                                Projections.fields(
                                                        ResReservationDTO.class
                                                        , reservation.id.as("reservationId")
                                                        , reservation.allDay
                                                        , reservation.consultingDatetimeFrom
                                                        , reservation.consultingDatetimeTo
                                                        , reservation.type
                                                        , reservation.contract
                                                        , reservation.completed
                                                        , reservation.started
                                                        , customer.name1.as("customerName")
                                                        , customer.id.as("customerId")
                                                        , new CaseBuilder()
                                                                .when(reservation.type.eq(2)).then(siteEnvSetting.typeName2)
                                                                .when(reservation.type.eq(3)).then(siteEnvSetting.typeName3)
                                                                .when(reservation.type.eq(4)).then(siteEnvSetting.typeName4)
                                                                .when(reservation.type.eq(5)).then(siteEnvSetting.typeName5)
                                                                .when(reservation.type.eq(6)).then(siteEnvSetting.typeName6)
                                                                .otherwise(Expressions.constant("맞춤상담"))
                                                                .as("typeString")
                                                )
                                        ).as("reservationList")
                                )
                        )
                );

        for (ResEmployeeScheduleDTO dto : results) {
            List<ResReservationDTO> filtered = dto.getReservationList().stream()
                    .filter(res -> res.getReservationId() != null)
                    .collect(Collectors.toList());
            dto.setReservationList(filtered);
        }

        return results;
    }

    @Override
    public Page<ResEmployeeListDTO> selectEmployeeList(String lang, Pageable pageable, SearchDTO searchDTO, Long storeId) {
        QUser user = QUser.user;
        QStore store = QStore.store;
        QCodeType position = QCodeType.codeType;

        List<OrderSpecifier> ORDERS = sortManager.getEmployeeListOrderSpecifiers(pageable);

        QueryResults<ResEmployeeListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResEmployeeListDTO.class
                                , user.id.as("userId")
                                , user.loginId
                                , user.mobileNumber
                                , user.insertDatetime
                                , user.name
                                , filterManager.getLanguageStringPath(lang, position, "typeName").coalesce("").as("position")
                        )
                )
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .leftJoin(position).on(user.position.eq(position))
                .where(
                        user.deleted.eq(false)
                                .and(user.store.id.eq(storeId))
                                .and(filterManager.getEmployeeListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getEmployeeListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public Page<ResConsultingPartnerListDTO> selectConsultingPartnerList(String lang, Pageable pageable, SearchDTO searchDTO, Long storeId) {
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;

        List<OrderSpecifier> ORDERS = sortManager.getConsultingPartnerListOrderSpecifiers(pageable);

        QueryResults<ResConsultingPartnerListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResConsultingPartnerListDTO.class
                                , consultingPartner.id.as("partnerId")
                                , consultingPartner.name
                                , consultingPartner.charge
                                , consultingPartner.insertDatetime
                        )
                )
                .from(consultingPartner)
                .where(
                        consultingPartner.deleted.eq(false)
                                .and(consultingPartner.store.id.eq(storeId))
                                .and(filterManager.getConsultingPartnerListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getConsultingPartnerListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ResConsultingPartnerDetailDTO selectConsultingPartnerDetail(Long partnerId, Long storeId) {
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;

        ResConsultingPartnerDetailDTO result = queryFactory
                .select(
                        Projections.fields(
                                ResConsultingPartnerDetailDTO.class
                                , consultingPartner.id.as("partnerId")
                                , consultingPartner.name
                                , consultingPartner.charge
                                , consultingPartner.memo
                        )
                )
                .from(consultingPartner)
                .where(
                        consultingPartner.deleted.isFalse()
                                .and(consultingPartner.store.id.eq(storeId))
                                .and(consultingPartner.id.eq(partnerId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResConsultingPartnerPicDTO> selectConsultingPartnerPicList(Long partnerId) {
        QConsultingPartnerPic consultingPartnerPic = QConsultingPartnerPic.consultingPartnerPic;

        List<ResConsultingPartnerPicDTO> results = queryFactory
                .select(
                        Projections.fields(
                                ResConsultingPartnerPicDTO.class
                                , consultingPartnerPic.id.as("picId")
                                , consultingPartnerPic.consultingPartner.id.as("partnerId")
                                , consultingPartnerPic.name
                                , consultingPartnerPic.contact
                                , consultingPartnerPic.insertDatetime
                        )
                )
                .from(consultingPartnerPic)
                .where(
                        consultingPartnerPic.deleted.isFalse()
                                .and(consultingPartnerPic.consultingPartner.id.eq(partnerId))
                )
                .orderBy(consultingPartnerPic.name.asc())
                .fetch();

        return results;
    }

    @Override
    public ResConsultingPartnerPicDTO selectConsultingPartnerPicDetail(Long picId) {
        QConsultingPartnerPic consultingPartnerPic = QConsultingPartnerPic.consultingPartnerPic;

        ResConsultingPartnerPicDTO result = queryFactory
                .select(
                        Projections.fields(
                                ResConsultingPartnerPicDTO.class
                                , consultingPartnerPic.id.as("picId")
                                , consultingPartnerPic.contact
                        )
                )
                .from(consultingPartnerPic)
                .where(
                        consultingPartnerPic.deleted.isFalse()
                                .and(consultingPartnerPic.id.eq(picId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResConsultingPartnerListDTO> selectConsultingPartnerList(Long storeId) {
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;

        List<ResConsultingPartnerListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResConsultingPartnerListDTO.class
                                , consultingPartner.id.as("partnerId")
                                , consultingPartner.name
                                , consultingPartner.charge
                                , consultingPartner.insertDatetime
                        )
                )
                .from(consultingPartner)
                .where(
                        consultingPartner.deleted.eq(false)
                                .and(consultingPartner.store.id.eq(storeId))
                )
                .orderBy(consultingPartner.name.asc())
                .fetch();
        return results;
    }

    @Override
    public Page<ResRentalItemListDTO> selectRentalItemList(Pageable pageable, SearchDTO searchDTO, Long storeId) {
        QRentalItem rentalItem = QRentalItem.rentalItem;

        List<OrderSpecifier> ORDERS = sortManager.getRentalItemListOrderSpecifiers(pageable);

        QueryResults<ResRentalItemListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResRentalItemListDTO.class
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name
                                , rentalItem.size
                                , rentalItem.barcode
                                , rentalItem.renting
                                , rentalItem.memo
                                , rentalItem.insertDatetime
                        )
                )
                .from(rentalItem)
                .where(
                        rentalItem.deleted.eq(false)
                                .and(rentalItem.store.id.eq(storeId))
                                .and(filterManager.getRentalItemListBooleanBuilderByKeyword(searchDTO.getSearchKeyword()))
                                .and(filterManager.getRentalItemListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ResRentalItemDTO selectRentalItemDetail(Long rentalItemId, Long storeId) {
        QRentalItem rentalItem = QRentalItem.rentalItem;

        ResRentalItemDTO result = queryFactory
                .select(
                        Projections.fields(
                                ResRentalItemDTO.class
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name
                                , rentalItem.size
                                , rentalItem.barcode
                                , rentalItem.renting
                                , rentalItem.memo
                        )
                )
                .from(rentalItem)
                .where(
                        rentalItem.deleted.isFalse()
                                .and(rentalItem.store.id.eq(storeId))
                                .and(rentalItem.id.eq(rentalItemId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResRentalItemListDTO> selectRentalItemList(LocalDate fromDate, LocalDate requestDate, String searchKeyword, Long storeId) {
        QRentalItem rentalItem = QRentalItem.rentalItem;
        QRental rental = QRental.rental;

        List<ResRentalItemListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResRentalItemListDTO.class
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name
                                , rentalItem.size
                                , rentalItem.barcode
                                , rentalItem.renting
                                , rentalItem.name
                                , rentalItem.memo
                                , rentalItem.insertDatetime
                        )
                )
                .from(rentalItem)
                .where(
                        rentalItem.deleted.eq(false)
                                .and(rentalItem.store.id.eq(storeId))
                                .and(filterManager.getRentalItemListBooleanBuilderByKeyword(searchKeyword))
                                .and(
                                        JPAExpressions
                                                .selectOne()
                                                .from(rental)
                                                .where(
                                                        rental.rentalItem.eq(rentalItem)
                                                                .and(rental.deleted.eq(false))
                                                                .and(
                                                                        // 예정 반납일과 겹치는 경우
                                                                        (rental.fromDate.loe(requestDate)
                                                                                .and(rental.requestDate.goe(fromDate)))
                                                                                .or(
                                                                                        // 실제 반납일과 겹치는 경우
                                                                                        rental.fromDate.loe(requestDate)
                                                                                                .and(rental.toDate.goe(fromDate))
                                                                                )
                                                                )
                                                )
                                                .notExists()
                                )
                )
                .orderBy()
                .fetch();

        return results;
    }

    @Override
    public List<ResRentalItemListDTO> selectRentalItemList(LocalDate fromDate, LocalDate requestDate, String searchKeyword, Long storeId, Long rentalId) {
        QRentalItem rentalItem = QRentalItem.rentalItem;
        QRental rental = QRental.rental;

        List<ResRentalItemListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResRentalItemListDTO.class
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name
                                , rentalItem.size
                                , rentalItem.barcode
                                , rentalItem.renting
                                , rentalItem.name
                                , rentalItem.memo
                                , rentalItem.insertDatetime
                        )
                )
                .from(rentalItem)
                .where(
                        rentalItem.deleted.eq(false)
                                .and(rentalItem.store.id.eq(storeId))
                                .and(filterManager.getRentalItemListBooleanBuilderByKeyword(searchKeyword))
                                .and(
                                        JPAExpressions
                                                .selectOne()
                                                .from(rental)
                                                .where(
                                                        rental.rentalItem.eq(rentalItem)
                                                                .and(rental.deleted.eq(false))
                                                                .and(rental.id.ne(rentalId))
                                                                .and(
                                                                        // 예정 반납일과 겹치는 경우
                                                                        (rental.fromDate.loe(requestDate)
                                                                                .and(rental.requestDate.goe(fromDate)))
                                                                                .or(
                                                                                        // 실제 반납일과 겹치는 경우
                                                                                        rental.fromDate.loe(requestDate)
                                                                                                .and(rental.toDate.goe(fromDate))
                                                                                )
                                                                )
                                                )
                                                .notExists()
                                )
                )
                .orderBy()
                .fetch();

        return results;
    }
}
