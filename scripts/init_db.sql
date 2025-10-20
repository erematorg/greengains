-- Database schema for the GreenGains MVP backend.

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS sensor_batches (
    id SERIAL PRIMARY KEY,
    device_hash TEXT NOT NULL,
    timestamp_utc TIMESTAMPTZ NOT NULL,
    batch_json JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS sensor_batches_device_hash_idx
    ON sensor_batches (device_hash);

CREATE INDEX IF NOT EXISTS sensor_batches_timestamp_idx
    ON sensor_batches (timestamp_utc);

-- Retention policy expectation: records older than 30 days should be removed
-- via scripts/cleanup.py or a scheduled job.
