package store.ggun.chat.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDTO {
    String firstName;
    String lastName;
    String email;
    String name;
    String password;
}
