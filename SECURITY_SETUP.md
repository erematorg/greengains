# Security Setup Guide

## API Key Configuration

The API keys are **no longer hardcoded** in the source code. You must provide them via environment variables.

### For Development (Android Native)

Set environment variables before building:

```bash
# Windows (PowerShell)
$env:GREENGAINS_API_KEY="your_api_key_here"

# Windows (Command Prompt)
set GREENGAINS_API_KEY=your_api_key_here

# Linux/Mac
export GREENGAINS_API_KEY="your_api_key_here"
```

### For Flutter Builds

Pass the API key when building:

```bash
# Debug build
flutter run --dart-define=BACKEND_API_KEY=your_key_here

# Release APK
flutter build apk --release --dart-define=BACKEND_API_KEY=your_key_here

# Both URL and API key
flutter build apk --release \
  --dart-define=BACKEND_API_KEY=your_key_here \
  --dart-define=BACKEND_URL=https://greengains.onrender.com
```

### For Production (Render.com)

Your Render.com backend already has the `API_KEY` environment variable set. Good!

### Cleaning Git History

Since the API key was previously committed, you should clean the git history:

**Option 1: BFG Repo-Cleaner (Recommended)**

```bash
# Install BFG
# Download from: https://rtyley.github.io/bfg-repo-cleaner/

# Replace the exposed key in history
java -jar bfg.jar --replace-text passwords.txt

# Where passwords.txt contains:
# Vb9kS3tP0xQ7fY2L

# Clean up
git reflog expire --expire=now --all
git gc --prune=now --aggressive

# Force push (WARNING: Rewrites history!)
git push origin --force --all
```

**Option 2: Rotate the API Key**

The safer approach:

1. Generate a new API key on your backend/Render.com
2. Update the `API_KEY` environment variable on Render.com
3. Set the new key locally: `export GREENGAINS_API_KEY="new_key_here"`
4. The old key in git history becomes useless

**Recommended:** Rotate the API key instead of rewriting git history.

## Security Checklist

- [x] API keys moved to environment variables
- [x] `.env` added to `.gitignore` (already done)
- [ ] Rotate API key on Render.com
- [ ] Update local environment with new key
- [ ] Test build with environment variables
- [ ] Document for team members

## Verifying It Works

After setting the environment variable, build and check:

```bash
# Set the key
export GREENGAINS_API_KEY="your_key_here"

# Build Android (native code will use BuildConfig)
flutter build apk --dart-define=BACKEND_API_KEY=your_key_here

# If the key is missing, the app will crash with a clear error message
```

The app will **refuse to start** if the API key is not provided, preventing accidental exposure.
