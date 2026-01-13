# GreenGains Refactoring Implementation Guide

**Complete step-by-step guide for implementing the architecture refactoring**

---

## Table of Contents
1. [Phase 1: Foundation Setup](#phase-1-foundation-setup)
2. [Phase 2: Controller Layer](#phase-2-controller-layer)
3. [Phase 3: Widget Refactoring](#phase-3-widget-refactoring)
4. [Phase 4: Complete Event Bus Migration](#phase-4-complete-event-bus-migration)
5. [Testing Checklist](#testing-checklist)

---

## Phase 1: Foundation Setup

### Step 1.1: Add Dependencies

**File**: `pubspec.yaml`

```yaml
dependencies:
  # Existing dependencies...

  # Add for dependency injection
  get_it: ^7.6.0

  # Add for better state management
  provider: ^6.1.1  # Optional, if you want Provider pattern
```

Run: `flutter pub get`

### Step 1.2: Create Error Handling Utilities

**File**: `lib/core/utils/async_result.dart` (NEW)

```dart
/// Type-safe result wrapper for async operations
/// Eliminates try-catch boilerplate
sealed class AsyncResult<T> {
  const AsyncResult();
}

class Success<T> extends AsyncResult<T> {
  final T data;
  const Success(this.data);
}

class Loading<T> extends AsyncResult<T> {
  const Loading();
}

class Error<T> extends AsyncResult<T> {
  final String message;
  final Exception? exception;

  const Error(this.message, [this.exception]);
}

// Extension for easy pattern matching
extension AsyncResultExtension<T> on AsyncResult<T> {
  R when<R>({
    required R Function(T data) success,
    required R Function() loading,
    required R Function(String message) error,
  }) {
    return switch (this) {
      Success(data: final data) => success(data),
      Loading() => loading(),
      Error(message: final msg) => error(msg),
    };
  }

  T? get dataOrNull => switch (this) {
    Success(data: final data) => data,
    _ => null,
  };

  bool get isSuccess => this is Success<T>;
  bool get isLoading => this is Loading<T>;
  bool get isError => this is Error<T>;
}
```

### Step 1.3: Create Dependency Injection Container

**File**: `lib/core/di/service_locator.dart` (NEW)

```dart
import 'package:get_it/get_it.dart';
import '../../data/local/database_helper.dart';
import '../../data/repositories/contribution_repository.dart';
import '../../services/location/foreground_location_service.dart';
import '../../services/daily_pot_service.dart';
import '../../core/events/app_events.dart';
import '../../core/app_preferences.dart';

/// Global service locator instance
final getIt = GetIt.instance;

/// Initialize all dependencies
/// Call this ONCE in main() before runApp()
Future<void> setupServiceLocator() async {
  // Core services (singletons)
  getIt.registerSingleton<DatabaseHelper>(DatabaseHelper.instance);
  getIt.registerSingleton<AppEventBus>(AppEventBus.instance);
  getIt.registerSingleton<AppPreferences>(AppPreferences.instance);

  // Business logic services (singletons)
  getIt.registerSingleton<ForegroundLocationService>(
    ForegroundLocationService.instance,
  );
  getIt.registerSingleton<DailyPotService>(
    DailyPotService.instance,
  );

  // Repositories (factories - lightweight, can create new instances)
  getIt.registerFactory<ContributionRepository>(
    () => ContributionRepository(),
  );

  print('[ServiceLocator] ✅ All services registered');
}

/// Extension for easier access in widgets
extension ServiceLocatorExtension on Object {
  T getService<T extends Object>() => getIt<T>();
}
```

### Step 1.4: Add Missing Events

**File**: `lib/core/events/app_events.dart` (UPDATE)

Add these new events to the existing file:

```dart
// ADD THESE NEW EVENTS:

/// Tile coverage updated - refresh map/coverage displays
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

/// Tracking session started
class SessionStartedEvent extends AppEvent {
  final DateTime timestamp;

  SessionStartedEvent({required this.timestamp});

  @override
  String get debugInfo => 'started=${timestamp.toIso8601String()}';
}

/// Tracking session stopped
class SessionStoppedEvent extends AppEvent {
  final DateTime timestamp;
  final String reason;
  final Duration? duration;

  SessionStoppedEvent({
    required this.timestamp,
    required this.reason,
    this.duration,
  });

  @override
  String get debugInfo => 'reason=$reason, duration=${duration?.inMinutes}m';
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

## Phase 2: Controller Layer

### Step 2.1: Create Base Controller

**File**: `lib/core/controllers/base_controller.dart` (NEW)

```dart
import 'dart:async';
import 'package:flutter/foundation.dart';
import '../events/app_events.dart';

/// Base controller with common functionality
/// All screen controllers should extend this
abstract class BaseController extends ChangeNotifier {
  final List<StreamSubscription> _subscriptions = [];
  bool _disposed = false;

  /// Check if controller is disposed
  bool get isDisposed => _disposed;

  /// Subscribe to event bus events
  /// Automatically cancels subscription on dispose
  void listenToEvent<T extends AppEvent>(
    void Function(T event) handler,
  ) {
    final sub = AppEventBus.instance.on<T>().listen(handler);
    _subscriptions.add(sub);
  }

  /// Emit event to event bus
  void emitEvent(AppEvent event) {
    AppEventBus.instance.emit(event);
  }

  /// Safe state update - only notifies if not disposed
  void updateState() {
    if (!_disposed) {
      notifyListeners();
    }
  }

  @override
  void dispose() {
    _disposed = true;
    // Cancel all event subscriptions
    for (final sub in _subscriptions) {
      sub.cancel();
    }
    _subscriptions.clear();
    super.dispose();
  }
}
```

### Step 2.2: Create Home Controller

**File**: `lib/controllers/home_controller.dart` (NEW)

```dart
import 'package:flutter/foundation.dart';
import '../core/controllers/base_controller.dart';
import '../core/utils/async_result.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';
import '../services/location/foreground_location_service.dart';
import '../core/events/app_events.dart';

/// Controller for Home Screen
/// Manages all home screen state and business logic
class HomeController extends BaseController {
  final ContributionRepository _contributionRepo;
  final ForegroundLocationService _locationService;

  HomeController({
    required ContributionRepository contributionRepo,
    required ForegroundLocationService locationService,
  })  : _contributionRepo = contributionRepo,
        _locationService = locationService {
    _init();
  }

  // Reactive state
  final _tileCoverageState = ValueNotifier<AsyncResult<TileCoverageStats>>(
    const Loading(),
  );

  ValueListenable<AsyncResult<TileCoverageStats>> get tileCoverageState =>
      _tileCoverageState;

  // Expose service state (read-only)
  ValueListenable<bool> get isServiceRunning => _locationService.isRunning;

  void _init() {
    // Load initial data
    loadTileCoverage();

    // Setup event listeners
    listenToEvent<UploadSuccessEvent>(_onUploadSuccess);
    listenToEvent<AppResumedEvent>(_onAppResumed);
    listenToEvent<SessionStartedEvent>(_onSessionStarted);
    listenToEvent<SessionStoppedEvent>(_onSessionStopped);
  }

  /// Load tile coverage from repository
  Future<void> loadTileCoverage() async {
    _tileCoverageState.value = const Loading();

    try {
      final stats = await _contributionRepo.getTodayTileCoverage();
      _tileCoverageState.value = Success(stats);

      // Emit event so other parts of app can react
      emitEvent(TileCoverageUpdatedEvent(
        dayTiles: stats.dayTiles,
        nightTiles: stats.nightTiles,
        totalTiles: stats.totalTiles,
      ));
    } catch (e) {
      _tileCoverageState.value = Error('Failed to load tile coverage: $e');
    }
  }

  /// Handle upload success - reload data
  void _onUploadSuccess(UploadSuccessEvent event) {
    debugPrint('[HomeController] Upload success, refreshing data');
    loadTileCoverage();
  }

  /// Handle app resumed from background
  void _onAppResumed(AppResumedEvent event) {
    debugPrint('[HomeController] App resumed, refreshing data');
    loadTileCoverage();
  }

  /// Handle tracking session started
  void _onSessionStarted(SessionStartedEvent event) {
    debugPrint('[HomeController] Session started');
    updateState();
  }

  /// Handle tracking session stopped
  void _onSessionStopped(SessionStoppedEvent event) {
    debugPrint('[HomeController] Session stopped: ${event.reason}');
    updateState();
  }

  @override
  void dispose() {
    _tileCoverageState.dispose();
    super.dispose();
  }
}
```

### Step 2.3: Create Statistics Controller

**File**: `lib/controllers/statistics_controller.dart` (NEW)

```dart
import 'package:flutter/foundation.dart';
import '../core/controllers/base_controller.dart';
import '../core/utils/async_result.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';
import '../core/events/app_events.dart';

/// Controller for Statistics Screen
/// Manages statistics state and reactive updates
class StatisticsController extends BaseController {
  final ContributionRepository _contributionRepo;

  StatisticsController({
    required ContributionRepository contributionRepo,
  }) : _contributionRepo = contributionRepo {
    _init();
  }

  // Reactive state
  final _statsState = ValueNotifier<AsyncResult<ContributionStats>>(
    const Loading(),
  );

  ValueListenable<AsyncResult<ContributionStats>> get statsState => _statsState;

  void _init() {
    // Load initial stats
    loadStats();

    // Setup event listeners
    listenToEvent<UploadSuccessEvent>(_onUploadSuccess);
    listenToEvent<StatsUpdatedEvent>(_onStatsUpdated);
    listenToEvent<AppResumedEvent>(_onAppResumed);
  }

  /// Load statistics from repository
  Future<void> loadStats() async {
    _statsState.value = const Loading();

    try {
      final stats = await _contributionRepo.getStats();
      _statsState.value = Success(stats);

      // Emit event for other listeners
      emitEvent(StatsUpdatedEvent(stats));
    } catch (e) {
      _statsState.value = Error('Failed to load statistics: $e');
    }
  }

  /// Handle upload success
  void _onUploadSuccess(UploadSuccessEvent event) {
    debugPrint('[StatisticsController] Upload success, reloading stats');
    loadStats();
  }

  /// Handle stats updated from elsewhere
  void _onStatsUpdated(StatsUpdatedEvent event) {
    debugPrint('[StatisticsController] Stats updated externally');
    _statsState.value = Success(event.stats);
  }

  /// Handle app resumed
  void _onAppResumed(AppResumedEvent event) {
    debugPrint('[StatisticsController] App resumed, reloading stats');
    loadStats();
  }

  @override
  void dispose() {
    _statsState.dispose();
    super.dispose();
  }
}
```

### Step 2.4: Register Controllers in DI

**File**: `lib/core/di/service_locator.dart` (UPDATE)

Add to `setupServiceLocator()`:

```dart
// Controllers (factories - new instance per screen)
getIt.registerFactory<HomeController>(
  () => HomeController(
    contributionRepo: getIt<ContributionRepository>(),
    locationService: getIt<ForegroundLocationService>(),
  ),
);

getIt.registerFactory<StatisticsController>(
  () => StatisticsController(
    contributionRepo: getIt<ContributionRepository>(),
  ),
);
```

---

## Phase 3: Widget Refactoring

### Step 3.1: Refactor HomeScreen

**File**: `lib/screens/home_screen.dart` (REFACTOR)

Key changes:
1. Remove all business logic
2. Inject controller via DI
3. Use controller for all state
4. Remove manual data fetching
5. Remove lifecycle polling

```dart
// BEFORE (720 lines with business logic)
class _HomeScreenState extends State<HomeScreen> with WidgetsBindingObserver {
  final _contributionRepo = ContributionRepository(); // ❌ Direct access
  TileCoverageStats? _tileCoverage;
  bool _tileCoverageLoading = true;

  @override
  void initState() {
    super.initState();
    _loadTileCoverage(); // ❌ Manual loading
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      _loadTileCoverage(); // ❌ Manual refresh
    }
  }

  Future<void> _loadTileCoverage() async {
    // ❌ Business logic in widget
    setState(() => _tileCoverageLoading = true);
    try {
      final stats = await _contributionRepo.getTodayTileCoverage();
      setState(() {
        _tileCoverage = stats;
        _tileCoverageLoading = false;
      });
    } catch (_) {
      setState(() => _tileCoverageLoading = false);
    }
  }
}

// AFTER (300 lines, pure UI)
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final HomeController _controller;

  @override
  void initState() {
    super.initState();
    // Inject controller from DI
    _controller = getIt<HomeController>();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = context.theme;
    final isDark = context.isDarkMode;

    return Scaffold(
      appBar: AppBar(),
      body: Stack(
        children: [
          ListView(
            padding: const EdgeInsets.symmetric(horizontal: AppTheme.spaceMd),
            children: [
              const SizedBox(height: AppTheme.spaceLg),
              const ServiceControlButton(),
              const SizedBox(height: AppTheme.spaceSm),

              // Upload status (listen to service directly)
              ListenableBuilder(
                listenable: _controller.isServiceRunning,
                builder: (context, _) => _buildUploadStatus(theme),
              ),

              const SizedBox(height: AppTheme.spaceLg),

              // Contribution Statistics
              const ContributionStatsCard(),
              const SizedBox(height: AppTheme.spaceMd),

              // Coverage Summary (listen to controller)
              ValueListenableBuilder<AsyncResult<TileCoverageStats>>(
                valueListenable: _controller.tileCoverageState,
                builder: (context, result, _) {
                  return result.when(
                    success: (stats) => _buildCoverageSummary(stats, theme, isDark),
                    loading: () => _buildCoverageLoading(theme, isDark),
                    error: (msg) => _buildCoverageError(msg, theme, isDark),
                  );
                },
              ),

              const SizedBox(height: AppTheme.spaceMd),

              // Sensor data (unchanged, already reactive)
              _buildSensorSection(theme, isDark),
            ],
          ),

          // Daily pot icon
          const Positioned(
            top: 70,
            right: 16,
            child: DailyPotIcon(),
          ),
        ],
      ),
    );
  }

  // All _build methods are now pure UI functions
  Widget _buildCoverageSummary(
    TileCoverageStats stats,
    ThemeData theme,
    bool isDark,
  ) {
    // Pure rendering logic only
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: Row(
        children: [
          // ... UI code only ...
        ],
      ),
    );
  }

  Widget _buildCoverageLoading(ThemeData theme, bool isDark) {
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: const Center(child: CircularProgressIndicator()),
    );
  }

  Widget _buildCoverageError(String message, ThemeData theme, bool isDark) {
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: Text('Error: $message', style: theme.textTheme.bodyMedium),
    );
  }

  // ... other pure UI methods ...
}
```

### Step 3.2: Refactor StatisticsScreen

**File**: `lib/screens/statistics_screen.dart` (REFACTOR)

```dart
// BEFORE
class _StatisticsScreenState extends State<StatisticsScreen> {
  final _contributionRepo = ContributionRepository(); // ❌
  ContributionStats? _stats;
  bool _isLoading = true;
  StreamSubscription<UploadSuccessEvent>? _uploadSuccessSub;

