package store.ggun.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.ggun.user.domain.QArticleModel;
import store.ggun.user.domain.QUserModel;

@Slf4j
@RequiredArgsConstructor
public class ArticleDaoImpl implements ArticleDao{
    private final JPAQueryFactory queryFactory;
    private final QArticleModel qArticle = QArticleModel.articleModel;
}
