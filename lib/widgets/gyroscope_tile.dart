import 'package:flutter/material.dart';
import '../core/themes.dart';

class GyroscopeTile extends StatelessWidget {
  final List<double>? values; // [x, y, z] or null

  const GyroscopeTile({super.key, required this.values});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isAvailable = values != null && values!.length >= 3;

    return Padding(
      padding: const EdgeInsets.all(AppTheme.spaceSm),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(
                Icons.rotate_right_outlined,
                size: 20,
                color: theme.colorScheme.primary,
              ),
              const SizedBox(width: AppTheme.spaceXs),
              Text(
                'Gyroscope',
                style: theme.textTheme.titleSmall?.copyWith(
                  color: theme.colorScheme.onSurfaceVariant,
                ),
              ),
              const SizedBox(width: AppTheme.spaceXs),
              Text(
                'rad/s',
                style: theme.textTheme.bodySmall?.copyWith(
                  color: theme.colorScheme.onSurfaceVariant,
                ),
              ),
            ],
          ),
          const SizedBox(height: AppTheme.spaceXs),
          if (isAvailable)
            Row(
              children: [
                Expanded(child: _AxisValue(label: 'X', value: values![0])),
                const SizedBox(width: AppTheme.spaceXs),
                Expanded(child: _AxisValue(label: 'Y', value: values![1])),
                const SizedBox(width: AppTheme.spaceXs),
                Expanded(child: _AxisValue(label: 'Z', value: values![2])),
              ],
            )
          else
            Text(
              'N/A',
              style: theme.textTheme.titleMedium?.copyWith(
                color: theme.colorScheme.onSurfaceVariant,
              ),
            ),
        ],
      ),
    );
  }
}

class _AxisValue extends StatelessWidget {
  final String label;
  final double value;

  const _AxisValue({required this.label, required this.value});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          label,
          style: theme.textTheme.labelSmall?.copyWith(
            color: theme.colorScheme.onSurfaceVariant,
          ),
        ),
        Text(
          value.toStringAsFixed(2),
          style: theme.textTheme.titleMedium?.copyWith(
            color: theme.colorScheme.onSurface,
            fontWeight: FontWeight.w600,
          ),
        ),
      ],
    );
  }
}
