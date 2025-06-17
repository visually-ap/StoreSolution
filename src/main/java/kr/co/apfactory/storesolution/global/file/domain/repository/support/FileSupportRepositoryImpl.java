package kr.co.apfactory.storesolution.global.file.domain.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;

// querydsl 사용
@RequiredArgsConstructor
public class FileSupportRepositoryImpl implements FileSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;
}
