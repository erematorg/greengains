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
      elevation: isActive ? 3 : 1,
      color: isActive
          ? Colors.green.shade50
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
                    ? Colors.green.shade100
                    : theme.colorScheme.surfaceContainerHighest,
                borderRadius: BorderRadius.circular(12),
              ),
              child: Icon(
                icon,
                size: 24,
                color: isActive
                    ? Colors.green.shade700
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
            Container(
              width: 12,
              height: 12,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: isActive ? Colors.green.shade600 : Colors.transparent,
                border: Border.all(
                  color: isActive ? Colors.green.shade600 : theme.colorScheme.outline,
                  width: 2,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
