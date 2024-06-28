package store.ggun.admin.article;
import store.ggun.admin.domain.model.ArticleModel;
import store.ggun.admin.domain.dto.ArticleDto;
import store.ggun.admin.repository.jpa.ArticleRepository;
import store.ggun.admin.service.ArticleService;
import store.ggun.admin.serviceImpl.ArticleServiceImpl;
import store.ggun.admin.repository.jpa.BoardRepository;
import store.ggun.admin.repository.jpa.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations = "classpath:junit-platform.yml")
public class ArticleModelServiceImplTest {

    private ArticleService service;
    private static ArticleModel testArticleModel;
    @Mock
    private ArticleRepository repository;
    @Mock
    private AdminRepository adminRepository;
    @Mock
    private BoardRepository boardRepository;
    @BeforeEach
    void setup() {
        this.service = new ArticleServiceImpl(repository, boardRepository, adminRepository);
    }

    @BeforeEach
    public void init(){
        testArticleModel = ArticleModel.of("테스트제목", "테스트글");
    }
    @Test
    public void 게시글_제목_검색(){
        //Given
        repository.save(testArticleModel);

        // When
        ArticleModel articleModel = repository.findById(1L).get();

        // Then
        assertThat(articleModel.getTitle()).isEqualTo("테스트제목");
    }
    @Test
    public void 게시글_전체_검색() throws SQLException {
        List<ArticleModel> articleModels = getList();
        BDDMockito.given(repository.findAll()).willReturn(articleModels);
        List<ArticleDto> list = service.findAll();
        assertThat(list.size()).isEqualTo(3);
    }

    private List<ArticleModel> getList() {
        return Arrays.asList(
                ArticleModel.builder().id(1l).title("유관순").content("유관순은 3.1운동의 주역이였다").build(),
                ArticleModel.builder().id(2l).title("김구").content("김구는 임시정부의 주역이였다").build(),
                ArticleModel.builder().id(3l).title("윤봉길").content("윤봉길은 독립운동가이다").build()
        );
    }
}
