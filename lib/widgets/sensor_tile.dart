import 'package:flutter/material.dart';
import '../core/themes.dart';

class SensorTile extends StatelessWidget {
  final String title;
  final String? unit;
  final double? value;
  final IconData icon;

  const SensorTile({
    super.key,
    required this.title,
    required this.value,
    this.unit,
    this.icon = Icons.light_mode_outlined,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final hasValue = value != null;
    final secondaryColor = theme.colorScheme.onSurfaceVariant;

    return Padding(
      padding: const EdgeInsets.symmetric(
        horizontal: AppTheme.spaceSm,
        vertical: AppTheme.spaceXs,
      ),
      child: Row(
        children: [
          Icon(
            icon,
            size: 22,
            color: secondaryColor,
          ),
          const SizedBox(width: AppTheme.spaceXs),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  title,
                  style: theme.textTheme.titleSmall?.copyWith(
                    color: secondaryColor,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                AnimatedSwitcher(
                  duration: const Duration(milliseconds: 200),
                  child: hasValue
                      ? Text(
                          '${value!.toStringAsFixed(1)}${unit != null ? ' $unit' : ''}',
                          key: ValueKey('${title}_value'),
                          style: theme.textTheme.titleMedium?.copyWith(
                            color: theme.colorScheme.onSurface,
                            fontWeight: FontWeight.w600,
                          ),
                        )
                      : Text(
                          'Waiting for sensor data',
                          key: ValueKey('${title}_empty'),
                          style: theme.textTheme.bodyMedium?.copyWith(
                            color: secondaryColor,
                          ),
                        ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
