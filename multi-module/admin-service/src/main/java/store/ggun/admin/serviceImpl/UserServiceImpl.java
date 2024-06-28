package store.ggun.admin.serviceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import store.ggun.admin.domain.model.Messenger;
import store.ggun.admin.security.JwtProvider;
import store.ggun.admin.domain.model.UserModel;
import store.ggun.admin.domain.dto.UserDto;
import store.ggun.admin.repository.jpa.UserRepository;
import store.ggun.admin.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public Messenger save(UserDto userDto) {
        String investmentPropensity = getInvestmentPropensityByColor(userDto.getColor()); // MBTI에 따른 투자 성향 설정
        UserModel ent = dtoToEntity(userDto); // UserDto를 User 엔티티로 변환
        ent.setInvestmentPropensity(investmentPropensity); // 투자 성향 설정
//         //asset 컬럼이 null이면 0으로 설정
        if (ent.getAsset() == null) {
            ent.setAsset(0L);
        }

        // sex 컬럼이 null이면 age 값의 7번째 자리에 따라 설정
        if (ent.getSex() == null) {
            String age = userDto.getAge();
            if (age != null && age.length() >= 7) {
                char seventhChar = age.charAt(6);
                if (seventhChar == '1' || seventhChar == '3' || seventhChar == '5' || seventhChar == '7') {
                    ent.setSex("male");
                } else {
                    ent.setSex("female");
                }
            } else {
                ent.setSex("unknown"); // age 값이 유효하지 않으면 기본값 설정
            }
        }



        UserModel savedUserModel = userRepository.save(ent);  // User 엔티티 저장
        boolean isSuccess = savedUserModel != null && savedUserModel.getId() != null;// 저장된 User 엔티티가 null이 아닌지 확인
        return Messenger.builder()
                .message(isSuccess ? "SUCCESS" : "FAILURE")
                .build();
    }

    // MBTI에 따른 투자 성향을 반환하는 메서드
    private String getInvestmentPropensityByColor(String color) {
        switch (color) {
            case "RED":
                return "매우 공격";
            case "YELLOW":
                return "공격적";
            case "PURPLE":
                return "중립적";
            case "BLUE":
                return "보수적";
            case "GREEN":
                return "매우 보수";
            default:
                return "";
        }
    }
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(i->entityToDto(i)).toList();
    }
    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).stream().map(i -> entityToDto(i)).findAny();
    }

    @Transactional
    @Override
    public Messenger modify(UserDto userDto) {
        UserModel userModel = userRepository.findById(userDto.getId()).get();
        if (userDto.getUsername() != null && !userDto.getUsername().equals("")) {
            userModel.setUsername(userDto.getUsername());
        }
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(userDto.getPassword());
        userModel.setName(userDto.getName());
        userModel.setAge(userDto.getAge());
        userModel.setSex(userDto.getSex());
        userModel.setEmail(userDto.getEmail());
        userModel.setAddress(userDto.getAddress());
        userModel.setPhone(userDto.getPhone());
        userModel.setAsset(userDto.getAsset());
        userModel.setInvestmentPropensity(userDto.getInvestmentPropensity());
        userRepository.save(userModel);

        return userRepository.findById(userDto.getId()).get().equals(userModel) ?
                Messenger.builder().message("SUCCESS").build() :
                Messenger.builder().message("FAILURE").build();
    }

    @Override
    public Messenger deleteById(Long id) {
        userRepository.deleteById(id);
        return Messenger.builder().message
                (Stream.of(id)
                        .filter(i -> existsById(i))
                        .peek(i -> userRepository.deleteById(i))
                        .map(i -> "SUCCESS")
                        .findAny()
                        .orElseGet(()->"FAILURE")).build();
    }
    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    // SRP에 따라 아이디 존재여부를 프론트에서 먼저 판단하고, 넘어옴 (시큐리티)
    @Transactional
    @Override
    public Messenger login(UserDto dto) {
//        log.info("로그인 서비스로 들어온 파라미터 : " +dto);
//        UserModel userModel = userRepository.findUserByUsername((dto.getUsername())).get();
//        String accessToken = jwtProvider.createUserToken(entityToDto(userModel));
//
//        boolean flag = userModel.getPassword().equals(dto.getPassword());
//        log.info("accessToken 확인용: "+accessToken);
//        userRepository.modifyTokenById(userModel.getId(), accessToken);
//        // 토큰을 각 섹션 (Header, payload, signature)으로 분할
//
//        jwtProvider.printPayload(accessToken);
        return Messenger.builder()
//                .message(flag ? "SUCCESS" : "FAILURE")
//                .accessToken(flag ? accessToken : "NONE")
                .build();
    }

    @Override
    public Boolean existsByUsername(String username) {
        Integer count  = userRepository.existsByUsername(username);
        return count ==1;
    }

    @Override
    public Optional<UserModel> findUsersByRole(String role) {
        return userRepository.findUsersByRole(role);
    }

    @Override
    public Optional<UserModel> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
    @Transactional
    @Override
    public Boolean logout(String token) {
        String accessToken = token != null && token.startsWith("Bearer ") ?
                token.substring(7) : "undefined";
        Long id = jwtProvider.getPayload(accessToken).get("userId", Long.class);
        String deleteToken = "";
        userRepository.modifyTokenById(id,deleteToken);
        return userRepository.findById(id).get().getToken().equals("");
    }

    @Override
    public Optional<UserDto> findUserInfo(String accessToken) {
        String splitToken = accessToken.substring(7);
        Long id = jwtProvider.getPayload(splitToken).get("id", Long.class);

        return Optional.of(entityToDto(userRepository.findById(id).orElseThrow()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
