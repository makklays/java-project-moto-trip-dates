-- V14__create_chat_members_table.sql
-- Migration #14: create table chat_members

-- Участники чатов (для дейтинга тут будет 2 юзера, для клуба — все члены)
CREATE TABLE IF NOT EXISTS chat_members
(
    id          BIGSERIAL PRIMARY KEY,
    chat_id     BIGINT NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
    user_id     BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    joined_at   TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_chat_user UNIQUE (chat_id, user_id)
);

