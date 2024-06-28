package store.ggun.alarm.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.alarm.domain.model.RoomModel;


@Repository
public interface RoomRepository extends ReactiveMongoRepository<RoomModel, String> {

}
