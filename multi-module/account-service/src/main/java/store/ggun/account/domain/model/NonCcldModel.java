package store.ggun.account.domain.model;


import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"id"})
@Entity(name = "non_cclds")
@Builder
@AllArgsConstructor
public class NonCcldModel extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ccldPrvs;
    private Long volume;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountModel accountModel;


}
