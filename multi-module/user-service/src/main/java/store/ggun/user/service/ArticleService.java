package store.ggun.user.service;

import store.ggun.user.domain.ArticleDto;
import store.ggun.user.domain.ArticleModel;
import store.ggun.user.domain.Messenger;

public interface ArticleService {
    ArticleModel save(ArticleDto model);
}
