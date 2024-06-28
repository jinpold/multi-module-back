package store.ggun.user.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
@Builder
public class ArticleDto {
    private long id;
    private String title;
    private String content;
    private String writerId;
    private String boardId;
    private String answer;

    @QueryProjection
    public ArticleDto(long id, String title, String content, String writerId, String boardId, String answer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.boardId = boardId;
        this.answer = answer;
    }
}
