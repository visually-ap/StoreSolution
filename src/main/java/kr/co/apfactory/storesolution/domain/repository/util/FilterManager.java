package kr.co.apfactory.storesolution.domain.repository.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.entity.QCustomer;
import kr.co.apfactory.storesolution.domain.entity.QReservation;
import kr.co.apfactory.storesolution.domain.entity.QStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

import static com.querydsl.core.types.dsl.Expressions.stringPath;

@Component
public class FilterManager {
    public StringPath getLanguageStringPath(String lang, Path<?> parent, String fieldName) {
        SimplePath result = Expressions.path(Object.class, parent, getLanguageFieldName(lang, fieldName));

        return stringPath(result.toString());
    }

    public String getLanguageFieldName(String lang, String fieldName) {
        switch (lang) {
            case "ko_KR":
                fieldName = fieldName + "Ko";
                break;

            case "zh_CN":
                fieldName = fieldName + "Cn";
                break;

            case "ja_JP":
                fieldName = fieldName + "Jp";
                break;

            case "in_ID":
                fieldName = fieldName + "In";
                break;

            case "en_US":
            default:
                fieldName = fieldName + "En";
                break;
        }

        return fieldName;
    }

    private BooleanExpression queryStringEq(StringPath path, String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return null;
        }
        return path.contains(keyword);
    }

    private BooleanExpression queryStringEq(NumberPath path, Long type) {
        return path.eq(type);
    }

    private BooleanExpression queryStringEq(NumberPath path, Integer type) {
        return path.eq(type);
    }

    private BooleanExpression queryStringEq(BooleanPath path, Boolean type) {
        return path.eq(type);
    }

    private BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    public BooleanBuilder getReservationCustomerListBooleanBuilderByKeyword(String searchKeyword) {
        return nullSafeBuilder(() ->
                queryStringEq(QCustomer.customer.name1, searchKeyword)
                        .or(queryStringEq(QCustomer.customer.name2, searchKeyword))
        );
    }

    public BooleanBuilder getCounselingCustomerListBooleanBuilderByKeyword(SearchDTO searchDTO) {
        if (!"".equals(searchDTO.getSearchKeyword())) {
            if ("all".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QCustomer.customer.name1, searchDTO.getSearchKeyword())
                                .or(queryStringEq(QCustomer.customer.mobile1, searchDTO.getSearchKeyword()))
                );
            } else if ("name".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QCustomer.customer.name1, searchDTO.getSearchKeyword())
                );
            } else if ("mobile".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QCustomer.customer.mobile1, searchDTO.getSearchKeyword())
                );
            } else {
                return nullSafeBuilder(() ->
                        queryStringEq(QCustomer.customer.name1, searchDTO.getSearchKeyword())
                                .or(queryStringEq(QCustomer.customer.mobile1, searchDTO.getSearchKeyword()))
                );
            }
        } else {
            return null;
        }
    }

    public BooleanBuilder getCounselingCustomerListBooleanBuilderByPeriod(SearchDTO searchDTO) {
        if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() != null) {
            if ("consulting".equals(searchDTO.getDateCondition())) {
                return nullSafeBuilder(() ->
                        QReservation.reservation.consultingDate.between(searchDTO.getDateFrom(), searchDTO.getDateTo())
                );
            } else if ("photo".equals(searchDTO.getDateCondition())) {
                return nullSafeBuilder(() ->
                        QCustomer.customer.photoDate.between(searchDTO.getDateFrom(), searchDTO.getDateTo())
                );
            } else if ("wedding".equals(searchDTO.getDateCondition())) {
                return nullSafeBuilder(() ->
                        QCustomer.customer.weddingDate.between(searchDTO.getDateFrom(), searchDTO.getDateTo())
                );
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
