package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.*;
import kr.co.apfactory.storesolution.domain.entity.QCodeType;
import kr.co.apfactory.storesolution.domain.entity.QStore;
import kr.co.apfactory.storesolution.domain.entity.QUser;
import kr.co.apfactory.storesolution.domain.repository.support.UserSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.*;


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
    public List<ResEmployeeScheduleDTO> selectEmployeeScheduleList(Long storeId) {
        QUser user = QUser.user;
        QStore store = QStore.store;

        List<ResEmployeeScheduleDTO> results = queryFactory.select(
                        Projections.fields(
                                ResEmployeeScheduleDTO.class
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
}
