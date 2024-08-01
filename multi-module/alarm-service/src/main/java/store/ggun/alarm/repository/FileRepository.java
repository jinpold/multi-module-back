package store.ggun.alarm.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import store.ggun.alarm.domain.model.FileModel;

@Repository
public interface FileRepository extends ReactiveMongoRepository<FileModel, String> {
}
