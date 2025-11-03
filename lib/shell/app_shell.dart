import 'package:flutter/material.dart';
import '../screens/home_screen.dart';
import '../screens/profile_screen.dart';
import '../core/app_preferences.dart';
import '../services/system/service_state_controller.dart';
import '../services/system/foreground_service.dart';
import '../services/sensors/sensor_manager.dart';

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
    ProfileScreen(),
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
