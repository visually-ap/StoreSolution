package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.CodeService;
import kr.co.apfactory.storesolution.application.service.CounselingService;
import kr.co.apfactory.storesolution.application.service.CustomerService;
import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.domain.dto.response.ResCounselingDTO;
import kr.co.apfactory.storesolution.global.file.application.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static kr.co.apfactory.storesolution.global.enums.CodePartEnum.COAT;
import static kr.co.apfactory.storesolution.global.enums.CodePartEnum.SUIT;
import static kr.co.apfactory.storesolution.global.enums.CodeTypeEnum.*;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/counseling")
public class CounselingController {
    private final CustomerService customerService;

    private final SiteService siteService;

    private final FileService fileService;

    private final CodeService codeService;

    private final CounselingService counselingService;

    @GetMapping("/customer/list")
    public String gotoCustomerList(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable,
                                   @ModelAttribute("searchDTO") SearchDTO searchDTO, Model model) {
        model.addAttribute("itemList", customerService.getCustomerList(pageable, searchDTO));
        return "views/counseling/customer/list";
    }

    @GetMapping("/customer/register")
    public String gotoCustomerRegisterForm(Model model) {
        return "views/counseling/customer/register";
    }

    @GetMapping("/about")
    public String openAboutPopup(Model model) {
        // 상담판 파일타입 = 3
        model.addAttribute("imageList", fileService.getAboutImageList());
        return "views/popup/about";
    }

    @GetMapping("/re-about")
    public String openReAboutPopup(Model model) {
        // 상담판 파일타입 = 3
        model.addAttribute("imageList", fileService.getAboutImageList());
        return "views/popup/reAbout";
    }

    @GetMapping("/fabric-item")
    public String gotoFabricAndItemPage(Model model, Long reservationId, Boolean completed) {
        ResCounselingDTO dto = counselingService.getCounselingDetail(reservationId);
        if (dto == null) {
            throw new RuntimeException("잘못된 접근입니다.");
        }
        model.addAttribute("counseling", dto);
        if (completed) {
            return "views/counseling/compFabricItem";
        } else {
            return "views/counseling/fabricItem";
        }
    }

    @GetMapping("/design")
    public String gotoDesignPage(Model model, Long reservationId, Boolean completed) {
        ResCounselingDTO dto = counselingService.getCounselingDetail(reservationId);
        if (dto == null) {
            throw new RuntimeException("잘못된 접근입니다.");
        }
        model.addAttribute("counseling", dto);
        model.addAttribute("envDto", siteService.getSiteEnvironment());

        // 상의 정보
        model.addAttribute("jacket", counselingService.getCounselingJacketDesignDetail(dto.getCounselingCommonId()));
        // 하의 정보
        model.addAttribute("pants", counselingService.getCounselingPantsDesignDetail(dto.getCounselingCommonId()));
        // 조끼 정보
        model.addAttribute("vest", counselingService.getCounselingVestDesignDetail(dto.getCounselingCommonId()));
        // 코트 정보
        model.addAttribute("coat", counselingService.getCounselingCoatDesignDetail(dto.getCounselingCommonId()));


        // 하의 패턴 코드 조회
        model.addAttribute("pantsPatternList", codeService.getPatternList(CODE_TYPE_PANTS.getValue()));
        // 조끼 패턴 코드 조회
        model.addAttribute("vestPatternList", codeService.getPatternList(CODE_TYPE_VEST.getValue()));

        // 정장 공통 코드 조회
        // 제외 코드
        List<Long> exceptionCodeIdList = new ArrayList<>(List.of(276L, 280L, 481L, 510L, 554L, 562L, 563L, 564L, 565L, 566L, 567L, 575L, 576L));
        model.addAttribute("suitCodeList", codeService.getCodeList(SUIT.getValue(), exceptionCodeIdList));
        // 코트 공통 코드 조회
        model.addAttribute("coatCodeList", codeService.getCodeList(COAT.getValue()));

        if (completed) {
            return "views/counseling/compDesign";
        } else {
            return "views/counseling/design";
        }
    }

    @GetMapping("/size")
    public String gotoSize(Model model, Long reservationId, Boolean completed) {
        ResCounselingDTO dto = counselingService.getCounselingDetail(reservationId);
        if (dto == null) {
            throw new RuntimeException("잘못된 접근입니다.");
        }
        model.addAttribute("counseling", dto);

        // 상의 정보
        model.addAttribute("jacket", counselingService.getCounselingJacketSizeDetail(dto.getCounselingCommonId()));
        // 하의 정보
        model.addAttribute("pants", counselingService.getCounselingPantsSizeDetail(dto.getCounselingCommonId()));
        // 조끼 정보
        model.addAttribute("vest", counselingService.getCounselingVestSizeDetail(dto.getCounselingCommonId()));
        // 코트 정보
        model.addAttribute("coat", counselingService.getCounselingCoatSizeDetail(dto.getCounselingCommonId()));

        // 상의 패턴 코드 조회
        model.addAttribute("jacketPatternList", codeService.getPatternList(CODE_TYPE_JACKET.getValue()));
        // 코트 패턴 코드 조회
        model.addAttribute("coatPatternList", codeService.getPatternList(CODE_TYPE_COAT.getValue()));
        if (dto.getFactory() == 3) {
            if (completed) {
                return "views/counseling/compSizeEtc";
            } else {
                return "views/counseling/sizeEtc";
            }
        } else {
            if (completed) {
                return "views/counseling/compSize";
            } else {
                return "views/counseling/size";
            }
        }
    }
}
