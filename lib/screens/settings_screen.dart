import 'package:flutter/material.dart';
import 'dart:developer' as developer;
import '../core/themes.dart';
import '../core/theme_controller.dart';
import 'webview_screen.dart';
import '../services/system/power_optimizations.dart';
import '../services/network/backend_client.dart';
import '../services/system/service_state_controller.dart';
import '../services/location/location_service.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:geolocator/geolocator.dart';
import '../core/app_preferences.dart';
import '../services/preferences/preferences_service.dart';

const _logTag = 'SettingsScreen';

class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  bool? _isExcluded;
  bool _loading = false;
  bool _useMobileData = false;
  bool _shareLocation = false;
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
      final allowLocation = prefs.shareLocation;
      developer.log('_refreshStatus: Mobile data=$allowMobile, Location=$allowLocation', name: _logTag);

      if (!mounted) {
        developer.log('_refreshStatus: Not mounted, returning early', name: _logTag);
        return;
      }

      setState(() {
        _isExcluded = v;
        _loading = false;
        _user = user;
        _useMobileData = allowMobile;
        _shareLocation = allowLocation;
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
      _showSnack('Opening system dialog... Come back and check status.');
      // Don't call _refreshStatus() - causes crash on MIUI when returning from settings
      // Status will be refreshed when user navigates back to this screen next time
    } else {
      _showSnack('Could not open settings.');
    }
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

      // Sync to backend
      PreferencesService.instance.savePreferences();
    } catch (e, stackTrace) {
      developer.log('_setUseMobileData: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
    }
  }

  Future<void> _setShareLocation(bool value) async {
    developer.log('_setShareLocation: Setting to $value', name: _logTag);
    try {
      await AppPreferences.instance.setShareLocation(value);
      developer.log('_setShareLocation: Preference saved', name: _logTag);
      if (!mounted) {
        developer.log('_setShareLocation: Not mounted, returning', name: _logTag);
        return;
      }
      setState(() => _shareLocation = value);
      developer.log('_setShareLocation: State updated', name: _logTag);

      // Auto-request permission when toggled ON
      if (value) {
        developer.log('_setShareLocation: Auto-requesting permission', name: _logTag);
        await _requestLocationPermission();
      }

      // Sync to backend
      PreferencesService.instance.savePreferences();
    } catch (e, stackTrace) {
      developer.log('_setShareLocation: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
    }
  }

  Future<void> _requestLocationPermission() async {
    developer.log('_requestLocationPermission: Starting', name: _logTag);
    final messenger = ScaffoldMessenger.of(context);

    try {
      // Check if location services are enabled first
      final serviceEnabled = await LocationService.instance.isLocationServiceEnabled();
      if (!serviceEnabled) {
        messenger.showSnackBar(
          const SnackBar(content: Text('Please enable Location Services in device settings')),
        );
        developer.log('_requestLocationPermission: Location services disabled', name: _logTag);
        // Open location settings
        await LocationService.instance.openLocationSettings();
        return;
      }

      // Check current permission status
      final permission = await LocationService.instance.checkPermission();
      developer.log('_requestLocationPermission: Current permission=$permission', name: _logTag);

      if (permission == LocationPermission.deniedForever) {
        messenger.showSnackBar(
          const SnackBar(content: Text('Location permission permanently denied. Please enable in app settings.')),
        );
        await LocationService.instance.openAppSettings();
        return;
      }

      if (permission == LocationPermission.whileInUse || permission == LocationPermission.always) {
        messenger.showSnackBar(
          const SnackBar(content: Text('Location permission already granted')),
        );
        return;
      }

      // Request permission (FINE + COARSE)
      developer.log('_requestLocationPermission: Requesting FINE location permission', name: _logTag);
      final granted = await LocationService.instance.requestLocation();
      developer.log('_requestLocationPermission: Permission granted=$granted', name: _logTag);

      if (granted) {
        messenger.showSnackBar(
          const SnackBar(content: Text('Location permission granted')),
        );
      } else {
        messenger.showSnackBar(
          const SnackBar(content: Text('Location permission denied')),
        );
      }
    } catch (e, stackTrace) {
      developer.log('_requestLocationPermission: ERROR', name: _logTag, error: e, stackTrace: stackTrace);
      messenger.showSnackBar(
        SnackBar(content: Text('Error requesting permission: $e')),
      );
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
        ? 'Checking status...'
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

                          // Sync to backend
                          PreferencesService.instance.savePreferences();
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
                child: SwitchListTile(
                  secondary: const Icon(Icons.location_on_outlined),
                  title: const Text('Share location'),
                  subtitle: const Text(
                    'Include precise GPS location (~10-50m accuracy) for smart city environmental data. Helps urban planning with street-level air quality and light pollution analysis.',
                  ),
                  value: _shareLocation,
                  onChanged: (value) => _setShareLocation(value),
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
