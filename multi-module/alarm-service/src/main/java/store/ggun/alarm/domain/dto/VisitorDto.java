package store.ggun.alarm.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDto {
    private String date;
    private Long count;
    private String month;
    private String year;
}

