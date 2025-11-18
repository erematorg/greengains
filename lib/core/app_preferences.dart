import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:uuid/uuid.dart';

class PreferenceKeys {
  const PreferenceKeys._();
  static const onboardingComplete = 'onboarding_complete';
  static const themeMode = 'theme_mode';
  static const useMobileUploads = 'use_mobile_data_uploads';
  static const deviceId = 'device_id';
  static const foregroundServiceEnabled = 'foreground_service_enabled';
  static const shareLocation = 'share_location';
  static const lastUploadAt = 'last_upload_at';
  static const dismissedTips = 'dismissed_tips';
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
      _sp.getBool(PreferenceKeys.useMobileUploads) ?? true;

  Future<void> setUseMobileUploads(bool value) async {
    await _sp.setBool(PreferenceKeys.useMobileUploads, value);
  }

  bool get foregroundServiceEnabled =>
      _sp.getBool(PreferenceKeys.foregroundServiceEnabled) ?? false;

  Future<void> setForegroundServiceEnabled(bool value) async {
    await _sp.setBool(PreferenceKeys.foregroundServiceEnabled, value);
  }

  /// Location sharing preference - defaults to FALSE, user must opt-in
  /// COARSE location (~200m) is privacy-friendly but requires explicit consent
  bool get shareLocation => _sp.getBool(PreferenceKeys.shareLocation) ?? false;

  Future<void> setShareLocation(bool value) async {
    await _sp.setBool(PreferenceKeys.shareLocation, value);
  }


  DateTime? get lastUploadAt {
    final raw = _sp.getString(PreferenceKeys.lastUploadAt);
    if (raw == null || raw.isEmpty) {
      return null;
    }
    final parsed = DateTime.tryParse(raw);
    return parsed?.toLocal();
  }

  Future<void> setLastUploadAt(DateTime timestamp) async {
    await _sp.setString(
      PreferenceKeys.lastUploadAt,
      timestamp.toUtc().toIso8601String(),
    );
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

  /// Check if a contextual tip has been dismissed
  bool isTipDismissed(String tipId) {
    final dismissed = _sp.getStringList(PreferenceKeys.dismissedTips) ?? [];
    return dismissed.contains(tipId);
  }

  /// Dismiss a contextual tip permanently
  Future<void> dismissTip(String tipId) async {
    final dismissed = _sp.getStringList(PreferenceKeys.dismissedTips) ?? [];
    if (!dismissed.contains(tipId)) {
      dismissed.add(tipId);
      await _sp.setStringList(PreferenceKeys.dismissedTips, dismissed);
    }
  }
}
