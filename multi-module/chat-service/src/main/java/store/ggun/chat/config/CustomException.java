package store.ggun.chat.config;

import java.io.Serial;

public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5970845585469454688L;

    public CustomException(String type) {
        System.out.println(type + "의 예외가 발생했습니다.");
    }

}
