package store.ggun.account.repository;

import store.ggun.account.domain.model.TradeModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeDao {


    List<TradeModel> getListByProductName(String prdtName);
}
