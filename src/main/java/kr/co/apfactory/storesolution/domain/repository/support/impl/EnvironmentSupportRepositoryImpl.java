package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeDetailDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResMypageDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResStoreInfoDTO;
import kr.co.apfactory.storesolution.domain.entity.QCodeType;
import kr.co.apfactory.storesolution.domain.entity.QStore;
import kr.co.apfactory.storesolution.domain.entity.QUser;
import kr.co.apfactory.storesolution.domain.repository.support.EnvironmentSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import kr.co.apfactory.storesolution.global.security.dto.LoginInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.EMPLOYEE;
import static kr.co.apfactory.storesolution.global.enums.AuthorityEnum.USER;


// querydsl 사용
@RequiredArgsConstructor
public class EnvironmentSupportRepositoryImpl implements EnvironmentSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;
}
