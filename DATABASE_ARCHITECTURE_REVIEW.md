# GreenGains Database Architecture Review

**Prepared by:** Database Architecture Specialist
**Date:** 2026-01-13
**Scope:** SQLite (Flutter + Kotlin) + PostgreSQL Backend

---

## Executive Summary

The GreenGains application uses a **polyglot persistence architecture** with:
1. **SQLite** (local, mobile) - shared between Flutter and Kotlin native code
2. **PostgreSQL** (backend, Supabase) - centralized data storage with JSONB

This review identifies **7 critical issues**, **12 moderate issues**, and provides **specific remediation recommendations**.

---

## 1. SQLite Schema Analysis (v2)

### 1.1 Current Schema

**File:** `lib/data/local/database_helper.dart`

```sql
-- Table: contributions
CREATE TABLE contributions (
  id TEXT PRIMARY KEY,
  timestamp INTEGER NOT NULL,
  samples_count INTEGER NOT NULL,
  geohash TEXT,
  success INTEGER NOT NULL,
  created_at INTEGER NOT NULL
);
CREATE INDEX idx_timestamp ON contributions(timestamp);

-- Table: tracking_sessions
CREATE TABLE tracking_sessions (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  started_at INTEGER NOT NULL,
  stopped_at INTEGER,
  duration_ms INTEGER,
  samples_collected INTEGER DEFAULT 0,
  uploads_completed INTEGER DEFAULT 0,
  ended_reason TEXT
);
CREATE INDEX idx_session_started ON tracking_sessions(started_at);

-- Table: daily_pot_cache
CREATE TABLE daily_pot_cache (
  user_id TEXT PRIMARY KEY,
  total_credits INTEGER NOT NULL,
  uploads_today INTEGER NOT NULL,
  uploads_required INTEGER NOT NULL,
  is_unlocked INTEGER NOT NULL,
  has_claimed_today INTEGER NOT NULL,
  last_claim_date TEXT,
  last_synced_at INTEGER NOT NULL,
  cached_at INTEGER NOT NULL
);

-- Table: upload_queue
CREATE TABLE upload_queue (
  id TEXT PRIMARY KEY,
  payload TEXT NOT NULL,
  created_at INTEGER NOT NULL,
  retry_count INTEGER DEFAULT 0,
  last_error TEXT,
  next_retry_at INTEGER
);

-- Table: app_state
CREATE TABLE app_state (
  key TEXT PRIMARY KEY,
  value TEXT NOT NULL,
  updated_at INTEGER NOT NULL
);
```

### 1.2 Schema Issues Identified

#### CRITICAL Issues

| Issue | Table | Description | Impact |
|-------|-------|-------------|--------|
| **C1** | `contributions` | No foreign key to `tracking_sessions` | Cannot correlate uploads with sessions |
| **C2** | `upload_queue` | `payload` stored as TEXT (unbounded) | Memory issues with large payloads |
| **C3** | All tables | No `user_id` column in `contributions` | Multi-user support broken |
| **C4** | `tracking_sessions` | Missing `user_id` | Session history not user-scoped |

#### MODERATE Issues

| Issue | Table | Description | Impact |
|-------|-------|-------------|--------|
| **M1** | `contributions` | Missing index on `geohash` | Slow tile coverage queries |
| **M2** | `contributions` | Missing index on `success` | Slow filtered queries |
| **M3** | `upload_queue` | Missing index on `next_retry_at` | Slow retry scheduling |
| **M4** | `daily_pot_cache` | Redundant `is_unlocked` column | Can be computed from `uploads_today >= uploads_required` |
| **M5** | `tracking_sessions` | Missing index on `stopped_at IS NULL` | Slow active session lookup |

#### MINOR Issues

| Issue | Table | Description |
|-------|-------|-------------|
| **L1** | `contributions` | `success` uses INTEGER (0/1) instead of BOOLEAN semantics |
| **L2** | `app_state` | Generic key-value store loses type safety |

### 1.3 Recommended Schema (v3)

