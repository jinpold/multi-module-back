package store.ggun.alarm.domain.model;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
