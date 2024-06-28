package store.ggun.admin.service;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.dto.AdminDto;
import store.ggun.admin.domain.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Messenger login(AdminDto adminDto);
    Messenger userLogin(UserDto userDto);
    String createToken(AdminDto adminDto);
    String createUserToken(UserDto userDto);
}
