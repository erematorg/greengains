import 'dart:async';
import 'package:light/light.dart';

/// Real ambient light sensor implementation using the `light` plugin.
/// Exposes a stream of lux values as doubles via `luxStream`.
class RealLightSensor {
  final _controller = StreamController<double>.broadcast();
  StreamSubscription<int>? _subscription;

  /// Lux values stream.
  Stream<double> get luxStream => _controller.stream;

  RealLightSensor() {
    try {
      final stream = Light().lightSensorStream; // Stream<int>
      _subscription = stream.listen(
        (value) {
          final v = value.toDouble();
          if (v < 0) {
            // -1 => no sensor or unavailable
            _controller.add(-1);
          } else {
            _controller.add(v);
          }
        },
        onError: (e) {
          // Error accessing the sensor
          _controller.add(-1);
        },
        cancelOnError: false,
      );
    } catch (_) {
      // Device without sensor or access not possible
      _controller.add(-1);
    }
  }

  void dispose() {
    _subscription?.cancel();
    _controller.close();
  }
}
