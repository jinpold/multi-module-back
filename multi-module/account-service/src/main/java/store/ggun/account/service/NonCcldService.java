package store.ggun.account.service;

import store.ggun.account.domain.model.AccountModel;
import store.ggun.account.domain.model.NonCcldModel;
import store.ggun.account.domain.dto.NonCcldDto;

import java.util.List;

public interface NonCcldService extends CommandService<NonCcldDto>, QueryService<NonCcldDto> {

    default NonCcldModel dtoToEntity(NonCcldDto nonCcldDto, AccountModel accountModel){
        return NonCcldModel.builder()
                .id(nonCcldDto.getId())
                .ccldPrvs(nonCcldDto.getCcldPrvs())
                .volume(nonCcldDto.getVolume())
                .accountModel(accountModel)
                .build();
    }

    default NonCcldDto entityToDto(NonCcldModel nonCcldModel){
        return NonCcldDto.builder()
                .id(nonCcldModel.getId())
                .ccldPrvs(nonCcldModel.getCcldPrvs())
                .volume(nonCcldModel.getVolume())
                .account(nonCcldModel.getAccountModel().getId())
                .build();
    }


    List<NonCcldDto> findByAccount(Long id);
}
