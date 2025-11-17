# GreenGains 🌱

Passive environmental sensor data collection for greener cities.

## Quick Start

### 1. Setup
```bash
# Copy the API key template
cp dart_defines.json.example dart_defines.json

# Edit dart_defines.json and add your API key
# (Get it from Render.com → greengains service → Environment → API_KEY)
```

### 2. Run
```bash
# Easy way (PowerShell)
.\run-debug.ps1

# Or standard Flutter
flutter run --dart-define-from-file=dart_defines.json
```

### 3. Build APK
```bash
# Easy way (PowerShell)
.\build-apk.ps1

# Or standard Flutter
flutter build apk --dart-define-from-file=dart_defines.json
```

---

## How It Works

- **Passive Collection**: Runs in background collecting sensor data (light, accelerometer, gyroscope)
- **Privacy First**: Optional coarse location (~200m accuracy)
- **Low Impact**: Minimal battery usage, uploads every 2 minutes
- **Track Impact**: See your contributions in the app

---

## Architecture

```
Flutter App → Render.com Backend → Supabase Database
```

- **Frontend**: Flutter (Android)
- **Backend**: Node.js/TypeScript on Render.com
- **Database**: PostgreSQL on Supabase
- **Auth**: Firebase Authentication

---

## Security

- API keys are NOT committed to git
- `dart_defines.json` is gitignored (local only)
- See `SECURITY_SETUP.md` for details

---

## Development Setup

See `SETUP_DEV.md` for detailed setup instructions.

---

## Contributing

1. Fork the repo
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

---

## License

MIT
