import 'package:flutter/foundation.dart';
import 'package:geolocator/geolocator.dart';

/// Service for handling location permissions
class PermissionService {
  PermissionService._();
  static final PermissionService instance = PermissionService._();

  /// Check if location permissions are granted
  Future<bool> hasLocationPermission() async {
    try {
      final permission = await Geolocator.checkPermission();
      return permission == LocationPermission.always ||
          permission == LocationPermission.whileInUse;
    } catch (e) {
      debugPrint('Error checking location permission: $e');
      return false;
    }
  }

  /// Request location permissions (FINE + COARSE)
  /// Returns true if granted, false otherwise
  Future<bool> requestLocationPermission() async {
    try {
      // First check if location services are enabled
      final serviceEnabled = await Geolocator.isLocationServiceEnabled();
      if (!serviceEnabled) {
        debugPrint('Location services are disabled');
        return false;
      }

      // Check current permission status
      LocationPermission permission = await Geolocator.checkPermission();

      if (permission == LocationPermission.denied) {
        // Request permission
        debugPrint('Requesting location permission...');
        permission = await Geolocator.requestPermission();
      }

      if (permission == LocationPermission.deniedForever) {
        // Permissions are permanently denied
        debugPrint('Location permissions are permanently denied');
        return false;
      }

      if (permission == LocationPermission.denied) {
        // Permissions are denied
        debugPrint('Location permissions denied by user');
        return false;
      }

      // Permission granted (whileInUse or always)
      debugPrint('Location permission granted: $permission');
      return true;
    } catch (e) {
      debugPrint('Error requesting location permission: $e');
      return false;
    }
  }

  /// Open app settings to manually grant permissions
  Future<bool> openLocationSettings() async {
    try {
      return await Geolocator.openAppSettings();
    } catch (e) {
      debugPrint('Error opening location settings: $e');
      return false;
    }
  }

  /// Get detailed permission status for UI display
  Future<String> getPermissionStatusText() async {
    try {
      final serviceEnabled = await Geolocator.isLocationServiceEnabled();
      if (!serviceEnabled) {
        return 'Location services disabled';
      }

      final permission = await Geolocator.checkPermission();
      switch (permission) {
        case LocationPermission.denied:
          return 'Permission denied';
        case LocationPermission.deniedForever:
          return 'Permission permanently denied';
        case LocationPermission.whileInUse:
          return 'Granted (while in use)';
        case LocationPermission.always:
          return 'Granted (always)';
        default:
          return 'Unknown';
      }
    } catch (e) {
      return 'Error: $e';
    }
  }
}