```sql
-- MIGRATION: v2 -> v3

-- Add user_id to contributions (with index)
ALTER TABLE contributions ADD COLUMN user_id TEXT;
CREATE INDEX idx_contributions_user ON contributions(user_id, timestamp DESC);

-- Add user_id to tracking_sessions
ALTER TABLE tracking_sessions ADD COLUMN user_id TEXT;
CREATE INDEX idx_sessions_user ON tracking_sessions(user_id, started_at DESC);

-- Add missing indexes
CREATE INDEX idx_contributions_geohash ON contributions(geohash) WHERE geohash IS NOT NULL;
CREATE INDEX idx_contributions_success ON contributions(success);
CREATE INDEX idx_upload_queue_retry ON upload_queue(next_retry_at) WHERE next_retry_at IS NOT NULL;

-- Add session_id FK to contributions
ALTER TABLE contributions ADD COLUMN session_id INTEGER REFERENCES tracking_sessions(id);
CREATE INDEX idx_contributions_session ON contributions(session_id);

-- Add constraint to upload_queue payload size
-- Note: SQLite doesn't support CHECK on TEXT length, enforce in application
```

---

## 2. Concurrency & Race Conditions

### 2.1 Architecture Problem

**Both Flutter and Kotlin write to the same SQLite database:**

```
Flutter (Dart)                    Kotlin (Native Service)
     |                                    |
     v                                    v
DatabaseHelper.instance      SQLiteDatabase.openDatabase()
     |                                    |
     +----------> greengains.db <---------+
```

**File Locations:**
- Flutter: `lib/data/local/database_helper.dart` (line 31)
- Kotlin: `android/.../NativeBackendUploader.kt` (line 403)

### 2.2 Race Condition Analysis

#### Identified Race Conditions

| Race | Writers | Scenario | Severity |
|------|---------|----------|----------|
| **R1** | Flutter + Kotlin | Both insert into `contributions` simultaneously | HIGH |
| **R2** | Flutter | Multiple `stopTrackingSession()` calls | MEDIUM |
| **R3** | Flutter | `cacheDailyPot()` during `fetchPotState()` | LOW |

#### Code Evidence - R1: Concurrent Writes to `contributions`

**Flutter side** (`database_helper.dart:183-194`):
```dart
Future<void> insertContribution({...}) async {
  final db = await database;
  await db.insert(
    'contributions',
    {...},
    conflictAlgorithm: ConflictAlgorithm.replace,  // Silent overwrite!
  );
}
```

**Kotlin side** (`NativeBackendUploader.kt:409-429`):
```kotlin
db.use {
  val values = ContentValues().apply {
    put("id", contributionId)  // UUID - unique
    put("timestamp", timestamp)
    // ...
  }
  val rowId = db.insert("contributions", null, values)  // No conflict handling!
}
```

**Problem:** If Flutter and Kotlin both generate the same contribution ID (unlikely but possible with UUID collision or clock skew), one write will silently overwrite the other.

### 2.3 Locking Strategy Analysis

**Current State:** NO EXPLICIT LOCKING

| Component | Locking | Issue |
|-----------|---------|-------|
| Flutter `sqflite` | SQLite WAL mode | Allows concurrent reads, serial writes |
| Kotlin `SQLiteDatabase.openDatabase()` | No explicit mode | Defaults to rollback journal |
| Cross-process | None | Different journal modes = corruption risk |

#### Critical Issue: Journal Mode Mismatch

Flutter's `sqflite` uses WAL mode by default on Android. Kotlin's native `SQLiteDatabase` uses rollback journal mode by default.

**Risk:** If both are active simultaneously with different journal modes, database corruption is possible.

### 2.4 Transaction Usage Analysis

| Component | Uses Transactions | Location |
|-----------|-------------------|----------|
| Flutter `insertContribution` | NO | Line 183-194 |
| Flutter `stopTrackingSession` | NO | Line 314-349 |
| Flutter `updateUploadRetry` | NO | Line 512-538 |
| Kotlin `saveContributionToDatabase` | NO | Line 391-433 |

**Finding:** No transactions are used in any write operation. This means:
- Non-atomic multi-step operations
- No rollback on partial failure
- Potential inconsistent state

### 2.5 Recommended Fixes

#### Fix 1: Unified Database Access (Kotlin via Flutter)

Instead of Kotlin opening SQLite directly, use Flutter's MethodChannel:

```kotlin
// BEFORE (NativeBackendUploader.kt:403-406)
val db = SQLiteDatabase.openDatabase(
    dbPath.absolutePath,
    null,
    SQLiteDatabase.OPEN_READWRITE
)

// AFTER - Use MethodChannel to Flutter
methodChannel?.invokeMethod("saveContribution", mapOf(
    "id" to contributionId,
    "timestamp" to timestamp,
    "samplesCount" to samplesCount,
    "geohash" to geohash
))
```

#### Fix 2: Add WAL Mode to Kotlin (if direct access required)

