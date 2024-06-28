package store.ggun.user.config;//package com.turing.api.common.config;
//
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import com.turing.api.common.component.interceptor.AuthInterceptor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@EnableWebSecurity
//@Configuration
//@RequiredArgsConstructor
//public class WebMvcConfig implements WebMvcConfigurer {

//    private final AuthInterceptor authInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor)
//                .excludePathPatterns("/api/auth/**")
//                .excludePathPatterns("/api/users/save")
//                .addPathPatterns("/api/**");
////        WebMvcConfigurer.super.addInterceptors(registry);
//    }
////    @Bean
////    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable);
////
////        return http.build();
////    }
//}
