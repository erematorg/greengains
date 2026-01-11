import 'package:flutter/foundation.dart';

/// Daily login reward model
/// Server-authoritative to prevent clock manipulation
@immutable
class DailyReward {
  final String userId;
  final int totalCredits;
  final int currentStreak;
  final int longestStreak;
  final DateTime? lastClaimDate; // UTC date, not timestamp
  final bool canClaimToday;
  final int todayReward; // Credits to claim today

  const DailyReward({
    required this.userId,
    required this.totalCredits,
    required this.currentStreak,
    required this.longestStreak,
    this.lastClaimDate,
    required this.canClaimToday,
    required this.todayReward,
  });

  factory DailyReward.empty(String userId) {
    return DailyReward(
      userId: userId,
      totalCredits: 0,
      currentStreak: 0,
      longestStreak: 0,
      lastClaimDate: null,
      canClaimToday: true,
      todayReward: 10, // Base reward
    );
  }

  factory DailyReward.fromJson(Map<String, dynamic> json) {
    return DailyReward(
      userId: json['user_id'] as String,
      totalCredits: json['total_credits'] as int,
      currentStreak: json['current_streak'] as int,
      longestStreak: json['longest_streak'] as int,
      lastClaimDate: json['last_claim_date'] != null
          ? DateTime.parse(json['last_claim_date'] as String)
          : null,
      canClaimToday: json['can_claim_today'] as bool,
      todayReward: json['today_reward'] as int,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'user_id': userId,
      'total_credits': totalCredits,
      'current_streak': currentStreak,
      'longest_streak': longestStreak,
      'last_claim_date': lastClaimDate?.toIso8601String(),
      'can_claim_today': canClaimToday,
      'today_reward': todayReward,
    };
  }

  /// Calculate reward based on streak (bonus for consistency)
  int getStreakBonus() {
    if (currentStreak < 3) return 0;
    if (currentStreak < 7) return 5; // 3-6 days: +5 credits
    if (currentStreak < 14) return 10; // 7-13 days: +10 credits
    if (currentStreak < 30) return 15; // 14-29 days: +15 credits
    return 20; // 30+ days: +20 credits
  }

  int getTotalRewardToday() => todayReward + getStreakBonus();

  DailyReward copyWith({
    String? userId,
    int? totalCredits,
    int? currentStreak,
    int? longestStreak,
    DateTime? lastClaimDate,
    bool? canClaimToday,
    int? todayReward,
  }) {
    return DailyReward(
      userId: userId ?? this.userId,
      totalCredits: totalCredits ?? this.totalCredits,
      currentStreak: currentStreak ?? this.currentStreak,
      longestStreak: longestStreak ?? this.longestStreak,
      lastClaimDate: lastClaimDate ?? this.lastClaimDate,
      canClaimToday: canClaimToday ?? this.canClaimToday,
      todayReward: todayReward ?? this.todayReward,
    );
  }
}
