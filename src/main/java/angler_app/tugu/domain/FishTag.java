package angler_app.tugu.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * 魚タグを管理するドメインクラス.
 * @author tugukurechan
 */
@Entity
@Table(name = "fish_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;  // UUIDの自動生成

    @Column(nullable = false)
    private String name; // 魚名

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 作成日時

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 更新日時

    // 魚タグと投稿の多対多リレーション
    @ManyToMany(mappedBy = "fishTags")
    private Set<Post> posts;
}
