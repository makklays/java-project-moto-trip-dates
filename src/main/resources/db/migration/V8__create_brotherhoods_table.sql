-- V8__create_brotherhoods_table.sql
-- Migration #8: create table for motorcycle brotherhoods (clubs)

-- Creating table 'brotherhoods' for storing club metadata
CREATE TABLE IF NOT EXISTS brotherhoods
(
    id            BIGSERIAL PRIMARY KEY,
    creator_id    BIGINT NOT NULL REFERENCES users(id), -- Основатель / Президент клуба

    title         VARCHAR(150) NOT NULL,
    description   TEXT,
    avatar        VARCHAR(255),
    banner        VARCHAR(255),

    -- Тип приватности: PUBLIC (открытый), PRIVATE (по заявкам), SECRET (скрытый)
    privacy_type  VARCHAR(50) NOT NULL DEFAULT 'PUBLIC',

    created_at    TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at    TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_brotherhoods_name UNIQUE (name)
);

-- Index for searching clubs by name
CREATE INDEX idx_brotherhoods_name ON brotherhoods(name);

