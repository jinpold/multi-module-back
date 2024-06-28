package store.ggun.chat.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "notices")
public class NotificationModel {
    @Id
    private Long id;
    private String roomId;
    private String msg;
    private String sender;
    private LocalDateTime createdAt;
}
