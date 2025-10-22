import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';
import '../screens/home_screen.dart';
import '../screens/settings_screen.dart';
import '../main.dart';
import '../core/app_preferences.dart';
import '../services/system/foreground_service.dart';
import '../services/sensors/sensor_manager.dart';
import '../services/system/service_state_controller.dart';

/// AppShell hosts the bottom NavigationBar with three primary tabs.
/// Tabs are intentionally light placeholders we can flesh out later.
class AppShell extends StatefulWidget {
  const AppShell({super.key});

  @override
  State<AppShell> createState() => _AppShellState();
}

class _AppShellState extends State<AppShell> {
  int _index = 0;

  final _pages = const [
    HomeScreen(),
    _ProfileScreenStub(),
  ];

  @override
  void initState() {
    super.initState();
    _restoreForegroundService();
  }

  Future<void> _restoreForegroundService() async {
    try {
      // Initialize the service state controller
      await ServiceStateController.instance.init();

      final prefs = AppPreferences.instance;
      final shouldBeRunning = prefs.foregroundServiceEnabled;
      final isRunning = ServiceStateController.instance.isRunning;

      // Auto-start if user had it enabled and it's not running
      if (shouldBeRunning && !isRunning) {
        await ForegroundService.start();
        await SensorManager.instance.start();
        ServiceStateController.instance.setRunning(true);
      }
    } catch (_) {
      // Silently fail - user can manually start if needed
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: IndexedStack(index: _index, children: _pages),
      bottomNavigationBar: NavigationBar(
        selectedIndex: _index,
        onDestinationSelected: (i) => setState(() => _index = i),
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.home_rounded),
            selectedIcon: Icon(Icons.home_filled),
            label: 'Home',
          ),
          NavigationDestination(
            icon: Icon(Icons.person_outline_rounded),
            selectedIcon: Icon(Icons.person_rounded),
            label: 'Profile',
          ),
        ],
      ),
    );
  }
}

class _ProfileScreenStub extends StatelessWidget {
  const _ProfileScreenStub();

  Future<void> _signOut(BuildContext context) async {
    final messenger = ScaffoldMessenger.of(context);
    final navigator = Navigator.of(context);

    try {
      await GoogleSignIn().signOut();
    } catch (_) {}

    await FirebaseAuth.instance.signOut();

    navigator.pushAndRemoveUntil(
      MaterialPageRoute(builder: (_) => const AuthGate()),
      (_) => false,
    );
    messenger.showSnackBar(const SnackBar(content: Text('Signed out')));
  }

  @override
  Widget build(BuildContext context) {
    final isMobile = Theme.of(context).platform == TargetPlatform.android ||
        Theme.of(context).platform == TargetPlatform.iOS;
    final user = FirebaseAuth.instance.currentUser;
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Profile', style: TextStyle(fontWeight: FontWeight.w700)),
      ),
      body: ListView(
        padding: const EdgeInsets.all(16),
        children: [
          // User Info Card
          if (user != null)
            Card(
              child: Padding(
                padding: const EdgeInsets.all(20),
                child: Column(
                  children: [
                    CircleAvatar(
                      radius: 40,
                      backgroundColor: theme.colorScheme.primaryContainer,
                      child: Icon(
                        Icons.person,
                        size: 40,
                        color: theme.colorScheme.onPrimaryContainer,
                      ),
                    ),
                    const SizedBox(height: 16),
                    Text(
                      user.email ?? 'No email',
                      style: theme.textTheme.titleMedium?.copyWith(
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      'Member since ${_formatDate(user.metadata.creationTime)}',
                      style: theme.textTheme.bodyMedium?.copyWith(
                        color: theme.colorScheme.onSurfaceVariant,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          const SizedBox(height: 16),

          // Settings
          if (isMobile)
            Card(
              child: ListTile(
                leading: const Icon(Icons.settings_outlined),
                title: const Text('Settings', style: TextStyle(fontWeight: FontWeight.w600)),
                subtitle: const Text('App preferences and permissions'),
                trailing: const Icon(Icons.chevron_right),
                onTap: () => Navigator.of(context).push(
                  MaterialPageRoute(builder: (_) => const SettingsScreen()),
                ),
              ),
            ),
          const SizedBox(height: 8),

          // Sign Out Button
          Card(
            color: theme.colorScheme.errorContainer,
            child: ListTile(
              leading: Icon(Icons.logout, color: theme.colorScheme.error),
              title: Text(
                'Sign Out',
                style: TextStyle(
                  fontWeight: FontWeight.w600,
                  color: theme.colorScheme.error,
                ),
              ),
              onTap: () => _signOut(context),
            ),
          ),
        ],
      ),
    );
  }

  String _formatDate(DateTime? date) {
    if (date == null) return 'Recently';
    final now = DateTime.now();
    final diff = now.difference(date).inDays;
    if (diff < 30) return '${diff + 1} days ago';
    if (diff < 365) return '${(diff / 30).floor()} months ago';
    return '${(diff / 365).floor()} year${diff >= 730 ? "s" : ""} ago';
  }
}
