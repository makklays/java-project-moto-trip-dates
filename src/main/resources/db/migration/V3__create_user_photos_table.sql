-- V3__create_user_photos_table.sql
-- Migration #3: create table user_photos

-- Creating table 'user_photos' for storing main user photos data
CREATE TABLE IF NOT EXISTS user_photos
(
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- URL файла в облачном хранилище (например, AWS S3, MinIO или локальная папка)
    photo_url      VARCHAR(255) NOT NULL,

    -- Порядок отображения (0 - главная фотка, которая идет первой, 1, 2, 3 и т.д.)
    display_order  INT NOT NULL DEFAULT 0,

    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

