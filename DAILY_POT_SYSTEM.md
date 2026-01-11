# Daily Pot System - Honeygain Lucky Pot Style

## Overview

A **minimal, passive reward system** inspired by Honeygain's Lucky Pot. Users upload sensor data → progress fills → unlock pot → claim random credits (10-100).

**NO streaks, NO intrusive popups** - just a small icon that jiggles when ready.

---

## How It Works

### 1. **Upload to Earn Progress**
- Every successful upload = progress toward unlocking pot
- **5 uploads = pot unlocks** (configurable)
- Progress shown as circular ring around icon

### 2. **Unlock & Claim**
- Once 5 uploads completed → pot unlocks
- Icon jiggles/wiggles → lock icon disappears
- Tap to claim random reward: **10, 15, 20, 25... up to 100 credits**

### 3. **Daily Reset**
- UTC midnight → pot resets
- Need 5 new uploads to unlock tomorrow's pot
- Total credits accumulate (never reset)

---

## Visual Design

### Locked State (0-4 uploads):
```
┌────────┐
│ 🍃🔒  │  ← Leaf icon + lock
│ ▁▁▂▂▃  │  ← Progress ring (60% filled)
└────────┘
```

### Unlocked State (5+ uploads, not claimed):
```
┌────────┐
│  🍃✨  │  ← Glowing, NO lock
│ ━━━━━  │  ← Full ring
└────────┘
  *wiggles*
```

### Claimed State:
```
┌────────┐
│  🍃   │  ← Neutral gray
│ ━━━━━  │  ← Full ring, no glow
└────────┘
```

---

## Reward Structure

### Random Credits:
- **Min**: 10 credits
- **Max**: 100 credits
- **Increments**: 5 (10, 15, 20, 25, 30... 100)

### No Multipliers/Streaks:
- Every day is independent
- Fair random chance
- Simple and transparent

---

## UI Placement

### Option A: App Bar (Top Right)
```dart
AppBar(
  actions: [
    Padding(
      padding: EdgeInsets.only(right: 12),
      child: DailyPotIcon(),
    ),
  ],
)
```

### Option B: Floating (Less Intrusive)
```dart
Stack(
  children: [
    ListView(...), // Main content
    Positioned(
      top: 16,
      right: 16,
      child: DailyPotIcon(),
    ),
  ],
)
```

**Recommendation**: Floating top-right to avoid app bar clutter

---

## Backend Setup

### Database (Supabase)

Run migration:
```bash
psql YOUR_SUPABASE_URL -f supabase_migrations/daily_pot.sql
```

Creates:
- `daily_pots` table
- `record_pot_upload()` function (increment progress)
- `claim_daily_pot()` function (random reward 10-100)
- `get_daily_pot_state()` function (check status)

### API Endpoints (Render Backend)

```javascript
// GET /daily-pot - Get current state
app.get('/daily-pot', authenticateUser, async (req, res) => {
  const { data } = await supabase
    .rpc('get_daily_pot_state', { p_user_id: req.user.id });
  res.json(data[0]);
});

// POST /daily-pot/upload - Record upload (increment progress)
app.post('/daily-pot/upload', authenticateUser, async (req, res) => {
  const { data } = await supabase
    .rpc('record_pot_upload', { p_user_id: req.user.id });
  res.json(data[0]);
});

// POST /daily-pot/claim - Claim pot (random reward)
app.post('/daily-pot/claim', authenticateUser, async (req, res) => {
  const { data, error } = await supabase
    .rpc('claim_daily_pot', { p_user_id: req.user.id });

  if (error) {
    if (error.message.includes('not unlocked')) {
      return res.status(400).json({ error: 'Pot not unlocked yet' });
    }
    return res.status(500).json({ error: error.message });
  }

  const result = data[0];
  if (result.has_claimed_today && result.claimed_amount === 0) {
    return res.status(409).json({ error: 'Already claimed today' });
  }

  res.json(result);
});
```

---

## Integration Steps

### 1. Backend
- ✅ Run Supabase migration (`daily_pot.sql`)
- ✅ Add 3 endpoints to Render backend

### 2. Frontend
- ✅ Initialize service in `main.dart`:
  ```dart
  await DailyPotService.instance.initialize();
  ```

- ✅ Record uploads in upload service:
  ```dart
  // After successful upload
  DailyPotService.instance.recordUpload();
  ```

- ✅ Add icon to home screen:
  ```dart
  // Floating top-right
  Stack(
    children: [
      ListView(...),
      Positioned(
        top: 16,
        right: 16,
        child: DailyPotIcon(),
      ),
    ],
  )
  ```

### 3. Testing
```dart
// Test progression
1. Start tracking
2. Wait for 5 uploads (10 minutes)
3. See icon jiggle + glow
4. Tap icon
5. See "+[random 10-100] credits! 🍯"
```

---

## Files Created

```
lib/
├── data/models/daily_pot.dart          # Data model
├── services/daily_pot_service.dart      # Service layer
├── widgets/daily_pot_icon.dart          # UI widget
└── main.dart                            # (add init call)

supabase_migrations/
└── daily_pot.sql                        # Database schema

backend/ (your Node.js app)
└── (add 3 endpoints)                    # API routes
```

---

## Future Enhancements

### What Can Credits Buy?
- 🎁 **Gift cards** ($5 = 1000 credits, $10 = 1800 credits)
- 🌱 **Donations** (donate to environmental causes)
- 🏆 **Leaderboards** (show top earners)
- 🎨 **App themes** (100 credits = unlock theme)
- 💰 **Cash out** (if you get funding)

### Possible Additions (Later):
- Weekly bonus pots (higher rewards)
- Referral bonuses (invite friend = 50 credits)
- Upload milestones (100th upload = bonus)
- Special event pots (Earth Day = 2x rewards)

---

## Why This Works

✅ **90% passive** - No interruptions, just a subtle icon
✅ **Fair & transparent** - Random rewards, no tricks
✅ **Cheat-resistant** - Server-authoritative, tied to uploads
✅ **Scalable** - Ready for reward redemption
✅ **Minimalist** - Fits GreenGains' clean design

**Goal**: Give users a reason to check the app daily WITHOUT breaking the passive experience.
