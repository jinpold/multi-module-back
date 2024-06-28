package store.ggun.account.repository;

import store.ggun.account.domain.dto.OwnStockDto;
import store.ggun.account.domain.model.OwnStockModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnStockRepository extends JpaRepository<OwnStockModel,Long> {



    Optional<OwnStockModel> findByPdnoAndAccountIdAndTradeType(String pdno, Long account, String tradeType);


    void deleteByPdnoAndAccountId(String pdno, Long account);

    List<OwnStockDto> findByAccountId(Long id);
}
