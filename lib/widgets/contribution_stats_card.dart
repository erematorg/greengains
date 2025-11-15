import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';
import '../services/location/foreground_location_service.dart';
import '../core/themes.dart';

/// Compact horizontal contribution stats bar
/// Optimized for fast rendering and clean code
class ContributionStatsCard extends StatefulWidget {
  const ContributionStatsCard({super.key});

  @override
  State<ContributionStatsCard> createState() => ContributionStatsCardState();
}

class ContributionStatsCardState extends State<ContributionStatsCard>
    with SingleTickerProviderStateMixin {
  final _repository = ContributionRepository();
  ContributionStats _stats = ContributionStats.empty;
  bool _loading = true;
  String? _error;
  late AnimationController _pulseController;
  late Animation<double> _pulseAnimation;

  @override
  void initState() {
    super.initState();
    _pulseController = AnimationController(
      duration: const Duration(milliseconds: 800),
      vsync: this,
    );
    _pulseAnimation = Tween<double>(begin: 1.0, end: 0.85).animate(
      CurvedAnimation(parent: _pulseController, curve: Curves.easeInOut),
    );
    _loadStats();
    // Listen to stats refresh trigger from service
    ForegroundLocationService.instance.statsRefreshTrigger.addListener(_onStatsRefreshTriggered);
  }

  @override
  void dispose() {
    ForegroundLocationService.instance.statsRefreshTrigger.removeListener(_onStatsRefreshTriggered);
    _pulseController.dispose();
    super.dispose();
  }

  void _onStatsRefreshTriggered() {
    if (mounted) {
      _loadStats();
      // Subtle pulse animation to indicate update
      _pulseController.forward().then((_) => _pulseController.reverse());
    }
  }

  /// Public method to refresh stats from parent widget
  Future<void> refresh() => _loadStats();

  Future<void> _loadStats() async {
    setState(() {
      _loading = true;
      _error = null;
    });
    try {
      final stats = await _repository.getStats();
      if (mounted) {
        setState(() {
          _stats = stats;
          _loading = false;
        });
      }
    } catch (e) {
      if (mounted) {
        setState(() {
          _loading = false;
          _error = e.toString();
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    // Skeleton loading state
    if (_loading) {
      return Container(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        decoration: BoxDecoration(
          color: isDark ? AppColors.darkSurface : AppColors.lightSurface,
          borderRadius: BorderRadius.circular(12),
          boxShadow: isDark
              ? AppColors.elevationDark(active: false)
              : AppColors.elevationLight(active: false),
        ),
        child: Row(
          children: [
            // Icon skeleton
            Container(
              width: 36,
              height: 36,
              decoration: BoxDecoration(
                color: isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurfaceElevated,
                borderRadius: BorderRadius.circular(8),
              ),
            ),
            const SizedBox(width: AppTheme.spaceMd),

            // Stats skeleton
            Expanded(
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  _buildSkeletonStat(isDark),
                  _buildDivider(isDark),
                  _buildSkeletonStat(isDark),
                  _buildDivider(isDark),
                  _buildSkeletonStat(isDark),
                ],
              ),
            ),

            const SizedBox(width: AppTheme.spaceXs),
            // Refresh button skeleton
            Container(
              width: 20,
              height: 20,
              decoration: BoxDecoration(
                color: isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurfaceElevated,
                borderRadius: BorderRadius.circular(4),
              ),
            ),
          ],
        ),
      );
    }

    // Error state
    if (_error != null) {
      return Container(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        decoration: BoxDecoration(
          color: isDark ? AppColors.darkSurface : AppColors.lightSurface,
          borderRadius: BorderRadius.circular(12),
          border: Border.all(
            color: AppColors.error.withValues(alpha: 0.3),
            width: 1,
          ),
          boxShadow: isDark
              ? AppColors.elevationDark(active: false)
              : AppColors.elevationLight(active: false),
        ),
        child: Row(
          children: [
            Container(
              padding: const EdgeInsets.all(AppTheme.spaceXs),
              decoration: BoxDecoration(
                color: AppColors.error.withValues(alpha: 0.1),
                borderRadius: BorderRadius.circular(8),
              ),
              child: Icon(
                Icons.error_outline,
                color: AppColors.error,
                size: 20,
              ),
            ),
            const SizedBox(width: AppTheme.spaceSm),
            Expanded(
              child: Text(
                'Failed to load stats',
                style: theme.textTheme.bodyMedium?.copyWith(
                  color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                ),
              ),
            ),
            TextButton(
              onPressed: _loadStats,
              style: TextButton.styleFrom(
                padding: const EdgeInsets.symmetric(horizontal: AppTheme.spaceMd, vertical: AppTheme.spaceXs),
              ),
              child: const Text('Retry'),
            ),
          ],
        ),
      );
    }

    // Empty state for first-time users
    if (_stats.totalUploads == 0) {
      return Container(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        decoration: BoxDecoration(
          color: isDark ? AppColors.darkSurface : AppColors.lightSurface,
          borderRadius: BorderRadius.circular(12),
          border: Border.all(
            color: AppColors.primary.withValues(alpha: 0.2),
            width: 1.5,
            strokeAlign: BorderSide.strokeAlignInside,
          ),
          boxShadow: isDark
              ? AppColors.elevationDark(active: false)
              : AppColors.elevationLight(active: false),
        ),
        child: Row(
          children: [
            Container(
              padding: const EdgeInsets.all(10),
              decoration: BoxDecoration(
                color: AppColors.primaryAlpha(0.12),
                borderRadius: BorderRadius.circular(10),
              ),
              child: Icon(
                Icons.eco_outlined,
                color: AppColors.primary,
                size: 24,
              ),
            ),
            const SizedBox(width: AppTheme.spaceMd),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Ready to contribute?',
                    style: theme.textTheme.titleSmall?.copyWith(
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  const SizedBox(height: 2),
                  Text(
                    'Start tracking to make your first contribution',
                    style: theme.textTheme.bodySmall?.copyWith(
                      color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                    ),
                  ),
                ],
              ),
            ),
            Icon(
              Icons.arrow_downward,
              color: AppColors.primary,
              size: 20,
            ),
          ],
        ),
      );
    }

    // Milestone detection
    final bool isFirstUpload = _stats.totalUploads == 1;
    final bool isMilestone = _stats.totalUploads > 0 && (_stats.totalUploads % 10 == 0 || _stats.totalUploads % 50 == 0 || _stats.totalUploads % 100 == 0);
    final bool hasStreak = _stats.currentStreak >= 3;

    return AnimatedBuilder(
      animation: _pulseAnimation,
      builder: (context, child) {
        return Opacity(
          opacity: _pulseAnimation.value,
          child: child,
        );
      },
      child: Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: BoxDecoration(
        color: isDark ? AppColors.darkSurface : AppColors.lightSurface,
        borderRadius: BorderRadius.circular(12),
        boxShadow: isDark
            ? AppColors.elevationDark(active: false)
            : AppColors.elevationLight(active: false),
      ),
      child: Row(
        children: [
          // Icon - simple, no animation
          Container(
            padding: const EdgeInsets.all(AppTheme.spaceXs),
            decoration: BoxDecoration(
              color: (isMilestone || isFirstUpload)
                  ? AppColors.primaryAlpha(0.2)
                  : AppColors.primaryAlpha(0.1),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Icon(
              (isMilestone || isFirstUpload) ? Icons.celebration : Icons.eco,
              color: AppColors.primary,
              size: 20,
            ),
          ),
          const SizedBox(width: AppTheme.spaceMd),

          // Stats in horizontal row
          Expanded(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _buildCompactStat(
                  theme,
                  isDark,
                  label: 'Total',
                  value: '${_stats.totalUploads}',
                ),
                _buildDivider(isDark),
                _buildCompactStat(
                  theme,
                  isDark,
                  label: 'Today',
                  value: '${_stats.uploadsToday}',
                ),
                if (_stats.currentStreak > 0) ...[
                  _buildDivider(isDark),
                  _buildCompactStat(
                    theme,
                    isDark,
                    label: 'Streak',
                    value: '${_stats.currentStreak}d',
                    icon: Icons.local_fire_department,
                    iconColor: hasStreak ? AppColors.warning : (isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary),
                  ),
                ],
              ],
            ),
          ),
        ],
      ),
    ),
    );
  }

  Widget _buildDivider(bool isDark) {
    return Container(
      width: 1,
      height: 24,
      color: isDark ? AppColors.darkDivider : AppColors.lightDivider,
    );
  }

  Widget _buildCompactStat(
    ThemeData theme,
    bool isDark, {
    required String label,
    required String value,
    IconData? icon,
    Color? iconColor,
  }) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        if (icon != null) Icon(icon, size: 16, color: iconColor ?? AppColors.primary),
        Text(
          value,
          style: theme.textTheme.titleMedium?.copyWith(
            fontWeight: FontWeight.bold,
            color: isDark ? AppColors.darkTextPrimary : AppColors.lightTextPrimary,
            height: 1.2,
          ),
        ),
        const SizedBox(height: 2),
        Text(
          label,
          style: theme.textTheme.bodySmall?.copyWith(
            color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
            fontSize: 11,
          ),
        ),
      ],
    );
  }

  Widget _buildSkeletonStat(bool isDark) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        Container(
          width: 32,
          height: 18,
          decoration: BoxDecoration(
            color: isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurfaceElevated,
            borderRadius: BorderRadius.circular(4),
          ),
        ),
        const SizedBox(height: 2),
        Container(
          width: 28,
          height: 12,
          decoration: BoxDecoration(
            color: isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurfaceElevated,
            borderRadius: BorderRadius.circular(4),
          ),
        ),
      ],
    );
  }
}
