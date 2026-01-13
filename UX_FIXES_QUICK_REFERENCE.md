# UX Consistency Fixes - Quick Reference Guide

**Purpose:** Step-by-step fixes for critical UX inconsistencies
**Estimated Time:** 2-3 hours for P0 + critical P1 issues
**Last Updated:** 2026-01-13

---

## P0 Fixes (Ship Blockers - Do These First)

### Fix 1: Profile Screen Auth Reactivity (30 min)
**File:** `lib/screens/profile_screen.dart`
**Problem:** Profile doesn't update when user signs in/out on another device

**Current Code (line 20-24):**
```dart
class _ProfileScreenState extends State<ProfileScreen> {

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser; // ❌ Only reads once
```

**Fixed Code:**
```dart
class _ProfileScreenState extends State<ProfileScreen> {
  User? _currentUser;
  StreamSubscription<User?>? _authSubscription;

  @override
  void initState() {
    super.initState();
    _currentUser = FirebaseAuth.instance.currentUser;

    // ✅ Listen to auth state changes
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
    final user = _currentUser; // ✅ Use local state
    final theme = context.theme;
    final isDark = context.isDarkMode;

    return Scaffold(
      appBar: AppBar(
        actions: [
          IconButton(
            icon: const Icon(Icons.settings_outlined),
            onPressed: () {
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => const SettingsScreen(),
                ),
              );
            },
            tooltip: 'Settings',
          ),
        ],
      ),
      body: user == null ? _buildSignedOutState(theme, isDark) : _buildSignedInState(user, theme, isDark),
    );
  }
}
```

---

### Fix 2: Statistics Chart Disclaimer (15 min)
**File:** `lib/screens/statistics_screen.dart`
**Problem:** Chart shows 6 days of zeros (fake data) - users think they had no activity

**Current Code (line 447-451):**
```dart
Text(
  'Visualization based on your current activity',
  style: theme.textTheme.labelSmall?.copyWith(
    color: AppColors.textTertiary(isDark),
    fontStyle: FontStyle.italic,
  ),
),
```

**Fixed Code:**
```dart
Container(
  padding: const EdgeInsets.symmetric(
    horizontal: AppTheme.spaceXs,
    vertical: AppTheme.spaceXxs,
  ),
  decoration: BoxDecoration(
    color: Colors.orange.withValues(alpha: 0.1),
    borderRadius: BorderRadius.circular(AppTheme.radiusMin),
    border: Border.all(
      color: Colors.orange.withValues(alpha: 0.3),
    ),
  ),
  child: Row(
    mainAxisSize: MainAxisSize.min,
    children: [
      Icon(
        Icons.info_outline,
        size: 14,
        color: Colors.orange,
      ),
      const SizedBox(width: 4),
      Expanded(
        child: Text(
          'Historical data tracking coming soon - chart currently shows today only',
          style: theme.textTheme.labelSmall?.copyWith(
            color: Colors.orange[700],
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    ],
  ),
),
```

**Alternative Quick Fix (even simpler):**
Change line 304-309 to only show today's data:
```dart
// Instead of 7 spots with zeros, just show 1 spot (today)
final spots = [FlSpot(0, todayCount)];

// Update chart config:
minX: 0,
maxX: 0, // Single point
```

---

### Fix 3: Daily Pot Loading State (20 min)
**File:** `lib/widgets/daily_pot_icon.dart`
**Problem:** Icon disappears for 2-5 seconds on launch (looks like a bug)

**Current Code (line 68-73):**
```dart
return ValueListenableBuilder<DailyPot?>(
  valueListenable: _service.pot,
  builder: (context, pot, _) {
    if (pot == null) {
      return const SizedBox.shrink(); // ❌ Just disappears!
    }
```

