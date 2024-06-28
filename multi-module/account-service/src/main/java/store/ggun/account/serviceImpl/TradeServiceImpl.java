package store.ggun.account.serviceImpl;

import store.ggun.account.domain.model.AccountModel;
import store.ggun.account.domain.dto.TradeDto;
import store.ggun.account.repository.TradeRepository;
import store.ggun.account.repository.AccountRepository;
import store.ggun.account.domain.dto.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import store.ggun.account.service.TradeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TradeServiceImpl implements TradeService {
    private final TradeRepository repository;
    private final AccountRepository accountRepo;
    @Override
    public Messenger save(TradeDto tradeDto) {
        return null;
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Optional<TradeDto> modify(TradeDto tradeDto) {
        return Optional.empty();
    }

    @Override
    public List<TradeDto> findAll() {
        return repository.findAll()
                .stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<TradeDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }


    @Override
    public List<TradeDto> findByAcno(Long id) {
        AccountModel ac = accountRepo.findById(id).get();
        return repository.findByAccount(ac)
                .stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public List<TradeDto> findByProductNo(String prdtName) {
        log.info("임플 확인 {}" ,repository.getListByProductName(prdtName) );
        return repository.getListByProductName(prdtName)
                .stream().map(i->entityToDto(i)).toList();
    }

}