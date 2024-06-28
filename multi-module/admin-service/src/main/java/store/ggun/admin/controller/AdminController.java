package store.ggun.admin.controller;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.AdminModel;
import store.ggun.admin.domain.dto.AdminDto;
import store.ggun.admin.repository.jpa.AdminRepository;
import store.ggun.admin.service.AdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.*;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController("adminController")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping
@Slf4j
public class AdminController {
    private final AdminService service;
    private final AdminRepository adminRepository;

    // ---------------------------------Command---------------------------------------
    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody AdminDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.save(dto));

    }
    @PatchMapping ("/modify") // 회원 정보변경
    public ResponseEntity<Messenger> modify(@RequestBody AdminDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.modify(dto));
    }
    @PatchMapping ("/modifyRole") // 회원 정보변경
    public ResponseEntity<Messenger> modifyRole(@RequestBody AdminDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.modify(dto));
    }
    @PatchMapping("/update") // 비밀번호 초기화
    public ResponseEntity<Messenger> update(@RequestBody AdminDto dto) {
        log.info("입력받은 정보 : {}", dto);
        return ResponseEntity.ok(service.update(dto));
    }
    // -----------------------------------Query ---------------------------------------

    @GetMapping("/list")
    public ResponseEntity<List<AdminDto>> findAll() throws SQLException {
        log.info("입력받은 정보 : {}");
        System.out.println(service.findAll());
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/detail")
    public ResponseEntity<AdminDto> findById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id).orElseGet(AdminDto::new));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.deleteById(id));
    }
    @GetMapping("/exists-id")
    public ResponseEntity<AdminDto> existsById(@RequestParam("id") Long id){
        service.existsById(0L);
        return ResponseEntity.ok(service.findById(id).orElseGet(AdminDto::new));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count()  {
        return ResponseEntity.ok(service.count());
    }
    @PostMapping("/search-enpName")
    public ResponseEntity<Optional<AdminModel>> findUsersByName(@RequestBody AdminDto param) {
        //log.info("입력받은 정보 : {}", name );
        return ResponseEntity.ok(service.findAdminByUsername(param.getEnpName()));
    }
    @GetMapping("/search-role")
    public ResponseEntity<Messenger> findUsersByRole(@RequestParam("Role") String role) {
        service.findAdminByRole(role);
        return ResponseEntity.ok(new Messenger());
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String accessToken){
        log.info("logout request : {}", accessToken);
        var flag = service.logout(accessToken); // 토큰이 없으면 false 있으면 true
        return ResponseEntity.ok(flag);
    }
    @GetMapping("/search")
    public ResponseEntity<Optional<AdminDto>> findUserInfo(@RequestHeader("Authorization") String accessToken) {
        log.info("입력받은 정보 : {}", accessToken );
        return ResponseEntity.ok(service.findUserInfo(accessToken));
    }
}