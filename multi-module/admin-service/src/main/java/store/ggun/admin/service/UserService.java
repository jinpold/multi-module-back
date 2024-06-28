package store.ggun.admin.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.UserModel;
import store.ggun.admin.domain.dto.UserDto;

import java.util.Optional;
public interface UserService extends CommandService<UserDto>, QueryService<UserDto>, UserDetailsService{
    // command
    Messenger modify(UserDto user);
    // query
    Messenger login(UserDto param);
    Boolean existsByUsername(String username);
    Optional<UserModel> findUsersByRole(String role);
    Optional<UserModel> findUserByUsername(String username);
    Boolean logout(String accessToken);
    Optional<UserDto> findUserInfo(String username);

    default UserModel dtoToEntity(UserDto dto){
        return UserModel.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .age(dto.getAge())
                .sex(dto.getSex())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .asset(dto.getAsset())
                .color(dto.getColor())
                .investmentPropensity(dto.getInvestmentPropensity())
                .role(dto.getRole())
                .build();
    }


    default UserDto entityToDto(UserModel ent) {
        return UserDto.builder()
                .id(ent.getId())
                .username(ent.getUsername())
                .password(ent.getPassword())
                .name(ent.getName())
                .age(ent.getAge())
                .sex(ent.getSex())
                .email(ent.getEmail())
                .address(ent.getAddress())
                .phone(ent.getPhone())
                .asset(ent.getAsset())
                .color(ent.getColor())
                .role(ent.getRole())
                .build();
    }



}