**Fixed Code:**
```dart
return ValueListenableBuilder<DailyPot?>(
  valueListenable: _service.pot,
  builder: (context, pot, _) {
    if (pot == null) {
      // ✅ Show loading skeleton
      return Container(
        width: 44,
        height: 44,
        decoration: BoxDecoration(
          shape: BoxShape.circle,
          color: AppColors.surfaceElevated(isDark).withValues(alpha: 0.3),
        ),
        child: Center(
          child: SizedBox(
            width: 20,
            height: 20,
            child: CircularProgressIndicator(
              strokeWidth: 2,
              valueColor: AlwaysStoppedAnimation(AppColors.primary),
            ),
          ),
        ),
      );
    }
```

---

## P1 Critical Fixes (Major UX Issues)

### Fix 4: Remove GlobalKey Anti-Pattern (10 min)
**File:** `lib/screens/home_screen.dart`
**Problem:** Parent widget manually calls child methods (tight coupling)

**Current Code (line 31, 146, 175):**
```dart
class _HomeScreenState extends State<HomeScreen> with WidgetsBindingObserver {
  final _locationService = ForegroundLocationService.instance;
  final _statsKey = GlobalKey<ContributionStatsCardState>(); // ❌ Anti-pattern
  // ...

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      _checkServiceStatus();
      _reloadUploadStatus();
      _statsKey.currentState?.refresh(); // ❌ Manual control
      _loadTileCoverage();
    }
  }

  Future<void> _refreshData() async {
    HapticFeedback.lightImpact();
    await _statsKey.currentState?.refresh(); // ❌ Manual control
    // ...
  }

  // ...
  ContributionStatsCard(key: _statsKey), // ❌ Passes key
```

**Fixed Code:**
```dart
class _HomeScreenState extends State<HomeScreen> with WidgetsBindingObserver {
  final _locationService = ForegroundLocationService.instance;
  // ✅ Delete: final _statsKey = GlobalKey<ContributionStatsCardState>();
  // ...

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      _checkServiceStatus();
      _reloadUploadStatus();
      // ✅ Delete: _statsKey.currentState?.refresh();
      _loadTileCoverage();
      // Stats card refreshes automatically via AppEventBus
    }
  }

  Future<void> _refreshData() async {
    HapticFeedback.lightImpact();
    // ✅ Delete: await _statsKey.currentState?.refresh();
    await _loadTileCoverage();
    // Stats card refreshes automatically via AppEventBus
    await Future.delayed(const Duration(milliseconds: 300));
  }

  // ...
  ContributionStatsCard(), // ✅ No key needed
```

**Why this works:**
ContributionStatsCard already listens to `UploadSuccessEvent` (line 41-43 in contribution_stats_card.dart), so it auto-refreshes when uploads succeed. No manual control needed.

---

### Fix 5: Sensor Timestamps Auto-Update (30 min)
**File:** `lib/widgets/sensor_data_card.dart`
**Problem:** Sensor data age never displayed, even though `updatedAt` is passed

**Add this import at top:**
```dart
import 'time_ago_text.dart';
```

**Current Code (line 160-186):**
```dart
AnimatedDefaultTextStyle(
  duration: const Duration(milliseconds: 300),
  style: theme.textTheme.bodyLarge?.copyWith(
        color: isActive
            ? AppColors.textPrimary(isDark)
            : AppColors.textSecondary(isDark),
        fontWeight:
            isActive ? FontWeight.w600 : FontWeight.normal,
      ) ??
      const TextStyle(),
  child: Text(
    widget.value ?? 'Waiting for data',
  ),
),
const SizedBox(height: 2),
Text(
  widget.unit,
  style: theme.textTheme.bodySmall?.copyWith(
    color: AppColors.textSecondary(isDark),
    fontSize: 12,
  ),
),
// ❌ updatedAt timestamp missing here
```

