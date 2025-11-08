import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/themes.dart';

/// Home screen showing sensor status and contribution info
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('GreenGains'),
      ),
      body: ListView(
        padding: AppTheme.pagePadding,
        children: [
          // User info card
          if (user != null)
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Row(
                  children: [
                    CircleAvatar(
                      radius: 24,
                      backgroundImage: user.photoURL != null
                          ? NetworkImage(user.photoURL!)
                          : null,
                      child: user.photoURL == null
                          ? const Icon(Icons.person)
                          : null,
                    ),
                    const SizedBox(width: AppTheme.spaceMd),
                    Expanded(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            user.displayName ?? 'User',
                            style: theme.textTheme.titleMedium,
                          ),
                          Text(
                            user.email ?? '',
                            style: theme.textTheme.bodySmall,
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),

          const SizedBox(height: AppTheme.spaceLg),

          // Status card
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Status',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceMd),
                  _StatusItem(
                    icon: Icons.check_circle_outline,
                    label: 'Backend Services',
                    value: 'Ready',
                    color: Colors.green,
                  ),
                  const SizedBox(height: AppTheme.spaceXs),
                  _StatusItem(
                    icon: Icons.sensors_off,
                    label: 'Sensors',
                    value: 'Not configured',
                    color: Colors.orange,
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Sensor placeholders
          Text(
            'Sensors (Coming Soon)',
            style: theme.textTheme.titleLarge,
          ),
          const SizedBox(height: AppTheme.spaceSm),
          _SensorCard(
            icon: Icons.light_mode,
            title: 'Light Sensor',
            subtitle: 'Ambient light detection',
            enabled: false,
          ),
          const SizedBox(height: AppTheme.spaceSm),
          _SensorCard(
            icon: Icons.motion_photos_on,
            title: 'Motion Sensors',
            subtitle: 'Accelerometer & gyroscope',
            enabled: false,
          ),
          const SizedBox(height: AppTheme.spaceSm),
          _SensorCard(
            icon: Icons.location_on,
            title: 'Location',
            subtitle: 'GPS for heatmap generation',
            enabled: false,
          ),
        ],
      ),
    );
  }
}

class _StatusItem extends StatelessWidget {
  final IconData icon;
  final String label;
  final String value;
  final Color color;

  const _StatusItem({
    required this.icon,
    required this.label,
    required this.value,
    required this.color,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Row(
      children: [
        Icon(icon, size: 20, color: color),
        const SizedBox(width: AppTheme.spaceXs),
        Text(label, style: theme.textTheme.bodyMedium),
        const Spacer(),
        Text(
          value,
          style: theme.textTheme.bodyMedium?.copyWith(
            color: color,
            fontWeight: FontWeight.w600,
          ),
        ),
      ],
    );
  }
}

class _SensorCard extends StatelessWidget {
  final IconData icon;
  final String title;
  final String subtitle;
  final bool enabled;

  const _SensorCard({
    required this.icon,
    required this.title,
    required this.subtitle,
    required this.enabled,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Card(
      child: ListTile(
        leading: Icon(
          icon,
          size: 32,
          color: enabled ? theme.colorScheme.primary : theme.colorScheme.outline,
        ),
        title: Text(title),
        subtitle: Text(subtitle),
        trailing: enabled
            ? Icon(Icons.check_circle, color: theme.colorScheme.primary)
            : Icon(Icons.circle_outlined, color: theme.colorScheme.outline),
      ),
    );
  }
}
