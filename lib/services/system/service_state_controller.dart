import 'package:flutter/foundation.dart';
import 'foreground_service.dart';

/// Singleton controller that manages and broadcasts the foreground service state
/// to keep UI components in sync
class ServiceStateController extends ChangeNotifier {
  ServiceStateController._();
  static final ServiceStateController instance = ServiceStateController._();

  bool _isRunning = false;
  bool get isRunning => _isRunning;

  /// Initialize the controller by checking actual service state
  Future<void> init() async {
    _isRunning = await ForegroundService.isRunning();
    notifyListeners();
  }

  /// Update the running state and notify all listeners
  void setRunning(bool running) {
    if (_isRunning != running) {
      _isRunning = running;
      notifyListeners();
    }
  }

  /// Refresh state from actual service
  Future<void> refresh() async {
    final running = await ForegroundService.isRunning();
    setRunning(running);
  }
}