**Fixed Code:**
```dart
AnimatedDefaultTextStyle(
  duration: const Duration(milliseconds: 300),
  style: theme.textTheme.bodyLarge?.copyWith(
        color: isActive
            ? AppColors.textPrimary(isDark)
            : AppColors.textSecondary(isDark),
        fontWeight:
            isActive ? FontWeight.w600 : FontWeight.normal,
      ) ??
      const TextStyle(),
  child: Text(
    widget.value ?? 'Waiting for data',
  ),
),
const SizedBox(height: 2),
Text(
  widget.unit,
  style: theme.textTheme.bodySmall?.copyWith(
    color: AppColors.textSecondary(isDark),
    fontSize: 12,
  ),
),
// ✅ Add timestamp display
if (widget.updatedAt != null && isActive) ...[
  const SizedBox(height: 4),
  TimeAgoText(
    timestamp: widget.updatedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.labelSmall?.copyWith(
      color: AppColors.textTertiary(isDark),
      fontSize: 10,
    ),
  ),
],
```

---

### Fix 6: Stats Card "Last Updated" Timestamp (20 min)
**File:** `lib/widgets/contribution_stats_card.dart`
**Problem:** Users can't tell if stats are fresh or stale

**Add import:**
```dart
import 'time_ago_text.dart';
```

**Add state variable (line 21-26):**
```dart
class ContributionStatsCardState extends State<ContributionStatsCard>
    with SingleTickerProviderStateMixin {
  final _repository = ContributionRepository();
  ContributionStats _stats = ContributionStats.empty;
  bool _loading = true;
  String? _error;
  DateTime? _lastLoadedAt; // ✅ Add this
  late AnimationController _pulseController;
```

**Update _loadStats method (line 64-88):**
```dart
Future<void> _loadStats() async {
  setState(() {
    _loading = true;
    _error = null;
  });
  try {
    final stats = await _repository.getStats();
    if (mounted) {
      setState(() {
        _stats = stats;
        _loading = false;
        _lastLoadedAt = DateTime.now(); // ✅ Add this
      });

      // Emit stats updated event so other parts of the app can react
      AppEventBus.instance.emit(StatsUpdatedEvent(stats));
    }
  } catch (e) {
    if (mounted) {
      setState(() {
        _loading = false;
        _error = e.toString();
      });
    }
  }
}
```

**Add timestamp display in build method (after line 360, before closing Container):**
```dart
          const SizedBox(width: AppTheme.spaceMd),

          // Stats in horizontal row
          Expanded(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _buildCompactStat(/* ... */),
                _buildDivider(isDark),
                _buildCompactStat(/* ... */),
                if (_stats.currentStreak > 0) ...[
                  _buildDivider(isDark),
                  _buildCompactStat(/* ... */),
                ],
              ],
            ),
          ),
        ],
      ),
      // ✅ Add timestamp footer
      child: Column(
        children: [
          Row(/* existing stats row */),
          if (_lastLoadedAt != null) ...[
            const SizedBox(height: AppTheme.spaceXs),
            TimeAgoText(
              timestamp: _lastLoadedAt!,
              prefix: 'Updated ',
              style: theme.textTheme.labelSmall?.copyWith(
                color: AppColors.textTertiary(isDark),
                fontSize: 10,
              ),
            ),
          ],
        ],
      ),
    ),
```

**Note:** This requires restructuring the Container's child to be a Column instead of a Row. Full restructure:

```dart
// Change from:
child: Row(children: [icon, stats]),

// To:
child: Column(
  children: [
    Row(children: [icon, stats]), // Main stats row
    if (_lastLoadedAt != null) ...[
      const SizedBox(height: AppTheme.spaceXs),
      TimeAgoText(...), // Timestamp footer
    ],
  ],
),
```

---

### Fix 7: Coverage Data Timestamp (20 min)
**File:** `lib/screens/home_screen.dart`
**Problem:** "Coverage Today" has no timestamp - users can't tell if stale

**Add state variable (line 33-37):**
```dart
class _HomeScreenState extends State<HomeScreen> with WidgetsBindingObserver {
  final _locationService = ForegroundLocationService.instance;
  final _contributionRepo = ContributionRepository();
  final _prefs = AppPreferences.instance;
  final Set<String> _dismissedTips = {};
  TileCoverageStats? _tileCoverage;
  bool _tileCoverageLoading = true;
  DateTime? _tileCoverageLoadedAt; // ✅ Add this
  bool _batteryPromptOpen = false;
```

