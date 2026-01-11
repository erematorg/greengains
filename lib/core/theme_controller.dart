import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'app_preferences.dart';

class ThemeController extends ChangeNotifier {
  ThemeController._();
  static final ThemeController instance = ThemeController._();

  ThemeMode _mode = ThemeMode.system; // Default to system preference
  ThemeMode get mode => _mode;
  bool get isDark => _mode == ThemeMode.dark;

  Future<void> load() async {
    final prefs = AppPreferences.instance;
    final s = prefs.themeModeRaw;
    _mode = prefs.decodeThemeMode(s);
    notifyListeners();
  }

  void setMode(ThemeMode m) {
    _mode = m;
    notifyListeners(); // Instant UI update

    // Only save to SharedPreferences if user is signed in (not anonymous)
    final user = FirebaseAuth.instance.currentUser;
    if (user != null && !user.isAnonymous) {
      final prefs = AppPreferences.instance;
      prefs.setThemeModeRaw(prefs.encodeThemeMode(m));
    }
    // Anonymous users: Theme not saved (resets to system default on app restart)
    // This encourages sign-in for a better, persistent experience
  }
}
