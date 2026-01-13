# GreenGains UI/UX Consistency Audit
**Date:** 2026-01-13
**Problem:** User feedback: "consistency needed, no custom weird shit or band aids"

---

## Executive Summary

The GreenGains app has **inconsistent reactive update patterns** across screens and components. Some widgets auto-update via event listeners, others require manual refresh, and some don't update at all. This creates unpredictable user experience where users don't know if data is fresh or stale.

### Critical Issues Found:
1. **Inconsistent update mechanisms** - mix of event-driven, polling, and manual refresh
2. **Timestamps don't auto-update** - TimeAgoText updates every 30s but other timestamps are static
3. **Daily pot updates unreliably** - depends on navigation/lifecycle timing
4. **Stats refresh inconsistent** - sometimes auto-updates, sometimes requires pull-to-refresh
5. **No unified state feedback** - loading/error states vary by component

---

## Screen Inventory & Update Patterns

### 1. Home Screen (`home_screen.dart`)
**Data Displayed:**
- Service running status (Start/Stop button)
- Upload status (last upload time, uploading state, errors)
- Contribution stats card (total uploads, today, streak)
- Tile coverage (day/night tiles)
- Live sensor readings (GPS, light, accelerometer, gyroscope, barometer)
- Daily pot icon (progress ring)
- Contextual tips (dismissible)

**Update Mechanisms:**
- **Service status**: ValueListenable (real-time) ✓ GOOD
- **Upload status**: ValueListenable with nested builders ✓ GOOD
- **Stats card**: Auto-updates via AppEventBus.on<UploadSuccessEvent>() ✓ GOOD (NEW)
- **Tile coverage**: Manual refresh on upload success + app lifecycle resume ⚠️ PARTIAL
- **Sensor readings**: StreamBuilder (real-time) ✓ GOOD
- **Daily pot**: ValueListenable but depends on service calls ⚠️ PARTIAL
- **Last upload timestamp**: TimeAgoText (auto-updates every 30s) ✓ GOOD

**Inconsistencies:**
1. Tile coverage loads on init but may be stale - no indicator
2. Daily pot may not appear until explicit refresh
3. Upload timestamp refreshes but sensor timestamps don't (inconsistent TimeAgoText usage)

---

### 2. Profile Screen (`profile_screen.dart`)
**Data Displayed:**
- User info (avatar, name, member since date)
- Referral card (static code generation)
- Statistics navigation link
- Sign out button

