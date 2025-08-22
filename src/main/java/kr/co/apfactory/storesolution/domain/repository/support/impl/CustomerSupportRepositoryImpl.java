package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


// querydsl 사용
@RequiredArgsConstructor
public class CustomerSupportRepositoryImpl implements CustomerSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public List<ResCustomerDTO> selectCustomerList(String searchKeyword, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;

        List<ResCustomerDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , customer.name2
                                , customer.mobile2
                                , reservation.completed
                                , reservation.id.as("reservationId")
                        )
                )
                .from(customer)
                .leftJoin(reservation).on(customer.eq(reservation.customer))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(filterManager.getReservationCustomerListBooleanBuilderByKeyword(searchKeyword))
                )
                .orderBy()
                .fetch();

        return results;
    }

    @Override
    public ResCustomerDTO selectCustomerDetailByReservationId(Long reservationId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;

        ResCustomerDTO result = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , customer.name2
                                , customer.mobile2
                                , customer.photoDate
                                , customer.photoPlace
                                , customer.weddingDate
                                , customer.weddingPlace
                                , customer.memo
                                , reservation.consultingManager.id.as("consultingManagerId")
                                , reservation.reservationManager.id.as("reservationManagerId")
                                , reservation.consultingDate
                                , reservation.consultingDatetimeFrom
                                , reservation.completed
                                , reservation.id.as("reservationId")
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(reservation.customer.eq(customer))
                .where(
                        customer.deleted.eq(false)
                                .and(reservation.id.eq(reservationId))
                )
                .orderBy()
                .fetchFirst();

        return result;
    }

    @Override
    public Page<ResCustomerDTO> selectCustomerList(Pageable pageable, SearchDTO searchDTO, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;
        QUser reservationManger = new QUser("reservationManger");
        QUser consultingManger = new QUser("consultingManger");

        QueryResults<ResCustomerDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , customer.name2
                                , customer.mobile2
                                , customer.photoDate
                                , customer.photoPlace
                                , customer.weddingDate
                                , customer.weddingPlace
                                , customer.memo
                                , reservationManger.name.as("reservationManagerName")
                                , consultingManger.name.as("consultingManagerName")
                                , reservation.id.as("reservationId")
                                , reservation.consultingDate
                        )
                )
                .from(customer)
                .innerJoin(reservation).on(customer.eq(reservation.customer))
                .innerJoin(reservationManger).on(reservation.reservationManager.eq(reservationManger))
                .innerJoin(consultingManger).on(reservation.reservationManager.eq(consultingManger))
                .where(
                        customer.deleted.eq(false)
                                .and(reservation.completed.isFalse())
                                .and(customer.store.id.eq(storeId))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(reservation.insertDatetime.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ResCounselingDTO selectCounselingDetail(Long reservationId) {
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
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(customer.eq(reservation.customer))
                .leftJoin(counselingCommon).on(reservation.eq(counselingCommon.reservation).and(counselingCommon.canceled.isFalse()))
                .where(
                        customer.deleted.eq(false)
                                .and(reservation.id.eq(reservationId))
                )
                .orderBy()
                .fetchOne();

        return result;
    }
}
