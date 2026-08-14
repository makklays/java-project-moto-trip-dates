-- V9__create_brotherhood_members_table.sql
-- Migration #9: create table for motorcycle brotherhood members (clubs)

-- Creating table 'brotherhood_members' for club hierarchy and membership
CREATE TABLE IF NOT EXISTS brotherhood_members
(
    id              BIGSERIAL PRIMARY KEY,
    brotherhood_id  BIGINT NOT NULL REFERENCES brotherhoods(id) ON DELETE CASCADE,
    user_id         BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- Роль в клубе: PRESIDENT, OFFICER, MEMBER
    club_role       VARCHAR(50) NOT NULL DEFAULT 'MEMBER',

    joined_at       TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_brotherhood_user UNIQUE (brotherhood_id, user_id)
);

-- Indexes for fast lookups of members and user clubs
CREATE INDEX idx_bh_members_brotherhood_id ON brotherhood_members(brotherhood_id);
CREATE INDEX idx_bh_members_user_id ON brotherhood_members(user_id);

