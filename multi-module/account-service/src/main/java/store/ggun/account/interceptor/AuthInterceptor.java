//package store.ggun.account.interceptor;
//
//import store.ggun.account.security.JwtProvider;
//import store.ggun.account.repository.UserRepository;
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
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class AuthInterceptor implements HandlerInterceptor {
//
//    private final JwtProvider jwtProvider;
//    private final UserRepository repository;
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response,
//                             Object handler) throws Exception {
////        String token = jwtProvider.extractTokenFromHeader(request);
////        log.info("1- 인터셉터 토큰 로그 Bearer 포함 : {}", token);
////
////        if (token.equals("undefined")) {
////            response.sendError(HttpServletResponse.SC_ACCEPTED);
////            return false;
////        }
////            Long id = jwtProvider.getpayload(token).get("userId", Long.class);
////
////            log.info("2- 인터셉터 사용자 ID : {}", id);
////
////            Optional<User> user = repository.findById(id);
////            log.info("3- 인터셉터 사용자 : {}", user);
////            if (user.isEmpty()) {
////                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
////                return false;
////            }
////            log.info("4- 인터셉터 최종여부 : {}", true);
//
////            Boolean b = Stream.of(request)
////                    .map(i->jwtProvider.extractTokenFromHeader(i))
////                    .filter(i->!i.equals("undefined"))
////                    .map(i->jwtProvider.getpayload(i).get("userId", Long.class))
////                    .map(i->repository.existsById(i))
////                    .findFirst()
////                    .isPresent();
////            return CorsUtils.isPreFlightRequest(request) ? true:
//                   return Stream.of(request)
//                    .map(i->jwtProvider.extractTokenFromHeader(i))
//                    .filter(i->!i.equals("undefined"))
//                    .peek(i->log.info("1- 인터셉터 토큰 확인 : {}",i))
//                    .map(i->jwtProvider.getpayload(i).get("userId", Long.class))
//                    .map(i->repository.findById(i))
//                    .peek(i->log.info("2- 인터셉터 user 확인 : {}",i))
//                    .anyMatch(i->i.isPresent());
//        }
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
//}