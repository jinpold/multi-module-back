package store.ggun.alarm.service;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.dto.ChatDto;
import store.ggun.alarm.domain.dto.RoomDto;
import store.ggun.alarm.domain.model.ChatModel;
import store.ggun.alarm.domain.model.RoomModel;


@Service

public interface RoomService extends CommandService<RoomModel, RoomDto>, QueryService<RoomModel, RoomDto> {
    Mono<ChatDto> saveChat(ChatDto chatDTO);
    Mono<ChatModel> findChatById(String id);
    Flux<ChatModel> findChatsByRoomId(String roomId);
    Flux<ServerSentEvent<ChatDto>> subscribeByRoomId(String roomId);
    Mono<Integer> countConnection();
}