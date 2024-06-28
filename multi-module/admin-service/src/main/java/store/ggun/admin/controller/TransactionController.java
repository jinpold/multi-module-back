package store.ggun.admin.controller;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.dto.TransactionDto;
import store.ggun.admin.serviceImpl.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/transactions")
@Slf4j
public class TransactionController {

    private final TransactionServiceImpl service;

    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody TransactionDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.save(dto));

    }
    @GetMapping("/list")
    public ResponseEntity<List<TransactionDto>> findTransactionsById() throws SQLException {
        System.out.println(service.findAll());
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count()  {
        return ResponseEntity.ok(service.count());
    }

    @GetMapping("/netProfitByDate")
    public ResponseEntity<Map<String, Double>> getNetProfitByDate() {
        return ResponseEntity.ok(service.getNetProfitByDate());
    }

    @GetMapping("/TotalByDate")
    public ResponseEntity<Map<String, Double>> getTotalByDate() {
        return ResponseEntity.ok(service.getTotalByDate());
    }

    @GetMapping("/QuantityByDate")
    public ResponseEntity<Map<String, Map<String, Integer>>> getQuantityByDate() {
        return ResponseEntity.ok(service.getQuantityByDate());
    }
}
