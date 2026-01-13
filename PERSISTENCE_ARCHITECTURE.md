# GreenGains Persistence Architecture

## Overview

This document describes the comprehensive persistence layer for the GreenGains app, designed to ensure state survives app restarts, crashes, and force-stops.

## Architecture Layers

### 1. SQLite Database (`DatabaseHelper`)

**Location**: `lib/data/local/database_helper.dart`

**Purpose**: Primary persistence layer for structured data

**Schema** (v2):

#### `contributions` table
- Tracks all upload history with geohash for tile coverage
- Fields: `id`, `timestamp`, `samples_count`, `geohash`, `success`, `created_at`
- Enables: Upload statistics, streak calculation, tile coverage

#### `tracking_sessions` table
- Records when user starts/stops data collection
- Fields: `id`, `started_at`, `stopped_at`, `duration_ms`, `samples_collected`, `uploads_completed`, `ended_reason`
- Enables: Session analytics, crash recovery, auto-restart logic

#### `daily_pot_cache` table
- Offline-first cache for daily pot reward state
- Fields: `user_id`, `total_credits`, `uploads_today`, `uploads_required`, `is_unlocked`, `has_claimed_today`, `last_claim_date`, `last_synced_at`, `cached_at`
- Enables: Instant UI updates, offline resilience, optimistic updates

#### `upload_queue` table
- Retry queue for failed uploads (future enhancement)
- Fields: `id`, `payload`, `created_at`, `retry_count`, `last_error`, `next_retry_at`
- Enables: Guaranteed delivery, offline operation

#### `app_state` table
- Generic key-value store for miscellaneous state
- Fields: `key`, `value`, `updated_at`
- Enables: Flexible state storage without schema changes

### 2. SharedPreferences (`AppPreferences`)

**Location**: `lib/core/app_preferences.dart`

**Purpose**: Simple key-value storage for settings and preferences

**Stored Values**:
- `onboarding_complete`: Has user completed onboarding?
- `theme_mode`: User's theme preference (light/dark/system)
- `use_mobile_data_uploads`: Allow uploads on mobile data?
- `device_id`: Unique device identifier (UUID)
- `foreground_service_enabled`: Is tracking currently active?
- `share_location`: Has user opted into location sharing?
- `last_upload_at`: Last successful upload timestamp (ISO8601)
- `dismissed_tips`: List of dismissed contextual tips
- `battery_optimization_prompt_dismissed`: Battery prompt state
- `battery_optimization_prompt_last_shown`: Last time prompt shown
- `post_onboarding_auth_prompted`: Post-onboarding auth state

**Sync**: Preferences are synced to backend via `PreferencesService`

### 3. Native State (Kotlin/Android)

**Location**: `android/app/src/main/kotlin/com/eremat/greengains/service/`

**Purpose**: Native-side state management for foreground service

**State Management**:
- Native code writes to same SQLite database (shared with Flutter)
- Saves upload timestamps to SharedPreferences
- Maintains sensor buffer state (up to 1000 readings)
- Tracks network/battery state for upload scheduling

**Coordination**:
- Native code saves contributions directly to SQLite
- Flutter reads from same database for UI updates
- SharedPreferences used as IPC for critical timestamps

## State Restoration Flow

### App Launch

1. **Initialize Database** (`DatabaseHelper`)
   - Open/create SQLite database
   - Run migrations if needed (v1 → v2)

2. **Initialize SharedPreferences** (`AppPreferences`)
   - Load user preferences
   - Restore device ID

3. **Restore Tracking State** (`TrackingSessionManager`)
   - Check for active session in database
   - Validate against `foreground_service_enabled` preference
   - Close orphaned sessions (crash recovery)
   - Restore active sessions (if service is still running)

4. **Restore Daily Pot** (`DailyPotService`)
   - Load cached pot state from database
   - Show cached state instantly
   - Background sync with backend
   - Update UI with fresh data

5. **Check Service State** (`ForegroundLocationService`)
   - Query native service if it's running
   - Auto-restart if it was running before crash
   - Sync Flutter state with native state

### Tracking Start

1. **User presses "Start Tracking"**
2. **Flutter calls** `ForegroundLocationService.start()`
3. **Native service starts** via `MethodChannel`
4. **Create tracking session** in database (`tracking_sessions` table)
5. **Update preference** `foreground_service_enabled = true`
6. **Emit event** `TrackingStateChangedEvent(isTracking: true)`

### Tracking Stop

1. **User presses "Stop Tracking"** OR **System kills service**
2. **Flutter calls** `ForegroundLocationService.stop()`
3. **Close tracking session** in database (set `stopped_at`, `duration_ms`, `ended_reason`)
4. **Update preference** `foreground_service_enabled = false`
5. **Emit event** `TrackingStateChangedEvent(isTracking: false)`

### Upload Success

1. **Native code uploads batch** to backend
2. **Native code saves** contribution to SQLite (`contributions` table)
3. **Native code saves** timestamp to SharedPreferences (`last_upload_at`)
4. **Native code notifies** Flutter via `MethodChannel`
5. **Flutter emits** `UploadSuccessEvent` via event bus
6. **Daily Pot optimistic update** (instant UI feedback)
7. **Daily Pot backend sync** (confirm state)
8. **Update tracking session** stats (`uploads_completed++`)

## Offline-First Design

### Daily Pot

**Problem**: Daily pot relies on backend calls, causing UI lag and poor offline experience.

