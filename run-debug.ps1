# Run GreenGains in debug mode with API key
# Usage: .\run-debug.ps1

Write-Host "Starting GreenGains (debug mode)..." -ForegroundColor Green
Write-Host "Loading API key from dart_defines.json..." -ForegroundColor Cyan

# Run with the API key from dart_defines.json
flutter run --dart-define-from-file=dart_defines.json
