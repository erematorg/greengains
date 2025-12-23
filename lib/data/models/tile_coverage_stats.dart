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
