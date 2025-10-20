import 'package:flutter/material.dart';

class GyroscopeTile extends StatelessWidget {
  final List<double>? values; // [x, y, z] or null

  const GyroscopeTile({super.key, required this.values});

  @override
  Widget build(BuildContext context) {
    final textTheme = Theme.of(context).textTheme;
    final onSurface = Theme.of(context).colorScheme.onSurface;
    String text;
    if (values == null || values!.length < 3) {
      text = 'N/A';
    } else {
      final x = values![0].toStringAsFixed(2);
      final y = values![1].toStringAsFixed(2);
      final z = values![2].toStringAsFixed(2);
      text = 'X: $x | Y: $y | Z: $z';
    }

    return ListTile(
      title: const Text('Gyroscope'),
      subtitle: Text(text,
          style: textTheme.titleMedium
              ?.copyWith(color: onSurface, fontWeight: FontWeight.w600)),
    );
  }
}
