package kkkw.subrandom.config;

import kkkw.subrandom.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /* 비지니스 로직 에러 */
    @ExceptionHandler(BaseException.class)
    private ResponseEntity<String> handleBusinessException(BaseException e) {
        log.error("BusinessError ");
        log.error(e.getErrorCode().getMessage());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(e.getErrorCode().getMessage());
    }
    /* 나머지 예외 처리 */
    @ExceptionHandler(Exception.class)
    private ResponseEntity<String>  handleException(Exception e) {
        log.error("Exception Error ", e);
        return ResponseEntity.status(500).body("서버 내부에서 알 수 없는 오류가 발생했습니다.");
    }
}
