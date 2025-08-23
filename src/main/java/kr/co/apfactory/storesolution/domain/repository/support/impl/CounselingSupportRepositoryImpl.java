package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.repository.support.CounselingSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;


// querydsl 사용
@RequiredArgsConstructor
public class CounselingSupportRepositoryImpl implements CounselingSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;


}
