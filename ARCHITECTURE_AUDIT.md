# GreenGains Architecture Audit & Refactoring Plan

**Date**: 2026-01-13
**Audited by**: Frontend Development Specialist
**Codebase**: 45 Dart files analyzed

---

## Executive Summary

### Current State
The GreenGains Flutter app has **inconsistent architectural patterns** across the codebase, mixing reactive and imperative approaches. While some parts follow modern event-driven architecture, others use legacy patterns creating maintenance challenges.

### Key Issues Identified
1. **Inconsistent State Management** - Mix of ValueNotifier, StreamSubscription, setState, manual polling
2. **Poor Separation of Concerns** - Business logic embedded in widgets
3. **Incomplete Event Bus Adoption** - Some screens listen to events, others don't
4. **Duplicate Code** - Stats loading logic repeated across screens
5. **Manual State Sync** - Polling instead of reactive updates in some areas
6. **Widget Complexity** - Deeply nested widget trees with mixed concerns

### Health Score: 6.5/10
- **Reactive Architecture**: 7/10 (Good foundation, incomplete adoption)
- **Separation of Concerns**: 5/10 (Services exist but widgets have business logic)
- **Code Consistency**: 6/10 (Mixed patterns throughout)
- **Maintainability**: 7/10 (Well-documented but inconsistent)
- **Performance**: 8/10 (Good reactive patterns where implemented)

---

## Detailed Analysis

## 1. State Management Patterns (Current)

### Pattern Inventory

#### A. Event Bus (Good - Event-Driven)
**Files Using**:
- `lib/core/events/app_events.dart` (Event definitions)
- `lib/screens/statistics_screen.dart` (Listens to UploadSuccessEvent, StatsUpdatedEvent)
- `lib/widgets/contribution_stats_card.dart` (Listens to UploadSuccessEvent, emits StatsUpdatedEvent)
- `lib/services/location/foreground_location_service.dart` (Emits events)
- `lib/services/daily_pot_service.dart` (Emits DailyPotUpdatedEvent, DailyPotClaimedEvent)

**Status**: Well-designed, but **underutilized**

#### B. ValueNotifier (Good - Reactive Primitives)
**Files Using**:
- `lib/services/location/foreground_location_service.dart` (NativeUploadStatus, isRunning)
- `lib/services/daily_pot_service.dart` (_potNotifier)
- `lib/core/services/time_ago_service.dart` (_tickNotifier)
- `lib/core/theme_controller.dart` (Theme state)

**Status**: Properly used for simple reactive state

#### C. StreamBuilder (Good - Reactive Widgets)
**Files Using**:
- `lib/screens/home_screen.dart` (Sensor data streams)

**Status**: Correctly used for continuous data streams

#### D. ValueListenableBuilder (Good - Reactive UI)
**Files Using**:
- `lib/screens/home_screen.dart` (Upload status, service running state)
- `lib/widgets/daily_pot_icon.dart` (Daily pot state)

**Status**: Properly used for reactive UI updates

#### E. Manual setState + Imperative Loading (BAD - Anti-pattern)
**Files Using**:
- `lib/screens/home_screen.dart` (_loadTileCoverage, _checkServiceStatus)
- `lib/screens/statistics_screen.dart` (_loadStats)
- `lib/screens/profile_screen.dart` (Basic state only)

**Status**: **NEEDS REFACTORING** - Should use event bus instead

#### F. App Lifecycle Observers (Mixed)
**Files Using**:
- `lib/screens/home_screen.dart` (didChangeAppLifecycleState with manual refresh)

**Status**: Valid pattern but could be improved with events

---

## 2. Separation of Concerns Analysis

### Current Architecture (Layered)

