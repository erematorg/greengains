import 'dart:async';
import 'package:flutter/foundation.dart';

/// Centralized service for managing "time ago" updates
/// Uses a single timer instead of creating one per widget
/// Notifies all listeners once per minute for efficient updates
class TimeAgoService {
  TimeAgoService._();
  static final instance = TimeAgoService._();

  Timer? _timer;
  final _tickNotifier = ValueNotifier<int>(0);

  /// Subscribe to minute ticks to update "time ago" displays
  /// Widget should rebuild when this notifier changes
  ValueListenable<int> get tick => _tickNotifier;

  /// Start the centralized timer (call once at app startup)
  void start() {
    if (_timer != null) return;

    // Update every 60 seconds
    _timer = Timer.periodic(const Duration(seconds: 60), (_) {
      _tickNotifier.value++;
    });

    debugPrint('[TimeAgoService] Started centralized timer');
  }

  /// Stop the timer (call at app shutdown if needed)
  void stop() {
    _timer?.cancel();
    _timer = null;
    debugPrint('[TimeAgoService] Stopped centralized timer');
  }

  /// Format a timestamp as "time ago" text
  static String format(DateTime timestamp) {
    final now = DateTime.now();
    final diff = now.difference(timestamp);

    if (diff.inMinutes < 1) {
      return 'just now';
    } else if (diff.inMinutes < 60) {
      return '${diff.inMinutes}m ago';
    } else if (diff.inHours < 24) {
      return '${diff.inHours}h ago';
    } else {
      return '${diff.inDays}d ago';
    }
  }

  void dispose() {
    stop();
    _tickNotifier.dispose();
  }
}
