-- Performance optimization indexes for user-specific queries
-- Date: 2026-01-19
--
-- These indexes improve performance for the tiles endpoint and user data queries
-- which filter by user_id and timestamp_utc simultaneously.

-- Composite index for user + timestamp queries
-- The tiles endpoint queries with: WHERE user_id = $1 AND timestamp_utc > ...
-- A composite index is much more efficient than separate indexes for this pattern
-- Ordering by timestamp DESC allows efficient "recent data first" queries
CREATE INDEX IF NOT EXISTS idx_sensor_batches_user_time
ON sensor_batches (user_id, timestamp_utc DESC);

-- GIN index specifically for location JSON queries
-- Enables fast filtering and extraction of location data from batch_json
-- Usage: WHERE batch_json->'location' IS NOT NULL
--        AND batch_json->'location'->>'lat' ...
CREATE INDEX IF NOT EXISTS idx_sensor_batches_location
ON sensor_batches USING GIN ((batch_json -> 'location'));

-- Performance notes:
-- - idx_sensor_batches_user_time: Dramatically improves tiles endpoint queries
--   which group and filter by user_id + timestamp simultaneously
-- - idx_sensor_batches_location: Speeds up location-based filtering and extraction
--   used in the coverage map tile generation
-- - These complement the existing idx_sensor_batches_user_id single-column index
-- - Safe for production: All indexes created with IF NOT EXISTS