**Update _loadTileCoverage (line 181-197):**
```dart
Future<void> _loadTileCoverage() async {
  try {
    final stats = await _contributionRepo.getTodayTileCoverage();
    if (mounted) {
      setState(() {
        _tileCoverage = stats;
        _tileCoverageLoading = false;
        _tileCoverageLoadedAt = DateTime.now(); // ✅ Add this
      });
    }
  } catch (_) {
    if (mounted) {
      setState(() {
        _tileCoverageLoading = false;
        _tileCoverageLoadedAt = DateTime.now(); // ✅ Even on error, mark attempt time
      });
    }
  }
}
```

**Update _buildCoverageSummary (add after line 506):**
```dart
Text(
  'Coverage Today',
  style: theme.textTheme.titleSmall?.copyWith(
    fontWeight: FontWeight.w600,
  ),
),
// ✅ Add timestamp below title
if (_tileCoverageLoadedAt != null) ...[
  const SizedBox(height: 2),
  TimeAgoText(
    timestamp: _tileCoverageLoadedAt!,
    prefix: 'Updated ',
    style: theme.textTheme.labelSmall?.copyWith(
      color: AppColors.textTertiary(isDark),
      fontSize: 10,
    ),
  ),
],
const SizedBox(height: 4),
```

---

### Fix 8: Daily Pot Unlock Celebration (45 min)
**File:** `lib/services/daily_pot_service.dart`
**Problem:** Pot unlocks silently - users miss the moment

**Add new event to `lib/core/events/app_events.dart`:**
```dart
/// Daily pot unlocked (not claimed yet)
class DailyPotUnlockedEvent extends AppEvent {
  final int uploadsCompleted;
  final DateTime timestamp;

  DailyPotUnlockedEvent({
    required this.uploadsCompleted,
    required this.timestamp,
  });

  @override
  String get debugInfo => 'uploadsCompleted=$uploadsCompleted';
}
```

**Update recordUpload method in daily_pot_service.dart (line 100-148):**
```dart
Future<void> recordUpload() async {
  try {
    final user = FirebaseAuth.instance.currentUser;
    if (user == null) {
      return;
    }

    final current = _potNotifier.value;

    // Optimistic update - instant UI feedback
    if (current != null && !current.hasClaimedToday) {
      final optimistic = current.copyWith(
        uploadsToday: current.uploadsToday + 1,
        isUnlocked: (current.uploadsToday + 1) >= current.uploadsRequired,
      );

      _potNotifier.value = optimistic;
      await _db.cacheDailyPot(optimistic);

      // Emit event for UI
      AppEventBus.instance.emit(DailyPotUpdatedEvent(optimistic));

      // ✅ Celebrate unlock!
      if (optimistic.isUnlocked && !current.isUnlocked) {
        debugPrint('🍯 Daily pot unlocked! ${optimistic.uploadsToday}/${optimistic.uploadsRequired} uploads');

        // Emit unlock event for celebration UI
        AppEventBus.instance.emit(DailyPotUnlockedEvent(
          uploadsCompleted: optimistic.uploadsToday,
          timestamp: DateTime.now(),
        ));
      }
    }
    // ... rest of method
  }
}
```

