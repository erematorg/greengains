import 'dart:async';
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/themes.dart';
import '../core/app_preferences.dart';
import '../core/app_colors.dart';
import '../services/location/foreground_location_service.dart';
import '../widgets/sensor_data_card.dart';
import '../widgets/contribution_stats_card.dart';

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
  Timer? _uploadStatusTimer;
  LocationData? _currentLocation;
  LightData? _currentLight;
  AccelerometerData? _currentAccelerometer;
  GyroscopeData? _currentGyroscope;

  @override
  void initState() {
    super.initState();
    _checkServiceStatus();
    _setupSensorListeners();
    _setupUploadSuccessListener();

    // Update upload status every 30 seconds to keep time-ago text accurate
    _uploadStatusTimer = Timer.periodic(const Duration(seconds: 30), (_) {
      if (mounted) setState(() {});
    });
  }

  void _setupSensorListeners() {
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

  void _setupUploadSuccessListener() {
    final uploader = _locationService.uploader;
    if (uploader != null) {
      uploader.uploadSuccess.addListener(_onUploadSuccess);
    }
  }

  void _onUploadSuccess() {
    if (!mounted) return;

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Row(
          children: [
            const Icon(Icons.check_circle, color: Colors.white),
            const SizedBox(width: 12),
            const Text('Contribution uploaded successfully!'),
          ],
        ),
        backgroundColor: AppColors.success,
        behavior: SnackBarBehavior.floating,
        duration: const Duration(seconds: 2),
      ),
    );
  }

  @override
  void dispose() {
    _uploadStatusTimer?.cancel();
    _locationSubscription?.cancel();
    _lightSubscription?.cancel();
    _accelerometerSubscription?.cancel();
    _gyroscopeSubscription?.cancel();

    // Clean up upload success listener
    final uploader = _locationService.uploader;
    uploader?.uploadSuccess.removeListener(_onUploadSuccess);

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
    final isDark = theme.brightness == Brightness.dark;

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
          Padding(
            padding: const EdgeInsets.only(bottom: AppTheme.spaceSm),
            child: Text(
              'Sensors',
              style: theme.textTheme.titleMedium?.copyWith(
                fontWeight: FontWeight.bold,
                color: isDark ? AppColors.darkTextPrimary : AppColors.lightTextSecondary,
              ),
            ),
          ),

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

          const SizedBox(height: AppTheme.spaceMd),

          // Contribution Statistics
          const ContributionStatsCard(),

          const SizedBox(height: AppTheme.spaceMd),

          // Service Control Button
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Column(
                children: [
                  Container(
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(12),
                      gradient: LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        colors: isRunning ? AppColors.gradientRed : AppColors.gradientGreen,
                      ),
                      boxShadow: AppColors.buttonShadow(isDark),
                    ),
                    child: FilledButton.icon(
                      onPressed: _toggleService,
                      icon: Icon(isRunning ? Icons.stop : Icons.play_arrow),
                      label: Text(isRunning ? 'Stop Tracking' : 'Start Tracking'),
                      style: FilledButton.styleFrom(
                        backgroundColor: Colors.transparent,
                        shadowColor: Colors.transparent,
                        minimumSize: const Size.fromHeight(48),
                      ),
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

        final isDark = theme.brightness == Brightness.dark;
        return Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.cloud_done,
              size: 14,
              color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
            ),
            const SizedBox(width: 4),
            Text(
              'Last upload: $timeAgo',
              style: theme.textTheme.bodySmall?.copyWith(
                color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
              ),
            ),
          ],
        );
      },
    );
  }
}
