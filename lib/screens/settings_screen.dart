import 'package:flutter/material.dart';
import '../core/themes.dart';
import '../core/theme_controller.dart';
import 'webview_screen.dart';
import '../services/system/power_optimizations.dart';
import '../services/system/foreground_service.dart';
import '../services/sensors/sensor_manager.dart';
import '../services/system/notifications.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import '../main.dart';
import '../core/app_preferences.dart';

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
  User? _user;
  DateTime? _lastLinkReturn;

  @override
  void initState() {
    super.initState();
    _refreshStatus();
  }

  Future<void> _refreshStatus() async {
    setState(() => _loading = true);
    final v = await PowerOptimizations.isIgnoringBatteryOptimizations();
    final running = await ForegroundService.isRunning();
    final user = FirebaseAuth.instance.currentUser;
    final prefs = AppPreferences.instance;
    final allowMobile = prefs.useMobileUploads;
    if (!mounted) return;
    setState(() {
      _isExcluded = v;
      _serviceRunning = running;
      _loading = false;
      _user = user;
      _useMobileData = allowMobile;
    });
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
    _lastLinkReturn = DateTime.now();
  }

  Future<void> _signOut() async {
    final messenger = ScaffoldMessenger.of(context);
    try {
      await GoogleSignIn().signOut();
    } catch (_) {}
    await FirebaseAuth.instance.signOut();
    if (!mounted) return;
    // Clear stack and return to AuthGate (which routes to Login/Home based on auth).
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(builder: (_) => const AuthGate()),
      (_) => false,
    );
    messenger.showSnackBar(const SnackBar(content: Text('Signed out')));
  }

  Future<void> _setUseMobileData(bool value) async {
    await AppPreferences.instance.setUseMobileUploads(value);
    if (!mounted) return;
    setState(() => _useMobileData = value);
  }

  @override
  Widget build(BuildContext context) {
    final subtitle = _loading
        ? 'Checking status…'
        : _isExcluded == true
            ? 'Excluded from power savings'
            : 'May be restricted by power savings';
    return Scaffold(
      body: SafeArea(
        child: PopScope(
          canPop: true,
          onPopInvokedWithResult: (didPop, result) {
            if (didPop) return;
            final now = DateTime.now();
            if (_lastLinkReturn != null &&
                now.difference(_lastLinkReturn!) <
                    const Duration(milliseconds: 600)) {
              _lastLinkReturn = null;
              Navigator.of(context).maybePop();
            }
          },
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
                        // Fire-and-forget for instant UI feedback; ThemeController
                        // notifies listeners synchronously, persistence happens async.
                        ThemeController.instance
                            .setMode(v ? ThemeMode.dark : ThemeMode.light);
                      },
                    );
                  },
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              if (_user != null) ...[
                Card(
                  child: ListTile(
                    leading: const Icon(Icons.person_outline),
                    title: const Text('Profile'),
                    subtitle: Text(_user!.email ?? '(no email)'),
                    trailing: SizedBox(
                      width: AppTheme.tileTrailingButtonWidth,
                      height: AppTheme.tileTrailingButtonHeight,
                      child: FilledButton(
                        onPressed: _signOut,
                        child: const Text('Sign out'),
                      ),
                    ),
                  ),
                ),
                const SizedBox(height: AppTheme.spaceSm),
              ],
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
                key: const ValueKey('service-card'),
                child: SwitchListTile(
                  key: const ValueKey('service-switch'),
                  secondary: const Icon(Icons.play_circle_fill_outlined),
                  title: const Text('Foreground service'),
                  subtitle: Text(_serviceRunning ? 'Running' : 'Stopped'),
                  value: _serviceRunning,
                  onChanged: (v) async {
                    bool ok;
                    if (v) {
                      // Check notifications permission (Android 13+) and open settings if disabled
                      final enabled = await AppNotifications.areEnabled();
                      if (!enabled) {
                        _showSnack(
                            'Enable notifications to run foreground service');
                        await AppNotifications.openSettings();
                      }
                      ok = await ForegroundService.start();
                      await SensorManager.instance.start();
                    } else {
                      ok = await ForegroundService.stop();
                      await SensorManager.instance.stop();
                    }
                    if (!mounted) return;
                    setState(() {
                      _serviceRunning = v && ok;
                    });
                  },
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),
              Card(
                child: Column(
                  children: [
                    ListTile(
                      leading: const Icon(Icons.description_outlined),
                      title: const Text('Terms of Service'),
                      onTap: () => _openExternal(
                          'https://greengains.eremat.org/terms-of-service'),
                    ),
                    const Divider(height: 1),
                    ListTile(
                      leading: const Icon(Icons.privacy_tip_outlined),
                      title: const Text('Privacy Policy'),
                      onTap: () => _openExternal(
                          'https://greengains.eremat.org/privacy-policy'),
                    ),
                    const Divider(height: 1),
                    ListTile(
                      leading: const Icon(Icons.info_outline),
                      title: const Text('Open source licenses'),
                      onTap: () => showAboutDialog(
                          context: context, applicationName: 'GreenGains'),
                    ),
                    const Divider(height: 1),
                    ListTile(
                      leading: const Icon(Icons.delete_outline),
                      title: const Text('Data deletion request'),
                      onTap: () => _openExternal(
                          'https://greengains.eremat.org/data-deletion-request'),
                    ),
                  ],
                ),
              ),
              // Note: Investigate rare Settings back crash on specific devices (MIUI).
              //       Current approach uses an in-app WebView route to keep stack stable.
            ],
          ),
        ),
      ),
    );
  }
}
