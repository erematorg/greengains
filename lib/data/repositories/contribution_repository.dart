import '../local/database_helper.dart';
import '../models/contribution_stats.dart';

/// Repository for contribution statistics
class ContributionRepository {
  final DatabaseHelper _db = DatabaseHelper.instance;

  /// Get current statistics
  Future<ContributionStats> getStats() async {
    final results = await Future.wait<int>([
      _db.getTotalCount(),
      _db.getTodayCount(),
      _db.getStreak(),
    ]);

    return ContributionStats(
      totalUploads: results[0],
      uploadsToday: results[1],
      currentStreak: results[2],
    );
  }

  Future<TileCoverageStats> getTodayTileCoverage() async {
    return _db.getTodayTileCoverage();
  }
}
