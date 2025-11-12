import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import '../core/themes.dart';
import '../core/theme_controller.dart';
import '../core/app_preferences.dart';
import 'webview_screen.dart';

/// Settings screen for Data & Privacy, Themes, and Legal
class SettingsScreen extends StatefulWidget {
  const SettingsScreen({super.key});

  @override
  State<SettingsScreen> createState() => _SettingsScreenState();
}

class _SettingsScreenState extends State<SettingsScreen> {
  final _prefs = AppPreferences.instance;
  final _themeController = ThemeController.instance;
  static const _fgChannel = MethodChannel('greengains/foreground');

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Settings'),
      ),
      body: ListView(
        padding: AppTheme.pagePadding,
        children: [
          // Appearance / Theme section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Theme',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  ListenableBuilder(
                    listenable: _themeController,
                    builder: (context, _) {
                      return SegmentedButton<ThemeMode>(
                        segments: const [
                          ButtonSegment(
                            value: ThemeMode.light,
                            icon: Icon(Icons.light_mode),
                            label: Text('Light'),
                          ),
                          ButtonSegment(
                            value: ThemeMode.dark,
                            icon: Icon(Icons.dark_mode),
                            label: Text('Dark'),
                          ),
                          ButtonSegment(
                            value: ThemeMode.system,
                            icon: Icon(Icons.auto_mode),
                            label: Text('Auto'),
                          ),
                        ],
                        selected: {_themeController.mode},
                        onSelectionChanged: (Set<ThemeMode> newSelection) {
                          _themeController.setMode(newSelection.first);
                        },
                      );
                    },
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Data & Privacy section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Data & Privacy',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  SwitchListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Share Location'),
                    subtitle: const Text('Allow location data collection for heatmaps'),
                    value: _prefs.shareLocation,
                    onChanged: (value) async {
                      if (value) {
                        // Request location permission when enabling
                        debugPrint('Requesting location permission from Flutter...');
                        try {
                          await _fgChannel.invokeMethod('requestLocationPermission');
                          debugPrint('Permission request completed');
                        } catch (e) {
                          debugPrint('Permission request error: $e');
                        }
                      }
                      setState(() {
                        _prefs.setShareLocation(value);
                      });
                    },
                  ),
                  SwitchListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Use Mobile Data'),
                    subtitle: const Text('Upload contributions on cellular'),
                    value: _prefs.useMobileUploads,
                    onChanged: (value) {
                      setState(() {
                        _prefs.setUseMobileUploads(value);
                      });
                    },
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // About section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'About',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Version'),
                    subtitle: const Text('1.0.0+1'),
                  ),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Backend Services'),
                    subtitle: const Text('Firebase • API • Tracking'),
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // Legal section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Legal',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    leading: const Icon(Icons.privacy_tip_outlined),
                    title: const Text('Privacy Policy'),
                    trailing: const Icon(Icons.chevron_right),
                    onTap: () => _openWebView(
                      context,
                      'https://greengains.eremat.org/privacy-policy',
                      'Privacy Policy',
                    ),
                  ),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    leading: const Icon(Icons.description_outlined),
                    title: const Text('Terms of Service'),
                    trailing: const Icon(Icons.chevron_right),
                    onTap: () => _openWebView(
                      context,
                      'https://greengains.eremat.org/terms-of-service',
                      'Terms of Service',
                    ),
                  ),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    leading: const Icon(Icons.delete_outline),
                    title: const Text('Request Data Deletion'),
                    trailing: const Icon(Icons.chevron_right),
                    onTap: () => _openWebView(
                      context,
                      'https://greengains.eremat.org/data-deletion-request',
                      'Request Data Deletion',
                    ),
                  ),
                ],
              ),
            ),
          ),

          const SizedBox(height: AppTheme.spaceLg),

          // About section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'About',
                    style: theme.textTheme.titleLarge,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Version'),
                    subtitle: const Text('1.0.0+1'),
                  ),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    title: const Text('Backend Services'),
                    subtitle: const Text('Firebase • API • Tracking'),
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  void _openWebView(BuildContext context, String url, String title) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => WebViewScreen(
          url: url,
          title: title,
        ),
      ),
    );
  }
}
