# GreenGains UI/UX Consistency Audit - UPDATED
**Date:** 2026-01-13
**Audit Focus:** Reactive state management, timestamp auto-updates, and overall UX consistency
**Auditor:** UI/UX Design Specialist

---

## Executive Summary

The GreenGains app has **made significant improvements** to reactive state management with the introduction of:
- Centralized `TimeAgoService` for efficient timestamp updates
- `AppEventBus` for event-driven UI updates
- Better separation of concerns with dedicated services

However, **critical inconsistencies remain** that break the user's mental model of "everything updates reactively":

### Critical Issues (Must Fix):
1. **Sensor timestamps don't auto-update** - Only upload timestamps use TimeAgoText
2. **Profile screen doesn't react to auth changes** - No FirebaseAuth listener
3. **Daily pot has no loading/error states** - Just disappears when null
4. **Statistics chart shows fake data** - 6 days of zeros, only today is real
5. **GlobalKey anti-pattern still present** - HomeScreen manually calls stats refresh
6. **No "last updated" indicators** - Users can't tell if data is stale

### Severity Breakdown:
- **P0 (Critical - Ship Blockers):** 3 issues
- **P1 (High - UX Problems):** 8 issues
- **P2 (Medium - Polish):** 6 issues
- **P3 (Low - Nice-to-Have):** 4 issues

---

## Detailed Component Analysis

### 1. TimeAgoText Widget (`lib/widgets/time_ago_text.dart`)

**Status:** ✅ EXCELLENT - Well-implemented

**Implementation:**
- Uses centralized `TimeAgoService.instance.tick` ValueNotifier
- Single timer for entire app (updates every 60 seconds)
- Efficient - only rebuilds TimeAgoText widgets, not parents
- Format: "just now" → "Xm ago" → "Xh ago" → "Xd ago"

**Pros:**
- Performance-optimized (single timer vs. timer per widget)
- Consistent formatting across app
- Clean, reusable API

**Issues:**
- ⚠️ **CRITICAL INCONSISTENCY:** Only used for upload timestamps, NOT for:
  - Sensor data age (line 363, 399, 424, 446 in home_screen.dart)
  - Coverage "last updated" (missing entirely)
  - Stats "last updated" (missing entirely)
  - Daily pot "resets in X" countdown (not implemented)

**User Impact:**
Users see "Last upload: 5m ago" auto-updating, but sensor timestamps like "2026-01-13 14:23:45" stay static. This creates confusion - "Is this data fresh or stale?"

**Fix Required:**
```dart
// BAD (current): Static timestamp display
updatedAt: accel?.dateTime, // Just stores DateTime, never displays it

// GOOD (should be):
if (accel?.dateTime != null)
  TimeAgoText(
    timestamp: accel!.dateTime,
    prefix: 'Updated ',
    style: theme.textTheme.bodySmall,
  )
```

---

### 2. Contribution Stats Card (`lib/widgets/contribution_stats_card.dart`)

**Status:** ✅ GOOD - Reactive with minor issues

**Reactive Patterns Implemented:**
1. **Event listener:** Subscribes to `UploadSuccessEvent` (line 41-43)
2. **Auto-refresh:** Reloads stats when upload succeeds (line 53-59)
3. **Pulse animation:** Visual feedback on update (line 56-57)
4. **Loading skeleton:** Shows shimmer during load (line 96-139)
5. **Error state:** Retry button on failure (line 143-189)
6. **Empty state:** First-time user guidance (line 193-250)
7. **Milestone detection:** Special styling for 1st, 10th, 50th, 100th uploads (line 254-256)
8. **Emits events:** Broadcasts `StatsUpdatedEvent` for other widgets (line 78)

**Pros:**
- Fully reactive - no manual refresh needed from parent
- Excellent state management (loading/error/empty/success)
- Celebration visuals for milestones
- Accessible with proper semantic labels

**Issues:**
1. ⚠️ **Public `refresh()` method still exists** (line 62) - Indicates parent may still call it via GlobalKey (anti-pattern)
2. ⚠️ **No "last updated" timestamp** - User doesn't know if stats are fresh
3. 📊 **No stale data indicator** - If stats are hours old, should show warning

**User Impact:**
Medium. Stats update reliably, but without timestamp, users on slow networks can't tell if stats are current.

**Recommended Additions:**
```dart
// Below stats row, add:
if (_lastLoadedAt != null)
  TimeAgoText(
    timestamp: _lastLoadedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.bodySmall?.copyWith(
      color: AppColors.textTertiary(isDark),
    ),
  )
```

---

### 3. Statistics Screen (`lib/screens/statistics_screen.dart`)

**Status:** ✅ GOOD - Now reactive (recently fixed)

