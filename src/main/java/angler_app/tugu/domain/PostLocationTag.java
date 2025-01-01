package angler_app.tugu.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 投稿と場所タグを管理する中間テーブルクラス.
 * @author tugukurechan
 */
@Entity
@Table(name = "post_location_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLocationTag {
    // 投稿と場所タグは1対1
    @Id
    @ManyToOne
    @JoinColumn(name = "post_uuid")
    private Post post;  // 投稿のUUID

    @ManyToOne
    @JoinColumn(name = "location_tag_uuid")
    private LocationTag locationTag;  // 場所タグのUUID
}

