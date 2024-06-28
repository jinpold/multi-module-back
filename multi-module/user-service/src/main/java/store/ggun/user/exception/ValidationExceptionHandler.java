package store.ggun.user.exception;

import store.ggun.user.enums.ErrorCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, NoSuchElementException.class})
    public ErrorCode ValidationException(Exception e) {
        return ErrorCode.VALIDATE_FAILED;
    }
}