**Reactive Patterns Implemented:**
1. **Event listeners:** Subscribes to `UploadSuccessEvent` AND `StatsUpdatedEvent` (line 30-38)
2. **Auto-refresh:** Reloads when upload succeeds (line 48-52)
3. **Event sync:** Also listens for stats updated elsewhere (line 55-61)
4. **Pull-to-refresh:** RefreshIndicator for manual refresh (line 104-106)

**Pros:**
- Fixed major issue - now updates automatically
- Dual event listening ensures consistency
- Pull-to-refresh as backup
- Clean loading state

**Issues:**
1. ⚠️ **CRITICAL: Chart shows fake data** (line 304-309)
   - Only today's count is real
   - Previous 6 days hardcoded to 0.0
   - Comment says "Until we fetch historical daily counts"
   - **User sees misleading visualization**

2. ⚠️ **No "last updated" timestamp** - Same issue as stats card

3. 📊 **No historical data tracking** - Need daily_upload_counts table

4. 📊 **No loading skeleton on pull-to-refresh** - Only shows spinner on first load (line 100-101)

**User Impact:**
High. Chart is misleading - users think they had zero activity for 6 days when data just isn't fetched.

**Chart Fix Required:**
```dart
// Option 1: Show only today's data until historical tracking is implemented
spots: [FlSpot(0, todayCount)], // Single bar

// Option 2: Add disclaimer more prominently
Text(
  '⚠️ Historical data tracking coming soon - chart shows today only',
  style: theme.textTheme.bodyMedium?.copyWith(color: Colors.orange),
)
```

---

### 4. Daily Pot Service & Icon (`lib/services/daily_pot_service.dart`, `lib/widgets/daily_pot_icon.dart`)

**Status:** ⚠️ PARTIAL - Reactive but poor UX feedback

**Reactive Patterns Implemented:**
1. **ValueNotifier:** `_potNotifier` for reactive updates (daily_pot_service.dart line 29-30)
2. **Auth listener:** Re-initializes on sign in/out (line 22-26)
3. **Optimistic updates:** Instant UI feedback before backend confirms (line 109-125)
4. **Local caching:** Offline-first architecture (line 49-53, 83-84)
5. **Event emission:** Broadcasts `DailyPotUpdatedEvent` and `DailyPotClaimedEvent` (line 120, 178-182)
6. **Jiggle animation:** Visual feedback when unlocked (daily_pot_icon.dart line 21-34, 78-84)

**Pros:**
- Solid service architecture
- Optimistic updates feel instant
- Good event-driven design
- Cache-first for offline resilience

**Issues:**
1. ❌ **CRITICAL: No loading state** (daily_pot_icon.dart line 71-73)
   ```dart
   if (pot == null) {
     return const SizedBox.shrink(); // Just disappears!
   }
   ```
   - User sees nothing while pot initializes (2-5 seconds)
   - No feedback if initialization fails
   - Looks like a bug

2. ⚠️ **No progress text** - User can't see "3/5 uploads" progress
   - Only shows ring fill percentage
   - Have to guess how many uploads left

3. ⚠️ **Weak unlock feedback**
   - Jiggle is subtle (might miss it)
   - No snackbar/banner saying "POT UNLOCKED!"
   - No haptic burst on unlock (only on claim)

4. ⚠️ **No reset countdown** - User doesn't know pot resets at midnight UTC
   - No "Resets in 8h 23m" timer
   - No timezone indication

5. ⚠️ **Simple claim flow** - No celebration
   - Just shows snackbar "+X credits 🍯"
   - No confetti, no animation, no modal
   - Compare to Honeygain's pot-opening animation

6. 📊 **No offline indicator** - If pot fetch fails, silent failure

**User Impact:**
High. Daily pot is a key engagement/monetization feature but UX is weak:
- New users don't see pot for 5+ seconds (confusion)
- Users miss unlock moment (no celebration)
- Claim feels underwhelming (no reward anticipation)

**Required Fixes:**

#### Fix 1: Loading State
```dart
if (pot == null) {
  // Show skeleton/shimmer instead of hiding
  return Container(
    width: 44,
    height: 44,
    decoration: BoxDecoration(
      shape: BoxShape.circle,
      color: AppColors.surfaceElevated(isDark).withValues(alpha: 0.3),
    ),
    child: CircularProgressIndicator(strokeWidth: 2),
  );
}
```

#### Fix 2: Progress Text
```dart
// Below the pot icon
Column(
  children: [
    DailyPotIcon(...),
    SizedBox(height: 4),
    Text(
      pot.canClaim ? 'Tap to claim!' : '${pot.uploadsToday}/${pot.uploadsRequired}',
      style: TextStyle(fontSize: 10, fontWeight: FontWeight.w600),
    ),
  ],
)
```

