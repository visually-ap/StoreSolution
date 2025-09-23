package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


// querydsl 사용
@RequiredArgsConstructor
public class CustomerSupportRepositoryImpl implements CustomerSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public List<ResCustomerDTO> selectCustomerList(String searchKeyword, LocalDate searchDate, Long storeId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO) {
        QReservation reservation = QReservation.reservation;
        QCustomer customer = QCustomer.customer;

        List<ResCustomerDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , customer.name2
                                , customer.mobile2
                                , customer.photoDate
                                , customer.weddingDate
                                , Expressions.stringTemplate("DATE_FORMAT({0}, {1})", reservation.insertDatetime, ConstantImpl.create("%Y-%m-%d")).as("insertDate")
                                , reservation.consultingDate
                                , reservation.consultingDate
                                , reservation.completed
                                , reservation.id.as("reservationId")
                                , new CaseBuilder()
                                        .when(reservation.type.eq(1)).then(Expressions.constant("맞춤상담"))
                                        .when(reservation.type.eq(2)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName2()))
                                        .when(reservation.type.eq(3)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName3()))
                                        .when(reservation.type.eq(4)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName4()))
                                        .when(reservation.type.eq(5)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName5()))
                                        .when(reservation.type.eq(6)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName6()))
                                        .otherwise(Expressions.constant("맞춤상담"))
                                        .as("reservationTypeString")
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(reservation.customer.eq(customer))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(filterManager.getReservationCustomerListBooleanBuilderByKeyword(searchKeyword))
                                .and(filterManager.getReservationCustomerListBooleanBuilderByDate(searchDate))
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
                                , reservation.type
                                , reservation.allDay.as("isAllday")
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
        QCounselingCommon counselingCommon = QCounselingCommon.counselingCommon;

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
                                , reservation.completed
                        )
                )
                .from(customer)
                .innerJoin(reservation).on(customer.eq(reservation.customer))
                .innerJoin(reservationManger).on(reservation.reservationManager.eq(reservationManger))
                .innerJoin(consultingManger).on(reservation.reservationManager.eq(consultingManger))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByState(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(reservation.insertDatetime.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
