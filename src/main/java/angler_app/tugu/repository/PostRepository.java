package angler_app.tugu.repository;

import angler_app.tugu.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

/**
 * Postテーブルのリポジトリクラス.
 * @author tugukurechan
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    // 投稿タイトルで検索するためのカスタムメソッド（例）
    Optional<Post> findByTitle(String title);

    // タイトルの部分一致で検索するためのメソッド
    List<Post> findByTitleContaining(String title);
}
