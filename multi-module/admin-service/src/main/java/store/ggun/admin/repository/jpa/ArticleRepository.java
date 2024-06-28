package store.ggun.admin.repository.jpa;
import store.ggun.admin.domain.model.ArticleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleModel, Long> {

    // JPQL Default 방식
    @Query("select a "
            + "from articles a where a.boardModel.id = :boardId order by a.id desc")
    List<ArticleModel> getArticleByBoardId(@Param("boardId") Long boardId);

}
