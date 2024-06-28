package store.ggun.alarm.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "Id")
@Document(collection = "users")
public class UserModel {

    @Id String Id ;
    String firstName ;
    String lastName ;
    String email;
    String password ;
    String profile;

    List <RoleModel> roles ;

}