package store.ggun.account.domain.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Setter
@AllArgsConstructor
@ToString(exclude = {"id"})
@Entity(name = "trades")
public class TradeModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ordDt;             //주문일
    private String ordGnoBrno;        //주문채번지점번호
    private String odno;              //주문번호
    private String ordDvsnName;       //주문구분명
    private String sllBuyDvsnCd;      //매도매수구분코드
    private String sllBuyDvsnCdName;  //매도매수구분코드명
    private String pdno;              //상품번호
    private String prdtName;          //상품명
    private String ordTmd;            //주문시각
    private String ordQty;            //주문수량
    private String totCcldQty;        //총체결수량
    private String avgPrvs;           //평균가
    private String totCcldAmt;        //총체결금액
    private String ordDvsnCd;         //주문구분코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountModel account;

}
