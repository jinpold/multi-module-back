package store.ggun.gateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import store.ggun.gateway.domain.vo.Role;
import store.ggun.gateway.service.provider.JwtTokenProvider;

import java.util.List;


@Slf4j
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>{

    private final JwtTokenProvider jwtTokenProvider;

    public AuthorizationHeaderFilter(JwtTokenProvider jwtTokenProvider){
        super(Config.class);
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Data
    public static class Config {
        private String headerName;
        private String headerValue;
        private List<Role> roles;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("Request URL: {}", exchange.getRequest().getURI());
            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                return onError(exchange, HttpStatus.UNAUTHORIZED, "No Authorization Header");

            @SuppressWarnings("null")
            String token = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if(token == null)
                return onError(exchange, HttpStatus.UNAUTHORIZED, "No Token or Invalid Token");

            String jwt = jwtTokenProvider.removeBearer(token);

            if(!jwtTokenProvider.isTokenValid(jwt, false))
                return onError(exchange, HttpStatus.UNAUTHORIZED, "Invalid Token");

            List<Role> roles = jwtTokenProvider.extractRoles(jwt).stream().map(i -> Role.valueOf(i)).toList();

            for(var i : config.getRoles()){
                if(roles.contains(i))
                    return chain.filter(exchange);
            }

            return onError(exchange, HttpStatus.UNAUTHORIZED, "No Permission");
        });
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatusCode httpStatusCode, String message){
        log.error("Error Occured : {}, {}, {}", exchange.getRequest().getURI(), httpStatusCode, message);
        exchange.getResponse().setStatusCode(httpStatusCode);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(message.getBytes());
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}