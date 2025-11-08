import 'dart:async';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/themes.dart';
import '../services/location/foreground_location_service.dart';

/// Home screen showing sensor status and contribution info
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final _locationService = ForegroundLocationService.instance;
  StreamSubscription<LocationData>? _locationSubscription;
  LocationData? _currentLocation;

  @override
  void initState() {
    super.initState();
    _checkServiceStatus();
    _locationSubscription = _locationService.locationStream.listen((location) {
      setState(() {
        _currentLocation = location;
      });
    });
  }

  @override
  void dispose() {
    _locationSubscription?.cancel();
    super.dispose();
  }

  Future<void> _checkServiceStatus() async {
    await _locationService.isServiceRunning();
  }

  Future<void> _toggleService() async {
    final isRunning = _locationService.isRunning.value;
    if (isRunning) {
      // Stop the service
      await _locationService.stop();
      setState(() {
        _currentLocation = null;
      });
    } else {
      // Start the foreground service
      // Permission request handled by MainActivity (their pattern)
      await _locationService.start();
    }
  }

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

          // Test Button
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.stretch,
                children: [
                  Text(
                    'Location Service Test',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  Text(
                    'Test the foreground service under various scenarios: background, screen lock, app switching',
                    style: theme.textTheme.bodyMedium,
                  ),
                  const SizedBox(height: AppTheme.spaceMd),
                  ListenableBuilder(
                    listenable: _locationService.isRunning,
                    builder: (context, _) {
                      final isRunning = _locationService.isRunning.value;
                      return FilledButton.icon(
                        onPressed: _toggleService,
                        icon: Icon(isRunning ? Icons.stop : Icons.play_arrow),
                        label: Text(isRunning ? 'Stop Tracking' : 'Start Tracking'),
                        style: FilledButton.styleFrom(
                          backgroundColor: isRunning ? Colors.red : theme.colorScheme.primary,
                        ),
                      );
                    },
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Location Display
          if (_currentLocation != null)
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Icon(Icons.location_on, color: theme.colorScheme.primary),
                        const SizedBox(width: AppTheme.spaceXs),
                        Text(
                          'Live Location',
                          style: theme.textTheme.titleLarge,
                        ),
                      ],
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    _LocationItem(
                      label: 'Latitude',
                      value: _currentLocation!.latitude.toStringAsFixed(6),
                    ),
                    const SizedBox(height: AppTheme.spaceXs),
                    _LocationItem(
                      label: 'Longitude',
                      value: _currentLocation!.longitude.toStringAsFixed(6),
                    ),
                    const SizedBox(height: AppTheme.spaceXs),
                    _LocationItem(
                      label: 'Accuracy',
                      value: '±${_currentLocation!.accuracy.toStringAsFixed(1)}m',
                    ),
                    if (_currentLocation!.altitude != null) ...[
                      const SizedBox(height: AppTheme.spaceXs),
                      _LocationItem(
                        label: 'Altitude',
                        value: '${_currentLocation!.altitude!.toStringAsFixed(1)}m',
                      ),
                    ],
                    if (_currentLocation!.speed != null) ...[
                      const SizedBox(height: AppTheme.spaceXs),
                      _LocationItem(
                        label: 'Speed',
                        value: '${_currentLocation!.speed!.toStringAsFixed(1)} m/s',
                      ),
                    ],
                    const SizedBox(height: AppTheme.spaceXs),
                    _LocationItem(
                      label: 'Provider',
                      value: _currentLocation!.provider ?? 'unknown',
                    ),
                    const SizedBox(height: AppTheme.spaceXs),
                    _LocationItem(
                      label: 'Time',
                      value: _formatTime(_currentLocation!.dateTime),
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
                  ListenableBuilder(
                    listenable: _locationService.isRunning,
                    builder: (context, _) {
                      final isRunning = _locationService.isRunning.value;
                      return _StatusItem(
                        icon: isRunning ? Icons.location_on : Icons.location_off,
                        label: 'Location Tracking',
                        value: isRunning ? 'Active' : 'Stopped',
                        color: isRunning ? Colors.green : Colors.grey,
                      );
                    },
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
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              return _SensorCard(
                icon: Icons.location_on,
                title: 'Location',
                subtitle: 'GPS for heatmap generation',
                enabled: _locationService.isRunning.value,
              );
            },
          ),
        ],
      ),
    );
  }

  String _formatTime(DateTime dateTime) {
    final now = DateTime.now();
    final diff = now.difference(dateTime);

    if (diff.inSeconds < 60) {
      return '${diff.inSeconds}s ago';
    } else if (diff.inMinutes < 60) {
      return '${diff.inMinutes}m ago';
    } else {
      return '${dateTime.hour.toString().padLeft(2, '0')}:${dateTime.minute.toString().padLeft(2, '0')}';
    }
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

class _LocationItem extends StatelessWidget {
  final String label;
  final String value;

  const _LocationItem({
    required this.label,
    required this.value,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(
          label,
          style: theme.textTheme.bodyMedium?.copyWith(
            color: theme.colorScheme.onSurface.withValues(alpha: 0.7),
          ),
        ),
        Text(
          value,
          style: theme.textTheme.bodyMedium?.copyWith(
            fontWeight: FontWeight.w600,
            fontFeatures: [const FontFeature.tabularFigures()],
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
