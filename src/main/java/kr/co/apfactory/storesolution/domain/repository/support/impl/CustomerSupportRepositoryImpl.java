package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.request.ReqCustomerPurchaseDTO;
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
    public List<ResCustomerDTO> selectCustomerList(String sortString, String searchKeyword, LocalDate searchDate, Long storeId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO) {
        QReservation reservation = QReservation.reservation;
        QCustomer customer = QCustomer.customer;

        List<OrderSpecifier> ORDERS = sortManager.getCustomerListOrderSpecifiers(sortString);

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
                                , reservation.completed
                                , reservation.id.as("reservationId")
                                , reservation.consultingDatetimeFrom
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
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .fetch();

        return results;
    }

    @Override
    public ResCustomerDTO selectCustomerDetailByReservationId(Long reservationId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;

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
                                , reservation.consultingManager.name.as("consultingManagerName")
                                , reservation.reservationManager.id.as("reservationManagerId")
                                , reservation.consultingDate
                                , reservation.consultingDatetimeFrom
                                , reservation.completed
                                , reservation.id.as("reservationId")
                                , reservation.type
                                , reservation.allDay.as("isAllday")
                                , reservation.contract
                                , consultingPartner.name.as("consultingPartnerName")
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(reservation.customer.eq(customer))
                .leftJoin(consultingPartner).on(customer.consultingPartner.eq(consultingPartner))
                .where(
                        customer.deleted.eq(false)
                                .and(reservation.id.eq(reservationId))
                )
                .orderBy()
                .fetchFirst();

        return result;
    }

    @Override
    public ResCustomerDTO selectReservationDetail(Long reservationId) {
        QCustomer customer = QCustomer.customer;
        QReservation reservation = QReservation.reservation;
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;

        ResCustomerDTO result = queryFactory.select(
                        Projections.fields(
                                ResCustomerDTO.class
                                , customer.id.as("customerId")
                                , customer.name1
                                , customer.mobile1
                                , reservation.memo
                                , reservation.consultingManager.id.as("consultingManagerId")
                                , reservation.consultingManager.name.as("consultingManagerName")
                                , reservation.reservationManager.id.as("reservationManagerId")
                                , reservation.consultingDate
                                , reservation.consultingDatetimeFrom
                                , reservation.completed
                                , reservation.started
                                , reservation.id.as("reservationId")
                                , reservation.type
                                , reservation.allDay.as("isAllday")
                                , reservation.contract
                                , consultingPartner.name.as("consultingPartnerName")
                        )
                )
                .from(reservation)
                .innerJoin(customer).on(reservation.customer.eq(customer))
                .leftJoin(consultingPartner).on(customer.consultingPartner.eq(consultingPartner))
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
        QUser consultingManager = new QUser("consultingManager");

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
                                , reservation.memo
                                , reservationManger.name.as("reservationManagerName")
                                , consultingManager.name.as("consultingManagerName")
                                , reservation.id.as("reservationId")
                                , reservation.consultingDate
                                , reservation.completed
                        )
                )
                .from(customer)
                .innerJoin(reservation).on(customer.eq(reservation.customer))
                .innerJoin(reservationManger).on(reservation.reservationManager.eq(reservationManger))
                .innerJoin(consultingManager).on(reservation.consultingManager.eq(consultingManager))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(reservation.type.eq(1))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByKeyword(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByState(searchDTO))
                                .and(filterManager.getCounselingCustomerListBooleanBuilderByPeriod(searchDTO))
                )
                .orderBy(reservation.consultingDate.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }

    @Override
    public ResCustomerDTO selectCustomerDetail(Long customerId, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QConsultingPartner consultingPartner = QConsultingPartner.consultingPartner;
        QConsultingPartnerPic consultingPartnerPic = QConsultingPartnerPic.consultingPartnerPic;

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
                                , consultingPartner.id.as("consultingPartnerId")
                                , consultingPartnerPic.id.as("consultingPartnerPicId")
                                , consultingPartner.charge.coalesce(0).as("charge")
                                , consultingPartnerPic.contact
                                , consultingPartner.memo
                        )
                )
                .from(customer)
                .leftJoin(consultingPartner).on(customer.consultingPartner.eq(consultingPartner))
                .leftJoin(consultingPartnerPic).on(customer.consultingPartnerPic.eq(consultingPartnerPic))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                                .and(customer.id.eq(customerId))
                )
                .orderBy()
                .fetchOne();

        if (result.getConsultingPartnerId() != null) {
            result.setPicList(
                    queryFactory.select(
                                    Projections.fields(
                                            ResConsultingPartnerPicDTO.class
                                            , consultingPartnerPic.id.as("picId")
                                            , consultingPartnerPic.name
                                            , consultingPartnerPic.contact
                                    )
                            )
                            .from(consultingPartnerPic)
                            .where(
                                    consultingPartnerPic.deleted.eq(false)
                                            .and(consultingPartnerPic.consultingPartner.id.eq(result.getConsultingPartnerId()))
                            )
                            .orderBy(consultingPartnerPic.name.asc())
                            .fetch()
            );
        }
        return result;
    }

    @Override
    public List<ResCustomerPurchaseListDTO> selectCustomerPurchaseList(Long customerId, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QCustomerPurchase customerPurchase = QCustomerPurchase.customerPurchase;

        List<ResCustomerPurchaseListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerPurchaseListDTO.class
                                , customerPurchase.id.as("purchaseId")
                                , customerPurchase.type
                                , new CaseBuilder()
                                        .when(customerPurchase.type.eq(0)).then(Expressions.constant("지정안함"))
                                        .when(customerPurchase.type.eq(1)).then(Expressions.constant("맞춤정장"))
                                        .otherwise(Expressions.constant("지정안함"))
                                        .as("typeString")
                                , customerPurchase.purchaseMemo
                                , customerPurchase.price
                                , customerPurchase.purchaseDate
                                , customerPurchase.charge
                        )
                )
                .from(customerPurchase)
                .innerJoin(customer).on(customerPurchase.customer.eq(customer).and(customer.store.id.eq(storeId)))
                .where(
                        customerPurchase.deleted.eq(false)
                                .and(customer.id.eq(customerId))
                )
                .orderBy(customerPurchase.insertDatetime.desc())
                .fetch();

        return results;
    }

    @Override
    public ResCustomerPurchaseDTO selectCustomerPurchaseDetail(Long purchaseId) {
        QCustomerPurchase customerPurchase = QCustomerPurchase.customerPurchase;

        ResCustomerPurchaseDTO result = queryFactory.select(
                        Projections.fields(
                                ResCustomerPurchaseDTO.class
                                , customerPurchase.id.as("purchaseId")
                                , customerPurchase.type
                                , customerPurchase.purchaseMemo
                                , customerPurchase.price
                                , customerPurchase.purchaseDate
                                , customerPurchase.charge
                        )
                )
                .from(customerPurchase)
                .where(
                        customerPurchase.deleted.eq(false)
                                .and(customerPurchase.id.eq(purchaseId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResCustomerPaymentListDTO> selectCustomerPaymentList(Long customerId, Long storeId) {
        QCustomer customer = QCustomer.customer;
        QCustomerPayment customerPayment = QCustomerPayment.customerPayment;

        List<ResCustomerPaymentListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerPaymentListDTO.class
                                , customerPayment.id.as("paymentId")
                                , customerPayment.paymentDate
                                , customerPayment.type
                                , new CaseBuilder()
                                        .when(customerPayment.type.eq(1)).then(Expressions.constant("선금"))
                                        .when(customerPayment.type.eq(2)).then(Expressions.constant("중도금"))
                                        .when(customerPayment.type.eq(3)).then(Expressions.constant("잔금"))
                                        .otherwise(Expressions.constant("기타"))
                                        .as("typeString")
                                , customerPayment.pic
                                , customerPayment.method
                                , new CaseBuilder()
                                        .when(customerPayment.method.eq(1)).then(Expressions.constant("카드"))
                                        .when(customerPayment.method.eq(2)).then(Expressions.constant("계좌이체"))
                                        .when(customerPayment.method.eq(3)).then(Expressions.constant("현금"))
                                        .otherwise(Expressions.constant("기타"))
                                        .as("methodString")
                                , customerPayment.amount
                                , customerPayment.outstanding
                                , customerPayment.memo.as("paymentMemo")
                        )
                )
                .from(customerPayment)
                .innerJoin(customer).on(customerPayment.customer.eq(customer).and(customer.store.id.eq(storeId)))
                .where(
                        customerPayment.deleted.eq(false)
                                .and(customer.id.eq(customerId))
                )
                .orderBy(customerPayment.insertDatetime.desc())
                .fetch();

        return results;
    }

    @Override
    public ResCustomerPaymentDTO selectCustomerPaymentDetail(Long paymentId) {
        QCustomerPayment customerPayment = QCustomerPayment.customerPayment;

        ResCustomerPaymentDTO result = queryFactory.select(
                        Projections.fields(
                                ResCustomerPaymentDTO.class
                                , customerPayment.id.as("paymentId")
                                , customerPayment.paymentDate
                                , customerPayment.type
                                , customerPayment.pic
                                , customerPayment.method
                                , customerPayment.amount
                                , customerPayment.outstanding
                                , customerPayment.memo
                        )
                )
                .from(customerPayment)
                .where(
                        customerPayment.deleted.eq(false)
                                .and(customerPayment.id.eq(paymentId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResRentalListDTO> selectCustomerRentalList(Long customerId) {
        QRental rental = QRental.rental;
        QRentalItem rentalItem = QRentalItem.rentalItem;

        List<ResRentalListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResRentalListDTO.class
                                , rental.id.as("rentalId")
                                , rental.type
                                , new CaseBuilder()
                                        .when(rental.type.eq(1)).then(Expressions.constant("선금"))
                                        .otherwise(Expressions.constant("기타"))
                                        .as("typeString")
                                , rental.fromDate
                                , rental.toDate
                                , rental.memo
                                , rental.renting
                                , rental.requestDate
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name.as("rentalItemName")
                                , rentalItem.size.as("rentalItemSize")
                        )
                )
                .from(rental)
                .innerJoin(rentalItem).on(rental.rentalItem.eq(rentalItem))
                .where(
                        rental.deleted.eq(false)
                                .and(rental.customer.id.eq(customerId))
                )
                .orderBy(rental.insertDatetime.desc())
                .fetch();

        return results;
    }

    @Override
    public ResRentalDTO selectCustomerRentalDetail(Long rentalId) {
        QRental rental = QRental.rental;
        QRentalItem rentalItem = QRentalItem.rentalItem;

        ResRentalDTO result = queryFactory.select(
                        Projections.fields(
                                ResRentalDTO.class
                                , rental.id.as("rentalId")
                                , rental.type
                                , rental.fromDate
                                , rental.toDate
                                , rental.memo
                                , rental.renting
                                , rental.requestDate
                                , rental.toDate
                                , rentalItem.id.as("rentalItemId")
                                , rentalItem.name.as("rentalItemName")
                        )
                )
                .from(rental)
                .innerJoin(rentalItem).on(rental.rentalItem.eq(rentalItem))
                .where(
                        rental.deleted.eq(false)
                                .and(rental.id.eq(rentalId))
                )
                .fetchOne();

        return result;
    }

    @Override
    public List<ResCustomerReservationDTO> selectCustomerReservationList(Long customerId, ResEnvironmentUpdateDTO resEnvironmentUpdateDTO) {
        QReservation reservation = QReservation.reservation;
        QUser user = QUser.user;

        List<ResCustomerReservationDTO> results = queryFactory.select(
                        Projections.fields(
                                ResCustomerReservationDTO.class
                                , reservation.id.as("reservationId")
                                , reservation.allDay
                                , reservation.type
                                , new CaseBuilder()
                                        .when(reservation.type.eq(2)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName2()))
                                        .when(reservation.type.eq(3)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName3()))
                                        .when(reservation.type.eq(4)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName4()))
                                        .when(reservation.type.eq(5)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName5()))
                                        .when(reservation.type.eq(6)).then(Expressions.constant(resEnvironmentUpdateDTO.getTypeName6()))
                                        .otherwise(Expressions.constant("맞춤상담"))
                                        .as("typeString")
                                , user.id.as("consultingManagerId")
                                , user.name.as("consultingManagerName")
                                , reservation.consultingDate
                                , reservation.consultingDatetimeFrom
                                , reservation.consultingDatetimeTo
                                , reservation.completed
                                , reservation.contract
                                , new CaseBuilder()
                                        .when(reservation.contract.eq(1)).then(Expressions.constant("계약"))
                                        .when(reservation.contract.eq(2)).then(Expressions.constant("투어"))
                                        .when(reservation.contract.eq(3)).then(Expressions.constant("취소"))
                                        .otherwise(Expressions.constant("미해당"))
                                        .as("contractString")
                                , reservation.memo
                        )
                )
                .from(reservation)
                .innerJoin(user).on(reservation.consultingManager.eq(user))
                .where(
                        reservation.deleted.eq(false)
                                .and(reservation.customer.id.eq(customerId))
                )
                .orderBy(reservation.consultingDate.desc(), reservation.consultingDatetimeFrom.desc())
                .fetch();

        return results;
    }

    @Override
    public ResStatDTO selectStatisticsList(Long storeId, LocalDate date) {
        QReservation reservation = QReservation.reservation;

        return queryFactory
                .select(Projections.fields(
                        ResStatDTO.class,

                        // --- 예약 종류(type)별 ---
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 1 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType1"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 2 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType2"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 3 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType3"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 4 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType4"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 5 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType5"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 6 THEN 1 ELSE 0 END)", reservation.type)
                                .coalesce(0L)
                                .as("reservationType6"),

                        // --- 계약 상태(contract)별 ---
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 0 THEN 1 ELSE 0 END)", reservation.contract)
                                .coalesce(0L)
                                .as("contractType0"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 1 THEN 1 ELSE 0 END)", reservation.contract)
                                .coalesce(0L)
                                .as("contractType1"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 2 THEN 1 ELSE 0 END)", reservation.contract)
                                .coalesce(0L)
                                .as("contractType2"),
                        Expressions.numberTemplate(Long.class,
                                        "SUM(CASE WHEN {0} = 3 THEN 1 ELSE 0 END)", reservation.contract)
                                .coalesce(0L)
                                .as("contractType3")
                ))
                .from(reservation)
                .where(
                        reservation.deleted.isFalse()
                        , reservation.consultingManager.store.id.eq(storeId)
                        , filterManager.getReservationCustomerListBooleanBuilderByDate(date)
                )
                .fetchOne();
    }
}
