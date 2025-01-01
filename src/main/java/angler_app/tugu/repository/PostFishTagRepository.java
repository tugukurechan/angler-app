package angler_app.tugu.repository;

import angler_app.tugu.domain.PostFishTag;
import angler_app.tugu.domain.PostFishTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Post(投稿)テーブルとFishTag(魚タグ)テーブルの中間リポジトリクラス.
 * @author tugukurechan
 */
@Repository
public interface PostFishTagRepository extends JpaRepository<PostFishTag, PostFishTagId> {
    // 投稿と魚タグの関連を取得するメソッドを追加することができます
}

