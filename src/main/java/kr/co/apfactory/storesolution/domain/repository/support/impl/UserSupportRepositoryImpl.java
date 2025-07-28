package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.UserSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.EMPLOYEE;
import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.USER;


// querydsl 사용
@RequiredArgsConstructor
public class UserSupportRepositoryImpl implements UserSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public LoginInfoDTO selectLoginInfo(String loginId) {
        QUser user = QUser.user;
        QStore store = QStore.store;

        LoginInfoDTO result = queryFactory.select(
                        Projections.fields(
                                LoginInfoDTO.class
                                , user.id
                                , user.loginId
                                , user.password
                                , user.authority
                                , user.enabled
                                , user.deleted
                                , user.name
                                , user.checkAuth.id.as("checkAuth")
                                , user.adminType.id.as("adminType")
                                , store.id.as("storeId")
                        )
                )
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .where(
                        user.deleted.eq(false)
                                .and(user.loginId.eq(loginId))
                                .and(user.authority.in(USER.getAuth(), EMPLOYEE.getAuth()))
                )
                .fetchOne();

        return result;
    }

    @Override
    public ResMypageDTO selectUserInfoForMypage(String lang, Long userId) {
        QUser user = QUser.user;
        QCodeType position = QCodeType.codeType;

        ResMypageDTO result = queryFactory.select(
                        Projections.fields(
                                ResMypageDTO.class
                                , user.id
                                , user.loginId
                                , user.name
                                , user.mobileNumber
                                , filterManager.getLanguageStringPath(lang, position, "typeName").as("position")
                        )
                )
                .from(user)
                .leftJoin(position).on(user.position.eq(position))
                .where(
                        user.deleted.eq(false)
                                .and(user.id.eq(userId))
                )
                .fetchOne();

        return result;
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
                )
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
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
        QOrderConsulting orderConsulting = QOrderConsulting.orderConsulting;
        QCustomer customer = QCustomer.customer;

        List<ResEmployeeScheduleDTO> results = queryFactory
                .from(user)
                .innerJoin(store).on(user.store.eq(store))
                .leftJoin(reservation).on(user.eq(reservation.consultingManager).and(reservation.deleted.isFalse()).and(reservation.consultingDate.eq(date)))
                .leftJoin(orderConsulting).on(reservation.orderConsulting.eq(orderConsulting))
                .leftJoin(customer).on(orderConsulting.customer.eq(customer))
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
                                                        , reservation.id
                                                        , reservation.allDay
                                                        , reservation.consultingDatetimeFrom
                                                        , reservation.consultingDatetimeTo
                                                        , reservation.type
                                                        , customer.name1.as("customerName")
                                                )
                                        ).as("reservationList")
                                )
                        )
                );

        for (ResEmployeeScheduleDTO dto : results) {
            List<ResReservationDTO> filtered = dto.getReservationList().stream()
                    .filter(res -> res.getId() != null)
                    .collect(Collectors.toList());
            dto.setReservationList(filtered);
        }

        return results;
    }



//                List<ResEmployeeScheduleDTO> results = queryFactory.select(
//                        Projections.fields(
//                                ResEmployeeScheduleDTO.class
//                                , user.id.as("userId")
//                                , user.name
//                        )
//                )
//                .from(user)
//                .innerJoin(store).on(user.store.eq(store))
//                .where(
//                        user.deleted.eq(false)
//                                .and(user.store.id.eq(storeId))
//                )
//                .orderBy(user.name.desc())
//                .fetch();
}
