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

    return Container(
      margin: const EdgeInsets.only(bottom: AppTheme.spaceSm),
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: BoxDecoration(
        color: isActive
            ? const Color(0xFFF1F8F4) // Subtle green tint (hsl(140, 30%, 96%))
            : const Color(0xFFFAFAFA), // Neutral surface
        borderRadius: BorderRadius.circular(12),
        boxShadow: [
          // Inner light shadow (top)
          BoxShadow(
            color: Colors.white.withOpacity(0.8),
            offset: const Offset(0, -1),
            blurRadius: 1,
            spreadRadius: 0,
          ),
          // Outer darker shadow (bottom)
          BoxShadow(
            color: Colors.black.withOpacity(isActive ? 0.12 : 0.06),
            offset: const Offset(0, 2),
            blurRadius: isActive ? 10 : 6,
            spreadRadius: 0,
          ),
        ],
      ),
      child: Row(
          children: [
            // Icon
            Container(
              padding: const EdgeInsets.all(12),
              decoration: BoxDecoration(
                color: isActive
                    ? const Color.fromRGBO(76, 175, 80, 0.15) // Primary green with alpha
                    : const Color(0xFFF0F0F0), // Neutral elevated
                borderRadius: BorderRadius.circular(12),
              ),
              child: Icon(
                icon,
                size: 24,
                color: isActive
                    ? const Color(0xFF4CAF50) // Primary green
                    : Colors.grey.shade500,
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
                color: isActive ? const Color(0xFF4CAF50) : Colors.grey.shade400,
                boxShadow: isActive
                    ? [
                        BoxShadow(
                          color: const Color(0xFF4CAF50).withOpacity(0.4),
                          blurRadius: 4,
                          spreadRadius: 1,
                        )
                      ]
                    : null,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
