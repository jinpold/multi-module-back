package store.ggun.admin.domain.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString(exclude = {"id"})
@Entity(name="boards")

public class BoardModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String description;

    @OneToMany(mappedBy = "boardModel" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ArticleModel> articleModel;
    @Builder(builderMethodName = "builder")
    public BoardModel(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
