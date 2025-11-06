import 'dart:developer' as developer;
import 'package:flutter/services.dart';

class ForegroundService {
  static const MethodChannel _ch = MethodChannel('greengains/foreground');
  static const Duration _timeout = Duration(seconds: 3);

  static Future<bool> start() async {
    try {
      developer.log('ForegroundService: Starting service...', name: 'ForegroundService');
      final res = await _ch
          .invokeMethod<bool>('startForegroundService')
          .timeout(_timeout);
      developer.log('ForegroundService: Start result: $res', name: 'ForegroundService');
      return res == true;
    } catch (error) {
      developer.log('ForegroundService: Start failed: $error', name: 'ForegroundService');
      return false;
    }
  }

  static Future<bool> stop() async {
    try {
      developer.log('ForegroundService: Stopping service...', name: 'ForegroundService');
      final res = await _ch
          .invokeMethod<bool>('stopForegroundService')
          .timeout(_timeout);
      developer.log('ForegroundService: Stop result: $res', name: 'ForegroundService');
      return res == true;
    } catch (error) {
      developer.log('ForegroundService: Stop failed: $error', name: 'ForegroundService');
      return false;
    }
  }

  static Future<bool> isRunning() async {
    try {
      developer.log('ForegroundService: Checking if running...', name: 'ForegroundService');
      final res = await _ch
          .invokeMethod<bool>('isForegroundServiceRunning')
          .timeout(_timeout);
      developer.log('ForegroundService: isRunning result: $res', name: 'ForegroundService');
      return res == true;
    } catch (error) {
      developer.log('ForegroundService: isRunning check failed: $error', name: 'ForegroundService');
      // On timeout/error, assume service is still running if we got here from resume
      // This prevents false "stopped" states that cause UI issues
      return false;
    }
  }
}
