package kr.co.apfactory.storesolution.domain.entity.util;

import kr.co.apfactory.storesolution.domain.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapStructMapper {
    TempOrderDsJacket toTempOrderDsJacket(CounselingJacket jacket);
    TempOrderDsPants toTempOrderDsPants(CounselingPants jacket);
    TempOrderDsVest toTempOrderDsVest(CounselingVest jacket);
    TempOrderDsCoat toTempOrderDsCoat(CounselingCoat jacket);
}
