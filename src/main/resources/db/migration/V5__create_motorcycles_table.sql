-- V5__create_motorcycles_table.sql
-- Migration #5: create table motorcycles

-- Creating table 'motorcycles' for storing main motorcycle data
CREATE TABLE motorcycles (
    id                  BIGSERIAL PRIMARY KEY,

    -- Связь идет к rider_profiles (а через него к users)
    rider_id            BIGINT NOT NULL REFERENCES rider_profiles(id) ON DELETE CASCADE,

    brand               VARCHAR(100) NOT NULL,      -- Honda, Yamaha, BMW
    model               VARCHAR(100) NOT NULL,
    bike_type           VARCHAR(50),                -- SPORT, ENDURO, CHOPPER
    engine_capacity     INT,                        -- Объем в кубах (например, 600)
    manufacture_year    INT,

    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

