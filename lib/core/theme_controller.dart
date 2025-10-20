import 'package:flutter/material.dart';
import 'app_preferences.dart';

class ThemeController extends ChangeNotifier {
  ThemeController._();
  static final ThemeController instance = ThemeController._();

  ThemeMode _mode = ThemeMode.light;
  ThemeMode get mode => _mode;
  bool get isDark => _mode == ThemeMode.dark;

  Future<void> load() async {
    final prefs = AppPreferences.instance;
    final s = prefs.themeModeRaw;
    _mode = prefs.decodeThemeMode(s);
    notifyListeners();
  }

  Future<void> setMode(ThemeMode m) async {
    _mode = m;
    notifyListeners();
    final prefs = AppPreferences.instance;
    await prefs.setThemeModeRaw(prefs.encodeThemeMode(m));
  }
}