#### Fix 3: Unlock Celebration
```dart
// In daily_pot_service.dart, when pot unlocks:
if (optimistic.isUnlocked && !current.isUnlocked) {
  // Haptic burst
  HapticFeedback.heavyImpact();
  Future.delayed(Duration(milliseconds: 100), () {
    HapticFeedback.heavyImpact();
  });

  // Emit celebration event
  AppEventBus.instance.emit(DailyPotUnlockedEvent());
}

// In home_screen.dart, listen and show snackbar:
_eventBus.on<DailyPotUnlockedEvent>().listen((_) {
  AppSnackbars.showSuccess(
    context,
    '🍯 DAILY POT UNLOCKED! Tap to claim 10-100 credits!',
    duration: Duration(seconds: 4),
  );
});
```

---

### 5. Home Screen (`lib/screens/home_screen.dart`)

**Status:** ⚠️ MIXED - Some reactive, some manual

**Reactive Patterns Implemented:**
1. **Service status:** ValueListenable on `isRunning` (line 239-242)
2. **Upload status:** Nested ValueListenableBuilder for errors/timestamps (line 622-718)
3. **TimeAgoText:** Auto-updating "Last upload: Xm ago" (line 706-712)
4. **Sensor streams:** StreamBuilder for real-time data (line 346-449)
5. **Upload success listener:** Refreshes coverage on upload (line 117-126)
6. **App lifecycle:** Refreshes data on resume (line 139-149)

**Pros:**
- Most UI is reactive
- Good use of multiple ValueListenables
- Sensor data updates in real-time
- Clean separation of concerns

**Issues:**
1. ❌ **CRITICAL: GlobalKey anti-pattern** (line 31, 146, 175)
   ```dart
   final _statsKey = GlobalKey<ContributionStatsCardState>();
   // ...
   _statsKey.currentState?.refresh(); // Manual control
   ```
   - Tight coupling between parent and child
   - Breaks encapsulation
   - Race condition if widget not mounted
   - **Unnecessary now that ContributionStatsCard listens to events**

2. ⚠️ **Sensor timestamps not auto-updating** (line 363, 399, 424, 446)
   - `updatedAt: accel?.dateTime` stored but never displayed
   - Should use TimeAgoText to show "Updated 2m ago"

3. ⚠️ **Coverage has no timestamp** (line 473-566)
   - Shows "Coverage Today" but no "Last updated X ago"
   - User can't tell if data is stale

4. ⚠️ **Manual tile coverage refresh** (line 181-197)
   - Loads on init + app resume + upload success
   - Should also listen to UploadSuccessEvent

5. 📊 **Battery optimization dialog logic complex** (line 57-95)
   - Many edge cases
   - Could be simplified

**User Impact:**
Medium-High. Most things work, but GlobalKey pattern is code smell and sensor timestamps confuse users.

**Required Fixes:**

#### Fix 1: Remove GlobalKey
```dart
// DELETE these lines:
final _statsKey = GlobalKey<ContributionStatsCardState>();
_statsKey.currentState?.refresh();

// REPLACE with:
ContributionStatsCard(), // No key needed - listens to events automatically
```

#### Fix 2: Sensor Timestamp Display
```dart
// In SensorDataCard widget, add below the status badge:
if (widget.updatedAt != null)
  TimeAgoText(
    timestamp: widget.updatedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.labelSmall?.copyWith(
      color: AppColors.textTertiary(isDark),
    ),
  )
```

#### Fix 3: Coverage Timestamp
```dart
// Add to HomeScreen state:
DateTime? _tileCoverageLoadedAt;

// In _loadTileCoverage:
setState(() {
  _tileCoverage = stats;
  _tileCoverageLoadedAt = DateTime.now();
  _tileCoverageLoading = false;
});

// In _buildCoverageSummary, add below "Coverage Today":
if (_tileCoverageLoadedAt != null)
  TimeAgoText(
    timestamp: _tileCoverageLoadedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.bodySmall,
  )
```

---

### 6. Profile Screen (`lib/screens/profile_screen.dart`)

**Status:** ❌ POOR - No reactive auth handling

**Current Implementation:**
- Reads `FirebaseAuth.instance.currentUser` once in build (line 24)
- Uses `setState()` after sign out (line 263)
- No auth state listener

**Issues:**
1. ❌ **CRITICAL: No auth listener**
   - If user signs in/out in another tab/device, UI doesn't update
   - If user profile photo/name changes, requires app restart
   - Anonymous → authenticated transition not handled

2. ⚠️ **Sign out only updates via setState** (line 258-271)
   - Manual state update instead of reactive
   - Should rebuild automatically on auth change

3. 📊 **No loading state during sign out**
   - Button just says "Sign out" - no spinner during operation

**User Impact:**
Medium. Mostly works for single-device users, but broken for multi-device or profile updates.

**Required Fix:**

