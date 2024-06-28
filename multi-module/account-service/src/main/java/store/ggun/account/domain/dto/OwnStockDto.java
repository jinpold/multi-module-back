package store.ggun.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Log4j2
public class OwnStockDto {

    private Long id;
    private String pdno;
    private String prdtName;
    private Long pdQty;
    private Long avgPrvs;
    private String tradeType;
    private Long account;
    private int sllBuyDvsnCd;

}
