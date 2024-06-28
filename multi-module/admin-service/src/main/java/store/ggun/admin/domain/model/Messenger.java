package store.ggun.admin.domain.model;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Messenger {

    private String message;
    private int status;
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String username;
}
