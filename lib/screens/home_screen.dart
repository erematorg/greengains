import 'package:flutter/material.dart';
import 'dart:async';

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
    if (_isCollecting) {
      // Stop everything
      await _uploader.stop();
      await ForegroundService.stop();
      await SensorManager.instance.stop();
      ServiceStateController.instance.setRunning(false);
    } else {
      // Start everything
      await ForegroundService.start();
      await SensorManager.instance.start();
      await _uploader.start();
      ServiceStateController.instance.setRunning(true);
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
                StatusCard(
                  isCollecting: _isCollecting,
                  isUploading: _isUploading,
                  onToggle: _handleToggle,
                ),
                const SizedBox(height: AppTheme.spaceMd),
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
}
