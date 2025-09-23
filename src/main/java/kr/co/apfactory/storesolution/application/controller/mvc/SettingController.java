package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.StoreService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
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

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/setting")
public class SettingController {
    private final StoreService storeService;

    private final UserService userService;

    private final SiteService siteService;

    @GetMapping("/store/info")
    public String gotoStoreInfoPage(Model model) {
        // 현재 로그인한 사용자의 매장
        model.addAttribute("item", storeService.getStoreInfo());
        return "views/setting/storeInfo";
    }

    @GetMapping("/employee/list")
    public String gotoEmployeeList(@PageableDefault(page = 0, size = 10, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable
            , @ModelAttribute SearchDTO searchDTO
            , HttpServletRequest request, Model model) {
        model.addAttribute("itemList", storeService.getEmployeeList(request, pageable, searchDTO));
        return "views/setting/employee/list";
    }

    @GetMapping("/employee/register")
    public String gotoEmployeeRegister() {
        return "views/setting/employee/register";
    }

    @GetMapping("/employee/detail")
    public String gotoEmployeeDetail(Long userId, HttpServletRequest request, Model model) {
        model.addAttribute("item", userService.getEmployeeDetail(request, userId));
        return "views/setting/employee/detail";
    }

    /**
     * 환경설정 페이지
     */
    @GetMapping("/environment")
    public String gotoSiteEnvironmentDetail(Model model) {
        model.addAttribute("dto", siteService.getSiteEnvironment());
        return "views/setting/environment";
    }

    @GetMapping("/images")
    public String gotoSiteImagesDetail(Model model) {
        model.addAttribute("dto", siteService.getSiteImages());
        return "views/setting/images";
    }
}
