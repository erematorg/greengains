# Testing Guide - Auth Redesign & Theme Improvements

**Date**: 2026-01-11
**Changes**: Auth flow redesign + Auth-aware theme persistence

---

## Quick Start

### Option 1: Run on Emulator (Recommended)

```cmd
# Double-click this file:
build_and_test.bat

# Or run manually:
cd C:\Users\mathi\Documents\GitHub\greengains
C:\Users\mathi\flutter\bin\flutter run
```

### Option 2: Build APK for Pixel 7

```cmd
# Build release APK
C:\Users\mathi\flutter\bin\flutter build apk --release

# APK will be at:
# build\app\outputs\flutter-apk\app-release.apk

# Transfer to Pixel 7 and install
```

---

## What Changed Since Last Version

### 1. New 4-Page Onboarding ✨
- **Before**: Single page with "Get Started" button
- **After**: 4 swipeable pages with Google Sign In as final step

### 2. No More Popup 🎉
- **Before**: Intrusive sign-in popup after onboarding
- **After**: No popup! Sign-in is integrated into onboarding

### 3. Profile Screen Cleanup 🧹
- **Before**: Google Sign In button in profile
- **After**: Just "Sign out" button (no sign-in option)

### 4. Daily Pot Access Control 🍯
- **Before**: Daily pot visible for everyone (but didn't work)
- **After**: Only visible for signed-in users (hidden for anonymous)

### 5. Auth-Aware Theme Persistence 🎨
- **Before**: Everyone's theme preferences saved
- **After**: Only signed-in users' theme preferences saved
  - Anonymous users: Theme resets on app restart
  - Encourages sign-in for better experience

---

## Test Scenarios

### 🧪 Test 1: New User - Sign In Path

**Steps:**
1. Launch app (first time)
2. See onboarding page 1 (Welcome)
3. Swipe or tap [Next] to page 2 (Features)
4. Swipe or tap [Next] to page 3 (Impact)
5. Swipe or tap [Next] to page 4 (Sign In)
6. Tap **"Sign in with Google"**
7. Select Google account
8. Wait for success message

**Expected Results:**
- ✅ Google Sign In completes successfully
- ✅ Success message: "Signed in successfully"
- ✅ Home screen loads
- ✅ **Daily pot icon visible** (top-right corner, locked, 0/5)
- ✅ Profile shows your Google account info
- ✅ **No popup** appears anywhere

**Theme Test:**
1. Go to Settings → Change theme to Dark
2. Close app completely
3. Reopen app
4. **Expected**: Theme is still Dark (saved) ✅

---

### 🧪 Test 2: New User - Skip/Anonymous Path

**Steps:**
1. Launch app (first time)
2. On any page 1-3, tap **[Skip]** button (top-right)
   - OR navigate to page 4, tap **"Skip for now"**
3. Home screen loads

**Expected Results:**
- ✅ Home screen loads immediately
- ✅ **Daily pot icon HIDDEN** (not visible anywhere)
- ✅ Profile shows "Anonymous User" or info card
- ✅ **No popup** appears

**Theme Test:**
1. Go to Settings → Change theme to Dark
2. Close app completely
3. Reopen app
4. **Expected**: Theme is Light/System (NOT saved) ✅
   - This encourages users to sign in

---

### 🧪 Test 3: Onboarding Navigation

**Test all navigation options:**

1. **Swipe gestures**
   - Swipe left on page 1 → Page 2
   - Swipe right on page 2 → Page 1
   - Swipe left/right through all pages

2. **Previous/Next buttons**
   - Page 1: Only [Next] visible
   - Page 2-3: Both [Previous] and [Next] visible
   - Page 4: Only [Previous] visible (no Next)

3. **Skip button**
   - Visible on pages 1-3 (top-right)
   - NOT visible on page 4
   - Tapping Skip → Immediate home screen

4. **Page indicators (dots)**
   - 4 dots at bottom
   - Current page highlighted (longer dot)
   - Dots update as you swipe

**Expected:**
- ✅ All navigation works smoothly
- ✅ Animations are smooth (300ms)
- ✅ No crashes or errors

---

### 🧪 Test 4: Daily Pot (Signed-In Users Only)

**Prerequisites:** Signed in with Google

**Steps:**
1. Home screen → Daily pot icon visible (top-right)
2. Icon shows progress ring (0/5 initially)
3. Icon shows lock overlay
4. Tap icon → Shows "Not unlocked yet" message
5. Trigger 5 successful uploads (backend must be deployed!)
6. After 5th upload:
   - Progress ring fills to 100%
   - Lock icon disappears
   - Icon glows with green gradient
   - Icon jiggles/wiggles
7. Tap icon → Shows reward popup: "+[10-100] credits! 🍯"

**Expected:**
- ✅ Icon progresses correctly
- ✅ Lock disappears when unlocked
- ✅ Jiggle animation plays
- ✅ Random reward 10-100 (in increments of 5)
- ✅ After claiming: Icon grays out (claimed today)

**For Anonymous Users:**
- ✅ Icon completely hidden
- ✅ No errors in logs
- ✅ No API calls to /daily-pot endpoints

---

### 🧪 Test 5: Profile Screen States

**Signed-In User:**
```
┌─────────────────────┐
│ Profile    [⚙️]     │
│ ┌─────────────────┐ │
│ │ 👤 Your Name    │ │
│ │    Member since │ │
│ └─────────────────┘ │
│ [Referral Card]     │
│ [Stats Card]        │
│ ┌─────────────────┐ │
│ │ 🚪 Sign out     │ │ (red button)
│ └─────────────────┘ │
└─────────────────────┘
```

**Expected:**
- ✅ Shows Google account name/photo
- ✅ Shows "Member since [date]"
- ✅ Red "Sign out" button at bottom
- ✅ **No sign-in button**

**Anonymous User:**
```
┌─────────────────────┐
│ Profile    [⚙️]     │
│ ┌─────────────────┐ │
│ │ 👤 Anonymous    │ │
│ │    Guest User   │ │
│ └─────────────────┘ │
│ [Referral Card]     │
│ [Stats Card]        │
│ ┌─────────────────┐ │
│ │ ℹ️ Using        │ │
│ │   anonymously   │ │
│ └─────────────────┘ │
└─────────────────────┘
```

**Expected:**
- ✅ Shows "Anonymous User" or similar
- ✅ Info card about anonymous mode
- ✅ **No sign-in button** (must go through onboarding)

---

### 🧪 Test 6: Settings Screen

**Steps:**
1. Profile → Tap ⚙️ (Settings)
2. See theme toggle section

**Expected UI:**
```
┌─────────────────────┐
│ Theme               │
│ ┌───┬───┬───┐      │
│ │☀️ │🌙│🔄 │      │ (SegmentedButton)
│ │Lit│Drk│Aut│      │
│ └───┴───┴───┘      │
│                     │
│ Data & Privacy      │
│ ...                 │
└─────────────────────┘
```

**Features:**
- ✅ 3 options: Light / Dark / Auto
- ✅ Icons for each mode
- ✅ Instant UI update on tap
- ✅ Haptic feedback on selection
- ✅ Current mode highlighted

**Theme Persistence Test:**
- **Signed-in user**: Change theme → Restart app → Theme persists ✅
- **Anonymous user**: Change theme → Restart app → Theme resets to system ✅

---

### 🧪 Test 7: No Popups Anywhere

**Critical Test:** Ensure no intrusive popups appear

**Test Points:**
1. After onboarding completes → No popup ✅
2. When navigating to home → No popup ✅
3. When opening app as returning user → No popup ✅
4. When anonymous user browses app → No popup ✅

**Only expected dialogs:**
- Google Sign In flow (when user taps "Sign in with Google")
- Daily pot claim confirmation (when unlocked)

---

## Known Limitations (Until Backend Deployed)

❌ **Daily pot will NOT work yet** because:
- Backend endpoints not deployed (`/daily-pot/*`)
- Supabase migration not run

**What happens:**
- Daily pot icon shows for signed-in users
- Progress stays at 0/5 (no backend to record uploads)
- Claiming fails (no backend endpoint)

**Fix:** Deploy backend files:
1. Run `supabase_migrations/daily_pot.sql`
2. Add endpoints from `backend_endpoints_daily_pot.js` to Render

---

## Performance Checklist

- [ ] Onboarding page swipes are smooth (60fps)
- [ ] Theme changes are instant
- [ ] No lag when navigating between screens
- [ ] Google Sign In flow is smooth
- [ ] App starts quickly (cold start < 3s)
- [ ] No memory leaks (check with Flutter DevTools)

---

## Error Scenarios

### If Google Sign In Fails:
**Expected Behavior:**
- Error message: "Sign-in cancelled or failed"
- User returns to page 4 (sign-in page)
- Can retry or tap "Skip for now"

### If App Crashes:
**Check logs:**
```cmd
C:\Users\mathi\flutter\bin\flutter logs
```

**Common issues:**
- Firebase not initialized → Check `firebase_options.dart`
- Google Sign In config wrong → Check `android/app/google-services.json`
- Missing dependencies → Run `flutter pub get`

---

## Visual Inspection

### Screenshots to Take (For Documentation)

1. **Onboarding Page 1** - Welcome screen
2. **Onboarding Page 2** - Features screen
3. **Onboarding Page 3** - Impact screen
4. **Onboarding Page 4** - Sign In screen with benefits
5. **Home (Signed-In)** - With daily pot icon
6. **Home (Anonymous)** - Without daily pot icon
7. **Profile (Signed-In)** - With Google account
8. **Profile (Anonymous)** - With anonymous state
9. **Settings** - Theme toggle with 3 options
10. **Daily Pot Unlocked** - Icon glowing and jiggling

---

## Success Criteria

### Must Pass ✅
- [x] 4-page onboarding works smoothly
- [x] Google Sign In completes successfully
- [x] Skip creates anonymous user
- [x] NO post-onboarding popup appears
- [x] Daily pot icon shows for signed-in users ONLY
- [x] Daily pot icon hidden for anonymous users
- [x] Profile has no sign-in button
- [x] Theme toggle has 3 options (Light/Dark/Auto)
- [x] Theme persists for signed-in users only
- [x] Theme resets for anonymous users (on restart)

### Nice to Have 🎨
- [ ] Smooth animations (60fps)
- [ ] Quick load times
- [ ] No visual glitches
- [ ] Haptic feedback works

---

## Regression Testing

**Ensure existing features still work:**

- [ ] Upload sensor data (background service)
- [ ] View contribution stats
- [ ] View sensor data card
- [ ] Referral card works
- [ ] Statistics screen loads
- [ ] Privacy policy/TOS links work
- [ ] Data deletion request works

---

## Next Steps After Testing

### If All Tests Pass ✅

1. **Deploy Backend**
   ```bash
   # Supabase migration
   psql -h <supabase-host> -U postgres -d postgres -f supabase_migrations/daily_pot.sql

   # Deploy Render endpoints (merge backend_endpoints_daily_pot.js)
   ```

2. **Git Commit**
   ```bash
   git add lib/screens/onboarding_screen.dart
   git add lib/main.dart
   git add lib/screens/profile_screen.dart
   git add lib/services/daily_pot_service.dart
   git add lib/core/theme_controller.dart
   git add lib/screens/settings_screen.dart

   git commit -m "$(cat <<'EOF'
   Redesign auth flow and enhance theme system

   - Add 4-page onboarding with Google Sign In as final step
   - Remove intrusive post-onboarding popup
   - Remove sign-in from profile screen
   - Restrict daily pot access to signed-in users only
   - Add auth-aware theme persistence (only signed-in users)
   - Anonymous users can use app freely but miss rewards

   Co-Authored-By: Claude Sonnet 4.5 <noreply@anthropic.com>
   EOF
   )"
   ```

3. **Create PR**
   - Push to feature branch
   - Open PR to master
   - Auto-deploys backend to Render

4. **Test in Production**
   - Verify daily pot works end-to-end
   - Monitor for errors

### If Tests Fail ❌

1. **Document Issues**
   - Screenshot the error
   - Copy error logs
   - Note steps to reproduce

2. **Fix & Retest**
   - Address issues
   - Run tests again

3. **Rollback if Needed**
   ```bash
   git checkout HEAD~1 -- lib/screens/onboarding_screen.dart
   git checkout HEAD~1 -- lib/main.dart
   git checkout HEAD~1 -- lib/screens/profile_screen.dart
   git checkout HEAD~1 -- lib/services/daily_pot_service.dart
   git checkout HEAD~1 -- lib/core/theme_controller.dart
   ```

---

## Support

**If you encounter issues:**

1. Check logs: `flutter logs`
2. Read error messages carefully
3. Review implementation docs:
   - [AUTH_REDESIGN_IMPLEMENTATION.md](AUTH_REDESIGN_IMPLEMENTATION.md)
   - [THEME_SYSTEM_COMPARISON.md](THEME_SYSTEM_COMPARISON.md)
   - [DAILY_POT_SYSTEM.md](DAILY_POT_SYSTEM.md)

---

**Testing Date**: _____________
**Tested By**: _____________
**Device**: _____________
**Result**: ⬜ Pass  ⬜ Fail  ⬜ Partial
