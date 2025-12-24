import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import '../models/contribution_stats.dart';

/// Minimal SQLite database for tracking contributions locally
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
    // Minimal schema: just track uploads
    await db.execute('''
      CREATE TABLE contributions (
        id TEXT PRIMARY KEY,
        timestamp INTEGER NOT NULL,
        samples_count INTEGER NOT NULL,
        geohash TEXT,
        success INTEGER NOT NULL,
        created_at INTEGER NOT NULL
      )
    ''');

    // Index for fast queries
    await db.execute(
      'CREATE INDEX idx_timestamp ON contributions(timestamp)',
    );
  }

  /// Insert a contribution record
  Future<void> insertContribution({
    required String id,
    required DateTime timestamp,
    required int samplesCount,
    String? geohash,
    required bool success,
  }) async {
    final db = await database;
    await db.insert('contributions', {
      'id': id,
      'timestamp': timestamp.millisecondsSinceEpoch,
      'samples_count': samplesCount,
      'geohash': geohash,
      'success': success ? 1 : 0,
      'created_at': DateTime.now().millisecondsSinceEpoch,
    });
  }

  /// Get total successful uploads count
  Future<int> getTotalCount() async {
    final db = await database;
    final result = await db.rawQuery(
      'SELECT COUNT(*) as count FROM contributions WHERE success = 1',
    );
    return Sqflite.firstIntValue(result) ?? 0;
  }

  /// Get today's uploads count
  Future<int> getTodayCount() async {
    final db = await database;
    final now = DateTime.now();
    final todayStart = DateTime(now.year, now.month, now.day);

    final result = await db.rawQuery(
      'SELECT COUNT(*) as count FROM contributions WHERE timestamp >= ? AND success = 1',
      [todayStart.millisecondsSinceEpoch],
    );
    return Sqflite.firstIntValue(result) ?? 0;
  }

  /// Get today's tile coverage split by day/night (distinct geohash per period)
  Future<TileCoverageStats> getTodayTileCoverage() async {
    final db = await database;
    final now = DateTime.now();
    final todayStart = DateTime(now.year, now.month, now.day);

    final results = await db.rawQuery(
      '''
        SELECT part, COUNT(*) as count
        FROM (
          SELECT DISTINCT
            geohash,
            CASE
              WHEN CAST(STRFTIME('%H', datetime(timestamp / 1000, 'unixepoch', 'localtime')) AS INTEGER)
                BETWEEN 6 AND 19 THEN 'day'
              ELSE 'night'
            END as part
          FROM contributions
          WHERE success = 1
            AND geohash IS NOT NULL
            AND geohash != ''
            AND timestamp >= ?
        ) t
        GROUP BY part
      ''',
      [todayStart.millisecondsSinceEpoch],
    );

    var day = 0;
    var night = 0;
    for (final row in results) {
      final part = row['part']?.toString();
      final count = row['count'] is int
          ? row['count'] as int
          : int.tryParse(row['count']?.toString() ?? '') ?? 0;
      if (part == 'day') {
        day = count;
      } else if (part == 'night') {
        night = count;
      }
    }

    return TileCoverageStats(dayTiles: day, nightTiles: night);
  }

  /// Get current streak (consecutive days with uploads)
  Future<int> getStreak() async {
    final db = await database;

    // Get distinct dates with successful uploads, ordered desc
    final results = await db.rawQuery('''
      SELECT DISTINCT DATE(timestamp / 1000, 'unixepoch') as date
      FROM contributions
      WHERE success = 1
      ORDER BY date DESC
    ''');

    if (results.isEmpty) return 0;

    int streak = 0;
    DateTime? expectedDate = DateTime.now();

    for (final row in results) {
      final dateStr = row['date'] as String;
      final date = DateTime.parse(dateStr);

      // Normalize to start of day
      final normalizedDate = DateTime(date.year, date.month, date.day);
      final normalizedExpected =
          DateTime(expectedDate!.year, expectedDate.month, expectedDate.day);

      if (normalizedDate == normalizedExpected) {
        streak++;
        expectedDate = normalizedDate.subtract(const Duration(days: 1));
      } else {
        break;
      }
    }

    return streak;
  }
}
