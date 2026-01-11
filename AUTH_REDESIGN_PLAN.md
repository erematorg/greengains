# Auth Flow Redesign Plan

## Overview
Redesign GreenGains' authentication flow inspired by flutter-starter-app patterns to:
1. Move Google Sign In to onboarding flow (no more popup)
2. Remove sign-in from profile screen
3. Require authentication for daily pot access
4. Simplify user experience

---

## Current Flow (Problems)

```
App Launch
    ↓
Anonymous Sign-In (automatic)
    ↓
Onboarding Screen (first time only)
    ↓
Home Screen
    ↓
PostOnboardingSignInSheet POPUP 🚫 (intrusive)
    ↓
User can ignore popup
    ↓
Profile screen has "Sign in with Google" button 🚫
```

**Problems:**
- Intrusive popup after onboarding
- Sign-in scattered across multiple screens
- Anonymous users can access everything (no incentive to sign in)
- Daily pot won't work for anonymous users (needs to be blocked)

---

## Target Flow (Solution)

```
App Launch
    ↓
Check Onboarding Status
    ↓
┌─────────────────────────────────┬──────────────────────┐
│ First Time User                 │ Returning User       │
├─────────────────────────────────┼──────────────────────┤
│ Onboarding Screen (Page 1-3)    │ Check Auth Status    │
│         ↓                        │         ↓            │
│ Google Sign In Screen (Page 4)  │   Anonymous Auth     │
│         ↓                        │         ↓            │
│ Home Screen ✅                   │   Home Screen        │
│ (Daily Pot accessible)           │   (Daily Pot HIDDEN) │
└─────────────────────────────────┴──────────────────────┘
```

**Benefits:**
- ✅ No intrusive popups
- ✅ Clear auth flow integrated into onboarding
- ✅ Daily pot only for signed-in users (incentive!)
- ✅ Clean profile screen (no sign-in button)
- ✅ Matches flutter-starter-app best practices

---

## Implementation Steps

### Step 1: Enhanced Onboarding with Sign-In
**File:** `lib/screens/onboarding_screen.dart`

**Changes:**
1. Add PageView with 4 pages (current 3 features + new sign-in page)
2. Add page indicators (dots)
3. Add "Skip" button (allows anonymous access)
4. Final page: Google Sign In screen with benefits
   - "Unlock rewards and sync across devices"
   - "Sign in with Google" button
   - "Skip for now" option (anonymous)

**Benefits shown on sign-in page:**
- 🍯 Access daily pot rewards
- ☁️ Sync data across devices
- 🏆 Future leaderboards and competitions

---

### Step 2: Remove PostOnboardingSignInSheet Popup
**File:** `lib/main.dart`

**Remove:**
```dart
// Lines 204-228: _maybePromptPostOnboardingAuth() function
// Lines 244-377: PostOnboardingSignInSheet class
```

**Simplify OnboardingWrapper:**
```dart
class _OnboardingWrapperState extends State<OnboardingWrapper> {
  bool _showOnboarding = true;

  @override
  void initState() {
    super.initState();
    _checkOnboarding();
  }

  Future<void> _checkOnboarding() async {
    final completed = AppPreferences.instance.onboardingComplete;
    if (mounted) {
      setState(() => _showOnboarding = !completed);
    }
  }

  Future<void> _handleOnboardingComplete() async {
    await AppPreferences.instance.setOnboardingComplete(true);
    if (!mounted) return;
    setState(() => _showOnboarding = false);
  }

  @override
  Widget build(BuildContext context) {
    if (_showOnboarding) {
      return OnboardingScreen(onComplete: _handleOnboardingComplete);
    }
    return const AppShell();
  }
}
```

---

### Step 3: Remove Sign-In from Profile Screen
**File:** `lib/screens/profile_screen.dart`

**Replace signed-out state:**

**Before (lines 49-90):**
```dart
Widget _buildSignedOutState(ThemeData theme, bool isDark) {
  return Card with "Sign in with Google" button
}
```

**After:**
```dart
Widget _buildSignedOutState(ThemeData theme, bool isDark) {
  return Padding(
    padding: AppTheme.pagePadding,
    child: Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceLg),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(Icons.account_circle_outlined, size: 80),
            const SizedBox(height: AppTheme.spaceMd),
            Text(
              'Anonymous User',
              style: theme.textTheme.titleLarge,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: AppTheme.spaceSm),
            Text(
              'Using app anonymously. Sign in during onboarding to unlock rewards.',
              style: theme.textTheme.bodyMedium,
              textAlign: TextAlign.center,
            ),
          ],
        ),
      ),
    ),
  );
}
```

**Remove:** `_handleGoogleSignIn()` method (no longer needed)

---

### Step 4: Add Auth Check for Daily Pot
**File:** `lib/services/daily_pot_service.dart`

**Add auth check in `initialize()` and `fetchPotState()`:**

