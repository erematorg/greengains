import 'dart:convert';
import 'dart:io';

import 'package:http/http.dart' as http;

const String kBackendBaseUrl = String.fromEnvironment(
  'BACKEND_URL',
  defaultValue: 'http://127.0.0.1:8000',
);

const String kBackendApiKey = String.fromEnvironment(
  'BACKEND_API_KEY',
  defaultValue: 'Vb9kS3tP0xQ7fY2L',
);

class BackendClient {
  BackendClient({
    http.Client? client,
    String? baseUrl,
    String? apiKey,
  })  : _client = client ?? http.Client(),
        _baseUrl = baseUrl ?? kBackendBaseUrl,
        _apiKey = apiKey ?? kBackendApiKey;

  final http.Client _client;
  final String _baseUrl;
  final String _apiKey;

  String get baseUrl => _baseUrl;
  String get apiKey => _apiKey;

  Uri get _uploadUri => Uri.parse('$_baseUrl/upload');

  Future<void> uploadBatch(
    Map<String, dynamic> payload, {
    bool compress = false,
  }) async {
    final jsonBody = jsonEncode(payload);
    final headers = <String, String>{
      HttpHeaders.contentTypeHeader: 'application/json',
      'X-API-Key': _apiKey,
    };
    final bodyBytes =
        compress ? gzip.encode(utf8.encode(jsonBody)) : utf8.encode(jsonBody);
    if (compress) {
      headers[HttpHeaders.contentEncodingHeader] = 'gzip';
    }

    final response = await _client.post(
      _uploadUri,
      headers: headers,
      body: bodyBytes,
    );

    if (response.statusCode != 202) {
      throw BackendException(
        'Upload failed with status ${response.statusCode}: ${response.body}',
        statusCode: response.statusCode,
      );
    }
  }

  void dispose() {
    _client.close();
  }
}

class BackendException implements Exception {
  BackendException(this.message, {this.statusCode});

  final String message;
  final int? statusCode;

  @override
  String toString() =>
      'BackendException(statusCode: $statusCode, message: $message)';
}
