package store.ggun.admin.domain.dto;
import store.ggun.admin.domain.model.ArticleModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class AdminDto {
    private Long id;
    private String username;
    private String password;
    private String enpName; //사원 이름 Employee name 약어
    private String enpNum; // 사원번호
    private String department; // 부서
    private String position; // 직책
    private String job; // 직무
    private String enpEmail;
    private String phone;
    private String role; // 권한
    private String token;
    private List<ArticleModel> articleModels;

}