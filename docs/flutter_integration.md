## Flutter Backend Integration Guide

### HTTP Client Setup
- Add `http: ^1.2.1` (or your preferred client) to `pubspec.yaml` if not already present.
- Keep the backend base URL in one place (e.g. using `const String kBackendBaseUrl = String.fromEnvironment('BACKEND_URL', defaultValue: 'http://127.0.0.1:8000');`).
- Provide the API key via Dart environment flag or secure storage: `const String kBackendApiKey = String.fromEnvironment('BACKEND_API_KEY', defaultValue: 'Vb9kS3tP0xQ7fY2L');`.

### In-App Uploader
- `SensorUploader` now runs from `HomeScreen` and batches readings every ~10s, auto-flushing once 12 samples (~2 minutes) or 2 minutes of data accumulate.
- It respects Wi-Fi/mobile preferences (`UploadManager`) and falls back to plain JSON payloads.

### Sample Uploader
```dart
import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;

class BackendUploader {
  BackendUploader({
    http.Client? client,
    this.baseUrl = kBackendBaseUrl,
    this.apiKey = kBackendApiKey,
  }) : _client = client ?? http.Client();

  final http.Client _client;
  final String baseUrl;
  final String apiKey;

  Future<void> sendBatch(Map<String, dynamic> batch) async {
    final uri = Uri.parse('$baseUrl/upload');
    final response = await _client.post(
      uri,
      headers: <String, String>{
        HttpHeaders.contentTypeHeader: 'application/json',
        'X-API-Key': apiKey,
      },
      body: jsonEncode(batch),
    );

    if (response.statusCode != 202) {
      throw HttpException(
        'Upload failed with status ${response.statusCode}: ${response.body}',
        uri: uri,
      );
    }
  }
}
```

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
