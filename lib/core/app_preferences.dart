import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:uuid/uuid.dart';

class PreferenceKeys {
  const PreferenceKeys._();
  static const onboardingComplete = 'onboarding_complete';
  static const themeMode = 'theme_mode';
  static const useMobileUploads = 'use_mobile_data_uploads';
  static const deviceId = 'device_id';
}

class AppPreferences {
  AppPreferences._();
  static final AppPreferences instance = AppPreferences._();

  SharedPreferences? _prefs;
  String? _deviceIdCache;
  static const _uuid = Uuid();

  Future<void> init() async {
    _prefs ??= await SharedPreferences.getInstance();
  }

  Future<void> ensureInitialized() => init();

  SharedPreferences get _sp {
    final prefs = _prefs;
    if (prefs == null) {
      throw StateError('AppPreferences not initialized. Call init() first.');
    }
    return prefs;
  }

  bool get onboardingComplete =>
      _sp.getBool(PreferenceKeys.onboardingComplete) ?? false;

  Future<void> setOnboardingComplete(bool value) async {
    await _sp.setBool(PreferenceKeys.onboardingComplete, value);
  }

  String? get themeModeRaw => _sp.getString(PreferenceKeys.themeMode);

  Future<void> setThemeModeRaw(String value) async {
    await _sp.setString(PreferenceKeys.themeMode, value);
  }

  bool get useMobileUploads =>
      _sp.getBool(PreferenceKeys.useMobileUploads) ?? false;

  Future<void> setUseMobileUploads(bool value) async {
    await _sp.setBool(PreferenceKeys.useMobileUploads, value);
  }

  Future<String> getOrCreateDeviceId() async {
    await ensureInitialized();
    if (_deviceIdCache != null) {
      return _deviceIdCache!;
    }
    final existing = _sp.getString(PreferenceKeys.deviceId);
    if (existing != null && existing.isNotEmpty) {
      _deviceIdCache = existing;
      return existing;
    }
    final generated = _uuid.v4();
    await _sp.setString(PreferenceKeys.deviceId, generated);
    _deviceIdCache = generated;
    return generated;
  }

  ThemeMode decodeThemeMode(String? raw) {
    switch (raw) {
      case 'dark':
        return ThemeMode.dark;
      case 'system':
        return ThemeMode.system;
      default:
        return ThemeMode.light;
    }
  }

  String encodeThemeMode(ThemeMode mode) {
    return mode == ThemeMode.dark
        ? 'dark'
        : mode == ThemeMode.system
            ? 'system'
            : 'light';
  }
}
