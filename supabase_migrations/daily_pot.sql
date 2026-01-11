-- Daily Pot System (Honeygain Lucky Pot style)
-- Upload-based unlock, no streaks, random rewards

CREATE TABLE IF NOT EXISTS daily_pots (
    user_id TEXT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    total_credits INTEGER NOT NULL DEFAULT 0,
    uploads_today INTEGER NOT NULL DEFAULT 0,
    uploads_required INTEGER NOT NULL DEFAULT 5,
    last_claim_date DATE, -- UTC date only
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Index for faster lookups
CREATE INDEX idx_daily_pots_user_id ON daily_pots(user_id);
CREATE INDEX idx_daily_pots_last_claim ON daily_pots(last_claim_date);

-- Function to record an upload (increment progress)
CREATE OR REPLACE FUNCTION record_pot_upload(
    p_user_id TEXT
)
RETURNS TABLE(
    user_id TEXT,
    total_credits INTEGER,
    uploads_today INTEGER,
    uploads_required INTEGER,
    is_unlocked BOOLEAN,
    has_claimed_today BOOLEAN,
    last_claim_date DATE
) AS $$
DECLARE
    v_row daily_pots%ROWTYPE;
    v_today DATE;
    v_uploads_today INTEGER;
BEGIN
    v_today := CURRENT_DATE AT TIME ZONE 'UTC';

    -- Get or create user record
    SELECT * INTO v_row FROM daily_pots WHERE daily_pots.user_id = p_user_id;

    IF NOT FOUND THEN
        -- First time user
        INSERT INTO daily_pots (user_id, total_credits, uploads_today, uploads_required, last_claim_date)
        VALUES (p_user_id, 0, 1, 5, NULL)
        RETURNING * INTO v_row;
    ELSE
        -- Check if new day (reset uploads_today)
        IF v_row.last_claim_date IS NOT NULL AND v_row.last_claim_date < v_today THEN
            v_uploads_today := 1; -- Reset for new day
        ELSE
            v_uploads_today := v_row.uploads_today + 1;
        END IF;

        -- Update record
        UPDATE daily_pots SET
            uploads_today = v_uploads_today,
            updated_at = NOW()
        WHERE daily_pots.user_id = p_user_id
        RETURNING * INTO v_row;
    END IF;

    -- Return state
    RETURN QUERY SELECT
        v_row.user_id,
        v_row.total_credits,
        v_row.uploads_today,
        v_row.uploads_required,
        (v_row.uploads_today >= v_row.uploads_required) AS is_unlocked,
        (v_row.last_claim_date = v_today) AS has_claimed_today,
        v_row.last_claim_date;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Function to claim pot (random reward 10-100, increments of 5)
CREATE OR REPLACE FUNCTION claim_daily_pot(
    p_user_id TEXT
)
RETURNS TABLE(
    user_id TEXT,
    total_credits INTEGER,
    uploads_today INTEGER,
    uploads_required INTEGER,
    is_unlocked BOOLEAN,
    has_claimed_today BOOLEAN,
    last_claim_date DATE,
    claimed_amount INTEGER
) AS $$
DECLARE
    v_row daily_pots%ROWTYPE;
    v_today DATE;
    v_reward INTEGER;
    v_random INTEGER;
BEGIN
    v_today := CURRENT_DATE AT TIME ZONE 'UTC';

    -- Get user record
    SELECT * INTO v_row FROM daily_pots WHERE daily_pots.user_id = p_user_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'User pot not found';
    END IF;

    -- Check if unlocked
    IF v_row.uploads_today < v_row.uploads_required THEN
        RAISE EXCEPTION 'Pot not unlocked yet';
    END IF;

    -- Check if already claimed today
    IF v_row.last_claim_date = v_today THEN
        -- Already claimed, return current state
        RETURN QUERY SELECT
            v_row.user_id,
            v_row.total_credits,
            v_row.uploads_today,
            v_row.uploads_required,
            TRUE AS is_unlocked,
            TRUE AS has_claimed_today,
            v_row.last_claim_date,
            0 AS claimed_amount;
        RETURN;
    END IF;

    -- Generate random reward: 10, 15, 20, 25... up to 100
    -- Random number 0-18, then multiply by 5 and add 10 = range 10-100 in increments of 5
    v_random := floor(random() * 19)::integer; -- 0 to 18
    v_reward := (v_random * 5) + 10; -- 10 to 100

    -- Update record
    UPDATE daily_pots SET
        total_credits = total_credits + v_reward,
        last_claim_date = v_today,
        updated_at = NOW()
    WHERE daily_pots.user_id = p_user_id
    RETURNING * INTO v_row;

    -- Return updated state
    RETURN QUERY SELECT
        v_row.user_id,
        v_row.total_credits,
        v_row.uploads_today,
        v_row.uploads_required,
        TRUE AS is_unlocked,
        TRUE AS has_claimed_today,
        v_row.last_claim_date,
        v_reward AS claimed_amount;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Function to get current pot state
CREATE OR REPLACE FUNCTION get_daily_pot_state(
    p_user_id TEXT
)
RETURNS TABLE(
    user_id TEXT,
    total_credits INTEGER,
    uploads_today INTEGER,
    uploads_required INTEGER,
    is_unlocked BOOLEAN,
    has_claimed_today BOOLEAN,
    last_claim_date DATE
) AS $$
DECLARE
    v_row daily_pots%ROWTYPE;
    v_today DATE;
BEGIN
    v_today := CURRENT_DATE AT TIME ZONE 'UTC';

    SELECT * INTO v_row FROM daily_pots WHERE daily_pots.user_id = p_user_id;

    IF NOT FOUND THEN
        -- New user
        RETURN QUERY SELECT
            p_user_id AS user_id,
            0 AS total_credits,
            0 AS uploads_today,
            5 AS uploads_required,
            FALSE AS is_unlocked,
            FALSE AS has_claimed_today,
            NULL::DATE AS last_claim_date;
        RETURN;
    END IF;

    -- Check if new day (reset uploads_today in response)
    IF v_row.last_claim_date IS NOT NULL AND v_row.last_claim_date < v_today THEN
        RETURN QUERY SELECT
            v_row.user_id,
            v_row.total_credits,
            0 AS uploads_today, -- Reset for new day
            v_row.uploads_required,
            FALSE AS is_unlocked,
            FALSE AS has_claimed_today,
            v_row.last_claim_date;
        RETURN;
    END IF;

    RETURN QUERY SELECT
        v_row.user_id,
        v_row.total_credits,
        v_row.uploads_today,
        v_row.uploads_required,
        (v_row.uploads_today >= v_row.uploads_required) AS is_unlocked,
        (v_row.last_claim_date = v_today) AS has_claimed_today,
        v_row.last_claim_date;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Grant permissions
GRANT SELECT, INSERT, UPDATE ON daily_pots TO authenticated;
GRANT EXECUTE ON FUNCTION record_pot_upload TO authenticated;
GRANT EXECUTE ON FUNCTION claim_daily_pot TO authenticated;
GRANT EXECUTE ON FUNCTION get_daily_pot_state TO authenticated;
