# Simple screenshot capture
# Usage: .\capture-screenshot.ps1 <filename>
# Example: .\capture-screenshot.ps1 home-screen.png

param(
    [Parameter(Mandatory=$true)]
    [string]$filename
)

$adb = "C:\Users\mathi\AppData\Local\Android\Sdk\platform-tools\adb.exe"
$outputDir = "screenshots\app"

Write-Host "Capturing screenshot..." -ForegroundColor Cyan

# Take screenshot on device
& $adb shell screencap -p /sdcard/screenshot.png

# Pull to computer
& $adb pull /sdcard/screenshot.png "$outputDir\$filename"

# Delete from device
& $adb shell rm /sdcard/screenshot.png

Write-Host "Saved: $outputDir\$filename" -ForegroundColor Green
