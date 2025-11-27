CREATE TABLE IF NOT EXISTS device_secrets (
  device_id VARCHAR(128) PRIMARY KEY,
  user_id VARCHAR(128) NOT NULL,
  secret VARCHAR(64) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
  last_used_at TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_device_secrets_secret ON device_secrets(secret);
