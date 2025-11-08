# Nodle Cash Deep Analysis & Implementation Plan for Greengains

## 🔍 Analysis Summary

**Decompiled Components:**
- ✅ Wallet module (zkSync, tokens, crypto integration)
- ✅ API layer (Retrofit, Kotlin coroutines)
- ✅ Repository pattern implementation
- ❌ Crowdsensing SDK (native libraries, not decompiled)
- ❌ UI layouts (heavily obfuscated by R8/ProGuard)

---

## 🏗️ Architecture Patterns Found

### 1. **Clean Architecture**
```
Presentation Layer (ViewModels)
    ↓
Domain Layer (Use Cases/Repositories)
    ↓
Data Layer (API/Database/Services)
```

**Nodle Implementation:**
- `ZkRewardsRepository` - Data source abstraction
- `ZkRewardsApi` - Network interface
- Dagger2 for dependency injection (@Inject)
- Kotlin Coroutines with `CoroutineDispatchers`

**Apply to Greengains:**
```dart
lib/
  data/
    repositories/
      contribution_repository.dart
      sensor_data_repository.dart
    datasources/
      local/
        contribution_local_datasource.dart  // SQLite
      remote/
        backend_api.dart
  domain/
    models/
      contribution.dart
      stats.dart
    usecases/
      get_contribution_stats.dart
  presentation/
    screens/
    viewmodels/
```

---

## 📊 Key Features Nodle Has (That Greengains Needs)

### **Feature 1: Contribution Tracking**

**Implementation Priority:** 🔴 CRITICAL

**What Nodle tracks:**
- Packets sent (real-time counter)
- Daily/weekly/monthly earnings
- Contribution streaks
- Historical data

**Greengains Implementation:**

```dart
// lib/data/models/contribution.dart
class Contribution {
  final String id;
  final DateTime timestamp;
  final int samplesCount;
  final String geohash;
  final bool success;
  final double? earnings; // Future: token rewards
}

// lib/data/models/contribution_stats.dart
class ContributionStats {
  final int uploadsToday;
  final int uploadsThisWeek;
  final int uploadsThisMonth;
  final int totalUploads;
  final int currentStreak; // Consecutive days
  final DateTime? firstContribution;
  final DateTime? lastContribution;

  // Gamification
  final int level; // Based on total uploads
  final List<String> badges;
}
```

**Database Schema (SQLite):**
```sql
CREATE TABLE contributions (
  id TEXT PRIMARY KEY,
  timestamp INTEGER NOT NULL,
  samples_count INTEGER NOT NULL,
  geohash TEXT,
  success INTEGER NOT NULL,
  created_at INTEGER NOT NULL
);

CREATE INDEX idx_timestamp ON contributions(timestamp);
CREATE INDEX idx_geohash ON contributions(geohash);
```

---

### **Feature 2: Real-Time Statistics Display**

**What to show:**
```
┌─────────────────────────────────┐
│   Today's Contributions         │
│   ███████████░░░ 42 uploads     │
│                                 │
│   This Week: 287 uploads        │
│   Current Streak: 12 days 🔥    │
│                                 │
│   Total Contributions: 3,421    │
│   Since: Jan 2025               │
└─────────────────────────────────┘
```

---

### **Feature 3: Heatmap Visualization**

**Current Need:** Visual representation of contributions by location

**Implementation Plan:**

```dart
// Use H3 hexagons for privacy-preserving heatmaps
dependencies:
  h3_flutter: ^1.0.0
  flutter_map: ^6.0.0

// lib/screens/heatmap_screen.dart
class HeatmapScreen extends StatelessWidget {
  // Show contributions aggregated by H3 hex
  // Color intensity = contribution density
  // Resolution 8 (~0.5km² cells)
}
```

**Backend Support:**
```typescript
// backend/src/routes/analytics.ts
app.get('/analytics/heatmap/:resolution', async (req, res) => {
  // Aggregate contributions by H3 cell
  // Return geojson with contribution counts
});
```

---

## 🎨 UI/UX Improvements

### **Priority 1: Contribution Dashboard**

```dart
// New tab in AppShell
class DashboardScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        _buildTodayCard(),      // Today's stats
        _buildStreakCard(),     // Streak counter with fire emoji
        _buildWeeklyChart(),    // Bar chart of last 7 days
        _buildMilestones(),     // Next milestone progress
        _buildBadges(),         // Earned badges
      ],
    );
  }
}
```

### **Priority 2: Enhanced Upload Status**

**Current:** "Last upload: 5m ago"

**Nodle-style:**
```
┌────────────────────────────┐
│ ✓ Upload Successful        │
│ 12 samples • geohash: u0t1z9│
│ 2 minutes ago              │
└────────────────────────────┘

Recent Activity:
• 18:23 - 12 samples uploaded
• 18:21 - 16 samples uploaded
• 18:19 - 14 samples uploaded
```

