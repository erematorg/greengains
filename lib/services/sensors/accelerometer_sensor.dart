import 'dart:async';
import 'package:sensors_plus/sensors_plus.dart';

class AccelerometerSensor {
  Stream<List<double>> get accelerometerStream =>
      accelerometerEventStream().map((event) => <double>[event.x, event.y, event.z]);
}