  Future<void> _loadStats() async {
    // ❌ Business logic in widget
    setState(() => _isLoading = true);
    try {
      final stats = await _contributionRepo.getStats();
      setState(() {
        _stats = stats;
        _isLoading = false;
      });
    } catch (e) {
      setState(() => _isLoading = false);
    }
  }
}

// AFTER
class StatisticsScreen extends StatefulWidget {
  const StatisticsScreen({super.key});

  @override
  State<StatisticsScreen> createState() => _StatisticsScreenState();
}

class _StatisticsScreenState extends State<StatisticsScreen> {
  late final StatisticsController _controller;

  @override
  void initState() {
    super.initState();
    _controller = getIt<StatisticsController>();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Statistics'),
      ),
      body: ValueListenableBuilder<AsyncResult<ContributionStats>>(
        valueListenable: _controller.statsState,
        builder: (context, result, _) {
          return result.when(
            success: (stats) => _buildStatsContent(stats, theme, isDark),
            loading: () => const Center(child: CircularProgressIndicator()),
            error: (msg) => _buildErrorState(msg, theme, isDark),
          );
        },
      ),
    );
  }

  Widget _buildStatsContent(
    ContributionStats stats,
    ThemeData theme,
    bool isDark,
  ) {
    if (stats.totalUploads == 0) {
      return _buildEmptyState(theme, isDark);
    }

    return RefreshIndicator(
      onRefresh: () => _controller.loadStats(),
      color: AppColors.primary,
      child: ListView(
        padding: AppTheme.pagePadding,
        children: [
          _buildQuickStatsGrid(stats, theme, isDark),
          const SizedBox(height: AppTheme.spaceLg),
          _buildSectionHeader('Contribution Timeline', theme, isDark),
          const SizedBox(height: AppTheme.spaceMd),
          _buildSimpleTrendChart(stats, theme, isDark),
          // ... rest of content ...
        ],
      ),
    );
  }

  // All _build methods are pure UI
  Widget _buildQuickStatsGrid(
    ContributionStats stats,
    ThemeData theme,
    bool isDark,
  ) {
    // Pure rendering
    return Row(
      children: [
        Expanded(
          child: _buildQuickStatTile(
            'Today',
            '${stats.uploadsToday}',
            Icons.today,
            theme,
            isDark,
          ),
        ),
        // ... more tiles ...
      ],
    );
  }

  Widget _buildErrorState(String message, ThemeData theme, bool isDark) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.error_outline, size: 64, color: AppColors.error),
          const SizedBox(height: AppTheme.spaceMd),
          Text('Error: $message', style: theme.textTheme.bodyLarge),
          const SizedBox(height: AppTheme.spaceMd),
          FilledButton(
            onPressed: () => _controller.loadStats(),
            child: const Text('Retry'),
          ),
        ],
      ),
    );
  }

  // ... other UI methods ...
}
```

### Step 3.3: Refactor ContributionStatsCard

**File**: `lib/widgets/contribution_stats_card.dart` (REFACTOR)

```dart
// BEFORE (435 lines with data fetching)
class ContributionStatsCardState extends State<ContributionStatsCard> {
  final _repository = ContributionRepository(); // ❌ Direct access
  ContributionStats _stats = ContributionStats.empty;
  bool _loading = true;

