# Theme System Comparison

## flutter-starter-app vs GreenGains

**Date**: 2026-01-11

---

## UI/UX Comparison

### Theme Toggle Widget

**flutter-starter-app (Reference)**:
```dart
SwitchListTile(
  title: Text('Dark Mode'),
  subtitle: Text('Use dark theme'),
  value: themeState.themeMode == ThemeMode.dark,
  onChanged: (value) {
    setThemeMode(value ? ThemeMode.dark : ThemeMode.light);
  },
)
```
- Simple on/off switch
- 2 options: Light or Dark
- No "Auto" mode (follow system)

**GreenGains (Ours)**:
```dart
SegmentedButton<ThemeMode>(
  segments: const [
    ButtonSegment(value: ThemeMode.light, icon: Icon(Icons.light_mode), label: Text('Light')),
    ButtonSegment(value: ThemeMode.dark, icon: Icon(Icons.dark_mode), label: Text('Dark')),
    ButtonSegment(value: ThemeMode.system, icon: Icon(Icons.auto_mode), label: Text('Auto')),
  ],
  selected: {_themeController.mode},
  onSelectionChanged: (newSelection) {
    _themeController.setMode(newSelection.first);
  },
)
```
- Material 3 SegmentedButton
- **3 options: Light / Dark / Auto**
- Auto mode follows system preference
- Visual icons for each mode

**Winner: 🏆 OURS (GreenGains)**
- Better UX with 3 options
- Auto mode respects user's system settings
- Modern Material 3 component
- Clear visual feedback with icons

---

## Features Comparison

### 1. Color Options

**flutter-starter-app**:
- **9 color seeds** to choose from:
  - M3 Baseline (Purple)
  - Indigo
  - Blue
  - Teal
  - **Green** ← We use this
  - Yellow
  - Orange
  - Deep Orange
  - Pink

```dart
enum ColorSeed {
  baseColor('M3 Baseline', Color(0xff6750a4)),
  indigo('Indigo', Colors.indigo),
  blue('Blue', Colors.blue),
  teal('Teal', Colors.teal),
  green('Green', Colors.green),
  // ... 4 more
}
```

- Settings UI shows color picker with circles
- User can switch entire app theme color

**GreenGains (Ours)**:
- **1 color: Green only** (brand color)
- No color picker in settings
- Fixed brand identity

**Winner: 🏆 THEIRS (flutter-starter-app) - but optional**
- More customization
- User preference
- However, for GreenGains, fixed green makes sense for brand consistency

---

### 2. Typography

**flutter-starter-app**:
```dart
fontFamily: GoogleFonts.inter().fontFamily,
```
- Uses **Google Fonts** (Inter)
- Modern, clean typeface
- Better readability

**GreenGains (Ours)**:
- Uses system default fonts
- No custom fonts

**Winner: 🏆 THEIRS (flutter-starter-app)**
- Google Fonts looks more polished
- Inter is a popular, readable font

**Should we add?** Up to you - adds dependency but improves aesthetics.

---

### 3. Auth-Aware Saving

**flutter-starter-app**:
```dart
Future<void> setThemeMode(ThemeMode mode) async {
  // Only save to preferences if user is authenticated
  final authState = ref.read(authProvider);
  if (authState.isAuthenticated) {
    await prefs.setInt(_themeModeKey, mode.index);
  }
  state = state.copyWith(themeMode: mode);
}
```
- **Only authenticated users** persist theme settings
- Anonymous users: Theme changes NOT saved (resets on restart)
- Consistent with "anonymous = no persistence" philosophy

**GreenGains (Ours)**:
```dart
void setMode(ThemeMode m) {
  _mode = m;
  notifyListeners();
  prefs.setThemeModeRaw(prefs.encodeThemeMode(m));
}
```
- **Everyone** can save theme settings (anonymous + signed-in)
- Theme persists for all users

**Winner: 🏆 THEIRS (flutter-starter-app) - for consistency**
- Matches our daily pot logic (only signed-in users get persistence)
- Anonymous users = temporary experience
- Encourages sign-in

**Should we adopt?** **YES** - for consistency with daily pot access control.

---

### 4. State Management

