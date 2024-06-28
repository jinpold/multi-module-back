package store.ggun.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.ggun.chat.domain.NotificationModel;
import store.ggun.chat.serviceImpl.NotificationService;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/chat")
public class NotificationController {
    private final NotificationService service;
//    @ResponseBody
//    @GetMapping(path = "/sse", produces = "text/event-stream")
//    Flux<String> sse() {
//        return Flux.interval(Duration.ofMillis(1000))
//                .map(i -> "VioletBeach: " + i);
//    }
//
//    @GetMapping(path = "/news", produces = "text/event-stream")
//    public Flux<ServerSentEvent<List<Article>>> news() throws IOException {
//        Resource resource = new ClassPathResource("news.json");
//        Path path = resource.getFile().toPath();
//        String json = Files.readString(path);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        News news = mapper.readValue(json, News.class);
//
//        return Flux.fromIterable(news.articles())
//                .window(3)
//                .delayElements(Duration.ofSeconds(1))
//                .flatMap(Flux::collectList)
//                .map(articles -> ServerSentEvent.<List<Article>>builder()
//                        .event("news")
//                        .data(articles)
//                        .build());
//    }
    @GetMapping(path = "/receive/{id}")
    public Flux<ServerSentEvent<NotificationModel>> receiveByRoomId(@PathVariable String id) {
        log.info("Receive request received : {}", id);
        return service.connect(id).subscribeOn(Schedulers.boundedElastic());
    }

    @PostMapping("/send")
    public Mono<Boolean> send(@RequestBody NotificationModel entity) {
        log.info("11{}",entity.toString());
        return service.save(entity).subscribeOn(Schedulers.boundedElastic());
    }
}

