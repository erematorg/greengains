import 'package:flutter/foundation.dart';

/// Daily pot model - simplified Honeygain-style reward system
/// No streaks, just: upload data → unlock pot → claim random reward
@immutable
class DailyPot {
  final String userId;
  final int totalCredits; // Lifetime credits earned
  final int uploadsToday; // Progress toward unlocking pot
  final int uploadsRequired; // How many uploads needed to unlock (default: 5)
  final bool isUnlocked; // Can claim pot?
  final bool hasClaimedToday; // Already claimed today?
  final DateTime? lastClaimDate; // UTC date (not timestamp)

  const DailyPot({
    required this.userId,
    required this.totalCredits,
    required this.uploadsToday,
    required this.uploadsRequired,
    required this.isUnlocked,
    required this.hasClaimedToday,
    this.lastClaimDate,
  });

  factory DailyPot.empty(String userId) {
    return DailyPot(
      userId: userId,
      totalCredits: 0,
      uploadsToday: 0,
      uploadsRequired: 5, // Default: 5 uploads to unlock
      isUnlocked: false,
      hasClaimedToday: false,
      lastClaimDate: null,
    );
  }

  factory DailyPot.fromJson(Map<String, dynamic> json) {
    return DailyPot(
      userId: json['user_id'] as String,
      totalCredits: json['total_credits'] as int,
      uploadsToday: json['uploads_today'] as int,
      uploadsRequired: json['uploads_required'] as int,
      isUnlocked: json['is_unlocked'] as bool,
      hasClaimedToday: json['has_claimed_today'] as bool,
      lastClaimDate: json['last_claim_date'] != null
          ? DateTime.parse(json['last_claim_date'] as String)
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'user_id': userId,
      'total_credits': totalCredits,
      'uploads_today': uploadsToday,
      'uploads_required': uploadsRequired,
      'is_unlocked': isUnlocked,
      'has_claimed_today': hasClaimedToday,
      'last_claim_date': lastClaimDate?.toIso8601String(),
    };
  }

  /// Progress percentage (0.0 to 1.0)
  double get progress =>
      (uploadsToday / uploadsRequired).clamp(0.0, 1.0);

  /// Can the user claim the pot?
  bool get canClaim => isUnlocked && !hasClaimedToday;

  DailyPot copyWith({
    String? userId,
    int? totalCredits,
    int? uploadsToday,
    int? uploadsRequired,
    bool? isUnlocked,
    bool? hasClaimedToday,
    DateTime? lastClaimDate,
  }) {
    return DailyPot(
      userId: userId ?? this.userId,
      totalCredits: totalCredits ?? this.totalCredits,
      uploadsToday: uploadsToday ?? this.uploadsToday,
      uploadsRequired: uploadsRequired ?? this.uploadsRequired,
      isUnlocked: isUnlocked ?? this.isUnlocked,
      hasClaimedToday: hasClaimedToday ?? this.hasClaimedToday,
      lastClaimDate: lastClaimDate ?? this.lastClaimDate,
    );
  }
}
