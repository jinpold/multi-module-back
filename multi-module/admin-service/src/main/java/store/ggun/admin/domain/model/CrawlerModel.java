package store.ggun.admin.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrawlerModel {
    private String imgLink;
    private String title;
    private String content;
    private String imgSrc;
}
