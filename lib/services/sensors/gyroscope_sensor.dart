import 'package:sensors_plus/sensors_plus.dart';

class GyroscopeSensor {
  Stream<List<double>> get gyroscopeStream =>
      gyroscopeEventStream().map((event) => <double>[event.x, event.y, event.z]);
}

