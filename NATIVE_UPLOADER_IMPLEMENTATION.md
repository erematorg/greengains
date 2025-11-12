# Native Background Uploader - Current State

This document tracks the production-ready Kotlin uploader that now handles every sensor upload on Android.

## Overview
- File: `android/app/src/main/kotlin/com/eremat/greengains/service/NativeBackendUploader.kt`
- Runs inside `ForegroundService`; unaffected by Flutter activity lifecycle.
- Captures the latest sensor snapshots every `NATIVE_SAMPLE_INTERVAL_MS` (default 10 s) and flushes to the backend every `uploadIntervalMs` (default 120 s).
- Uses OkHttp + Gson with a circular in-memory buffer (1 000 readings) and automatic retry + requeue on failure.
- Emits status callbacks (`onNativeUploadStatus`) so Flutter can show “last upload” timestamps without owning the network layer.

## Configuration Knobs
| Purpose | Location | Default | Notes |
| --- | --- | --- | --- |
| Backend base URL | `NativeBackendUploader.baseUrl` | `https://greengains.onrender.com` | Keep trailing `/upload` path. |
| API key | `NativeBackendUploader.apiKey` | `Vb9kS3tP0xQ7fY2L` | Pull from secure storage/CI for production builds. |
| Upload interval | `uploadIntervalMs` (constructor) | `120_000` | Lower for faster flushing; higher for battery savings. |
| Sampling interval | `ForegroundService.NATIVE_SAMPLE_INTERVAL_MS` | `10_000` | Governs how often we snapshot flows into the buffer. |
| Buffer cap | `maxBufferSize` | `1000` | Older readings are dropped in FIFO order if exceeded. |

## Verification Checklist
1. Run `flutter run` (or install APK) and toggle “Start Tracking” in the UI.
2. Tail logs: `adb logcat -s NativeBackendUploader ForegroundService`.
3. Expected log flow every ~2 minutes:  
   - `Uploading <N> readings to backend`  
   - `Upload succeeded...` **or** `Upload failed ... Network error` (automatic retry keeps buffered data).  
4. Swipe the app from recents; the notification stays, sensors keep running, and upload logs continue.
5. Reopen the app; the “Last upload” indicator reads from the same native callback (`ForegroundLocationService.uploadStatus`).
6. Confirm batches on the backend (Render/Supabase) to ensure payloads match expectations.

## Maintenance Tips
- To silence log noise, `postToFlutter` skips emission when `MethodChannel` is null (common after the activity dies). Additional throttling can be added there if necessary.
- If network failures persist, consider:  
  - Tweaking OkHttp timeouts (30 s by default).  
  - Adding exponential backoff inside `handleFailure`.  
  - Persisting batches to disk instead of RAM (e.g., `Room` or `MMKV`) for ultra-long offline windows.
- Remember iOS still lacks an equivalent; plan a separate background-task strategy when porting.

## Known Limitations
- OEM task killers can still stop even foreground services (notably on Xiaomi/Huawei). Testing on those devices is recommended.
- Extreme battery saver modes may pause timers; Android usually resumes our service once the device wakes.
- The uploader currently shares location only when `flutter.share_location` is `true`; update that preference from the Settings screen to opt users in/out.