```dart
class _ProfileScreenState extends State<ProfileScreen> {
  User? _currentUser;
  StreamSubscription<User?>? _authSubscription;

  @override
  void initState() {
    super.initState();
    _currentUser = FirebaseAuth.instance.currentUser;

    // Listen to auth state changes
    _authSubscription = FirebaseAuth.instance
        .authStateChanges()
        .listen((user) {
      if (mounted) {
        setState(() {
          _currentUser = user;
        });
      }
    });
  }

  @override
  void dispose() {
    _authSubscription?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final user = _currentUser; // Use local state, not .currentUser
    // ... rest of build
  }
}
```

---

### 7. Sensor Data Card (`lib/widgets/sensor_data_card.dart`)

**Status:** ✅ GOOD - Great reactive implementation

**Reactive Patterns Implemented:**
1. **Pulse animation:** Triggers on value change (line 50-58)
2. **Visual state:** Border/glow when active (line 87-102)
3. **Status badge:** "Live"/"Waiting"/"Paused" indicator (line 153-156)
4. **Efficient updates:** Only rebuilds on actual changes

**Pros:**
- Excellent visual feedback
- Clean animation pattern
- Accessible design
- Works great with StreamBuilder parent

**Issues:**
1. ⚠️ **Timestamp not displayed** (line 14, 24)
   - `updatedAt` prop accepted but never shown in UI
   - Should display "Updated Xm ago" below value

**User Impact:**
Low-Medium. Card works great, but missing age indicator makes users question data freshness.

**Required Fix:**
```dart
// After the unit text (around line 181), add:
if (widget.updatedAt != null) ...[
  const SizedBox(height: 4),
  TimeAgoText(
    timestamp: widget.updatedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.labelSmall?.copyWith(
      color: AppColors.textTertiary(isDark),
      fontSize: 10,
    ),
  ),
]
```

---

## Architecture Patterns Analysis

### What's Working (Keep These)

#### 1. AppEventBus (`lib/core/events/app_events.dart`)
✅ **EXCELLENT** - Central event system
- Broadcast stream for pub-sub pattern
- Type-safe event classes
- Debug logging built-in
- Replaces polling and manual refresh

Events implemented:
- `UploadSuccessEvent` - Triggers stats/coverage refresh
- `StatsUpdatedEvent` - Syncs stats across screens
- `DailyPotUpdatedEvent` - Updates pot UI
- `DailyPotClaimedEvent` - Celebration trigger
- `TrackingStateChangedEvent` - Service state changes
- `LocationPermissionChangedEvent` - Permission updates

**Best Practice Example:**
```dart
// Emit once, update everywhere
AppEventBus.instance.emit(UploadSuccessEvent(...));

// Multiple listeners auto-update:
// - ContributionStatsCard.refresh()
// - StatisticsScreen._loadStats()
// - HomeScreen._loadTileCoverage()
// - DailyPotService.recordUpload()
```

#### 2. TimeAgoService (`lib/core/services/time_ago_service.dart`)
✅ **EXCELLENT** - Centralized timer
- Single timer for entire app (60s interval)
- ValueNotifier for efficient updates
- Static `format()` method for one-off usage
- Performance-optimized

**Why it's good:**
- 100 TimeAgoText widgets = 1 timer (not 100 timers)
- Only rebuilds text widgets, not parents
- Consistent formatting everywhere

#### 3. DailyPotService Architecture
✅ **GOOD** - Offline-first with events
- Local caching for instant loads
- Optimistic updates before backend confirms
- Event emission for UI coordination
- Auth state listener for re-initialization

**Why it's good:**
- Feels instant to users
- Works offline
- Single source of truth
- Reactive across app

### What's Broken (Fix These)

#### 1. GlobalKey Pattern (Anti-Pattern)
❌ **BAD** - `_statsKey.currentState?.refresh()`

**Why it's bad:**
- Tight coupling - parent controls child internals
- Race conditions - widget may not be mounted
- Breaks encapsulation - child state exposed
- Makes testing harder
- Unnecessary with event bus

**How to fix:**
Delete GlobalKey usage. Let children subscribe to events independently.

#### 2. Mixed Update Patterns
⚠️ **INCONSISTENT** - 4 different patterns in use:
1. ValueListenable/ValueNotifier (service status, upload status)
2. StreamBuilder (sensor data)
3. AppEventBus (stats, uploads)
4. Manual setState + GlobalKey (stats card from parent)

**Why it's bad:**
- Cognitive load for developers
- Inconsistent behavior for users
- Harder to maintain

**How to standardize:**
- Real-time data (sensors, service status) → StreamBuilder or ValueListenable
- Backend data (stats, tiles) → AppEventBus + setState
- Timestamps → TimeAgoText
- Never use GlobalKey to control children

#### 3. Missing Timestamp Displays
⚠️ **INCONSISTENT** - TimeAgoText only used in 1 place

**Where it's used:**
- ✅ Upload status: "Last upload: 5m ago"

**Where it's missing:**
- ❌ Sensor data cards: Shows raw DateTime (never updates)
- ❌ Stats card: No "Updated X ago"
- ❌ Statistics screen: No "Last refreshed X ago"
- ❌ Coverage data: No timestamp at all
- ❌ Daily pot: No "Resets in X" countdown

