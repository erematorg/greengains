# Persistence Layer Integration Guide

## Quick Start

If you're integrating the new persistence layer or need to understand how to use it, this guide is for you.

## Installation Steps

### 1. Database Migration (Automatic)

No action needed - existing users will automatically migrate from v1 to v2 on first launch.

### 2. Initialize Managers

Already integrated in `main.dart`:

```dart
Future<void> _initializeApp() async {
  // ... Firebase, SharedPreferences ...

  // Initialize tracking session manager (restore state from database)
  await TrackingSessionManager.instance.initialize();

  // Initialize daily pot service (non-blocking)
  DailyPotService.instance.initialize();

  // ... rest of initialization ...
}
```

## Using Tracking Session Manager

### Basic Usage

```dart
import 'package:greengains/services/tracking/tracking_session_manager.dart';

final sessionManager = TrackingSessionManager.instance;

// Start a tracking session
await sessionManager.startSession();

// Stop tracking
await sessionManager.stopSession(reason: 'user_stopped');

// Check if currently tracking
final isTracking = sessionManager.isTracking.value;

// Listen to tracking state changes
sessionManager.isTracking.addListener(() {
  print('Tracking state: ${sessionManager.isTracking.value}');
});
```

### Recording Statistics

```dart
// Record an upload completed
await sessionManager.recordUploadCompleted();

// Record samples collected (if needed in future)
await sessionManager.recordSamplesCollected(100);
```

### Querying Session Data

```dart
// Get current session info
final currentSession = await sessionManager.getCurrentSession();
if (currentSession != null) {
  final startedAt = currentSession['started_at'];
  final samplesCollected = currentSession['samples_collected'];
  print('Session started at: $startedAt, samples: $samplesCollected');
}

// Get session history
final history = await sessionManager.getHistory(limit: 10);
for (final session in history) {
  final duration = session['duration_ms'];
  final uploads = session['uploads_completed'];
  print('Session duration: ${duration ~/ 1000}s, uploads: $uploads');
}

// Get aggregated statistics
final stats = await sessionManager.getStats();
print('Total sessions: ${stats.totalSessions}');
print('Average duration: ${stats.averageDuration.inMinutes} minutes');
print('Total uploads: ${stats.totalUploads}');
```

## Using Daily Pot with Offline Cache

### Basic Usage

```dart
import 'package:greengains/services/daily_pot_service.dart';

final potService = DailyPotService.instance;

// Initialize (loads from cache first, then syncs)
await potService.initialize();

// Listen to pot state changes
potService.pot.addListener(() {
  final pot = potService.pot.value;
  if (pot != null) {
    print('Uploads: ${pot.uploadsToday}/${pot.uploadsRequired}');
    print('Unlocked: ${pot.isUnlocked}');
  }
});

// Record an upload (optimistic update + background sync)
await potService.recordUpload();

// Claim the pot
final amount = await potService.claimPot();
if (amount > 0) {
  print('Claimed $amount credits!');
}
```

### Offline Access

```dart
// Get cached pot state (works offline)
final cachedPot = await potService.getCachedPot();

// Clear cache (e.g., on sign out)
await potService.clearCache();

// Manual refresh from backend
await potService.fetchPotState();
```

## Using App State Manager

### Basic Usage

```dart
import 'package:greengains/services/state/app_state_manager.dart';

final stateManager = AppStateManager.instance;

// Initialize (automatic state restoration)
await stateManager.initialize();

// Get app state summary
final summary = await stateManager.getStateSummary();
print('Tracking: ${summary.isTracking}');
print('Total contributions: ${summary.totalContributions}');
print('Total sessions: ${summary.totalSessions}');
```

### Debugging

```dart
// Export state as JSON
final json = await stateManager.exportStateJson();
print(json);

// Save to file for support
final file = File('app_state.json');
await file.writeAsString(json);
```

### Persistent Key-Value Store

```dart
// Save arbitrary state
await stateManager.saveState('onboarding_step', '3');

// Retrieve state
final step = await stateManager.getState('onboarding_step');
print('User on step: $step');
```

## Using Database Helper Directly

