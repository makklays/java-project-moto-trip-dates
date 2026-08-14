-- V11__create_sos_alerts_table.sql
-- Migration #11: create table for motorcycle sos alerts

-- Creating table 'sos_alerts' for sos alerts
CREATE TABLE IF NOT EXISTS sos_alerts
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,

    -- Географическая точка (Долгота, Широта) в формате SRID 4326 (стандарт GPS)
    coordinates     GEOMETRY(Point, 4326) NOT NULL, -- Где случилась беда

    -- Тип аварии: ACCIDENT (ДТП), BREAKDOWN (сломался), NO_FUEL (сухой бак), MEDICAL (нужна аптечка)
    sos_type        VARCHAR(50) NOT NULL,
    description     TEXT, -- Дополнительная инфа (например: "Пробил колесо, нужен ключ на 17")

    -- Статус: ACTIVE (нужна помощь), RESOLVED (помогли / уехал), FALSE_ALARM (отмена)
    status          VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',

    created_at      TIMESTAMP WITH TIME ZONE DEFAULT now(),
    updated_at      TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE INDEX idx_sos_alerts_geo ON sos_alerts USING GIST(coordinates);
CREATE INDEX idx_sos_alerts_status ON sos_alerts(status);

