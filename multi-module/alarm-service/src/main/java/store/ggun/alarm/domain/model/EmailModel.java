package store.ggun.alarm.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "emails")
public class EmailModel {

    @Id
    private String id;
    private String email;
    private String subject; // 추가
    private String message;
    private List<String> recipients;
}
