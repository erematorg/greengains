import 'package:flutter/services.dart';

class PowerOptimizations {
  static const MethodChannel _channel = MethodChannel('greengains/power');

  /// Returns true if the app is already exempt from battery optimizations (Doze).
  static Future<bool> isIgnoringBatteryOptimizations() async {
    try {
      final res = await _channel.invokeMethod<bool>('isIgnoringBatteryOptimizations');
      return res == true;
    } catch (_) {
      return false;
    }
  }

  /// Requests exemption from battery optimizations by opening the system dialog.
  /// Returns true if the intent was launched (not the user's choice result).
  static Future<bool> requestIgnoreBatteryOptimizations() async {
    try {
      final res = await _channel.invokeMethod<bool>('requestIgnoreBatteryOptimizations');
      return res == true;
    } catch (_) {
      return false;
    }
  }
}

