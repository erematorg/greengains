# API Key Setup Complete ✅

## Your New API Key

```
Xk9mW2vN8qL5pR3tY7jH6bF4
```

## What I Did For You

1. ✅ Generated a secure 24-character API key
2. ✅ Created `.env` file with the key (for reference)
3. ✅ Created PowerShell build scripts (for easy building)
4. ✅ Added build scripts to `.gitignore` (won't be committed)

---

## What YOU Need to Do

### 1. Update Render.com (REQUIRED!)

1. Go to: https://dashboard.render.com
2. Click your `greengains` web service
3. Click **Environment** tab
4. Find `API_KEY` row → Click **Edit**
5. Paste: `Xk9mW2vN8qL5pR3tY7jH6bF4`
6. Click **Save Changes**
7. Wait ~30 seconds for auto-restart

**✋ DO THIS FIRST before building the app!**

---

## How to Build & Run

### Option 1: Easy Way (PowerShell Scripts)

```powershell
# Build release APK
.\build-apk.ps1

# Run debug mode
.\run-debug.ps1
```

### Option 2: Manual Way

```bash
# Build APK
flutter build apk --dart-define=BACKEND_API_KEY=Xk9mW2vN8qL5pR3tY7jH6bF4

# Run debug
flutter run --dart-define=BACKEND_API_KEY=Xk9mW2vN8qL5pR3tY7jH6bF4
```

---

## Verify It Works

After updating Render.com and building the app:

1. Install the APK on your phone
2. Open the app (you'll see onboarding)
3. Click "Get Started"
4. Click "Start Tracking"
5. Wait 2 minutes
6. Check if stats update → If yes, it's working! 🎉

If you get errors, it means:
- ❌ API key not updated on Render.com yet
- ❌ Or Render backend hasn't restarted

---

## Security Notes

- ✅ Old key (`Vb9kS3tP0xQ7fY2L`) will stop working once you update Render
- ✅ New key is NOT in git history
- ✅ `.env` and build scripts are in `.gitignore`
- ⚠️ **NEVER** commit the new key to git!

---

## Troubleshooting

**Error: "Invalid API key"**
→ Make sure you updated Render.com and waited for restart

**Error: "Missing API key"**
→ Make sure you built with `--dart-define=BACKEND_API_KEY=...`

**Backend not responding**
→ Check Render.com logs to see if backend restarted successfully
