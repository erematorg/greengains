import 'package:sensors_plus/sensors_plus.dart';

class MagnetometerSensor {
  Stream<List<double>> get magnetometerStream => magnetometerEventStream()
      .map((event) => <double>[event.x, event.y, event.z]);
}
