package store.ggun.alarm.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import store.ggun.alarm.domain.model.NotificationModel;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<NotificationModel, String> {
    Flux<NotificationModel> findByAdminId(String adminId);
}
