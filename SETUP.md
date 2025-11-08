# GreenGains Setup Instructions

## Phase 1 - Minimal UI (Current State)

This project has been rebuilt with a clean minimal UI. The backend services are intact, but sensor implementations have been removed and will be reimplemented in Phase 2.

### Prerequisites

- Flutter SDK (3.6.0 or higher)
- Android Studio / Xcode (for mobile development)
- Firebase CLI and FlutterFire CLI

### Initial Setup

1. **Install dependencies:**
   ```bash
   flutter pub get
   ```

2. **Configure Firebase:**

   The `firebase_options.dart` file is gitignored. You need to generate it:

   ```bash
   # Install FlutterFire CLI if you haven't
   dart pub global activate flutterfire_cli

   # Run configuration (this will create lib/firebase_options.dart)
   flutterfire configure
   ```

   If you just want to test the UI without Firebase, the stub file at `lib/firebase_options.dart`
   has been created locally with dummy credentials. **Do not commit this file.**

3. **Run the app:**
   ```bash
   flutter run
   ```

### Project Structure

```
lib/
├── main.dart                 # App entry point with Firebase & theme initialization
├── app_shell.dart            # Bottom navigation shell (Home/Settings)
├── firebase_options.dart     # Firebase config (gitignored - generate with flutterfire)
├── core/
│   ├── app_preferences.dart  # SharedPreferences wrapper
│   ├── theme_controller.dart # Theme state management
│   ├── themes.dart           # Material3 theme definitions
│   ├── constants.dart        # App constants
│   └── utils.dart           # Utility functions
├── screens/
│   ├── home_screen.dart      # Home screen with sensor status
│   └── settings_screen.dart  # Settings with auth & preferences
└── services/
    ├── auth/
    │   └── auth_service.dart       # Firebase auth with Google Sign-in
    ├── location/
    │   └── location_service.dart   # GPS location service (working)
    ├── network/
    │   ├── api_client.dart         # API client (working)
    │   ├── backend_client.dart     # Backend client (working)
    │   ├── upload_manager.dart     # Upload queue manager (working)
    │   └── sensor_uploader.dart    # Sensor data uploader (needs sensors in Phase 2)
    ├── sensors/
    │   └── sensor_manager.dart     # STUB - Phase 2 will implement native sensors
    ├── system/
    │   └── battery_service.dart    # STUB - Phase 2 will implement battery info
    └── tracking/
        └── contribution_tracker.dart # Contribution tracking (working)
```

### Features (Phase 1)

**Working:**
- Material3 UI with light/dark theme
- Bottom navigation (Home/Settings)
- Google Sign-in/Sign-out
- Theme persistence
- User preferences (location sharing, mobile data)
- Backend service integration (ready for Phase 2)

**Stubbed (Phase 2):**
- Sensor data collection (light, motion, location)
- Native Android foreground service
- Battery telemetry
- Real-time sensor displays

### Testing the UI

1. **Home Screen:**
   - Shows user info if signed in
   - Displays backend service status
   - Shows sensor placeholders (not functional yet)

2. **Settings Screen:**
   - Sign in with Google
   - Switch theme (Light/Dark/Auto)
   - Configure data preferences
   - View app info

### Known Issues

- `firebase_options.dart` is gitignored - you must generate it locally
- Sensors are stubbed and will not collect data
- Some imports in `sensor_uploader.dart` reference stub files

### Next Steps - Phase 2

Phase 2 will implement:
1. Native Android foreground service (using patterns from `tmp/ForegroundServiceSamples/`)
2. Light sensor (ambient lux readings)
3. Motion sensors (accelerometer, gyroscope)
4. Location service integration (GPS heatmaps)
5. MethodChannel bridge for Flutter ↔ Native communication
6. Proper lifecycle management (background, screen lock, app switching)

See the issue/PR description for detailed Phase 2 requirements.
