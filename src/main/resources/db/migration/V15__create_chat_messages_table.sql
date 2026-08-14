-- V15__create_chat_messages_table.sql
-- Migration #15: create table chat_messages

-- Таблица сообщений чата (определенного чата)
CREATE TABLE IF NOT EXISTS chat_messages
(
    id          BIGSERIAL PRIMARY KEY,
    chat_id     BIGINT NOT NULL REFERENCES chats(id) ON DELETE CASCADE,
    sender_id   BIGINT NOT NULL REFERENCES users(id),

    message_text TEXT NOT NULL,

    created_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE INDEX idx_messages_chat_id ON messages(chat_id);

