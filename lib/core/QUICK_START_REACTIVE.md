# Quick Start: Reactive Patterns

## Common Scenarios

### Scenario 1: Show "X time ago" timestamp
```dart
import '../widgets/time_ago_text.dart';

// In your widget build method:
TimeAgoText(
  timestamp: myDateTime,
  prefix: 'Last upload: ',
  style: TextStyle(color: Colors.green),
)
// Output: "Last upload: 5m ago" (auto-updates every minute)
```

### Scenario 2: React to upload success
```dart
import 'dart:async';
import '../core/events/app_events.dart';

class MyWidget extends StatefulWidget {
  @override
  State<MyWidget> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<MyWidget> {
  StreamSubscription<UploadSuccessEvent>? _sub;

  @override
  void initState() {
    super.initState();

    // Listen for upload success
    _sub = AppEventBus.instance
        .on<UploadSuccessEvent>()
        .listen((event) {
          // Handle event
          print('Upload succeeded: ${event.samplesCount} samples');
          _refreshMyData();
        });
  }

  @override
  void dispose() {
    _sub?.cancel(); // CRITICAL: Always cancel!
    super.dispose();
  }

  void _refreshMyData() {
    // Your refresh logic
  }
}
```

### Scenario 3: Display live upload status
```dart
import '../services/location/foreground_location_service.dart';

// In your build method:
ValueListenableBuilder<DateTime?>(
  valueListenable: ForegroundLocationService.instance.uploadStatus.lastUpload,
  builder: (context, lastUpload, _) {
    if (lastUpload == null) {
      return Text('No uploads yet');
    }
    return TimeAgoText(
      timestamp: lastUpload,
      prefix: 'Last: ',
    );
  },
)
```

### Scenario 4: Show uploading spinner
```dart
ValueListenableBuilder<bool>(
  valueListenable: ForegroundLocationService.instance.uploadStatus.uploading,
  builder: (context, uploading, _) {
    return uploading
        ? CircularProgressIndicator()
        : Icon(Icons.check);
  },
)
```

### Scenario 5: Emit an event
```dart
import '../core/events/app_events.dart';

// After saving stats to database:
AppEventBus.instance.emit(StatsUpdatedEvent(myStats));

// After upload completes:
AppEventBus.instance.emit(UploadSuccessEvent(
  samplesCount: 100,
  timestamp: DateTime.now(),
));
```

### Scenario 6: Listen to multiple events
```dart
StreamSubscription<UploadSuccessEvent>? _uploadSub;
StreamSubscription<UploadFailedEvent>? _failureSub;

@override
void initState() {
  super.initState();

  _uploadSub = AppEventBus.instance
      .on<UploadSuccessEvent>()
      .listen(_handleSuccess);

  _failureSub = AppEventBus.instance
      .on<UploadFailedEvent>()
      .listen(_handleFailure);
}

@override
void dispose() {
  _uploadSub?.cancel();
  _failureSub?.cancel();
  super.dispose();
}
```

## Anti-Patterns (DON'T DO THIS)

### DON'T: Create your own timer for time updates
```dart
// BAD - creates duplicate timers
Timer.periodic(Duration(seconds: 60), (_) {
  setState(() {});
});

// GOOD - use TimeAgoText widget
TimeAgoText(timestamp: myTime)
```

### DON'T: Manually refresh on navigation
```dart
// BAD - manual polling
@override
void didChangeDependencies() {
  super.didChangeDependencies();
  _refreshStats(); // Don't do this!
}

// GOOD - react to events
@override
void initState() {
  super.initState();
  AppEventBus.instance.on<StatsUpdatedEvent>().listen((event) {
    setState(() => _stats = event.stats);
  });
}
```

### DON'T: Forget to cancel subscriptions
```dart
// BAD - memory leak!
@override
void initState() {
  super.initState();
  AppEventBus.instance.on<UploadSuccessEvent>().listen(_handle);
  // No dispose() to cancel!
}

// GOOD
StreamSubscription? _sub;

@override
void initState() {
  super.initState();
  _sub = AppEventBus.instance.on<UploadSuccessEvent>().listen(_handle);
}

@override
void dispose() {
  _sub?.cancel();
  super.dispose();
}
```

## Cheat Sheet

| Task | Solution |
|------|----------|
| Show "5m ago" | `TimeAgoText(timestamp: myTime)` |
| React to upload | Listen to `UploadSuccessEvent` |
| Show upload spinner | `ValueListenableBuilder` on `uploadStatus.uploading` |
| Emit event | `AppEventBus.instance.emit(MyEvent())` |
| Cancel listener | `_sub?.cancel()` in `dispose()` |

## Performance Tips

1. Only listen to events you need
2. Always cancel subscriptions in dispose()
3. Use ValueListenableBuilder for simple state
4. Use TimeAgoText instead of custom timers
5. Emit events sparingly (not on every frame)

## Need Help?

See full documentation: `lib/core/REACTIVE_ARCHITECTURE.md`
