package angler_app.tugu.repository;

import angler_app.tugu.domain.FishTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * FishTagテーブルのリポジトリクラス.
 * @author tugukurechan
 */
@Repository
public interface FishTagRepository extends JpaRepository<FishTag, UUID> {
    // 魚タグ名で検索するためのカスタムメソッド（例）
    List<FishTag> findByName(String name);
}

