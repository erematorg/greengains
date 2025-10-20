import 'dart:async';
import 'package:flutter/foundation.dart';
import '../services/sensors/sensor_manager.dart';

/// Provides sensor values to widgets via a Listenable.
class SensorProvider extends ChangeNotifier {
  final SensorManager _manager;
  StreamSubscription<double>? _luxSub;
  StreamSubscription<List<double>>? _accelSub;
  StreamSubscription<List<double>>? _gyroSub;
  StreamSubscription<List<double>>? _magSub;

  double? _ambientLux;
  double? get ambientLux => _ambientLux;

  List<double>? _accelerometer;
  List<double>? get accelerometer => _accelerometer;

  List<double>? _gyroscope;
  List<double>? get gyroscope => _gyroscope;

  List<double>? _magnetometer;
  List<double>? get magnetometer => _magnetometer;

  SensorProvider({SensorManager? manager})
      : _manager = manager ?? SensorManager.instance;

  Future<void> start() async {
    final isMobile = defaultTargetPlatform == TargetPlatform.android ||
        defaultTargetPlatform == TargetPlatform.iOS;
    if (!isMobile) {
      // Desktop/Web — no sensors: leave values null and don't subscribe.
      return;
    }
    await _manager.start();
    _luxSub ??= _manager.ambientLux$.listen((lux) {
      _ambientLux = (lux < 0) ? null : lux;
      notifyListeners();
    });
    _accelSub ??= _manager.accelerometer$.listen((values) {
      _accelerometer = values;
      notifyListeners();
    });
    _gyroSub ??= _manager.gyroscope$.listen((values) {
      _gyroscope = values;
      notifyListeners();
    });
    _magSub ??= _manager.magnetometer$.listen((values) {
      _magnetometer = values;
      notifyListeners();
    });
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
  }

  @override
  void dispose() {
    _luxSub?.cancel();
    _accelSub?.cancel();
    _gyroSub?.cancel();
    _magSub?.cancel();
    super.dispose();
  }
}
