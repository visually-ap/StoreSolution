package kr.co.apfactory.storesolution.domain.repository.support.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.apfactory.storesolution.domain.dto.response.ResCodeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCodeValueListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternGaugeDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternListDTO;
import kr.co.apfactory.storesolution.domain.entity.QCommonCodeChild;
import kr.co.apfactory.storesolution.domain.entity.QCommonCodeParent;
import kr.co.apfactory.storesolution.domain.entity.QPattern;
import kr.co.apfactory.storesolution.domain.entity.QPatternSize;
import kr.co.apfactory.storesolution.domain.repository.support.CommonCodeSupportRepository;
import kr.co.apfactory.storesolution.domain.repository.util.FilterManager;
import kr.co.apfactory.storesolution.domain.repository.util.SortManager;
import lombok.RequiredArgsConstructor;

import java.util.List;


// querydsl 사용
@RequiredArgsConstructor
public class CommonCodeSupportRepositoryImpl implements CommonCodeSupportRepository {
    private final JPAQueryFactory queryFactory;

    private final SortManager sortManager;

    private final FilterManager filterManager;

    @Override
    public List<ResPatternListDTO> selectPatternList(Long patternType) {
        QPattern pattern = QPattern.pattern;

        List<ResPatternListDTO> results = queryFactory.select(
                        Projections.fields(
                                ResPatternListDTO.class
                                , pattern.type.id
                                , pattern.patternNameKo.as("name")
                                , pattern.id
                                , pattern.fileAttachStoreSolution.id.as("fileId")
                        )
                )
                .from(pattern)
                .where(pattern.deleted.eq(false)
                        .and(pattern.type.id.eq(patternType))
                )
                .orderBy(pattern.showSequence.asc())
                .fetch();
        return results;
    }

    @Override
    public List<ResCodeListDTO> selectCodeList(Long part, List<Long> exceptionCodeIdList) {
        QCommonCodeParent commonCodeParent = QCommonCodeParent.commonCodeParent;
        QCommonCodeChild commonCodeChild = QCommonCodeChild.commonCodeChild;

        List<ResCodeListDTO> results = queryFactory
                .select(Projections.fields(
                        ResCodeListDTO.class
                                , commonCodeParent.parentCodeValue
                                , commonCodeParent.part.id
                                , commonCodeParent.type.id
                                , commonCodeChild.codeNameKo.as("codeName")
                                , commonCodeChild.childCodeValue
                                , commonCodeChild.showSequence
                                , commonCodeChild.id
                                , commonCodeChild.yard
                                , commonCodeChild.fileAttachStoreSolution.id.as("fileId")
                        )
                )
                .from(commonCodeChild)
                .leftJoin(commonCodeParent).on(commonCodeChild.parentCode.parentCodeValue.eq(commonCodeParent.parentCodeValue))
                .orderBy(
                        commonCodeParent.parentCodeValue.asc()
                        , commonCodeChild.showSequence.asc()
                        , commonCodeChild.insertDatetime.asc()
                )
                .where(
                        commonCodeChild.deleted.isFalse()
                                .and(commonCodeParent.deleted.isFalse())
                                .and(commonCodeParent.part.id.eq(part))
                                .and(commonCodeChild.id.notIn(exceptionCodeIdList))
                ).fetch();

        return results;
    }

    @Override
    public List<ResCodeListDTO> selectCodeList(Long part) {
        QCommonCodeParent commonCodeParent = QCommonCodeParent.commonCodeParent;
        QCommonCodeChild commonCodeChild = QCommonCodeChild.commonCodeChild;

        List<ResCodeListDTO> results = queryFactory
                .select(Projections.fields(
                        ResCodeListDTO.class
                                , commonCodeParent.parentCodeValue
                                , commonCodeParent.part.id
                                , commonCodeParent.type.id
                                , commonCodeChild.codeNameKo.as("codeName")
                                , commonCodeChild.childCodeValue
                                , commonCodeChild.showSequence
                                , commonCodeChild.id
                                , commonCodeChild.yard
                                , commonCodeChild.fileAttachStoreSolution.id.as("fileId")
                        )
                )
                .from(commonCodeChild)
                .leftJoin(commonCodeParent).on(commonCodeChild.parentCode.parentCodeValue.eq(commonCodeParent.parentCodeValue))
                .orderBy(
                        commonCodeParent.parentCodeValue.asc()
                        , commonCodeChild.showSequence.asc()
                        , commonCodeChild.insertDatetime.asc()
                )
                .where(
                        commonCodeChild.deleted.isFalse()
                                .and(commonCodeParent.deleted.isFalse())
                                .and(commonCodeParent.part.id.eq(part))
                ).fetch();

        return results;
    }

    @Override
    public List<ResCodeValueListDTO> selectAllCodeList() {
        QCommonCodeChild commonCodeChild = QCommonCodeChild.commonCodeChild;

        List<ResCodeValueListDTO> results = queryFactory
                .select(
                        Projections.fields(
                                ResCodeValueListDTO.class
                                , commonCodeChild.codeNameKo.as("codeName")
                                , commonCodeChild.childCodeValue
                                , commonCodeChild.showSequence
                                , commonCodeChild.id
                                , commonCodeChild.yard
                                , commonCodeChild.fileAttachStoreSolution.id.as("fileId")
                        )
                )
                .from(commonCodeChild)
                .where(commonCodeChild.deleted.isFalse())
                .fetch();

        return results;
    }

    @Override
    public List<ResPatternGaugeDTO> selectPatternSizeList(Long id) {
        QPattern pattern = QPattern.pattern;
        QPatternSize patternSize = QPatternSize.patternSize;

        List<ResPatternGaugeDTO> results = queryFactory.select(
                        Projections.fields(
                                ResPatternGaugeDTO.class
                                , patternSize.id
                                , patternSize.sizeType
                                , patternSize.data01
                                , patternSize.data02
                                , patternSize.data03
                                , patternSize.data04
                                , patternSize.data05
                                , patternSize.data06
                                , patternSize.data07
                                , patternSize.data08
                                , patternSize.data09
                                , patternSize.data10
                                , patternSize.data11
                                , patternSize.data12
                                , patternSize.data13
                                , patternSize.yard
                                , patternSize.showSequence
                                , patternSize.insertDatetime
                        )
                )
                .from(pattern)
                .innerJoin(patternSize).on(pattern.eq(patternSize.pattern))
                .where(
                        pattern.deleted.eq(false)
                                .and(patternSize.deleted.eq(false))
                                .and(pattern.id.eq(id))
                )
                .orderBy(patternSize.showSequence.asc(), patternSize.id.asc())
                .fetch();

        return results;
    }
}
