package store.ggun.admin.security;
import store.ggun.admin.domain.dto.AdminDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Log4j2
@Component
public class JwtProvider {
    Instant expiredDate = Instant.now().plus(1, ChronoUnit.DAYS);

    @Value("${jwt.iss}")
    private String issuer;
    private final SecretKey secretKey;

//    public enum TokenType{REFRESH,ACCESS}
//
//    public TokenVo makeToken(UserDto userDto){
//        return TokenVo.builder()
//                .email(userDto.getUsername())
//                .role(userDto.getJob())
//                .refreshToken(createToken(userDto,Arrays.asList(userDto.getJob()),TokenType.REFRESH))
//                .accessToken(createToken(userDto, Arrays.asList(userDto.getJob()),TokenType.ACCESS))
//                .build();
//    }

    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String createToken(AdminDto adminDto) {

        //Access Token
        String accessToken = Jwts.builder()
                .issuer(issuer)
                .signWith(secretKey)
//                .expiration(Date.from((tokenType.equals(TokenType.ACCESS))?Instant.now().plus(1, ChronoUnit.DAYS):Instant.now().plus(1, ChronoUnit.MINUTES)))
                .expiration(Date.from(expiredDate))
                .claim("sub", "turing")
                .claim("username", adminDto.getUsername())
                .claim("role", adminDto.getRole())  // 관리자(ad), 소비자 (role)
                .claim("adminId", adminDto.getId())
                .compact();

        log.info("로그인 성공으로 발급된 토큰 : " + accessToken);
        return accessToken;

    }
//
//    public String createUserToken(UserDto userDtDo){
//
//        String accessToken = Jwts.builder()
//                .issuer(issuer)
//                .signWith(secretKey)
//                .expiration(Date.from(expiredDate))
//                .claim("sub", "turing")
//                .claim("username", userDtDo.getUsername())
//                .claim("userId", userDtDo.getId())
//                .compact();
//
//        log.info("로그인 성공으로 발급된 토큰 : " + accessToken);
//        return accessToken;
//
//    }


    public String extractTokenFromHeader(HttpServletRequest request) {
        log.info("프론트에서 넘어온 리퀘스트 값 : {}", request.getServletPath());
        String bearerToken = request.getHeader("Authorization");
        log.info("프론트에서 넘어온 토큰 값 : {}", bearerToken);
        return bearerToken != null && bearerToken.startsWith("Bearer ") ?
                bearerToken.substring(7): "undefined";

    }
    // 토큰 발급이 잘됐는지 확인하기 위한 코드 (void로 바꾸고 리턴 없앰)
    public void printPayload(String accessToken) {
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        log.info("accessToken Header :" +header);
        log.info("accessToken payload :" +payload);
    }
    // getPayload = Claims의 집합 => secretKey 때문에 Jwt프로바이더에서 로직을 만들었음. (원래 인터셉터)
    public Claims getPayload(String token){
        try {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build().parseSignedClaims(token).getPayload();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to parse JWT token", exception);
        }
    }
}
