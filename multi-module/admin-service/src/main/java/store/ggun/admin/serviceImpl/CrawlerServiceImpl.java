package store.ggun.admin.serviceImpl;
import store.ggun.admin.domain.model.CrawlerModel;
import store.ggun.admin.repository.etc.CrawlerRepository;
import store.ggun.admin.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CrawlerServiceImpl implements CrawlerService {
    private final CrawlerRepository crawlerRepository;

    @Override
    public List<CrawlerModel> findNews() throws IOException {
        return crawlerRepository.findNews();
    }
}