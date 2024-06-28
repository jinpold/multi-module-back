package store.ggun.account.serviceImpl;

import store.ggun.account.domain.model.AccHistoryModel;
import store.ggun.account.repository.AccHistoryRepository;
import store.ggun.account.domain.model.AccountModel;
import store.ggun.account.domain.dto.AccountDto;
import store.ggun.account.repository.AccountRepository;
import store.ggun.account.domain.dto.Messenger;
import store.ggun.account.service.AccountService;
import store.ggun.account.service.UtilService;
import store.ggun.account.domain.model.UserModel;
import store.ggun.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final UserRepository userRepository;
    private final AccHistoryRepository accHistoryRepository;
    private final UtilService util;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Messenger save(AccountDto accountDto) {
        UserModel userModel = userRepository.findById(accountDto.getUser()).orElseThrow();

        String encodePassword = passwordEncoder.encode(accountDto.getAcpw());
        String acno = "";
        if (accountDto.getAcType().equals("01")) {
            acno = util.createRandomInteger(20000000, 29999999) + "-" + accountDto.getAcType();
            while (repository.existsByAcno(acno)) {
                acno = util.createRandomInteger(20000000, 29999999) + "-" + accountDto.getAcType();
            }
        } else if (accountDto.getAcType().equals("02")) {
            acno = util.createRandomInteger(50000000, 59999999) + "-" + accountDto.getAcType();
            while (repository.existsByAcno(acno)) {
                acno = util.createRandomInteger(50000000, 59999999) + "-" + accountDto.getAcType();
            }
        }

        AccountModel accountModel = repository.save(AccountModel.builder()
                .id(accountDto.getId())
                .acno(acno)
                .acpw(encodePassword)
                .balance(0L)
                .refundAcno(accountDto.getRefundAcno())
                .bank(accountDto.getBank())
                .acType(accountDto.getAcType())
                .user(userModel)
                .build());

        return Messenger.builder()
                .message(accountModel instanceof AccountModel ? "SUCCESS" : "FAIURE")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Optional<AccountDto> modify(AccountDto accountDto) {
        return Optional.empty();
    }

    @Override
    public List<AccountDto> findAll() {
        return null;
    }

    @Override
    public Optional<AccountDto> findById(Long id) {

        return Optional.ofNullable(
                entityToDto(Objects.requireNonNull(repository.findById(id).orElse(null))));
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<AccountDto> findByUser(Long id) {
        UserModel userModel = userRepository.findById(id).get();
        return repository.findByUser(userModel)
                .stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    @Transactional
    public Messenger deposit(AccountDto accountDto) {
        AccountModel ac = repository.findById(accountDto.getId()).get();

        ac.setBalance(ac.getBalance() + accountDto.getBalance());

        repository.save(ac);

        accHistoryRepository.save(AccHistoryModel.builder()
                .balance(accountDto.getBalance())
                .tradeType(accountDto.getTradeType())
                .bank(accountDto.getBank())
                .imp_uid(accountDto.getPaymentUid())
                .account(ac)
                .build());

        return Messenger.builder()
                .message(repository.save(ac).getBalance() >= accountDto.getBalance() ? "SUCCESS" : "FAIURE")
                .build();
    }

    @Override
    @Transactional
    public Messenger withdraw(AccountDto accountDto) {
        AccountModel ac = repository.findById(accountDto.getId()).get();
        long txBalance = ac.getBalance() - accountDto.getBalance();

        if (txBalance >= 0) {
            ac.setBalance(txBalance);

            repository.save(ac);

            accHistoryRepository.save(AccHistoryModel.builder()
                    .balance(accountDto.getBalance())
                    .tradeType(accountDto.getTradeType())
                    .bank(accountDto.getBank())
                    .imp_uid(accountDto.getPaymentUid())
                    .account(ac)
                    .build());

            return Messenger.builder()
                    .message(repository.save(ac).getBalance() <= ac.getBalance() ? "SUCCESS" : "FAIURE")
                    .build();
        }else {
            return Messenger.builder()
                    .message("잔액이 부족합니다.")
                    .build();
        }
    }

}
