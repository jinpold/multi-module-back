package store.ggun.chat.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import store.ggun.chat.domain.Messenger;
import store.ggun.chat.domain.TradesHistriesModel;
import store.ggun.chat.serviceImpl.TradesHistoriesService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/trades")
public class TradesHistoriesController {
    private final TradesHistoriesService service;

    @PostMapping("/history")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Messenger> getAllUsers(@RequestBody TradesHistriesModel model) {
        return service.save(model);
    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<TradesHistriesModel> getAllHistory(@RequestBody TradesHistriesModel model) {
        return service.getAllHistory(model);
    }
}
