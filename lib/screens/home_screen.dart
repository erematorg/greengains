import 'package:flutter/material.dart';
import 'dart:async';

import '../providers/sensor_provider.dart';
import '../widgets/sensor_tile.dart';
import '../widgets/accelerometer_tile.dart';
import '../widgets/gyroscope_tile.dart';
import '../widgets/magnetometer_tile.dart';
import 'settings_screen.dart';
import '../core/themes.dart';
import '../services/network/sensor_uploader.dart';
import '../services/system/foreground_service.dart';
import '../services/system/service_state_controller.dart';

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

    // Listen to service state controller for real-time updates
    _isCollecting = ServiceStateController.instance.isRunning;
    ServiceStateController.instance.addListener(_onServiceStateChanged);
  }

  void _onUploadStatusChanged() {
    if (mounted) {
      setState(() => _isUploading = _uploader.uploading.value);
    }
  }

  void _onServiceStateChanged() {
    if (mounted) {
      setState(() => _isCollecting = ServiceStateController.instance.isRunning);
    }
  }

  @override
  void dispose() {
    ServiceStateController.instance.removeListener(_onServiceStateChanged);
    _uploader.uploading.removeListener(_onUploadStatusChanged);
    _provider.dispose();
    // Note: _uploader is a screen-local instance, but disposal is async.
    // Clean shutdown without awaiting to prevent blocking dispose().
    unawaited(_uploader.dispose());
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: null,
        actions: [
          if (Theme.of(context).platform == TargetPlatform.android ||
              Theme.of(context).platform == TargetPlatform.iOS)
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
        child: AnimatedBuilder(
          animation: _provider,
          builder: (context, _) {
            return ListView(
              padding: const EdgeInsets.symmetric(
                vertical: AppTheme.spaceXs,
                horizontal: AppTheme.spaceSm,
              ),
              children: [
                _StatusCard(isCollecting: _isCollecting, isUploading: _isUploading),
                const SizedBox(height: AppTheme.spaceMd),
                const _SectionTitle('Environment'),
                Card(
                  child: SensorTile(
                    title: 'Light',
                    unit: 'lux',
                    value: _provider.ambientLux,
                  ),
                ),
                const Divider(height: AppTheme.spaceMd),
                const _SectionTitle('Motion'),
                Card(child: AccelerometerTile(values: _provider.accelerometer)),
                Card(child: GyroscopeTile(values: _provider.gyroscope)),
                Card(child: MagnetometerTile(values: _provider.magnetometer)),
              ],
            );
          },
        ),
      ),
      // Note: Rewards system entry point
    );
  }
}

class _SectionTitle extends StatelessWidget {
  final String text;
  const _SectionTitle(this.text);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: AppTheme.spaceXs),
      child: Text(
        text,
        style: Theme.of(context)
            .textTheme
            .titleLarge
            ?.copyWith(fontWeight: FontWeight.w700),
      ),
    );
  }
}

class _StatusCard extends StatelessWidget {
  final bool isCollecting;
  final bool isUploading;
  const _StatusCard({required this.isCollecting, required this.isUploading});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final statusColor = isCollecting
        ? Colors.green
        : theme.colorScheme.onSurfaceVariant;
    final statusText = isCollecting ? 'Collecting data' : 'Paused';
    final statusIcon = isCollecting ? Icons.sensors : Icons.pause_circle_outline;

    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceSm),
        child: Column(
          children: [
            Row(
              children: [
                Icon(statusIcon, color: statusColor),
                const SizedBox(width: AppTheme.spaceXs),
                Expanded(
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        statusText,
                        style: theme.textTheme.titleMedium?.copyWith(
                          fontWeight: FontWeight.w600,
                          color: statusColor,
                        ),
                      ),
                      const SizedBox(height: 2),
                      Text(
                        isCollecting
                            ? 'Sensors are active and uploading data'
                            : 'Enable in Settings to start collecting',
                        style: theme.textTheme.bodySmall?.copyWith(
                          color: theme.colorScheme.onSurfaceVariant,
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
            if (isCollecting && isUploading) ...[
              const SizedBox(height: AppTheme.spaceXs),
              Row(
                children: [
                  SizedBox(
                    width: 16,
                    height: 16,
                    child: CircularProgressIndicator(
                      strokeWidth: 2,
                      valueColor: AlwaysStoppedAnimation<Color>(
                        theme.colorScheme.primary,
                      ),
                    ),
                  ),
                  const SizedBox(width: AppTheme.spaceXs),
                  Text(
                    'Uploading...',
                    style: theme.textTheme.bodySmall?.copyWith(
                      color: theme.colorScheme.primary,
                      fontWeight: FontWeight.w500,
                    ),
                  ),
                ],
              ),
            ],
          ],
        ),
      ),
    );
  }
}