  Future<void> _loadStats() async {
    // ❌ Business logic in widget
    setState(() => _loading = true);
    try {
      final stats = await _repository.getStats();
      setState(() {
        _stats = stats;
        _loading = false;
      });
      AppEventBus.instance.emit(StatsUpdatedEvent(stats));
    } catch (e) {
      setState(() => _loading = false);
    }
  }

  Future<void> refresh() => _loadStats();
}

// AFTER (200 lines, pure presentation)
/// Pure presentation widget - listens to events only
/// No data fetching, no business logic
class ContributionStatsCard extends StatefulWidget {
  const ContributionStatsCard({super.key});

  @override
  State<ContributionStatsCard> createState() => ContributionStatsCardState();
}

class ContributionStatsCardState extends State<ContributionStatsCard>
    with SingleTickerProviderStateMixin {
  late AnimationController _pulseController;
  late Animation<double> _pulseAnimation;
  StreamSubscription<StatsUpdatedEvent>? _statsSub;
  ContributionStats _stats = ContributionStats.empty;

  @override
  void initState() {
    super.initState();
    _pulseController = AnimationController(
      duration: const Duration(milliseconds: 1200),
      vsync: this,
    );
    _pulseAnimation = Tween<double>(begin: 1.0, end: 1.05).animate(
      CurvedAnimation(parent: _pulseController, curve: Curves.easeInOut),
    );

    // Listen to stats updates from anywhere in the app
    _statsSub = AppEventBus.instance
        .on<StatsUpdatedEvent>()
        .listen(_onStatsUpdated);
  }

  @override
  void dispose() {
    _statsSub?.cancel();
    _pulseController.dispose();
    super.dispose();
  }

  void _onStatsUpdated(StatsUpdatedEvent event) {
    if (mounted) {
      setState(() => _stats = event.stats);
      // Subtle pulse animation
      _pulseController.forward().then((_) => _pulseController.reverse());
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    // Pure UI rendering based on _stats
    if (_stats.totalUploads == 0) {
      return _buildEmptyState(theme, isDark);
    }

    return AnimatedBuilder(
      animation: _pulseAnimation,
      builder: (context, child) {
        return Transform.scale(
          scale: _pulseAnimation.value,
          child: child,
        );
      },
      child: _buildStatsContent(_stats, theme, isDark),
    );
  }

  Widget _buildStatsContent(
    ContributionStats stats,
    ThemeData theme,
    bool isDark,
  ) {
    // Pure rendering logic
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: Row(
        children: [
          // Icon
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: AppColors.primaryAlpha(0.12),
              borderRadius: BorderRadius.circular(12),
            ),
            child: Icon(Icons.eco, color: AppColors.primary, size: 22),
          ),
          const SizedBox(width: AppTheme.spaceMd),

          // Stats
          Expanded(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _buildCompactStat('Total', '${stats.totalUploads}', theme, isDark),
                _buildDivider(isDark),
                _buildCompactStat('Today', '${stats.uploadsToday}', theme, isDark),
                if (stats.currentStreak > 0) ...[
                  _buildDivider(isDark),
                  _buildCompactStat(
                    'Streak',
                    '${stats.currentStreak}d',
                    theme,
                    isDark,
                    icon: Icons.local_fire_department,
                  ),
                ],
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildEmptyState(ThemeData theme, bool isDark) {
    // Pure UI
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: Row(
        children: [
          Icon(Icons.eco_outlined, color: AppColors.primary, size: 24),
          const SizedBox(width: AppTheme.spaceMd),
          Expanded(
            child: Text(
              'Start tracking to make your first contribution',
              style: theme.textTheme.bodyMedium,
            ),
          ),
        ],
      ),
    );
  }

  // Pure UI helper methods
  Widget _buildCompactStat(
    String label,
    String value,
    ThemeData theme,
    bool isDark, {
    IconData? icon,
  }) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        if (icon != null) Icon(icon, size: 16, color: AppColors.primary),
        Text(value, style: theme.textTheme.titleLarge),
        Text(label, style: theme.textTheme.bodySmall),
      ],
    );
  }

  Widget _buildDivider(bool isDark) {
    return Container(
      width: 1,
      height: 24,
      color: AppColors.divider(isDark),
    );
  }
}
```

---

## Phase 4: Complete Event Bus Migration

### Step 4.1: Update ForegroundLocationService to Emit All Events

**File**: `lib/services/location/foreground_location_service.dart` (UPDATE)

Add these emissions:

```dart
// In start() method, after successful start:
Future<bool> start() async {
  // ... existing code ...
  if (result == true) {
    _isRunningNotifier.value = true;
    await _sessionManager.startSession();

    // ✅ ADD: Emit session started event
    AppEventBus.instance.emit(SessionStartedEvent(
      timestamp: DateTime.now(),
    ));

    debugPrint('Foreground location service started');
  }
  return result ?? false;
}

