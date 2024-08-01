package store.ggun.alarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.model.FileModel;
import store.ggun.alarm.repository.FileRepository;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public Mono<FileModel> save(FileModel fileModel) {
        return fileRepository.save(fileModel);
    }

    public Mono<FileModel> getFile(String id) {
        return fileRepository.findById(id);
    }
}
