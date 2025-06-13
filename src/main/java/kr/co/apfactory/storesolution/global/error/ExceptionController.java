package kr.co.apfactory.storesolution.global.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    // 400
    @ExceptionHandler({ RuntimeException.class })
    public String BadRequestException(final RuntimeException ex) {
        log.error("error", ex);
        return "redirect:/error/access";
    }

    // 401
    @ExceptionHandler({ AccessDeniedException.class })
    public String handleAccessDeniedException(final AccessDeniedException ex) {
        return "redirect:/error/deny";
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public String handleAll(final Exception ex) {
        log.error("error", ex);
        return "redirect:/error/access";
    }
}
