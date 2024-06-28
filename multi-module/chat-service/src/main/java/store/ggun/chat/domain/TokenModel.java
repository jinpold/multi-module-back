package store.ggun.chat.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "tokens")
public class TokenModel {
    @Id
    private String tokenId;
    private String refreshToken;
    private String email;
    private Date expiration;

    @Override
    public String toString() {
        return "RefreshTokenModel [tokenId=" + tokenId +
                ", refreshToken=" + refreshToken +
                ", email=" + email +
                ", expiration=" + expiration +
                "]";
    }

}
