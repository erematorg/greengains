/// Simple model for contribution statistics
class ContributionStats {
  final int totalUploads;
  final int uploadsToday;
  final int currentStreak;

  const ContributionStats({
    required this.totalUploads,
    required this.uploadsToday,
    required this.currentStreak,
  });

  static const ContributionStats empty = ContributionStats(
    totalUploads: 0,
    uploadsToday: 0,
    currentStreak: 0,
  );
}

class TileCoverageStats {
  final int dayTiles;
  final int nightTiles;

  const TileCoverageStats({
    required this.dayTiles,
    required this.nightTiles,
  });

  int get totalTiles => dayTiles + nightTiles;

  static const TileCoverageStats empty = TileCoverageStats(
    dayTiles: 0,
    nightTiles: 0,
  );
}
