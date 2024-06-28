//package store.ggun.admin.common.component.interceptor;
//
//import store.ggun.admin.common.component.security.JwtProvider;
//import store.ggun.admin.repository.jpa.UserRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.stream.Stream;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class UserInterceptor implements HandlerInterceptor {
//
//    private final JwtProvider jwtProvider;
//    private final UserRepository userRepository;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { // request
//
//        return Stream.of(request)
//                .map(i -> jwtProvider.extractTokenFromHeader(i))
//                .filter(i -> !i.equals("undefined"))
//                .peek(i-> log.info("1- userId 인터셉터 토큰 로그 Bearer 포함: {} " , i))
//                .map(i -> jwtProvider.getPayload(i).get("userId",Long.class))
//                .map(i -> userRepository.findById(i))
//                .filter(i -> i.isPresent())
//                .peek(i-> log.info("2- userId 인터셉터 사용자 id : {} ", i))
//                .findFirst().isPresent();
//    }
//
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//
//
//}