**Solution**:
1. **Cache pot state** in SQLite after every backend sync
2. **Load from cache** on app launch (instant UI)
3. **Optimistic updates** when recording uploads (instant feedback)
4. **Background sync** to confirm state with backend
5. **Fallback to cache** if backend unavailable

**Benefits**:
- Instant UI updates (no loading spinners)
- Works offline
- Reduces backend load
- Better UX

### Contributions

**Problem**: Upload statistics only available when online.

**Solution**:
1. **Native code saves** every upload to SQLite
2. **Flutter reads** from local database
3. **No backend dependency** for displaying stats
4. **Backend used only** for cross-device sync

**Benefits**:
- Stats always available
- Fast queries (local database)
- No network required

## Migration Strategy

### Database Migrations

**Current Version**: v2

**Migration Path**:
- v1 → v2: Add new tables (`tracking_sessions`, `daily_pot_cache`, `upload_queue`, `app_state`)
- Future migrations: Increment version, add `IF NOT EXISTS` checks

**Safety**:
- All migrations use `IF NOT EXISTS`
- Never drop existing tables
- Backwards compatible

### Preference Migrations

**Strategy**: Add new keys without removing old ones

**Deprecation**:
- Mark old keys as deprecated in code
- Keep reading old keys for backwards compatibility
- Write to new keys only

## State Synchronization

### Flutter ↔ Native

**Mechanisms**:
1. **Shared SQLite Database**: Native writes, Flutter reads
2. **SharedPreferences**: Both read/write (Flutter namespace)
3. **MethodChannel**: Events from native → Flutter
4. **Service state**: Flutter queries native via `MethodChannel`

**Critical State**:
- `foreground_service_enabled`: Source of truth for tracking state
- `last_upload_at`: Native writes, Flutter reads
- Contributions: Native writes, Flutter reads
- Device ID: Flutter writes, native reads

### Flutter ↔ Backend

**Mechanisms**:
1. **HTTP REST API**: Upload data, sync preferences
2. **Firebase Auth**: User authentication
3. **Daily Pot API**: Reward system state

**Sync Strategy**:
- **On app launch**: Fetch fresh data, update cache
- **On upload**: Optimistic update, then confirm
- **Periodically**: Background sync (not yet implemented)

## Debugging Tools

### State Export

```dart
final state = await AppStateManager.instance.exportStateJson();
print(state);
```

**Output**: JSON with all app state for debugging

### Database Stats

```dart
final stats = await DatabaseHelper.instance.getDatabaseStats();
print('Contributions: ${stats['contributions']}');
print('Sessions: ${stats['tracking_sessions']}');
print('Queued uploads: ${stats['queued_uploads']}');
```

### Session History

```dart
final history = await TrackingSessionManager.instance.getHistory(limit: 10);
for (final session in history) {
  print('Session: ${session['started_at']} - ${session['stopped_at']}');
}
```

## Best Practices

### State Management

1. **Use events for reactive updates**: Don't poll, listen to `AppEventBus`
2. **Cache aggressively**: Show cached data first, sync in background
3. **Optimistic updates**: Update UI immediately, sync later
4. **Graceful degradation**: Work offline when possible

### Database Access

1. **Use repositories**: Don't access `DatabaseHelper` directly from UI
2. **Batch operations**: Use transactions for multiple writes
3. **Index queries**: All common queries have indexes
4. **Clean old data**: Periodic cleanup prevents unbounded growth

### Preferences

1. **Namespace keys**: Use `PreferenceKeys` constants
2. **Type safety**: Use typed getters (getBool, getString, etc.)
3. **Defaults**: Always provide sensible defaults
4. **Sync to backend**: Important preferences synced across devices

## Performance Considerations

### Database

- **Indexes**: All timestamp columns indexed
- **Cleanup**: Contributions older than 90 days auto-deleted
- **Batch inserts**: Native code uses transactions

### Memory

- **No large caches**: Database is source of truth
- **Stream-based UI**: Use `StreamBuilder` to avoid holding data
- **Dispose listeners**: Always clean up `ValueNotifier` listeners

### Network

- **Debounce syncs**: Prevent excessive backend calls
- **Retry logic**: Upload queue for guaranteed delivery (future)
- **Optimistic updates**: Reduce perceived latency

## Future Enhancements

1. **Upload retry queue**: Implement retry logic for failed uploads
2. **Periodic background sync**: Sync pot state every hour
3. **Multi-device sync**: Sync tracking sessions across devices
4. **Export/import**: Backup and restore entire app state
5. **Analytics**: Track session patterns, optimize battery usage
6. **Compression**: Compress old data to reduce database size

## Testing Strategy

### Unit Tests

- Database migrations (v1 → v2)
- State restoration logic
- Optimistic update reconciliation
- Offline fallback behavior

### Integration Tests

- Native ↔ Flutter state sync
- Crash recovery (orphaned sessions)
- Service auto-restart
- Offline mode

### Manual Testing

- Force-stop app during tracking
- Airplane mode operation
- Database corruption recovery
- Version upgrade scenarios

## Summary

The GreenGains persistence architecture provides:

1. **Crash resilience**: State always recoverable
2. **Offline-first**: Core features work without network
3. **Fast UI**: Cached data shown instantly
4. **Scalable**: Database migrations for evolution
5. **Debuggable**: Export tools for troubleshooting

Key files:
- `lib/data/local/database_helper.dart` - SQLite database
- `lib/core/app_preferences.dart` - SharedPreferences
- `lib/services/tracking/tracking_session_manager.dart` - Session state
- `lib/services/daily_pot_service.dart` - Daily pot cache
- `lib/services/state/app_state_manager.dart` - Centralized state management
