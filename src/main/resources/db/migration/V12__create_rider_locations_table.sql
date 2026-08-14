-- V12__create_rider_locations_table.sql
-- Migration #12: create table for rider locations

-- Последние известные координаты пользователей на карте
CREATE TABLE IF NOT EXISTS rider_locations
(
    user_id     BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,

    -- Географическая точка (Долгота, Широта) в формате SRID 4326 (стандарт GPS)
    coordinates GEOMETRY(Point, 4326) NOT NULL,

    -- Направление движения в градусах (от 0 до 360), чтобы на карте React байк смотрел в нужную сторону
    bearing     REAL,

    updated_at  TIMESTAMP WITH TIME ZONE DEFAULT now()
);

-- Пространственный индекс (GIST) для ультра-быстрого поиска людей в радиусе N км
CREATE INDEX idx_rider_locations_geo ON rider_locations USING GIST(coordinates);

