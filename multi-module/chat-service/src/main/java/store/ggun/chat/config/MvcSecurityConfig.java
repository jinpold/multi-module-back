package store.ggun.chat.config;//package org.example.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class MvcSecurityConfig {
//    @Bean
//    SecurityFilterChain springFilterChain(HttpSecurity http) throws Exception{
//        return http
//            .authorizeHttpRequests(i -> i.anyRequest().permitAll())
//            .httpBasic(i -> i.disable())
//            .formLogin(i -> i.disable())
//            .cors(cors -> cors.disable())
//            .csrf(csrf -> csrf.disable())
//            .build();
//    }
//}
