package store.ggun.admin.serviceImpl;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.domain.dto.AdminDto;
import store.ggun.admin.repository.jpa.AdminRepository;
import store.ggun.admin.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AdminRepository adminRepository;


    @Override
    public Messenger login(AdminDto adminDto) {

        boolean flag = adminRepository.findAdminByUsername
                (adminDto.getUsername()).get().getPassword().equals(adminDto.getPassword());

        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .accessToken(flag ? createToken(adminDto) : "NONE")
                .build();
    }


    @Override
    public String createToken(AdminDto adminDto) {
        Claims claims = (Claims) Jwts.claims();
        claims.put("username", adminDto.getUsername());
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tokenValidTime = now.plusSeconds(24*60*60*1000);



        String token = Jwts.builder()
                .claims()
                .add("iss", "turing.co.kr")
                .add("sub", "turing")
                .add("exp",tokenValidTime)
                .add("adminId", adminDto.getId())
                .add("username", adminDto.getUsername())
                .add("role", "admin") // 관리자(ad)
                .and()
                .compact();
        log.info("로그인 성공으로 발급된 토큰 : " + token);
        return token;
    }

}
