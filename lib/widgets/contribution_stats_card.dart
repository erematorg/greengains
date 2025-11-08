import 'package:flutter/material.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';
import '../core/themes.dart';

/// Compact horizontal contribution stats bar
class ContributionStatsCard extends StatefulWidget {
  const ContributionStatsCard({super.key});

  @override
  State<ContributionStatsCard> createState() => _ContributionStatsCardState();
}

class _ContributionStatsCardState extends State<ContributionStatsCard> {
  final _repository = ContributionRepository();
  ContributionStats _stats = ContributionStats.empty;
  bool _loading = true;

  @override
  void initState() {
    super.initState();
    _loadStats();
  }

  Future<void> _loadStats() async {
    setState(() => _loading = true);
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
        setState(() => _loading = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    if (_loading) {
      return Container(
        padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 16),
        child: Center(
          child: SizedBox(
            width: 16,
            height: 16,
            child: CircularProgressIndicator(
              strokeWidth: 2,
              color: AppColors.primary,
            ),
          ),
        ),
      );
    }

    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: isDark ? AppColors.darkSurface : AppColors.lightSurface,
        borderRadius: BorderRadius.circular(12),
        boxShadow: isDark
            ? AppColors.elevationDark(active: false)
            : AppColors.elevationLight(active: false),
      ),
      child: Row(
        children: [
          // Icon
          Container(
            padding: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              color: AppColors.primaryAlpha(0.1),
              borderRadius: BorderRadius.circular(8),
            ),
            child: Icon(
              Icons.eco,
              color: AppColors.primary,
              size: 20,
            ),
          ),
          const SizedBox(width: 16),

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
                    iconColor: AppColors.warning,
                  ),
                ],
              ],
            ),
          ),

          // Refresh button
          const SizedBox(width: 8),
          IconButton(
            icon: const Icon(Icons.refresh, size: 18),
            onPressed: _loadStats,
            color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
            padding: EdgeInsets.zero,
            constraints: const BoxConstraints(
              minWidth: 32,
              minHeight: 32,
            ),
          ),
        ],
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
        if (icon != null) Icon(icon, size: 14, color: iconColor ?? AppColors.primary),
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
}
