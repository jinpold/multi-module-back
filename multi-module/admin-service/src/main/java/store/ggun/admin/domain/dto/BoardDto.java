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
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String regDate;
    private String modDate;
    private List<ArticleModel> articleModel;
}