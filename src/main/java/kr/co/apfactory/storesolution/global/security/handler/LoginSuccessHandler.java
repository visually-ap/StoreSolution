package kr.co.apfactory.storesolution.global.security.handler;

import kr.co.apfactory.storesolution.application.service.UserService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Data
@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    private String defaultUrl = "/";

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 로그인 실패 에러 세션 삭제
        clearAuthenticationAttributes(request);

        // 로그인 후 경로 설정
        resultRedirectStrategy(request, response);

        // 로그인 이력 저장
        userService.saveLoginHistory();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Security에서 요청을 가로챈 경우 사용자가 이전에 사용했던 uri 정보를 저장한 객체
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String targetUrl = "";

        // uri 정보가 있다면 전 페이지로 보낸다.
        if (savedRequest != null) {
            targetUrl = savedRequest.getRedirectUrl();

            if(targetUrl.endsWith("error")){
                targetUrl = "/";
            }

            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            HttpSession session = request.getSession();
            if (session != null) {
                targetUrl = (String) session.getAttribute("prevPage");
                // 회원 관련 기능에서 시작하면 홈으로
                if (targetUrl != null) {
                    session.removeAttribute("prevPage");
                    getRedirectStrategy().sendRedirect(request, response, targetUrl);
                } else {
                    redirectStrategy.sendRedirect(request, response, defaultUrl);
                }
            } else {
                redirectStrategy.sendRedirect(request, response, defaultUrl);
            }
        }
    }
}


