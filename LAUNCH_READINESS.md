# GreenGains Launch Readiness Assessment
**Date:** January 19, 2026

## Executive Summary

**Current Status:** App is 85% ready for beta launch. Core functionality works, but several polish items and backend hardening tasks needed before shipping.

**Recommendation:** Complete 5-7 critical fixes over next 3-5 days, then ship to closed beta.

---

## Critical Issues (Must Fix Before Beta)

### 1. Credits Not Displayed in UI ⚠️
**Problem:** Daily pot awards credits (10-100 per claim), backend tracks total_credits, but there's NO UI showing users their balance.

**Impact:** Users claim rewards but can't see what they earned. Breaks the reward loop.

**Fix Needed:**
- Add credits display to profile screen or home screen
- Show "+X credits" animation when claiming pot
- Consider what users can eventually do with credits (redeem? leaderboard?)

**Estimate:** 2-3 hours

---

### 2. Daily Pot Claim Flow Not Fully Tested
**Problem:** You mentioned "our daily pot doesn't work" - need to verify the entire flow works end-to-end after our fixes.

**Testing Checklist:**
- [ ] Upload 5 batches of data → pot unlocks
- [ ] Claim pot → receive random 10-100 credits
- [ ] Counter resets to 0 after claim
- [ ] Can't claim twice same day
- [ ] Next day at midnight → counter resets, can unlock again

**Estimate:** 1 hour testing

---

### 3. Backend Rate Limiting Missing
**Problem:** No rate limiting on upload endpoints. Malicious users could spam the backend.

**Critical Endpoints:**
- POST /upload (sensor data)
- POST /daily-pot/upload
- POST /daily-pot/claim

**Fix Needed:**
- Add fastify-rate-limit plugin
- Limit uploads to 120/hour per user
- Limit claims to 10/hour per user (prevent spam retries)

**Estimate:** 1-2 hours

---

### 4. Backend Monitoring/Error Tracking
**Problem:** No visibility into production errors or performance.

**Fix Needed:**
- Add Sentry or similar error tracking
- Add basic logging to file (winston or pino)
- Monitor database connection pool health

**Estimate:** 2 hours

---

### 5. Legal Documents Need Accuracy Review
**Problem:** Current privacy policy mentions "magnetometer" and "general location" but app actually collects:
- GPS precise location (not just general)
- Barometric pressure sensor
- Light sensor
- No magnetometer currently

**Fix Needed:**
- Update privacy policy to accurately list sensors collected
- Remove AI-sounding phrases with em-dashes
- Update terms to reflect actual app behavior
- Update data deletion process to match Supabase setup

**Files:**
- C:\Users\mathi\Documents\Eremat\public_html\greengains\legal\privacy-policy.html
- C:\Users\mathi\Documents\Eremat\public_html\greengains\legal\terms-of-service.html
- C:\Users\mathi\Documents\Eremat\public_html\greengains\legal\data-deletion.html

**Estimate:** 2-3 hours

---

## Medium Priority (Nice to Have)

### 6. Empty States and Error Handling
- No internet connection message
- No GPS permission granted state
- Failed upload retry UI
- Loading states for map tiles

**Estimate:** 3-4 hours

### 7. Onboarding Improvements
- Currently has 4 pages (Welcome, Features, Impact, Sign In)
- Could add visual walkthrough of daily pot mechanic
- Explain what credits are for

**Estimate:** 2 hours

### 8. Backend Data Validation
- Validate sensor ranges (pressure 900-1100 hPa, etc.)
- Reject obviously fake GPS coordinates
- Validate timestamp is recent (not future, not too old)

**Estimate:** 2 hours

---

## Already Working Well ✓

- GPS tracking with real-time updates
- Sensor collection (pressure, light, motion)
- Background service with notifications
- Data upload to Supabase
- H3 tile coverage visualization
- Interactive map with recenter
- Google Sign In authentication
- User profile
- Database indexed and optimized
- Settings screen with privacy controls

---

## Play Store Requirements

Before uploading to Play Console, you need:

### Assets
- [ ] App screenshots (2-8 images, phone + tablet)
- [ ] Feature graphic (1024x500 banner)
- [ ] App icon (512x512 high-res)
- [ ] Short description (80 chars max)
- [ ] Full description (4000 chars max) - **READY, see PLAY_STORE_DESCRIPTION.md**
- [ ] Privacy policy URL (have it: https://greengains.eremat.org/legal/privacy-policy.html)

### Technical
- [ ] minSdkVersion check (currently targeting Android 8+)
- [ ] targetSdkVersion 34 (Android 14) required by Play Store
- [ ] App signing key configured
- [ ] ProGuard rules if using obfuscation

### Compliance
- [ ] Data safety form filled out (location, sensor data collection)
- [ ] Content rating questionnaire
- [ ] Target audience selection
- [ ] Test with 20+ users for 14 days (Play Store requirement)

---

## Recommended Timeline

### Days 1-2 (Backend Hardening)
- Add rate limiting to all endpoints
- Set up error monitoring (Sentry)
- Add input validation for sensor data
- Deploy to production

### Days 3-4 (UI Polish)
- Add credits display to profile screen
- Add "+X credits" animation on pot claim
- Test daily pot flow thoroughly
- Fix any empty states

### Day 5 (Legal & Documentation)
- Update privacy policy with accurate sensor list
- Update terms of service
- Review data deletion process
- Create Play Store screenshots

### Day 6 (Final Testing)
- Test on fresh install
- Test with multiple accounts
- Test all user flows
- Fix critical bugs only

### Day 7 (Ship to Beta)
- Upload APK to Play Console
- Set up closed testing track
- Invite 20+ beta testers
- Monitor for crashes

---

## Backend Security Checklist

Before production:
- [ ] Rate limiting on all POST endpoints
- [ ] SQL injection protection (using parameterized queries) ✓ Already done
- [ ] Firebase token verification on all authenticated routes ✓ Already done
- [ ] CORS configured properly
- [ ] HTTPS enforced (should be via Supabase)
- [ ] Database connection pooling configured ✓ Already done
- [ ] Error messages don't leak sensitive info
- [ ] Logs don't contain PII
- [ ] Database backups configured
- [ ] Environment variables secured (not in git) ✓ Already done

---

## What Can Wait Until Post-Beta

These can be added based on beta feedback:
- Credits redemption system
- Leaderboards
- Referral program (widget exists but not wired up)
- Advanced map filters
- Data export for users
- Push notifications for pot unlocks
- Achievements system
- Social sharing

---

## Summary

**To ship beta in 5-7 days:**
1. Fix credits display (critical UX issue)
2. Add backend rate limiting (security)
3. Update legal docs for accuracy (compliance)
4. Test daily pot flow thoroughly (core feature)
5. Create Play Store assets (required)

**Total effort:** ~20 hours of focused work spread over a week.

After beta ships, use the mandatory 14-day testing period to:
- Monitor crashes and errors
- Gather user feedback on confusing parts
- Polish based on real usage data
- Prepare marketing materials for full launch
