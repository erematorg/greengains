@echo off
echo ========================================
echo   GreenGains - Quick Run Script
echo ========================================
echo.

cd /d C:\Users\mathi\Documents\GitHub\greengains

echo [1/3] Getting dependencies (including Google Fonts)...
C:\Users\mathi\flutter\bin\flutter pub get
if %errorlevel% neq 0 (
    echo ERROR: Failed to get dependencies
    pause
    exit /b 1
)
echo.

echo [2/3] Listing available devices...
C:\Users\mathi\flutter\bin\flutter devices
echo.

echo [3/3] Launching app on emulator...
echo.
echo WHAT YOU'LL SEE:
echo   - 4-page onboarding (swipe through)
echo   - Page 4: Google Sign In option
echo   - Try both: Sign In AND Skip
echo   - Check if daily pot shows/hides correctly
echo.
echo Press Ctrl+C to stop the app
echo.

C:\Users\mathi\flutter\bin\flutter run

pause
