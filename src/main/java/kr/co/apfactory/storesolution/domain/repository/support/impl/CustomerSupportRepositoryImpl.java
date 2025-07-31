package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.response.ResCustomerDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResEmployeeListDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.domain.repository.support.CustomerSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;

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
                                , orderConsulting.completed
                        )
                )
                .from(customer)
                .leftJoin(orderConsulting).on(customer.eq(orderConsulting.customer))
                .where(
                        customer.deleted.eq(false)
                                .and(customer.store.id.eq(storeId))
                )
                .orderBy()
                .fetch();

        return results;
    }
}
