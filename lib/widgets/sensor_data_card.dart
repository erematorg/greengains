import 'package:flutter/material.dart';
import '../core/themes.dart';

/// Reusable sensor data display card with live values
/// Shows icon, title, current value, and unit
/// Optimized for fast rendering and instant updates
class SensorDataCard extends StatelessWidget {
  final IconData icon;
  final String title;
  final String? value;
  final String unit;
  final bool enabled;

  const SensorDataCard({
    super.key,
    required this.icon,
    required this.title,
    required this.value,
    required this.unit,
    required this.enabled,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;
    final isActive = enabled && value != null;

    return Container(
      margin: const EdgeInsets.only(bottom: AppTheme.spaceSm),
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(
        isDark: isDark,
        active: isActive,
      ),
      child: Row(
        children: [
          // Icon - no animation for instant render
          Container(
            padding: const EdgeInsets.all(AppTheme.spaceSm),
            decoration: AppTheme.iconContainer(
              isDark: isDark,
              active: isActive,
            ),
            child: Icon(
              icon,
              size: 22,
              color: isActive
                  ? AppColors.primary
                  : AppColors.textTertiary(isDark),
            ),
          ),
          const SizedBox(width: AppTheme.spaceMd),

          // Title and status - instant updates
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: theme.textTheme.titleSmall?.copyWith(
                    fontWeight: FontWeight.w600,
                  ),
                ),
                const SizedBox(height: AppTheme.spaceXxs),
                Text(
                  value ?? 'Waiting...',
                  style: theme.textTheme.bodyMedium?.copyWith(
                    color: isActive
                        ? theme.colorScheme.onSurface
                        : theme.colorScheme.outline,
                    fontWeight: isActive ? FontWeight.w600 : FontWeight.normal,
                  ),
                ),
                Text(
                  unit,
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.colorScheme.outline,
                    fontSize: 12,
                  ),
                ),
              ],
            ),
          ),

          // Status indicator - simple, no pulse animation
          Container(
            width: 8,
            height: 8,
            decoration: BoxDecoration(
              shape: BoxShape.circle,
              color: isActive
                  ? AppColors.primary
                  : AppColors.border(isDark),
            ),
          ),
        ],
      ),
    );
  }
}
