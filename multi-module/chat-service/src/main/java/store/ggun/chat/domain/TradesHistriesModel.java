package store.ggun.chat.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "trades_histories")
public class TradesHistriesModel extends BaseEntity {
    @Id
    private Long tradeId;
    private long odno; //주문번호
    private String ordDvsnName; //주문구분명 : 시장가 or 지정가
    private int ordDvsnCd; //주문 구분 코드
    private int sllBuyDvsnCd; // 매도 매수 구분코드
    private int pdno; //상품번호
    private String prdtName; //상품명
    private int ordQty; //주문 수량
    private int totCcldQty; // 총 체결수량
    private int ccldPrvs; // 평균가(체결가, 지정가) - 이름만 평균가임
    private String tradeType; // 거래타입(ai,user)
    private long purchaseFee; //매수 수수료
    private long sellingFee; //매도 수수료
    private long purchaseTax;
    private long sellingTax;
    private float standardFee; // 수수료(기준)
    private float baseTax; //세금(기준)
    // 기존에 odno와 ord_tmd컬럼은 reg_date로 처리하기로 함

}