// In stop() method, after successful stop:
Future<bool> stop() async {
  // ... existing code ...
  if (result == true) {
    _isRunningNotifier.value = false;
    uploadStatus.reset();
    await _sessionManager.stopSession(reason: 'user_stopped');

    // ✅ ADD: Emit session stopped event
    AppEventBus.instance.emit(SessionStoppedEvent(
      timestamp: DateTime.now(),
      reason: 'user_stopped',
      duration: null, // Can add duration from session manager if needed
    ));

    debugPrint('Foreground location service stopped');
  }
  return result ?? false;
}
```

### Step 4.2: Emit AppResumed Event from AppShell

**File**: `lib/app_shell.dart` (UPDATE)

```dart
class _AppShellState extends State<AppShell> with WidgetsBindingObserver {
  // ... existing code ...

  @override
  void initState() {
    super.initState();
    _pageController = PageController(initialPage: _currentIndex);
    TimeAgoService.instance.start();

    // ✅ ADD: Register as lifecycle observer
    WidgetsBinding.instance.addObserver(this);
  }

  @override
  void dispose() {
    // ✅ ADD: Unregister observer
    WidgetsBinding.instance.removeObserver(this);
    _pageController.dispose();
    super.dispose();
  }

  // ✅ ADD: Emit event on app resume
  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      AppEventBus.instance.emit(AppResumedEvent(
        timestamp: DateTime.now(),
      ));
    }
  }

  // ... rest of code ...
}
```

### Step 4.3: Update Main.dart to Initialize DI

**File**: `lib/main.dart` (UPDATE)

```dart
import 'core/di/service_locator.dart'; // ADD

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // ✅ ADD: Setup dependency injection FIRST
  await setupServiceLocator();

  runApp(const MyApp());
}

