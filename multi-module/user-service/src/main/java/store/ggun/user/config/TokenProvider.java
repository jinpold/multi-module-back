package store.ggun.user.config;

import store.ggun.user.enums.TokenType;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.SecretKey;

@Slf4j
@Component
public class TokenProvider {

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }


    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    // JWT 생성
    public String generateToken(String authentication, TokenType tokenType) {
        // 인증 정보에서 사용자 이름 추출
        return Jwts.builder()
                .issuer("soo")
                .subject(authentication)
                .audience().add(authentication).and()
                .expiration(Date.from((tokenType.equals(TokenType.ACCESS))? Instant.now().plus(10, ChronoUnit.MINUTES):Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(this.getSigningKey())
                .compact();
    }
    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(this.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractUsername(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    @SuppressWarnings("deprecation")
    public String validateToken(String token) {
        String claims = null;
        try {
            claims = extractAllClaims(token).getSubject();
            log.info("토큰프로바이더 : {}",claims);
            if(claims != null) return claims;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return claims;
    }
    public String getToken (HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        log.info("bearerToken찾는곳: {}",bearerToken);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
        {
            log.info(bearerToken.substring(7));
            return bearerToken.substring(7);
        }
        return null;
    }
}
