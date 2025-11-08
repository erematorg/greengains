import 'package:flutter/material.dart';
import '../core/themes.dart';

/// Reusable settings section card with title and content
/// Reduces boilerplate for consistent card layout across settings
class SettingsSection extends StatelessWidget {
  final String title;
  final List<Widget> children;

  const SettingsSection({
    super.key,
    required this.title,
    required this.children,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              title,
              style: theme.textTheme.titleLarge,
            ),
            const SizedBox(height: AppTheme.spaceSm),
            ...children,
          ],
        ),
      ),
    );
  }
}
