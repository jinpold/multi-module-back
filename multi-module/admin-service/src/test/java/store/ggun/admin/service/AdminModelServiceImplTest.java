package store.ggun.admin.service;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.dto.AdminDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@Transactional
public class AdminModelServiceImplTest {

    @Autowired
    private AdminService service;

    @Test
    @DisplayName("회원수")
    void count() {
        Long totalUsers = service.count();
        assertThat(totalUsers).isEqualTo(20L);
    }
    @Test
    @DisplayName("회원가입")
    void save() {
        AdminDto dto = new AdminDto();
        Messenger msg = service.save(dto);
        assertThat(msg.getMessage()).isEqualTo("성공");
    }
    @Test
    @DisplayName("회원목록")
    void findAll() throws SQLException {
        System.out.println(service.findAll());
    }
    @Test
    @DisplayName("계정 조회")
    void findById() {
        Long id = 1L;
        System.out.println(service.findById(id));
    }
    @Test
    @DisplayName("정보 변경")
    void modify() {
        AdminDto dto = new AdminDto();
        Messenger msg = service.save(dto);
        assertThat(msg.getMessage()).isEqualTo("성공");
    }
    @Test
    @DisplayName("계정 삭제")
    void deleteById() {
        Long id = 1L;
        System.out.println(service.findById(id));
    }
}