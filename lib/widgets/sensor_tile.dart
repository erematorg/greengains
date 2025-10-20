import 'package:flutter/material.dart';

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
    final textTheme = Theme.of(context).textTheme;
    final onSurface = Theme.of(context).colorScheme.onSurface;
    final display = value != null ? value!.toStringAsFixed(1) : 'N/A';
    final suffix = unit != null ? ' $unit' : '';

    return ListTile(
      title: Text(title),
      subtitle: Text(
        '$display$suffix',
        style: textTheme.headlineSmall
            ?.copyWith(color: onSurface, fontWeight: FontWeight.w600),
      ),
    );
  }
}
