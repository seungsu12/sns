package code.sns.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundObjectException  extends RuntimeException{

    public NotFoundObjectException(String message) {
        super(message);
    }
}
