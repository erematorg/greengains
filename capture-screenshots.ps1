# Screenshot Capture Script for GreenGains
# Usage: Navigate to each screen on your phone, then run the corresponding command

$adb = "C:\Users\mathi\AppData\Local\Android\Sdk\platform-tools\adb.exe"
$outputDir = "screenshots\app"

Write-Host "GreenGains Screenshot Capture Tool" -ForegroundColor Green
Write-Host "====================================" -ForegroundColor Green
Write-Host ""
Write-Host "Instructions:" -ForegroundColor Cyan
Write-Host "1. Navigate to the screen you want to capture on your phone"
Write-Host "2. Press the corresponding number key"
Write-Host "3. Screenshot will be saved to screenshots/app/"
Write-Host ""
Write-Host "Available screens:" -ForegroundColor Yellow
Write-Host "  1. Onboarding - Welcome"
Write-Host "  2. Onboarding - Features"
Write-Host "  3. Onboarding - Privacy"
Write-Host "  4. Onboarding - Sign In"
Write-Host "  5. Home Screen"
Write-Host "  6. Profile Screen"
Write-Host "  7. Settings Screen"
Write-Host "  8. Map View"
Write-Host "  9. Daily Pot (Unclaimed)"
Write-Host " 10. Daily Pot (Claimed)"
Write-Host " 11. Language Selector (French)"
Write-Host " 12. Theme Selector (Dark Mode)"
Write-Host "  Q. Quit"
Write-Host ""

while ($true) {
    $choice = Read-Host "Navigate to screen and press number (or Q to quit)"

    if ($choice -eq "Q" -or $choice -eq "q") {
        Write-Host "Done! Screenshots saved in screenshots/app/" -ForegroundColor Green
        break
    }

    $filename = switch ($choice) {
        "1"  { "01-onboarding-welcome.png" }
        "2"  { "02-onboarding-features.png" }
        "3"  { "03-onboarding-privacy.png" }
        "4"  { "04-onboarding-signin.png" }
        "5"  { "05-home-screen.png" }
        "6"  { "06-profile-screen.png" }
        "7"  { "07-settings-screen.png" }
        "8"  { "08-map-view.png" }
        "9"  { "09-daily-pot-unclaimed.png" }
        "10" { "10-daily-pot-claimed.png" }
        "11" { "11-settings-language-french.png" }
        "12" { "12-settings-theme-dark.png" }
        default { $null }
    }

    if ($filename) {
        Write-Host "Capturing screenshot..." -ForegroundColor Cyan

        # Take screenshot on device
        & $adb shell screencap -p /sdcard/screenshot.png

        # Pull to computer
        & $adb pull /sdcard/screenshot.png "$outputDir\$filename"

        # Delete from device
        & $adb shell rm /sdcard/screenshot.png

        Write-Host "✓ Saved: $filename" -ForegroundColor Green
        Write-Host ""
    } else {
        Write-Host "Invalid choice. Try again." -ForegroundColor Red
    }
}