// Rest of file unchanged
```

---

## Phase 5: Testing & Validation

### Testing Checklist

#### Unit Tests (Create These)

**File**: `test/controllers/home_controller_test.dart` (NEW)

```dart
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:greengains/controllers/home_controller.dart';

void main() {
  group('HomeController', () {
    late HomeController controller;
    late MockContributionRepository mockRepo;
    late MockForegroundLocationService mockService;

    setUp(() {
      mockRepo = MockContributionRepository();
      mockService = MockForegroundLocationService();
      controller = HomeController(
        contributionRepo: mockRepo,
        locationService: mockService,
      );
    });

    tearDown(() {
      controller.dispose();
    });

    test('loads tile coverage on init', () async {
      // Arrange
      final mockStats = TileCoverageStats(/* ... */);
      when(mockRepo.getTodayTileCoverage())
          .thenAnswer((_) async => mockStats);

      // Act
      await controller.loadTileCoverage();

      // Assert
      expect(controller.tileCoverageState.value, isA<Success>());
      expect(
        (controller.tileCoverageState.value as Success).data,
        equals(mockStats),
      );
    });

    test('reacts to upload success event', () async {
      // Arrange
      final mockStats = TileCoverageStats(/* ... */);
      when(mockRepo.getTodayTileCoverage())
          .thenAnswer((_) async => mockStats);

      // Act
      AppEventBus.instance.emit(UploadSuccessEvent(
        samplesCount: 100,
        timestamp: DateTime.now(),
      ));
      await Future.delayed(Duration(milliseconds: 100)); // Wait for event

      // Assert
      verify(mockRepo.getTodayTileCoverage()).called(2); // Init + event
    });
  });
}
```

#### Manual Testing Checklist

- [ ] **Home Screen**
  - [ ] Stats card updates automatically on upload
  - [ ] Tile coverage updates on upload success
  - [ ] No manual refresh needed on app resume
  - [ ] Service status reflects correctly
  - [ ] Daily pot icon shows correct state

- [ ] **Statistics Screen**
  - [ ] Loads stats immediately
  - [ ] Updates automatically on upload
  - [ ] Pull-to-refresh works
  - [ ] Error state shows correctly
  - [ ] Loading state shows skeleton

- [ ] **Profile Screen**
  - [ ] User info displays correctly
  - [ ] Settings navigation works
  - [ ] Sign out works properly

- [ ] **Event Flow**
  - [ ] Upload success triggers stats refresh everywhere
  - [ ] App resume triggers data reload
  - [ ] Session start/stop emits events
  - [ ] All screens react to relevant events

- [ ] **Performance**
  - [ ] No jank or stuttering
  - [ ] Smooth animations
  - [ ] Fast screen transitions
  - [ ] Memory usage stable

---

## Phase 6: Cleanup & Documentation

### Step 6.1: Remove Deprecated Code

Files to clean up:
- Remove unused manual refresh methods
- Remove lifecycle observers from screens (moved to app_shell)
- Remove direct repository instantiation
- Remove setState boilerplate

### Step 6.2: Update Documentation

Update these files:
- `REACTIVE_ARCHITECTURE.md` - Add controller pattern
- `README.md` - Update architecture section
- Add code comments to controllers

### Step 6.3: Create Migration Guide

**File**: `MIGRATION_GUIDE.md` (NEW)

```markdown
# Migration Guide: Old Pattern → New Pattern

