import 'dart:async';

/// Stub for SensorManager - will be properly implemented in Phase 2
/// This allows the codebase to compile while sensors are not yet configured
class SensorManager {
  SensorManager._();
  static final SensorManager instance = SensorManager._();

  // Stub streams - emit no data for now
  Stream<List<double>> get accelerometer$ => const Stream.empty();
  Stream<List<double>> get gyroscope$ => const Stream.empty();
  Stream<List<double>> get magnetometer$ => const Stream.empty();
  Stream<double> get ambientLux$ => const Stream.empty();

  Future<void> start() async {
    // Stub - will be implemented with native Android sensors in Phase 2
  }

  Future<void> stop() async {
    // Stub
  }
}
