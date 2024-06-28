package store.ggun.alarm.domain.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDto {

    private String username ; //  email 로 username 대체 가능
    private String password ;
}
