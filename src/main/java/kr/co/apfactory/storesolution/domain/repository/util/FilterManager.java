package kr.co.apfactory.storesolution.domain.repository.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.*;
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
}
