package store.ggun.account.repository;

import store.ggun.account.domain.model.QTradeModel;
import store.ggun.account.domain.model.TradeModel;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;



@RequiredArgsConstructor
@Slf4j
public class TradeDaoImpl implements TradeDao{
    private final JPAQueryFactory factory;
    private final QTradeModel trade = QTradeModel.tradeModel;
    @Override
    public List<TradeModel> getListByProductName(String prdtName) {

        log.info("쿼리dsl {}",factory.selectFrom(trade)
                .where(trade.prdtName.eq(prdtName))
                .fetch());

        return factory.selectFrom(trade)
                .where(trade.prdtName.eq(prdtName))
                .fetch();

    }
}