```kotlin
val db = SQLiteDatabase.openDatabase(
    dbPath.absolutePath,
    null,
    SQLiteDatabase.OPEN_READWRITE or SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING
)
```

#### Fix 3: Add Transactions to Multi-Step Operations

```dart
Future<void> stopTrackingSession({String? endedReason}) async {
  final db = await database;

  await db.transaction((txn) async {
    final results = await txn.query(
      'tracking_sessions',
      where: 'stopped_at IS NULL',
      orderBy: 'started_at DESC',
      limit: 1,
    );

    if (results.isEmpty) return;

    final session = results.first;
    final sessionId = session['id'] as int;
    final startedAt = session['started_at'] as int;
    final duration = DateTime.now().millisecondsSinceEpoch - startedAt;

    await txn.update(
      'tracking_sessions',
      {
        'stopped_at': DateTime.now().millisecondsSinceEpoch,
        'duration_ms': duration,
        'ended_reason': endedReason,
      },
      where: 'id = ?',
      whereArgs: [sessionId],
    );
  });
}
```

---

## 3. Data Sync Strategy

### 3.1 Current Architecture

```
                     +------------------+
                     |   PostgreSQL     |
                     |   (Supabase)     |
                     +--------+---------+
                              |
                              | HTTPS API
                              |
+-----------------------------+-----------------------------+
|                             |                             |
v                             v                             v
sensor_batches          daily_pots                    user_stats
(JSONB)                 (relational)                  (relational)
                              ^
                              |
                    +-------------------+
                    |  Backend (Node)   |
                    +-------------------+
                              ^
                              | HTTPS
                              |
+-----------------------------+-----------------------------+
|                                                           |
|                      Mobile App                           |
|                                                           |
|  +-------------------+          +---------------------+   |
|  |    Flutter        |          |    Kotlin Native    |   |
|  |  (UI + Services)  |          |  (Foreground Svc)   |   |
|  +--------+----------+          +-----------+---------+   |
|           |                                 |             |
|           +-------> SQLite <----------------+             |
|                   (greengains.db)                         |
+-----------------------------------------------------------+
```

### 3.2 Sync Flow Analysis

#### Upload Flow (Working)

```
Kotlin ForegroundService
    |
    | (sensor readings every 10s)
    v
NativeBackendUploader.uploadBatch()
    |
    +--> POST /upload (to backend)
    |         |
    |         v
    |    sensor_batches (PostgreSQL)
    |    user_stats (PostgreSQL)
    |
    +--> saveContributionToDatabase() (SQLite)
```

**Issue:** Kotlin writes to SQLite ONLY on upload success. If Flutter UI reads `contributions` before Kotlin writes, counts are stale.

#### Daily Pot Sync (Working with Issues)

```
DailyPotService.recordUpload()
    |
    +--> Optimistic update to ValueNotifier
    |
    +--> cacheDailyPot() to SQLite
    |
    +--> POST /daily-pot/upload (to backend)
    |         |
    |         v
    |    daily_pots (PostgreSQL)
    |
    +--> cacheDailyPot() with confirmed state
```

**Issue:** Optimistic update can diverge from backend if network fails. No reconciliation on app restart.

### 3.3 Conflict Resolution

| Scenario | Current Behavior | Recommended |
|----------|------------------|-------------|
| Optimistic update fails | Keep optimistic state | Revert to last known good state |
| Cache stale on restart | Fetch from backend | Fetch with timestamp comparison |
| Offline claims | Not supported | Queue locally, sync when online |
| Backend ahead of cache | Overwrite cache | Merge with conflict detection |

### 3.4 Recommended Sync Improvements

#### Add Version/Timestamp to Cache

```sql
-- Modify daily_pot_cache
ALTER TABLE daily_pot_cache ADD COLUMN backend_updated_at INTEGER;

-- On fetch, compare:
-- IF backend_updated_at > local_cached_at THEN use backend
-- ELSE keep local (user may have offline changes)
```

#### Add Sync State Tracking

```dart
enum SyncState { synced, pending, conflict }

class CachedDailyPot {
  final DailyPot pot;
  final SyncState syncState;
  final DateTime lastSyncAttempt;
  final String? lastSyncError;
}
```

---

## 4. Backend Schema Analysis

### 4.1 Current Tables

