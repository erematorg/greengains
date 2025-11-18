ALTER TABLE public.sensor_aggregates_5m
    ADD COLUMN IF NOT EXISTS quality_samples BIGINT NOT NULL DEFAULT 0,
    ADD COLUMN IF NOT EXISTS quality_valid_ratio DOUBLE PRECISION,
    ADD COLUMN IF NOT EXISTS quality_pocket_ratio DOUBLE PRECISION;

ALTER TABLE public.sensor_aggregates_daily
    ADD COLUMN IF NOT EXISTS quality_samples BIGINT NOT NULL DEFAULT 0,
    ADD COLUMN IF NOT EXISTS quality_valid_ratio DOUBLE PRECISION,
    ADD COLUMN IF NOT EXISTS quality_pocket_ratio DOUBLE PRECISION;
