package store.ggun.admin.repository.jpa;
import store.ggun.admin.repository.dao.TransactionDao;
import store.ggun.admin.domain.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long>, TransactionDao {

}