```
┌─────────────────────────────────────┐
│         UI Layer (Widgets)          │
│  - home_screen.dart                 │
│  - statistics_screen.dart           │
│  - profile_screen.dart              │
│  - contribution_stats_card.dart     │
│  ❌ PROBLEM: Business logic mixed in │
└─────────────────────────────────────┘
          ↕️ (Direct calls)
┌─────────────────────────────────────┐
│      Business Logic Layer           │
│  - foreground_location_service.dart │
│  - daily_pot_service.dart           │
│  - tracking_session_manager.dart    │
│  ✅ GOOD: Service layer exists       │
└─────────────────────────────────────┘
          ↕️ (Repository pattern)
┌─────────────────────────────────────┐
│         Data Layer                  │
│  - database_helper.dart             │
│  - contribution_repository.dart     │
│  - app_preferences.dart             │
│  ✅ GOOD: Repository pattern used    │
└─────────────────────────────────────┘
```

### Issues Found

#### ❌ UI Layer Violations
1. **HomeScreen** (lines 172-197):
   - Directly calls `_contributionRepo.getTodayTileCoverage()`
   - Manual state management with `setState`
   - Should delegate to a service/controller

2. **StatisticsScreen** (lines 64-79):
   - Directly calls `_contributionRepo.getStats()`
   - Manual loading state management
   - Should use controller/state manager

3. **ContributionStatsCard** (lines 64-88):
   - Directly calls `_repository.getStats()`
   - Mixed concerns: UI + data fetching + event emission
   - Should be a pure presentation widget

#### ❌ Service Layer Violations
4. **ForegroundLocationService** (lines 338-343):
   - Directly accesses `AppPreferences.instance`
   - Tight coupling to concrete implementations
   - Should use dependency injection

#### ✅ Good Patterns Found
1. **Repository Pattern**: `ContributionRepository` properly abstracts database
2. **Service Layer**: Services like `DailyPotService` handle business logic
3. **Event Bus**: Clean separation for cross-screen communication

---

## 3. Event Bus Adoption Audit

### Current Event Flow

```
Event Sources (Emitters):
├─ ForegroundLocationService
│  ├─ UploadSuccessEvent ✅
│  └─ UploadFailedEvent ✅
├─ ContributionStatsCard
│  └─ StatsUpdatedEvent ✅
└─ DailyPotService
   ├─ DailyPotUpdatedEvent ✅
   └─ DailyPotClaimedEvent ✅

Event Consumers (Listeners):
├─ StatisticsScreen
│  ├─ Listens: UploadSuccessEvent ✅
│  └─ Listens: StatsUpdatedEvent ✅
├─ ContributionStatsCard
│  └─ Listens: UploadSuccessEvent ✅
└─ HomeScreen
   └─ Listens: NONE ❌ (Uses manual ValueNotifier instead)
```

### Missing Event Listeners

#### ❌ HomeScreen Should Listen To:
- `StatsUpdatedEvent` - Auto-refresh stats card
- `DailyPotUpdatedEvent` - Update daily pot icon
- `TrackingStateChangedEvent` - Update UI on service state change

Currently uses:
- Manual `_loadTileCoverage()` on lifecycle resume
- Manual `_checkServiceStatus()` polling
- Manual `_reloadUploadStatus()` from SharedPreferences

**IMPACT**: Inconsistent updates, unnecessary polling, tight coupling

#### ❌ Missing Events:
- `TileCoverageUpdatedEvent` - For tile stats updates
- `SessionStartedEvent` - When tracking begins
- `SessionStoppedEvent` - When tracking ends

---

## 4. Code Duplication Analysis

### Duplicate Pattern #1: Stats Loading

**Occurrence 1**: `lib/widgets/contribution_stats_card.dart` (lines 64-88)
```dart
Future<void> _loadStats() async {
  setState(() { _loading = true; });
  try {
    final stats = await _repository.getStats();
    if (mounted) setState(() { _stats = stats; _loading = false; });
  } catch (e) {
    if (mounted) setState(() { _loading = false; _error = e; });
  }
}
```

**Occurrence 2**: `lib/screens/statistics_screen.dart` (lines 64-79)
```dart
Future<void> _loadStats() async {
  setState(() => _isLoading = true);
  try {
    final stats = await _contributionRepo.getStats();
    if (mounted) setState(() { _stats = stats; _isLoading = false; });
  } catch (e) {
    if (mounted) setState(() => _isLoading = false);
  }
}
```

