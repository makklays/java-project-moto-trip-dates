-- V13__create_chats_table.sql
-- Migration #13: create table chats

-- Таблица чат-комнат (комната для мэтча или комната для клуба)
CREATE TABLE IF NOT EXISTS chats
(
    id          BIGSERIAL PRIMARY KEY,

    -- Тип чата: DATING (между двумя при мэтче), BROTHERHOOD (групповой чат клуба), TRIP (чат поездки)
    chat_type   VARCHAR(50) NOT NULL,

    -- ID сущности, к которой привязан чат (например, ID brotherhoods или ID поездки). NULL для дейтинга.
    source_id   BIGINT,

    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

