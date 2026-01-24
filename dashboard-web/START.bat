@echo off
echo Installing dependencies...
call npm install
echo.
echo Starting Next.js development server...
echo.
echo Your dashboard will be at: http://localhost:3000
echo.
call npm run dev
pause
