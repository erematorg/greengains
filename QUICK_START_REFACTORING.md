# Quick Start: Architecture Refactoring

**For developers ready to start implementing immediately**

---

## Step 1: Add Dependencies (5 minutes)

### 1.1 Update pubspec.yaml

```yaml
dependencies:
  # ... existing dependencies ...

  # Add this:
  get_it: ^7.6.0
```

### 1.2 Install
```bash
flutter pub get
```

---

## Step 2: Initialize DI Container (10 minutes)

### 2.1 Update main.dart

```dart
// ADD THIS IMPORT:
import 'core/di/service_locator.dart';

// UPDATE main() function:
void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // ADD THIS LINE:
  await setupServiceLocator();

  runApp(const MyApp());
}
```

### 2.2 Test
Run the app. You should see console output:
```
[ServiceLocator] ✅ DatabaseHelper registered
[ServiceLocator] ✅ AppEventBus registered
... etc ...
```

---

## Step 3: Add New Events (15 minutes)

### 3.1 Update lib/core/events/app_events.dart

Add these at the bottom of the file (before the closing brace):

```dart
/// Tile coverage updated
class TileCoverageUpdatedEvent extends AppEvent {
  final int dayTiles;
  final int nightTiles;
  final int totalTiles;

  TileCoverageUpdatedEvent({
    required this.dayTiles,
    required this.nightTiles,
    required this.totalTiles,
  });

  @override
  String get debugInfo => 'day=$dayTiles, night=$nightTiles, total=$totalTiles';
}

/// Session started
class SessionStartedEvent extends AppEvent {
  final DateTime timestamp;

  SessionStartedEvent({required this.timestamp});

  @override
  String get debugInfo => 'started=${timestamp.toIso8601String()}';
}

/// Session stopped
class SessionStoppedEvent extends AppEvent {
  final DateTime timestamp;
  final String reason;

  SessionStoppedEvent({
    required this.timestamp,
    required this.reason,
  });

  @override
  String get debugInfo => 'reason=$reason';
}

/// App resumed from background
class AppResumedEvent extends AppEvent {
  final DateTime timestamp;

  AppResumedEvent({required this.timestamp});

  @override
  String get debugInfo => 'resumed=${timestamp.toIso8601String()}';
}
```

---

## Step 4: Emit AppResumed Event (10 minutes)

### 4.1 Update lib/app_shell.dart

```dart
// CHANGE 1: Add WidgetsBindingObserver to _AppShellState
class _AppShellState extends State<AppShell> with WidgetsBindingObserver {
  // ... existing code ...

  @override
  void initState() {
    super.initState();
    _pageController = PageController(initialPage: _currentIndex);
    TimeAgoService.instance.start();

    // ADD THIS LINE:
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    // ADD THIS LINE:
    WidgetsBinding.instance.removeObserver(this);
    _pageController.dispose();
    super.dispose();
  }

  // ADD THIS METHOD:
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      AppEventBus.instance.emit(AppResumedEvent(
        timestamp: DateTime.now(),
      ));
    }
  }

  // ... rest of code unchanged ...
}
```

### 4.2 Test
Run app, minimize it, then restore. You should see:
```
[EventBus] AppResumedEvent: resumed=2026-01-13T...
```

---

## Step 5: Create Your First Controller (30 minutes)

### 5.1 Create lib/controllers/home_controller.dart

```dart
import 'package:flutter/foundation.dart';
import '../core/controllers/base_controller.dart';
import '../core/utils/async_result.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';
import '../services/location/foreground_location_service.dart';
import '../core/events/app_events.dart';

class HomeController extends BaseController {
  final ContributionRepository _contributionRepo;
  final ForegroundLocationService _locationService;

  HomeController({
    required ContributionRepository contributionRepo,
    required ForegroundLocationService locationService,
  })  : _contributionRepo = contributionRepo,
        _locationService = locationService {
    init();
  }

  // State
  final _tileCoverageState = ValueNotifier<AsyncResult<TileCoverageStats>>(
    const Loading(),
  );

  ValueListenable<AsyncResult<TileCoverageStats>> get tileCoverageState =>
      _tileCoverageState;

  ValueListenable<bool> get isServiceRunning => _locationService.isRunning;

  @override
  void init() {
    loadTileCoverage();

    // Event listeners (auto-cleanup on dispose)
    listenToEvent<UploadSuccessEvent>(_onUploadSuccess);
    listenToEvent<AppResumedEvent>(_onAppResumed);
  }

  Future<void> loadTileCoverage() async {
    _tileCoverageState.value = const Loading();
    try {
      final stats = await _contributionRepo.getTodayTileCoverage();
      _tileCoverageState.value = Success(stats);
    } catch (e) {
      _tileCoverageState.value = Error('Failed to load: $e');
    }
  }

  void _onUploadSuccess(UploadSuccessEvent event) {
    debugPrint('[HomeController] Upload success, refreshing');
    loadTileCoverage();
  }

  void _onAppResumed(AppResumedEvent event) {
    debugPrint('[HomeController] App resumed, refreshing');
    loadTileCoverage();
  }

  @override
  void dispose() {
    _tileCoverageState.dispose();
    super.dispose();
  }
}
```

