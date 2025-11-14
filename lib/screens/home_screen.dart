import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/extensions/context_extensions.dart';
import '../core/themes.dart';
import '../services/location/foreground_location_service.dart';
import '../utils/app_snackbars.dart';
import '../widgets/contribution_stats_card.dart';
import '../widgets/sensor_data_card.dart';

/// Home screen showing live sensor data and tracking status
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final _locationService = ForegroundLocationService.instance;
  StreamSubscription<LightData>? _lightSubscription;
  StreamSubscription<AccelerometerData>? _accelerometerSubscription;
  StreamSubscription<GyroscopeData>? _gyroscopeSubscription;
  Timer? _uploadStatusTimer;
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
    _locationService.uploadStatus.uploadSuccess.addListener(_onUploadSuccess);
  }

  void _onUploadSuccess() {
    if (!mounted) return;

    AppSnackbars.showSuccess(context, 'Contribution uploaded successfully!');
  }

  @override
  void dispose() {
    _uploadStatusTimer?.cancel();
    _lightSubscription?.cancel();
    _accelerometerSubscription?.cancel();
    _gyroscopeSubscription?.cancel();

    // Clean up upload success listener
    _locationService.uploadStatus.uploadSuccess.removeListener(_onUploadSuccess);

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
        _currentLight = null;
        _currentAccelerometer = null;
        _currentGyroscope = null;
      });
    } else {
      HapticFeedback.mediumImpact();
      // Just start the service - no permission requests here
      await _locationService.start();
    }
  }

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;
    final theme = context.theme;
    final isDark = context.isDarkMode;
    final isRunningNow = _locationService.isRunning.value;
    final overlayStyle = isRunningNow
        ? SystemUiOverlayStyle.light.copyWith(statusBarColor: Colors.transparent)
        : SystemUiOverlayStyle.dark.copyWith(statusBarColor: Colors.transparent);

    return AnnotatedRegion<SystemUiOverlayStyle>(
      value: overlayStyle,
      child: Scaffold(
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

          // Contribution Statistics (moved to top)
          const ContributionStatsCard(),
          const SizedBox(height: AppTheme.spaceLg),

          // Collapsible Sensor Details
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Card(
                child: ExpansionTile(
                  title: Text(
                    'Live sensor readings',
                    style: theme.textTheme.titleMedium?.copyWith(
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  subtitle: Text(
                    'Use these values to verify the device is streaming correctly',
                    style: theme.textTheme.bodySmall,
                  ),
                  children: [
                    Padding(
                      padding: const EdgeInsets.fromLTRB(16, 0, 16, 16),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          // Environment Section
                          Text(
                            'Environment',
                            style: theme.textTheme.labelLarge?.copyWith(
                              color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                            ),
                          ),
                          const SizedBox(height: 8),

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

                          const SizedBox(height: 16),

                          // Motion Section
                          Text(
                            'Motion',
                            style: theme.textTheme.labelLarge?.copyWith(
                              color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                            ),
                          ),
                          const SizedBox(height: 8),

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

                          // Location tracking in background (no UI card)
                        ],
                      ),
                    ),
                  ],
                ),
              );
            },
          ),

          const SizedBox(height: AppTheme.spaceMd),

          // Service Control Button
          ListenableBuilder(
            listenable: _locationService.isRunning,
            builder: (context, _) {
              final isRunning = _locationService.isRunning.value;
              return Column(
                children: [
                  AnimatedScale(
                    scale: isRunning ? 1.02 : 1.0,
                    duration: const Duration(milliseconds: 250),
                    curve: Curves.easeOut,
                    child: Container(
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
    ),
  );
  }

  Widget _buildUploadStatus(ThemeData theme) {
    final status = _locationService.uploadStatus;
    final isServiceRunning = _locationService.isRunning.value;

    if (!isServiceRunning) {
      return Text(
        'Background service stopped',
        style: theme.textTheme.bodySmall?.copyWith(
          color: theme.colorScheme.outline,
        ),
      );
    }

    return ValueListenableBuilder<DateTime?>(
      valueListenable: status.lastUpload,
      builder: (context, lastUpload, _) {
        if (lastUpload == null) {
          return ValueListenableBuilder<bool>(
            valueListenable: status.uploading,
            builder: (context, uploading, _) {
              return Text(
                uploading ? 'Uploading…' : 'Backend: Waiting for data',
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
