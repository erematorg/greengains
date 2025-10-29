import 'dart:async';
import 'package:battery_plus/battery_plus.dart';
import 'dart:developer' as developer;

const _logTag = 'BatteryService';

/// Manages battery telemetry collection (like Nodle Cash)
///
/// Collects:
/// - Battery level (0-100%)
/// - Charging status (true/false)
///
/// Use case: Monitor fleet health and app battery impact
class BatteryService {
  BatteryService._();
  static final BatteryService instance = BatteryService._();

  final Battery _battery = Battery();

  /// Get current battery information
  ///
  /// Returns:
  /// - battery_level: 0-100 (percentage)
  /// - is_charging: true/false
  /// - null if battery info unavailable
  ///
  /// Note: Returns -1 for battery_level if charging (Nodle pattern)
  Future<Map<String, dynamic>?> getBatteryInfo() async {
    try {
      final level = await _battery.batteryLevel;
      final state = await _battery.batteryState;
      final isCharging = state == BatteryState.charging || state == BatteryState.full;

      final statusLabel = isCharging ? 'Charging' : '$level%';
      developer.log(
        'Battery: $statusLabel (level=$level, charging=$isCharging)',
        name: _logTag,
      );

      return {
        'battery_level': isCharging ? -1 : level,  // Nodle pattern: -1 when charging
        'is_charging': isCharging,
      };
    } catch (e) {
      developer.log('Error getting battery info: $e', name: _logTag, error: e);
      return null;
    }
  }
}
