package store.ggun.admin.service;
import store.ggun.admin.domain.model.TransactionModel;
import store.ggun.admin.domain.dto.TransactionDto;

import java.util.Map;


public interface TransactionService extends CommandService<TransactionDto>, QueryService<TransactionDto> {

    Map<String, Double> getNetProfitByDate();

    Map<String, Double> getTotalByDate();

    Map<String, Map<String, Integer>> getQuantityByDate();

    default TransactionModel dtoToEntity(TransactionDto dto){
        return TransactionModel.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .buyStock(dto.getBuyStock())
                .buyQuantity(dto.getBuyQuantity())
                .buyTotal(dto.getBuyTotal())
                .sellStock(dto.getSellStock())
                .sellQuantity(dto.getSellQuantity())
                .sellTotal(dto.getSellTotal())
                .tradeDate(dto.getTradeDate())
                .closingPrice(dto.getClosingPrice())
                .netProfit(dto.getNetProfit())
                .purchaseFee(dto.getPurchaseFee())
                .sellingFee(dto.getSellingFee())
                .purchaseTax(dto.getPurchaseTax())
                .sellingTax(dto.getSellingTax())
                .purchaseTotal(dto.getPurchaseTotal())
                .sellingTotal(dto.getSellingTotal())
                .standardFee(dto.getStandardFee())
                .baseTax(dto.getBaseTax())
                .tradeTotal(dto.getTradeTotal())
                .build();
    }

    default TransactionDto entityToDto(TransactionModel ent) {
        return TransactionDto.builder()
                .id(ent.getId())
                .username(ent.getUsername())
                .buyStock(ent.getBuyStock())
                .buyQuantity(ent.getBuyQuantity())
                .buyTotal(ent.getBuyTotal())
                .sellStock(ent.getSellStock())
                .sellQuantity(ent.getSellQuantity())
                .sellTotal(ent.getSellTotal())
                .tradeDate(ent.getTradeDate())
                .closingPrice(ent.getClosingPrice())
                .netProfit(ent.getNetProfit())
                .purchaseFee(ent.getPurchaseFee())
                .sellingFee(ent.getSellingFee())
                .purchaseTax(ent.getPurchaseTax())
                .sellingTax(ent.getSellingTax())
                .purchaseTotal(ent.getPurchaseTotal())
                .sellingTotal(ent.getSellingTotal())
                .standardFee(ent.getStandardFee())
                .baseTax(ent.getBaseTax())
                .tradeTotal(ent.getTradeTotal())
                .build();
    }
}
