package code.sns.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundObjectException.class)
    protected ResponseEntity<?> handleNotFoundArgumentException(NotFoundObjectException e) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code("not found item")
                .message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
