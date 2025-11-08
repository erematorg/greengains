# GreenGains Clean Rebuild Plan

## ✅ What's Ready

### Branch: `fresh-start` (pushed to GitHub)
Clean slate branch with:
- **BACKEND_REFERENCE.md** - All backend files, endpoints, data format documented
- **NATIVE_SERVICE_REFERENCE.kt** - Proven Kotlin service (NO freezes)
- Current GreenGains codebase intact (backend files ready to copy)

### Proven Native Service
Located: `C:\Users\mathi\Documents\GitHub\greengains\tmp\ForegroundServiceSamples`

**Tested and verified:**
- ✅ No freezes on background
- ✅ No freezes on lock screen (10+ minutes)
- ✅ No freezes on memory pressure
- ✅ Coroutine-based ticker keeps running
- ✅ START_STICKY ensures service persists

## 🎯 Claude Code Web Rebuild Strategy

### Phase 1: Native Service Foundation
1. **Start with ForegroundServiceSamples as base**
   - Copy entire `tmp/ForegroundServiceSamples` folder structure
   - Rename package from `com.landomen.sample.foregroundservice` → `com.eremat.greengains`
   - Keep ExampleForegroundService.kt as-is (it works!)

### Phase 2: Minimal Flutter Shell
1. Create basic Flutter app
2. Single screen with:
   - Start/Stop button
   - Service status indicator
   - Simple sensor display
3. MethodChannel to communicate with native service

### Phase 3: Backend Integration (from BACKEND_REFERENCE.md)
1. Copy backend files:
   - `lib/services/network/backend_client.dart`
   - `lib/services/network/sensor_uploader.dart`
   - `lib/services/sensors/sensor_manager.dart`
2. Trigger sensor collection from native service (like dev-rebuild)
3. Upload batches to `greengains.onrender.com/upload`

### Phase 4: Incremental Features
Add one at a time, test for freezes after each:
1. Location service + geohash
2. Battery telemetry
3. Contribution tracking
4. Firebase auth
5. Full UI (gamification, map, analytics)

## 📁 Files to Reference

### Must-Have Backend Files
```
lib/services/network/
├── backend_client.dart          # HTTP client
├── sensor_uploader.dart         # Upload logic (modify to use native triggers)
└── upload_manager.dart          # WiFi/mobile control

lib/services/sensors/
└── sensor_manager.dart          # Sensor streams

lib/services/location/
└── location_service.dart        # GPS with permissions

lib/services/system/
└── battery_service.dart         # Battery info

lib/core/
├── app_preferences.dart         # SharedPreferences
└── themes.dart                  # Material theme
```

### Native Service Pattern (from NATIVE_SERVICE_REFERENCE.kt)
```kotlin
private fun startPeriodicSensorTrigger() {
    tickerJob = coroutineScope.launch {
        tickerFlow(SENSOR_TRIGGER_INTERVAL, SENSOR_TRIGGER_INTERVAL)
            .collectLatest {
                triggerDartSensorCollection()
            }
    }
}

private suspend fun triggerDartSensorCollection() {
    withContext(Dispatchers.Main) {
        methodChannel?.invokeMethod("collectSensors", null)
    }
}
```

## 🚀 Quick Start Commands (for CC Web)

```bash
# Start fresh
cd C:\Users\mathi\Documents\GitHub\greengains
git checkout fresh-start

# Reference the proven service
cd tmp/ForegroundServiceSamples
# This is your Android base - copy the service structure

# Backend files are in master/dev-rebuild branches
git show master:lib/services/network/backend_client.dart
git show master:lib/services/sensors/sensor_manager.dart
```

## ⚠️ Critical: What NOT to Do
1. ❌ Don't use flutter_foreground_task plugin
2. ❌ Don't use Dart Timer.periodic for background work
3. ❌ Don't start from master/dev-rebuild (integration issues)
4. ✅ DO start from ForegroundServiceSamples native base
5. ✅ DO test for freezes after each feature addition
6. ✅ DO keep service lifecycle in native Kotlin

## 📊 Success Criteria
- [ ] Service runs 10+ minutes with screen locked
- [ ] No freezes when backgrounding app
- [ ] No freezes after heavy app usage
- [ ] Sensor uploads continue every 2 minutes
- [ ] Battery drain < 5% per hour
- [ ] Clean logcat (no errors/warnings)

## 🎁 What You Have Now
- **Branch:** `fresh-start` (clean slate, pushed to GitHub)
- **Reference docs:** Backend files, native service pattern
- **Proven service:** ForegroundServiceSamples (works perfectly)
- **Backend:** All files documented and ready to integrate
- **Endpoint:** greengains.onrender.com (production ready)

---

**Ready for Claude Code Web to rebuild from scratch with confidence!** 🚀
