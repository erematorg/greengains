import 'dart:convert';
import 'dart:developer' as developer;
import 'package:http/http.dart' as http;
import 'package:firebase_auth/firebase_auth.dart';
import 'package:geolocator/geolocator.dart';
import '../network/backend_client.dart';
import '../../core/app_preferences.dart';
import '../../core/theme_controller.dart';
import 'package:flutter/material.dart';

const _logTag = 'PreferencesService';

/// Syncs user preferences between local storage and backend
class PreferencesService {
  PreferencesService._();
  static final PreferencesService instance = PreferencesService._();

  final http.Client _client = http.Client();

  /// Fetch preferences from backend and apply them locally
  Future<void> fetchAndApplyPreferences() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        developer.log('No user signed in, skipping preference sync', name: _logTag);
        return;
      }

      final token = await user.getIdToken();
      if (token == null) {
        developer.log('Failed to get Firebase token', name: _logTag);
        return;
      }

      final uri = Uri.parse('$kBackendBaseUrl/user/preferences');
      final response = await _client.get(
        uri,
        headers: {
          'Authorization': 'Bearer $token',
        },
      ).timeout(
        const Duration(seconds: 5),
        onTimeout: () {
          developer.log('Preference fetch timed out', name: _logTag);
          throw Exception('Network timeout');
        },
      );

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        developer.log('Fetched preferences from backend: $data', name: _logTag);

        // Apply preferences locally
        final prefs = AppPreferences.instance;
        await prefs.setUseMobileUploads(data['use_mobile_data'] as bool? ?? false);

        // Smart restore for location: validate actual permission
        final backendWantsLocation = data['share_location'] as bool? ?? false;
        if (backendWantsLocation) {
          final permission = await Geolocator.checkPermission();
          if (permission == LocationPermission.always ||
              permission == LocationPermission.whileInUse) {
            // Permission still granted - apply preference
            await prefs.setShareLocation(true);
            developer.log('Location restored: permission OK', name: _logTag);
          } else {
            // Permission revoked (e.g., after app reinstall) - reset locally
            await prefs.setShareLocation(false);
            developer.log('Location preference reset: permission denied after restore', name: _logTag);
          }
        } else {
          await prefs.setShareLocation(false);
        }

        // Apply theme
        final themeMode = data['theme_mode'] as String? ?? 'system';
        ThemeMode mode;
        switch (themeMode) {
          case 'light':
            mode = ThemeMode.light;
            break;
          case 'dark':
            mode = ThemeMode.dark;
            break;
          default:
            mode = ThemeMode.system;
        }
        ThemeController.instance.setMode(mode);

        developer.log('Applied preferences locally', name: _logTag);
      } else if (response.statusCode == 401) {
        developer.log('Authentication failed, token may be invalid', name: _logTag);
      } else {
        developer.log('Failed to fetch preferences: ${response.statusCode}', name: _logTag);
      }
    } catch (e) {
      developer.log('Error fetching preferences: $e', name: _logTag, error: e);
    }
  }

  /// Save preferences to backend (runs in background, non-blocking)
  void savePreferences() {
    // Fire-and-forget: save to backend without blocking UI
    _savePreferencesAsync();
  }

  Future<void> _savePreferencesAsync() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        developer.log('No user signed in, skipping preference save', name: _logTag);
        return;
      }

      final token = await user.getIdToken();
      if (token == null) {
        developer.log('Failed to get Firebase token', name: _logTag);
        return;
      }

      final prefs = AppPreferences.instance;
      final themeController = ThemeController.instance;

      // Map ThemeMode to string
      String themeMode;
      switch (themeController.mode) {
        case ThemeMode.light:
          themeMode = 'light';
          break;
        case ThemeMode.dark:
          themeMode = 'dark';
          break;
        default:
          themeMode = 'system';
      }

      final payload = {
        'theme_mode': themeMode,
        'use_mobile_data': prefs.useMobileUploads,
        'share_location': prefs.shareLocation,
      };

      final uri = Uri.parse('$kBackendBaseUrl/user/preferences');
      final response = await _client.put(
        uri,
        headers: {
          'Authorization': 'Bearer $token',
          'Content-Type': 'application/json',
        },
        body: jsonEncode(payload),
      ).timeout(
        const Duration(seconds: 5),
        onTimeout: () {
          developer.log('Preference save timed out', name: _logTag);
          throw Exception('Network timeout');
        },
      );

      if (response.statusCode == 200) {
        developer.log('Saved preferences to backend', name: _logTag);
      } else if (response.statusCode == 401) {
        developer.log('Authentication failed, token may be invalid', name: _logTag);
      } else {
        developer.log('Failed to save preferences: ${response.statusCode} ${response.body}', name: _logTag);
      }
    } catch (e) {
      developer.log('Error saving preferences: $e', name: _logTag, error: e);
    }
  }

  void dispose() {
    _client.close();
  }
}
