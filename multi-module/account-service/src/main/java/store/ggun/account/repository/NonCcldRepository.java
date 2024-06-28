package store.ggun.account.repository;


import store.ggun.account.domain.model.NonCcldModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonCcldRepository extends JpaRepository<NonCcldModel,Long> {
}
