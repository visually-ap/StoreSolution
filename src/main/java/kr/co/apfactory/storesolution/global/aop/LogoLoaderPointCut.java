package kr.co.apfactory.storesolution.global.aop;

import kr.co.apfactory.storesolution.application.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

@Aspect
@Component
@RequiredArgsConstructor
public class LogoLoaderPointCut {
    private final SiteService siteService;

    //com/example/aop/controller 패키지 하위 클래스들 전부 적용하겠다고 지점 설정
    @Pointcut("(execution(* kr.co.apfactory.storesolution.application.controller.mvc.*.*(..)) || execution(* kr.co.apfactory.storesolution..controller.*.*(..))) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void cut() {
    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) throws MalformedURLException, UnsupportedEncodingException {
        //실행되는 함수 이름을 가져오고 출력
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        if (!"initBinder".equals(method.getName())) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof Model) {
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

                    Model model = (Model) arg;

                    model.addAttribute("logo", siteService.getLogoImage());
                }
            }
        }
    }
}
