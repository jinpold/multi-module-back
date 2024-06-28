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
@Builder
@Data
@Log4j2
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String job;
    private String token;

//    private List<Account> accounts= new ArrayList<>();
}
