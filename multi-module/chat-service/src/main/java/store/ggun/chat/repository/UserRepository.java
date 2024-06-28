package store.ggun.chat.repository;


import store.ggun.chat.domain.UserModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveMongoRepository<UserModel, Long> {

    Flux<UserModel> findByLastName(String lastName);

    Flux<UserModel> findAll();
    Mono<UserModel> findByEmail(String email);
}
