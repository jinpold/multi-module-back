package store.ggun.admin.domain.model;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@ToString(exclude = {"id"})
@AllArgsConstructor
public class TransactionModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "buy_stock")
    private String buyStock;
    @Column(name = "buy_quantity")
    private String buyQuantity;
    @Column(name = "buy_total")
    private String buyTotal;
    @Column(name = "sell_stock")
    private String sellStock;
    @Column(name = "sell_quantity")
    private String sellQuantity;
    @Column(name = "sell_total")
    private String sellTotal;
    @Column(name = "trade_date")
    private String tradeDate;
    @Column(name = "closing_Price")
    private String closingPrice;
    @Column(name = "net_profit")
    private String netProfit;
    @Column(name = "purchase_fee")
    private String purchaseFee;
    @Column(name = "selling_fee")
    private String sellingFee;
    @Column(name = "purchase_tax")
    private String purchaseTax;
    @Column(name = "selling_tax")
    private String sellingTax;
    @Column(name = "purchase_total")
    private String purchaseTotal;
    @Column(name = "selling_total")
    private String sellingTotal;
    @Column(name = "standard_fee")
    private String standardFee;
    @Column(name = "base_tax")
    private String baseTax;
    @Column(name = "trade_total")
    private String tradeTotal;

}

