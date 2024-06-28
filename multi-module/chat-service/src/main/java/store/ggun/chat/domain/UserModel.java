package store.ggun.chat.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class UserModel {

    @Id Long userId ;
    String firstName ;
    String lastName ;
    String email;
    String password ;


    List <RoleModel> roles ;



    @Override
    public String toString() {
        return "User [userId=" + userId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                ", password=" + password +
                "]";
    }
}