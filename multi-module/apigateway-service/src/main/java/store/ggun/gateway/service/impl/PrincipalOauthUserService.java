package store.ggun.gateway.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import store.ggun.gateway.domain.model.OAuth2UserDto;
import store.ggun.gateway.domain.model.PrincipalUserDetails;
import store.ggun.gateway.domain.vo.Registration;


@Service
@RequiredArgsConstructor
public class PrincipalOauthUserService implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final WebClient webClient;

    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        return new DefaultReactiveOAuth2UserService()
                .loadUser(userRequest)
                .log()
                .flatMap(user -> Mono.just(user.getAttributes()))
                .flatMap(attributes ->
                        Mono.just(userRequest.getClientRegistration().getClientName())
                                .log()
                                .flatMap(clientId -> Mono.just(Registration.valueOf(clientId.toUpperCase())))
                                .flatMap(registration ->
                                        Mono.just(OAuth2UserDto.of(registration, attributes))
                                                .flatMap(oauth2UserDTO ->
                                                        webClient.post()
                                                                .uri("lb://user-service/auth/oauth2/" + registration.name().toLowerCase())
                                                                .accept(MediaType.APPLICATION_JSON)
                                                                .bodyValue(oauth2UserDTO)
                                                                .retrieve()
                                                                .bodyToMono(PrincipalUserDetails.class)
                                                )
                                )
                )
                ;
    }
}
