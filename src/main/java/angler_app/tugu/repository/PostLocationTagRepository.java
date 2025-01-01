package angler_app.tugu.repository;

import angler_app.tugu.domain.PostLocationTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Post(投稿)テーブルとLocationTag(場所タグ)テーブルの中間リポジトリクラス.
 * @author tugukurechan
 */
@Repository
public interface PostLocationTagRepository extends JpaRepository<PostLocationTag, UUID> {
    // 投稿と場所タグの関連を取得するメソッドを追加することができます
}
