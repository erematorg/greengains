SELECT 
  user_id,
  uploads_today,
  uploads_required,
  total_credits,
  last_claim_date,
  updated_at
FROM daily_pots
ORDER BY updated_at DESC
LIMIT 5;