## Accessing Services

### OLD (Direct Singleton)
```dart
final service = ForegroundLocationService.instance;
```

### NEW (Dependency Injection)
```dart
final service = getIt<ForegroundLocationService>();
```

## Managing Screen State

### OLD (setState + manual loading)
```dart
class _MyScreenState extends State<MyScreen> {
  bool _loading = true;
  Data? _data;

  Future<void> _loadData() async {
    setState(() => _loading = true);
    _data = await repository.getData();
    setState(() => _loading = false);
  }
}
```

### NEW (Controller + AsyncResult)
```dart
class MyScreen extends StatefulWidget {
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
  Widget build(BuildContext context) {
    return ValueListenableBuilder<AsyncResult<Data>>(
      valueListenable: _controller.dataState,
      builder: (context, result, _) {
        return result.when(
          success: (data) => DataWidget(data: data),
          loading: () => LoadingWidget(),
          error: (msg) => ErrorWidget(message: msg),
        );
      },
    );
  }
}
```

## Reacting to Events

### OLD (Manual subscription)
```dart
StreamSubscription? _sub;

@override
void initState() {
  super.initState();
  _sub = AppEventBus.instance
      .on<MyEvent>()
      .listen((event) {
        _handleEvent(event);
      });
}

@override
void dispose() {
  _sub?.cancel();
  super.dispose();
}
```

