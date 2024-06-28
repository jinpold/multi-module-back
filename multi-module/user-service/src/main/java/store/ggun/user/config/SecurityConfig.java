package store.ggun.user.config;

import store.ggun.user.filter.CustomRequestFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import lombok.RequiredArgsConstructor;

@Configurable
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됨
public class SecurityConfig {
    private final CustomRequestFilter customRequestFilter;
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
    AuthenticationManager manager = managerBuilder.build();
    http.authenticationManager(manager);
        http
                // rest api 설정
//                .csrf(AbstractHttpConfigurer::disable) // csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
//                .cors(AbstractHttpConfigurer::disable) // cors 비활성화 -> 프론트와 연결 시 따로 설정 필요
//                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
//                .formLogin(AbstractHttpConfigurer::disable) // 기본 login form 비활성화
//                .logout(AbstractHttpConfigurer::disable) // 기본 logout 비활성화
//                .headers(c -> c.frameOptions(
//                        FrameOptionsConfig::disable).disable()) // X-Frame-Options 비활성화
//                .sessionManagement(c ->
//                        c.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용하지 않음
                // request 인증, 인가 설정
//                .authorizeHttpRequests(request ->
//                        request
//                                .requestMatchers("/swagger-ui/**").permitAll()
//                                .requestMatchers(
//                                        new AntPathRequestMatcher("/"),
//                                        new AntPathRequestMatcher("/auth/success")).permitAll().anyRequest().authenticated()
//                )
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeRequest ->
                    authorizeRequest
                            .requestMatchers("api/boards/save", "api/boards/delete").hasRole("SUPER_ADMIN")
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/users/**").hasRole("USER")
                            .requestMatchers("/api/boards/**").hasRole("USER")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers(
                                    AntPathRequestMatcher.antMatcher("/**"),
                                    AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                                    AntPathRequestMatcher.antMatcher("/swagger-resources/**")
                            ).authenticated()


            )
            .oauth2Login(form ->
                form
//                        .loginPage("http://localhost:3000")
//                        .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/auth/oauthLogin"))//필요한가??
                        .redirectionEndpoint(endpoint->endpoint.baseUri("/"))//필요한가?? 얘가 있어야 값이 날아옴
                        .userInfoEndpoint(endpoint -> endpoint.userService(oAuth2UserService))
                        .successHandler(oAuth2SuccessHandler)
            )
            .formLogin(form->{
                form
                        .loginPage("http://localhost:3000")
                        .loginProcessingUrl("http://localhost:3000")
                        .defaultSuccessUrl("http://localhost:3000")
                        .successForwardUrl("http://localhost:3000")
                ;
            })
            .logout(logout -> logout
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
                .addFilterBefore(customRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    { return authenticationConfiguration.getAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder()
    { return new BCryptPasswordEncoder(); }

}