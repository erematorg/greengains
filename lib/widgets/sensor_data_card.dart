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
    final isActive = enabled && value != null;

    return Card(
      color: isActive
          ? theme.colorScheme.primaryContainer.withValues(alpha: 0.3)
          : null,
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        child: Row(
          children: [
            // Icon
            Container(
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: isActive
                    ? theme.colorScheme.primary.withValues(alpha: 0.1)
                    : theme.colorScheme.surfaceContainerHighest,
                borderRadius: BorderRadius.circular(12),
              ),
              child: Icon(
                icon,
                size: 24,
                color: isActive
                    ? theme.colorScheme.primary
                    : theme.colorScheme.outline,
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
            Icon(
              isActive ? Icons.circle : Icons.circle_outlined,
              size: 12,
              color: isActive ? Colors.green : theme.colorScheme.outline,
            ),
          ],
        ),
      ),
    );
  }
}
