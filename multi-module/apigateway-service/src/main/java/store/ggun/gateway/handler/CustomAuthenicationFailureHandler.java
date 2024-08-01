package store.ggun.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class CustomAuthenicationFailureHandler implements ServerAuthenticationFailureHandler {

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        webFilterExchange.getExchange().getResponse().setStatusCode(HttpStatus.FOUND);
        webFilterExchange.getExchange().getResponse().getHeaders().setLocation(URI.create("http://localhost:3000"));
        webFilterExchange.getExchange().getResponse().getHeaders().add("Content-Type", "application/json");
        return webFilterExchange.getExchange().getResponse().setComplete();

        // throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
    }

}