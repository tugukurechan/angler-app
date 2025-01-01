package angler_app.tugu.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * 投稿と魚タグを管理する中間ーブルクラスPostFishTagRepositoryで使用される
 * 複合キーのドメインクラス.
 * Post(投稿)のUUIDとFishTag(FishTag)のUUIDを複合キーとして管理している
 *
 * @author tugukurechan
 */
@Setter
@Getter
public class PostFishTagId implements Serializable {
    // getter と setter
    private UUID postUuid;    // 投稿のUUID
    private UUID fishTagUuid; // 魚タグのUUID

    // デフォルトコンストラクタ
    public PostFishTagId() {}

    // コンストラクタ
    public PostFishTagId(UUID postUuid, UUID fishTagUuid) {
        this.postUuid = postUuid;
        this.fishTagUuid = fishTagUuid;
    }

    // equals と hashCode をオーバーライド
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostFishTagId that = (PostFishTagId) o;

        if (!postUuid.equals(that.postUuid)) return false;
        return fishTagUuid.equals(that.fishTagUuid);
    }

    @Override
    public int hashCode() {
        int result = postUuid.hashCode();
        result = 31 * result + fishTagUuid.hashCode();
        return result;
    }
}

