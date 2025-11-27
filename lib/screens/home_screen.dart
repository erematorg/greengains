import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/extensions/context_extensions.dart';
import '../core/themes.dart';
import '../services/location/foreground_location_service.dart';
import '../utils/app_snackbars.dart';
import '../core/app_preferences.dart';
import '../widgets/contribution_stats_card.dart';
import '../widgets/contextual_tip_card.dart';
import '../widgets/sensor_data_card.dart';
import '../widgets/service_control_button.dart';
import '../widgets/time_ago_text.dart';

/// Home screen showing live sensor data and tracking status
/// Optimized for fast performance with minimal animations
class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> with WidgetsBindingObserver {
  final _locationService = ForegroundLocationService.instance;
  final _statsKey = GlobalKey<ContributionStatsCardState>();
  final _prefs = AppPreferences.instance;
  final Set<String> _dismissedTips = {};

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance.addObserver(this);
    _checkServiceStatus();
    _setupUploadSuccessListener();
    _loadDismissedTips();
  }

  void _loadDismissedTips() {
    // Load which tips have been dismissed
    setState(() {
      if (_prefs.isTipDismissed('expand_sensors')) {
        _dismissedTips.add('expand_sensors');
      }
    });
  }

  Future<void> _dismissTip(String tipId) async {
    await _prefs.dismissTip(tipId);
    setState(() {
      _dismissedTips.add(tipId);
    });
  }

  bool _shouldShowTip(String tipId) {
    return !_dismissedTips.contains(tipId);
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
    WidgetsBinding.instance.removeObserver(this);
    // Clean up upload success listener
    _locationService.uploadStatus.uploadSuccess.removeListener(_onUploadSuccess);

    super.dispose();
  }

  @override
  void didChangeAppLifecycleState(AppLifecycleState state) {
    if (state == AppLifecycleState.resumed) {
      // Sync service status with native
      _checkServiceStatus();
      // Reload last upload time from preferences
      _reloadUploadStatus();
      // Auto-refresh stats when user returns to app
      _statsKey.currentState?.refresh();
    }
  }

  Future<void> _reloadUploadStatus() async {
    await _prefs.ensureInitialized();
    final lastUpload = _prefs.lastUploadAt;
    if (lastUpload != null) {
      _locationService.uploadStatus.lastUpload.value = lastUpload;
    }
  }

  Future<void> _checkServiceStatus() async {
    await _locationService.isServiceRunning();
  }

  Future<void> _refreshData() async {
    HapticFeedback.lightImpact();
    // Refresh contribution stats
    await _statsKey.currentState?.refresh();
    // Min duration for better UX feedback
    await Future.delayed(const Duration(milliseconds: 300));
  }

  String _getLightDescription(double lux) {
    if (lux < 10) return 'Dark';
    if (lux < 50) return 'Dim';
    if (lux < 500) return 'Normal';
    if (lux < 10000) return 'Bright';
    return 'Very Bright';
  }

  String _sensorStatus({
    required bool isRunning,
    required bool hasData,
  }) {
    if (!isRunning) return 'Paused';
    if (!hasData) return 'Waiting';
    return 'Live';
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
        appBar: AppBar(),
        body: ListView(
          padding: const EdgeInsets.symmetric(horizontal: AppTheme.spaceMd),
          children: [
            const SizedBox(height: AppTheme.spaceSm),
            ListenableBuilder(
              listenable: _locationService.isRunning,
              builder: (context, _) => _buildUploadStatus(theme),
            ),

            const SizedBox(height: AppTheme.spaceMd),
            const ServiceControlButton(),
            const SizedBox(height: AppTheme.spaceLg),

            // Contribution Statistics
            ContributionStatsCard(key: _statsKey),
            const SizedBox(height: AppTheme.spaceMd),

            // Contextual Tips
            if (_locationService.isRunning.value && _shouldShowTip('expand_sensors'))
              ContextualTipCard(
                tipId: 'expand_sensors',
                icon: Icons.expand_more,
                title: 'View live data',
                message: 'Expand the sensor section below to verify data is streaming correctly',
                onDismiss: () => _dismissTip('expand_sensors'),
              ),

            // Coverage Map Placeholder
            _buildMapPlaceholder(theme, isDark),
            const SizedBox(height: AppTheme.spaceMd),

            // Collapsible Sensor Details
            ListenableBuilder(
              listenable: _locationService.isRunning,
              builder: (context, _) {
                final isRunning = _locationService.isRunning.value;

                return Card(
                  child: ExpansionTile(
                    onExpansionChanged: (expanded) {
                      if (expanded) {
                        HapticFeedback.lightImpact();
                      }
                    },
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
                      if (!isRunning)
                        Padding(
                          padding: const EdgeInsets.all(AppTheme.spaceMd),
                          child: Column(
                            children: [
                              Container(
                                padding: const EdgeInsets.all(AppTheme.spaceMd),
                                decoration: AppTheme.surfaceContainer(
                                  isDark: isDark,
                                  border: Border.all(
                                    color: AppColors.primary.withValues(alpha: 0.3),
                                    width: 1.5,
                                  ),
                                ),
                                child: Column(
                                  children: [
                                    Icon(
                                      Icons.sensors_off,
                                      size: 48,
                                      color: AppColors.textSecondary(isDark),
                                    ),
                                    const SizedBox(height: AppTheme.spaceSm),
                                    Text(
                                      'Sensors inactive',
                                      style: theme.textTheme.titleMedium?.copyWith(
                                        fontWeight: FontWeight.w600,
                                      ),
                                    ),
                                    const SizedBox(height: AppTheme.spaceXs),
                                    Text(
                                      'Start tracking above to begin collecting data',
                                      style: theme.textTheme.bodySmall?.copyWith(
                                        color: AppColors.textSecondary(isDark),
                                      ),
                                      textAlign: TextAlign.center,
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                        )
                      else
                        Padding(
                          padding: const EdgeInsets.fromLTRB(AppTheme.spaceMd, 0, AppTheme.spaceMd, AppTheme.spaceMd),
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              // Environment Section
                              Text(
                                'Environment',
                                style: theme.textTheme.labelLarge?.copyWith(
                                  color: AppColors.textSecondary(isDark),
                                ),
                              ),
                              const SizedBox(height: AppTheme.spaceXs),

                              // Light Sensor
                              StreamBuilder<LightData>(
                                stream: _locationService.lightStream,
                                builder: (context, snapshot) {
                                  final light = snapshot.data;
                                  final isRunning = _locationService.isRunning.value;
                                  return SensorDataCard(
                                    icon: Icons.light_mode,
                                    title: 'Light',
                                    value: light != null
                                        ? '${light.lux.toStringAsFixed(0)} lux'
                                        : null,
                                    unit: light != null ? _getLightDescription(light.lux) : 'lux',
                                    enabled: isRunning,
                                    statusLabel: _sensorStatus(
                                      isRunning: isRunning,
                                      hasData: light != null,
                                    ),
                                    updatedAt: light?.dateTime,
                                  );
                                },
                              ),

                              const SizedBox(height: AppTheme.spaceMd),

                              // Motion Section
                              Text(
                                'Motion',
                                style: theme.textTheme.labelLarge?.copyWith(
                                  color: AppColors.textSecondary(isDark),
                                ),
                              ),
                              const SizedBox(height: AppTheme.spaceXs),

                              // Accelerometer
                              StreamBuilder<AccelerometerData>(
                                stream: _locationService.accelerometerStream,
                                builder: (context, snapshot) {
                                  final accel = snapshot.data;
                                  final isRunning = _locationService.isRunning.value;
                                  return SensorDataCard(
                                    icon: Icons.vibration,
                                    title: 'Accelerometer',
                                    value: accel != null
                                        ? '${accel.magnitude.toStringAsFixed(1)} m/s²'
                                        : null,
                                    unit: accel != null
                                        ? '(${accel.x.toStringAsFixed(1)}, ${accel.y.toStringAsFixed(1)}, ${accel.z.toStringAsFixed(1)})'
                                        : 'm/s²',
                                    enabled: isRunning,
                                    statusLabel: _sensorStatus(
                                      isRunning: isRunning,
                                      hasData: accel != null,
                                    ),
                                    updatedAt: accel?.dateTime,
                                  );
                                },
                              ),

                              // Gyroscope
                              StreamBuilder<GyroscopeData>(
                                stream: _locationService.gyroscopeStream,
                                builder: (context, snapshot) {
                                  final gyro = snapshot.data;
                                  final isRunning = _locationService.isRunning.value;
                                  return SensorDataCard(
                                    icon: Icons.rotate_90_degrees_ccw,
                                    title: 'Gyroscope',
                                    value: gyro != null
                                        ? '${gyro.magnitude.toStringAsFixed(2)} rad/s'
                                        : null,
                                    unit: gyro != null
                                        ? '(${gyro.x.toStringAsFixed(2)}, ${gyro.y.toStringAsFixed(2)}, ${gyro.z.toStringAsFixed(2)})'
                                        : 'rad/s',
                                    enabled: isRunning,
                                    statusLabel: _sensorStatus(
                                      isRunning: isRunning,
                                      hasData: gyro != null,
                                    ),
                                    updatedAt: gyro?.dateTime,
                                  );
                                },
                              ),

                              // Barometer
                              StreamBuilder<PressureData>(
                                stream: _locationService.pressureStream,
                                builder: (context, snapshot) {
                                  final data = snapshot.data;
                                  return SensorDataCard(
                                    icon: Icons.compress,
                                    title: 'Barometer',
                                    value: data != null
                                        ? '${data.hPa.toStringAsFixed(1)} hPa'
                                        : null,
                                    unit: 'Atmospheric Pressure',
                                    enabled: isRunning,
                                    statusLabel: _sensorStatus(
                                      isRunning: isRunning,
                                      hasData: data != null,
                                    ),
                                    updatedAt: data?.dateTime,
                                  );
                                },
                              ),
                            ],
                          ),
                        ),
                    ],
                  ),
                );
              },
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildMapPlaceholder(ThemeData theme, bool isDark) {
    return Container(
      padding: const EdgeInsets.all(AppTheme.spaceMd),
      decoration: AppTheme.surfaceContainer(isDark: isDark),
      child: Row(
        children: [
          Container(
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
              color: AppColors.primaryAlpha(0.12),
              borderRadius: BorderRadius.circular(10),
            ),
            child: Icon(
              Icons.map,
              color: AppColors.primary,
              size: 24,
            ),
          ),
          const SizedBox(width: AppTheme.spaceMd),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  'Your Coverage',
                  style: theme.textTheme.titleSmall?.copyWith(
                    fontWeight: FontWeight.w600,
                  ),
                ),
                const SizedBox(height: 2),
                Text(
                  'Map visualization coming soon',
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: AppColors.textSecondary(isDark),
                  ),
                ),
              ],
            ),
          ),
          Icon(
            Icons.location_on,
            color: AppColors.textTertiary(isDark),
            size: 20,
          ),
        ],
      ),
    );
  }

  Widget _buildUploadStatus(ThemeData theme) {
    final status = _locationService.uploadStatus;
    final isServiceRunning = _locationService.isRunning.value;
    final isDark = theme.brightness == Brightness.dark;

    if (!isServiceRunning) {
      return Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(
            Icons.sensors_off,
            size: 16,
            color: theme.colorScheme.outline,
          ),
          const SizedBox(width: 6),
          Text(
            'Tracking paused',
            style: theme.textTheme.bodySmall?.copyWith(
              color: theme.colorScheme.outline,
            ),
          ),
        ],
      );
    }

    // Check for errors first (highest priority)
    return ValueListenableBuilder<String?>(
      valueListenable: status.lastError,
      builder: (context, lastError, _) {
        if (lastError != null) {
          return Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(
                Icons.cloud_off,
                size: 16,
                color: theme.colorScheme.error,
              ),
              const SizedBox(width: 6),
              Flexible(
                child: Text(
                  'Upload failed: $lastError',
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: theme.colorScheme.error,
                  ),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 1,
                ),
              ),
            ],
          );
        }

        // No error, check upload status
        return ValueListenableBuilder<DateTime?>(
          valueListenable: status.lastUpload,
          builder: (context, lastUpload, _) {
            if (lastUpload == null) {
              return ValueListenableBuilder<bool>(
                valueListenable: status.uploading,
                builder: (context, uploading, _) {
                  return Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      if (uploading) ...[
                        SizedBox(
                          width: 14,
                          height: 14,
                          child: CircularProgressIndicator(
                            strokeWidth: 2,
                            valueColor: AlwaysStoppedAnimation(
                              AppColors.textSecondary(isDark),
                            ),
                          ),
                        ),
                        const SizedBox(width: 6),
                      ] else ...[
                        Icon(
                          Icons.cloud_queue,
                          size: 16,
                          color: AppColors.textSecondary(isDark),
                        ),
                        const SizedBox(width: 6),
                      ],
                      Text(
                        uploading ? 'Uploading data...' : 'Waiting for data...',
                        style: theme.textTheme.bodySmall?.copyWith(
                          color: AppColors.textSecondary(isDark),
                        ),
                      ),
                    ],
                  );
                },
              );
            }

            return Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(
                  Icons.cloud_done,
                  size: 16,
                  color: AppColors.success,
                ),
                const SizedBox(width: 6),
                TimeAgoText(
                  timestamp: lastUpload,
                  prefix: 'Last upload: ',
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: AppColors.textSecondary(isDark),
                  ),
                ),
              ],
            );
          },
        );
      },
    );
  }
}
