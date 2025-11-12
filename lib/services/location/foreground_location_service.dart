import 'dart:async';
import 'dart:math';
import 'package:dart_geohash/dart_geohash.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:uuid/uuid.dart';

import '../../core/app_preferences.dart';
import '../../data/local/database_helper.dart';
import '../tracking/contribution_tracker.dart';

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

/// Model for accelerometer data (acceleration in m/s²)
class AccelerometerData {
  final double x;
  final double y;
  final double z;
  final int timestamp;

  AccelerometerData({
    required this.x,
    required this.y,
    required this.z,
    required this.timestamp,
  });

  factory AccelerometerData.fromMap(Map<dynamic, dynamic> map) {
    return AccelerometerData(
      x: (map['x'] as num).toDouble(),
      y: (map['y'] as num).toDouble(),
      z: (map['z'] as num).toDouble(),
      timestamp: map['timestamp'] as int,
    );
  }

  DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(timestamp);

  /// Calculate magnitude (total acceleration)
  double get magnitude => sqrt(x * x + y * y + z * z);

  @override
  String toString() {
    return 'AccelerometerData(${magnitude.toStringAsFixed(1)} m/s²)';
  }
}

/// Model for gyroscope data (rotation rate in rad/s)
class GyroscopeData {
  final double x;
  final double y;
  final double z;
  final int timestamp;

  GyroscopeData({
    required this.x,
    required this.y,
    required this.z,
    required this.timestamp,
  });

  factory GyroscopeData.fromMap(Map<dynamic, dynamic> map) {
    return GyroscopeData(
      x: (map['x'] as num).toDouble(),
      y: (map['y'] as num).toDouble(),
      z: (map['z'] as num).toDouble(),
      timestamp: map['timestamp'] as int,
    );
  }

  DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(timestamp);

  /// Calculate magnitude (total rotation rate)
  double get magnitude => sqrt(x * x + y * y + z * z);

  @override
  String toString() {
    return 'GyroscopeData(${magnitude.toStringAsFixed(2)} rad/s)';
  }
}

/// Service for managing the native Android foreground service for sensor data collection
class ForegroundLocationService {
  static const _fgChannel = MethodChannel('greengains/foreground');
  static const _sensorTriggerChannel = MethodChannel('greengains/sensor_trigger');

  final _locationController = StreamController<LocationData>.broadcast();
  final _lightController = StreamController<LightData>.broadcast();
  final _accelerometerController = StreamController<AccelerometerData>.broadcast();
  final _gyroscopeController = StreamController<GyroscopeData>.broadcast();
  final _isRunningNotifier = ValueNotifier<bool>(false);

  Stream<LocationData> get locationStream => _locationController.stream;
  Stream<LightData> get lightStream => _lightController.stream;
  Stream<AccelerometerData> get accelerometerStream => _accelerometerController.stream;
  Stream<GyroscopeData> get gyroscopeStream => _gyroscopeController.stream;
  ValueListenable<bool> get isRunning => _isRunningNotifier;

  LocationData? _lastLocation;
  LocationData? get lastLocation => _lastLocation;

  LightData? _lastLight;
  LightData? get lastLight => _lastLight;

  AccelerometerData? _lastAccelerometer;
  AccelerometerData? get lastAccelerometer => _lastAccelerometer;

  GyroscopeData? _lastGyroscope;
  GyroscopeData? get lastGyroscope => _lastGyroscope;

  // Native upload status exposed to the UI
  final NativeUploadStatus uploadStatus = NativeUploadStatus();
  final GeoHasher _geoHasher = GeoHasher();
  final Uuid _uuid = const Uuid();

  ForegroundLocationService._() {
    _setupMethodCallHandler();
    unawaited(_bootstrapUploadStatus());
    unawaited(ContributionTracker.instance.initialize());
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
        case 'onAccelerometerUpdate':
          final accel = AccelerometerData.fromMap(call.arguments as Map);
          _lastAccelerometer = accel;
          _accelerometerController.add(accel);
          break;
        case 'onGyroscopeUpdate':
          final gyro = GyroscopeData.fromMap(call.arguments as Map);
          _lastGyroscope = gyro;
          _gyroscopeController.add(gyro);
          break;
      case 'collectSensors':
        // This is called periodically by the native service
        // We don't need to do anything here for now
        break;
      case 'onNativeUploadStatus':
        await _handleNativeUploadStatus(call.arguments as Map);
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
        uploadStatus.reset();
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

  Future<void> _bootstrapUploadStatus() async {
    await AppPreferences.instance.ensureInitialized();
    uploadStatus.lastUpload.value = AppPreferences.instance.lastUploadAt;
  }

  Future<void> _handleNativeUploadStatus(Map<dynamic, dynamic> data) async {
    final status = data['status'] as String? ?? 'unknown';
    final timestampMs = (data['timestamp'] as num?)?.toInt();
    final batchSize = (data['batchSize'] as num?)?.toInt() ?? 0;

    switch (status) {
      case 'started':
        uploadStatus.uploading.value = true;
        break;
      case 'success':
        uploadStatus.uploading.value = false;
        if (timestampMs != null) {
          final ts = DateTime.fromMillisecondsSinceEpoch(timestampMs);
          uploadStatus.lastUpload.value = ts;
          await AppPreferences.instance.ensureInitialized();
          await AppPreferences.instance.setLastUploadAt(ts);
        }
        uploadStatus.uploadSuccess.value++;
        await _saveContribution(samplesCount: batchSize);
        break;
      case 'failure':
        uploadStatus.uploading.value = false;
        final error = data['error'];
        debugPrint('Native upload failed: $error');
        break;
      default:
        debugPrint('Received unknown native upload status: $status');
    }
  }

  Future<void> _saveContribution({required int samplesCount}) async {
    if (samplesCount <= 0) {
      return;
    }

    try {
      final geohash = _computeCurrentGeohash();
      final now = DateTime.now();
      await DatabaseHelper.instance.insertContribution(
        id: _uuid.v4(),
        timestamp: now,
        samplesCount: samplesCount,
        geohash: geohash,
        success: true,
      );

      if (geohash != null) {
        await ContributionTracker.instance.initialize();
        unawaited(ContributionTracker.instance.recordTile(geohash, now));
      }
    } catch (e) {
      debugPrint('Failed to save contribution: $e');
    }
  }

  String? _computeCurrentGeohash() {
    final loc = _lastLocation;
    if (loc == null) {
      return null;
    }

    try {
      return _geoHasher.encode(
        loc.longitude,
        loc.latitude,
        precision: 6,
      );
    } catch (e) {
      debugPrint('Failed to compute geohash: $e');
      return null;
    }
  }

  void dispose() {
    _locationController.close();
    _lightController.close();
    _isRunningNotifier.dispose();
    uploadStatus.dispose();
  }
}

class NativeUploadStatus {
  final ValueNotifier<bool> uploading = ValueNotifier<bool>(false);
  final ValueNotifier<DateTime?> lastUpload = ValueNotifier<DateTime?>(null);
  final ValueNotifier<int> uploadSuccess = ValueNotifier<int>(0);

  void reset() {
    uploading.value = false;
  }

  void dispose() {
    uploading.dispose();
    lastUpload.dispose();
    uploadSuccess.dispose();
  }
}
