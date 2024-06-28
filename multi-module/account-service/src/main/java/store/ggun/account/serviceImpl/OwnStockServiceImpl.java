package store.ggun.account.serviceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import store.ggun.account.domain.dto.AccountDto;
import store.ggun.account.domain.dto.Messenger;
import store.ggun.account.domain.dto.OwnStockDto;
import store.ggun.account.domain.model.AccountModel;
import store.ggun.account.domain.model.OwnStockModel;
import store.ggun.account.repository.AccountRepository;
import store.ggun.account.repository.OwnStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import store.ggun.account.service.AccountService;
import store.ggun.account.service.OwnStockService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnStockServiceImpl implements OwnStockService {

    private final OwnStockRepository ownStockRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final KISOpenFeign openFeign;
//    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emf"); //엔티티 매니저 팩토리 생성
//    private final EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
//    private final EntityTransaction tx = em.getTransaction();

    @Override
    public List<OwnStockDto> findByAccount(Long id) {
        return ownStockRepository.findByAccountId(id);
    }

    @Override
    @Transactional
    public Messenger save(OwnStockDto ownStockDto) {
        Optional<OwnStockModel> stock = ownStockRepository.findByPdnoAndAccountIdAndTradeType(ownStockDto.getPdno(), ownStockDto.getAccount(), ownStockDto.getTradeType());
        Optional<AccountModel> account = accountRepository.findById(ownStockDto.getAccount());

        long totalOrderAmount = ownStockDto.getPdQty() * ownStockDto.getAvgPrvs();

        long totalPurchaseAmount = stock.isEmpty() ? 0L : stock.get().getPdQty() * stock.get().getAvgPrvs();

        long totalPdQty = 0L;

        if (ownStockDto.getSllBuyDvsnCd() == 1) {

            AccountDto acDto = AccountDto.builder()
                    .id(account.get().getId())
                    .balance(totalOrderAmount)
                    .tradeType("출금")
                    .bank(ownStockDto.getPrdtName() + " 매수")
                    .build();
            String msg = accountService.withdraw(acDto).getMessage();
            if (msg.equals("SUCCESS")) {
                if (stock.isEmpty()) {
                    ownStockRepository.save(OwnStockModel.builder()
                            .pdno(ownStockDto.getPdno())
                            .prdtName(ownStockDto.getPrdtName())
                            .pdQty(ownStockDto.getPdQty())
                            .avgPrvs(ownStockDto.getAvgPrvs())
                            .tradeType(ownStockDto.getTradeType())
                            .account(account.get())
                            .build());
                    return Messenger.builder().message(msg).build();

                } else {
                    totalPdQty = stock.get().getPdQty() + ownStockDto.getPdQty();


                    stock.get().setPdQty(totalPdQty);
                    stock.get().setAvgPrvs((totalOrderAmount + totalPurchaseAmount) / totalPdQty);

                    ownStockRepository.save(stock.get());


                    return Messenger.builder().message(msg).build();
                }
            }else {
                return Messenger.builder().message(msg).build();
            }
        } else if (ownStockDto.getSllBuyDvsnCd() == 2) {
            if (stock.isEmpty()) {

                return Messenger.builder().message("종목을 보유하고 있지 않습니다.").build();

            } else {
                totalPdQty = stock.get().getPdQty() - ownStockDto.getPdQty();

                stock.get().setPdQty(totalPdQty);
//                stock.get().setAvgPrvs( totalPurchaseAmount / totalPdQty);

                if (ownStockRepository.save(stock.get()).getPdQty() == 0) {
                    ownStockRepository.deleteByPdnoAndAccountId(ownStockDto.getPdno(), ownStockDto.getAccount());
                }

                AccountDto acDto = AccountDto.builder()
                        .id(account.get().getId())
                        .balance(totalOrderAmount)
                        .tradeType("입금")
                        .bank(ownStockDto.getPrdtName() + " 매도")
                        .build();

                accountService.deposit(acDto);
                return Messenger.builder().message("주문(매도) 완료").build();
            }
        }
        return Messenger.builder().message("매도/매수 선택").build();
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Optional<OwnStockDto> modify(OwnStockDto ownStockDto) {
        return Optional.empty();
    }

    @Override
    public List<OwnStockDto> findAll() {
        return null;
    }

    @Override
    public Optional<OwnStockDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        openFeign.getAppToken();
        openFeign.getPrice("005930");
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }


}
