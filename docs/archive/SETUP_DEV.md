# Development Setup

## Prerequisites
- Flutter 3.7+
- Android Studio / VS Code

## Setup Steps

### 1. Clone Repository
```bash
git clone https://github.com/erematorg/greengains.git
cd greengains
```

### 2. Install Dependencies
```bash
flutter pub get
```

### 3. Configure API Key (Required!)

Copy the example file:
```bash
cp dart_defines.json.example dart_defines.json
```

Edit `dart_defines.json` and add your API key:
```json
{
  "BACKEND_API_KEY": "your-actual-api-key-from-render",
  "BACKEND_URL": "https://greengains.onrender.com"
}
```

**⚠️ NEVER commit `dart_defines.json` to git!** (Already in `.gitignore`)

### 4. Run the App

Now plain `flutter run` works! The app will automatically load the API key from `dart_defines.json`.

```bash
flutter run
```

Or build APK:
```bash
flutter build apk --dart-define-from-file=dart_defines.json
```

---

## How It Works

Flutter automatically looks for `dart_defines.json` when you run without flags.
- ✅ Local dev: You have the file with real key (not in git)
- ✅ Public repo: Only the `.example` file is committed
- ✅ Team members: Copy example, add their key

---

## Getting the API Key

Contact the project maintainer for the API key, or:
1. Go to Render.com dashboard
2. Find the `greengains` backend service
3. Environment tab → Copy `API_KEY` value
