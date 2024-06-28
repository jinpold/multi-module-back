package store.ggun.alarm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.model.TokenModel;

@Repository
public interface TokenRepository extends ReactiveMongoRepository<TokenModel, String> {

    Mono<TokenModel> findByEmail(String email);
}
