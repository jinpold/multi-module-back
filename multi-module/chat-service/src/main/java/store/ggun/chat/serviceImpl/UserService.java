package store.ggun.chat.serviceImpl;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import store.ggun.chat.domain.Messenger;
import store.ggun.chat.service.TokenProvider;
import store.ggun.chat.domain.UserModel;
import store.ggun.chat.domain.UserDto;
import store.ggun.chat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final TokenProvider jwtProvider;
    private final TokenServiceImpl tokenService;
    @Value("${jwt.expiration.access}")
    private long accessTokenExpiration;
    @Value("${jwt.expiration.refresh}")
    private long refreshTokenExpiration;

    private final UserRepository userRepository;

    public Flux<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<UserModel> getUserDetailByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Mono<UserModel> getUserDetailById(Long id) {
        return userRepository.findById(id);
    }

    public Mono<Messenger> addUser(UserModel userModel) {
        return userRepository.save(userModel).flatMap(i -> Mono.just(Messenger.builder().message("SUCCESS").build()))
                .switchIfEmpty(Mono.just(Messenger.builder().message("FAILURE").build()))
                ;
    }

    public Mono<UserModel> updateUser(Long id, UserModel userModel) {
        return userRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalUser -> {
                    if (optionalUser.isPresent()) {
                        userModel.setUserId(id);
                        return userRepository.save(userModel);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    public Mono<Void> deleteAllUsers() {
        return userRepository.deleteAll();
    }

    public Flux<UserModel> findByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    public Mono<Messenger> login(UserModel userModel) {
        log.info("로그인에 사용되는 이메일 : {}", userModel.getEmail());

        var accessToken = jwtProvider.generateToken(null, userModel, "accessToken");
        if(accessToken.equals("")){
            log.info("접속토큰 발급 실패");
        }

        var refreshToken = jwtProvider.generateToken(null, userModel, "refreshToken");
        if(refreshToken.equals("")){
            log.info("리프레시 토큰 발급 실패");
        }

        log.info("로그인 성공시 접속토큰  : {}", accessToken);
        log.info("로그인 성공시 재생토큰  : {}", refreshToken);
        log.info("로그인 성공시 재생토큰  : {}", accessTokenExpiration);
        log.info("로그인 성공시 재생토큰  : {}", refreshTokenExpiration);

        tokenService.saveRefrshToken(userModel.getEmail(), refreshToken, refreshTokenExpiration);

        // Sync
        return userRepository.findByEmail(userModel.getEmail())
                .filter(i -> i.getPassword().equals(userModel.getPassword()))
                .map(i -> UserDto.builder().email(i.getEmail()).firstName(i.getFirstName()).lastName(i.getLastName()).build())
                .log()
                .map(i -> Messenger.builder().message("SUCCESS").data(i)
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessTokenExpire(accessTokenExpiration)
                        .refreshTokenExpire(refreshTokenExpiration)
                        .build())

                ;
    }
    public Mono<Messenger> login2(UserModel userModel) {
        log.info("로그인 2 에 사용되는 이메일 : {}", userModel.getEmail());
        // Async
        // attach 방식으로 사용
        return userRepository.findByEmail(userModel.getEmail())
                .filter(i -> i.getPassword().equals(userModel.getEmail()))
                .flatMap(i -> Mono.just(UserDto.builder().email(i.getEmail()).firstName(i.getFirstName()).lastName(i.getLastName()).build()))
                .log()
                .flatMap(i -> Mono.just(Messenger.builder().data(i).build()))

                ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDto getUserDetailsByEmail(String userName) {
        return null;
    }
}