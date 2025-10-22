import 'package:flutter/material.dart';
import 'dart:developer' as developer;
import '../core/themes.dart';
import '../core/theme_controller.dart';
import 'webview_screen.dart';
import '../services/system/power_optimizations.dart';
import '../services/system/foreground_service.dart';
import '../services/sensors/sensor_manager.dart';
import '../services/system/notifications.dart';
import '../services/network/backend_client.dart';
import '../services/system/service_state_controller.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import '../main.dart';
import '../core/app_preferences.dart';

const _logTag = 'SettingsScreen';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  bool? _isExcluded;
  bool _serviceRunning = false;
  bool _loading = false;
  bool _useMobileData = false;
  bool _testingBackend = false;
  User? _user;
  final BackendClient _backendClient = BackendClient();

  @override
  void initState() {
    developer.log('initState called', name: _logTag);
    super.initState();
    _refreshStatus();
  }

  Future<void> _refreshStatus() async {
    developer.log('_refreshStatus: Starting', name: _logTag);
    if (!mounted) return;

    try {
      if (mounted) setState(() => _loading = true);

      developer.log('_refreshStatus: Checking battery optimizations', name: _logTag);
      final v = await PowerOptimizations.isIgnoringBatteryOptimizations();
      developer.log('_refreshStatus: Battery excluded=$v', name: _logTag);

      developer.log('_refreshStatus: Checking foreground service', name: _logTag);
      final running = ServiceStateController.instance.isRunning;
      developer.log('_refreshStatus: Service running=$running', name: _logTag);

      developer.log('_refreshStatus: Getting Firebase user', name: _logTag);
      final user = FirebaseAuth.instance.currentUser;
      developer.log('_refreshStatus: User=${user?.email ?? "null"}', name: _logTag);

      developer.log('_refreshStatus: Getting preferences', name: _logTag);
      final prefs = AppPreferences.instance;
      final allowMobile = prefs.useMobileUploads;
      developer.log('_refreshStatus: Mobile data=$allowMobile', name: _logTag);

      if (!mounted) {
        developer.log('_refreshStatus: Not mounted, returning early', name: _logTag);
        return;
      }

      setState(() {
        _isExcluded = v;
        _serviceRunning = running;
        _loading = false;
        _user = user;
        _useMobileData = allowMobile;
      });
      developer.log('_refreshStatus: Completed successfully', name: _logTag);
    } catch (e, stackTrace) {
      developer.log('_refreshStatus: ERROR - $e', name: _logTag, error: e, stackTrace: stackTrace);
      if (mounted) {
        setState(() => _loading = false);
      }
    }
  }

  Future<void> _requestExclusion() async {
    final already = await PowerOptimizations.isIgnoringBatteryOptimizations();
    if (already) {
      _showSnack('Already excluded from power savings');
      return;
    }
    final opened = await PowerOptimizations.requestIgnoreBatteryOptimizations();
    if (opened) {
      _showSnack('Opening system dialog…');
    } else {
      _showSnack('Could not open settings.');
    }
    await _refreshStatus();
  }

  void _showSnack(String msg) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(msg)),
    );
  }

  Future<void> _openExternal(String url) async {
    // Open inside the app with a lightweight WebView route to keep Nav stack intact.
    if (!mounted) return;
    await Navigator.of(context).push(
      MaterialPageRoute(builder: (_) => WebViewScreen(url: url)),
    );
  }


  Future<void> _setUseMobileData(bool value) async {
    developer.log('_setUseMobileData: Setting to $value', name: _logTag);
    try {
      await AppPreferences.instance.setUseMobileUploads(value);
      developer.log('_setUseMobileData: Preference saved', name: _logTag);
      if (!mounted) {
        developer.log('_setUseMobileData: Not mounted, returning', name: _logTag);
        return;
      }
      setState(() => _useMobileData = value);
      developer.log('_setUseMobileData: State updated', name: _logTag);
    } catch (e, stackTrace) {
      developer.log('_setUseMobileData: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
    }
  }

  Future<void> _testBackendConnection() async {
    if (!mounted) return;
    setState(() => _testingBackend = true);
    final messenger = ScaffoldMessenger.of(context);

    try {
      await AppPreferences.instance.ensureInitialized();
      final deviceId = await AppPreferences.instance.getOrCreateDeviceId();
      final timestamp = DateTime.now().toUtc().toIso8601String();
      final payload = {
        'device_id': deviceId,
        'timestamp': timestamp,
        'batch': [
          {
            't': timestamp,
            'light': 0.0,
            'accel': [0.0, 0.0, 9.81],
            'gyro': [0.0, 0.0, 0.0],
            'magnet': [0.0, 0.0, 0.0],
          },
        ],
      };
      await _backendClient.uploadBatch(payload, compress: true);
      messenger
          .showSnackBar(const SnackBar(content: Text('Backend reachable.')));
    } on BackendException catch (e) {
      messenger.showSnackBar(
        SnackBar(content: Text('Backend test failed: ${e.message}')),
      );
    } catch (e) {
      messenger.showSnackBar(
        SnackBar(content: Text('Backend test error: $e')),
      );
    } finally {
      if (mounted) {
        setState(() => _testingBackend = false);
      }
    }
  }

  Widget _buildTextLink(String label, VoidCallback onTap) {
    return InkWell(
      onTap: onTap,
      borderRadius: BorderRadius.circular(4),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 6.0),
        child: Text(
          label,
          style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                color: Theme.of(context).colorScheme.primary,
              ),
        ),
      ),
    );
  }

  @override
  void dispose() {
    developer.log('dispose called', name: _logTag);
    _backendClient.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    developer.log('build called - loading=$_loading, user=${_user?.email ?? "null"}', name: _logTag);
    final subtitle = _loading
        ? 'Checking status…'
        : _isExcluded == true
            ? 'Excluded from power savings'
            : 'May be restricted by power savings';
    return Scaffold(
      body: SafeArea(
        child: ListView(
            padding: const EdgeInsets.symmetric(
              horizontal: AppTheme.spaceSm,
              vertical: AppTheme.spaceSm,
            ),
            children: [
              Card(
                key: const ValueKey('theme-card'),
                child: AnimatedBuilder(
                  animation: ThemeController.instance,
                  builder: (context, _) {
                    final isDark = ThemeController.instance.isDark;
                    return SwitchListTile(
                      key: const ValueKey('theme-switch'),
                      secondary: const Icon(Icons.dark_mode_outlined),
                      title: const Text('Dark mode'),
                      subtitle: Text(isDark ? 'On' : 'Off'),
                      value: isDark,
                      onChanged: (v) {
                        developer.log('Theme switch: toggled to ${v ? "dark" : "light"}', name: _logTag);
                        if (!mounted) {
                          developer.log('Theme switch: Not mounted, ignoring', name: _logTag);
                          return;
                        }
                        try {
                          // Fire-and-forget for instant UI feedback; ThemeController
                          // notifies listeners synchronously, persistence happens async.
                          ThemeController.instance
                              .setMode(v ? ThemeMode.dark : ThemeMode.light);
                          developer.log('Theme switch: Mode set successfully', name: _logTag);
                        } catch (e, stackTrace) {
                          developer.log('Theme switch: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
                        }
                      },
                    );
                  },
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                child: ListTile(
                  leading: const Icon(Icons.battery_saver_outlined),
                  title: const Text('Battery optimization'),
                  subtitle: Text(subtitle),
                  trailing: SizedBox(
                    width: AppTheme.tileTrailingButtonWidth,
                    height: AppTheme.tileTrailingButtonHeight,
                    child: FilledButton(
                      onPressed: _requestExclusion,
                      child: const Text('Request'),
                    ),
                  ),
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                child: SwitchListTile(
                  secondary: const Icon(Icons.cloud_upload_outlined),
                  title: const Text('Use mobile data'),
                  subtitle: const Text(
                    'Allow GreenGains to upload data when Wi-Fi is unavailable (uses <10 MB/month).',
                  ),
                  value: _useMobileData,
                  onChanged: (value) => _setUseMobileData(value),
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                child: ListTile(
                  leading: const Icon(Icons.cloud_done_outlined),
                  title: const Text('Test backend connection'),
                  subtitle: Text(
                    'Target: ${_backendClient.baseUrl}',
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  trailing: _testingBackend
                      ? const SizedBox(
                          width: 24,
                          height: 24,
                          child: CircularProgressIndicator(strokeWidth: 2),
                        )
                      : SizedBox(
                          width: AppTheme.tileTrailingButtonWidth,
                          height: AppTheme.tileTrailingButtonHeight,
                          child: FilledButton(
                            onPressed: _testBackendConnection,
                            child: const Text('Run'),
                          ),
                        ),
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                key: const ValueKey('service-card'),
                child: SwitchListTile(
                  key: const ValueKey('service-switch'),
                  secondary: const Icon(Icons.play_circle_fill_outlined),
                  title: const Text('Foreground service'),
                  subtitle: Text(_serviceRunning ? 'Running' : 'Stopped'),
                  value: _serviceRunning,
                  onChanged: (v) async {
                    developer.log('Foreground service: Toggle to ${v ? "start" : "stop"}', name: _logTag);
                    bool ok;
                    try {
                      if (v) {
                        // Check notifications permission (Android 13+) and open settings if disabled
                        developer.log('Foreground service: Checking notifications permission', name: _logTag);
                        final enabled = await AppNotifications.areEnabled();
                        developer.log('Foreground service: Notifications enabled=$enabled', name: _logTag);

                        if (!enabled) {
                          developer.log('Foreground service: Opening notification settings', name: _logTag);
                          _showSnack(
                              'Enable notifications to run foreground service');
                          await AppNotifications.openSettings();
                        }

                        developer.log('Foreground service: Starting service', name: _logTag);
                        ok = await ForegroundService.start();
                        developer.log('Foreground service: Service start result=$ok', name: _logTag);

                        developer.log('Foreground service: Starting sensor manager', name: _logTag);
                        await SensorManager.instance.start();
                        developer.log('Foreground service: Sensor manager started', name: _logTag);
                      } else {
                        developer.log('Foreground service: Stopping service', name: _logTag);
                        ok = await ForegroundService.stop();
                        developer.log('Foreground service: Service stop result=$ok', name: _logTag);

                        developer.log('Foreground service: Stopping sensor manager', name: _logTag);
                        await SensorManager.instance.stop();
                        developer.log('Foreground service: Sensor manager stopped', name: _logTag);
                      }

                      if (!mounted) {
                        developer.log('Foreground service: Not mounted after operation', name: _logTag);
                        return;
                      }

                      setState(() {
                        _serviceRunning = v && ok;
                      });
                      developer.log('Foreground service: State updated to $_serviceRunning', name: _logTag);

                      // Update shared controller so all screens stay in sync
                      ServiceStateController.instance.setRunning(_serviceRunning);

                      // Save preference so it persists across app restarts
                      await AppPreferences.instance.setForegroundServiceEnabled(_serviceRunning);
                      developer.log('Foreground service: Preference saved', name: _logTag);
                    } catch (e, stackTrace) {
                      developer.log('Foreground service: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
                      if (mounted) {
                        _showSnack('Error: ${e.toString()}');
                      }
                    }
                  },
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Legal',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              fontWeight: FontWeight.w600,
                            ),
                      ),
                      const SizedBox(height: 8),
                      _buildTextLink(
                        'Terms of Service',
                        () => _openExternal(
                            'https://greengains.eremat.org/terms-of-service'),
                      ),
                      _buildTextLink(
                        'Privacy Policy',
                        () => _openExternal(
                            'https://greengains.eremat.org/privacy-policy'),
                      ),
                      _buildTextLink(
                        'Open source licenses',
                        () => showAboutDialog(
                            context: context, applicationName: 'GreenGains'),
                      ),
                      _buildTextLink(
                        'Data deletion request',
                        () => _openExternal(
                            'https://greengains.eremat.org/data-deletion-request'),
                      ),
                    ],
                  ),
                ),
              ),
              // Note: Removed PopScope wrapper that was causing navigation issues on MIUI devices.
              //       The in-app WebView route keeps the navigation stack stable.
            ],
          ),
      ),
    );
  }
}
