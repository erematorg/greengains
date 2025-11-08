import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/themes.dart';
import '../core/app_preferences.dart';
import '../services/location/foreground_location_service.dart';

/// Home screen showing live sensor data and tracking status
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  static const _fgChannel = MethodChannel('greengains/foreground');
  final _locationService = ForegroundLocationService.instance;
  final _prefs = AppPreferences.instance;
  StreamSubscription<LocationData>? _locationSubscription;
  StreamSubscription<LightData>? _lightSubscription;
  LocationData? _currentLocation;
  LightData? _currentLight;

  @override
  void initState() {
    super.initState();
    _checkServiceStatus();
    _locationSubscription = _locationService.locationStream.listen((location) {
      setState(() {
        _currentLocation = location;
      });
    });
    _lightSubscription = _locationService.lightStream.listen((light) {
      setState(() {
        _currentLight = light;
      });
    });
  }

  @override
  void dispose() {
    _locationSubscription?.cancel();
    _lightSubscription?.cancel();
    super.dispose();
  }

  Future<void> _checkServiceStatus() async {
    await _locationService.isServiceRunning();
  }

  String _getLightDescription(double lux) {
    if (lux < 10) return 'Dark';
    if (lux < 50) return 'Dim';
    if (lux < 500) return 'Normal';
    if (lux < 10000) return 'Bright';
    return 'Very Bright';
  }

  Future<void> _toggleService() async {
    final isRunning = _locationService.isRunning.value;
    if (isRunning) {
      await _locationService.stop();
      setState(() {
        _currentLocation = null;
        _currentLight = null;
      });
    } else {
      // Request location permission before starting service
      // (only if Share Location is enabled)
      if (_prefs.shareLocation) {
        try {
          await _fgChannel.invokeMethod('requestLocationPermission');
        } catch (e) {
          // Permission request failed, but continue to start service
          // (service will skip location if permission not granted)
        }
      }
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
        actions: [
          // Quick status indicator in app bar
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Padding(
                padding: const EdgeInsets.only(right: 16),
                child: Icon(
                  isRunning ? Icons.sensors : Icons.sensors_off,
                  color: isRunning ? Colors.green : theme.colorScheme.outline,
                ),
              );
            },
          ),
        ],
      ),
      body: ListView(
        padding: AppTheme.pagePadding,
        children: [
          // User greeting (compact)
          if (user != null) ...[
            Text(
              'Hello, ${user.displayName?.split(' ').first ?? 'User'}!',
              style: theme.textTheme.headlineSmall,
            ),
            const SizedBox(height: AppTheme.spaceSm),
          ],

          // Sensor Data Section
          Text(
            'Live Sensor Data',
            style: theme.textTheme.titleLarge?.copyWith(fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: AppTheme.spaceMd),

          // Light Sensor Card
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return _SensorDataCard(
                icon: Icons.light_mode,
                title: 'Light',
                value: _currentLight != null
                    ? '${_currentLight!.lux.toStringAsFixed(0)} lux'
                    : null,
                unit: _currentLight != null ? _getLightDescription(_currentLight!.lux) : 'lux',
                enabled: isRunning,
              );
            },
          ),
          const SizedBox(height: AppTheme.spaceSm),

          // Motion Sensors Card
          _SensorDataCard(
            icon: Icons.motion_photos_on,
            title: 'Motion',
            value: null, // Will be populated later
            unit: 'm/s²',
            enabled: false,
          ),
          const SizedBox(height: AppTheme.spaceSm),

          // Location Card
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              final shareLocationEnabled = _prefs.shareLocation;
              return _SensorDataCard(
                icon: Icons.location_on,
                title: 'Location',
                value: _currentLocation != null
                    ? '${_currentLocation!.latitude.toStringAsFixed(4)}, ${_currentLocation!.longitude.toStringAsFixed(4)}'
                    : null,
                unit: _currentLocation != null ? '±${_currentLocation!.accuracy.toStringAsFixed(0)}m' : 'GPS',
                enabled: isRunning && shareLocationEnabled,
              );
            },
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Service Control Button
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
                  minimumSize: const Size.fromHeight(48),
                ),
              );
            },
          ),

        ],
      ),
    );
  }
}

/// Sensor data display card with live values
class _SensorDataCard extends StatelessWidget {
  final IconData icon;
  final String title;
  final String? value;
  final String unit;
  final bool enabled;

  const _SensorDataCard({
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
      color: isActive
          ? theme.colorScheme.primaryContainer.withValues(alpha: 0.3)
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
                    ? theme.colorScheme.primary.withValues(alpha: 0.1)
                    : theme.colorScheme.surfaceContainerHighest,
                borderRadius: BorderRadius.circular(12),
              ),
              child: Icon(
                icon,
                size: 24,
                color: isActive
                    ? theme.colorScheme.primary
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
                    style: theme.textTheme.titleMedium?.copyWith(
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  const SizedBox(height: 4),
                  Text(
                    enabled ? (value ?? 'Waiting...') : 'Disabled',
                    style: theme.textTheme.bodySmall?.copyWith(
                      color: theme.colorScheme.onSurface.withValues(alpha: 0.6),
                    ),
                  ),
                ],
              ),
            ),

            // Value display
            if (isActive) ...[
              Container(
                padding: const EdgeInsets.symmetric(
                  horizontal: 12,
                  vertical: 6,
                ),
                decoration: BoxDecoration(
                  color: theme.colorScheme.primary.withValues(alpha: 0.1),
                  borderRadius: BorderRadius.circular(8),
                ),
                child: Text(
                  unit,
                  style: theme.textTheme.labelMedium?.copyWith(
                    color: theme.colorScheme.primary,
                    fontWeight: FontWeight.w600,
                  ),
                ),
              ),
            ] else ...[
              Icon(
                Icons.circle_outlined,
                size: 20,
                color: theme.colorScheme.outline,
              ),
            ],
          ],
        ),
      ),
    );
  }
}

