package store.ggun.admin.filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    private Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
//        String userName = ((User) auth.getPrincipal()).getUsername();
//        UserDto userDetails = userService.getUserDetailsByEmail(userName);
//        byte[] secretKeyBytes = Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes());
//        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyBytes);
//        Instant now = Instant.now();
//        String token = Jwts.builder()
//                .subject(userDetails.getUserId())
//                .expiration(Date.from(now.plusMillis(Long.parseLong(environment.getProperty("token.expiration_time")))))
//                .issuedAt(Date.from(now))
//                .signWith(secretKey)
//                .compact();
//        res.addHeader("token", token);
//        res.addHeader("userId", userDetails.getUserId());
    }
}
