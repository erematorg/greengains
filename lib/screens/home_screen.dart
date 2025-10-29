import 'dart:async';
import 'dart:developer' as developer;

import 'package:flutter/material.dart';

import '../providers/sensor_provider.dart';
import '../widgets/sensor_tile.dart';
import '../widgets/accelerometer_tile.dart';
import '../widgets/gyroscope_tile.dart';
import '../widgets/magnetometer_tile.dart';
import '../widgets/status_card.dart';
import '../widgets/section_title.dart';
import 'settings_screen.dart';
import '../core/themes.dart';
import '../services/network/sensor_uploader.dart';
import '../services/system/foreground_service.dart';
import '../services/system/service_state_controller.dart';
import '../services/sensors/sensor_manager.dart';

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

  Future<void> _handleToggle() async {
    if (!mounted) {
      return;
    }

    final messenger = ScaffoldMessenger.of(context);

    try {
      if (_isCollecting) {
        // Stop everything
        await _uploader.stop();
        final stopped = await ForegroundService.stop();
        await SensorManager.instance.stop();

        if (!stopped && mounted) {
          messenger.showSnackBar(
            const SnackBar(
              content: Text('Could not stop background collection. Check system settings.'),
            ),
          );
          await ServiceStateController.instance.refresh();
          return;
        }

        ServiceStateController.instance.setRunning(false);
      } else {
        // Start everything with failure feedback
        final started = await ForegroundService.start();
        if (!started) {
          if (mounted) {
            messenger.showSnackBar(
              const SnackBar(
                content: Text('Could not start background collection. Check battery optimisation settings.'),
              ),
            );
          }
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

      if (mounted) {
        messenger.showSnackBar(
          const SnackBar(
            content: Text('Something went wrong while toggling collection.'),
          ),
        );
      }

      if (!_isCollecting) {
        // Rolling back to a safe state if starting failed halfway through.
        await ForegroundService.stop();
      }

      await ServiceStateController.instance.refresh();
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
            final hasLiveData = _provider.hasLiveData;
            return ListView(
              padding: const EdgeInsets.symmetric(
                vertical: AppTheme.spaceXs,
                horizontal: AppTheme.spaceSm,
              ),
              children: [
                StatusCard(
                  isCollecting: _isCollecting,
                  isUploading: _isUploading,
                  onToggle: _handleToggle,
                ),
                const SizedBox(height: AppTheme.spaceMd),
                if (!hasLiveData)
                  _buildSensorHintCard(context, isCollecting: _isCollecting),
                if (!hasLiveData) const SizedBox(height: AppTheme.spaceSm),
                const SectionTitle('Environment'),
                Card(
                  child: SensorTile(
                    title: 'Light',
                    unit: 'lux',
                    value: _provider.ambientLux,
                  ),
                ),
                const Divider(height: AppTheme.spaceMd),
                const SectionTitle('Motion'),
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

  Widget _buildSensorHintCard(BuildContext context, {required bool isCollecting}) {
    final theme = Theme.of(context);
    final title = isCollecting ? 'Warming up sensors' : 'Ready for the first readings';
    final message = isCollecting
        ? 'Give it a few seconds while we capture the first sample set. Live values appear as soon as the device streams data.'
        : 'Tap “Collecting data” to start or review permissions if the tiles stay blank.';
    final ctaLabel = isCollecting ? 'Open settings' : 'Review permissions';

    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceSm),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Icon(
              Icons.insights_outlined,
              color: theme.colorScheme.primary,
            ),
            const SizedBox(width: AppTheme.spaceSm),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    title,
                    style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
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
                        MaterialPageRoute(builder: (_) => const SettingsScreen()),
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
}