| Table | Purpose | Primary Key | Indexes |
|-------|---------|-------------|---------|
| `sensor_batches` | Raw sensor data | (implicit) | timestamp, device_hash, user_id, JSONB |
| `sensor_aggregates_5m` | 5-min aggregates | (window_start, geohash) | geohash, window_start |
| `sensor_aggregates_daily` | Daily aggregates | (day, geohash) | geohash |
| `user_stats` | Per-device totals | device_hash | last_upload_at, user_id |
| `daily_pots` | Reward system | user_id | last_claim_date |
| `device_secrets` | Auth tokens | device_id | secret |

### 4.2 JSONB Usage Analysis

**Table:** `sensor_batches.batch_json`

```json
{
  "timestamp": 1704067200000,
  "summary": {
    "count": 30,
    "period_start": "2024-01-01T00:00:00Z",
    "period_end": "2024-01-01T00:05:00Z",
    "light": { "avg": 150.5, "min": 10, "max": 500 },
    "accel_rms": 0.98,
    "gyro_rms": 0.05,
    "pressure": { "avg": 1013.25, "min": 1012, "max": 1014 }
  },
  "batch": [
    { "t": 1704067200000, "light": 150, "accel": [0.1, 0.2, 9.8], "gyro": [0.01, 0.02, 0.01], "pressure": 1013.2 },
    // ... more readings
  ],
  "location": { "lat": 52.5200, "lon": 13.4050, "accuracy_m": 10 },
  "geohash": "u33dc0",
  "battery_level": 85,
  "is_charging": false
}
```

**Analysis:**

| Aspect | Assessment | Recommendation |
|--------|------------|----------------|
| Schema flexibility | GOOD - handles optional fields | Keep JSONB |
| Query performance | MODERATE - GIN indexes help | Add expression indexes |
| Storage efficiency | POOR - 30+ readings per batch = bloat | Consider batch table |
| Data integrity | POOR - no schema validation | Add CHECK constraints |

### 4.3 Missing Backend Indexes

```sql
-- RECOMMENDED: Add these indexes

-- For /daily-pot queries (frequently accessed)
CREATE INDEX idx_daily_pots_claim_status ON daily_pots(last_claim_date, uploads_today);

-- For sensor aggregation job (runs every minute)
CREATE INDEX idx_sensor_batches_aggregation ON sensor_batches(timestamp_utc DESC)
  WHERE timestamp_utc > NOW() - INTERVAL '1 hour';

-- For user stats lookups
CREATE INDEX idx_user_stats_device_user ON user_stats(device_hash, user_id);

-- JSONB expression index for pressure data (new field)
CREATE INDEX idx_sensor_batches_pressure ON sensor_batches USING GIN ((batch_json -> 'summary' -> 'pressure'));
```

### 4.4 sensor_batches Table Issues

**Issue: No PRIMARY KEY defined**

From migration files, `sensor_batches` was never explicitly created. It appears to be created implicitly by the first INSERT.

**Recommended Table Definition:**

```sql
CREATE TABLE IF NOT EXISTS sensor_batches (
  id BIGSERIAL PRIMARY KEY,
  device_hash TEXT NOT NULL,
  user_id TEXT,
  timestamp_utc TIMESTAMPTZ NOT NULL,
  batch_json JSONB NOT NULL,
  created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

  -- Constraints
  CONSTRAINT valid_timestamp CHECK (timestamp_utc <= NOW() + INTERVAL '1 minute'),
  CONSTRAINT valid_batch CHECK (jsonb_typeof(batch_json -> 'batch') = 'array')
);

-- Partition by month for scalability (PostgreSQL 11+)
CREATE TABLE sensor_batches_2024_01 PARTITION OF sensor_batches
  FOR VALUES FROM ('2024-01-01') TO ('2024-02-01');
```

---

## 5. Scalability & Growth Projections

### 5.1 Data Volume Estimates

| Metric | Per User/Day | 1K Users/Day | 10K Users/Day |
|--------|--------------|--------------|---------------|
| Uploads | 288 (5min intervals) | 288K | 2.88M |
| Readings | ~30 per upload | 8.6M | 86M |
| SQLite rows (contributions) | 288 | N/A | N/A |
| sensor_batches rows | 288 | 288K | 2.88M |
| sensor_batches size | ~500KB | 500MB | 5GB |

### 5.2 Local SQLite Growth

| Days | Contributions Rows | Estimated Size |
|------|-------------------|----------------|
| 7 | 2,016 | ~200KB |
| 30 | 8,640 | ~800KB |
| 90 | 25,920 | ~2.5MB |
| 365 | 105,120 | ~10MB |

**Current Cleanup Policy:**
```dart
// database_helper.dart:594-609
Future<void> cleanOldData({int daysToKeep = 90}) async {
  // Deletes contributions older than 90 days
}
```

