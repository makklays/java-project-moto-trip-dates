-- V6__create_dating_tables.sql
-- Migration #6: create table for dating and matching system

-- Таблица для лайков и мэтчей
CREATE TABLE IF NOT EXISTS matches
(
    id              BIGSERIAL PRIMARY KEY,
    sender_id       BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,   -- Кто лайкнул
    receiver_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,   -- Кого лайкнули

    -- Статус: LIKED (просто лайк), MATCHED (взаимно, можно открывать чат), DISLIKED (пропуск профиля)
    status          VARCHAR(50) NOT NULL DEFAULT 'LIKED',

    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),

    -- Уникальный индекс, чтобы нельзя было лайкнуть одного человека дважды
    CONSTRAINT uk_sender_receiver UNIQUE (sender_id, receiver_id)
);

