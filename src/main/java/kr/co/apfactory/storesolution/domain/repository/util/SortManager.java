package kr.co.apfactory.storesolution.domain.repository.util;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import kr.co.apfactory.storesolution.domain.entity.QConsultingPartner;
import kr.co.apfactory.storesolution.domain.entity.QRentalItem;
import kr.co.apfactory.storesolution.domain.entity.QUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class SortManager {
    private OrderSpecifier getSortedColumn(Order order, Path<?> parent, String fieldName) {
        Path<Object> fieldPath = Expressions.path(Object.class, parent, fieldName);
        return new OrderSpecifier(order, fieldPath);
    }

    private Order getSortType(Sort.Order order) {
        return order.getDirection().isAscending() ? Order.ASC : Order.DESC;
    }

    public List<OrderSpecifier> getEmployeeListOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!isEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                switch (order.getProperty()) {
                    case "regDate":
                        ORDERS.add(getSortedColumn(getSortType(order), QUser.user.insertDatetime, "insertDatetime"));
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }

    public List<OrderSpecifier> getConsultingPartnerListOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!isEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                switch (order.getProperty()) {
                    case "regDate":
                        ORDERS.add(getSortedColumn(getSortType(order), QConsultingPartner.consultingPartner.insertDatetime, "insertDatetime"));
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }

    public List<OrderSpecifier> getRentalItemListOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (!isEmpty(pageable.getSort())) {
            for (Sort.Order order : pageable.getSort()) {
                switch (order.getProperty()) {
                    case "regDate":
                        ORDERS.add(getSortedColumn(getSortType(order), QRentalItem.rentalItem.insertDatetime, "insertDatetime"));
                        break;
                    case "name":
                        ORDERS.add(getSortedColumn(getSortType(order), QRentalItem.rentalItem.name, "insertDatetime"));
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }
}