**Add listener to home_screen.dart (in initState, around line 40-49):**
```dart
@override
void initState() {
  super.initState();
  WidgetsBinding.instance.addObserver(this);
  _locationService.isRunning.addListener(_handleServiceRunningChange);
  _checkServiceStatus();
  _setupUploadSuccessListener();
  _setupDailyPotUnlockListener(); // ✅ Add this
  _loadDismissedTips();
  _checkBatteryOptimization();
  _loadTileCoverage();
}

// ✅ Add new method:
void _setupDailyPotUnlockListener() {
  AppEventBus.instance
      .on<DailyPotUnlockedEvent>()
      .listen(_onDailyPotUnlocked);
}

void _onDailyPotUnlocked(DailyPotUnlockedEvent event) {
  if (!mounted) return;

  // Heavy haptic feedback burst
  HapticFeedback.heavyImpact();
  Future.delayed(const Duration(milliseconds: 100), () {
    HapticFeedback.heavyImpact();
  });

  // Prominent snackbar
  AppSnackbars.showSuccess(
    context,
    '🍯 DAILY POT UNLOCKED! Tap the pot icon to claim 10-100 credits!',
    duration: const Duration(seconds: 5),
  );
}
```

**Note:** You'll need to import HapticFeedback in home_screen.dart if not already imported:
```dart
import 'package:flutter/services.dart';
```

---

### Fix 9: Daily Pot Progress Text (30 min)
**File:** `lib/widgets/daily_pot_icon.dart`
**Problem:** Users can't see "3/5 uploads" - have to guess progress

**Replace the entire widget return (line 75-160) with:**
```dart
return GestureDetector(
  onTap: pot.canClaim ? _claimPot : null,
  child: Column(
    mainAxisSize: MainAxisSize.min,
    children: [
      // Pot icon with jiggle animation
      AnimatedBuilder(
        animation: _jiggleAnimation,
        builder: (context, child) {
          return Transform.rotate(
            angle: pot.canClaim ? _jiggleAnimation.value : 0,
            child: child,
          );
        },
        child: SizedBox(
          width: 44,
          height: 44,
          child: Stack(
            alignment: Alignment.center,
            children: [
              // Progress ring
              SizedBox(
                width: 44,
                height: 44,
                child: CircularProgressIndicator(
                  value: pot.progress,
                  strokeWidth: 3,
                  backgroundColor: AppColors.surfaceElevated(isDark).withValues(alpha: 0.3),
                  valueColor: AlwaysStoppedAnimation(
                    pot.isUnlocked
                        ? AppColors.success
                        : AppColors.primary,
                  ),
                ),
              ),

              // Center icon
              Container(
                width: 30,
                height: 30,
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  gradient: pot.canClaim
                      ? const LinearGradient(
                          colors: AppColors.gradientGreen,
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                        )
                      : null,
                  color: pot.canClaim ? null : AppColors.surfaceElevated(isDark),
                  boxShadow: pot.canClaim
                      ? [
                          BoxShadow(
                            color: AppColors.primary.withValues(alpha: 0.4),
                            blurRadius: 12,
                            spreadRadius: 2,
                          ),
                        ]
                      : null,
                ),
                child: Stack(
                  alignment: Alignment.center,
                  children: [
                    Icon(
                      Icons.eco,
                      size: 18,
                      color: pot.canClaim
                          ? Colors.white
                          : AppColors.primary,
                    ),
                    if (!pot.isUnlocked)
                      Icon(
                        Icons.lock,
                        size: 14,
                        color: isDark
                            ? Colors.white.withValues(alpha: 0.7)
                            : Colors.black.withValues(alpha: 0.5),
                      ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),

      // ✅ Progress text below icon
      const SizedBox(height: 4),
      Text(
        pot.canClaim
            ? 'Tap to claim!'
            : pot.hasClaimedToday
                ? 'Claimed'
                : '${pot.uploadsToday}/${pot.uploadsRequired}',
        style: TextStyle(
          fontSize: 10,
          fontWeight: FontWeight.w600,
          color: pot.canClaim
              ? AppColors.success
              : pot.hasClaimedToday
                  ? AppColors.textSecondary(isDark)
                  : AppColors.primary,
        ),
      ),
    ],
  ),
);
```

**Adjust positioning in home_screen.dart (line 462-466):**
```dart
// Floating daily pot icon (top-right corner)
const Positioned(
  top: 60, // ✅ Move up slightly to accommodate text below icon
  right: 16,
  child: DailyPotIcon(),
),
```

