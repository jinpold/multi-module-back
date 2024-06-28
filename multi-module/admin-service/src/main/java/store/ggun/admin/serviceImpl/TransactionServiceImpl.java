package store.ggun.admin.serviceImpl;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.TransactionModel;
import store.ggun.admin.domain.dto.TransactionDto;
import store.ggun.admin.repository.jpa.TransactionRepository;
import store.ggun.admin.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Messenger save(TransactionDto transactionDto) {
        TransactionModel transactionModel = transactionRepository.save(dtoToEntity(transactionDto));
        System.out.println((transactionModel instanceof TransactionModel) ? "SUCCESS" : "FAILURE");
        return Messenger.builder()
                .message((transactionModel instanceof TransactionModel) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Messenger modify(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public List<TransactionDto> findAll() throws SQLException {
        return transactionRepository.getAllTransactions().stream().toList();
    }

    @Override
    public Optional<TransactionDto> findById(Long id) {
        return transactionRepository.findById(id).stream().map(i -> entityToDto(i)).findAny();
    }

    @Override
    public Long count() {
        return transactionRepository.countAllTransactions();
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public Map<String, Double> getNetProfitByDate() {
        return transactionRepository.getNetProfitByDate();
    }

    @Override
    public Map<String, Double> getTotalByDate()  {
        return transactionRepository.getTotalByDate();
    }

    @Override
    public Map<String, Map<String, Integer>> getQuantityByDate() {
        return transactionRepository.getQuantityByDate();
    }
}