---

## 🔄 Background Service Patterns

### **Nodle's Approach (Inferred):**

1. **Foreground Service** for continuous data collection
2. **WorkManager** for periodic uploads (when service not running)
3. **Battery Optimization** - adaptive sampling rates
4. **Network Awareness** - WiFi-only mode

**Greengains Already Has:**
- ✅ Foreground service with proper lifecycle
- ✅ Background sensor collection
- ✅ Batching (12 samples or 2 min)

**Still Need:**
- ❌ WorkManager backup (if foreground service killed)
- ❌ Battery-adaptive sampling
- ❌ Retry queue for failed uploads

---

## 📱 Gamification System

### **Levels & Milestones**

```dart
enum ContributionLevel {
  newbie(0, 'Newbie', '🌱'),
  contributor(100, 'Contributor', '🌿'),
  regular(500, 'Regular', '🌳'),
  champion(1000, 'Champion', '🏆'),
  legend(5000, 'Legend', '⭐'),
}

class Milestone {
  final String title;
  final String description;
  final int target;
  final String emoji;

  // Examples:
  // "First Upload" - 1 upload
  // "Week Warrior" - 7 day streak
  // "Century Club" - 100 total uploads
  // "Night Owl" - Upload between 22:00-06:00
}
```

### **Badges**

```dart
enum Badge {
  earlyAdopter('Early Adopter', '🚀'),
  weekStreak('Week Streak', '🔥'),
  monthStreak('Month Streak', '💎'),
  nightOwl('Night Owl', '🦉'),
  cityExplorer('City Explorer', '🗺️'), // 10+ different geohashes
  dataScientist('Data Scientist', '📊'), // 1000+ samples
}
```

---

## 🗺️ Geospatial Features

### **H3 Hexagon Integration**

**Why H3?**
- Privacy-preserving (consistent cell sizes)
- Hierarchical (zoom levels)
- Perfect for heatmaps

**Greengains Implementation:**

```dart
import 'package:h3_flutter/h3_flutter.dart';

class GeohashService {
  final h3 = H3Factory.load();

  // Convert lat/lon to H3 hex (resolution 8 ≈ 0.5km²)
  String getH3Cell(double lat, double lon, int resolution) {
    return h3.geoToH3(lat, lon, resolution);
  }

  // Get neighboring cells for expansion
  List<String> getNeighbors(String h3Cell) {
    return h3.kRing(h3Cell, 1);
  }

  // For heatmap visualization
  Map<String, int> aggregateByH3(List<Contribution> contributions) {
    final counts = <String, int>{};
    for (final contrib in contributions) {
      if (contrib.geohash != null) {
        final h3Cell = getH3Cell(
          contrib.location.lat,
          contrib.location.lon,
          8, // Resolution 8
        );
        counts[h3Cell] = (counts[h3Cell] ?? 0) + 1;
      }
    }
    return counts;
  }
}
```

### **Heatmap Visualization**

```dart
import 'package:flutter_map/flutter_map.dart';

class ContributionHeatmapLayer extends StatelessWidget {
  final Map<String, int> h3Counts;

  @override
  Widget build(BuildContext context) {
    return PolygonLayer(
      polygons: h3Counts.entries.map((entry) {
        final boundary = h3.h3ToGeoBoundary(entry.key);
        final intensity = _calculateIntensity(entry.value);

        return Polygon(
          points: boundary,
          color: Colors.blue.withOpacity(intensity),
          borderColor: Colors.blue.withOpacity(0.8),
          borderStrokeWidth: 1,
        );
      }).toList(),
    );
  }

  double _calculateIntensity(int count) {
    // Normalize to 0.1 - 0.8 opacity
    return 0.1 + (count / maxCount) * 0.7;
  }
}
```

---

## 📋 Implementation Roadmap

### **Phase 1: Contribution Tracking** (Tonight - 2-3 hours)

1. Create SQLite database schema
2. Implement `ContributionRepository`
3. Update `SensorUploader` to save to DB
4. Create `ContributionStats` model and queries

### **Phase 2: Dashboard UI** (Tomorrow - 3-4 hours)

1. Create Dashboard screen
2. Add stats cards (today, week, total)
3. Implement streak counter
4. Add recent activity list

### **Phase 3: Gamification** (Day 3 - 2-3 hours)

1. Level system based on total uploads
2. Milestone tracking
3. Badge earning logic
4. Achievement notifications

### **Phase 4: Heatmap** (Day 4 - 4-5 hours)

1. Add H3 flutter package
2. Create heatmap screen
3. Backend analytics endpoint
4. Interactive map with contribution density

### **Phase 5: Visual Polish** (Day 5 - 3-4 hours)

1. Success animations (Lottie)
2. Better upload feedback
3. Improved color scheme
4. Micro-interactions

---

## 🎯 Quick Wins for Tonight

