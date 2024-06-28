package store.ggun.user.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import store.ggun.user.domain.ArticleDto;
import store.ggun.user.domain.ArticleModel;
import store.ggun.user.domain.Messenger;
import store.ggun.user.repository.ArticleRepository;
import store.ggun.user.service.ArticleService;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    @Override
    public ArticleModel save(ArticleDto model) {
        Long writerId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        ArticleModel article = ArticleModel.builder()
                .title(model.getTitle())
                .content(model.getContent())
                .writerId(writerId)
                .boardId(model.getBoardId())
                .build();
        ArticleModel article1 = repository.save(article);
        return article1;
    }
}
