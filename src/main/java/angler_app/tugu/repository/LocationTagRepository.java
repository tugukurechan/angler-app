package angler_app.tugu.repository;

import angler_app.tugu.domain.LocationTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Locationテーブルのリポジトリクラス.
 * @author tugukurechan
 */
@Repository
public interface LocationTagRepository extends JpaRepository<LocationTag, UUID> {
    // 場所タグ名で検索するためのカスタムメソッド（例）
    List<LocationTag> findByName(String name);
}

