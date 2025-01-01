-- pgcrypto拡張が存在しない場合は作成
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- 投稿テーブル
DROP TABLE IF EXISTS posts CASCADE;
CREATE TABLE posts (
  uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY, -- UUIDの自動生成
  title VARCHAR(255) NOT NULL, -- タイトル
  content TEXT NOT NULL, -- 本文
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 更新日時（最初は自動設定）
);

-- 魚のタグ
DROP TABLE IF EXISTS fish_tags CASCADE;
CREATE TABLE fish_tags (
  uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY, -- UUIDの自動生成
  name VARCHAR(255) NOT NULL, -- 魚名
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 更新日時
);

-- 場所のタグ
DROP TABLE IF EXISTS location_tags CASCADE;
CREATE TABLE location_tags (
  uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY, -- UUIDの自動生成
  name VARCHAR(255) NOT NULL, -- 場所名
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 作成日時
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 更新日時
);

-- 投稿と魚タグは多対多
DROP TABLE IF EXISTS post_fish_tags CASCADE;
CREATE TABLE post_fish_tags (
  post_uuid UUID, -- 投稿のUUID
  fish_tag_uuid UUID, -- 魚タグのUUID
  PRIMARY KEY(post_uuid, fish_tag_uuid), -- 複合主キー
  FOREIGN KEY(post_uuid) REFERENCES posts(uuid) ON DELETE CASCADE, -- 投稿とのリレーション（投稿削除時に関連タグも削除）
  FOREIGN KEY(fish_tag_uuid) REFERENCES fish_tags(uuid) ON DELETE CASCADE -- 魚タグとのリレーション（魚タグ削除時に関連投稿も削除）
);

-- 投稿と場所タグは1対多
DROP TABLE IF EXISTS post_location_tag CASCADE;
CREATE TABLE post_location_tag (
  post_uuid UUID PRIMARY KEY, -- 投稿のUUID
  location_tag_uuid UUID, -- 場所タグのUUID
  FOREIGN KEY(post_uuid) REFERENCES posts(uuid) ON DELETE CASCADE, -- 投稿とのリレーション（投稿削除時に関連タグも削除）
  FOREIGN KEY(location_tag_uuid) REFERENCES location_tags(uuid) ON DELETE CASCADE -- 場所タグとのリレーション（場所タグ削除時に関連投稿も削除）
);

-- トリガー関数を作成
CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  -- UPDATED_AT フィールドを現在の時刻に更新
  NEW.updated_at = CURRENT_TIMESTAMP;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- posts テーブルに対して UPDATE の前にトリガーを実行するように設定
CREATE TRIGGER set_updated_at
BEFORE UPDATE ON posts
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
-- fish_tags テーブルに対して UPDATE の前にトリガーを実行するように設定
CREATE TRIGGER set_updated_at_fish_tags
BEFORE UPDATE ON fish_tags
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();
-- location_tags テーブルに対して UPDATE の前にトリガーを実行するように設定
CREATE TRIGGER set_updated_at_location_tags
BEFORE UPDATE ON location_tags
FOR EACH ROW
EXECUTE FUNCTION update_timestamp();


--====== データ挿入 ======
INSERT INTO fish_tags (name) VALUES
  ('イワシ'),
  ('サバ'),
  ('アジ');

INSERT INTO location_tags (name) VALUES
  ('東京湾'),
  ('相模湾'),
  ('湘南');

INSERT INTO posts (title, content) VALUES
  ('東京湾での釣果', '東京湾でイワシとアジを釣りました。天気が良く、釣り日和でした。'),
  ('相模湾での釣り', '相模湾でサバを釣りました。大きなサバで楽しめました。');

-- 投稿と魚タグのリレーション挿入（多対多）
-- 投稿「東京湾での釣果」に「イワシ」と「アジ」をタグ付け
INSERT INTO post_fish_tags (post_uuid, fish_tag_uuid)
  SELECT p.uuid, f.uuid
  FROM posts p
  JOIN fish_tags f ON f.name IN ('イワシ', 'アジ')
  WHERE p.title = '東京湾での釣果';
-- 投稿「相模湾での釣り」に「サバ」をタグ付け
INSERT INTO post_fish_tags (post_uuid, fish_tag_uuid)
  SELECT p.uuid, f.uuid
  FROM posts p
  JOIN fish_tags f ON f.name = 'サバ'
  WHERE p.title = '相模湾での釣り';

-- 投稿と場所タグのリレーション挿入（1対多）
-- 投稿「東京湾での釣果」に「東京湾」を場所タグとして関連付け
INSERT INTO post_location_tag (post_uuid, location_tag_uuid)
  SELECT p.uuid, l.uuid
  FROM posts p
  JOIN location_tags l ON l.name = '東京湾'
  WHERE p.title = '東京湾での釣果';
-- 投稿「相模湾での釣り」に「相模湾」を場所タグとして関連付け
INSERT INTO post_location_tag (post_uuid, location_tag_uuid)
  SELECT p.uuid, l.uuid
  FROM posts p
  JOIN location_tags l ON l.name = '相模湾'
  WHERE p.title = '相模湾での釣り';