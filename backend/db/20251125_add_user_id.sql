-- Migration: Add user_id to link data to Firebase Users (Honeygain Model)
-- Date: 2025-11-25

-- 1. Add user_id to sensor_batches (Historical Data)
ALTER TABLE public.sensor_batches
ADD COLUMN IF NOT EXISTS user_id TEXT;

CREATE INDEX IF NOT EXISTS idx_sensor_batches_user_id
ON public.sensor_batches (user_id);

-- 2. Add user_id to user_stats (Device Registry)
-- Note: user_stats is currently keyed by device_hash.
-- Adding user_id allows us to group multiple devices under one user account.
ALTER TABLE public.user_stats
ADD COLUMN IF NOT EXISTS user_id TEXT;

CREATE INDEX IF NOT EXISTS idx_user_stats_user_id
ON public.user_stats (user_id);

-- 3. Comment explaining the change
COMMENT ON COLUMN public.sensor_batches.user_id IS 'Firebase UID of the user who uploaded this batch.';
COMMENT ON COLUMN public.user_stats.user_id IS 'Firebase UID of the user who owns this device.';
