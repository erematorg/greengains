import 'dart:async';
import 'dart:developer' as developer;

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/foundation.dart';
import 'package:geolocator/geolocator.dart';

import '../../core/app_preferences.dart';
import '../sensors/sensor_manager.dart';
import '../location/location_service.dart';
import '../system/battery_service.dart';
import 'backend_client.dart';
import 'upload_manager.dart';
import 'package:dart_geohash/dart_geohash.dart';

const _logTag = 'SensorUploader';

class SensorUploader {
  SensorUploader({
    SensorManager? sensorManager,
    BackendClient? backendClient,
    Connectivity? connectivity,
    Duration sampleInterval = const Duration(seconds: 10),
    int maxBatchSize = 12,
    Duration maxBatchAge = const Duration(minutes: 2),
    bool compressPayload = false,
  })  : _sensorManager = sensorManager ?? SensorManager.instance,
        _backendClient = backendClient ?? BackendClient(),
        _connectivity = connectivity ?? Connectivity(),
        _sampleInterval = sampleInterval,
        _maxBatchSize = maxBatchSize,
        _maxBatchAge = maxBatchAge,
        _compressPayload = compressPayload;

  final SensorManager _sensorManager;
  final BackendClient _backendClient;
  final Connectivity _connectivity;
  final Duration _sampleInterval;
  final int _maxBatchSize;
  final Duration _maxBatchAge;
  final bool _compressPayload;

  Timer? _ticker;
  StreamSubscription<List<double>>? _accelSub;
  StreamSubscription<List<double>>? _gyroSub;
  StreamSubscription<List<double>>? _magSub;
  StreamSubscription<double>? _luxSub;
  StreamSubscription<ConnectivityResult>? _connectivitySub;

  List<double>? _lastAccel;
  List<double>? _lastGyro;
  List<double>? _lastMagnet;
  double? _lastLux;
  bool _isWifiConnected = false;
  bool _uploadInFlight = false;
  bool _started = false;

  final List<Map<String, dynamic>> _buffer = [];
  DateTime? _batchStart;
  String? _deviceId;

  final ValueNotifier<bool> _uploadingNotifier = ValueNotifier<bool>(false);
  ValueListenable<bool> get uploading => _uploadingNotifier;

  Future<void> start() async {
    if (_started) {
      return;
    }
    _started = true;
    await _sensorManager.start();
    await _primeConnectivity();

    _accelSub = _sensorManager.accelerometer$.listen((values) {
      _lastAccel = values;
    });
    _gyroSub = _sensorManager.gyroscope$.listen((values) {
      _lastGyro = values;
    });
    _magSub = _sensorManager.magnetometer$.listen((values) {
      _lastMagnet = values;
    });
    _luxSub = _sensorManager.ambientLux$.listen((lux) {
      _lastLux = lux < 0 ? null : lux;
    });

    _connectivitySub =
        _connectivity.onConnectivityChanged.listen(_handleConnectivity);

    _ticker = Timer.periodic(_sampleInterval, (_) {
      unawaited(_collectReading());
    });
  }

  Future<void> _primeConnectivity() async {
    final result = await _connectivity.checkConnectivity();
    _handleConnectivity(result);
  }

  void _handleConnectivity(ConnectivityResult result) {
    _isWifiConnected = result == ConnectivityResult.wifi ||
        result == ConnectivityResult.ethernet;
  }

  Future<void> _collectReading() async {
    if (_uploadInFlight) {
      return;
    }
    final accel = _lastAccel;
    final gyro = _lastGyro;
    final magnet = _lastMagnet;
    final lux = _lastLux;

    if (accel == null || gyro == null || magnet == null || lux == null) {
      return;
    }

    final timestamp = DateTime.now().toUtc();
    _buffer.add({
      't': timestamp.toIso8601String(),
      'light': lux,
      'accel': List<double>.from(accel),
      'gyro': List<double>.from(gyro),
      'magnet': List<double>.from(magnet),
    });
    _batchStart ??= timestamp;

    final batchAge = timestamp.difference(_batchStart!);
    final shouldFlush =
        _buffer.length >= _maxBatchSize || batchAge >= _maxBatchAge;

    if (shouldFlush) {
      await _flushBuffer();
    }
  }

