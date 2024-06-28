package store.ggun.admin.controller;
import store.ggun.admin.domain.dto.ArticleDto;
import store.ggun.admin.repository.jpa.ArticleRepository;
import store.ggun.admin.serviceImpl.ArticleServiceImpl;
import store.ggun.admin.domain.model.Messenger;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*") // 기존에 origins = http://localhost:3000 이던걸 보안 걸었음. 퍼사드 패턴
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/articles")
@Slf4j
public class ArticleController {
    private final ArticleServiceImpl service;
    private final ArticleRepository repository;

    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody ArticleDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.save(dto));

    }
    @GetMapping("/list")
    public ResponseEntity<List<ArticleDto>> findByBoardId() throws SQLException {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/detail")
    public ResponseEntity<ArticleDto> findById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id).orElseGet(ArticleDto::new));
    }
    @PutMapping ("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody ArticleDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.modify(dto));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.deleteById(id));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count()  {
        return ResponseEntity.ok(service.count());
    }
    @GetMapping("/exists")
    public ResponseEntity<Messenger> existsById(@RequestParam("id") Long id){
        service.existsById(id);
        return ResponseEntity.ok(new Messenger());
    }
    @GetMapping("/myList")
    public ResponseEntity<List<ArticleDto>> getArticleByBoardId(@RequestParam("id") Long boardId) {
        return ResponseEntity.ok(service.getArticleByBoardId(boardId));
    }
}
