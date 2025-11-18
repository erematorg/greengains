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
  final String statusLabel;
  final DateTime? updatedAt;

  const SensorDataCard({
    super.key,
    required this.icon,
    required this.title,
    required this.value,
    required this.unit,
    required this.enabled,
    required this.statusLabel,
    this.updatedAt,
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
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
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
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Expanded(
                          child: Text(
                            title,
                            style: theme.textTheme.titleSmall?.copyWith(
                              fontWeight: FontWeight.w600,
                            ),
                          ),
                        ),
                        const SizedBox(width: AppTheme.spaceSm),
                        _StatusBadge(
                          label: statusLabel,
                          active: isActive,
                        ),
                      ],
                    ),
                    const SizedBox(height: AppTheme.spaceXxs),
                    Text(
                      value ?? 'Waiting for data',
                      style: theme.textTheme.bodyMedium?.copyWith(
                        color: isActive
                            ? theme.colorScheme.onSurface
                            : theme.colorScheme.outline,
                        fontWeight:
                            isActive ? FontWeight.w600 : FontWeight.normal,
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
            ],
          ),
          const SizedBox(height: AppTheme.spaceXxs),
          Text(
            _formatUpdatedAt(updatedAt) ?? 'Awaiting first sample',
            style: theme.textTheme.labelSmall?.copyWith(
              color: AppColors.textTertiary(isDark),
            ),
          ),
        ],
      ),
    );
  }

  String? _formatUpdatedAt(DateTime? timestamp) {
    if (timestamp == null) return null;
    final diff = DateTime.now().difference(timestamp);
    if (diff.inSeconds < 15) return 'Updated just now';
    if (diff.inMinutes < 1) return 'Updated ${diff.inSeconds}s ago';
    if (diff.inHours < 1) return 'Updated ${diff.inMinutes}m ago';
    if (diff.inHours < 24) return 'Updated ${diff.inHours}h ago';
    return 'Updated ${diff.inDays}d ago';
  }
}

class _StatusBadge extends StatelessWidget {
  const _StatusBadge({required this.label, required this.active});

  final String label;
  final bool active;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;
    return Container(
      padding: const EdgeInsets.symmetric(
        horizontal: AppTheme.spaceSm,
        vertical: AppTheme.spaceXxs,
      ),
      decoration: BoxDecoration(
        color: active
            ? AppColors.primaryAlpha(0.15)
            : AppColors.border(isDark),
        borderRadius: BorderRadius.circular(AppTheme.radiusSm),
      ),
      child: Text(
        label,
        style: theme.textTheme.labelSmall?.copyWith(
          color: active ? AppColors.primary : theme.colorScheme.outline,
          fontWeight: FontWeight.w600,
        ),
      ),
    );
  }
}
