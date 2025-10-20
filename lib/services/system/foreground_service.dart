import 'package:flutter/services.dart';

class ForegroundService {
  static const MethodChannel _ch = MethodChannel('greengains/foreground');

  static Future<bool> start() async {
    try {
      final res = await _ch.invokeMethod<bool>('startForegroundService');
      return res == true;
    } catch (_) {
      return false;
    }
  }

  static Future<bool> stop() async {
    try {
      final res = await _ch.invokeMethod<bool>('stopForegroundService');
      return res == true;
    } catch (_) {
      return false;
    }
  }

  static Future<bool> isRunning() async {
    try {
      final res = await _ch.invokeMethod<bool>('isForegroundServiceRunning');
      return res == true;
    } catch (_) {
      return false;
    }
  }
}
