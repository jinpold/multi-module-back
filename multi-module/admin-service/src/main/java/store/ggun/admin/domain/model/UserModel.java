package store.ggun.admin.domain.model;
import jakarta.persistence.*;
import lombok.*;


@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
@ToString(exclude = {"id"})
@AllArgsConstructor
public class UserModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    private String age;
    private String sex;
    private String email;
    private String ssn;
    private String address;
    private String phone;
    private Long asset;
    private String color;
    private String investmentPropensity;
    private String token;
    private String role;


    public void setPassword(String password) {
        this.password = password;
    }
}