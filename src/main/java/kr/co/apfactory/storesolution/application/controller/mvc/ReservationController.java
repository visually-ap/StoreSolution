package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.SiteService;
import kr.co.apfactory.storesolution.application.service.StoreService;
import kr.co.apfactory.storesolution.application.service.UserService;
import kr.co.apfactory.storesolution.domain.dto.common.SearchDTO;
import kr.co.apfactory.storesolution.global.security.utility.LoginUser;
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
@RequestMapping("/reservation")
public class ReservationController {
    private final StoreService storeService;

    private final UserService userService;

    private final SiteService siteService;

    @GetMapping("/main")
    public String gotoReservationMainPage(Model model) {
        model.addAttribute("item", storeService.getStoreInfo());
        return "views/reservation/main_bak";
    }

    @GetMapping("/register")
    public String gotoNewCustomerRegister(Model model) {
        model.addAttribute("employeeList", userService.getEmployeeList());
        model.addAttribute("userId", LoginUser.getDetails().getId());
        model.addAttribute("siteEnvDto", siteService.getSiteEnvironment());
        return "views/reservation/register";
    }
}
