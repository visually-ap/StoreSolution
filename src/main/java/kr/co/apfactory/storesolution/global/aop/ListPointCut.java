package kr.co.apfactory.storesolution.global.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

@Aspect
@Component
public class ListPointCut {

    //com/example/aop/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("execution(* kr.co.apfactory.storesolution.application.controller.mvc.*.*(..)) || execution(* kr.co.apfactory.storesolution..controller.*.*(..))")
    private void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) throws MalformedURLException, UnsupportedEncodingException {

        //실행되는 함수 이름을 가져오고 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (!"initBinder".equals(method.getName())) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            // 리퀘스트 받기
            String query = request.getQueryString() == null ? "" : "?" + URLDecoder.decode(request.getQueryString(), "UTF-8");
            URL url = new URL(request.getRequestURL().toString());
            String path = url.getPath();
            String gotoList = path + query;
            if (path.endsWith("list")) {
                session.setAttribute("gotoList_" + path, gotoList);
            }
        }
    }
}
