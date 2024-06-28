package store.ggun.admin.domain.dto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Data
@Builder
@NoArgsConstructor
@Log4j2
public class TransactionDto {

    private Long id;
    private String username;
    private String buyStock;
    private String buyQuantity;
    private String buyTotal;
    private String sellStock;
    private String sellQuantity;
    private String sellTotal;
    private String tradeDate;
    private String closingPrice;
    private String netProfit;
    private String purchaseFee;
    private String sellingFee;
    private String purchaseTax;
    private String sellingTax;
    private String purchaseTotal;
    private String sellingTotal;
    private String standardFee;
    private String baseTax;
    private String tradeTotal;


    @QueryProjection
    public TransactionDto(Long id, String username, String buyStock,
                          String buyQuantity, String buyTotal, String sellStock,
                          String sellQuantity, String sellTotal, String tradeDate,
                          String closingPrice, String netProfit, String purchaseFee,
                          String sellingFee, String purchaseTax, String sellingTax,
                          String purchaseTotal, String sellingTotal, String standardFee,
                          String baseTax, String tradeTotal) {
        this.id = id;
        this.username = username;
        this.buyStock = buyStock;
        this.buyQuantity = buyQuantity;
        this.buyTotal = buyTotal;
        this.sellStock = sellStock;
        this.sellQuantity = sellQuantity;
        this.sellTotal = sellTotal;
        this.tradeDate = tradeDate;
        this.closingPrice = closingPrice;
        this.netProfit = netProfit;
        this.purchaseFee = purchaseFee;
        this.sellingFee = sellingFee;
        this.purchaseTax = purchaseTax;
        this.sellingTax = sellingTax;
        this.purchaseTotal = purchaseTotal;
        this.sellingTotal = sellingTotal;
        this.standardFee = standardFee;
        this.baseTax = baseTax;
        this.tradeTotal = tradeTotal;
    }
}
