package store.ggun.admin.serviceImpl;
import store.ggun.admin.domain.model.ArticleModel;
import store.ggun.admin.domain.dto.ArticleDto;
import store.ggun.admin.repository.jpa.ArticleRepository;
import store.ggun.admin.domain.model.BoardModel;
import store.ggun.admin.repository.jpa.BoardRepository;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.AdminModel;
import store.ggun.admin.repository.jpa.AdminRepository;
import store.ggun.admin.service.ArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;
    private final AdminRepository adminRepository;

    @Transactional
    @Override
    public Messenger save(ArticleDto articleDto) {
        BoardModel boardModel = boardRepository.findById(articleDto.getBoardId()).orElseThrow();
        AdminModel adminModel = adminRepository.findById(articleDto.getWriterId()).orElseThrow();
        ArticleModel articleModel = articleRepository.save(dtoToEntity(articleDto, boardModel, adminModel));

        return Messenger.builder()
                .id(articleModel.getBoardModel().getId()) //board id
                .message(articleModel instanceof ArticleModel ? "SUCCESS" : "FAILURE")
                .build();

    }

    @Override
    public Messenger deleteById(Long id) {
        articleRepository.deleteById(id);
        return Messenger.builder()
                .message(articleRepository.findById(id).isPresent() ? "SUCCESS" : "FAILURE")
                .build();
    }
    @Transactional
    @Override
    public Messenger modify(ArticleDto articleDto) {
        Optional<ArticleModel> article = articleRepository.findById(articleDto.getId());

        if (article.isEmpty()) {
            return Messenger.builder()
                    .message("FAILURE")
                    .build();
        }

        article.get().setTitle(articleDto.getTitle());
        article.get().setContent(articleDto.getContent());
        articleRepository.save(article.get());
        return Messenger.builder()
                .message("SUCCESS")
                .build();

    }

//        articleRepository.save(dtoToEntity(dto, boardRepository, userRepository));
//        return Messenger.builder()
//                .message("성공")
//                .build();

    @Override
    public List<ArticleDto> findAll() throws SQLException {
        return articleRepository.findAll().stream().map(i -> entityToDto(i)).toList();
    }
    @Override
    public Optional<ArticleDto> findById(Long id) {
        return articleRepository.findById(id).stream().map(i -> entityToDto(i)).findAny();
    }
    @Override
    public Long count() {
        return articleRepository.count();
    }
    @Override
    public boolean existsById(Long id) {
        return articleRepository.existsById(id);
    }
    @Override
    public List<ArticleDto> getArticleByBoardId(Long boardId) {
        return articleRepository.getArticleByBoardId(boardId)
                .stream().map(i -> entityToDto(i))
                .toList();
    }
}