### 5.2 Register Controller in DI

Edit `lib/core/di/service_locator.dart`:

```dart
// ADD THIS IMPORT:
import '../../controllers/home_controller.dart';

// IN setupServiceLocator(), ADD THIS AFTER REPOSITORIES:

// ===== CONTROLLERS (Factories) =====
getIt.registerFactory<HomeController>(
  () => HomeController(
    contributionRepo: getIt<ContributionRepository>(),
    locationService: getIt<ForegroundLocationService>(),
  ),
);
print('[ServiceLocator] ✅ HomeController registered');
```

---

## Step 6: Test Your Controller (15 minutes)

### 6.1 Create a Simple Test Widget

Create `lib/screens/test_controller_screen.dart`:

```dart
import 'package:flutter/material.dart';
import '../core/di/service_locator.dart';
import '../core/utils/async_result.dart';
import '../controllers/home_controller.dart';
import '../data/models/contribution_stats.dart';

class TestControllerScreen extends StatefulWidget {
  const TestControllerScreen({super.key});

  @override
  State<TestControllerScreen> createState() => _TestControllerScreenState();
}

class _TestControllerScreenState extends State<TestControllerScreen> {
  late final HomeController _controller;

  @override
  void initState() {
    super.initState();
    _controller = getIt<HomeController>();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Controller Test')),
      body: ValueListenableBuilder<AsyncResult<TileCoverageStats>>(
        valueListenable: _controller.tileCoverageState,
        builder: (context, result, _) {
          return result.when(
            success: (stats) => Center(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text('✅ Controller Works!',
                    style: Theme.of(context).textTheme.headlineMedium,
                  ),
                  const SizedBox(height: 16),
                  Text('Day Tiles: ${stats.dayTiles}'),
                  Text('Night Tiles: ${stats.nightTiles}'),
                  Text('Total: ${stats.totalTiles}'),
                  const SizedBox(height: 32),
                  ElevatedButton(
                    onPressed: () => _controller.loadTileCoverage(),
                    child: const Text('Refresh'),
                  ),
                ],
              ),
            ),
            loading: () => const Center(
              child: CircularProgressIndicator(),
            ),
            error: (msg) => Center(
              child: Text('Error: $msg'),
            ),
          );
        },
      ),
    );
  }
}
```

### 6.2 Navigate to Test Screen

Temporarily add a button in HomeScreen or AppShell to navigate:

```dart
ElevatedButton(
  onPressed: () {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (_) => const TestControllerScreen(),
      ),
    );
  },
  child: const Text('Test Controller'),
)
```

### 6.3 Test
- Run app
- Tap "Test Controller" button
- Should see loading indicator, then stats
- Tap "Refresh" button - should reload
- Check console for event logs

---

## Step 7: Refactor One Screen (1-2 hours)

Now you're ready to refactor a real screen!

### Option A: Start with StatisticsScreen (Easier)
Follow the pattern from Step 5, but for statistics.

### Option B: Refactor HomeScreen (More Complex)
Follow the full example in `REFACTORING_IMPLEMENTATION.md`

---

## Common Patterns Cheat Sheet

### Pattern 1: Basic Controller Setup

