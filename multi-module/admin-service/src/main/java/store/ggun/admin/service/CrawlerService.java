package store.ggun.admin.service;
import store.ggun.admin.domain.model.CrawlerModel;
import java.io.IOException;
import java.util.List;


public interface CrawlerService {
    List<CrawlerModel> findNews() throws IOException;
}