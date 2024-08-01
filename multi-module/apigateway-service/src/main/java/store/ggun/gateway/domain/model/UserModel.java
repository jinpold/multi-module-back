package store.ggun.gateway.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.ggun.gateway.domain.vo.Registration;
import store.ggun.gateway.domain.vo.Role;

import java.util.List;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private String id;
    private String email;
    private String name;
    private String profile;
    private List<Role> roles;
    private Registration registration;
}
