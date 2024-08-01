package store.ggun.gateway.domain.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
