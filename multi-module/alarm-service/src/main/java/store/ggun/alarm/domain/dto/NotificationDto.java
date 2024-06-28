package store.ggun.alarm.domain.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NotificationDto {
    private String id;
    private String message;
    private String userId; // 임직원 사용자
    private String response;
    private String adminId; // 관리자
    private String status;
}
