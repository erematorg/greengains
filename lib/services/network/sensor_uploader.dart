import 'dart:async';
import 'dart:developer' as developer;

import 'package:connectivity_plus/connectivity_plus.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:geolocator/geolocator.dart';

import '../../core/app_preferences.dart';
import '../location/foreground_location_service.dart';
import '../system/battery_service.dart';
import '../tracking/contribution_tracker.dart';
import 'backend_client.dart';
import 'upload_manager.dart';
import 'package:dart_geohash/dart_geohash.dart';

const _logTag = 'SensorUploader';

class SensorUploader {
  static const _sensorTriggerChannel = MethodChannel('greengains/sensor_trigger');
  SensorUploader({
    ForegroundLocationService? locationService,
    BackendClient? backendClient,
    Connectivity? connectivity,
    Duration sampleInterval = const Duration(seconds: 10),
    int maxBatchSize = 12,
    Duration maxBatchAge = const Duration(minutes: 2),
    bool compressPayload = false,
  })  : _locationService = locationService ?? ForegroundLocationService.instance,
        _backendClient = backendClient ?? BackendClient(),
        _connectivity = connectivity ?? Connectivity(),
        _sampleInterval = sampleInterval,
        _maxBatchSize = maxBatchSize,
        _maxBatchAge = maxBatchAge,
        _compressPayload = compressPayload;

  final ForegroundLocationService _locationService;
  final BackendClient _backendClient;
  final Connectivity _connectivity;
  final Duration _sampleInterval;
  final int _maxBatchSize;
  final Duration _maxBatchAge;
  final bool _compressPayload;

  StreamSubscription<LocationData>? _locationSub;
  StreamSubscription<LightData>? _lightSub;
  StreamSubscription<AccelerometerData>? _accelSub;
  StreamSubscription<GyroscopeData>? _gyroSub;
  StreamSubscription<ConnectivityResult>? _connectivitySub;

  LocationData? _lastLocation;
  LightData? _lastLight;
  AccelerometerData? _lastAccel;
  GyroscopeData? _lastGyro;
  bool _isWifiConnected = false;
  bool _uploadInFlight = false;
  bool _started = false;
  Timer? _collectionTimer;

  final List<Map<String, dynamic>> _buffer = [];
  DateTime? _batchStart;
  String? _deviceId;

  final ValueNotifier<bool> _uploadingNotifier = ValueNotifier<bool>(false);
  final ValueNotifier<DateTime?> _lastUploadNotifier =
      ValueNotifier<DateTime?>(null);
  final ValueNotifier<ConnectivityResult> _connectivityStatusNotifier =
      ValueNotifier<ConnectivityResult>(ConnectivityResult.none);

  ValueListenable<bool> get uploading => _uploadingNotifier;
  ValueListenable<DateTime?> get lastUpload => _lastUploadNotifier;
  ValueListenable<ConnectivityResult> get connectivityStatus =>
      _connectivityStatusNotifier;

  Future<void> start() async {
    if (_started) {
      return;
    }
    _started = true;
    await AppPreferences.instance.ensureInitialized();
    _lastUploadNotifier.value = AppPreferences.instance.lastUploadAt;
    await _primeConnectivity();

    // Listen to native ForegroundService sensor streams
    _locationSub = _locationService.locationStream.listen((location) {
      _lastLocation = location;
    });
    _lightSub = _locationService.lightStream.listen((light) {
      _lastLight = light;
    });
    _accelSub = _locationService.accelerometerStream.listen((accel) {
      _lastAccel = accel;
    });
    _gyroSub = _locationService.gyroscopeStream.listen((gyro) {
      _lastGyro = gyro;
    });

    _connectivitySub =
        _connectivity.onConnectivityChanged.listen(_handleConnectivity);

    // Periodically collect sensor readings for batching and upload
    _collectionTimer = Timer.periodic(_sampleInterval, (_) async {
      await _collectReading();
    });

    developer.log('Started listening for native sensor streams', name: _logTag);
  }

  Future<void> _primeConnectivity() async {
    final result = await _connectivity.checkConnectivity();
    _handleConnectivity(result);
  }

  void _handleConnectivity(ConnectivityResult result) {
    _isWifiConnected = result == ConnectivityResult.wifi ||
        result == ConnectivityResult.ethernet;
    _connectivityStatusNotifier.value = result;
  }

  Future<void> _collectReading() async {
    if (_uploadInFlight) {
      return;
    }
    final accel = _lastAccel;
    final gyro = _lastGyro;
    final light = _lastLight;

    // Need at least light + one motion sensor to upload
    if (light == null || (accel == null && gyro == null)) {
      return;
    }

    final timestamp = DateTime.now().toUtc();
    final sample = <String, dynamic>{
      't': timestamp.toIso8601String(),
      'light': light.lux,
    };

    // Add accelerometer if available
    if (accel != null) {
      sample['accel'] = [accel.x, accel.y, accel.z];
    }

    // Add gyroscope if available
    if (gyro != null) {
      sample['gyro'] = [gyro.x, gyro.y, gyro.z];
    }

    _buffer.add(sample);
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

      // Use location from ForegroundService if available and user allowed
      Map<String, dynamic>? location;
      String? geohash;
      try {
        if (AppPreferences.instance.shareLocation && _lastLocation != null) {
          final loc = _lastLocation!;
          location = {
            'lat': loc.latitude,
            'lon': loc.longitude,
            'accuracy_m': loc.accuracy,
            if (loc.altitude != null) 'altitude': loc.altitude,
            if (loc.speed != null) 'speed_mps': loc.speed,
            if (loc.bearing != null) 'bearing_deg': loc.bearing,
          };

          developer.log(
            'GPS location included: ${loc.latitude}, ${loc.longitude} (accuracy: ${loc.accuracy}m)',
            name: _logTag,
          );

          // Compute GeoHash for privacy (6 characters = ~1.2km grid, like Nodle Cash)
          final geoHasher = GeoHasher();
          geohash = geoHasher.encode(
              loc.longitude, loc.latitude,
              precision: 6);
          developer.log('GeoHash computed: $geohash (~1.2km precision)',
              name: _logTag);

          // Track tile for gamification (local only)
          unawaited(
            ContributionTracker.instance.recordTile(geohash!, DateTime.now())
          );
        }
      } catch (e) {
        // Location processing failed - continue upload without it
        developer.log('Failed to process location, continuing without it: $e',
            name: _logTag);
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
        if (location != null) 'location': location, // Optional: GPS location
        if (geohash != null)
          'geohash': geohash, // Optional: Privacy-preserving grid
        if (battery != null) ...battery, // Optional: battery_level, is_charging
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
        final now = DateTime.now();
        _lastUploadNotifier.value = now;
        await AppPreferences.instance.setLastUploadAt(now);
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
    _collectionTimer?.cancel();
    _collectionTimer = null;
    await _locationSub?.cancel();
    await _lightSub?.cancel();
    await _accelSub?.cancel();
    await _gyroSub?.cancel();
    await _connectivitySub?.cancel();
    _started = false;
  }

  Future<void> dispose() async {
    await stop();
    await flushNow();
    _backendClient.dispose();
    _uploadingNotifier.dispose();
    _lastUploadNotifier.dispose();
    _connectivityStatusNotifier.dispose();
  }
}
