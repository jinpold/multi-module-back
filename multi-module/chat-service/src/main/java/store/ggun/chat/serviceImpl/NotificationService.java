package store.ggun.chat.serviceImpl;

import lombok.RequiredArgsConstructor;
import store.ggun.chat.domain.NotificationModel;
import store.ggun.chat.repository.NotificationRepository;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository chatRepository;
    private final Map<String, Sinks.Many<ServerSentEvent<NotificationModel>>> sinks = new HashMap<>();

    public Flux<NotificationModel> receiveByRoomId(String id) {
        return null;
    }

    public Mono<Boolean> save(NotificationModel entity) {
        return Mono.just(entity)
                .flatMap(i -> {
                    i.setCreatedAt(LocalDateTime.now());
                    return Mono.just(i);
                })
                .flatMap(i ->  chatRepository.save(i))
                .doOnNext(i -> {
                    if(sinks.containsKey(i.getRoomId())){
                        sinks.get(i.getRoomId()).tryEmitNext(ServerSentEvent.builder(NotificationModel.builder()
                                .id(i.getId())
                                .roomId(i.getRoomId())
                                .msg(i.getMsg())
                                .sender(i.getSender())
                                .createdAt(i.getCreatedAt())
                                .build()).build());
                    }
                })
                .flatMap(i -> Mono.just(Boolean.TRUE))
                .switchIfEmpty(Mono.just(Boolean.FALSE));
    }

    public Flux<ServerSentEvent<NotificationModel>> connect(String roomId){
        if(sinks.containsKey(roomId))
            return sinks.get(roomId).asFlux();
        sinks.put(roomId, Sinks.many().multicast().onBackpressureBuffer());
        chatRepository.findByRoomId(roomId).subscribe(i -> {
            sinks.get(roomId).tryEmitNext(ServerSentEvent.builder(NotificationModel.builder()
                    .id(i.getId())
                    .msg(i.getMsg())
                    .sender(i.getSender())
                    .createdAt(i.getCreatedAt())
                    .build()).build());
        });
        return sinks.get(roomId).asFlux().doOnCancel(() -> {
            sinks.get(roomId).tryEmitComplete();
            sinks.remove(roomId);
        });
    }
}
