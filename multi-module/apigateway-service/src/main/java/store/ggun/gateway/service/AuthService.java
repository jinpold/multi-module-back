package store.ggun.gateway.service;

import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import store.ggun.gateway.domain.dto.LoginDto;

public interface AuthService {
    Mono<ServerResponse> localLogin(LoginDto dto);
    Mono<ServerResponse> refresh(String refreshToken);
    Mono<ServerResponse> logout(String refreshToken);
}