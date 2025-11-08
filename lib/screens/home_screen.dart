import 'dart:async';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/themes.dart';
import '../core/app_preferences.dart';
import '../services/location/foreground_location_service.dart';
import '../widgets/sensor_data_card.dart';

/// Home screen showing live sensor data and tracking status
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final _locationService = ForegroundLocationService.instance;
  final _prefs = AppPreferences.instance;
  StreamSubscription<LocationData>? _locationSubscription;
  StreamSubscription<LightData>? _lightSubscription;
  StreamSubscription<AccelerometerData>? _accelerometerSubscription;
  StreamSubscription<GyroscopeData>? _gyroscopeSubscription;
  LocationData? _currentLocation;
  LightData? _currentLight;
  AccelerometerData? _currentAccelerometer;
  GyroscopeData? _currentGyroscope;

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
    _accelerometerSubscription = _locationService.accelerometerStream.listen((accel) {
      setState(() {
        _currentAccelerometer = accel;
      });
    });
    _gyroscopeSubscription = _locationService.gyroscopeStream.listen((gyro) {
      setState(() {
        _currentGyroscope = gyro;
      });
    });
  }

  @override
  void dispose() {
    _locationSubscription?.cancel();
    _lightSubscription?.cancel();
    _accelerometerSubscription?.cancel();
    _gyroscopeSubscription?.cancel();
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
        _currentAccelerometer = null;
        _currentGyroscope = null;
      });
    } else {
      // Just start the service - no permission requests here
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

          // All sensor cards in one ListenableBuilder to reduce duplication
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Column(
                children: [
                  // Light Sensor
                  SensorDataCard(
                    icon: Icons.light_mode,
                    title: 'Light',
                    value: _currentLight != null
                        ? '${_currentLight!.lux.toStringAsFixed(0)} lux'
                        : null,
                    unit: _currentLight != null ? _getLightDescription(_currentLight!.lux) : 'lux',
                    enabled: isRunning,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),

                  // Accelerometer
                  SensorDataCard(
                    icon: Icons.vibration,
                    title: 'Accelerometer',
                    value: _currentAccelerometer != null
                        ? '${_currentAccelerometer!.magnitude.toStringAsFixed(1)} m/s²'
                        : null,
                    unit: _currentAccelerometer != null
                        ? '(${_currentAccelerometer!.x.toStringAsFixed(1)}, ${_currentAccelerometer!.y.toStringAsFixed(1)}, ${_currentAccelerometer!.z.toStringAsFixed(1)})'
                        : 'm/s²',
                    enabled: isRunning,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),

                  // Gyroscope
                  SensorDataCard(
                    icon: Icons.rotate_90_degrees_ccw,
                    title: 'Gyroscope',
                    value: _currentGyroscope != null
                        ? '${_currentGyroscope!.magnitude.toStringAsFixed(2)} rad/s'
                        : null,
                    unit: _currentGyroscope != null
                        ? '(${_currentGyroscope!.x.toStringAsFixed(2)}, ${_currentGyroscope!.y.toStringAsFixed(2)}, ${_currentGyroscope!.z.toStringAsFixed(2)})'
                        : 'rad/s',
                    enabled: isRunning,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),

                  // Location
                  SensorDataCard(
                    icon: Icons.location_on,
                    title: 'Location',
                    value: _currentLocation != null
                        ? '${_currentLocation!.latitude.toStringAsFixed(4)}, ${_currentLocation!.longitude.toStringAsFixed(4)}'
                        : null,
                    unit: _currentLocation != null ? '±${_currentLocation!.accuracy.toStringAsFixed(0)}m' : 'GPS',
                    enabled: isRunning && _prefs.shareLocation,
                  ),
                ],
              );
            },
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Service Control Button
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Column(
                children: [
                  FilledButton.icon(
                    onPressed: _toggleService,
                    icon: Icon(isRunning ? Icons.stop : Icons.play_arrow),
                    label: Text(isRunning ? 'Stop Tracking' : 'Start Tracking'),
                    style: FilledButton.styleFrom(
                      backgroundColor: isRunning ? Colors.red : theme.colorScheme.primary,
                      minimumSize: const Size.fromHeight(48),
                    ),
                  ),

                  // Last Upload Status
                  const SizedBox(height: AppTheme.spaceSm),
                  _buildUploadStatus(theme),
                ],
              );
            },
          ),

        ],
      ),
    );
  }

  Widget _buildUploadStatus(ThemeData theme) {
    final uploader = _locationService.uploader;
    if (uploader == null) {
      return Text(
        'Backend: Not started',
        style: theme.textTheme.bodySmall?.copyWith(
          color: theme.colorScheme.outline,
        ),
      );
    }

    return ValueListenableBuilder<DateTime?>(
      valueListenable: uploader.lastUpload,
      builder: (context, lastUpload, _) {
        if (lastUpload == null) {
          return ValueListenableBuilder<bool>(
            valueListenable: uploader.uploading,
            builder: (context, uploading, _) {
              return Text(
                uploading ? 'Uploading...' : 'Backend: Waiting for data',
                style: theme.textTheme.bodySmall?.copyWith(
                  color: theme.colorScheme.outline,
                ),
              );
            },
          );
        }

        final now = DateTime.now();
        final diff = now.difference(lastUpload);
        final timeAgo = diff.inMinutes < 1
            ? 'just now'
            : diff.inMinutes < 60
                ? '${diff.inMinutes}m ago'
                : diff.inHours < 24
                    ? '${diff.inHours}h ago'
                    : '${diff.inDays}d ago';

        return Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.cloud_upload,
              size: 14,
              color: theme.colorScheme.primary,
            ),
            const SizedBox(width: 4),
            Text(
              'Last upload: $timeAgo',
              style: theme.textTheme.bodySmall?.copyWith(
                color: theme.colorScheme.primary,
              ),
            ),
          ],
        );
      },
    );
  }
}