**Note**: Prefer using the manager classes above. Direct database access is for advanced use cases.

```dart
import 'package:greengains/data/local/database_helper.dart';

final db = DatabaseHelper.instance;

// Contributions
await db.insertContribution(
  id: uuid.v4(),
  timestamp: DateTime.now(),
  samplesCount: 30,
  geohash: 'u4pruyd',
  success: true,
);

final totalUploads = await db.getTotalCount();
final todayUploads = await db.getTodayCount();
final streak = await db.getStreak();

// Tracking sessions (use TrackingSessionManager instead)
final sessionId = await db.startTrackingSession();
await db.stopTrackingSession(endedReason: 'user_stopped');

// Daily pot cache (use DailyPotService instead)
await db.cacheDailyPot(pot);
final cached = await db.getCachedDailyPot(userId);

// Upload queue (future feature)
await db.queueUpload(id: id, payload: jsonPayload);
final pending = await db.getPendingUploads();

// Maintenance
await db.cleanOldData(daysToKeep: 90);
final stats = await db.getDatabaseStats();
```

## Event-Driven Updates

The persistence layer emits events via `AppEventBus` for reactive UI updates.

### Listening to Events

```dart
import 'package:greengains/core/events/app_events.dart';

final eventBus = AppEventBus.instance;

// Listen to upload success
eventBus.on<UploadSuccessEvent>().listen((event) {
  print('Upload succeeded: ${event.samplesCount} samples');
  // Refresh UI, show notification, etc.
});

// Listen to tracking state changes
eventBus.on<TrackingStateChangedEvent>().listen((event) {
  print('Tracking: ${event.isTracking}');
  // Update UI state
});

// Listen to daily pot updates
eventBus.on<DailyPotUpdatedEvent>().listen((event) {
  print('Daily pot progress: ${event.pot.uploadsToday}/${event.pot.uploadsRequired}');
  // Update progress bar
});

// Listen to pot claims
eventBus.on<DailyPotClaimedEvent>().listen((event) {
  print('Claimed ${event.amount} credits!');
  // Show celebration animation
});
```

### Emitting Events

Managers emit events automatically, but you can emit custom events:

```dart
// Emit a custom event
eventBus.emit(TrackingStateChangedEvent(
  isTracking: true,
  timestamp: DateTime.now(),
));
```

## Widget Integration

### Using with StatefulWidget

```dart
class MyWidget extends StatefulWidget {
  @override
  State<MyWidget> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  final _sessionManager = TrackingSessionManager.instance;
  StreamSubscription? _eventSubscription;

  @override
  void initState() {
    super.initState();

    // Listen to tracking state
    _sessionManager.isTracking.addListener(_onTrackingChanged);

    // Listen to events
    _eventSubscription = AppEventBus.instance
        .on<UploadSuccessEvent>()
        .listen(_onUploadSuccess);
  }

  void _onTrackingChanged() {
    setState(() {
      // Rebuild widget
    });
  }

  void _onUploadSuccess(UploadSuccessEvent event) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text('Upload successful!')),
    );
  }

  @override
  void dispose() {
    _sessionManager.isTracking.removeListener(_onTrackingChanged);
    _eventSubscription?.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final isTracking = _sessionManager.isTracking.value;
    return Text(isTracking ? 'Tracking' : 'Stopped');
  }
}
```

### Using with ListenableBuilder

```dart
class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListenableBuilder(
      listenable: TrackingSessionManager.instance.isTracking,
      builder: (context, _) {
        final isTracking = TrackingSessionManager.instance.isTracking.value;
        return Text(isTracking ? 'Tracking' : 'Stopped');
      },
    );
  }
}
```

### Using with StreamBuilder

```dart
class MyWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StreamBuilder<UploadSuccessEvent>(
      stream: AppEventBus.instance.on<UploadSuccessEvent>(),
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          final event = snapshot.data!;
          return Text('Last upload: ${event.samplesCount} samples');
        }
        return Text('No uploads yet');
      },
    );
  }
}
```

## Common Patterns

### Loading Cached Data First

