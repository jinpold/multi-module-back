package store.ggun.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jsoup.HttpStatusException;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    AUTHENTICATION_FAILED(401, "AUTH_001", "AUTHENTICATION_FAILED"),
    LOGIN_FAILED(401, "AUTH_002", "LOGIN_FAILED"),
    ACCESS_DENIED(401, "AUTH_003", "ACCESS_DENIED"),
    TOKEN_GENERATION_FAILED(500, "AUTH_005", "TOKEN_GENERATION_FAILED"),
    SUCCESS(200,"SUCCESS","SUCCESS"),
    VALIDATE_FAILED(400,"VALIDATE_FAILED","VALIDATE_FAILED"),
    DUPLICATE_ID(401,"DUPLICATE_ID","DUPLICATE_ID"),
    DATABASE_ERROR(500,"DATABASE_ERROR","DATABASE_ERROR"),;
    private final int status;
    private final String code;
    private final String message;
}