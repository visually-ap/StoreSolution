package kr.co.apfactory.storesolution.domain.repository.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.entity.*;
import kr.co.apfactory.storesolution.global.utility.parser.LocalDateParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
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

    public BooleanBuilder getEmployeeListBooleanBuilderByKeyword(SearchDTO searchDTO) {
        if (!"".equals(searchDTO.getSearchKeyword())) {
            if ("name".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QUser.user.name, searchDTO.getSearchKeyword())
                );
            } else if ("mobile".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QUser.user.mobileNumber, searchDTO.getSearchKeyword())
                );
            } else {
                return nullSafeBuilder(() ->
                        queryStringEq(QUser.user.name, searchDTO.getSearchKeyword())
                                .or(queryStringEq(QUser.user.mobileNumber, searchDTO.getSearchKeyword()))
                );
            }
        } else {
            return null;
        }
    }

    public BooleanBuilder getEmployeeListBooleanBuilderByPeriod(SearchDTO searchDTO) {
        if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() != null) {
            return nullSafeBuilder(() ->
                    QUser.user.insertDatetime.between(
                            LocalDateParser.getStartDatetimeFromDate(searchDTO.getDateFrom())
                            , LocalDateParser.getEndDatetimeFromDate(searchDTO.getDateTo())
                    )
            );
        } else {
            return null;
        }
    }

    public BooleanBuilder getReservationCustomerListBooleanBuilderByKeyword(String searchKeyword) {
        if (!"".equals(searchKeyword)) {
            return nullSafeBuilder(() ->
                    queryStringEq(QCustomer.customer.name1, searchKeyword)
                            .or(queryStringEq(QCustomer.customer.name2, searchKeyword))
            );
        } else {
            return null;
        }
    }

    public BooleanBuilder getReservationCustomerListBooleanBuilderByDate(LocalDate searchDate) {
        if (searchDate != null) {
            return nullSafeBuilder(() ->
                    QReservation.reservation.consultingDate.eq(searchDate)
            );
        } else {
            return null;
        }
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

    public BooleanBuilder getCounselingCustomerListBooleanBuilderByState(SearchDTO searchDTO) {
        if (!"".equals(searchDTO.getCounselingState())) {
            if ("before".equals(searchDTO.getCounselingState())) {
                return nullSafeBuilder(() ->
                        QReservation.reservation.completed.isFalse()
                );
            } else if ("completed".equals(searchDTO.getCounselingState())) {
                return nullSafeBuilder(() ->
                        QReservation.reservation.completed.isTrue()
                );
            } else {
                return nullSafeBuilder(() ->
                        QReservation.reservation.completed.isFalse()
                );
            }
        } else {
            return nullSafeBuilder(() ->
                    QReservation.reservation.completed.isFalse()
            );
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

    public BooleanBuilder getConsultingPartnerListBooleanBuilderByKeyword(SearchDTO searchDTO) {
        if (!"".equals(searchDTO.getSearchKeyword())) {
            if ("name".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QConsultingPartner.consultingPartner.name, searchDTO.getSearchKeyword())
                );
            } else if ("pic".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QConsultingPartner.consultingPartner.pic, searchDTO.getSearchKeyword())
                );
            } else if ("contact".equals(searchDTO.getSearchCondition())) {
                return nullSafeBuilder(() ->
                        queryStringEq(QConsultingPartner.consultingPartner.contact, searchDTO.getSearchKeyword())
                );
            } else {
                return nullSafeBuilder(() ->
                        queryStringEq(QConsultingPartner.consultingPartner.name, searchDTO.getSearchKeyword())
                                .or(queryStringEq(QConsultingPartner.consultingPartner.pic, searchDTO.getSearchKeyword()))
                                .or(queryStringEq(QConsultingPartner.consultingPartner.contact, searchDTO.getSearchKeyword()))
                );
            }
        } else {
            return null;
        }
    }

    public BooleanBuilder getConsultingPartnerListBooleanBuilderByPeriod(SearchDTO searchDTO) {
        if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() != null) {
            return nullSafeBuilder(() ->
                    QConsultingPartner.consultingPartner.insertDatetime.between(
                            LocalDateParser.getStartDatetimeFromDate(searchDTO.getDateFrom())
                            , LocalDateParser.getEndDatetimeFromDate(searchDTO.getDateTo())
                    )
            );
        } else {
            return null;
        }
    }

    public BooleanBuilder getRentalItemListBooleanBuilderByKeyword(String searchKeyword) {
        if (!"".equals(searchKeyword)) {
            if ("name".equals(searchKeyword)) {
                return nullSafeBuilder(() ->
                        queryStringEq(QRentalItem.rentalItem.name, searchKeyword)
                );
            } else {
                return nullSafeBuilder(() ->
                        queryStringEq(QRentalItem.rentalItem.name, searchKeyword)
                );
            }
        } else {
            return null;
        }
    }

    public BooleanBuilder getRentalItemListBooleanBuilderByPeriod(SearchDTO searchDTO) {
        if (searchDTO.getDateFrom() != null && searchDTO.getDateTo() != null) {
            return nullSafeBuilder(() ->
                    QRentalItem.rentalItem.insertDatetime.between(
                            LocalDateParser.getStartDatetimeFromDate(searchDTO.getDateFrom())
                            , LocalDateParser.getEndDatetimeFromDate(searchDTO.getDateTo())
                    )
            );
        } else {
            return null;
        }
    }
}