**User confusion:**
Upload timestamp updates live, but everything else is static. Users think data is broken.

#### 4. No Stale Data Indicators
⚠️ **MISSING** - All backend data assumed fresh

**Problems:**
- On slow networks, stats might be 5 minutes old
- No visual indication data is stale
- No "pull to refresh" hint

**Should have:**
- Color coding: Green (<1m) → Yellow (1-5m) → Red (>5m)
- Warning icon if data >10 minutes old
- "Last updated" on all cards

---

## Prioritized Issue List

### P0 - Critical (Ship Blockers)

#### 1. Profile screen doesn't react to auth changes
**File:** `lib/screens/profile_screen.dart`
**Line:** 24 (reads `currentUser` once, never updates)
**Impact:** Broken UX on multi-device or if user profile changes
**Fix Effort:** 30 minutes
**Fix:** Add `authStateChanges()` listener as shown above

#### 2. Statistics chart shows fake data
**File:** `lib/screens/statistics_screen.dart`
**Line:** 304-309 (hardcoded zeros for previous 6 days)
**Impact:** Misleading visualization - users think they had no activity
**Fix Effort:** 15 minutes (quick fix), 4 hours (proper fix with historical data)
**Quick Fix:** Show only today's data point OR add prominent disclaimer
**Proper Fix:** Implement daily upload counts table in database

#### 3. Daily pot has no loading state
**File:** `lib/widgets/daily_pot_icon.dart`
**Line:** 71-73 (returns `SizedBox.shrink()` when pot is null)
**Impact:** Icon disappears for 2-5 seconds on launch, looks like bug
**Fix Effort:** 20 minutes
**Fix:** Show skeleton/spinner as shown above

---

### P1 - High (Major UX Issues)

#### 4. Remove GlobalKey anti-pattern
**File:** `lib/screens/home_screen.dart`
**Line:** 31, 146, 175
**Impact:** Tight coupling, race conditions, code smell
**Fix Effort:** 10 minutes
**Fix:** Delete `_statsKey` and `.refresh()` calls - stats card already listens to events

