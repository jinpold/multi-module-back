package store.ggun.admin.domain.model;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString(exclude = {"id"})
@Entity(name = "articles")
@AllArgsConstructor
@Builder

public class ArticleModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private AdminModel writer;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardModel boardModel;

    public static ArticleModel of(String title, String content){
        ArticleModel articleModel = new ArticleModel();;
        articleModel.title = title;
        articleModel.content = content;
        return articleModel;
    }
}
