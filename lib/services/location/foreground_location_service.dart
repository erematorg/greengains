import 'dart:async';
import 'dart:math';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import '../../core/app_preferences.dart';
import '../../core/events/app_events.dart';
import '../daily_pot_service.dart';
import '../tracking/tracking_session_manager.dart';

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

/// Model for pressure sensor data (hPa)
class PressureData {
  final double hPa;
  final int timestamp;

  PressureData({
    required this.hPa,
    required this.timestamp,
  });

  factory PressureData.fromMap(Map<dynamic, dynamic> map) {
    return PressureData(
      hPa: (map['hPa'] as num).toDouble(),
      timestamp: map['timestamp'] as int,
    );
  }

  DateTime get dateTime => DateTime.fromMillisecondsSinceEpoch(timestamp);

  @override
  String toString() {
    return 'PressureData(${hPa.toStringAsFixed(2)} hPa)';
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
  final _pressureController = StreamController<PressureData>.broadcast();
  final _isRunningNotifier = ValueNotifier<bool>(false);
  final _statsRefreshTrigger = ValueNotifier<int>(0);

  Stream<LocationData> get locationStream => _locationController.stream;
  Stream<LightData> get lightStream => _lightController.stream;
  Stream<AccelerometerData> get accelerometerStream => _accelerometerController.stream;
  Stream<GyroscopeData> get gyroscopeStream => _gyroscopeController.stream;
  Stream<PressureData> get pressureStream => _pressureController.stream;
  ValueListenable<bool> get isRunning => _isRunningNotifier;
  ValueListenable<int> get statsRefreshTrigger => _statsRefreshTrigger;

  LocationData? _lastLocation;
  LocationData? get lastLocation => _lastLocation;

  LightData? _lastLight;
  LightData? get lastLight => _lastLight;

  AccelerometerData? _lastAccelerometer;
  AccelerometerData? get lastAccelerometer => _lastAccelerometer;

  GyroscopeData? _lastGyroscope;
  GyroscopeData? get lastGyroscope => _lastGyroscope;

  PressureData? _lastPressure;
  PressureData? get lastPressure => _lastPressure;

  // Native upload status exposed to the UI
  final NativeUploadStatus uploadStatus = NativeUploadStatus();

  final _sessionManager = TrackingSessionManager.instance;

  ForegroundLocationService._() {
    _setupMethodCallHandler();
    unawaited(_bootstrapUploadStatus());
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
        case 'onPressureUpdate':
          final pressure = PressureData.fromMap(call.arguments as Map);
          _lastPressure = pressure;
          _pressureController.add(pressure);
          break;
        case 'collectSensors':
          // This is called periodically by the native service
          // We don't need to do anything here for now
          break;
        case 'onNativeUploadStatus':
          _handleNativeUploadStatus(call.arguments as Map);
          break;
        case 'onServiceStopped':
          _isRunningNotifier.value = false;
          uploadStatus.reset();
          // Stop tracking session (service was killed)
          await _sessionManager.stopSession(reason: 'service_stopped');
          debugPrint('Foreground service reported stopped');
          break;
      }
    });
  }

  bool _isChangingState = false;

  /// Start the foreground service
  Future<bool> start() async {
    if (_isChangingState) return false;
    _isChangingState = true;
    try {
      final result = await _fgChannel.invokeMethod<bool>('startForegroundService');
      if (result == true) {
        _isRunningNotifier.value = true;
        // Start tracking session in database
        await _sessionManager.startSession();
        debugPrint('Foreground location service started');
      }
      return result ?? false;
    } catch (e) {
      debugPrint('Error starting foreground service: $e');
      return false;
    } finally {
      _isChangingState = false;
    }
  }

  Future<void> requestLocationPermission() async {
    try {
      await _fgChannel.invokeMethod('requestLocationPermission');
    } catch (e) {
      debugPrint('Error requesting location permission: $e');
    }
  }

  /// Flush sensor FIFO buffers to get immediate data delivery
  /// Useful when user opens the app to show fresh sensor readings
  Future<bool> flushSensorBuffers() async {
    try {
      final result = await _fgChannel.invokeMethod<bool>('flushSensorBuffers');
      debugPrint('Sensor FIFO buffers flushed: ${result == true ? 'success' : 'failed'}');
      return result ?? false;
    } catch (e) {
      debugPrint('Error flushing sensor buffers: $e');
      return false;
    }
  }

  /// Stop the foreground service
  Future<bool> stop() async {
    if (_isChangingState) return false;
    _isChangingState = true;
    try {
      final result = await _fgChannel.invokeMethod<bool>('stopForegroundService');
      if (result == true) {
        _isRunningNotifier.value = false;
        _lastLocation = null;
        uploadStatus.reset();
        // Stop tracking session in database
        await _sessionManager.stopSession(reason: 'user_stopped');
        debugPrint('Foreground location service stopped');
      }
      return result ?? false;
    } catch (e) {
      debugPrint('Error stopping foreground service: $e');
      return false;
    } finally {
      _isChangingState = false;
    }
  }

  /// Check if the foreground service is currently running
  Future<bool> isServiceRunning() async {
    // If we are currently toggling the service (e.g. start() is in progress),
    // do not let this background check overwrite the state.
    if (_isChangingState) {
      return _isRunningNotifier.value;
    }

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
    _isRunningNotifier.value =
        AppPreferences.instance.foregroundServiceEnabled;
  }

  void _handleNativeUploadStatus(Map<dynamic, dynamic> data) {
    final status = data['status'] as String? ?? 'unknown';
    final timestampMs = (data['timestamp'] as num?)?.toInt();

    switch (status) {
      case 'started':
        uploadStatus.uploading.value = true;
        break;
      case 'success':
        uploadStatus.uploading.value = false;
        final timestamp = timestampMs != null
            ? DateTime.fromMillisecondsSinceEpoch(timestampMs)
            : DateTime.now();

        if (timestampMs != null) {
          uploadStatus.lastUpload.value = timestamp;
          // Note: Native code already saved timestamp to SharedPreferences
        }
        uploadStatus.uploadSuccess.value++;
        // Clear any previous errors on successful upload
        uploadStatus.lastError.value = null;
        uploadStatus.lastErrorTime.value = null;

        // Emit event for reactive UI updates (replaces _statsRefreshTrigger)
        final samplesCount = (data['batchSize'] as num?)?.toInt() ?? 0;
        AppEventBus.instance.emit(UploadSuccessEvent(
          samplesCount: samplesCount,
          timestamp: timestamp,
          geohash: null, // Can be added later if needed
        ));

        // Note: Native code already saved contribution to SQLite database
        // Record upload for daily pot progress (non-blocking)
        DailyPotService.instance.recordUpload().catchError((e) {
          debugPrint('Daily pot record upload failed (non-critical): $e');
        });

        // Record upload in tracking session (non-blocking)
        _sessionManager.recordUploadCompleted().catchError((e) {
          debugPrint('Session upload record failed (non-critical): $e');
        });
        break;
      case 'failure':
        uploadStatus.uploading.value = false;
        final error = data['error'] as String? ?? 'Unknown error';
        final failTimestamp = DateTime.now();
        uploadStatus.lastError.value = error;
        uploadStatus.lastErrorTime.value = failTimestamp;

        // Emit event for UI to react to failures
        AppEventBus.instance.emit(UploadFailedEvent(
          reason: error,
          timestamp: failTimestamp,
        ));

        debugPrint('Native upload failed: $error');
        break;
      default:
        debugPrint('Received unknown native upload status: $status');
    }
  }

  void dispose() {
    _locationController.close();
    _lightController.close();
    _accelerometerController.close();
    _gyroscopeController.close();
    _pressureController.close();
    _isRunningNotifier.dispose();
    uploadStatus.dispose();
  }
}

class NativeUploadStatus {
  final ValueNotifier<bool> uploading = ValueNotifier<bool>(false);
  final ValueNotifier<DateTime?> lastUpload = ValueNotifier<DateTime?>(null);
  final ValueNotifier<int> uploadSuccess = ValueNotifier<int>(0);
  final ValueNotifier<String?> lastError = ValueNotifier<String?>(null);
  final ValueNotifier<DateTime?> lastErrorTime = ValueNotifier<DateTime?>(null);

  void reset() {
    uploading.value = false;
  }

  void dispose() {
    uploading.dispose();
    lastUpload.dispose();
    uploadSuccess.dispose();
    lastError.dispose();
    lastErrorTime.dispose();
  }
}
