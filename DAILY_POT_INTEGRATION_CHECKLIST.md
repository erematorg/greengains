# Daily Pot Integration Checklist

## ✅ Frontend Complete

### Files Modified:
1. ✅ `lib/main.dart` - Added DailyPotService initialization
2. ✅ `lib/screens/home_screen.dart` - Added floating DailyPotIcon (top-right)
3. ✅ `lib/services/location/foreground_location_service.dart` - Hooked up upload recording

### Files Created:
1. ✅ `lib/data/models/daily_pot.dart`
2. ✅ `lib/services/daily_pot_service.dart`
3. ✅ `lib/widgets/daily_pot_icon.dart`

---

## ⏳ Backend Pending

### 1. Run Supabase Migration

```bash
# Connect to your Supabase database
psql YOUR_SUPABASE_DATABASE_URL

# Run the migration
\i supabase_migrations/daily_pot.sql

# Verify tables created
\dt daily_pots
```

**File**: `supabase_migrations/daily_pot.sql`

---

### 2. Add API Endpoints to Render Backend

Add these 3 endpoints to your Node.js/Express app:

**File**: `backend_endpoints_daily_pot.js` (copy code into your main app)

```javascript
GET  /daily-pot         // Get current state
POST /daily-pot/upload  // Record upload (increment progress)
POST /daily-pot/claim   // Claim pot (random reward)
```

**Integration steps:**
1. Copy code from `backend_endpoints_daily_pot.js`
2. Add to your existing Express app (after authentication middleware)
3. Redeploy to Render

---

## 🧪 Testing

### 1. Start Tracking
```
1. Open app
2. Press "Start Tracking"
3. Grant location permissions
```

### 2. Wait for Uploads
```
Wait ~10 minutes for 5 uploads
Progress ring fills gradually:
- 1 upload = 20% ring filled
- 2 uploads = 40% ring filled
...
- 5 uploads = 100% ring filled + unlocked!
```

### 3. Claim Pot
```
1. See icon jiggle/wiggle + glow
2. Lock disappears
3. Tap icon
4. See "+[random 10-100] credits! 🍯"
```

### 4. Verify Daily Reset
```
1. Wait until next day (UTC midnight)
2. Open app
3. See progress reset to 0%
4. Need 5 new uploads to unlock
```

---

## 📊 What to Check

### App Launch:
- ✅ No crashes
- ✅ Icon appears in top-right corner (small, minimal)
- ✅ Icon shows 0% progress (empty ring + lock)

### After 1st Upload:
- ✅ Icon ring fills 20%
- ✅ Still locked

### After 5th Upload:
- ✅ Icon jiggles/wiggles
- ✅ Lock vanishes
- ✅ Icon glows green
- ✅ Ring 100% filled

### Claim Pot:
- ✅ Haptic feedback
- ✅ Success message: "+[amount] credits! 🍯"
- ✅ Icon stops jiggling
- ✅ Icon turns neutral gray

### Next Day:
- ✅ Progress resets
- ✅ Total credits persist

---

## 🐛 Common Issues

### Issue: Icon doesn't appear
**Fix**: Check that `DailyPotService.instance.initialize()` is called in `main.dart`

### Issue: Progress doesn't increase
**Fix**:
1. Check backend logs - is `/daily-pot/upload` endpoint working?
2. Verify Supabase migration ran successfully
3. Check auth token is being passed correctly

### Issue: Can't claim pot
**Fix**:
1. Check backend logs - is `/daily-pot/claim` endpoint working?
2. Verify pot is actually unlocked (5+ uploads)
3. Check for "already claimed today" error

### Issue: Random reward always same
**Fix**: Check `claim_daily_pot()` function in Supabase - should use `random()` for reward generation

---

## 🎯 Expected Behavior

### Normal Flow:
```
Day 1:
- Start tracking → Make 5 uploads → Unlock pot → Claim → Get 45 credits

Day 2:
- Open app → Progress reset → Make 5 uploads → Claim → Get 25 credits (total: 70)

Day 3:
- Open app → Progress reset → Make 5 uploads → Claim → Get 90 credits (total: 160)
```

### Edge Cases:
- **Claim twice in one day**: Shows "Already claimed today"
- **Close app before claiming**: Pot stays unlocked until claimed or next day
- **Miss a day**: Progress resets, total credits persist
- **Reinstall app**: Credits persist (tied to Firebase user ID)

---

## 📝 Next Steps After Integration

1. Test on virtual device (Pixel_3a_API_34)
2. Test on real device (your Pixel 7)
3. Monitor backend logs for errors
4. Adjust `uploads_required` if 5 is too many/few
5. Consider adding leaderboard (future feature)

---

## 🔧 Configuration Options

### Adjust Uploads Required:
In `supabase_migrations/daily_pot.sql`, change line:
```sql
uploads_required INTEGER NOT NULL DEFAULT 5,
```
To:
```sql
uploads_required INTEGER NOT NULL DEFAULT 3,  -- Easier
```

### Adjust Reward Range:
In `claim_daily_pot()` function, change:
```sql
v_random := floor(random() * 19)::integer; -- 0 to 18
v_reward := (v_random * 5) + 10; -- 10 to 100
```
To:
```sql
v_random := floor(random() * 9)::integer; -- 0 to 8
v_reward := (v_random * 10) + 20; -- 20 to 100, increments of 10
```

---

## ✅ When Complete

Mark these as done:
- [ ] Supabase migration run
- [ ] Backend endpoints added & deployed
- [ ] Tested on emulator
- [ ] Tested on real device (Pixel 7)
- [ ] Verified credits accumulate
- [ ] Verified daily reset works
- [ ] No crashes or errors

**Then you're ready for balanced mode optimization!**
