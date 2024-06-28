package store.ggun.account.domain.model;

import jakarta.persistence.*;
//import com.jsggun.account.trade.model.Trade;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"id"})
@Entity(name = "accounts")
@Builder
@AllArgsConstructor
@Setter
public class AccountModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private String acno;
    private String acpw;
    private Long balance;
    private String refundAcno;
    private String bank;
    private String acType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

//    @OneToMany(mappedBy = "account",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Trade> trades;




}

