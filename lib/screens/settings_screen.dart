import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/services.dart';
import '../core/themes.dart';
import '../core/theme_controller.dart';
import '../core/app_preferences.dart';
import '../services/auth/auth_service.dart';

/// Settings screen for app configuration
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
    final user = FirebaseAuth.instance.currentUser;
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Settings'),
      ),
      body: ListView(
        padding: AppTheme.pagePadding,
        children: [
          // Account section
          if (user == null) ...[
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Text(
                      'Account',
                      style: theme.textTheme.titleLarge,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    Text(
                      'Sign in to sync your contributions and earn rewards',
                      style: theme.textTheme.bodyMedium,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    FilledButton.icon(
                      onPressed: _handleGoogleSignIn,
                      icon: const Icon(Icons.login),
                      label: const Text('Sign in with Google'),
                    ),
                  ],
                ),
              ),
            ),
          ] else ...[
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Account',
                      style: theme.textTheme.titleLarge,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    Row(
                      children: [
                        CircleAvatar(
                          radius: 24,
                          backgroundImage: user.photoURL != null
                              ? NetworkImage(user.photoURL!)
                              : null,
                          child: user.photoURL == null
                              ? const Icon(Icons.person)
                              : null,
                        ),
                        const SizedBox(width: AppTheme.spaceMd),
                        Expanded(
                          child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text(
                                user.displayName ?? 'User',
                                style: theme.textTheme.titleMedium,
                              ),
                              Text(
                                user.email ?? '',
                                style: theme.textTheme.bodySmall,
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    TextButton.icon(
                      onPressed: _handleSignOut,
                      icon: const Icon(Icons.logout),
                      label: const Text('Sign out'),
                    ),
                  ],
                ),
              ),
            ),
          ],

          const SizedBox(height: AppTheme.spaceLg),

          // Appearance section
          Card(
            child: Padding(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Appearance',
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
                    trailing: const Icon(Icons.open_in_new),
                    onTap: () {
                      // TODO: Open privacy policy URL
                    },
                  ),
                  ListTile(
                    contentPadding: EdgeInsets.zero,
                    leading: const Icon(Icons.description_outlined),
                    title: const Text('Terms of Service'),
                    trailing: const Icon(Icons.open_in_new),
                    onTap: () {
                      // TODO: Open terms of service URL
                    },
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Future<void> _handleGoogleSignIn() async {
    try {
      await AuthService.signInWithGoogleUniversal();
      if (mounted) {
        setState(() {});
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Signed in successfully')),
        );
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Sign-in failed: $e')),
        );
      }
    }
  }

  Future<void> _handleSignOut() async {
    try {
      await FirebaseAuth.instance.signOut();
      if (mounted) {
        setState(() {});
        ScaffoldMessenger.of(context).showSnackBar(
          const SnackBar(content: Text('Signed out')),
        );
      }
    } catch (e) {
      if (mounted) {
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(content: Text('Sign-out failed: $e')),
        );
      }
    }
  }
}
