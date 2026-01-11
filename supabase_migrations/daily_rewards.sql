-- Daily Rewards System
-- Server-authoritative to prevent clock manipulation cheating

CREATE TABLE IF NOT EXISTS daily_rewards (
    user_id TEXT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    total_credits INTEGER NOT NULL DEFAULT 0,
    current_streak INTEGER NOT NULL DEFAULT 0,
    longest_streak INTEGER NOT NULL DEFAULT 0,
    last_claim_date DATE, -- UTC date only, not timestamp!
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Index for faster lookups
CREATE INDEX idx_daily_rewards_user_id ON daily_rewards(user_id);
CREATE INDEX idx_daily_rewards_last_claim ON daily_rewards(last_claim_date);

-- Function to claim daily reward (server-authoritative)
CREATE OR REPLACE FUNCTION claim_daily_reward(
    p_user_id TEXT
)
RETURNS TABLE(
    user_id TEXT,
    total_credits INTEGER,
    current_streak INTEGER,
    longest_streak INTEGER,
    last_claim_date DATE,
    can_claim_today BOOLEAN,
    today_reward INTEGER,
    reward_amount INTEGER
) AS $$
DECLARE
    v_row daily_rewards%ROWTYPE;
    v_today DATE;
    v_days_since_last INTEGER;
    v_new_streak INTEGER;
    v_base_reward INTEGER := 10;
    v_streak_bonus INTEGER := 0;
    v_total_reward INTEGER;
BEGIN
    -- Get server time in UTC (anti-cheat: never trust client clock)
    v_today := CURRENT_DATE AT TIME ZONE 'UTC';

    -- Get or create user record
    SELECT * INTO v_row FROM daily_rewards WHERE daily_rewards.user_id = p_user_id;

    IF NOT FOUND THEN
        -- First time user
        INSERT INTO daily_rewards (user_id, total_credits, current_streak, longest_streak, last_claim_date)
        VALUES (p_user_id, 0, 0, 0, NULL)
        RETURNING * INTO v_row;
    END IF;

    -- Check if already claimed today
    IF v_row.last_claim_date = v_today THEN
        -- Already claimed, return current state
        RETURN QUERY SELECT
            v_row.user_id,
            v_row.total_credits,
            v_row.current_streak,
            v_row.longest_streak,
            v_row.last_claim_date,
            FALSE AS can_claim_today,
            v_base_reward AS today_reward,
            0 AS reward_amount;
        RETURN;
    END IF;

    -- Calculate days since last claim
    IF v_row.last_claim_date IS NULL THEN
        v_days_since_last := 999; -- First claim ever
    ELSE
        v_days_since_last := v_today - v_row.last_claim_date;
    END IF;

    -- Update streak (soft reset: 1 day miss = pause, 2+ days = reset)
    IF v_days_since_last = 1 THEN
        -- Consecutive day - increment streak
        v_new_streak := v_row.current_streak + 1;
    ELSIF v_days_since_last = 2 THEN
        -- Missed 1 day - pause streak (no penalty)
        v_new_streak := v_row.current_streak;
    ELSE
        -- Missed 2+ days or first claim - reset to 1
        v_new_streak := 1;
    END IF;

    -- Calculate streak bonus (reward consistency)
    IF v_new_streak >= 30 THEN
        v_streak_bonus := 20;
    ELSIF v_new_streak >= 14 THEN
        v_streak_bonus := 15;
    ELSIF v_new_streak >= 7 THEN
        v_streak_bonus := 10;
    ELSIF v_new_streak >= 3 THEN
        v_streak_bonus := 5;
    END IF;

    v_total_reward := v_base_reward + v_streak_bonus;

    -- Update record
    UPDATE daily_rewards SET
        total_credits = total_credits + v_total_reward,
        current_streak = v_new_streak,
        longest_streak = GREATEST(longest_streak, v_new_streak),
        last_claim_date = v_today,
        updated_at = NOW()
    WHERE daily_rewards.user_id = p_user_id
    RETURNING * INTO v_row;

    -- Return updated state
    RETURN QUERY SELECT
        v_row.user_id,
        v_row.total_credits,
        v_row.current_streak,
        v_row.longest_streak,
        v_row.last_claim_date,
        FALSE AS can_claim_today, -- Just claimed, can't claim again today
        v_base_reward AS today_reward,
        v_total_reward AS reward_amount;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Function to get current reward state
CREATE OR REPLACE FUNCTION get_daily_reward_state(
    p_user_id TEXT
)
RETURNS TABLE(
    user_id TEXT,
    total_credits INTEGER,
    current_streak INTEGER,
    longest_streak INTEGER,
    last_claim_date DATE,
    can_claim_today BOOLEAN,
    today_reward INTEGER
) AS $$
DECLARE
    v_row daily_rewards%ROWTYPE;
    v_today DATE;
    v_base_reward INTEGER := 10;
    v_can_claim BOOLEAN;
BEGIN
    v_today := CURRENT_DATE AT TIME ZONE 'UTC';

    SELECT * INTO v_row FROM daily_rewards WHERE daily_rewards.user_id = p_user_id;

    IF NOT FOUND THEN
        -- New user, can claim
        RETURN QUERY SELECT
            p_user_id AS user_id,
            0 AS total_credits,
            0 AS current_streak,
            0 AS longest_streak,
            NULL::DATE AS last_claim_date,
            TRUE AS can_claim_today,
            v_base_reward AS today_reward;
        RETURN;
    END IF;

    -- Check if can claim today
    v_can_claim := (v_row.last_claim_date IS NULL OR v_row.last_claim_date < v_today);

    RETURN QUERY SELECT
        v_row.user_id,
        v_row.total_credits,
        v_row.current_streak,
        v_row.longest_streak,
        v_row.last_claim_date,
        v_can_claim AS can_claim_today,
        v_base_reward AS today_reward;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

-- Grant permissions (adjust based on your auth setup)
GRANT SELECT, INSERT, UPDATE ON daily_rewards TO authenticated;
GRANT EXECUTE ON FUNCTION claim_daily_reward TO authenticated;
GRANT EXECUTE ON FUNCTION get_daily_reward_state TO authenticated;
