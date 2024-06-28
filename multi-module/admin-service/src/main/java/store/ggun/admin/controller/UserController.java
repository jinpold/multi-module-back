package store.ggun.admin.controller;

import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.model.UserModel;
import store.ggun.admin.domain.dto.UserDto;
import store.ggun.admin.repository.jpa.UserRepository;
import store.ggun.admin.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController("userController")
@RequiredArgsConstructor
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")})
@RequestMapping(path = "/users")
@Slf4j
public class UserController {
    private final UserService service;
    private final UserRepository userRepository;

    // ---------------------------------Command---------------------------------------
    @SuppressWarnings("static-access")
    @PostMapping( "/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.save(dto));

    }
    @PutMapping ("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody UserDto dto) {
        log.info("입력받은 정보 : {}", dto );
        return ResponseEntity.ok(service.modify(dto));
    }
    // -----------------------------------Query ---------------------------------------

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> findAll() throws SQLException {
        log.info("입력받은 정보 : {}");
        System.out.println(service.findAll());
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/detail")
    public ResponseEntity<UserDto> findById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.findById(id).orElseGet(UserDto::new));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam("id") Long id) {
        log.info("입력받은 정보 : {}", id );
        return ResponseEntity.ok(service.deleteById(id));
    }
    @GetMapping("/exists-id")
    public ResponseEntity<UserDto> existsById(@RequestParam("id") Long id){
        service.existsById(0L);
        return ResponseEntity.ok(service.findById(id).orElseGet(UserDto::new));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> count()  {
        return ResponseEntity.ok(service.count());
    }
    @PostMapping("/search-name")
    public ResponseEntity<Optional<UserModel>> findUsersByName(@RequestBody UserDto param) {
        //log.info("입력받은 정보 : {}", name );
        return ResponseEntity.ok(service.findUserByUsername(param.getName()));
    }
    @GetMapping("/search-job")
    public ResponseEntity<Messenger> findUsersByRole(@RequestParam("Role") String role) {
        service.findUsersByRole(role);
        return ResponseEntity.ok(new Messenger());
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String accessToken){
        log.info("logout request : {}", accessToken);
        var flag = service.logout(accessToken); // 토큰이 없으면 false 있으면 true
        return ResponseEntity.ok(flag);
    }
    @GetMapping("/search")
    public ResponseEntity<Optional<UserDto>> findUserInfo(@RequestHeader("Authorization") String accessToken) {
        log.info("입력받은 정보 : {}", accessToken );
        return ResponseEntity.ok(service.findUserInfo(accessToken));
    }
}