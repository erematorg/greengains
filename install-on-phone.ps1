# Install GreenGains APK on Connected Phone
# Usage: .\install-on-phone.ps1

Write-Host "Installing GreenGains on your phone..." -ForegroundColor Green

# Check if phone is connected
Write-Host "Checking for connected devices..." -ForegroundColor Cyan
adb devices

Write-Host ""
Write-Host "Uninstalling old version (if exists)..." -ForegroundColor Cyan
adb uninstall com.eremat.greengains 2>$null

Write-Host ""
Write-Host "Installing new version..." -ForegroundColor Cyan
adb install build\app\outputs\flutter-apk\app-release.apk

Write-Host ""
Write-Host "Done! Check your phone." -ForegroundColor Green
