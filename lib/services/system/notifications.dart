import 'dart:io' show Platform;
import 'package:flutter/services.dart';

class AppNotifications {
  static const MethodChannel _ch = MethodChannel('greengains/notifications');

  static Future<bool> areEnabled() async {
    try {
      if (!Platform.isAndroid) return true;
      final res = await _ch.invokeMethod<bool>('areNotificationsEnabled');
      return res == true;
    } catch (_) {
      return true;
    }
  }

  static Future<bool> openSettings() async {
    try {
      if (!Platform.isAndroid) return true;
      final res = await _ch.invokeMethod<bool>('openNotificationSettings');
      return res == true;
    } catch (_) {
      return false;
    }
  }
}