**flutter-starter-app**:
- Uses **Riverpod** + **Freezed**
- Immutable state with `ThemeState` class
- More complex but scalable

**GreenGains (Ours)**:
- Uses **ChangeNotifier**
- Simple, direct
- Easy to understand

**Winner: 🏆 TIE**
- Theirs: Better for large apps
- Ours: Simpler, sufficient for our needs

---

## Summary Table

| Feature | flutter-starter-app | GreenGains | Winner |
|---------|---------------------|------------|--------|
| **Theme Toggle UI** | SwitchListTile (2 options) | SegmentedButton (3 options) | **🏆 Ours** |
| **Auto Mode** | ❌ No | ✅ Yes | **🏆 Ours** |
| **Color Themes** | ✅ 9 options | ❌ Green only | 🏆 Theirs (optional) |
| **Google Fonts** | ✅ Inter | ❌ System fonts | 🏆 Theirs (optional) |
| **Auth-Aware Saving** | ✅ Yes | ❌ No | **🏆 Theirs** (important!) |
| **State Management** | Riverpod + Freezed | ChangeNotifier | 🏆 Tie |

---

## Recommendations

### Must Adopt: Auth-Aware Theme Saving ✅

**Why:** Consistency with daily pot logic (only signed-in users get persistence)

**Implementation:**
```dart
// In ThemeController
void setMode(ThemeMode m) {
  _mode = m;
  notifyListeners(); // Instant UI update

  // Only save if user is signed in (not anonymous)
  final user = FirebaseAuth.instance.currentUser;
  if (user != null && !user.isAnonymous) {
    final prefs = AppPreferences.instance;
    prefs.setThemeModeRaw(prefs.encodeThemeMode(m));
  }
}
```

**User experience:**
- **Signed-in users**: Theme persists across app restarts ✅
- **Anonymous users**: Theme resets to system default on restart ❌
- Encourages users to sign in for better experience

---

### Optional: Google Fonts

**Pros:**
- More polished look
- Better readability
- Modern aesthetic

**Cons:**
- Adds dependency: `google_fonts` package
- Slightly larger app size
- Need to pick a font (Inter is good)

**Recommendation:** Add if you want a more polished look.

---

### Optional: Color Themes

**Pros:**
- User customization
- Fun feature

**Cons:**
- Might dilute GreenGains brand (green = eco)
- Extra complexity
- Settings UI gets more crowded

**Recommendation:** Skip for now. Green is your brand. Keep it simple.

---

## Final Verdict

**Overall Winner: HYBRID APPROACH**

✅ **Keep from ours:**
- SegmentedButton with Light/Dark/Auto
- Simple ChangeNotifier
- Green-only theme (brand consistency)

✅ **Adopt from theirs:**
- **Auth-aware theme saving** (important for consistency)
- Google Fonts (optional, for polish)

❌ **Skip:**
- Color seed system (not needed for GreenGains)
- Riverpod (overkill for our simple theme needs)

---

## Implementation Plan

If you want to adopt auth-aware theme saving:

### File to Modify:
[lib/core/theme_controller.dart](lib/core/theme_controller.dart)

### Change:
```dart
import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart'; // Add this
import 'app_preferences.dart';

class ThemeController extends ChangeNotifier {
  // ... existing code ...

  void setMode(ThemeMode m) {
    _mode = m;
    notifyListeners(); // Instant UI update

    // Only save if user is signed in (not anonymous)
    final user = FirebaseAuth.instance.currentUser;
    if (user != null && !user.isAnonymous) {
      final prefs = AppPreferences.instance;
      prefs.setThemeModeRaw(prefs.encodeThemeMode(m));
    }
    // Anonymous users: Theme not saved (resets on app restart)
  }
}
```

**Result:**
- Signed-in users: Theme persists ✅
- Anonymous users: Theme resets on restart (encourages sign-in) ✅

---

## User Messaging

Update Settings screen to show:

**For anonymous users:**
```
ℹ️ Theme changes will not be saved. Sign in to persist your preferences.
```

**For signed-in users:**
```
✅ Theme preferences saved automatically.
```

---

**Conclusion:** Our theme toggle UI is better, but we should add auth-aware saving for consistency with daily pot logic.
