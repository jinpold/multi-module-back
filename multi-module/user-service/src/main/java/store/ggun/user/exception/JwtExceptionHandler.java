package store.ggun.user.exception;

import store.ggun.user.config.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtExceptionHandler {
    public static TokenProvider jwt;

    @GetMapping("/exception")
    @ExceptionHandler(ExpiredJwtException.class)
    public String ReAccessToken (){
        return "access token expired";
    }
}
