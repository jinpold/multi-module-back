package store.ggun.admin.service;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.dto.AdminDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    Messenger login(AdminDto adminDto);
    String createToken(AdminDto adminDto);
}