**Solution**: Create `StatsController` or use a centralized state manager

### Duplicate Pattern #2: Service Status Checking

**Occurrence 1**: `lib/screens/home_screen.dart` (lines 162-170)
**Occurrence 2**: `lib/main.dart` (TrackingSessionManager initialization)

**Solution**: Use `TrackingStateChangedEvent` instead of polling

### Duplicate Pattern #3: Error Handling Boilerplate

Found in 8+ files with identical try-catch-setState patterns.

**Solution**: Create reactive data fetcher utilities or use state management solution

---

## 5. Widget Complexity Analysis

### HomeScreen (720 lines) - TOO COMPLEX

**Responsibilities** (Should be 1, has 8):
1. ✅ Display UI (Primary responsibility)
2. ❌ Data fetching (tile coverage, stats)
3. ❌ Service lifecycle management
4. ❌ Permission handling
5. ❌ Battery optimization prompts
6. ❌ Tip dismissal logic
7. ❌ Upload status monitoring
8. ❌ Sensor data stream management

**Nesting Depth**: 5-6 levels in some areas (Max recommended: 3)

**Refactoring Plan**:
- Extract battery optimization to `BatteryOptimizationManager`
- Extract tip management to `TipManager` service
- Extract sensor display to separate widget components
- Move data fetching to controllers
- Reduce to ~300 lines

### StatisticsScreen (589 lines) - MODERATE COMPLEXITY

**Issues**:
- Chart rendering mixed with data logic
- Manual data loading instead of reactive streams
- Complex state management for loading/error states

**Refactoring Plan**:
- Extract chart widgets to separate files
- Create `StatsController` for state management
- Use event bus for auto-updates

---

## 6. Architectural Anti-Patterns Found

### Anti-Pattern #1: Manual Polling on Lifecycle Resume
**Location**: `lib/screens/home_screen.dart` (lines 139-149)
```dart
@override
void didChangeAppLifecycleState(AppLifecycleState state) {
  if (state == AppLifecycleState.resumed) {
    _checkServiceStatus();      // ❌ Manual poll
    _reloadUploadStatus();       // ❌ Manual poll
    _statsKey.currentState?.refresh(); // ❌ Manual refresh
    _loadTileCoverage();         // ❌ Manual poll
  }
}
```

**Why Bad**:
- Polling instead of reactive
- May refresh unnecessarily
- Tight coupling

**Solution**: Use event bus events triggered by services

### Anti-Pattern #2: Widget-Level Repository Access
**Location**: Multiple screens
```dart
final _contributionRepo = ContributionRepository(); // ❌ Direct instantiation
```

**Why Bad**:
- Tight coupling
- Hard to test
- No dependency injection

**Solution**: Use dependency injection or controller layer

### Anti-Pattern #3: Business Logic in Widgets
**Location**: `lib/widgets/contribution_stats_card.dart` (lines 64-88)
```dart
Future<void> _loadStats() async {
  // Business logic for fetching, caching, emitting events
  AppEventBus.instance.emit(StatsUpdatedEvent(stats)); // ❌ Widget emitting domain events
}
```

**Why Bad**:
- Widgets should only render
- Makes testing harder
- Violates SRP

**Solution**: Move to controller/service layer

### Anti-Pattern #4: Global Singleton Everywhere
**Locations**: 10+ services
```dart
static final instance = ServiceName._(); // ❌ Overuse of singletons
```

**Why Bad**:
- Hard to test
- Global state
- Hidden dependencies

**Solution**: Use dependency injection container (GetIt, Provider, Riverpod)

---

## 7. Missing Abstractions

### Missing #1: State Management Layer
**Need**: Controllers/ViewModels for screens
**Current**: Widgets directly manage state with setState

**Proposed**:
```dart
class HomeController extends ChangeNotifier {
  // Handle all home screen state
  // Expose streams and notifiers
  // Business logic here
}
```

### Missing #2: Navigation Service
**Need**: Centralized navigation with type safety
**Current**: Direct Navigator calls in widgets

### Missing #3: Dependency Injection Container
**Need**: Service locator for loose coupling
**Current**: Direct singleton access everywhere

