# Run GreenGains in debug mode with API key
# Usage: .\run-debug.ps1

Write-Host "Starting GreenGains (debug mode)..." -ForegroundColor Green
Write-Host "Loading API key from dart_defines.json..." -ForegroundColor Cyan

# Run with the API key from dart_defines.json
# Check if flutter is in PATH
$flutterCmd = "flutter"
if (-not (Get-Command "flutter" -ErrorAction SilentlyContinue)) {
    # Fallback to the path seen in user's environment variables
    $localPath = "C:\Users\mathi\flutter\bin\flutter.bat"
    if (Test-Path $localPath) {
        Write-Host "Flutter not in PATH, using local path: $localPath" -ForegroundColor Yellow
        $flutterCmd = $localPath
    } else {
        Write-Error "Flutter not found! Please check your installation."
        exit 1
    }
}

# Run with the API key from dart_defines.json
& $flutterCmd run --dart-define-from-file=dart_defines.json
