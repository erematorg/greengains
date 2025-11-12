## Flutter Backend Integration Guide

### HTTP Client Setup
- Add `http: ^1.2.1` (or your preferred client) to `pubspec.yaml` if not already present.
- Keep the backend base URL in one place (e.g. using `const String kBackendBaseUrl = String.fromEnvironment('BACKEND_URL', defaultValue: 'http://127.0.0.1:8000');`).
- Provide the API key via Dart environment flag or secure storage: `const String kBackendApiKey = String.fromEnvironment('BACKEND_API_KEY', defaultValue: 'Vb9kS3tP0xQ7fY2L');`.

### Native Uploader
- Sensor batching + HTTP uploads now live in `android/app/src/main/kotlin/com/eremat/greengains/service/NativeBackendUploader.kt`.
- The uploader runs inside the Android foreground service, so uploads continue even if Flutter is killed.
- Adjust backend URL/API key there (defaults still read from `https://greengains.onrender.com` + `Vb9kS3tP0xQ7fY2L`).
- Flutter only listens for `onNativeUploadStatus` events; no Dart HTTP layer is required for sensor data anymore.

### Gzip Compression (Optional)
- Wrap the JSON body in `gzip.encode(utf8.encode(jsonString))`.
- Set `headers['Content-Encoding'] = 'gzip'`.
- Only enable once plain JSON uploads succeed.

### Testing Checklist
- Run the emulator/device with backend running locally (`uvicorn api.main:app --reload`).
- Perform an upload and confirm `202` response.
- Verify new rows appear in Supabase `sensor_batches`.
- Add retry/backoff on the Flutter side in case the network is flaky.

### Environment Variables (Flutter)
- When building the app pass `--dart-define=BACKEND_URL=https://your-domain`
  and `--dart-define=BACKEND_API_KEY=your-prod-key`.
- Keep dev/test keys separate from production.
