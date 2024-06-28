package store.ggun.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import store.ggun.user.domain.ArticleModel;

public interface ArticleRepository extends JpaRepository<ArticleModel, Long>, ArticleDao {
    ArticleModel findByTitle(String title);
}