```dart
class MyController extends BaseController {
  final MyRepository _repo;

  MyController({required MyRepository repo}) : _repo = repo {
    init();
  }

  final _dataState = ValueNotifier<AsyncResult<MyData>>(const Loading());
  ValueListenable<AsyncResult<MyData>> get dataState => _dataState;

  @override
  void init() {
    loadData();
    listenToEvent<SomeEvent>(_handleEvent);
  }

  Future<void> loadData() async {
    _dataState.value = const Loading();
    try {
      final data = await _repo.getData();
      _dataState.value = Success(data);
    } catch (e) {
      _dataState.value = Error('Failed: $e');
    }
  }

  void _handleEvent(SomeEvent event) {
    // React to event
  }

  @override
  void dispose() {
    _dataState.dispose();
    super.dispose();
  }
}
```

### Pattern 2: Widget Using Controller

```dart
class MyScreen extends StatefulWidget {
  const MyScreen({super.key});

  @override
  State<MyScreen> createState() => _MyScreenState();
}

class _MyScreenState extends State<MyScreen> {
  late final MyController _controller;

  @override
  void initState() {
    super.initState();
    _controller = getIt<MyController>();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ValueListenableBuilder<AsyncResult<MyData>>(
        valueListenable: _controller.dataState,
        builder: (context, result, _) {
          return result.when(
            success: (data) => MyDataWidget(data),
            loading: () => LoadingWidget(),
            error: (msg) => ErrorWidget(msg),
          );
        },
      ),
    );
  }
}
```

### Pattern 3: Registering in DI

```dart
// In lib/core/di/service_locator.dart

// Import controller
import '../../controllers/my_controller.dart';

// In setupServiceLocator():
getIt.registerFactory<MyController>(
  () => MyController(
    repo: getIt<MyRepository>(),
  ),
);
```

---

## Debugging Tips

### Check DI Registration
```dart
print('HomeController registered: ${isServiceRegistered<HomeController>()}');
```

### Check Event Flow
Events automatically log to console:
```
[EventBus] UploadSuccessEvent: samples=100
[HomeController] Upload success, refreshing
```

### Check Controller State
```dart
print('State: ${_controller.dataState.value}');
// Output: Success(MyData(...)) or Loading() or Error(...)
```

---

## What NOT to Do

❌ **DON'T**: Access repositories directly from widgets
```dart
final _repo = ContributionRepository(); // WRONG
```

❌ **DON'T**: Use setState for async data
```dart
setState(() => _loading = true); // WRONG - use controller
```

❌ **DON'T**: Poll in lifecycle methods
```dart
@override
void didChangeAppLifecycleState(AppLifecycleState state) {
  if (state == AppLifecycleState.resumed) {
    _loadData(); // WRONG - listen to events
  }
}
```

❌ **DON'T**: Create timers in widgets
```dart
Timer.periodic(Duration(minutes: 1), (_) { }); // WRONG - use service
```

---

## Getting Help

### Read These First
1. `ARCHITECTURE_REFACTOR_SUMMARY.md` - Overview
2. `REFACTORING_IMPLEMENTATION.md` - Detailed guide
3. `ARCHITECTURE_AUDIT.md` - Why we're doing this

### Examples in Codebase
- `lib/core/utils/async_result.dart` - AsyncResult examples
- `lib/core/controllers/base_controller.dart` - Controller base class
- `lib/core/services/time_ago_service.dart` - Good service example
- `lib/widgets/daily_pot_icon.dart` - Good reactive widget example

---

## Progress Checklist

### Phase 1: Foundation ✅
- [x] Created utility classes
- [x] Created base controller
- [x] Created DI setup
- [ ] Added get_it dependency
- [ ] Added new events
- [ ] Initialized DI in main
- [ ] Added AppResumed event

### Phase 2: First Controller
- [ ] Created HomeController
- [ ] Registered in DI
- [ ] Tested controller independently
- [ ] Verified event listeners work

### Phase 3: First Refactor
- [ ] Refactored one screen to use controller
- [ ] Removed business logic from widget
- [ ] Verified reactive updates work
- [ ] Manual testing passed

### Phase 4: Complete Migration
- [ ] All screens use controllers
- [ ] All widgets are pure UI
- [ ] No manual polling anywhere
- [ ] All state changes emit events

---

## Success! You've Started

Once you complete Step 6, you have:
- ✅ Working dependency injection
- ✅ Event bus with new events
- ✅ First controller implemented
- ✅ Understanding of the pattern

Now continue with the full screens following `REFACTORING_IMPLEMENTATION.md`!

---

**Estimated Time for Full Refactor**: 3-4 weeks
**Estimated Time to See Benefits**: After first screen refactor (Week 2)

Good luck! 🚀