**Issue:** `cleanOldData()` is never called automatically. Must be invoked manually.

### 5.3 Backend PostgreSQL Growth

| Period | sensor_batches | sensor_aggregates_5m | sensor_aggregates_daily |
|--------|---------------|---------------------|------------------------|
| 1 month | 8.6M rows (1K users) | 26K rows | 900 rows |
| 6 months | 52M rows | 156K rows | 5.4K rows |
| 1 year | 105M rows | 315K rows | 10.8K rows |

**Storage Estimate (1 year, 1K users):**
- sensor_batches: ~50GB (JSONB bloat)
- sensor_aggregates_5m: ~50MB
- sensor_aggregates_daily: ~2MB

### 5.4 Recommended Data Retention Policy

```sql
-- Backend: Archive old sensor_batches
CREATE TABLE sensor_batches_archive (
  LIKE sensor_batches INCLUDING ALL
) PARTITION BY RANGE (timestamp_utc);

-- Move data older than 30 days to archive
INSERT INTO sensor_batches_archive
SELECT * FROM sensor_batches WHERE timestamp_utc < NOW() - INTERVAL '30 days';

DELETE FROM sensor_batches WHERE timestamp_utc < NOW() - INTERVAL '30 days';

-- Create archival procedure (run weekly)
CREATE OR REPLACE FUNCTION archive_old_batches() RETURNS void AS $$
BEGIN
  INSERT INTO sensor_batches_archive
  SELECT * FROM sensor_batches WHERE timestamp_utc < NOW() - INTERVAL '30 days';

  DELETE FROM sensor_batches WHERE timestamp_utc < NOW() - INTERVAL '30 days';

  VACUUM sensor_batches;
END;
$$ LANGUAGE plpgsql;
```

### 5.5 Local SQLite Maintenance

```dart
// Add to DatabaseHelper class:

/// Schedule automatic cleanup on app start
Future<void> performMaintenance() async {
  final db = await database;

  // 1. Clean old contributions (>90 days)
  await cleanOldData(daysToKeep: 90);

  // 2. Clean completed tracking sessions (>30 days)
  await db.delete(
    'tracking_sessions',
    where: 'stopped_at IS NOT NULL AND started_at < ?',
    whereArgs: [DateTime.now().subtract(Duration(days: 30)).millisecondsSinceEpoch],
  );

  // 3. Clean stale upload queue entries (>7 days)
  await db.delete(
    'upload_queue',
    where: 'created_at < ?',
    whereArgs: [DateTime.now().subtract(Duration(days: 7)).millisecondsSinceEpoch],
  );

  // 4. Vacuum database
  await db.execute('VACUUM');
}
```

---

## 6. Migration Plan

### 6.1 SQLite v2 -> v3 Migration

```dart
Future<void> _onUpgrade(Database db, int oldVersion, int newVersion) async {
  if (oldVersion < 3) {
    // Add user_id to contributions
    await db.execute('ALTER TABLE contributions ADD COLUMN user_id TEXT');
    await db.execute('CREATE INDEX idx_contributions_user ON contributions(user_id, timestamp DESC)');

    // Add user_id to tracking_sessions
    await db.execute('ALTER TABLE tracking_sessions ADD COLUMN user_id TEXT');
    await db.execute('CREATE INDEX idx_sessions_user ON tracking_sessions(user_id, started_at DESC)');

    // Add session_id FK to contributions
    await db.execute('ALTER TABLE contributions ADD COLUMN session_id INTEGER');
    await db.execute('CREATE INDEX idx_contributions_session ON contributions(session_id)');

    // Add missing indexes
    await db.execute('CREATE INDEX IF NOT EXISTS idx_contributions_geohash ON contributions(geohash)');
    await db.execute('CREATE INDEX IF NOT EXISTS idx_upload_queue_retry ON upload_queue(next_retry_at)');

    // Backfill user_id from app_state (if available)
    final userId = await getState('current_user_id');
    if (userId != null) {
      await db.execute('UPDATE contributions SET user_id = ? WHERE user_id IS NULL', [userId]);
      await db.execute('UPDATE tracking_sessions SET user_id = ? WHERE user_id IS NULL', [userId]);
    }
  }
}
```

### 6.2 Backend Migration Script