### Missing #4: Error Boundary/Handler
**Need**: Centralized error handling
**Current**: Try-catch in every widget

---

## 8. Positive Patterns to Keep

### ✅ Event Bus Architecture
- Clean implementation in `app_events.dart`
- Well-documented events
- Proper broadcast stream pattern

### ✅ Repository Pattern
- `ContributionRepository` properly abstracts data layer
- Clean interface for database access

### ✅ Service Layer
- Services like `DailyPotService` handle domain logic well
- Clear responsibilities

### ✅ Reactive Primitives
- Good use of ValueNotifier, StreamBuilder
- TimeAgoService is excellent pattern

### ✅ Offline-First Architecture
- SQLite caching for daily pot
- Optimistic updates
- Well-designed persistence layer

---

## 9. Refactoring Priority Matrix

### HIGH PRIORITY (Do First)

#### P1: Complete Event Bus Migration
**Impact**: High | **Effort**: Medium | **Risk**: Low
- Make HomeScreen listen to all relevant events
- Remove manual polling on lifecycle resume
- Add missing events (TileCoverageUpdated, SessionState)
- **Files to modify**: `home_screen.dart`, `foreground_location_service.dart`

#### P2: Extract Business Logic from Widgets
**Impact**: High | **Effort**: High | **Risk**: Medium
- Create controllers for Home, Statistics, Profile screens
- Move data fetching to controllers
- Remove repository access from widgets
- **Files to modify**: All screen files, create new controller files

#### P3: Standardize Error Handling
**Impact**: Medium | **Effort**: Low | **Risk**: Low
- Create `AsyncResult<T>` type for operations
- Create `ErrorHandler` service
- Remove duplicate try-catch patterns
- **Files to modify**: All files with async operations

### MEDIUM PRIORITY (Do Second)

#### P4: Dependency Injection
**Impact**: Medium | **Effort**: Medium | **Risk**: Medium
- Add GetIt or Provider
- Remove direct singleton access
- Inject dependencies into widgets
- **Files to modify**: All service files, main.dart

#### P5: Widget Decomposition
**Impact**: Medium | **Effort**: Medium | **Risk**: Low
- Break HomeScreen into smaller widgets
- Extract chart rendering from StatisticsScreen
- Create reusable sensor display components
- **Files to modify**: `home_screen.dart`, `statistics_screen.dart`

#### P6: Reactive Data Fetching
**Impact**: Medium | **Effort**: Low | **Risk**: Low
- Create reactive repository wrappers
- Stream-based data instead of Future
- Cache + stream pattern for stats
- **Files to modify**: Repository files

### LOW PRIORITY (Nice to Have)

#### P7: Navigation Service
**Impact**: Low | **Effort**: Low | **Risk**: Low
- Type-safe navigation
- Centralized route management

#### P8: Theme Architecture
**Impact**: Low | **Effort**: Low | **Risk**: Low
- Already well-designed
- Minor improvements only

---

## 10. Proposed Architecture (Target State)

### New Architecture Layers

```
┌────────────────────────────────────────────┐
│         Presentation Layer (Widgets)       │
│  - Pure UI rendering only                  │
│  - Listen to controllers/events            │
│  - No business logic                       │
│  - No direct service/repository access     │
└────────────────────────────────────────────┘
          ↕️ (Observe)
┌────────────────────────────────────────────┐
│      Controller Layer (NEW!)               │
│  - HomeController                          │
│  - StatisticsController                    │
│  - ProfileController                       │
│  - Expose reactive state (Streams/Listenable)│
│  - Handle user actions                     │
│  - Coordinate services                     │
└────────────────────────────────────────────┘
          ↕️ (Call)
┌────────────────────────────────────────────┐
│      Business Logic Layer (Services)       │
│  - Domain logic                            │
│  - Emit events via AppEventBus             │
│  - Pure functions where possible           │
└────────────────────────────────────────────┘
          ↕️ (Repository pattern)
┌────────────────────────────────────────────┐
│      Data Layer (Repositories)             │
│  - Database access                         │
│  - API calls                               │
│  - Caching                                 │
└────────────────────────────────────────────┘
          ↕️ (Event bus for cross-cutting)
┌────────────────────────────────────────────┐
│      Event Bus (Cross-Cutting)             │
│  - App-wide event communication            │
│  - Decoupled component communication       │
└────────────────────────────────────────────┘
```

