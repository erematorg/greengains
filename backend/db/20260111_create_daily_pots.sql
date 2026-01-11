-- Daily Pot Reward System
-- Honeygain-style reward system: Upload data → unlock pot → claim random credits

CREATE TABLE IF NOT EXISTS daily_pots (
    user_id TEXT PRIMARY KEY,
    total_credits INTEGER NOT NULL DEFAULT 0,
    uploads_today INTEGER NOT NULL DEFAULT 0,
    uploads_required INTEGER NOT NULL DEFAULT 5,
    last_claim_date DATE,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Index for faster lookups by user
CREATE INDEX IF NOT EXISTS idx_daily_pots_user_id ON daily_pots(user_id);

-- Index for claim date queries
CREATE INDEX IF NOT EXISTS idx_daily_pots_last_claim ON daily_pots(last_claim_date);

-- Updated timestamp trigger
CREATE OR REPLACE FUNCTION update_daily_pots_timestamp()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER daily_pots_updated_at
    BEFORE UPDATE ON daily_pots
    FOR EACH ROW
    EXECUTE FUNCTION update_daily_pots_timestamp();
