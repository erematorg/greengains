# GreenGains Backend Reference

## Preserved Backend Files (for CC Web integration later)

### Core Backend
- `lib/services/network/backend_client.dart` - HTTP client for greengains.onrender.com
- `lib/services/network/sensor_uploader.dart` - Sensor batch upload logic
- `lib/services/network/upload_manager.dart` - WiFi/mobile data upload control

### Sensor Services
- `lib/services/sensors/sensor_manager.dart` - Accelerometer, gyro, magnetometer, light
- `lib/services/location/location_service.dart` - GPS location with permissions
- `lib/services/system/battery_service.dart` - Battery level/charging telemetry
- `lib/services/tracking/contribution_tracker.dart` - Geohash tile tracking

### Authentication
- `lib/services/auth/` - Firebase Auth integration
- Firebase config: `android/app/google-services.json`

### Preferences
- `lib/core/app_preferences.dart` - SharedPreferences wrapper
- `lib/services/preferences/preferences_service.dart`

### Theme
- `lib/core/themes.dart` - Material theme definitions
- `lib/core/theme_controller.dart` - Theme state management

## Backend Endpoint
- Production: `https://greengains.onrender.com`
- POST `/upload` - Batch sensor upload with geohash, location, battery

## Data Format
```json
{
  "device_id": "uuid",
  "timestamp": "ISO8601",
  "batch": [
    {
      "t": "ISO8601",
      "light": 123.45,
      "accel": [x, y, z],
      "gyro": [x, y, z],
      "magnet": [x, y, z]
    }
  ],
  "location": {"lat": 0, "lon": 0, "altitude": 0, "accuracy_m": 0},
  "geohash": "6char",
  "battery_level": 0.85,
  "is_charging": false
}
```

## Native Android Service (PROVEN WORKING)
- Source: `tmp/ForegroundServiceSamples/app/src/main/java/com/landomen/sample/foregroundservice/`
- Uses: Kotlin coroutines, Flow-based ticker, ServiceCompat.startForeground()
- No freezes in ANY scenario (background, lock, memory pressure)

## Next Steps for CC Web
1. Start with ForegroundServiceSamples native service base
2. Create minimal Flutter UI (single screen, start/stop button)
3. Integrate sensor collection triggered by native service
4. Add backend upload with these files
5. Gradually add features (location, battery, gamification)
