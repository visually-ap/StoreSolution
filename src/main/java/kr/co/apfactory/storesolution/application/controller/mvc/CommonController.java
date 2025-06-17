package kr.co.apfactory.storesolution.application.controller.mvc;

import kr.co.apfactory.storesolution.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@RequiredArgsConstructor
@Controller
public class CommonController {
    private final Environment environment;

    private final UserService userService;

    /**
     * 홈이동
     */
    @GetMapping("/")
    public String gotoHomePage(Model model, HttpServletRequest request, String searchKeyword) {
        model.addAttribute("searchKeyword", searchKeyword);
        return "views/home";
    }

    /**
     * 로그인 페이지 이동
     *
     * @return
     */
    @RequestMapping("/login")
    public String gotoLoginPage() {
        return "views/account/login";
    }

    /**
     * 로그아웃
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // 계정 존재 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String gotoMypagePage(HttpServletRequest request, Model model) {
        // 현재 로그인한 사용자의 매장
        model.addAttribute("item", userService.getUserInfoForMypage(request));
        return "views/account/mypage";
    }

    @GetMapping("/error/access")
    public String gotoInvalidAccessPage() {
        return "error/invalidAccess";
    }

    @GetMapping("/error/deny")
    public String gotoDenyPage() {
        return "error/deny";
    }
}
