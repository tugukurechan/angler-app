package angler_app.tugu.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * 投稿と魚タグを管理する中間テーブルクラス.
 * @author tugukurechan
 */
@Entity
@Table(name = "post_fish_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostFishTag {
    // 投稿1つに対して魚タグ多
    @Id
    @ManyToOne
    @JoinColumn(name = "post_uuid")
    private Post post;  // 投稿のUUID

    @Id
    @ManyToOne
    @JoinColumn(name = "fish_tag_uuid")
    private FishTag fishTag;  // 魚タグのUUID
}

