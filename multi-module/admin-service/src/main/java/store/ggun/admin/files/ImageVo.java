package store.ggun.admin.files;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageVo {

    private long imageId;
    String name;
    long lastModified;
    long lastModifiedDate;
    String type;
    String webkitRelativePath;
    long size;

}