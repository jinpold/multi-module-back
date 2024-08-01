package store.ggun.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import store.ggun.gateway.domain.dto.LoginDto;
import store.ggun.gateway.service.impl.AuthServiceImpl;


@Configuration
@RequiredArgsConstructor
public class AuthRouter {
    private final AuthServiceImpl authServiceImpl;

    @Bean
    RouterFunction<ServerResponse> authRoutes() {
        return RouterFunctions.route()
                .POST("/auth/login/local", req -> req.bodyToMono(LoginDto.class).flatMap(authServiceImpl::localLogin))
                .POST("/auth/refresh", req -> authServiceImpl.refresh(req.headers().header("Authorization").get(0)))
                .POST("/auth/logout", req -> authServiceImpl.logout(req.headers().header("Authorization").get(0)))
                .POST("/auth/admin/login", req -> req.bodyToMono(LoginDto.class).flatMap(authServiceImpl::adminLogin))
                .build();
    }
}