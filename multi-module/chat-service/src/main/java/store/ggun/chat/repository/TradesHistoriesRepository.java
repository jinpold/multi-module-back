package store.ggun.chat.repository;

import store.ggun.chat.domain.TradesHistriesModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradesHistoriesRepository extends ReactiveMongoRepository<TradesHistriesModel, Long>/* , QuerydslPredicateExecutor<TradesHistriesModel>*/ {
}
