import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

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
