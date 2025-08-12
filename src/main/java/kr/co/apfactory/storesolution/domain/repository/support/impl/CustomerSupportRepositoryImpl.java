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
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;

        List<ResCustomerDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , customer.name2
                                , customer.mobile2
                                , orderConsulting.completed
                                , orderConsulting.id.as("orderConsultingId")
                        )
                )
                .from(customer)
                .leftJoin(orderConsulting).on(customer.eq(orderConsulting.customer))
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
    public ResCustomerDTO selectCustomerDetailByOrderConsultingId(Long orderConsultingId) {
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;
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
                                , orderConsulting.completed
                                , orderConsulting.id.as("orderConsultingId")
                        )
                )
                .from(orderConsulting)
                .innerJoin(customer).on(orderConsulting.customer.eq(customer))
                .innerJoin(reservation).on(orderConsulting.eq(reservation.orderConsulting))
                .where(
                        customer.deleted.eq(false)
                                .and(orderConsulting.id.eq(orderConsultingId))
                )
                .orderBy()
                .fetchFirst();

        return result;
    }

    @Override
    public Page<ResCustomerDTO> selectCustomerList(Pageable pageable, SearchDTO searchDTO, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;
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
                                , orderConsulting.completed
                                , orderConsulting.id.as("orderConsultingId")
                                , reservation.id.as("reservationId")
                                , reservation.consultingDate
                        )
                )
                .from(customer)
                .innerJoin(orderConsulting).on(customer.eq(orderConsulting.customer))
                .innerJoin(reservation).on(orderConsulting.eq(reservation.orderConsulting))
                .innerJoin(reservationManger).on(reservation.reservationManager.eq(reservationManger))
                .innerJoin(consultingManger).on(reservation.reservationManager.eq(consultingManger))
//                .from(reservation)
//                .innerJoin(reservationManger).on(reservation.reservationManager.eq(reservationManger))
//                .innerJoin(consultingManger).on(reservation.reservationManager.eq(consultingManger))
//                .innerJoin(orderConsulting).on(reservation.orderConsulting.eq(orderConsulting))
//                .innerJoin(customer).on(orderConsulting.customer.eq(customer))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(reservation.insertDatetime.desc())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ResCustomerDTO selectCustomerDetail(Long customerId) {
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;
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
                                , orderConsulting.completed
                                , orderConsulting.id.as("orderConsultingId")
                        )
                )
                .from(customer)
                .innerJoin(orderConsulting).on(customer.eq(orderConsulting.customer))
                .innerJoin(reservation).on(orderConsulting.eq(reservation.orderConsulting))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.id.eq(customerId))
                )
                .orderBy()
                .fetchFirst();

        return result;
    }

    @Override
    public ResCounselingDTO selectCounselingDetail(Long customerId) {
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;
        QOrderStore orderStore = QOrderStore.orderStore;

        ResCounselingDTO result = queryFactory.select(
                        Projections.fields(
                                ResCounselingDTO.class
                                , customer.id.as("customerId")
                                , orderConsulting.id.as("orderConsultingId")
                                , reservation.id.as("reservationId")
                                , customer.name1
                                , customer.mobile1
                                , orderStore.fabricCompany
                                , orderStore.fabricPattern
                                , orderStore.fabricColor
                        )
                )
                .from(customer)
                .innerJoin(orderConsulting).on(customer.eq(orderConsulting.customer))
                .innerJoin(reservation).on(orderConsulting.eq(reservation.orderConsulting))
                .leftJoin(orderStore).on(customer.eq(orderStore.customer).and(orderStore.canceled.isFalse()))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.id.eq(customerId))
                )
                .orderBy()
                .fetchFirst();

        return result;
    }
}
