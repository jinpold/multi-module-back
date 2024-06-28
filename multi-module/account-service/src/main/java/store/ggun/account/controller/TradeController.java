package store.ggun.account.controller;

import store.ggun.account.domain.dto.TradeDto;
import store.ggun.account.service.TradeService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ApiResponses({
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "200", description = "SUCCESS"),
})
@RequiredArgsConstructor
@RequestMapping("/trades")
@CrossOrigin(origins = "*",allowedHeaders = "*")
@Slf4j
public class TradeController {
    private final TradeService service;

    @GetMapping("/detail")
    public ResponseEntity<Optional<TradeDto>> findById(@RequestParam Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TradeDto>> findByProductNo(@RequestParam("prdtName") String prdtName){
        log.info("입력받은 정보 : {}",prdtName);
        log.info("리스폰스 정보 : {}",service.findByProductNo(prdtName));
        return ResponseEntity.ok(service.findByProductNo(prdtName));
    }

    @GetMapping("/list")
    public ResponseEntity<List<TradeDto>> findAll(@RequestParam Long id){
        log.info("입력받은 정보 : {}",id);
        return ResponseEntity.ok(service.findByAcno(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok( service.count());
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsById(@RequestParam Long id){
        return ResponseEntity.ok(service.existsById(id));
    }


}
