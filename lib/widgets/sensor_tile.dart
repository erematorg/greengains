import 'package:flutter/material.dart';
import '../core/themes.dart';

class SensorTile extends StatelessWidget {
  final String title;
  final String? unit;
  final double? value;

  const SensorTile({
    super.key,
    required this.title,
    required this.value,
    this.unit,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final display = value != null ? value!.toStringAsFixed(1) : 'N/A';
    final suffix = unit != null ? ' $unit' : '';

    return Padding(
      padding: const EdgeInsets.all(AppTheme.spaceSm),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(
                Icons.light_mode_outlined,
                size: 20,
                color: theme.colorScheme.primary,
              ),
              const SizedBox(width: AppTheme.spaceXs),
              Text(
                title,
                style: theme.textTheme.titleSmall?.copyWith(
                  color: theme.colorScheme.onSurfaceVariant,
                ),
              ),
            ],
          ),
          const SizedBox(height: 4),
          Text(
            '$display$suffix',
            style: theme.textTheme.headlineMedium?.copyWith(
              color: theme.colorScheme.onSurface,
            ),
          ),
        ],
      ),
    );
  }
}
