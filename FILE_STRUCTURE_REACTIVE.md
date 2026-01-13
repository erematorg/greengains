# Reactive Architecture - File Structure

## Directory Overview

```
greengains/
├── lib/
│   ├── core/
│   │   ├── events/
│   │   │   └── app_events.dart          ⭐ Event definitions & AppEventBus
│   │   ├── services/
│   │   │   └── time_ago_service.dart    ✨ NEW - Centralized timer
│   │   ├── REACTIVE_ARCHITECTURE.md     ✨ NEW - Full documentation
│   │   └── QUICK_START_REACTIVE.md      ✨ NEW - Quick reference
│   │
│   ├── screens/
│   │   ├── home_screen.dart             🔧 USES reactive patterns
│   │   ├── statistics_screen.dart       🔧 MODIFIED - Added listeners
│   │   └── profile_screen.dart          📊 No changes needed
│   │
│   ├── widgets/
│   │   ├── contribution_stats_card.dart 🔧 MODIFIED - Emits events
│   │   ├── time_ago_text.dart          🔧 REFACTORED - Uses service
│   │   └── sensor_data_card.dart        📊 No changes needed
│   │
│   ├── services/
│   │   └── location/
│   │       └── foreground_location_service.dart  ⭐ Emits upload events
│   │
│   └── app_shell.dart                   🔧 MODIFIED - Starts service
│
├── FIXES_SUMMARY.md                     ✨ NEW - Executive summary
├── CHANGES_SUMMARY.md                   ✨ NEW - Detailed changes
├── TEST_PLAN_REACTIVE.md                ✨ NEW - Testing guide
└── FILE_STRUCTURE_REACTIVE.md           ✨ NEW - This file
```

**Legend:**
- ⭐ Core reactive infrastructure
- ✨ NEW files created
- 🔧 MODIFIED existing files
- 📊 Existing files (no changes)

---

## Core Files Deep Dive

### 1. `lib/core/events/app_events.dart`
**Role:** Central event system

**Contains:**
- `AppEventBus` class (singleton)
- Event definitions:
  - `UploadSuccessEvent`
  - `UploadFailedEvent`
  - `StatsUpdatedEvent`
  - `TrackingStateChangedEvent`
  - `DailyPotUpdatedEvent`
  - `DailyPotClaimedEvent`
  - `LocationPermissionChangedEvent`

**Used by:**
- All screens listening to events
- All services emitting events

---

### 2. `lib/core/services/time_ago_service.dart` ✨ NEW
**Role:** Centralized timer for timestamp updates

**Exports:**
- `TimeAgoService` singleton
- `start()` - Initialize timer
- `stop()` - Cleanup (rarely used)
- `tick` ValueNotifier - Subscribe for updates
- `format()` static method - Convert DateTime to "Xm ago"

**Started by:**
- `app_shell.dart` in `initState()`

**Used by:**
- `time_ago_text.dart` widget

---

### 3. `lib/widgets/time_ago_text.dart` 🔧 REFACTORED
**Role:** Auto-updating timestamp widget

**API:**
```dart
TimeAgoText({
  required DateTime timestamp,
  String prefix = '',
  TextStyle? style,
})
```

**Example:**
```dart
TimeAgoText(
  timestamp: myDateTime,
  prefix: 'Last upload: ',
  style: TextStyle(color: Colors.green),
)
// Output: "Last upload: 5m ago"
```

**Internal:**
- Listens to `TimeAgoService.instance.tick`
- Rebuilds every 60 seconds
- Stateless widget (efficient)

---

### 4. `lib/screens/statistics_screen.dart` 🔧 MODIFIED
**Role:** Show detailed contribution statistics

**Reactive to:**
- `UploadSuccessEvent` → Reload stats from DB
- `StatsUpdatedEvent` → Update UI directly

**Implementation:**
```dart
StreamSubscription<UploadSuccessEvent>? _uploadSuccessSub;
StreamSubscription<StatsUpdatedEvent>? _statsUpdatedSub;

initState() {
  _uploadSuccessSub = AppEventBus.instance
      .on<UploadSuccessEvent>()
      .listen(_onUploadSuccess);

  _statsUpdatedSub = AppEventBus.instance
      .on<StatsUpdatedEvent>()
      .listen(_onStatsUpdated);
}

dispose() {
  _uploadSuccessSub?.cancel();
  _statsUpdatedSub?.cancel();
}
```

---

### 5. `lib/widgets/contribution_stats_card.dart` 🔧 MODIFIED
**Role:** Compact stats display on home screen

**Reactive to:**
- `UploadSuccessEvent` → Reload stats from DB

**Emits:**
- `StatsUpdatedEvent` after loading stats

**Why emit?**
- Prevents duplicate DB queries
- Statistics screen gets updated stats for free
- Cross-screen synchronization

---

### 6. `lib/app_shell.dart` 🔧 MODIFIED
**Role:** Main navigation shell

**Reactive setup:**
```dart
import 'core/services/time_ago_service.dart';

@override
void initState() {
  super.initState();
  _pageController = PageController(initialPage: _currentIndex);

  // Start centralized time ago service
  TimeAgoService.instance.start();
}
```

**Why here?**
- Early in widget tree
- Lives for app lifetime
- Single initialization point

---

## Documentation Files

