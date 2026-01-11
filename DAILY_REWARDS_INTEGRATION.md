# Daily Rewards System - Integration Guide

## Overview

A **passive-friendly** daily login reward system that doesn't interrupt users. Just open the app → see a subtle badge → tap to claim credits.

## Features

✅ **Server-authoritative** - Uses Supabase functions for all date logic (prevents clock manipulation)
✅ **Soft streak system** - Miss 1 day = pause (no penalty), miss 2+ days = reset
✅ **Streak bonuses** - Consistent users get bonus credits
✅ **Non-intrusive UI** - Small pulsing badge, no popups or interruptions
✅ **Account-based** - Credits tied to Firebase user ID, persist across devices

## Reward Structure

### Base Reward
- **10 credits** per daily login

### Streak Bonuses
- 3-6 days: +5 credits (15 total)
- 7-13 days: +10 credits (20 total)
- 14-29 days: +15 credits (25 total)
- 30+ days: +20 credits (30 total)

### Streak Rules (Soft)
- **Consecutive day**: Streak +1
- **Miss 1 day (2 days gap)**: Streak pauses (no penalty!)
- **Miss 2+ days**: Streak resets to 1

## Backend Setup

### 1. Run Supabase Migration

```bash
# Apply the migration to create tables and functions
psql YOUR_SUPABASE_DB_URL -f supabase_migrations/daily_rewards.sql
```

This creates:
- `daily_rewards` table
- `claim_daily_reward()` function (server-authoritative claim)
- `get_daily_reward_state()` function (check eligibility)

### 2. Add Backend Endpoints

Add these to your `greengains` backend (Node.js/Render):

```javascript
// GET /daily-reward - Get current reward state
app.get('/daily-reward', authenticateUser, async (req, res) => {
  const userId = req.user.id;

  const { data, error } = await supabase
    .rpc('get_daily_reward_state', { p_user_id: userId });

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  res.json(data[0]); // Return first row
});

// POST /daily-reward/claim - Claim today's reward
app.post('/daily-reward/claim', authenticateUser, async (req, res) => {
  const userId = req.user.id;

  const { data, error } = await supabase
    .rpc('claim_daily_reward', { p_user_id: userId });

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  const result = data[0];

  // Check if already claimed today (can_claim_today = false)
  if (!result.can_claim_today && result.reward_amount === 0) {
    return res.status(409).json({ error: 'Already claimed today' });
  }

  res.json(result);
});
```

## Frontend Integration

### 1. Initialize Service on App Start

In `lib/main.dart`, add to `_initializeApp()`:

```dart
import 'services/daily_reward_service.dart';

Future<void> _initializeApp() async {
  // ... existing initialization ...

  // Initialize daily rewards (non-blocking)
  DailyRewardService.instance.initialize().catchError((e) {
    print('Daily rewards init failed (non-critical): $e');
  });
}
```

### 2. Add Badge to Home Screen

In `lib/screens/home_screen.dart`, add the badge to the app bar:

```dart
import '../widgets/daily_reward_badge.dart';

@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(
      title: const Text('GreenGains'),
      actions: [
        // Add daily reward badge
        const Padding(
          padding: EdgeInsets.only(right: 16),
          child: DailyRewardBadge(),
        ),
      ],
    ),
    // ... rest of home screen
  );
}
```

### Alternative: Floating Badge (Bottom Right)

Instead of app bar, you can show it as a floating indicator:

```dart
body: Stack(
  children: [
    // Your main content
    ListView(...),

    // Floating daily reward badge
    Positioned(
      bottom: 80, // Above bottom nav
      right: 16,
      child: const DailyRewardBadge(),
    ),
  ],
)
```

## Anti-Cheat Measures

### What's Prevented:
✅ **Clock manipulation** - Server time used (not device clock)
✅ **Multiple accounts** - Tied to Firebase user ID
✅ **Reinstall exploits** - Data stored server-side

### What's NOT Prevented (by design):
- Multiple Firebase accounts per person (acceptable for crowdsensing)
- VPN/proxy usage (doesn't affect functionality)

## Future Enhancements

### What Credits Can Buy (Later):
- 🎁 **Real rewards** - Gift cards, discounts
- 🏆 **Leaderboards** - Global/local rankings
- 🎨 **Cosmetics** - App themes, badges
- 💰 **Cash out** - Convert credits to money (if funded)
- 🌱 **Donations** - Donate credits to environmental causes

### Potential Features:
- Weekly challenges for bonus credits
- Referral bonuses
- Upload milestones (100 uploads = bonus)
- Community goals (city-wide tracking targets)

## Testing

### Test Scenarios:

1. **First claim**
   ```
   - Open app
   - See glowing badge with "0" credits
   - Tap badge
   - See "+10 credits claimed! 🎉"
   - Badge shows "10", no longer glowing
   ```

2. **Consecutive days**
   ```
   Day 1: Claim (+10) = 10 credits, 1 day streak
   Day 2: Claim (+10) = 20 credits, 2 day streak
   Day 3: Claim (+15) = 35 credits, 3 day streak (bonus!)
   ```

3. **Miss 1 day (soft reset)**
   ```
   Day 1: Claim (streak: 5)
   Day 2: [Don't open app]
   Day 3: Claim (streak: 5 - PAUSED, no penalty!)
   ```

4. **Miss 2+ days (hard reset)**
   ```
   Day 1: Claim (streak: 10)
   Day 2-3: [Don't open app]
   Day 4: Claim (streak: 1 - RESET)
   ```

## Database Queries (Debug)

```sql
-- Check user's reward state
SELECT * FROM daily_rewards WHERE user_id = 'YOUR_USER_ID';

-- Manually reset for testing
DELETE FROM daily_rewards WHERE user_id = 'YOUR_USER_ID';

-- Check all users' streaks
SELECT user_id, current_streak, total_credits
FROM daily_rewards
ORDER BY current_streak DESC
LIMIT 10;
```

## Files Created

```
lib/
├── data/models/daily_reward.dart          # Data model
├── services/daily_reward_service.dart      # Service layer
├── widgets/daily_reward_badge.dart         # UI widget
└── main.dart                               # (needs update for init)

supabase_migrations/
└── daily_rewards.sql                       # Database schema

backend/ (your Node.js app)
└── (needs 2 new endpoints)                 # API routes
```

## Summary

This system is:
- ✅ **90% passive** - No popups, just a badge
- ✅ **Cheat-resistant** - Server-authoritative
- ✅ **User-friendly** - Soft streaks, no harsh penalties
- ✅ **Scalable** - Ready for future reward redemption

The goal: **Give users a reason to open the app daily** without being annoying or interrupting the passive tracking experience.
