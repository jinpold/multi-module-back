package store.ggun.user.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
@Data
@Builder
public class UserDto extends BaseEntity {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String age;
    private String sex;
    private String email;
    private String ssnF;
    private String ssnS;
    private String address;
    private String phone;
    private Long asset;
    private String color;
    private String investmentPropensity;
    private String token;
    private String role;

    @QueryProjection
    public UserDto(Long id, String username, String password, String name, String age, String sex, String email, String ssnF, String ssnS, String address, String phone, Long asset, String color, String investmentPropensity, String token, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.ssnF = ssnF;
        this.ssnS = ssnS;
        this.address = address;
        this.phone = phone;
        this.asset = asset;
        this.color = color;
        this.investmentPropensity = investmentPropensity;
        this.token = token;
        this.role = role;
    }
}
