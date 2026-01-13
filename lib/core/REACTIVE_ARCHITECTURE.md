# Reactive Architecture Guide

## Overview
This app uses a centralized event-driven architecture for real-time UI updates without polling or manual refreshes.

## Core Components

### 1. AppEventBus (`lib/core/events/app_events.dart`)
Centralized event system for app-wide reactive updates.

**Events:**
- `UploadSuccessEvent` - Emitted when data upload completes
- `UploadFailedEvent` - Emitted when upload fails
- `StatsUpdatedEvent` - Emitted when contribution stats are refreshed
- `TrackingStateChangedEvent` - Emitted when foreground service starts/stops
- `DailyPotUpdatedEvent` - Emitted when daily pot state changes
- `DailyPotClaimedEvent` - Emitted when daily pot is claimed
- `LocationPermissionChangedEvent` - Emitted when location permission changes

**Usage Pattern:**
```dart
// Emit event
AppEventBus.instance.emit(UploadSuccessEvent(
  samplesCount: 100,
  timestamp: DateTime.now(),
));

// Listen to events
StreamSubscription? _sub;

@override
void initState() {
  super.initState();
  _sub = AppEventBus.instance
      .on<UploadSuccessEvent>()
      .listen(_handleUploadSuccess);
}

void _handleUploadSuccess(UploadSuccessEvent event) {
  // React to event
  _refreshData();
}

@override
void dispose() {
  _sub?.cancel();
  super.dispose();
}
```

### 2. TimeAgoService (`lib/core/services/time_ago_service.dart`)
Centralized timer service for "time ago" text updates.

**Why?**
- Single timer instead of one per widget
- Updates once per minute (efficient)
- All TimeAgoText widgets listen to the same tick

**Lifecycle:**
- Started in `AppShell.initState()`
- Runs for app lifetime
- Updates every 60 seconds

### 3. TimeAgoText Widget (`lib/widgets/time_ago_text.dart`)
Stateless widget that automatically updates "X time ago" text.

**Usage:**
```dart
TimeAgoText(
  timestamp: lastUploadTime,
  prefix: 'Last upload: ',
  style: TextStyle(color: Colors.green),
)
```

**Output Examples:**
- "Last upload: just now"
- "Last upload: 5m ago"
- "Last upload: 2h ago"
- "Last upload: 3d ago"

## Reactive Patterns by Screen

### Home Screen
**Reactive to:**
- `UploadSuccessEvent` - Refreshes stats card
- `StatsUpdatedEvent` - Updates contribution stats display
- `uploadStatus.lastUpload` ValueNotifier - Updates "Last upload: Xm ago"
- `uploadStatus.uploading` ValueNotifier - Shows upload spinner
- `uploadStatus.lastError` ValueNotifier - Shows error messages
- TimeAgoService tick - Updates timestamp every minute

**Implementation:**
- Uses `ValueListenableBuilder` for upload status
- Uses `TimeAgoText` for auto-updating timestamps
- ContributionStatsCard listens to `UploadSuccessEvent`

### Statistics Screen
**Reactive to:**
- `UploadSuccessEvent` - Reloads stats from database
- `StatsUpdatedEvent` - Updates displayed stats

**Implementation:**
```dart
StreamSubscription<UploadSuccessEvent>? _uploadSuccessSub;
StreamSubscription<StatsUpdatedEvent>? _statsUpdatedSub;

@override
void initState() {
  super.initState();
  _loadStats();

  _uploadSuccessSub = AppEventBus.instance
      .on<UploadSuccessEvent>()
      .listen(_onUploadSuccess);

  _statsUpdatedSub = AppEventBus.instance
      .on<StatsUpdatedEvent>()
      .listen(_onStatsUpdated);
}
```

### Contribution Stats Card
**Reactive to:**
- `UploadSuccessEvent` - Refreshes stats with pulse animation

**Emits:**
- `StatsUpdatedEvent` - After loading stats from database

**Implementation:**
- Listens to AppEventBus in `initState()`
- Triggers database query on upload success
- Emits StatsUpdatedEvent so other screens can react

## Data Flow

### Upload Success Flow
```
1. Native Android uploads data to backend
2. NativeBackendUploader calls Flutter via MethodChannel
3. ForegroundLocationService._handleNativeUploadStatus()
4. Emits UploadSuccessEvent via AppEventBus
5. All listeners react:
   - ContributionStatsCard refreshes stats
   - StatisticsScreen reloads data
   - Home screen shows success snackbar
6. ContributionStatsCard emits StatsUpdatedEvent
7. StatisticsScreen receives fresh stats
```

### Timestamp Update Flow
```
1. TimeAgoService started in AppShell.initState()
2. Timer ticks every 60 seconds
3. ValueNotifier increments
4. All TimeAgoText widgets rebuild
5. Timestamps show updated "Xm ago" text
```

## Best Practices

### DO:
- Use AppEventBus for cross-screen state changes
- Use ValueNotifier for simple local state
- Use StreamSubscription for event listeners
- Cancel subscriptions in dispose()
- Emit StatsUpdatedEvent after database queries
- Use TimeAgoText for relative timestamps

### DON'T:
- Create separate timers for time updates
- Poll for state changes
- Manually refresh stats on navigation
- Use setState() for cross-widget updates
- Create new events without documenting them

## Performance Considerations

1. **Single Timer**: TimeAgoService uses one timer for all widgets
2. **Minimal Rebuilds**: Only listening widgets rebuild on events
3. **Efficient Updates**: Timestamps update at 60s intervals (not every second)
4. **Event Pruning**: Listeners only receive events they subscribe to

## Debugging

Enable event logging:
```dart
// Events automatically log to console
[EventBus] UploadSuccessEvent: samples=100, geohash=abc123
[EventBus] StatsUpdatedEvent: uploads=50, today=5, streak=3
```

Check timer status:
```dart
[TimeAgoService] Started centralized timer
```

## Future Enhancements

- Add debouncing to high-frequency events
- Implement event replay for late subscribers
- Add typed event filters for complex queries
- Create event middleware for logging/analytics
