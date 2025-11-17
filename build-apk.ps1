# Build GreenGains APK with API key
# Usage: .\build-apk.ps1

Write-Host "Building GreenGains APK..." -ForegroundColor Green
Write-Host "Loading API key from dart_defines.json..." -ForegroundColor Cyan

# Build APK with the API key from dart_defines.json
flutter build apk --dart-define-from-file=dart_defines.json

Write-Host ""
Write-Host "Build complete!" -ForegroundColor Green
Write-Host "APK location: build/app/outputs/flutter-apk/app-release.apk" -ForegroundColor Yellow
