-- Migration: Create user_preferences table
-- Run: psql -U postgres -d greengains -f db/migrations/001_create_user_preferences.sql

CREATE TABLE IF NOT EXISTS user_preferences (
    firebase_uid VARCHAR(128) PRIMARY KEY,
    theme_mode VARCHAR(10) DEFAULT 'system' CHECK (theme_mode IN ('light', 'dark', 'system')),
    use_mobile_data BOOLEAN DEFAULT FALSE,
    share_location BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT NOW(),
    updated_at TIMESTAMPTZ DEFAULT NOW()
);

-- Index for fast lookups
CREATE INDEX IF NOT EXISTS idx_user_prefs_uid ON user_preferences(firebase_uid);

-- Trigger to auto-update updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_user_preferences_updated_at
    BEFORE UPDATE ON user_preferences
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