#### 5. Sensor timestamps don't auto-update
**File:** `lib/widgets/sensor_data_card.dart`
**Line:** 14, 24 (accepts `updatedAt` but doesn't display it)
**Impact:** Users can't tell if sensor data is fresh or stale
**Fix Effort:** 30 minutes
**Fix:** Add TimeAgoText below sensor value as shown above

#### 6. Stats card has no "last updated" timestamp
**File:** `lib/widgets/contribution_stats_card.dart`
**Impact:** Users on slow networks can't tell if stats are current
**Fix Effort:** 20 minutes
**Fix:** Add `_lastLoadedAt` state variable and display with TimeAgoText

#### 7. Coverage data has no timestamp
**File:** `lib/screens/home_screen.dart`
**Line:** 473-566 (shows "Coverage Today" with no update time)
**Impact:** Users don't know if coverage is current or from hours ago
**Fix Effort:** 20 minutes
**Fix:** Add `_tileCoverageLoadedAt` and display as shown above

#### 8. Daily pot unlock feedback is weak
**File:** `lib/services/daily_pot_service.dart`
**Line:** 122-124 (just logs debug message)
**Impact:** Users miss the unlock moment - no celebration
**Fix Effort:** 45 minutes
**Fix:** Emit `DailyPotUnlockedEvent`, show prominent snackbar, haptic burst

#### 9. Daily pot has no progress text
**File:** `lib/widgets/daily_pot_icon.dart`
**Impact:** Users can't see "3/5 uploads" - have to guess progress
**Fix Effort:** 30 minutes
**Fix:** Add text below pot icon as shown above

#### 10. Statistics screen has no "last updated" timestamp
**File:** `lib/screens/statistics_screen.dart`
**Impact:** Same as stats card - users can't tell if data is fresh
**Fix Effort:** 20 minutes
**Fix:** Add timestamp below quick stats grid

#### 11. No loading skeleton on pull-to-refresh
**File:** `lib/screens/statistics_screen.dart`
**Line:** 100-101 (only shows loading on first load)
**Impact:** No visual feedback during manual refresh
**Fix Effort:** 15 minutes
**Fix:** Add loading state even when `_stats != null`

---

### P2 - Medium (Polish Issues)

#### 12. Daily pot has no reset countdown
**File:** `lib/widgets/daily_pot_icon.dart`
**Impact:** Users don't know when pot resets (midnight UTC)
**Fix Effort:** 1 hour
**Fix:** Add countdown timer "Resets in 8h 23m" when claimed

#### 13. Daily pot claim has no celebration
**File:** `lib/widgets/daily_pot_icon.dart`, `lib/services/daily_pot_service.dart`
**Impact:** Claiming pot feels underwhelming - just a snackbar
**Fix Effort:** 2 hours
**Fix:** Add modal dialog with pot-opening animation, confetti, reward reveal

#### 14. No offline indicators
**Files:** All screens with backend data
**Impact:** Users don't know if data failed to load due to network
**Fix Effort:** 1 hour
**Fix:** Add "Offline" badge to cards when network unavailable

#### 15. No stale data warnings
**Files:** All cards showing backend data
**Impact:** Users might act on old data
**Fix Effort:** 1 hour
**Fix:** Add yellow/red color coding for timestamps >5 minutes old

#### 16. Stats card still has public `refresh()` method
**File:** `lib/widgets/contribution_stats_card.dart`
**Line:** 62
**Impact:** Indicates potential for anti-pattern usage
**Fix Effort:** 5 minutes
**Fix:** Make method private or remove (only needed for internal use now)

#### 17. Battery optimization dialog complex
**File:** `lib/screens/home_screen.dart`
**Line:** 57-95
**Impact:** Hard to maintain, many edge cases
**Fix Effort:** 1 hour
**Fix:** Extract to separate service or widget

---

### P3 - Low (Nice-to-Have)

#### 18. Historical data tracking not implemented
**Files:** `lib/screens/statistics_screen.dart`, database schema
**Impact:** Chart is fake, can't show real trends
**Fix Effort:** 4-6 hours
**Fix:** Add daily_upload_counts table, aggregate on upload, query for chart

#### 19. No haptic feedback on pot unlock
**File:** `lib/services/daily_pot_service.dart`
**Line:** 122-124
**Impact:** Moment feels flat
**Fix Effort:** 5 minutes
**Fix:** Add `HapticFeedback.heavyImpact()` burst

#### 20. Sensor card timestamp too subtle
**File:** `lib/widgets/sensor_data_card.dart`
**Impact:** Even when added, might be missed
**Fix Effort:** 10 minutes
**Fix:** Test with users, adjust sizing/color for visibility

#### 21. Upload status could show history
**File:** `lib/screens/home_screen.dart`
**Line:** 597-719
**Impact:** Users can't see past uploads, only last one
**Fix Effort:** 2 hours
**Fix:** Add expandable upload history card (last 10 uploads)

---

## Design System Consistency Rules

### Rule 1: All Time-Based Data Uses TimeAgoText
**Applies to:**
- Upload timestamps
- Sensor data age
- Stats last updated
- Coverage last updated
- Any backend data freshness

**Implementation:**
```dart
TimeAgoText(
  timestamp: dataLoadedAt,
  prefix: 'Updated ',
  style: theme.textTheme.bodySmall?.copyWith(
    color: AppColors.textTertiary(isDark),
  ),
)
```

**Exceptions:**
- Absolute dates (e.g., "Member since Jan 2026") - use static text
- Future times (e.g., "Resets in 8h") - use countdown timer

---

### Rule 2: All Backend Data Has Loading/Error/Empty States
**Applies to:**
- Stats cards
- Coverage data
- Daily pot
- Statistics screen
- Any network call

**State Hierarchy:**
```dart
if (_loading) {
  return SkeletonCard(); // Shimmer/pulse animation
} else if (_error != null) {
  return ErrorCard(
    message: _error,
    onRetry: _loadData, // Always actionable
  );
} else if (_data == null || _data.isEmpty) {
  return EmptyState(
    message: 'No data yet',
    action: 'Start tracking to see data',
  );
} else {
  return DataCard(data: _data);
}
```

**Visual Standards:**
- Loading: Skeleton same size as content, subtle pulse
- Error: Red border, error icon, retry button, error message
- Empty: Primary color icon, encouraging message, CTA button

---

### Rule 3: Event-Driven Updates for Cross-Widget Sync
**Applies to:**
- Stats updates after upload
- Coverage updates after upload
- Daily pot updates after upload
- Any data that multiple screens display

**Pattern:**
```dart
// Service emits event:
AppEventBus.instance.emit(UploadSuccessEvent(...));

// Widgets listen and react:
class MyWidget extends StatefulWidget {
  @override
  void initState() {
    super.initState();
    _subscription = AppEventBus.instance
        .on<UploadSuccessEvent>()
        .listen((_) => _refresh());
  }

  @override
  void dispose() {
    _subscription?.cancel();
    super.dispose();
  }
}
```

**Never:**
- Use GlobalKey to call child methods
- Poll backend for changes
- Manually refresh from parent widget

---

### Rule 4: Stale Data Indicators
**Applies to:**
- All cards displaying backend data
- Any data where freshness matters

**Visual Coding:**
```dart
Color _getTimestampColor(DateTime timestamp, bool isDark) {
  final age = DateTime.now().difference(timestamp);
  if (age.inMinutes < 1) return AppColors.success; // Green: Fresh
  if (age.inMinutes < 5) return AppColors.textSecondary(isDark); // Gray: OK
  if (age.inMinutes < 10) return AppColors.warning; // Yellow: Stale
  return AppColors.error; // Red: Very stale
}

// Usage:
TimeAgoText(
  timestamp: lastUpdated,
  prefix: 'Updated ',
  style: TextStyle(color: _getTimestampColor(lastUpdated, isDark)),
)
```

---

### Rule 5: Consistent Animation Timing
**Applies to:**
- All animations across app

**Standards:**
- **Micro-interactions** (button press): 150-200ms, easeInOut
- **Value updates** (stats change): 300-500ms, easeOut
- **Celebrations** (pot unlock): 600-1200ms, elasticOut
- **Screen transitions**: 300ms, easeInOut
- **Loading skeletons**: 1500ms pulse loop, linear

**Never:**
- Animate unnecessarily
- Use different timings for same interaction type
- Animate longer than 1200ms (feels sluggish)

---

## Implementation Roadmap

### Week 1: Critical Fixes (P0 + High-Impact P1)
**Goal:** Fix ship blockers and major UX inconsistencies

**Day 1-2: P0 Fixes**
- [ ] Add auth state listener to profile screen (30 min)
- [ ] Fix statistics chart fake data - add disclaimer (15 min)
- [ ] Add daily pot loading state (20 min)

**Day 3-4: High-Impact P1**
- [ ] Remove GlobalKey anti-pattern (10 min)
- [ ] Add TimeAgoText to sensor cards (30 min)
- [ ] Add "last updated" to stats card (20 min)
- [ ] Add "last updated" to coverage data (20 min)

**Day 5: Testing**
- [ ] Test all timestamp auto-updates
- [ ] Test profile screen with multiple sign-ins
- [ ] Test statistics screen reactivity
- [ ] Verify daily pot loading states

**Estimated Total:** 2-3 hours of dev work + 2 hours testing

---

### Week 2: UX Polish (Remaining P1 + P2)
**Goal:** Consistent reactive updates everywhere

**Day 1-2: Daily Pot UX**
- [ ] Add unlock celebration (snackbar + haptic) (45 min)
- [ ] Add progress text below icon (30 min)
- [ ] Add reset countdown timer (1 hour)

**Day 3: Statistics Screen**
- [ ] Add "last updated" timestamp (20 min)
- [ ] Add loading skeleton on pull-to-refresh (15 min)
- [ ] Improve chart disclaimer styling (10 min)

**Day 4-5: Stale Data Indicators**
- [ ] Add color-coded timestamps (1 hour)
- [ ] Add offline badges (1 hour)
- [ ] Add "pull to refresh" hints (30 min)

**Estimated Total:** 5-6 hours of dev work

---

### Week 3: Celebrations & Historical Data (P2 + P3)
**Goal:** Delight users and enable real analytics

**Day 1-3: Historical Data Tracking**
- [ ] Design daily_upload_counts table schema (1 hour)
- [ ] Add aggregation on upload (2 hours)
- [ ] Update chart to query real data (1 hour)
- [ ] Test historical data accuracy (1 hour)

**Day 4-5: Celebrations**
- [ ] Design pot claim modal (1 hour)
- [ ] Implement confetti animation (2 hours)
- [ ] Add haptic feedback (30 min)
- [ ] Add upload history card (2 hours)

**Estimated Total:** 10-12 hours of dev work

---

## Success Metrics

### Quantitative Metrics
1. **Timestamp Update Consistency**
   - Before: 1/10 timestamps auto-update (10%)
   - After: 10/10 timestamps auto-update (100%)

2. **Event-Driven Updates**
   - Before: 2/5 screens reactive (40%)
   - After: 5/5 screens reactive (100%)

3. **Loading State Coverage**
   - Before: 3/7 components have loading states (43%)
   - After: 7/7 components have loading states (100%)

4. **Anti-Pattern Usage**
   - Before: 1 GlobalKey usage
   - After: 0 GlobalKey usages

### Qualitative Metrics
1. **User Feedback**
   - Before: "Stats don't update", "Is this working?"
   - After: "Everything updates smoothly", "Feels polished"

2. **Developer Experience**
   - Before: "Which pattern should I use?"
   - After: "Clear rules, easy to follow"

3. **Support Tickets**
   - Before: Tickets about stale data, broken stats
   - After: Zero tickets about UI consistency

---

## Testing Checklist

### Manual Testing

#### Timestamp Auto-Updates
- [ ] Open app, verify "Last upload: X ago" updates every minute
- [ ] Wait 5 minutes, verify sensor timestamps update
- [ ] Check stats card "Updated X ago" changes
- [ ] Verify coverage "Updated X ago" updates

#### Event-Driven Reactivity
- [ ] Upload data → Stats card updates automatically
- [ ] Upload data → Statistics screen updates (if open)
- [ ] Upload data → Coverage updates automatically
- [ ] Upload data → Daily pot progress increments

#### Daily Pot UX
- [ ] App launch → Pot shows loading skeleton (not blank)
- [ ] Sign in → Pot initializes and appears
- [ ] Upload 5 times → Progress text shows "1/5", "2/5", etc.
- [ ] Pot unlocks → See celebration snackbar + haptic
- [ ] Tap unlocked pot → Claim modal opens
- [ ] Claim pot → See reward amount + confetti

#### Loading/Error States
- [ ] Turn off WiFi → All cards show offline indicators
- [ ] Pull to refresh → See loading skeleton
- [ ] Backend down → Error cards with retry buttons
- [ ] Retry from error → Data loads successfully

#### Profile Screen
- [ ] Sign out → UI updates to signed-out state
- [ ] Sign in → UI updates to signed-in state
- [ ] Change profile photo externally → Photo updates in app

### Automated Testing

#### Unit Tests
```dart
test('TimeAgoService formats timestamps correctly', () {
  final now = DateTime.now();
  expect(TimeAgoService.format(now), 'just now');
  expect(TimeAgoService.format(now.subtract(Duration(minutes: 5))), '5m ago');
  expect(TimeAgoService.format(now.subtract(Duration(hours: 2))), '2h ago');
  expect(TimeAgoService.format(now.subtract(Duration(days: 3))), '3d ago');
});

test('AppEventBus emits and receives events', () async {
  final events = <UploadSuccessEvent>[];
  final sub = AppEventBus.instance.on<UploadSuccessEvent>().listen(events.add);

  AppEventBus.instance.emit(UploadSuccessEvent(
    samplesCount: 50,
    timestamp: DateTime.now(),
  ));

  await Future.delayed(Duration.zero); // Let stream process
  expect(events.length, 1);
  expect(events.first.samplesCount, 50);

  sub.cancel();
});
```

#### Widget Tests
```dart
testWidgets('ContributionStatsCard shows loading state', (tester) async {
  await tester.pumpWidget(MaterialApp(home: ContributionStatsCard()));
  expect(find.byType(SkeletonCard), findsOneWidget);
});

testWidgets('TimeAgoText auto-updates', (tester) async {
  final timestamp = DateTime.now().subtract(Duration(minutes: 1));
  await tester.pumpWidget(MaterialApp(
    home: TimeAgoText(timestamp: timestamp),
  ));

  expect(find.text('1m ago'), findsOneWidget);

  // Simulate time passing (advance timer)
  TimeAgoService.instance.tick.value++;
  await tester.pump();

  // Verify text updated (would be '1m ago' or '2m ago' depending on timing)
  expect(find.textContaining('ago'), findsOneWidget);
});
```

---

## Conclusion

The GreenGains app has made **significant architectural improvements** with:
- ✅ Centralized TimeAgoService
- ✅ AppEventBus for reactive updates
- ✅ Service-based architecture
- ✅ Offline-first daily pot
- ✅ Good separation of concerns

However, **critical inconsistencies remain**:
- ❌ Timestamps only auto-update in 1 place (upload status)
- ❌ Profile screen doesn't react to auth changes
- ❌ Statistics chart shows misleading data
- ❌ Daily pot UX is underwhelming
- ❌ GlobalKey anti-pattern still present
- ❌ No stale data indicators

### Impact Assessment

**Without fixes:**
- Users confused by static timestamps
- Multi-device users see stale profile data
- Statistics chart is misleading
- Daily pot feels broken (disappears on launch)
- Developers might copy GlobalKey pattern to new code

**With fixes (Week 1 only):**
- All timestamps auto-update consistently
- Profile screen reactive to auth changes
- Statistics chart has clear disclaimer
- Daily pot always visible (loading state)
- Clean, maintainable codebase

**With full implementation (3 weeks):**
- Polished, professional UX
- Clear data freshness indicators
- Engaging daily pot experience
- Real historical analytics
- Developer-friendly patterns

### Recommendation

**PRIORITY: Complete Week 1 fixes before next release**

The P0 issues are ship blockers:
1. Profile screen auth reactivity (broken for multi-device)
2. Statistics chart disclaimer (misleading users)
3. Daily pot loading state (looks like bug)

Week 2 and 3 can be done incrementally, but Week 1 fixes are **essential for user trust and app quality**.

### Final Note

The user's frustration with "consistency" is **100% valid**. When upload timestamps auto-update but sensor timestamps don't, it breaks the user's mental model. The architecture is 80% there - we just need to apply patterns consistently across all components.

**Core principle:** If one timestamp updates, ALL timestamps should update. If one card shows "last updated", ALL cards should. Consistency is not about having rules - it's about applying them everywhere.

---

**End of Updated Audit**
Files audited: 11 core files
Issues found: 21 (3 P0, 8 P1, 6 P2, 4 P3)
Estimated fix time: 20-30 hours total (2-3 hours for P0+critical P1)