### **1. Add SQLite Database**

```bash
flutter pub add sqflite path
```

```dart
// lib/data/local/database_helper.dart
class DatabaseHelper {
  static final DatabaseHelper instance = DatabaseHelper._();
  static Database? _database;

  DatabaseHelper._();

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'greengains.db');

    return await openDatabase(
      path,
      version: 1,
      onCreate: _onCreate,
    );
  }

  Future<void> _onCreate(Database db, int version) async {
    await db.execute('''
      CREATE TABLE contributions (
        id TEXT PRIMARY KEY,
        timestamp INTEGER NOT NULL,
        samples_count INTEGER NOT NULL,
        geohash TEXT,
        lat REAL,
        lon REAL,
        success INTEGER NOT NULL,
        created_at INTEGER NOT NULL
      )
    ''');

    await db.execute('''
      CREATE INDEX idx_timestamp ON contributions(timestamp)
    ''');

    await db.execute('''
      CREATE INDEX idx_geohash ON contributions(geohash)
    ''');
  }
}
```

### **2. Track Uploads in Database**

```dart
// In SensorUploader after successful upload:
await _saveContribution(
  samplesCount: _buffer.length,
  geohash: geohash,
  location: _lastLocation,
  success: true,
);

Future<void> _saveContribution({
  required int samplesCount,
  String? geohash,
  LocationData? location,
  required bool success,
}) async {
  final db = await DatabaseHelper.instance.database;
  await db.insert('contributions', {
    'id': const Uuid().v4(),
    'timestamp': DateTime.now().millisecondsSinceEpoch,
    'samples_count': samplesCount,
    'geohash': geohash,
    'lat': location?.lat,
    'lon': location?.lon,
    'success': success ? 1 : 0,
    'created_at': DateTime.now().millisecondsSinceEpoch,
  });
}
```

### **3. Query Statistics**

```dart
// lib/data/repositories/contribution_repository.dart
class ContributionRepository {
  final DatabaseHelper _db = DatabaseHelper.instance;

  Future<ContributionStats> getStats() async {
    final db = await _db.database;

    final now = DateTime.now();
    final todayStart = DateTime(now.year, now.month, now.day);
    final weekStart = now.subtract(Duration(days: 7));
    final monthStart = DateTime(now.year, now.month, 1);

    final stats = await Future.wait([
      _countSince(db, todayStart),
      _countSince(db, weekStart),
      _countSince(db, monthStart),
      _countTotal(db),
      _getStreak(db),
      _getFirstContribution(db),
    ]);

    return ContributionStats(
      uploadsToday: stats[0] as int,
      uploadsThisWeek: stats[1] as int,
      uploadsThisMonth: stats[2] as int,
      totalUploads: stats[3] as int,
      currentStreak: stats[4] as int,
      firstContribution: stats[5] as DateTime?,
    );
  }

  Future<int> _countSince(Database db, DateTime since) async {
    final result = await db.rawQuery(
      'SELECT COUNT(*) as count FROM contributions WHERE timestamp >= ? AND success = 1',
      [since.millisecondsSinceEpoch],
    );
    return Sqflite.firstIntValue(result) ?? 0;
  }

  Future<int> _getStreak(Database db) async {
    // Calculate consecutive days with uploads
    final results = await db.rawQuery('''
      SELECT DISTINCT DATE(timestamp / 1000, 'unixepoch') as date
      FROM contributions
      WHERE success = 1
      ORDER BY date DESC
    ''');

    int streak = 0;
    DateTime? lastDate;

    for (final row in results) {
      final date = DateTime.parse(row['date'] as String);
      if (lastDate == null) {
        lastDate = date;
        streak = 1;
      } else if (lastDate.difference(date).inDays == 1) {
        streak++;
        lastDate = date;
      } else {
        break;
      }
    }

    return streak;
  }
}
```

---

## 💡 Key Takeaways

**What Nodle Does Well:**
1. Clean architecture with clear separation of concerns
2. Coroutines for async operations
3. Repository pattern for data abstraction
4. Gamification to engage users

**What Greengains Should Adopt:**
1. ✅ Local database for contribution tracking
2. ✅ Statistics dashboard
3. ✅ Streak counter and milestones
4. ✅ H3 heatmap visualization
5. ✅ Visual feedback for uploads

**Greengains' Unique Advantage:**
- 🌟 **Environmental focus** (light pollution, urban sensing)
- 🌟 **Open data** (not just blockchain rewards)
- 🌟 **Smart cities integration**
- 🌟 **Aggregated insights** (5min + daily)

---

## 🚀 Let's Build This Tonight!

Ready to implement? I recommend starting with **Phase 1: Contribution Tracking** to have immediate value.

Should I proceed with implementing:
1. SQLite database setup
2. Contribution tracking in SensorUploader
3. Stats repository and queries
4. Basic dashboard screen

All in one go?
