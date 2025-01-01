package angler_app.tugu.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * 投稿を管理するドメインクラス.
 * @author tugukurechan
 */
@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;  // UUIDの自動生成

    @Column(nullable = false)
    private String title; // タイトル

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 本文

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 作成日時

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 更新日時

    // 投稿と魚タグの多対多リレーション
    @ManyToMany
    @JoinTable(
            name = "post_fish_tags",
            joinColumns = @JoinColumn(name = "post_uuid"),
            inverseJoinColumns = @JoinColumn(name = "fish_tag_uuid")
    )
    private Set<FishTag> fishTags;

    // 投稿と場所タグの1対多リレーション
    @OneToOne
    @JoinColumn(name = "location_tag_uuid")
    private LocationTag locationTag;
}
