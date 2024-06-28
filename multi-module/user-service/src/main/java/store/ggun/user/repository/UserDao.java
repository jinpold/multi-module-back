package store.ggun.user.repository;

import com.querydsl.core.Tuple;
import store.ggun.user.domain.UserDto;
import store.ggun.user.domain.UserModel;

import java.util.List;

public interface UserDao {
    void modify(UserDto userDto);

    UserModel modifyFind(UserDto userDto);
}
