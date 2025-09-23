package kr.co.apfactory.storesolution.global.security.handler;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Log4j2
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private String message;

    /**
        HttpServletRequest : 웹에서 넘어온 request 값을 가지고 있는 객체
        HttpServletResponse : 출력을 정의할 수 있는 객체
        AuthenticationException : 로그인 실패 정보를 가지고 있는 객체
    */
    @Override
    @ResponseBody
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        // request 객체의 getParameter 메소드를 통하여 유저 아이디와 비밀번호를 불러올수 있다.
        String loginId = request.getParameter("loginId");
        String failMsg = getFailureMessage(exception); // 로그인이 실패정보

        request.setAttribute("loginId", loginId);
        request.setAttribute("message", failMsg);

        request.getRequestDispatcher("/login").forward(request, response);

    }

    private String getFailureMessage(AuthenticationException exception) {
        if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
            return message = "아이디 또는 비밀번호를 확인해 주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            return message = "아이디 또는 비밀번호를 확인해 주세요.";
        } else if (exception instanceof DisabledException) {
            return message = "아이디 또는 비밀번호를 확인해 주세요.";
//            return message = "비활성화 또는 강퇴된 계정입니다. 관리자에게 문의해주세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            return message = "아이디 또는 비밀번호를 확인해 주세요.";
//            return message = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의해주세요.";
        } else {
            return message = "알 수 없는 예외입니다.";
        }
    }
}
