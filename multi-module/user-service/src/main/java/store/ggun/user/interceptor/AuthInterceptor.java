package store.ggun.user.interceptor;//package com.turing.api.common.component.interceptor;
//
//import com.turing.api.security.filter.TokenProvider;
//import com.turing.api.user.repository.UserRepository;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.annotation.Nullable;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.antlr.v4.runtime.Token;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.stream.Stream;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class AuthInterceptor implements HandlerInterceptor {
//
//    private final TokenProvider jwt;
//    private final UserRepository re;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ExpiredJwtException {
//        return Stream.of(request)
//                .map(i->jwt.getToken(i))
//                .filter(i->!i.equals("undefined"))
//                .map(i->jwt.extractAllClaims(i))
////                .map(i->re.findById(i))
////                .filter(i->i.isPresent())
//                .findFirst()
//                .isPresent();
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//}
