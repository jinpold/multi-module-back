//package store.ggun.user.controller;
//
//
//import store.ggun.user.repository.BoardRepository;
//import store.ggun.user.service.BoardService;
//import store.ggun.user.domain.Messenger;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(path="/api/boards")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public class BoardController {
//
//    private final BoardService service;
//    private final BoardRepository repository;
//
//    @PostMapping(path = "/save")
//    public ResponseEntity<Messenger> save(@RequestBody BoardDto board){
//        return ResponseEntity.ok(service.save(board));
//    }
//
//    @PostMapping(path = "/modify")
//    public ResponseEntity<Messenger> modify(@RequestBody BoardDto board){
//        return ResponseEntity.ok(service.modify(board));
//    }
//
//    @GetMapping(path = "/list")
//    public ResponseEntity<List<BoardModel>> list(){
//        return ResponseEntity.ok(repository.findAll());
//    }
//
//    @DeleteMapping(path = "/delete")
//    public ResponseEntity<Messenger> delete(@RequestParam Long id){
//        return ResponseEntity.ok(service.delete(id));
//    }
//
//}
