package store.ggun.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.ggun.user.domain.ArticleDto;
import store.ggun.user.domain.ArticleModel;
import store.ggun.user.domain.Messenger;
import store.ggun.user.repository.ArticleRepository;
import store.ggun.user.service.ArticleService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/articles")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArticleController {

    private final ArticleService service;
    private final ArticleRepository repository;

    @GetMapping(path = "/list")
    public ResponseEntity<ArticleModel> list(@RequestParam Long id){
        return ResponseEntity.ok(repository.findById(id).orElse(null));
    }

    @PostMapping(path = "/save")
    public ResponseEntity<ArticleModel> save(@RequestBody ArticleDto model){
        return ResponseEntity.ok(service.save(model));
    }
}
