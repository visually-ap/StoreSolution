package kr.co.apfactory.storesolution.global.error;

import kr.co.apfactory.storesolution.domain.dto.common.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionRestController {
    // 400
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<?> BadRequestException(final RuntimeException ex) {
        log.error("error", ex);
        return new ResponseEntity<>(ResponseDTO.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 401
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(final AccessDeniedException ex) {
        return new ResponseEntity<>(ResponseDTO.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 500
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<?> handleAll(final Exception ex) {
        log.error("error", ex);
        return new ResponseEntity<>(ResponseDTO.builder().build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
