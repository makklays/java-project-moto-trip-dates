-- V7__create_matche_blocks_tables.sql
-- Migration #7: create table for block matching system

-- Таблица для черного списка (блокировок)
CREATE TABLE IF NOT EXISTS matche_blocks
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,   -- Кто блокирует
    blocked_user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,   -- Кого блокируют

    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_user_blocked UNIQUE (user_id, blocked_user_id)
);

CREATE INDEX idx_blocks_user ON blocks(user_id);

