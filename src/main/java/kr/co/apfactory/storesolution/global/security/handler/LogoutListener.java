package kr.co.apfactory.storesolution.global.security.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import java.util.List;


// ApplicationListener 인터페이스를 이용해 Context가 종료되는 event를 전달 받을 수 있다.
@RequiredArgsConstructor
@Component
public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        List<SecurityContext> contexts = event.getSecurityContexts();
        for (SecurityContext securityContext : contexts) {
            if (contexts.isEmpty() == false) {

            }
        }
    }
}