```sql
-- 20260114_schema_improvements.sql

-- 1. Add explicit PK to sensor_batches if missing
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_name = 'sensor_batches' AND column_name = 'id'
  ) THEN
    ALTER TABLE sensor_batches ADD COLUMN id BIGSERIAL PRIMARY KEY;
  END IF;
END $$;

-- 2. Add CHECK constraints
ALTER TABLE sensor_batches
  ADD CONSTRAINT valid_batch_json
  CHECK (jsonb_typeof(batch_json -> 'batch') = 'array');

-- 3. Add composite index for daily pot queries
CREATE INDEX IF NOT EXISTS idx_daily_pots_status
  ON daily_pots(user_id, last_claim_date, uploads_today);

-- 4. Add expression index for JSONB pressure queries
CREATE INDEX IF NOT EXISTS idx_sensor_batches_has_pressure
  ON sensor_batches USING BTREE ((batch_json -> 'summary' -> 'pressure' IS NOT NULL));

-- 5. Add timestamp trigger to user_stats
CREATE OR REPLACE FUNCTION update_user_stats_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS user_stats_updated_at ON user_stats;
CREATE TRIGGER user_stats_updated_at
  BEFORE UPDATE ON user_stats
  FOR EACH ROW
  EXECUTE FUNCTION update_user_stats_timestamp();
```

---

## 7. Summary of Recommendations

### Critical (Fix Immediately)

| # | Issue | Fix |
|---|-------|-----|
| 1 | Kotlin/Flutter SQLite concurrency | Unify via MethodChannel OR ensure WAL mode in both |
| 2 | No transactions in write operations | Add db.transaction() wrapper |
| 3 | Missing user_id in SQLite tables | Add column + index in v3 migration |
| 4 | No explicit PK on sensor_batches | Add id BIGSERIAL PRIMARY KEY |

### High Priority (This Sprint)

| # | Issue | Fix |
|---|-------|-----|
| 5 | Missing geohash index | Add CREATE INDEX |
| 6 | No auto-cleanup of local data | Call performMaintenance() on startup |
| 7 | Daily pot cache/backend divergence | Add version tracking + reconciliation |

### Medium Priority (Next Sprint)

| # | Issue | Fix |
|---|-------|-----|
| 8 | upload_queue never processed | Implement retry worker |
| 9 | No partitioning on sensor_batches | Add monthly partitions |
| 10 | JSONB schema validation | Add CHECK constraints |

### Low Priority (Backlog)

| # | Issue | Fix |
|---|-------|-----|
| 11 | app_state loses type safety | Consider SharedPreferences for simple values |
| 12 | Redundant is_unlocked column | Remove (compute from uploads) |

---

## Appendix A: Full ERD

```
+-------------------+       +-------------------+
|   contributions   |       | tracking_sessions |
+-------------------+       +-------------------+
| id (PK)           |       | id (PK, AUTO)     |
| timestamp         |       | started_at        |
| samples_count     |       | stopped_at        |
| geohash           |       | duration_ms       |
| success           |       | samples_collected |
| created_at        |       | uploads_completed |
| user_id (NEW)     |------>| ended_reason      |
| session_id (NEW)  |       | user_id (NEW)     |
+-------------------+       +-------------------+

+-------------------+       +-------------------+
| daily_pot_cache   |       |    upload_queue   |
+-------------------+       +-------------------+
| user_id (PK)      |       | id (PK)           |
| total_credits     |       | payload           |
| uploads_today     |       | created_at        |
| uploads_required  |       | retry_count       |
| is_unlocked       |       | last_error        |
| has_claimed_today |       | next_retry_at     |
| last_claim_date   |       +-------------------+
| last_synced_at    |
| cached_at         |       +-------------------+
+-------------------+       |    app_state      |
                            +-------------------+
                            | key (PK)          |
                            | value             |
                            | updated_at        |
                            +-------------------+
```

## Appendix B: Query Performance Analysis

### Slow Queries Identified

1. **getTodayTileCoverage()** - Full scan on contributions due to complex CASE statement
2. **getStreak()** - Full scan + sorting for streak calculation
3. **getActiveTrackingSession()** - No index on `stopped_at IS NULL`

### Recommended Query Optimizations

```sql
-- 1. Partial index for active sessions
CREATE INDEX idx_active_sessions ON tracking_sessions(started_at DESC) WHERE stopped_at IS NULL;

-- 2. Materialized view for streak calculation
CREATE MATERIALIZED VIEW user_streaks AS
SELECT
  user_id,
  COUNT(DISTINCT DATE(timestamp / 1000, 'unixepoch')) as total_days,
  -- streak calculation here
FROM contributions
WHERE success = 1
GROUP BY user_id;
```

---

**End of Report**
