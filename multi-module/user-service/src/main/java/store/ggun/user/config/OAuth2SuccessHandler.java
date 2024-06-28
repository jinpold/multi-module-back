package store.ggun.user.config;

import store.ggun.user.domain.Messenger;
import store.ggun.user.domain.UserModel;
import store.ggun.user.enums.TokenType;
import store.ggun.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import store.ggun.user.serviceImpl.PrincipalOauth2UserService;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider jwtProvider;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final UserRepository repository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String email= principalOauth2UserService.email;
        if (repository.existsByEmail(email)) {
            response.addHeader("message","아이디가 존재합니다.");
            response.sendRedirect("http://localhost:3000");
        }else {
            repository.save(UserModel.builder()
                    .email(email)
                    .role("ROLE_TEMP")
                    .build());
            UserModel user = repository.findByEmail(email);
            Cookie accessTokenCookie = new Cookie("tempToken", jwtProvider.generateToken(String.valueOf(user.getId()), TokenType.TEMP));
            accessTokenCookie.setPath("/");
            accessTokenCookie.setHttpOnly(false);
            accessTokenCookie.setSecure(false);
            response.addCookie(accessTokenCookie);
            response.addHeader("message", "SUCCESS");
            response.sendRedirect("http://localhost:3000/pages/users/join");
        }
    }
}
