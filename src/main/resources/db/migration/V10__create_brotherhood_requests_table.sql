-- V10__create_brotherhood_requests_table.sql
-- Migration #10: create table for motorcycle brotherhood requests (clubs)

-- Creating table 'brotherhood_requests' for join requests (for PRIVATE clubs)
CREATE TABLE IF NOT EXISTS brotherhood_requests
(
    id              BIGSERIAL PRIMARY KEY,
    brotherhood_id  BIGINT NOT NULL REFERENCES brotherhoods(id) ON DELETE CASCADE,
    user_id         BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- Статус заявки: PENDING (ожидает), APPROVED (принята), REJECTED (отклонена)
    status          VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    message         TEXT, -- Приветственное сообщение от кандидата

    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_brotherhood_request_pending UNIQUE (brotherhood_id, user_id, status)
);

CREATE INDEX idx_bh_requests_pending ON brotherhood_requests(brotherhood_id, status);

