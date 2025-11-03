-- Aggregated sensor metrics (5-minute windows)
CREATE TABLE IF NOT EXISTS sensor_aggregates_5m (
    window_start TIMESTAMPTZ NOT NULL,
    window_end   TIMESTAMPTZ NOT NULL,
    geohash      TEXT        NOT NULL,
    samples_count INTEGER    NOT NULL,
    device_count  INTEGER    NOT NULL,
    avg_light     DOUBLE PRECISION,
    avg_light_min DOUBLE PRECISION,
    avg_light_max DOUBLE PRECISION,
    avg_accel_rms DOUBLE PRECISION,
    avg_gyro_rms  DOUBLE PRECISION,
    movement_score DOUBLE PRECISION,
    battery_avg    DOUBLE PRECISION,
    location_share DOUBLE PRECISION,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (window_start, geohash)
);

CREATE INDEX IF NOT EXISTS idx_sensor_aggregates_5m_geohash
    ON sensor_aggregates_5m (geohash);

CREATE INDEX IF NOT EXISTS idx_sensor_aggregates_5m_window
    ON sensor_aggregates_5m (window_start);


-- Daily roll-ups
CREATE TABLE IF NOT EXISTS sensor_aggregates_daily (
    day           DATE NOT NULL,
    geohash       TEXT NOT NULL,
    samples_count BIGINT NOT NULL,
    device_count  INTEGER NOT NULL,
    avg_light     DOUBLE PRECISION,
    avg_light_min DOUBLE PRECISION,
    avg_light_max DOUBLE PRECISION,
    avg_accel_rms DOUBLE PRECISION,
    avg_gyro_rms  DOUBLE PRECISION,
    movement_score DOUBLE PRECISION,
    battery_avg    DOUBLE PRECISION,
    location_share DOUBLE PRECISION,
    device_hours   DOUBLE PRECISION,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (day, geohash)
);

CREATE INDEX IF NOT EXISTS idx_sensor_aggregates_daily_geohash
    ON sensor_aggregates_daily (geohash);
