package store.ggun.account.service;

import store.ggun.account.domain.dto.Messenger;
import store.ggun.account.domain.dto.UserDto;
import store.ggun.account.domain.model.UserModel;

import java.util.List;
import java.util.Optional;


public interface UserService extends CommandService<UserDto>, QueryService<UserDto> {
    //command

    //query
    List<UserDto> findByName(String name);
    List<UserDto> findUsersByJob(String job);
    List<UserDto> findUsersByName(String name);
    Optional<UserDto> findUserByUsername(String username);


    default UserModel dtoToEntity(UserDto userDto){
        return UserModel.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .job(userDto.getJob())
                .build();
    }

    default UserDto entityToDto(UserModel userModel){
        return UserDto.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .password(userModel.getPassword())
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .job(userModel.getJob())
//                .regDate(String.valueOf(user.getRegDate()))
//                .modDate(String.valueOf(user.getRegDate()))
                .build();
    }

    Messenger login(UserDto param);

    Boolean existsByUsername(String param);

    Boolean logout(String accessToken);
}
