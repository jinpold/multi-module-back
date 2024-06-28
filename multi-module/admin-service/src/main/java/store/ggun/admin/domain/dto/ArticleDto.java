package store.ggun.admin.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component  // = object와 유사, 제일 범위가 큼
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class ArticleDto  {
    private Long id;
    private String title;
    private String content;
    private Long writerId;
    private Long boardId;
    private String regDate;
    private String modDate;
}