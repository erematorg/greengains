import 'dart:async';

import 'light_sensor.dart';
import 'package:flutter/foundation.dart';
import 'accelerometer_sensor.dart';
import 'gyroscope_sensor.dart';
import 'magnetometer_sensor.dart';

/// Central point to manage device sensors.
class SensorManager {
  SensorManager._internal();
  static final SensorManager instance = SensorManager._internal();

  RealLightSensor? _light;
  StreamSubscription<double>? _lightSub;

  final _ambientLuxController = StreamController<double>.broadcast();
  double? _lastLux;

  Stream<double> get ambientLux$ => _ambientLuxController.stream;
  double? get lastAmbientLux => _lastLux;

  // Accelerometer
  final AccelerometerSensor _accelerometerSensor = AccelerometerSensor();
  late final Stream<List<double>> accelerometer$ =
      _accelerometerSensor.accelerometerStream;

  // Gyroscope
  final GyroscopeSensor _gyroscopeSensor = GyroscopeSensor();
  late final Stream<List<double>> gyroscope$ = _gyroscopeSensor.gyroscopeStream;

  // Magnetometer
  final MagnetometerSensor _magnetometerSensor = MagnetometerSensor();
  late final Stream<List<double>> magnetometer$ =
      _magnetometerSensor.magnetometerStream;

  bool _started = false;
  bool get started => _started;

  /// Initialize desired sensors.
  Future<void> start() async {
    if (_started) return;
    _started = true;
    final isMobile = defaultTargetPlatform == TargetPlatform.android ||
        defaultTargetPlatform == TargetPlatform.iOS;
    if (!isMobile) {
      // No sensors on desktop; emit a single "unsupported" value and stop.
      _ambientLuxController.add(-1);
      return;
    }
    _light = RealLightSensor();
    _lightSub = _light!.luxStream.listen((lux) {
      _lastLux = lux;
      _ambientLuxController.add(lux);
    });
  }

  Future<void> stop() async {
    _started = false;
    await _lightSub?.cancel();
    _lightSub = null;
    _light?.dispose();
    _light = null;
  }

  void dispose() {
    stop();
    _ambientLuxController.close();
  }
}
