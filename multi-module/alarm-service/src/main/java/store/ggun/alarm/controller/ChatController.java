package store.ggun.alarm.controller;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import store.ggun.alarm.domain.dto.ChatDto;
import store.ggun.alarm.domain.dto.RoomDto;
import store.ggun.alarm.exception.ChatException;
import store.ggun.alarm.domain.model.RoomModel;
import store.ggun.alarm.service.RoomService;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatController {
    private final RoomService roomService;



    @GetMapping("/checkServer")
    public Mono<String> getMethodName() {
        log.info("Check server status");
        return roomService.countConnection()
                .flatMap(count -> Mono.just("Server is running. Total connection: " + count));
    }

    @PostMapping("/save")
    public Mono<RoomModel> saveRoom(@RequestBody RoomDto dto) {
        log.info("Save room");
        return roomService.save(dto);
    }

    @GetMapping("/list")
    public Flux<RoomModel> findAllRooms() {
        log.info("Find all rooms");
        return roomService.findAll();
    }


    @GetMapping("/recieve/{roomId}")
    public Flux<ServerSentEvent<ChatDto>> subscribeByRoomId(@PathVariable String roomId) {
        log.info("subscribe chat by room id {}", roomId);
        return roomService.subscribeByRoomId(roomId)
                .switchIfEmpty(Flux.error(new ChatException("Room not found")));
    }

    @PostMapping("/send")
    public Mono<ChatDto> sendChat(@RequestBody ChatDto chatDTO) {
        log.info("Send chat {}", chatDTO.toString());
        return roomService.saveChat(chatDTO)
                .switchIfEmpty(Mono.error(new ChatException("Room not found")));
    }
}