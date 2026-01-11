@echo off
echo ========================================
echo GreenGains - Build and Test Script
echo ========================================
echo.

cd /d C:\Users\mathi\Documents\GitHub\greengains

echo [1/4] Cleaning previous build...
C:\Users\mathi\flutter\bin\flutter clean
if %errorlevel% neq 0 (
    echo ERROR: Flutter clean failed
    pause
    exit /b 1
)
echo.

echo [2/4] Getting dependencies...
C:\Users\mathi\flutter\bin\flutter pub get
if %errorlevel% neq 0 (
    echo ERROR: Flutter pub get failed
    pause
    exit /b 1
)
echo.

echo [3/4] Listing available devices...
C:\Users\mathi\flutter\bin\flutter devices
echo.

echo [4/4] Ready to run!
echo.
echo Choose an option:
echo   1. Run on emulator (debug mode)
echo   2. Build APK (release mode)
echo   3. Exit
echo.

set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" (
    echo.
    echo Starting app on emulator...
    echo Press Ctrl+C to stop the app
    echo.
    C:\Users\mathi\flutter\bin\flutter run
) else if "%choice%"=="2" (
    echo.
    echo Building release APK...
    C:\Users\mathi\flutter\bin\flutter build apk --release
    if %errorlevel% equ 0 (
        echo.
        echo ========================================
        echo SUCCESS! APK built at:
        echo build\app\outputs\flutter-apk\app-release.apk
        echo ========================================
    )
) else (
    echo Exiting...
)

echo.
pause
