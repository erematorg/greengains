import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import '../core/themes.dart';
import '../services/daily_reward_service.dart';
import '../data/models/daily_reward.dart';
import '../utils/app_snackbars.dart';

/// Subtle daily reward badge - shows in app bar or as floating indicator
/// Non-intrusive: just a small badge that pulses when claimable
class DailyRewardBadge extends StatefulWidget {
  const DailyRewardBadge({super.key});

  @override
  State<DailyRewardBadge> createState() => _DailyRewardBadgeState();
}

class _DailyRewardBadgeState extends State<DailyRewardBadge>
    with SingleTickerProviderStateMixin {
  final _service = DailyRewardService.instance;
  late AnimationController _pulseController;
  late Animation<double> _pulseAnimation;
  bool _claiming = false;

  @override
  void initState() {
    super.initState();
    _pulseController = AnimationController(
      duration: const Duration(milliseconds: 1500),
      vsync: this,
    )..repeat(reverse: true);
    _pulseAnimation = Tween<double>(begin: 1.0, end: 1.08).animate(
      CurvedAnimation(parent: _pulseController, curve: Curves.easeInOut),
    );
  }

  @override
  void dispose() {
    _pulseController.dispose();
    super.dispose();
  }

  Future<void> _claimReward() async {
    if (_claiming) return;
    setState(() => _claiming = true);

    HapticFeedback.mediumImpact();
    final success = await _service.claimReward();

    if (!mounted) return;
    setState(() => _claiming = false);

    if (success) {
      final reward = _service.reward.value;
      if (reward != null) {
        HapticFeedback.heavyImpact();
        AppSnackbars.showSuccess(
          context,
          '+${reward.getTotalRewardToday()} credits claimed! 🎉',
        );
      }
    } else {
      AppSnackbars.showInfo(context, 'Already claimed today');
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    return ValueListenableBuilder<DailyReward?>(
      valueListenable: _service.reward,
      builder: (context, reward, _) {
        if (reward == null) {
          return const SizedBox.shrink();
        }

        final canClaim = reward.canClaimToday;
        final credits = reward.totalCredits;
        final streak = reward.currentStreak;

        return GestureDetector(
          onTap: canClaim ? _claimReward : null,
          child: AnimatedBuilder(
            animation: _pulseAnimation,
            builder: (context, child) {
              return Transform.scale(
                scale: canClaim ? _pulseAnimation.value : 1.0,
                child: child,
              );
            },
            child: Container(
              padding: const EdgeInsets.symmetric(
                horizontal: AppTheme.spaceMd,
                vertical: AppTheme.spaceSm,
              ),
              decoration: BoxDecoration(
                gradient: canClaim
                    ? const LinearGradient(
                        colors: AppColors.gradientGreen,
                        begin: Alignment.topLeft,
                        end: Alignment.bottomRight,
                      )
                    : null,
                color: canClaim ? null : AppColors.surfaceElevated(isDark),
                borderRadius: BorderRadius.circular(AppTheme.radiusPill),
                border: Border.all(
                  color: canClaim
                      ? AppColors.primary.withValues(alpha: 0.5)
                      : AppColors.border(isDark),
                  width: canClaim ? 2 : 1,
                ),
                boxShadow: canClaim
                    ? [
                        BoxShadow(
                          color: AppColors.primary.withValues(alpha: 0.3),
                          blurRadius: 12,
                          offset: const Offset(0, 4),
                        ),
                      ]
                    : null,
              ),
              child: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  // Icon
                  Icon(
                    canClaim ? Icons.card_giftcard : Icons.stars,
                    size: 18,
                    color: canClaim ? Colors.white : AppColors.primary,
                  ),
                  const SizedBox(width: AppTheme.spaceXs),

                  // Credits count
                  if (_claiming)
                    const SizedBox(
                      width: 14,
                      height: 14,
                      child: CircularProgressIndicator(
                        strokeWidth: 2,
                        valueColor: AlwaysStoppedAnimation(Colors.white),
                      ),
                    )
                  else
                    Text(
                      '$credits',
                      style: theme.textTheme.labelLarge?.copyWith(
                        color: canClaim ? Colors.white : AppColors.primary,
                        fontWeight: FontWeight.w700,
                      ),
                    ),

                  // Streak indicator (if active)
                  if (streak > 0) ...[
                    const SizedBox(width: AppTheme.spaceXs),
                    Container(
                      padding: const EdgeInsets.symmetric(
                        horizontal: 6,
                        vertical: 2,
                      ),
                      decoration: BoxDecoration(
                        color: canClaim
                            ? Colors.white.withValues(alpha: 0.2)
                            : AppColors.warning.withValues(alpha: 0.15),
                        borderRadius: BorderRadius.circular(8),
                      ),
                      child: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          Icon(
                            Icons.local_fire_department,
                            size: 12,
                            color: canClaim ? Colors.white : AppColors.warning,
                          ),
                          const SizedBox(width: 2),
                          Text(
                            '$streak',
                            style: theme.textTheme.labelSmall?.copyWith(
                              color:
                                  canClaim ? Colors.white : AppColors.warning,
                              fontWeight: FontWeight.w600,
                              fontSize: 11,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ],
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