**Update Mechanisms:**
- **User info**: Static from FirebaseAuth.currentUser (no listener) ⚠️ ISSUE
- **Member date**: Static text, no auto-formatting ✓ OK (doesn't change)
- **Referral code**: Computed on build ✓ OK

**Inconsistencies:**
1. User profile photo/name don't update if changed externally (requires app restart)
2. No auth state listener - if user signs out elsewhere, UI doesn't react

---

### 3. Statistics Screen (`statistics_screen.dart`)
**Data Displayed:**
- Quick stats grid (today, streak, total)
- 7-day activity trend chart
- Achievements placeholder
- Earnings placeholder

**Update Mechanisms:**
- **Stats**: Load on init only - requires manual pull-to-refresh ⚠️ ISSUE
- **Chart data**: Based on loaded stats (currently shows zeros for historical days) ⚠️ ISSUE
- **No event bus listener**: Stats don't auto-update when uploads succeed ❌ CRITICAL

**Inconsistencies:**
1. User uploads data → stats screen shows stale data until manual refresh
2. No loading skeleton on pull-to-refresh (only initial load)
3. Historical data not implemented (chart shows zeros)

---

### 4. Settings Screen (`settings_screen.dart`)
**Data Displayed:**
- Theme mode selector
- Mobile data toggle
- Legal links
- App version

**Update Mechanisms:**
- **Theme mode**: ListenableBuilder on ThemeController ✓ GOOD
- **Settings toggles**: setState() on change ✓ GOOD
- **Version**: Loaded once on init ✓ OK

**Inconsistencies:**
None - this screen is mostly static/settings-driven.

---

## Widget-Level Analysis

### Contribution Stats Card (`contribution_stats_card.dart`)
**Update Pattern:** ✓ GOOD (RECENTLY IMPROVED)
- Subscribes to `AppEventBus.on<UploadSuccessEvent>()`
- Auto-refreshes when uploads succeed
- Shows pulse animation on update
- Has loading skeleton and error state
- Parent can manually call `refresh()` via GlobalKey

**Issues:**
- Relies on GlobalKey from parent for manual refresh - could use event bus exclusively
- No stale data indicator (user doesn't know if stats are recent or from yesterday)

---

### Daily Pot Icon (`daily_pot_icon.dart`)
**Update Pattern:** ⚠️ PARTIAL
- Uses ValueListenable on DailyPotService.pot
- Jiggles when unlocked
- Auto-updates when pot state changes in service

**Issues:**
1. **Initialization timing**: DailyPotService.initialize() called on app start, but:
   - If user signs in after launch, may not re-initialize properly
   - Auth state listener exists but re-initialization is async (race conditions)
2. **No loading state**: Icon just doesn't appear if pot is null (confusing)
3. **No error feedback**: If pot fetch fails, user has no indication
4. **Offline behavior unclear**: What happens when offline? Does it show stale data?

---

### Time Ago Text (`time_ago_text.dart`)
**Update Pattern:** ✓ GOOD
- Self-contained Timer updates every 30 seconds
- No parent widget rebuilds needed

**Issues:**
1. **Inconsistent usage**: Only used for upload timestamps, not for:
   - Sensor data timestamps (shown as raw DateTime)
   - Coverage data (no timestamp shown)
   - Stats card (no "last updated" indicator)

---

### Sensor Data Cards (`sensor_data_card.dart`)
**Update Pattern:** ✓ GOOD
- StreamBuilder for real-time data
- Pulse animation on value change
- Status badges (Live/Waiting/Paused)

**Issues:**
1. **Timestamp display**: Shows DateTime but doesn't auto-format "time ago"
2. **No data age indicator**: Old sensor data looks the same as fresh data

---

### Service Control Button (`service_control_button.dart`)
**Update Pattern:** ✓ GOOD
- ValueListenableBuilder for real-time state
- Loading state during toggle
- Scale animation feedback

**Issues:**
None - well-implemented.

---

## Daily Pot UX Deep Dive

### Current Implementation Issues

1. **Inconsistent visibility**
   - Pot may not appear on first launch (race condition with auth)
   - Appears only after DailyPotService.initialize() completes
   - No placeholder or loading state

2. **Progress feedback unclear**
   - Progress ring shows fill percentage but no text label
   - User doesn't know "X/5 uploads needed"
   - No indication of when pot resets (midnight UTC?)

3. **Unlock feedback weak**
   - Jiggle animation is subtle (user may miss it)
   - No banner/snackbar when pot unlocks
   - No sound/haptic burst on unlock

4. **Claim flow confusing**
   - Tap to claim - no confirmation dialog
   - Success shown as snackbar only (easy to miss)
   - No celebration animation/confetti

5. **Offline behavior undefined**
   - What happens if user is offline when pot unlocks?
   - Does progress sync when back online?
   - No offline indicator

6. **Reset timing unclear**
   - User doesn't know pot resets at midnight UTC
   - No countdown to reset
   - No timezone conversion

### Honeygain Lucky Pot (Reference)
**What they do well:**
- Clear progress text: "3/5 daily tasks completed"
- Unlock notification: Banner + haptic feedback
- Claim animation: Pot opens with coins flying out
- Reset countdown: "Resets in 4h 23m"
- Offline handling: Progress syncs when back online
- Clear CTA: "Complete 2 more tasks to unlock"

### Recommended Improvements

#### 1. Enhanced Progress Display
```
[Daily Pot Icon with Ring]
Below icon: "3/5 uploads" (small text)
When unlocked: "Tap to claim!" (pulsing)
When claimed: Checkmark overlay
```

#### 2. Unlock Celebration
- Heavy haptic feedback burst
- Persistent snackbar: "Daily Pot Unlocked! Tap to claim 10-100 credits"
- Jiggle animation + glow effect
- Optional: Local notification if app is backgrounded

#### 3. Better Claim Flow
```
Tap pot → Show dialog:
  "Open Daily Pot?"
  "Claim your random reward: 10-100 credits"
  [Cancel] [Open Pot]

Success → Full-screen celebration:
  - Pot opening animation
  - "+X credits!" with growing text
  - Confetti effect
  - [Awesome!] button
```

#### 4. Reset Timer
```
Below pot icon (when claimed):
"Resets in 8h 45m"
(Use countdown timer, update every minute)
```

#### 5. Offline Handling
- Show "Offline" badge on pot if no network
- Progress still counts during offline
- Sync when back online (show "Syncing..." briefly)

#### 6. Loading/Error States
```
Loading: Skeleton circle with shimmer
Error: Gray pot with "!" badge → Tap to retry
Empty state: "Complete 5 uploads to unlock daily pot"
```

---

## Unified State Management Issues

### Current Approach (Mixed Patterns)
The app uses **3 different update patterns**:

1. **ValueListenable/ValueNotifier** (good for simple state)
   - Service running status
   - Upload status
   - Daily pot state

2. **StreamBuilder** (good for continuous data)
   - Sensor readings

3. **AppEventBus** (good for cross-widget updates)
   - Upload success triggers stats refresh
   - Recently added, not consistently used everywhere

4. **Manual setState + GlobalKey** (anti-pattern)
   - Parent calling child's refresh() method
   - Used in home_screen for stats card

### Problems This Causes

1. **Cognitive load**: Developers must remember which pattern to use where
2. **Race conditions**: Async initialization + multiple update paths
3. **Stale data**: Screens may not react to events from other screens
4. **No single source of truth**: Same data may exist in multiple places

### Recommended Architecture

**Single Event-Driven Pattern:**
```
User Action → Native/Backend → Event Emitted → All Listeners Update
```

**Example Flow:**
```
1. User uploads data
2. Native code calls onNativeUploadStatus('success')
3. ForegroundLocationService emits UploadSuccessEvent
4. Listeners respond:
   - ContributionStatsCard.refresh()
   - HomeScreen._loadTileCoverage()
   - DailyPotService.recordUpload()
   - StatisticsScreen.refresh() (if mounted)
5. UI updates reactively everywhere
```

---

## Consistency Rules (Design System)

### Rule 1: Real-Time Data = StreamBuilder or ValueListenable
**Applies to:**
- Service running status
- Sensor readings
- Upload progress

**Implementation:**
Always use reactive listeners, never poll or manual refresh.

---

### Rule 2: Backend Data = Event-Driven Refresh
**Applies to:**
- Contribution stats
- Tile coverage
- Daily pot state
- User profile data

**Implementation:**
1. Subscribe to relevant events in initState()
2. Refresh data when event fires
3. Show loading state during refresh
4. Handle errors with retry button

**Pattern:**
```dart
@override
void initState() {
  super.initState();
  _loadData();
  _subscription = AppEventBus.instance
    .on<DataChangedEvent>()
    .listen((_) => _loadData());
}

Future<void> _loadData() async {
  setState(() => _loading = true);
  try {
    final data = await _repository.getData();
    setState(() {
      _data = data;
      _loading = false;
    });
  } catch (e) {
    setState(() {
      _error = e.toString();
      _loading = false;
    });
  }
}
```

---

### Rule 3: Timestamps = Auto-Formatting
**Applies to:**
- Last upload time
- Sensor data age
- Coverage data freshness
- Stats last updated

**Implementation:**
Always use TimeAgoText widget or similar auto-updating component.

**Bad:**
```dart
Text('Updated: ${DateTime.now()}') // Static, never updates
```

**Good:**
```dart
TimeAgoText(
  timestamp: lastUpdated,
  prefix: 'Updated ',
)
```

---

### Rule 4: Loading States = Consistent Skeletons
**Applies to:**
All cards/components that load async data

**Implementation:**
```dart
if (_loading) {
  return SkeletonCard(); // Consistent shimmer/pulse
} else if (_error != null) {
  return ErrorCard(
    error: _error,
    onRetry: _loadData,
  );
} else {
  return DataCard(data: _data);
}
```

**Skeleton Design:**
- Same shape/size as loaded content
- Subtle pulse animation (not shimmer - too distracting)
- Neutral gray color (adapts to theme)

---

### Rule 5: Error States = Actionable Feedback
**Applies to:**
All network operations

**Requirements:**
1. Clear error message (user-friendly)
2. Retry button (always actionable)
3. Timestamp of failure
4. Optional: "View details" for debugging

**Pattern:**
```dart
Container(
  padding: EdgeInsets.all(16),
  decoration: BoxDecoration(
    color: Colors.red.withOpacity(0.1),
    border: Border.all(color: Colors.red),
    borderRadius: BorderRadius.circular(12),
  ),
  child: Row(
    children: [
      Icon(Icons.error_outline, color: Colors.red),
      SizedBox(width: 12),
      Expanded(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('Failed to load', style: boldStyle),
            Text(errorMessage, style: smallStyle),
          ],
        ),
      ),
      TextButton(
        onPressed: onRetry,
        child: Text('Retry'),
      ),
    ],
  ),
)
```

---

### Rule 6: Success Feedback = Clear but Non-Intrusive
**Applies to:**
- Upload success
- Pot claimed
- Settings saved

**Implementation Hierarchy:**
1. **Minor success** (expected): Status icon change only
2. **Standard success** (important): Snackbar for 2 seconds
3. **Major success** (celebration): Modal/dialog with animation

**Examples:**
- Settings toggled → Icon changes, no snackbar
- Upload complete → Green snackbar "Upload successful"
- Pot claimed → Full-screen celebration modal

---

## Animation & Transition Guidelines

### Principle: Purposeful Motion Only
**Don't animate just because you can.**

### When to Animate

1. **State transitions** (button press, toggle switch)
   - Duration: 150-200ms
   - Curve: easeInOut

2. **Value changes** (stats increment, progress bar)
   - Duration: 300-500ms
   - Curve: easeOut (accelerates at start)

3. **Attention grabbing** (error, unlock, milestone)
   - Duration: 600-1200ms
   - Curve: elasticOut (bouncy) or custom spring

4. **Navigation** (screen transitions)
   - Duration: 300ms
   - Curve: easeInOut

### When NOT to Animate

1. **Text updates** (numbers, labels) - just update instantly
2. **List items** (no enter/exit animations unless deleting)
3. **Theme switches** (instant is better than jarring fade)

### Consistent Animation Patterns

**Scale on tap:**
```dart
Transform.scale(
  scale: pressed ? 0.95 : 1.0,
  child: button,
)
```

**Pulse on update:**
```dart
// Trigger: value changes
// Effect: Scale 1.0 → 1.05 → 1.0 over 600ms
```

**Fade in on load:**
```dart
// Trigger: data loaded
// Effect: Opacity 0.0 → 1.0 over 300ms
```

---

## Accessibility Consistency

### Rule 1: All Interactive Elements = Semantic Labels
```dart
IconButton(
  icon: Icon(Icons.refresh),
  tooltip: 'Refresh statistics', // Required
  onPressed: _refresh,
)
```

### Rule 2: Loading States = Announcements
```dart
if (_loading) {
  return Semantics(
    label: 'Loading statistics',
    child: CircularProgressIndicator(),
  );
}
```

### Rule 3: Errors = Clear Voice Feedback
```dart
Semantics(
  label: 'Error: Failed to upload data. Retry button available.',
  child: ErrorCard(...),
)
```

### Rule 4: Color ≠ Only Indicator
- Always pair color with icon or text
- Error: Red background + error icon + "Error" text
- Success: Green background + checkmark + "Success" text

---

## Prioritized Improvement List

### P0 - Critical (Fix Immediately)

1. **Statistics screen doesn't auto-update**
   - Add AppEventBus listener for UploadSuccessEvent
   - Refresh stats when user uploads data
   - Show "last updated" timestamp with auto-format

2. **Daily pot initialization race conditions**
   - Add loading skeleton when pot is initializing
   - Show error state if initialization fails
   - Ensure auth listener properly re-initializes

3. **Profile screen doesn't react to auth changes**
   - Add FirebaseAuth.authStateChanges() listener
   - Rebuild UI when user signs in/out
   - Handle anonymous → authenticated transition

### P1 - Important (Fix Soon)

4. **Inconsistent timestamp formatting**
   - Replace all DateTime displays with TimeAgoText
   - Add "last updated" to stats card
   - Show sensor data age

5. **Daily pot unlock feedback weak**
   - Add snackbar when pot unlocks
   - Stronger haptic feedback
   - More visible jiggle animation

6. **No stale data indicators**
   - Add "last updated X ago" to all data cards
   - Show loading spinner during refresh
   - Highlight newly updated data (brief pulse)

### P2 - Nice to Have (Backlog)

7. **Daily pot claim flow**
   - Add confirmation dialog
   - Celebration animation with confetti
   - Reset countdown timer

8. **Better loading skeletons**
   - Consistent skeleton design across app
   - Smooth transitions from skeleton → data
   - Proper content sizing to prevent layout shift

9. **Error state improvements**
   - Retry buttons everywhere
   - "View details" expanders
   - Error timestamping

10. **Offline mode indicators**
    - Show "Offline" badge on data cards
    - Queue operations for when back online
    - Sync status notifications

---

## Implementation Checklist

### Phase 1: Event Bus Consistency (Week 1)
- [ ] Add UploadSuccessEvent listener to StatisticsScreen
- [ ] Add DailyPotUpdatedEvent emission from DailyPotService
- [ ] Add TrackingStateChangedEvent emission from ForegroundLocationService
- [ ] Remove GlobalKey pattern from home_screen (use events instead)
- [ ] Add event listener to profile screen for auth changes

### Phase 2: Timestamp Consistency (Week 1)
- [ ] Replace all static DateTime displays with TimeAgoText
- [ ] Add "last updated" to ContributionStatsCard
- [ ] Add "last updated" to tile coverage section
- [ ] Show sensor data age in sensor cards

### Phase 3: Daily Pot UX (Week 2)
- [ ] Add loading skeleton to DailyPotIcon
- [ ] Add unlock celebration (snackbar + haptic)
- [ ] Add claim confirmation dialog
- [ ] Add reset countdown timer
- [ ] Add offline indicator

### Phase 4: Loading/Error States (Week 2)
- [ ] Create reusable SkeletonCard widget
- [ ] Create reusable ErrorCard widget
- [ ] Apply to all async data loads
- [ ] Add stale data indicators

### Phase 5: Polish (Week 3)
- [ ] Audit all animations for consistency
- [ ] Add accessibility labels
- [ ] Add celebration effects for milestones
- [ ] Implement offline queue

---

## Wireframes & Specifications

### Enhanced Daily Pot Icon

```
┌─────────────────┐
│  ┌──────────┐   │  ← Floating widget (top-right)
│  │ ⚫⚫⚫⚫⚫⚫ │   │
│  │ ⚫      ⚫ │   │  ← Progress ring (green when unlocked)
│  │ ⚫  🍯  ⚫ │   │  ← Pot icon (jiggles when unlocked)
│  │ ⚫      ⚫ │   │  ← Lock overlay when locked
│  │ ⚫⚫⚫⚫⚫⚫ │   │
│  └──────────┘   │
│   "3/5 uploads" │  ← Progress text (below ring)
└─────────────────┘

States:
1. Locked: Gray ring, lock icon overlay
2. Progress: Blue ring filling, no lock, progress text
3. Unlocked: Green ring full, jiggling, "Tap to claim!"
4. Claimed: Checkmark overlay, "Claimed today"
5. Loading: Skeleton circle with shimmer
6. Error: Gray with "!" badge
```

---

### Enhanced Stats Card

```
┌────────────────────────────────────────┐
│ 🌱  Total: 42  │  Today: 3  │  Streak: 5d 🔥 │
│                                        │
│ Updated 2m ago • Tap to refresh       │  ← New: Last updated indicator
└────────────────────────────────────────┘

Loading State:
┌────────────────────────────────────────┐
│ ▢   ████  │  ████  │  ████            │  ← Skeleton
└────────────────────────────────────────┘

Error State:
┌────────────────────────────────────────┐
│ ⚠️  Failed to load stats              │
│     Network error                      │
│                          [Retry] →     │
└────────────────────────────────────────┘
```

---

### Enhanced Upload Status

```
Current (home screen, below Start/Stop button):

☁️ Last upload: 5m ago        ← Uses TimeAgoText ✓
🔄 Uploading data...           ← Spinner shown
❌ Upload failed: Network error

Proposed addition:
┌────────────────────────────────────────┐
│ Upload History (last 5)               │
│ ✓ 5m ago - 48 samples - 📍 Seattle    │
│ ✓ 23m ago - 52 samples - 📍 Seattle   │
│ ✓ 1h ago - 45 samples - 📍 Tacoma     │
│ ❌ 2h ago - Failed (no network)        │
│ ✓ 3h ago - 39 samples - 📍 Olympia    │
│                        [View all] →    │
└────────────────────────────────────────┘
```

---

## Success Metrics

### How to Measure Consistency Improvements

1. **User confusion rate** (support tickets)
   - Before: "My stats don't update"
   - After: Should be 0 tickets about stale data

2. **Engagement with daily pot**
   - Track: Unlock rate, claim rate, time-to-claim
   - Goal: 80%+ of unlocks result in claims

3. **App rating mentions**
   - Before: "Inconsistent updates, needs refresh"
   - After: "Everything works smoothly"

4. **Developer velocity**
   - Track: Time to implement new features
   - Goal: Faster with consistent patterns

---

## Conclusion

The GreenGains app has solid foundations but suffers from **inconsistent reactive update patterns**. By:

1. **Standardizing on event-driven updates** (AppEventBus)
2. **Making all timestamps auto-update** (TimeAgoText)
3. **Adding consistent loading/error states** (skeletons, retry buttons)
4. **Improving daily pot UX** (progress text, celebrations, reset timer)
5. **Eliminating manual refresh patterns** (GlobalKey, setState in parent)

We can create a **predictable, cohesive user experience** where:
- Data always feels fresh
- Users know what's happening (clear feedback)
- UI updates consistently across screens
- No "weird custom shit or band aids"

**The app will feel professional, polished, and trustworthy.**

---

## Appendix: Code Patterns to Avoid

### ❌ Anti-Pattern 1: GlobalKey for Child Control
```dart
// BAD: Parent controlling child state
final _statsKey = GlobalKey<ContributionStatsCardState>();
// ...
_statsKey.currentState?.refresh();
```

**Why it's bad:**
- Tight coupling between parent and child
- Race conditions if widget not mounted
- Breaks encapsulation

**Better approach:**
```dart
// GOOD: Child subscribes to events
@override
void initState() {
  super.initState();
  _subscription = AppEventBus.instance
    .on<UploadSuccessEvent>()
    .listen((_) => _loadStats());
}
```

---

### ❌ Anti-Pattern 2: Static DateTime Display
```dart
// BAD: Never updates
Text('Last upload: ${lastUpload.toString()}')
```

**Better:**
```dart
// GOOD: Auto-updates
TimeAgoText(
  timestamp: lastUpload,
  prefix: 'Last upload: ',
)
```

---

### ❌ Anti-Pattern 3: No Loading State
```dart
// BAD: Shows stale data while loading
if (_data != null) {
  return DataCard(data: _data);
}
```

**Better:**
```dart
// GOOD: Shows loading indicator
if (_loading) {
  return SkeletonCard();
} else if (_error != null) {
  return ErrorCard(error: _error, onRetry: _load);
} else if (_data != null) {
  return DataCard(data: _data);
} else {
  return EmptyState();
}
```

---

### ❌ Anti-Pattern 4: Silent Failures
```dart
// BAD: Error is logged but user sees nothing
try {
  await upload();
} catch (e) {
  debugPrint('Upload failed: $e');
}
```

**Better:**
```dart
// GOOD: User gets feedback + action
try {
  await upload();
  showSuccessSnackbar();
} catch (e) {
  showErrorSnackbar(
    message: 'Upload failed: ${e.message}',
    action: SnackBarAction(
      label: 'Retry',
      onPressed: upload,
    ),
  );
}
```

---

End of audit. Next steps: Review with team, prioritize fixes, implement Phase 1.
