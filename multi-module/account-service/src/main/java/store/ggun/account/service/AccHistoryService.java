package store.ggun.account.service;

import store.ggun.account.domain.model.AccHistoryModel;
import store.ggun.account.domain.dto.AccHistoryDto;
import store.ggun.account.domain.model.AccountModel;

import java.util.List;

public interface AccHistoryService extends CommandService<AccHistoryDto>, QueryService<AccHistoryDto> {


    default AccHistoryModel dtoToEntity(AccHistoryDto accHistorydto, AccountModel accountModel){
        return AccHistoryModel.builder()
                .id(accHistorydto.getId())
                .balance(accHistorydto.getBalance())
                .tradeType(accHistorydto.getTradeType())
                .bank(accHistorydto.getBank())
                .account(accountModel)
                .build();
    }

    default AccHistoryDto entityToDto(AccHistoryModel accHistoryModel){
        return AccHistoryDto.builder()
                .id(accHistoryModel.getId())
                .balance(accHistoryModel.getBalance())
                .tradeType(accHistoryModel.getTradeType())
                .bank(accHistoryModel.getBank())
                .account(accHistoryModel.getAccount().getId())
                .build();
    }


    List<AccHistoryDto> findByAccount(Long id);
}
