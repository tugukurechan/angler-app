package angler_app.tugu.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * 場所タグを管理するドメインクラス.
 * @author tugukurechan
 */
@Entity
@Table(name = "location_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;  // UUIDの自動生成

    @Column(nullable = false)
    private String name; // 場所名

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;  // 作成日時

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 更新日時

    // 場所タグと投稿の1対多リレーション
    @OneToMany(mappedBy = "locationTag")
    private Set<Post> posts;
}

