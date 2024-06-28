package store.ggun.user.serviceImpl;

import store.ggun.user.config.OAuthCustomOAuth2User;
import store.ggun.user.domain.UserModel;
import store.ggun.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    public String email;

    // 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("여기는 loaduser입니다 {}",userRequest.getClientRegistration().getClientName());


        String oauthClientName = userRequest.getClientRegistration().getClientName();
        try {
            log.info("여기는 http://localhost:8080/test/oauth/login/naver"+oauthClientName);
        } catch (Exception e){
            e.printStackTrace();
        }
        UserModel userEntity = null;
        String userId = null;
        String email = null;
        if (oauthClientName.equals("kakao")){
            userId = "kakao" + oAuth2User.getAttributes().get("id");
            userEntity = new UserModel(userId, "email@kakao.com");
        } else if (oauthClientName.equals("naver")) {
            Map<String,String> map = (Map<String, String>) oAuth2User.getAttributes().get("response");
            userId = "naver" + map.get("id").substring(0,14);
            email = map.get("email");
            userEntity = new UserModel(userId, email);
        }
        this.email = email;
        log.info("1111111111111"+userEntity);
        return new OAuthCustomOAuth2User(userId);
//        // 구글로그인 버튼 클릭 -> 구글로그인창 -> 로그인을 완료 -> code를 리턴(OAuth2-Client 라이브러리) -> AccessToken 요청
//        // userRequest 정보 -> 회원 프로필 받아야함(loadUser함수 호출) -> 구글로부터 회원프로필 받아준다.
//        System.out.println("getAttributes = " + oAuth2User.getAttributes());
//
//        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
//        String providerId = oAuth2User.getAttribute("sub");
//        String username = provider + "_" + providerId; // google_10021320120
////        String password = bCryptPasswordEncoder.encode("겟인데어");
//        String email = oAuth2User.getAttribute("email");
//        String role = "ROLE_USER";
//
//        UserModel userEntity = userRepository.findByUsername(username);
//
//        if (userEntity == null) {
//            System.out.println("구글 로그인이 최초입니다.");
//            userEntity = UserModel.builder()
//                    .username(username)
//                    .password(password)
//                    .email(email)
//                    .role(role)
//                    .build();
//            userRepository.save(userEntity);
//        } else {
//            System.out.println("구글 로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
//        }
//
//        // 회원 가입을 강제로 진행해볼 예정
//        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}