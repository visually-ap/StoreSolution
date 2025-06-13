package kr.co.apfactory.storesolution.global.cookie;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CookieManager {
    public void setCookie(HttpServletResponse res, String key, String value, int seconds){
        Cookie cookie = new Cookie(key, value); // 쿠키 이름 지정하여 생성( key, value 개념)
        if (seconds > 0) {
            cookie.setMaxAge(seconds); //쿠키 유효 기간: 하루로 설정(60초 * 60분 * 24시간)
        }
        cookie.setPath("/"); //모든 경로에서 접근 가능하도록 설정
        res.addCookie(cookie); //response에 Cookie 추가
    }

    public String getCookie(HttpServletRequest req, String key){
        Cookie[] cookies=req.getCookies(); // 모든 쿠키 가져오기
        if(cookies!=null){
            for (Cookie c : cookies) {
                String name = c.getName(); // 쿠키 이름 가져오기
                String value = c.getValue(); // 쿠키 값 가져오기
                if (name.equals(key)) {
                    return value;
                }
            }
        }
        return null;
    }

    public void deleteCookie(HttpServletResponse res){
        Cookie cookie = new Cookie("삭제하고 싶은 쿠키 ID", null); // 삭제할 쿠키에 대한 값을 null로 지정
        cookie.setMaxAge(0); // 유효시간을 0으로 설정해서 바로 만료시킨다.
        res.addCookie(cookie); // 응답에 추가해서 없어지도록 함
    }

    public void deleteAllCookies(HttpServletRequest req,HttpServletResponse res) {
        Cookie[] cookies = req.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
        if (cookies != null) { // 쿠키가 한개라도 있으면 실행
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
                res.addCookie(cookies[i]); // 응답에 추가하여 만료시키기.
            }
        }
    }
}
