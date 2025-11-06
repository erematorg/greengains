import 'dart:async';
import 'dart:developer' as developer;

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:geolocator/geolocator.dart';

import '../core/app_preferences.dart';
import '../core/themes.dart';
import '../providers/sensor_provider.dart';
import '../services/location/location_service.dart';
import '../services/network/sensor_uploader.dart';
import '../services/sensors/sensor_manager.dart';
import '../services/system/foreground_service.dart';
import '../services/system/service_state_controller.dart';
import '../widgets/accelerometer_tile.dart';
import '../widgets/contribution_card.dart';
import '../widgets/gyroscope_tile.dart';
import '../widgets/magnetometer_tile.dart';
import '../widgets/map_placeholder.dart';
import '../widgets/section_title.dart';
import '../widgets/sensor_tile.dart';
import '../widgets/status_card.dart';
import 'profile_screen.dart';
import 'settings_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final SensorProvider _provider;
  late final SensorUploader _uploader;
  bool _isCollecting = false;
  bool _isUploading = false;

  @override
  void initState() {
    super.initState();
    _provider = SensorProvider();
    _provider.start();

    _uploader = SensorUploader(compressPayload: true);
    _uploader.uploading.addListener(_onUploadStatusChanged);
    unawaited(_uploader.start());

    _isCollecting = ServiceStateController.instance.isRunning;
    ServiceStateController.instance.addListener(_onServiceStateChanged);
  }

  @override
  void dispose() {
    ServiceStateController.instance.removeListener(_onServiceStateChanged);
    _uploader.uploading.removeListener(_onUploadStatusChanged);
    _provider.dispose();
    unawaited(_uploader.dispose());
    super.dispose();
  }

  void _onServiceStateChanged() {
    if (!mounted) return;
    setState(() {
      _isCollecting = ServiceStateController.instance.isRunning;
    });
  }

  void _onUploadStatusChanged() {
    if (!mounted) return;
    setState(() {
      _isUploading = _uploader.uploading.value;
    });
  }

  Future<void> _handleToggle() async {
    HapticFeedback.selectionClick();
    if (!mounted) return;

    final messenger = ScaffoldMessenger.of(context);

    try {
      if (_isCollecting) {
        await _uploader.stop();
        final stopped = await ForegroundService.stop();
        await SensorManager.instance.stop();

        if (!stopped) {
          messenger.showSnackBar(
            const SnackBar(
              content: Text(
                'Could not stop background collection. Check system settings.',
              ),
            ),
          );
          await ServiceStateController.instance.refresh();
          return;
        }

        ServiceStateController.instance.setRunning(false);
      } else {
        final started = await ForegroundService.start();
        if (!started) {
          messenger.showSnackBar(
            const SnackBar(
              content: Text(
                'Could not start background collection. Check battery optimisation settings.',
              ),
            ),
          );
          await ServiceStateController.instance.refresh();
          return;
        }

        await SensorManager.instance.start();
        await _uploader.start();
        ServiceStateController.instance.setRunning(true);
      }
    } catch (error, stackTrace) {
      developer.log(
        'Failed to toggle collection',
        name: 'HomeScreen',
        error: error,
        stackTrace: stackTrace,
      );

      messenger.showSnackBar(
        const SnackBar(
          content: Text('Something went wrong while toggling collection.'),
        ),
      );

      if (!_isCollecting) {
        await ForegroundService.stop();
      }

      await ServiceStateController.instance.refresh();
    }
  }

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;
    final shareLocation = AppPreferences.instance.shareLocation;

    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        titleSpacing: AppTheme.spaceSm,
        title: Row(
          children: [
            GestureDetector(
              onTap: () => Navigator.of(context).push(
                MaterialPageRoute(builder: (_) => const ProfileScreen()),
              ),
              child: _buildAvatar(user),
            ),
            const Spacer(),
          ],
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.settings_outlined),
            tooltip: 'Settings',
            onPressed: () => Navigator.of(context).push(
              MaterialPageRoute(builder: (_) => const SettingsScreen()),
            ),
          ),
        ],
      ),
      body: SafeArea(
        child: ListView(
          padding: const EdgeInsets.symmetric(
            vertical: AppTheme.spaceXs,
          ),
          children: [
            // Today's contribution card
            const Padding(
              padding: EdgeInsets.symmetric(
                horizontal: AppTheme.spaceSm,
              ),
              child: ContributionCard(),
            ),
            const SizedBox(height: AppTheme.spaceSm),
            // Status card
            Padding(
              padding: const EdgeInsets.symmetric(
                horizontal: AppTheme.spaceSm,
              ),
              child: StatusCard(
                isCollecting: _isCollecting,
                isUploading: _isUploading,
                onToggle: _handleToggle,
              ),
            ),
            // Only rebuild this part when sensors update
            AnimatedBuilder(
              animation: _provider,
              builder: (context, _) {
                final hasLiveData = _provider.hasLiveData;
                if (!hasLiveData) {
                  return Column(
                    children: [
                      const SizedBox(height: AppTheme.spaceSm),
                      Padding(
                        padding: const EdgeInsets.symmetric(
                          horizontal: AppTheme.spaceSm,
                        ),
                        child: _buildSensorHintCard(
                          context,
                          isCollecting: _isCollecting,
                        ),
                      ),
                    ],
                  );
                }
                return const SizedBox.shrink();
              },
            ),
            const SizedBox(height: AppTheme.spaceSm),
            Padding(
              padding: const EdgeInsets.symmetric(
                horizontal: AppTheme.spaceSm,
              ),
              child: _buildTelemetryInsights(),
            ),
            const SizedBox(height: AppTheme.spaceSm),
            // Map placeholder
            const Padding(
              padding: EdgeInsets.symmetric(
                horizontal: AppTheme.spaceSm,
              ),
              child: MapPlaceholder(),
            ),
            const SizedBox(height: AppTheme.spaceSm),
            if (shareLocation) ...[
              const SizedBox(height: AppTheme.spaceSm),
              Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: AppTheme.spaceSm,
                ),
                child: _buildLocationReminder(),
              ),
            ],
            const SizedBox(height: AppTheme.spaceSm),
            // Sensor expansion - only this needs to rebuild on sensor updates
            AnimatedBuilder(
              animation: _provider,
              builder: (context, _) => Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: AppTheme.spaceSm,
                ),
                child: _buildSensorExpansion(context),
              ),
            ),
            const SizedBox(height: AppTheme.spaceLg),
          ],
        ),
      ),
    );
  }

  Widget _buildAvatar(User? user) {
    final theme = Theme.of(context);
    final photo = user?.photoURL;
    if (photo != null && photo.isNotEmpty) {
      return CircleAvatar(
        radius: 18,
        backgroundImage: NetworkImage(photo),
      );
    }
    return CircleAvatar(
      radius: 18,
      backgroundColor: theme.colorScheme.primaryContainer,
      child: Icon(
        Icons.person_outline,
        color: theme.colorScheme.onPrimaryContainer,
      ),
    );
  }

  Widget _buildSensorHintCard(
    BuildContext context, {
    required bool isCollecting,
  }) {
    final theme = Theme.of(context);
    final title =
        isCollecting ? 'Warming up sensors' : 'Ready for the first readings';
    final message = isCollecting
        ? 'Give it a few seconds while we capture the first sample set. Live values appear as soon as the device streams data.'
        : 'Tap "Collecting data" to start or review permissions if the tiles stay blank.';
    final ctaLabel = isCollecting ? 'Open settings' : 'Review permissions';

    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceSm),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Icon(Icons.insights_outlined, color: theme.colorScheme.primary),
            const SizedBox(width: AppTheme.spaceSm),
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
                  const SizedBox(height: AppTheme.spaceXs),
                  Text(
                    message,
                    style: theme.textTheme.bodyMedium?.copyWith(
                      color: theme.colorScheme.onSurfaceVariant,
                    ),
                  ),
                  const SizedBox(height: AppTheme.spaceXs),
                  Align(
                    alignment: Alignment.centerLeft,
                    child: TextButton(
                      onPressed: () => Navigator.of(context).push(
                        MaterialPageRoute(
                          builder: (_) => const SettingsScreen(),
                        ),
                      ),
                      child: Text(ctaLabel),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildTelemetryInsights() {
    return ValueListenableBuilder<DateTime?>(
      valueListenable: _uploader.lastUpload,
      builder: (context, lastUpload, _) {
        return ValueListenableBuilder<ConnectivityResult>(
          valueListenable: _uploader.connectivityStatus,
          builder: (context, connectivity, __) {
            return Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceSm),
                child: Row(
                  children: [
                    Expanded(
                      child: _buildInsightTile(
                        context,
                        icon: Icons.cloud_done_outlined,
                        label: 'Last upload',
                        value: _formatRelativeTime(lastUpload),
                      ),
                    ),
                    const SizedBox(width: AppTheme.spaceSm),
                    Expanded(
                      child: _buildInsightTile(
                        context,
                        icon: _connectionIcon(connectivity),
                        label: 'Network',
                        value: _connectionLabel(connectivity),
                        valueColor: _connectionColor(
                          Theme.of(context),
                          connectivity,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            );
          },
        );
      },
    );
  }

  Widget _buildInsightTile(
    BuildContext context, {
    required IconData icon,
    required String label,
    required String value,
    Color? valueColor,
  }) {
    final theme = Theme.of(context);
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Container(
          width: 36,
          height: 36,
          decoration: BoxDecoration(
            color: theme.colorScheme.primaryContainer,
            borderRadius: BorderRadius.circular(12),
          ),
          child: Icon(
            icon,
            size: 20,
            color: theme.colorScheme.onPrimaryContainer,
          ),
        ),
        const SizedBox(width: AppTheme.spaceSm),
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                label,
                style: theme.textTheme.labelMedium?.copyWith(
                  color: theme.colorScheme.onSurfaceVariant,
                ),
              ),
              const SizedBox(height: 4),
              Text(
                value,
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.w600,
                  color: valueColor ?? theme.colorScheme.onSurface,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }

  Widget _buildLocationReminder() {
    return FutureBuilder<LocationPermission>(
      future: LocationService.instance.checkPermission(),
      builder: (context, snapshot) {
        if (!snapshot.hasData) {
          if (snapshot.connectionState == ConnectionState.waiting) {
            return const SizedBox.shrink();
          }
        }

        final permission = snapshot.data ?? LocationPermission.denied;
        if (permission == LocationPermission.always ||
            permission == LocationPermission.whileInUse) {
          return const SizedBox.shrink();
        }

        final theme = Theme.of(context);
        final isForever = permission == LocationPermission.deniedForever;
        final description = isForever
            ? 'Location access was denied permanently. Enable it in system settings to include precise GPS in uploads.'
            : 'Allow precise location so batches include street-level context for city analytics.';

        return Card(
          child: Padding(
            padding: const EdgeInsets.all(AppTheme.spaceSm),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(
                      Icons.location_off_outlined,
                      color: theme.colorScheme.error,
                    ),
                    const SizedBox(width: AppTheme.spaceXs),
                    Text(
                      'Location permission needed',
                      style: theme.textTheme.titleSmall?.copyWith(
                        fontWeight: FontWeight.w600,
                        color: theme.colorScheme.error,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: AppTheme.spaceXs),
                Text(
                  description,
                  style: theme.textTheme.bodyMedium?.copyWith(
                    color: theme.colorScheme.onSurfaceVariant,
                  ),
                ),
                const SizedBox(height: AppTheme.spaceXs),
                Align(
                  alignment: Alignment.centerLeft,
                  child: FilledButton.tonal(
                    onPressed: () =>
                        _handleLocationPermissionRequest(isForever),
                    child: Text(isForever ? 'Open settings' : 'Grant access'),
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }

  Future<void> _handleLocationPermissionRequest(bool openSettings) async {
    if (openSettings) {
      await LocationService.instance.openAppSettings();
    } else {
      await LocationService.instance.requestLocation();
    }
    if (mounted) {
      setState(() {});
    }
  }

  String _formatRelativeTime(DateTime? timestamp) {
    if (timestamp == null) {
      return 'No uploads yet';
    }
    final now = DateTime.now();
    final diff = now.difference(timestamp);
    if (diff.inSeconds < 45) {
      return 'Just now';
    }
    if (diff.inMinutes < 1) {
      return '${diff.inSeconds}s ago';
    }
    if (diff.inMinutes < 60) {
      return '${diff.inMinutes}m ago';
    }
    if (diff.inHours < 24) {
      return '${diff.inHours}h ago';
    }
    return '${diff.inDays}d ago';
  }

  IconData _connectionIcon(ConnectivityResult result) {
    switch (result) {
      case ConnectivityResult.wifi:
        return Icons.wifi;
      case ConnectivityResult.ethernet:
        return Icons.settings_ethernet;
      case ConnectivityResult.mobile:
        return Icons.signal_cellular_alt;
      case ConnectivityResult.bluetooth:
        return Icons.bluetooth;
      case ConnectivityResult.vpn:
        return Icons.lock;
      default:
        return Icons.signal_wifi_connected_no_internet_4;
    }
  }

  String _connectionLabel(ConnectivityResult result) {
    switch (result) {
      case ConnectivityResult.wifi:
        return 'Wi-Fi connected';
      case ConnectivityResult.ethernet:
        return 'Ethernet connected';
      case ConnectivityResult.mobile:
        return 'Mobile data';
      case ConnectivityResult.bluetooth:
        return 'Bluetooth network';
      case ConnectivityResult.vpn:
        return 'VPN active';
      case ConnectivityResult.none:
        return 'Offline';
      default:
        return 'Unknown network';
    }
  }

  Color _connectionColor(ThemeData theme, ConnectivityResult result) {
    if (result == ConnectivityResult.none) {
      return theme.colorScheme.error;
    }
    return theme.colorScheme.onSurface;
  }

  Widget _buildSensorExpansion(BuildContext context) {
    final theme = Theme.of(context);
    return Card(
      child: Theme(
        data: theme.copyWith(dividerColor: Colors.transparent),
        child: ExpansionTile(
          initiallyExpanded: false,
          iconColor: theme.colorScheme.onSurfaceVariant,
          collapsedIconColor: theme.colorScheme.onSurfaceVariant,
          textColor: theme.colorScheme.onSurface,
          collapsedTextColor: theme.colorScheme.onSurface,
          title: Text(
            'Live sensor readings',
            style: theme.textTheme.titleMedium?.copyWith(
              fontWeight: FontWeight.w600,
              color: theme.colorScheme.onSurface,
            ),
          ),
          subtitle: Text(
            'Use these values to verify the device is streaming correctly.',
            style: theme.textTheme.bodySmall?.copyWith(
              color: theme.colorScheme.onSurfaceVariant,
            ),
          ),
          childrenPadding:
              const EdgeInsets.symmetric(horizontal: AppTheme.spaceSm),
          children: [
            const SectionTitle('Environment'),
            Card(
              child: SensorTile(
                title: 'Light',
                unit: 'lux',
                value: _provider.ambientLux,
                icon: Icons.wb_sunny_outlined,
              ),
            ),
            const SizedBox(height: AppTheme.spaceXs),
            const SectionTitle('Motion'),
            Card(
              child: AccelerometerTile(values: _provider.accelerometer),
            ),
            Card(child: GyroscopeTile(values: _provider.gyroscope)),
            Card(child: MagnetometerTile(values: _provider.magnetometer)),
            const SizedBox(height: AppTheme.spaceSm),
          ],
        ),
      ),
    );
  }
}
