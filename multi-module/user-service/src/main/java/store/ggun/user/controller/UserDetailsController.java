package store.ggun.user.controller;//package com.turing.api.security.controller;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//@Log
//@Component
//@RequiredArgsConstructor
//public class UserDetailsController implements AuthenticationProvider {
//
//    private final UserDetailsServiceImpl detailsServiceImpl;
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.expiration}")
//    private long validityInMs;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return false;
//    }
//}
