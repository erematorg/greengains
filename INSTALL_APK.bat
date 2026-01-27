@echo off
echo Installing GreenGains on your Pixel 7...
echo.

echo Checking for connected devices...
adb devices
echo.

echo Uninstalling old version...
adb uninstall com.eremat.greengains
echo.

echo Installing new release APK...
adb install build\app\outputs\flutter-apk\app-release.apk
echo.

echo Done! Check your phone.
pause