```dart
// Pattern: Show cached data immediately, then refresh
Future<void> loadData() async {
  // 1. Load from cache (instant)
  final cached = await db.getCachedDailyPot(userId);
  if (cached != null) {
    setState(() => _pot = cached);
  }

  // 2. Fetch fresh data (background)
  try {
    final fresh = await api.getDailyPot();
    await db.cacheDailyPot(fresh);
    setState(() => _pot = fresh);
  } catch (e) {
    // Cache is still valid
    print('Failed to refresh, using cache: $e');
  }
}
```

### Optimistic Updates

```dart
// Pattern: Update UI immediately, sync in background
Future<void> recordUpload() async {
  // 1. Optimistic update (instant UI feedback)
  final optimistic = _pot.copyWith(
    uploadsToday: _pot.uploadsToday + 1,
  );
  setState(() => _pot = optimistic);
  await db.cacheDailyPot(optimistic);

  // 2. Confirm with backend (background)
  try {
    final confirmed = await api.recordUpload();
    await db.cacheDailyPot(confirmed);
    setState(() => _pot = confirmed);
  } catch (e) {
    // Rollback if needed
    print('Backend sync failed, optimistic update kept: $e');
  }
}
```

### Graceful Degradation

```dart
// Pattern: Work offline when possible
Future<List<Contribution>> getContributions() async {
  try {
    // Try backend first
    final fresh = await api.getContributions();
    await db.cacheContributions(fresh);
    return fresh;
  } catch (e) {
    // Fall back to cache
    print('Offline mode: $e');
    return await db.getContributions();
  }
}
```

## Migration Guide

### From Old Code to New Persistence Layer

**Before** (no state persistence):
```dart
class MyService {
  bool _isTracking = false;

  void start() {
    _isTracking = true;
    // State lost on app restart
  }
}
```

**After** (with persistence):
```dart
class MyService {
  final _sessionManager = TrackingSessionManager.instance;

  Future<void> start() async {
    await _sessionManager.startSession();
    // State persisted, survives restarts
  }

  bool get isTracking => _sessionManager.isTracking.value;
}
```

## Troubleshooting

### Database Migration Failed

```dart
// Check migration status
final db = await DatabaseHelper.instance.database;
final version = await db.getVersion();
print('Database version: $version');

// Force rebuild database (dev only)
await deleteDatabase('greengains.db');
```

### State Not Restoring

```dart
// Debug state restoration
await AppStateManager.instance.initialize();
final summary = await AppStateManager.instance.getStateSummary();
print('State: $summary');

// Export state for analysis
final json = await AppStateManager.instance.exportStateJson();
print(json);
```

### Session Not Recording

```dart
// Check active session
final session = await TrackingSessionManager.instance.getCurrentSession();
if (session == null) {
  print('No active session - forgot to call startSession()?');
} else {
  print('Active session: $session');
}

// Check session history
final history = await TrackingSessionManager.instance.getHistory();
print('Recent sessions: ${history.length}');
```

## Best Practices

1. **Always use manager classes**: Don't access `DatabaseHelper` directly
2. **Listen to events**: Use `AppEventBus` for reactive updates
3. **Show cached data first**: Load cache immediately, refresh in background
4. **Optimistic updates**: Update UI instantly, sync later
5. **Handle errors gracefully**: Fall back to cache on network errors
6. **Clean up listeners**: Always dispose `ValueNotifier` listeners
7. **Test offline mode**: Verify app works without network

## Performance Tips

1. **Use indexes**: All timestamp queries are indexed
2. **Batch operations**: Use transactions for multiple writes
3. **Limit queries**: Use `LIMIT` clause for large tables
4. **Cache aggressively**: Database is fast, but caching is faster
5. **Stream-based UI**: Avoid holding large datasets in memory

## Summary

The persistence layer provides:

- **TrackingSessionManager**: Manage tracking sessions
- **DailyPotService**: Offline-first daily pot
- **AppStateManager**: Centralized state coordination
- **DatabaseHelper**: Direct database access (advanced)
- **AppEventBus**: Reactive event system

All designed to work together for a seamless, offline-first experience.
