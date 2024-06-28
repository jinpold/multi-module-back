package store.ggun.account.serviceImpl;

import store.ggun.account.security.JwtProvider;
import store.ggun.account.domain.dto.Messenger;
import store.ggun.account.domain.model.UserModel;
import store.ggun.account.domain.dto.UserDto;
import store.ggun.account.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.ggun.account.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImple implements UserService {

    private final UserRepository repository;

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Messenger save(UserDto userDto) {
        String encodePassword = passwordEncoder.encode(userDto.getPassword());

        var user = repository.save(UserModel.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(encodePassword)
                .name(userDto.getName())
                .phone(userDto.getPhone())
                .job(userDto.getJob())
                .build());

        return Messenger.builder()
                .message(user instanceof UserModel ? "SUCCESS":"FAIURE")
                .build();
    }

    @Override
    public Messenger deleteById(Long id) {
        repository.deleteById(id);

//        return Messenger.builder()
//                .message(repository.findById(id).isPresent() ? "SUCCESS" :"FAILURE")
//                .build();

//        return Messenger.builder()
//                .message(repository.existsById(id) ? "SUCCESS" :"FAILURE")
//                .build();

        return Messenger.builder()
                .message(
                        Stream.of(id)
                                .filter(i->existsById(i))
                                .peek(i->repository.deleteById(i))
                                .map(i->"SUCCESS")
                                .findAny()
                                .orElseGet(()->"FAILURE")
                )
                .build();
    }

    @Override
    public Optional<UserDto> modify(UserDto userDto) {
        var user = repository.findById(userDto.getId());
        user.get().setName(userDto.getName());
        user.get().setPhone(userDto.getPhone());
        user.get().setJob(userDto.getJob());
        user.get().setUsername(userDto.getUsername());
        return Optional.of(repository.save(user.get())).map(i -> entityToDto(i));
    }


    @Override
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream().map(i->entityToDto(i)).toList();
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return Optional.ofNullable(
                entityToDto(Objects.requireNonNull(repository.findById(id).orElse(null))));
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public List<UserDto> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<UserDto> findUsersByJob(String job) {
        return repository.findUsersByJob(job);
    }

    @Override
    public List<UserDto> findUsersByName(String name) {
        return null;
    }

    @Override
    public Optional<UserDto> findUserByUsername(String username) {
        var user = repository.findByUsername(username);
        return Optional.of(entityToDto(user.get()));
    }


//  Srp 에 따라 아이디 존재여부를 프론트에서 먼저 판단하고 넘어옴 (시큐리티)
    @Transactional
    @Override
    public Messenger login(UserDto userDto) {
        log.info("로그인 서비스 확인 : "+userDto);
        var user = repository.findByUsername(userDto.getUsername()).get();

//        var flag = user.getPassword().equals(userDto.getPassword());
        var flag = passwordEncoder.matches(userDto.getPassword() , user.getPassword());


        var accessToken = jwtProvider.createToken(entityToDto(user));

        jwtProvider.printPayload(accessToken);

        repository.modifyTokenById(user.getId(), accessToken);

        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .accessToken(flag ? accessToken : "None")
                .build();
    }

    @Override
    public Boolean existsByUsername(String param) {
        return repository.existsByUsername(param);
    }

    @Transactional
    @Override
    public Boolean logout(String token) {
        String accessToken = token != null && token.startsWith("Bearer ") ?
                token.substring(7) : "undefined";

        Long id = jwtProvider.getpayload(accessToken).get("userId", Long.class);

        String updateToken = null;

        Boolean a = repository.existsById(id);

        repository.modifyTokenById(id,updateToken);

        log.info("결과 : {}",a);
        return a;
    }
}