```dart
Future<void> initialize() async {
  // Check if user is signed in (not anonymous)
  final user = FirebaseAuth.instance.currentUser;
  if (user == null || user.isAnonymous) {
    _potNotifier.value = null; // Hide daily pot for anonymous users
    return;
  }

  try {
    await fetchPotState();
    _startPolling();
  } catch (e) {
    debugPrint('Daily pot init error: $e');
    _potNotifier.value = null;
  }
}

Future<void> fetchPotState() async {
  final user = FirebaseAuth.instance.currentUser;
  if (user == null || user.isAnonymous) {
    _potNotifier.value = null;
    return;
  }
  // ... rest of existing code
}

Future<void> recordUpload() async {
  final user = FirebaseAuth.instance.currentUser;
  if (user == null || user.isAnonymous) {
    return; // Silently skip for anonymous users
  }
  // ... rest of existing code
}

Future<int> claimPot() async {
  final user = FirebaseAuth.instance.currentUser;
  if (user == null || user.isAnonymous) {
    throw Exception('Sign in required to claim rewards');
  }
  // ... rest of existing code
}
```

---

### Step 5: Update Daily Pot Icon Widget
**File:** `lib/widgets/daily_pot_icon.dart`

**Already handles null state correctly:**
```dart
return ValueListenableBuilder<DailyPot?>(...) {
  if (pot == null) {
    return const SizedBox.shrink(); // Hidden for anonymous users ✅
  }
  // ... rest of widget
}
```

**No changes needed** - widget already hides when pot is null.

---

### Step 6: Clean Up App Preferences
**File:** `lib/core/app_preferences.dart`

**Remove (no longer needed):**
- `postOnboardingAuthPrompted` getter
- `setPostOnboardingAuthPrompted()` method

---

## Testing Checklist

### New User Flow (First Install)
- [ ] App launches → Shows onboarding page 1
- [ ] Swipe through pages 1-3 (features)
- [ ] Page 4: Google Sign In screen
- [ ] Tap "Sign in with Google" → Success → Home screen
- [ ] Daily pot icon visible and functional
- [ ] Profile screen shows signed-in user

### New User Flow (Skip Sign-In)
- [ ] App launches → Shows onboarding page 1
- [ ] Tap "Skip" button → Home screen
- [ ] Daily pot icon HIDDEN
- [ ] Profile screen shows "Anonymous User"
- [ ] No popups or prompts

### Returning User (Signed In)
- [ ] App launches → Home screen directly
- [ ] Daily pot icon visible and functional
- [ ] Profile screen shows user info

### Returning User (Anonymous)
- [ ] App launches → Home screen directly
- [ ] Daily pot icon HIDDEN
- [ ] Profile screen shows "Anonymous User"

### Daily Pot Access Control
- [ ] Signed-in user: Can see pot, unlock, and claim
- [ ] Anonymous user: Icon hidden, no API calls made
- [ ] Upload success: Only signed-in users get pot progress

---

## Files to Modify

### Primary Changes (Required)
1. ✅ `lib/screens/onboarding_screen.dart` - Add PageView with sign-in page
2. ✅ `lib/main.dart` - Remove PostOnboardingSignInSheet
3. ✅ `lib/screens/profile_screen.dart` - Simplify signed-out state
4. ✅ `lib/services/daily_pot_service.dart` - Add auth checks
5. ✅ `lib/core/app_preferences.dart` - Remove unused auth prompt methods

### No Changes Needed
- `lib/widgets/daily_pot_icon.dart` - Already handles null correctly
- `lib/data/models/daily_pot.dart` - No changes
- Backend files - No changes

---

## Risk Assessment

### Low Risk ✅
- Removing popup: Simple removal, no dependencies
- Profile screen change: UI-only, no logic changes
- Daily pot auth check: Graceful null handling already exists

### Medium Risk ⚠️
- Onboarding redesign: New PageView logic, needs testing
- Anonymous user experience: Ensure no crashes when pot is null

### Mitigation
- Keep anonymous auth as fallback (preserve current behavior)
- Daily pot gracefully hides for anonymous users (no errors)
- "Skip" button ensures users can still use app anonymously

---

## Follow-Up Tasks (After Auth Redesign)

Once auth flow is working:
1. Test daily pot system end-to-end
2. Deploy backend (Supabase migration + Render endpoints)
3. Git commit & PR to master
4. Address battery optimization modes (Balanced vs Performance)
5. Apply flutter-starter-app dark theme enhancements

---

## Rollback Plan

If auth redesign causes issues:

1. **Revert onboarding:** Restore simple 3-feature screen without PageView
2. **Restore popup:** Uncomment PostOnboardingSignInSheet in main.dart
3. **Restore profile sign-in:** Add back Google Sign In button
4. **Remove daily pot auth checks:** Make daily pot available to all

**Git commands:**
```bash
git checkout HEAD -- lib/screens/onboarding_screen.dart
git checkout HEAD -- lib/main.dart
git checkout HEAD -- lib/screens/profile_screen.dart
git checkout HEAD -- lib/services/daily_pot_service.dart
```

---

## Summary

**Goal:** Move Google Sign In to onboarding, remove intrusive popup, restrict daily pot to signed-in users.

**Key Principle:** Signed-in users get rewards (daily pot), anonymous users use app freely but miss out on gamification.

**Next Step:** Implement Step 1 (Enhanced Onboarding)
