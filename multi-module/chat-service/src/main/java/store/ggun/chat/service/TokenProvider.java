package store.ggun.chat.service;

import store.ggun.chat.domain.UserModel;

import java.util.Map;

public interface TokenProvider {
    String generateToken(Map<String, Object> extraClaims, UserModel userModelDetails, String isRefreshToken);
    boolean isTokenValid(String token);
}