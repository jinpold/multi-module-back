package store.ggun.chat.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import store.ggun.chat.domain.TokenModel;
import store.ggun.chat.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl {

    private final TokenRepository tokenRepository;

    public void saveRefrshToken(String email, String refreshToken, long refreshTokenExpiration){

        TokenModel token = TokenModel.builder()
                .email(email)
                .refreshToken(refreshToken)
                .expiration(Date.from(Instant.now().plusSeconds(refreshTokenExpiration)))
                .build();

        log.info("service - tokenmodel token : {}",token);
        tokenRepository.save(token)
                .flatMap(i -> Mono.just(i.getRefreshToken())).subscribe();

    }
}
