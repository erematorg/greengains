-- Check daily pot state and recent uploads for your user
SELECT 
  dp.user_id,
  dp.uploads_today,
  dp.uploads_required,
  dp.total_credits,
  dp.last_claim_date,
  dp.updated_at as pot_last_updated,
  dp.created_at as pot_created,
  (SELECT COUNT(*) 
   FROM sensor_batches sb 
   WHERE sb.user_id = dp.user_id 
   AND sb.timestamp_utc > CURRENT_DATE) as actual_uploads_today
FROM daily_pots dp
ORDER BY dp.updated_at DESC
LIMIT 5;