### State Management Strategy

**Adopt**: Event Bus + Controllers + ValueNotifier pattern

```dart
// Controller pattern
class HomeController extends ChangeNotifier {
  // Dependencies injected
  final ContributionRepository _repo;
  final ForegroundLocationService _locationService;

  // Reactive state
  final tileCoverage = ValueNotifier<TileCoverageStats?>(null);
  final isLoading = ValueNotifier<bool>(false);

  // Event subscriptions
  StreamSubscription? _uploadSub;

  HomeController(this._repo, this._locationService) {
    _setupEventListeners();
    _init();
  }

  void _setupEventListeners() {
    _uploadSub = AppEventBus.instance
        .on<UploadSuccessEvent>()
        .listen(_handleUploadSuccess);
  }

  Future<void> _handleUploadSuccess(UploadSuccessEvent event) async {
    await loadTileCoverage(); // Auto-refresh
  }

  Future<void> loadTileCoverage() async {
    isLoading.value = true;
    tileCoverage.value = await _repo.getTodayTileCoverage();
    isLoading.value = false;
  }

  @override
  void dispose() {
    _uploadSub?.cancel();
    tileCoverage.dispose();
    isLoading.dispose();
    super.dispose();
  }
}

// Widget usage (pure UI)
class HomeScreen extends StatelessWidget {
  final HomeController controller;

  const HomeScreen({required this.controller});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ValueListenableBuilder(
        valueListenable: controller.tileCoverage,
        builder: (context, coverage, _) {
          if (coverage == null) return LoadingWidget();
          return TileCoverageWidget(coverage: coverage);
        },
      ),
    );
  }
}
```

### Dependency Injection Pattern

```dart
// main.dart
void main() {
  // Setup DI container
  final getIt = GetIt.instance;

  // Register services (singletons)
  getIt.registerSingleton<DatabaseHelper>(DatabaseHelper.instance);
  getIt.registerSingleton<AppEventBus>(AppEventBus.instance);
  getIt.registerSingleton<ForegroundLocationService>(
    ForegroundLocationService.instance,
  );

  // Register repositories
  getIt.registerFactory<ContributionRepository>(
    () => ContributionRepository(getIt<DatabaseHelper>()),
  );

  // Register controllers (factories - new instance per screen)
  getIt.registerFactory<HomeController>(
    () => HomeController(
      getIt<ContributionRepository>(),
      getIt<ForegroundLocationService>(),
    ),
  );

  runApp(MyApp());
}

// Widget usage
class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (_) => GetIt.I<HomeController>(),
      child: HomeScreen(),
    );
  }
}
```

---

## 11. Migration Strategy (Step-by-Step)

### Phase 1: Foundation (Week 1)
**Goal**: Set up new architecture without breaking existing code

1. **Add dependency injection** (GetIt)
   - Install package
   - Register existing services
   - Keep singleton access as fallback

2. **Create controller base classes**
   - `BaseController` with common patterns
   - `BaseScreenController` for screens

3. **Add missing events**
   - `TileCoverageUpdatedEvent`
   - `SessionStartedEvent`
   - `SessionStoppedEvent`

4. **Create error handling utilities**
   - `AsyncResult<T>` type
   - `ErrorHandler` service

**Testing**: Existing functionality unchanged

### Phase 2: Widget Refactoring (Week 2)
**Goal**: Remove business logic from widgets

1. **Refactor HomeScreen**
   - Create `HomeController`
   - Move data fetching to controller
   - Update widget to listen to controller
   - Remove direct repository access

2. **Refactor StatisticsScreen**
   - Create `StatisticsController`
   - Move stats loading to controller
   - Extract chart widgets

3. **Refactor ContributionStatsCard**
   - Make pure presentation widget
   - Listen to controller/events only
   - Remove internal data fetching

