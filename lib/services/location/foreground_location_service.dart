import 'dart:async';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

/// Model for location data received from the native foreground service
class LocationData {
  final double latitude;
  final double longitude;
  final double accuracy;
  final double? altitude;
  final double? speed;
  final double? bearing;
  final int timestamp;
  final String? provider;

  LocationData({
    required this.latitude,
    required this.longitude,
    required this.accuracy,
    this.altitude,
    this.speed,
    this.bearing,
    required this.timestamp,
    this.provider,
  });

  factory LocationData.fromMap(Map<dynamic, dynamic> map) {
    return LocationData(
      latitude: (map['latitude'] as num).toDouble(),
      longitude: (map['longitude'] as num).toDouble(),
      accuracy: (map['accuracy'] as num).toDouble(),
      altitude: map['altitude'] != null ? (map['altitude'] as num).toDouble() : null,
      speed: map['speed'] != null ? (map['speed'] as num).toDouble() : null,
      bearing: map['bearing'] != null ? (map['bearing'] as num).toDouble() : null,
      timestamp: map['timestamp'] as int,
      provider: map['provider'] as String?,
    );
  }

  DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(timestamp);

  @override
  String toString() {
    return 'LocationData(lat: $latitude, lon: $longitude, accuracy: ${accuracy.toStringAsFixed(1)}m, provider: $provider)';
  }
}

/// Model for light sensor data (ambient light in lux)
class LightData {
  final double lux;
  final int timestamp;

  LightData({
    required this.lux,
    required this.timestamp,
  });

  factory LightData.fromMap(Map<dynamic, dynamic> map) {
    return LightData(
      lux: (map['lux'] as num).toDouble(),
      timestamp: map['timestamp'] as int,
    );
  }

  DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(timestamp);

  @override
  String toString() {
    return 'LightData(${lux.toStringAsFixed(0)} lux)';
  }
}

/// Service for managing the native Android foreground service for sensor data collection
class ForegroundLocationService {
  static const _fgChannel = MethodChannel('greengains/foreground');
  static const _sensorTriggerChannel = MethodChannel('greengains/sensor_trigger');

  final _locationController = StreamController<LocationData>.broadcast();
  final _lightController = StreamController<LightData>.broadcast();
  final _isRunningNotifier = ValueNotifier<bool>(false);

  Stream<LocationData> get locationStream => _locationController.stream;
  Stream<LightData> get lightStream => _lightController.stream;
  ValueListenable<bool> get isRunning => _isRunningNotifier;

  LocationData? _lastLocation;
  LocationData? get lastLocation => _lastLocation;

  LightData? _lastLight;
  LightData? get lastLight => _lastLight;

  ForegroundLocationService._() {
    _setupMethodCallHandler();
  }

  static final ForegroundLocationService instance = ForegroundLocationService._();

  void _setupMethodCallHandler() {
    _sensorTriggerChannel.setMethodCallHandler((call) async {
      switch (call.method) {
        case 'onLocationUpdate':
          final location = LocationData.fromMap(call.arguments as Map);
          _lastLocation = location;
          _locationController.add(location);
          break;
        case 'onLightUpdate':
          final light = LightData.fromMap(call.arguments as Map);
          _lastLight = light;
          _lightController.add(light);
          break;
        case 'collectSensors':
          // This is called periodically by the native service
          // We don't need to do anything here for now
          break;
      }
    });
  }

  /// Start the foreground service
  Future<bool> start() async {
    try {
      final result = await _fgChannel.invokeMethod<bool>('startForegroundService');
      if (result == true) {
        _isRunningNotifier.value = true;
        debugPrint('Foreground location service started');
      }
      return result ?? false;
    } catch (e) {
      debugPrint('Error starting foreground service: $e');
      return false;
    }
  }

  /// Stop the foreground service
  Future<bool> stop() async {
    try {
      final result = await _fgChannel.invokeMethod<bool>('stopForegroundService');
      if (result == true) {
        _isRunningNotifier.value = false;
        _lastLocation = null;
        debugPrint('Foreground location service stopped');
      }
      return result ?? false;
    } catch (e) {
      debugPrint('Error stopping foreground service: $e');
      return false;
    }
  }

  /// Check if the foreground service is currently running
  Future<bool> isServiceRunning() async {
    try {
      final result = await _fgChannel.invokeMethod<bool>('isForegroundServiceRunning');
      _isRunningNotifier.value = result ?? false;
      return result ?? false;
    } catch (e) {
      debugPrint('Error checking service status: $e');
      return false;
    }
  }

  void dispose() {
    _locationController.close();
    _lightController.close();
    _isRunningNotifier.dispose();
  }
}
