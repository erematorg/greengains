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
