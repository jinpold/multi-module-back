package store.ggun.alarm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import store.ggun.alarm.domain.model.PostModel;

@Repository
public interface PostRepository extends ReactiveMongoRepository<PostModel, String> {
//    Flux<PostModel> findByPostId (String postId);
}
