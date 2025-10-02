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
}
