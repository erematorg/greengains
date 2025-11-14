import 'package:flutter/material.dart';
import '../core/themes.dart';

/// Reusable sensor data display card with live values
/// Shows icon, title, current value, and unit
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
      decoration: BoxDecoration(
        color: isActive
            ? (isDark ? AppColors.darkSurfaceActive : AppColors.lightSurfaceActive)
            : (isDark ? AppColors.darkSurface : AppColors.lightSurface),
        borderRadius: BorderRadius.circular(12),
        boxShadow: isDark
            ? AppColors.elevationDark(active: isActive)
            : AppColors.elevationLight(active: isActive),
      ),
      child: Row(
        children: [
          // Icon
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: isActive
                  ? AppColors.primaryAlpha(0.15)
                  : (isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurfaceElevated),
              borderRadius: BorderRadius.circular(12),
            ),
            child: Icon(
              icon,
              size: 24,
              color: isActive
                  ? AppColors.primary
                  : (isDark ? AppColors.darkTextTertiary : AppColors.lightTextTertiary),
            ),
          ),
          const SizedBox(width: AppTheme.spaceMd),

          // Title and status
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: theme.textTheme.titleMedium,
                ),
                const SizedBox(height: 4),
                Text(
                  value ?? 'Waiting...',
                  style: theme.textTheme.bodyLarge?.copyWith(
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
                  ),
                ),
              ],
            ),
          ),

          // Status indicator
          Container(
            width: 10,
            height: 10,
            decoration: BoxDecoration(
              shape: BoxShape.circle,
              color: isActive
                  ? AppColors.primary
                  : (isDark ? AppColors.darkBorder : AppColors.lightBorder),
              boxShadow: isActive ? AppColors.glowEffect(AppColors.primary) : null,
            ),
          ),
        ],
      ),
    );
  }
}