---

## Quick Testing Checklist

After implementing fixes, test these scenarios:

### P0 Tests (5 minutes)
- [ ] Sign out and sign back in on profile screen - UI updates
- [ ] Open statistics screen - chart has prominent disclaimer or shows only today
- [ ] Launch app - daily pot shows loading spinner (not blank)

### P1 Tests (10 minutes)
- [ ] Wait 2 minutes on home screen - sensor "Updated X ago" timestamps change
- [ ] Upload data - stats card shows "Updated just now"
- [ ] Look at coverage section - shows "Updated X ago"
- [ ] Upload 5 times - daily pot shows "1/5", "2/5", etc.
- [ ] On 5th upload - see snackbar "POT UNLOCKED!" + feel haptic

### Regression Tests (5 minutes)
- [ ] All existing functionality still works
- [ ] No console errors
- [ ] App doesn't crash
- [ ] Pull-to-refresh still works

---

## Common Pitfalls

### 1. Forgetting to import TimeAgoText
**Symptom:** "Undefined name 'TimeAgoText'"
**Fix:** Add import at top of file:
```dart
import '../widgets/time_ago_text.dart';
// or
import 'time_ago_text.dart';
```

### 2. DateTime? nullable errors
**Symptom:** "The property can't be unconditionally accessed because the receiver can be 'null'"
**Fix:** Always null-check before displaying:
```dart
if (widget.updatedAt != null)
  TimeAgoText(timestamp: widget.updatedAt!)
```

### 3. Widget tree structure changes
**Symptom:** Layout breaks after adding timestamp
**Fix:** Wrap in Column and adjust flex/spacing:
```dart
// Before: Row(children: [icon, text])
// After:
Column(
  children: [
    Row(children: [icon, text]),
    if (timestamp != null) TimeAgoText(...),
  ],
)
```

### 4. Subscription not cancelled
**Symptom:** Memory leak warnings, listeners fire after dispose
**Fix:** Always cancel in dispose:
```dart
@override
void dispose() {
  _subscription?.cancel();
  super.dispose();
}
```

---

## File Modification Summary

### Modified Files:
1. `lib/screens/profile_screen.dart` - Add auth listener (30 lines changed)
2. `lib/screens/statistics_screen.dart` - Update chart disclaimer (20 lines changed)
3. `lib/widgets/daily_pot_icon.dart` - Add loading state + progress text (60 lines changed)
4. `lib/screens/home_screen.dart` - Remove GlobalKey + add unlock listener (30 lines changed)
5. `lib/widgets/sensor_data_card.dart` - Add timestamp display (15 lines changed)
6. `lib/widgets/contribution_stats_card.dart` - Add timestamp display (25 lines changed)
7. `lib/services/daily_pot_service.dart` - Emit unlock event (10 lines changed)
8. `lib/core/events/app_events.dart` - Add DailyPotUnlockedEvent (15 lines added)

### Total Changes:
- ~205 lines added/modified across 8 files
- 0 breaking changes (all backward compatible)
- 0 new dependencies required

---

## Need Help?

### If tests fail:
1. Check console for error messages
2. Verify all imports are correct
3. Ensure TimeAgoService is started in app_shell.dart (it is - line 29)
4. Check that AppEventBus is initialized (it is - singleton pattern)

### If UI looks broken:
1. Hot restart (not hot reload) - structural changes need full restart
2. Check widget tree structure (Column vs Row)
3. Verify nullable types are handled (use `if (value != null)`)
4. Test on both light and dark themes

### If performance issues:
1. Profile screen auth listener is properly disposed (check dispose method)
2. TimeAgoService uses single timer (already optimized)
3. No infinite rebuild loops (check setState calls)

---

**END OF QUICK REFERENCE**

Continue to full audit document (UX_CONSISTENCY_AUDIT_UPDATED.md) for:
- P2 and P3 fixes (polish and nice-to-have)
- Design system rules
- Testing checklist
- Success metrics
