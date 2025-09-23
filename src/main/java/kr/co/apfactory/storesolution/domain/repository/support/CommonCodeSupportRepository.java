package kr.co.apfactory.storesolution.domain.repository.support;

import kr.co.apfactory.storesolution.domain.dto.response.ResCodeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCodeValueListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternGaugeDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternListDTO;

import java.util.List;

public interface CommonCodeSupportRepository {
    List<ResPatternListDTO> selectPatternList(Long patternType);

    List<ResCodeListDTO> selectCodeList(Long part);

    List<ResCodeValueListDTO> selectAllCodeList();

    List<ResPatternGaugeDTO> selectPatternSizeList(Long id);
}
