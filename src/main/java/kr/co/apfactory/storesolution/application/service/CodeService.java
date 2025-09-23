package kr.co.apfactory.storesolution.application.service;

import kr.co.apfactory.storesolution.domain.dto.response.ResCodeListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCodeValueListDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternGaugeDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResPatternListDTO;
import kr.co.apfactory.storesolution.domain.repository.CommonCodeChildRepository;
import kr.co.apfactory.storesolution.domain.repository.PatternRepository;
import kr.co.apfactory.storesolution.domain.repository.PatternSizeRepository;
import kr.co.apfactory.storesolution.domain.repository.support.CommonCodeSupportRepository;
import kr.co.apfactory.storesolution.global.enums.ParentCodeEnum;
import kr.co.apfactory.storesolution.global.i18n.I18nUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CodeService {
    private final PasswordEncoder passwordEncoder;

    private final I18nUtility i18nUtility;

    private final PatternRepository patternRepository;

    private final PatternSizeRepository patternSizeRepository;

    private final CommonCodeChildRepository commonCodeChildRepository;

    private static HashMap<String, List<ResCodeValueListDTO>> getCodeList(List<ResCodeListDTO> codeListDTOList) {

        HashMap<String, List<ResCodeValueListDTO>> codeValueListDTOHashMap = new HashMap<>();

        for (ParentCodeEnum code : ParentCodeEnum.values()) {
            List<ResCodeValueListDTO> codes = new ArrayList<>();

            codeListDTOList.stream().filter(i -> i.getParentCodeValue().equals(code.getCodeValue())).forEach(
                    s -> codes.add(ResCodeValueListDTO.builder()
                            .id(s.getId())
                            .codeName(s.getCodeName())
                            .codeValue(s.getCodeValue())
                            .yard(s.getYard())
                            .showSequence(s.getShowSequence())
                            .fileId(s.getFileId())
                            .build())
            );
            codeValueListDTOHashMap.put(code.getCodeValue(), codes);
        }
        return codeValueListDTOHashMap;
    }

    public List<ResPatternListDTO> getPatternList(Long patternType) {
        return patternRepository.selectPatternList(patternType);
    }

    public List<ResCodeValueListDTO> getAllCodeList() {
        return commonCodeChildRepository.selectAllCodeList();
    }

    public HashMap<String, List<ResCodeValueListDTO>> getCodeList(Long part) {
        // 공통코드 목록 조회
        List<ResCodeListDTO> codeListList = commonCodeChildRepository.selectCodeList(part);
        // 공통코드 목록을 맵으로 변환
        return getCodeList(codeListList);
    }

    public List<ResPatternGaugeDTO> getGaugeSizeList(long patternId) {
        return patternSizeRepository.selectPatternSizeList(patternId);
    }
}
