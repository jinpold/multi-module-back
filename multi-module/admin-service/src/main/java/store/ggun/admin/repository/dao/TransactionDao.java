package store.ggun.admin.repository.dao;
import store.ggun.admin.domain.dto.TransactionDto;

import java.util.List;
import java.util.Map;


public interface TransactionDao {

    Long countAllTransactions();

    List<TransactionDto> getAllTransactions();

    Map<String, Double> getTotalByDate();

    Map<String, Map<String, Integer>> getQuantityByDate();


//    @Query("SELECT t FROM Transactions t ORDER BY t.id DESC")
    Long getTransactionsById();

//    @Query("SELECT t FROM Transactions t ORDER BY t.username ASC")
    List<String> getTransactionsByUsername();

//    @Query("SELECT t FROM Transactions t ORDER BY t.buyStock ASC")
//    List<TransactionDto> getTransactionsByBuyStock();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.buyQuantity ASC")
//    List<TransactionDto> getTransactionsByBuyQuantity();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.buyTotal ASC")
//    List<TransactionDto> getTransactionsByBuyTotal();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellStock ASC")
//    List<TransactionDto> getTransactionsBySellStock();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellQuantity ASC")
//    List<TransactionDto> getTransactionsBySellQuantity();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellTotal ASC")
//    List<TransactionDto> getTransactionsBySellTotal();

//    @Query("SELECT t FROM Transactions t ORDER BY t.tradeDate ASC")
      Map<String, Double> getNetProfitByDate();

//    @Query("SELECT t FROM Transactions t ORDER BY t.closingPrice ASC")
//    List<TransactionDto> getTransactionsByClosingPrice();

//    @Query("SELECT t FROM Transactions t ORDER BY t.netProfit ASC")
    List<TransactionDto> getTransactionsByNetProfit();

//    @Query("SELECT t FROM Transactions t ORDER BY t.purchaseFee ASC")
//    List<TransactionDto> getTransactionsByPurchaseFee();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellingFee ASC")
//    List<TransactionDto> getTransactionsBySellingFee();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.purchaseTax ASC")
//    List<TransactionDto> getTransactionsByPurchaseTax();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellingTax ASC")
//    List<TransactionDto> getTransactionsBySellingTax();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.purchaseTotal ASC")
//    List<TransactionDto> getTransactionsByPurchaseTotal();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.sellingTotal ASC")
//    List<TransactionDto> getTransactionsBySellingTotal();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.standardFee ASC")
//    List<TransactionDto> getTransactionsByStandardFee();
//
//    @Query("SELECT t FROM Transactions t ORDER BY t.baseTax ASC")
//    List<TransactionDto> getTransactionsByBaseTax();
}