**Testing**: Manual testing of all screens

### Phase 3: Service Layer (Week 3)
**Goal**: Complete event bus adoption

1. **Update ForegroundLocationService**
   - Emit all missing events
   - Remove direct SharedPreferences access
   - Use injected dependencies

2. **Update tracking services**
   - Emit SessionStarted/Stopped events
   - Remove polling patterns

3. **Update all event listeners**
   - HomeScreen subscribes to all events
   - Remove manual refresh calls
   - Remove lifecycle polling

**Testing**: Verify reactive updates work across app

### Phase 4: Polish & Cleanup (Week 4)
**Goal**: Remove old patterns, improve consistency

1. **Remove singleton fallbacks**
   - Force DI everywhere
   - Remove `instance` getters

2. **Widget decomposition**
   - Break down large widgets
   - Create reusable components

3. **Documentation update**
   - Update REACTIVE_ARCHITECTURE.md
   - Add controller documentation
   - Create migration guide

**Testing**: Full regression testing

---

## 12. Testing Strategy

### Unit Tests
- Controllers (business logic)
- Services (domain logic)
- Repositories (data access)
- Event emission/handling

### Widget Tests
- Pure presentation widgets
- Controller integration
- Event listener behavior

### Integration Tests
- Complete user flows
- Service coordination
- Event propagation

---

## 13. Success Metrics

### Code Quality Metrics
- **Widget Complexity**: Reduce to max 300 lines
- **Cyclomatic Complexity**: < 10 per method
- **Test Coverage**: > 80% for business logic
- **Event Bus Coverage**: 100% of state changes

### Architecture Metrics
- **Separation of Concerns**: All widgets are pure UI
- **Consistency**: Single state management pattern
- **Reactivity**: Zero manual polling
- **Loose Coupling**: All dependencies injected

### Performance Metrics
- **Rebuild Count**: Measure with DevTools
- **Memory Usage**: No memory leaks from listeners
- **Startup Time**: < 2 seconds

---

## 14. Risk Assessment

### HIGH RISK
- **Complete refactor during active development**
  - Mitigation: Phased approach, feature flags

### MEDIUM RISK
- **Breaking existing functionality**
  - Mitigation: Comprehensive testing, gradual rollout

### LOW RISK
- **Developer learning curve**
  - Mitigation: Good documentation, pair programming

---

## 15. Conclusion

The GreenGains app has a **solid foundation** with event bus and service layer, but **inconsistent adoption** creates maintenance challenges. The refactoring plan focuses on:

1. **Completing event bus migration** (eliminate polling)
2. **Extracting business logic** (controllers)
3. **Standardizing patterns** (DI, error handling)
4. **Decomposing complexity** (smaller widgets)

**Estimated Effort**: 3-4 weeks for complete refactor
**Risk Level**: Medium (manageable with phased approach)
**Expected Improvement**: 8.5/10 architecture score after completion

The proposed architecture maintains the excellent reactive and offline-first patterns while adding consistency and maintainability.

---

## Appendix A: File-by-File Recommendations

### `lib/screens/home_screen.dart`
- **Action**: Major refactor
- **Create**: `HomeController`
- **Remove**: Direct repository access, manual polling
- **Add**: Event bus listeners for all state changes
- **Lines**: 720 → 300

### `lib/screens/statistics_screen.dart`
- **Action**: Moderate refactor
- **Create**: `StatisticsController`
- **Extract**: Chart widgets to separate files
- **Lines**: 589 → 400

### `lib/widgets/contribution_stats_card.dart`
- **Action**: Moderate refactor
- **Change**: Make pure presentation widget
- **Remove**: Internal data fetching logic
- **Add**: Controller dependency

### `lib/services/location/foreground_location_service.dart`
- **Action**: Minor refactor
- **Add**: Emit SessionStarted/Stopped events
- **Remove**: Direct AppPreferences access
- **Add**: Dependency injection

### All Other Files
- Minor updates for DI integration
- Consistent error handling
- Documentation improvements

---

**End of Audit Report**
