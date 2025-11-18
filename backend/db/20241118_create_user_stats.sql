-- User contribution stats per device hash
CREATE TABLE IF NOT EXISTS public.user_stats (
    device_hash TEXT PRIMARY KEY,
    samples_count BIGINT NOT NULL DEFAULT 0,
    valid_samples BIGINT NOT NULL DEFAULT 0,
    pocket_samples BIGINT NOT NULL DEFAULT 0,
    uptime_seconds BIGINT NOT NULL DEFAULT 0,
    last_upload_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS user_stats_last_upload_idx
    ON public.user_stats (last_upload_at DESC);

COMMENT ON TABLE public.user_stats IS 'Per-device contribution totals used for dashboards and incentives.';
