-- V4__create_riders_table.sql
-- Migration #4: create table riders

-- Creating table 'riders' for storing main rider data
CREATE TABLE IF NOT EXISTS riders (
    id                        BIGSERIAL PRIMARY KEY,
    user_id                   BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    nickname                  VARCHAR(100),
    riding_since_year         INTEGER,           -- Год начала катания (стаж)
    driving_style             VARCHAR(50),       -- стиль езды: CALM, AGGRESSIVE, TOURING, ENDURO_MUD
    rider_type                VARCHAR(50),       -- роль на дороге: SOLO, GROUP, PASSENGER_LOOKER - ищу двойку
    has_helmet_for_passenger  BOOLEAN DEFAULT FALSE,
    blood_type                VARCHAR(10),       -- Например, A+, B-

    bio                       TEXT,              -- О себе, девиз, любимые маршруты

    status                    VARCHAR(50) NOT NULL DEFAULT 'PLANNING', -- PLANNING, ACTIVE, FINISHED

    created_at                TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at                TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT uk_riders_user_id UNIQUE (user_id) -- Один юзер = один мото-профиль
);

CREATE INDEX idx_riders_user_id ON riders(user_id);