### NEW (Controller handles subscriptions)
```dart
// In controller:
class MyController extends BaseController {
  MyController() {
    listenToEvent<MyEvent>(_handleEvent); // Auto-cleanup on dispose
  }

  void _handleEvent(MyEvent event) {
    // Handle event
  }
}

// Widget just uses controller, no manual subscription
```

## Summary

Key changes:
1. Use DI instead of singletons
2. Use controllers instead of widget state
3. Use AsyncResult instead of loading/error booleans
4. Use BaseController for automatic cleanup
5. Let controllers handle event subscriptions
```

---

## Appendix: Common Patterns

### Pattern 1: Loading State

```dart
// Controller
final _dataState = ValueNotifier<AsyncResult<T>>(const Loading());

Future<void> loadData() async {
  _dataState.value = const Loading();
  try {
    final data = await repository.getData();
    _dataState.value = Success(data);
  } catch (e) {
    _dataState.value = Error('Failed: $e');
  }
}

// Widget
ValueListenableBuilder<AsyncResult<T>>(
  valueListenable: controller.dataState,
  builder: (context, result, _) {
    return result.when(
      success: (data) => SuccessWidget(data),
      loading: () => LoadingWidget(),
      error: (msg) => ErrorWidget(msg),
    );
  },
)
```

### Pattern 2: Event Emission

```dart
// In service/controller
emitEvent(MyCustomEvent(data: someData));

// Other controllers listen automatically
class OtherController extends BaseController {
  OtherController() {
    listenToEvent<MyCustomEvent>(_handleCustomEvent);
  }

  void _handleCustomEvent(MyCustomEvent event) {
    // React to event
  }
}
```

### Pattern 3: Reactive List Updates

```dart
// Controller
final _itemsState = ValueNotifier<AsyncResult<List<Item>>>(const Loading());

void addItem(Item item) {
  final current = _itemsState.value;
  if (current is Success<List<Item>>) {
    final newList = [...current.data, item];
    _itemsState.value = Success(newList);
  }
}

// Widget
ValueListenableBuilder<AsyncResult<List<Item>>>(
  valueListenable: controller.itemsState,
  builder: (context, result, _) {
    return result.when(
      success: (items) => ListView.builder(
        itemCount: items.length,
        itemBuilder: (context, index) => ItemWidget(items[index]),
      ),
      loading: () => LoadingWidget(),
      error: (msg) => ErrorWidget(msg),
    );
  },
)
```

---

**End of Implementation Guide**

**Next Steps**:
1. Review this guide with team
2. Start with Phase 1 (Foundation)
3. Test each phase before moving to next
4. Update guide based on learnings
5. Complete refactor in 3-4 weeks