### 1. `lib/core/REACTIVE_ARCHITECTURE.md` ✨ NEW
**Audience:** Developers (technical)

**Contents:**
- ~400 lines
- Event bus explanation
- Data flow diagrams
- Reactive patterns by screen
- Best practices
- Debugging guide
- Future enhancements

---

### 2. `lib/core/QUICK_START_REACTIVE.md` ✨ NEW
**Audience:** Developers (practical)

**Contents:**
- ~200 lines
- Copy-paste code examples
- 6 common scenarios
- Anti-patterns to avoid
- Cheat sheet
- Performance tips

---

### 3. `FIXES_SUMMARY.md` ✨ NEW
**Audience:** Product/Tech leads

**Contents:**
- ~250 lines
- Problems fixed (detailed)
- Technical implementation
- Testing checklist
- Performance metrics
- Developer notes

---

### 4. `CHANGES_SUMMARY.md` ✨ NEW
**Audience:** All stakeholders

**Contents:**
- ~400 lines
- Executive summary
- Files changed (with diffs)
- Architecture diagrams
- Performance impact
- Migration guide

---

### 5. `TEST_PLAN_REACTIVE.md` ✨ NEW
**Audience:** QA/Testers

**Contents:**
- ~350 lines
- 7 test suites
- 23 test cases
- Step-by-step instructions
- Expected results
- Performance benchmarks

---

## Import Map

Shows which files import which (reactive components only):

```
app_shell.dart
  └─> time_ago_service.dart

time_ago_text.dart
  └─> time_ago_service.dart

statistics_screen.dart
  └─> app_events.dart

contribution_stats_card.dart
  └─> app_events.dart

home_screen.dart
  ├─> time_ago_text.dart
  └─> (indirectly) time_ago_service.dart

foreground_location_service.dart
  └─> app_events.dart
```

---

## Data Flow

### Upload Success Flow
```
1. Native Android
   └─> ForegroundLocationService._handleNativeUploadStatus()
       └─> AppEventBus.emit(UploadSuccessEvent)
           ├─> ContributionStatsCard.refresh()
           │   └─> ContributionRepository.getStats()
           │       └─> AppEventBus.emit(StatsUpdatedEvent)
           │           └─> StatisticsScreen.update()
           │
           └─> StatisticsScreen.reload()
               └─> ContributionRepository.getStats()
```

### Timestamp Update Flow
```
1. TimeAgoService.start() (in AppShell.initState)
   └─> Timer.periodic(60s)
       └─> _tickNotifier.value++
           └─> All TimeAgoText widgets rebuild
               └─> Display updated "Xm ago"
```

---

## Dependency Graph

```
app_shell.dart
    │
    ├─ Starts ──> TimeAgoService
    │
    └─ Contains ─> home_screen.dart
                       │
                       ├─ Uses ─> time_ago_text.dart
                       │              │
                       │              └─ Listens ─> TimeAgoService.tick
                       │
                       └─ Contains ─> contribution_stats_card.dart
                                          │
                                          ├─ Listens ─> UploadSuccessEvent
                                          └─ Emits ───> StatsUpdatedEvent
                                                             │
                                                             └─ Received by ─> statistics_screen.dart
                                                                                    │
                                                                                    └─ Also listens ─> UploadSuccessEvent
```

---

## File Size Reference

| File | Type | Size | Lines |
|------|------|------|-------|
| `time_ago_service.dart` | Code | ~2KB | ~60 |
| `time_ago_text.dart` | Code | ~1KB | ~30 |
| `statistics_screen.dart` | Code | +2KB | +40 |
| `contribution_stats_card.dart` | Code | +0.5KB | +5 |
| `app_shell.dart` | Code | +0.3KB | +4 |
| `REACTIVE_ARCHITECTURE.md` | Docs | ~15KB | ~400 |
| `QUICK_START_REACTIVE.md` | Docs | ~8KB | ~200 |
| `FIXES_SUMMARY.md` | Docs | ~10KB | ~250 |
| `CHANGES_SUMMARY.md` | Docs | ~15KB | ~400 |
| `TEST_PLAN_REACTIVE.md` | Docs | ~12KB | ~350 |
| **TOTAL** | | **~66KB** | **~1740** |

---

## Quick Navigation

Need to...
- **Understand the architecture?** → `lib/core/REACTIVE_ARCHITECTURE.md`
- **Add a new reactive feature?** → `lib/core/QUICK_START_REACTIVE.md`
- **See what changed?** → `CHANGES_SUMMARY.md`
- **Test the changes?** → `TEST_PLAN_REACTIVE.md`
- **Find specific file?** → This document (FILE_STRUCTURE_REACTIVE.md)

---

## Version History

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2026-01-13 | Initial reactive architecture implementation |

---

## Maintenance Notes

### When adding new screens:
1. Add event listeners in `initState()`
2. Cancel subscriptions in `dispose()`
3. Use `TimeAgoText` for timestamps
4. Follow patterns from `QUICK_START_REACTIVE.md`

### When adding new events:
1. Define in `lib/core/events/app_events.dart`
2. Emit where state changes
3. Document in `REACTIVE_ARCHITECTURE.md`
4. Add test cases

### When refactoring:
1. Check this file structure first
2. Update documentation if patterns change
3. Test thoroughly (use TEST_PLAN_REACTIVE.md)
4. Update version history