  Future<void> flushNow() => _flushBuffer(force: true);

  Future<void> _flushBuffer({bool force = false}) async {
    if (_buffer.isEmpty || _uploadInFlight) {
      return;
    }

    _uploadInFlight = true;
    _uploadingNotifier.value = true;
    try {
      _deviceId ??= await AppPreferences.instance.getOrCreateDeviceId();

      // Try to get location if user preferences allow (defaults to FALSE)
      // Uses FINE location (~10-50m GPS accuracy) for smart city data
      Map<String, dynamic>? location;
      String? geohash;
      try {
        if (AppPreferences.instance.shareLocation) {
          // Automatically request permission if not granted yet
          final hasPermission = await LocationService.instance.checkPermission();
          if (hasPermission == LocationPermission.denied ||
              hasPermission == LocationPermission.deniedForever) {
            developer.log('Location permission not granted, trying to request', name: _logTag);
            await LocationService.instance.requestLocation();
          }

          // Try to get location (silently fails if permission denied)
          location = await LocationService.instance.getLocation();
          if (location != null) {
            developer.log(
              'GPS location included: ${location['lat']}, ${location['lon']}, ${location['altitude']}m (accuracy: ${location['accuracy_m']}m)',
              name: _logTag,
            );

            // Compute GeoHash for privacy (6 characters = ~1.2km grid, like Nodle Cash)
            final geoHasher = GeoHasher();
            geohash = geoHasher.encode(location['lon'] as double, location['lat'] as double, precision: 6);
            developer.log('GeoHash computed: $geohash (~1.2km precision)', name: _logTag);
          }
        }
      } catch (e) {
        // Location fetch failed - continue upload without it
        developer.log('Failed to get location, continuing without it: $e', name: _logTag);
      }

      // Collect battery telemetry (like Nodle Cash)
      Map<String, dynamic>? battery;
      try {
        battery = await BatteryService.instance.getBatteryInfo();
      } catch (e) {
        developer.log('Failed to get battery info: $e', name: _logTag);
      }

      final payload = {
        'device_id': _deviceId,
        'timestamp': DateTime.now().toUtc().toIso8601String(),
        'batch': List<Map<String, dynamic>>.from(_buffer),
        if (location != null) 'location': location,  // Optional: GPS location
        if (geohash != null) 'geohash': geohash,  // Optional: Privacy-preserving grid
        if (battery != null) ...battery,  // Optional: battery_level, is_charging
      };

      final uploaded = await UploadManager.uploadPendingBatches(
        isWifiConnected: _isWifiConnected,
        uploader: () async {
          await _backendClient.uploadBatch(
            payload,
            compress: _compressPayload,
          );
        },
      );

      if (uploaded) {
        _buffer.clear();
        _batchStart = null;
      } else {
        // Keep recent samples so memory stays bounded; older entries fall off.
        if (_buffer.length > _maxBatchSize) {
          _buffer.removeRange(0, _buffer.length - _maxBatchSize);
        }
        _batchStart = _buffer.isNotEmpty
            ? DateTime.parse(_buffer.first['t'] as String)
            : null;
      }
    } catch (_) {
      // Keep buffer for retry on failure, but trim if it grows too large.
      if (_buffer.length > _maxBatchSize * 3) {
        _buffer.removeRange(0, _buffer.length - (_maxBatchSize * 3));
      }
    } finally {
      _uploadInFlight = false;
      _uploadingNotifier.value = false;
    }
  }

  Future<void> stop() async {
    _ticker?.cancel();
    _ticker = null;
    await _accelSub?.cancel();
    await _gyroSub?.cancel();
    await _magSub?.cancel();
    await _luxSub?.cancel();
    await _connectivitySub?.cancel();
    _started = false;
  }

  Future<void> dispose() async {
    await stop();
    await flushNow();
    _backendClient.dispose();
    _uploadingNotifier.dispose();
  }
}
