package store.ggun.account.domain.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(exclude = {"id"})
@Entity(name = "own_stocks")
@Builder
@AllArgsConstructor
public class OwnStockModel extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pdno;
    private String prdtName;
    private Long pdQty;
    private Long avgPrvs;
    private String tradeType;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountModel account;


}
