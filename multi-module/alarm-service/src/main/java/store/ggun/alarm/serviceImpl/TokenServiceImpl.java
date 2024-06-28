package store.ggun.alarm.serviceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.model.TokenModel;
import store.ggun.alarm.repository.TokenRepository;
import java.time.Instant;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl {

    private final TokenRepository tokenRepository;

    public void saveRefreshToken(String email, String refreshToken, long refreshTokenExpiration){

        TokenModel token = TokenModel.builder()
                .email(email)
                .refreshToken(refreshToken)
                .expiration(Date.from(Instant.now().plusSeconds(refreshTokenExpiration)))
                .build();

        log.info("Service - TokenModel token : {}",token);

        tokenRepository.save(token)
                .flatMap(i -> Mono.just(i.getRefreshToken())).subscribe();

    }
}
