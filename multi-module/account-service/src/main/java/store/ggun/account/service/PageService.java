package store.ggun.account.service;

import store.ggun.account.domain.dto.PageDto;
import org.springframework.stereotype.Component;

@Component
public interface PageService {
    PageDto getPageDTO(int toTalPageSize, int pageNo);

}
