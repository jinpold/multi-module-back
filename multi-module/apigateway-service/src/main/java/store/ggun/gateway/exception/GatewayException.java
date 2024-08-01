package store.ggun.gateway.exception;

import lombok.Getter;
import store.ggun.gateway.domain.vo.ExceptionStatus;

@Getter
public class GatewayException extends RuntimeException {
    private final ExceptionStatus status;

    public GatewayException(ExceptionStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public GatewayException(ExceptionStatus status, String message) {
        super(status.getMessage() + " : " + message);
        this.status = status;
    }

}