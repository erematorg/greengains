import 'package:flutter/material.dart';
import 'app_preferences.dart';

class LanguageController extends ChangeNotifier {
  LanguageController._();
  static final LanguageController instance = LanguageController._();

  // null = system default, 'en' = English, 'fr' = French
  Locale? _locale;
  Locale? get locale => _locale;

  Future<void> load() async {
    final prefs = AppPreferences.instance;
    final code = prefs.languageCode;
    _locale = code != null ? Locale(code) : null;
    notifyListeners();
  }

  /// Set language preference
  /// Pass null to use system default
  Future<void> setLocale(Locale? locale) async {
    _locale = locale;
    notifyListeners(); // Instant UI update

    // Save to SharedPreferences
    final prefs = AppPreferences.instance;
    await prefs.setLanguageCode(locale?.languageCode);
  }

  /// Get display name for a language code
  static String getLanguageName(String? code, String currentLanguageCode) {
    if (code == null) {
      return currentLanguageCode == 'fr' ? 'Système' : 'System';
    }
    switch (code) {
      case 'en':
        return 'English';
      case 'fr':
        return 'Français';
      default:
        return code.toUpperCase();
    }
  }
}
