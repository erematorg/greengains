import 'dart:async';
import 'dart:developer' as developer;
import 'package:flutter/foundation.dart';
import '../services/sensors/sensor_manager.dart';

/// Provides sensor values to widgets via a Listenable.
class SensorProvider extends ChangeNotifier {
  final SensorManager _manager;
  StreamSubscription<double>? _luxSub;
  StreamSubscription<List<double>>? _accelSub;
  StreamSubscription<List<double>>? _gyroSub;
  StreamSubscription<List<double>>? _magSub;
  bool _isDisposed = false;
  DateTime? _lastNotify;

  double? _ambientLux;
  double? get ambientLux => _ambientLux;

  List<double>? _accelerometer;
  List<double>? get accelerometer => _accelerometer;

  List<double>? _gyroscope;
  List<double>? get gyroscope => _gyroscope;

  List<double>? _magnetometer;
  List<double>? get magnetometer => _magnetometer;

  /// True when at least one sensor stream has delivered data.
  bool get hasLiveData =>
      _ambientLux != null ||
      _accelerometer != null ||
      _gyroscope != null ||
      _magnetometer != null;

  SensorProvider({SensorManager? manager})
      : _manager = manager ?? SensorManager.instance;

  /// Throttled notifyListeners - only notify once per 100ms to prevent UI freeze
  void _notifyThrottled() {
    final now = DateTime.now();
    if (_lastNotify == null || now.difference(_lastNotify!) > const Duration(milliseconds: 100)) {
      _lastNotify = now;
      notifyListeners();
    }
  }

  Future<void> start() async {
    if (_isDisposed) {
      developer.log('SensorProvider: Cannot start, already disposed', name: 'SensorProvider');
      return;
    }

    final isMobile = defaultTargetPlatform == TargetPlatform.android ||
        defaultTargetPlatform == TargetPlatform.iOS;
    if (!isMobile) {
      // Desktop/Web -> no sensors: leave values null and don't subscribe.
      return;
    }

    try {
      await _manager.start();
      _luxSub ??= _manager.ambientLux$.listen(
        (lux) {
          if (_isDisposed) return;
          _ambientLux = (lux < 0) ? null : lux;
          _notifyThrottled(); // Throttled to prevent UI freeze
        },
        onError: (error) {
          developer.log('SensorProvider: Light sensor error', name: 'SensorProvider', error: error);
        },
      );
      _accelSub ??= _manager.accelerometer$.listen(
        (values) {
          if (_isDisposed) return;
          _accelerometer = values;
          _notifyThrottled(); // Throttled to prevent UI freeze
        },
        onError: (error) {
          developer.log('SensorProvider: Accelerometer error', name: 'SensorProvider', error: error);
        },
      );
      _gyroSub ??= _manager.gyroscope$.listen(
        (values) {
          if (_isDisposed) return;
          _gyroscope = values;
          _notifyThrottled(); // Throttled to prevent UI freeze
        },
        onError: (error) {
          developer.log('SensorProvider: Gyroscope error', name: 'SensorProvider', error: error);
        },
      );
      _magSub ??= _manager.magnetometer$.listen(
        (values) {
          if (_isDisposed) return;
          _magnetometer = values;
          _notifyThrottled(); // Throttled to prevent UI freeze
        },
        onError: (error) {
          developer.log('SensorProvider: Magnetometer error', name: 'SensorProvider', error: error);
        },
      );
    } catch (error, stackTrace) {
      developer.log(
        'SensorProvider: Failed to start sensors',
        name: 'SensorProvider',
        error: error,
        stackTrace: stackTrace,
      );
    }
  }

  Future<void> stop() async {
    await _manager.stop();
    await _luxSub?.cancel();
    _luxSub = null;
    await _accelSub?.cancel();
    _accelSub = null;
    await _gyroSub?.cancel();
    _gyroSub = null;
    await _magSub?.cancel();
    _magSub = null;
    _ambientLux = null;
    _accelerometer = null;
    _gyroscope = null;
    _magnetometer = null;
    notifyListeners();
  }

  @override
  void dispose() {
    developer.log('SensorProvider: Disposing', name: 'SensorProvider');
    _isDisposed = true;
    _luxSub?.cancel();
    _accelSub?.cancel();
    _gyroSub?.cancel();
    _magSub?.cancel();
    super.dispose();
  }
}
