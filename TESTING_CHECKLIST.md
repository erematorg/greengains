# Persistence Layer Testing Checklist

## Pre-Testing Setup

- [ ] Build the app successfully
- [ ] Run on physical Android device (recommended)
- [ ] Enable developer options on device
- [ ] Have ADB access for debugging

## Database Migration Tests

### New Installation (v2)
- [ ] Uninstall app completely
- [ ] Install fresh build
- [ ] Verify app launches without errors
- [ ] Check logs for "Database version: 2"
- [ ] Verify all tables created successfully

### Upgrade from v1 to v2
- [ ] Install v1 build (if available)
- [ ] Create some contributions (upload data)
- [ ] Install v2 build (this version)
- [ ] Verify app launches without errors
- [ ] Check logs for "Upgrading database from v1 to v2"
- [ ] Check logs for "Database upgraded successfully to v2"
- [ ] Verify old contributions still visible
- [ ] Verify stats still correct

## Tracking Session Tests

### Normal Session Flow
- [ ] Launch app
- [ ] Start tracking
- [ ] Verify session created in database
- [ ] Check logs for "Started tracking session: {id}"
- [ ] Stop tracking
- [ ] Verify session closed in database
- [ ] Check logs for "Stopped tracking session {id}"

### Crash Recovery
- [ ] Start tracking
- [ ] Verify tracking is active
- [ ] Force-stop app (via Android settings)
- [ ] Relaunch app
- [ ] Verify session was closed automatically
- [ ] Check logs for "Closed orphaned tracking session"
- [ ] Verify tracking button shows "Start" (not stuck in "tracking" state)

### Auto-Restart
- [ ] Start tracking
- [ ] Force-stop app
- [ ] Relaunch app
- [ ] Verify tracking auto-restarts (if `foreground_service_enabled` is true)
- [ ] Check logs for "Auto-restarting service"
- [ ] Verify notification appears

### Multiple Sessions
- [ ] Start tracking
- [ ] Stop tracking
- [ ] Start tracking again
- [ ] Stop tracking again
- [ ] Navigate to session history (if UI exists)
- [ ] Verify all sessions recorded
- [ ] Verify durations calculated correctly

## Daily Pot Cache Tests

### Initial Load
- [ ] Sign in with Google
- [ ] Verify daily pot loads from backend
- [ ] Check logs for "DailyPot: Synced with backend"
- [ ] Force-close app
- [ ] Relaunch app
- [ ] Verify daily pot loads from cache
- [ ] Check logs for "DailyPot: Loaded from cache"
- [ ] Verify UI shows pot state immediately (no loading spinner)

### Optimistic Updates
- [ ] Start tracking
- [ ] Wait for upload to complete
- [ ] Verify daily pot progress increments immediately
- [ ] Check logs for "Daily pot optimistic update"
- [ ] Verify backend sync happens in background
- [ ] Check logs for "Daily pot backend sync confirmed"
- [ ] Verify progress bar updates smoothly

### Offline Mode
- [ ] Enable airplane mode
- [ ] Launch app
- [ ] Verify daily pot loads from cache
- [ ] Start tracking
- [ ] Wait for upload attempt (should show cached state)
- [ ] Verify optimistic update still works
- [ ] Disable airplane mode
- [ ] Verify backend sync happens automatically
- [ ] Verify pot state is correct after sync

### Sign Out / Sign In
- [ ] Sign in with Google
- [ ] Verify daily pot loads
- [ ] Sign out
- [ ] Verify daily pot cache cleared
- [ ] Sign in again
- [ ] Verify fresh pot data loads

## Upload State Tests

### Normal Upload
- [ ] Start tracking
- [ ] Wait for upload to complete (5 minutes)
- [ ] Verify contribution saved to database
- [ ] Verify upload timestamp saved to SharedPreferences
- [ ] Verify stats updated correctly
- [ ] Verify daily pot progress incremented

### Upload Failure
- [ ] Start tracking
- [ ] Enable airplane mode before upload
- [ ] Wait for upload attempt
- [ ] Verify error shown in UI
- [ ] Check logs for "Upload failed"
- [ ] Disable airplane mode
- [ ] Verify next upload succeeds
- [ ] Verify stats eventually update

### Last Upload Timestamp
- [ ] Upload data
- [ ] Note "Last upload: X seconds ago"
- [ ] Force-close app
- [ ] Relaunch app
- [ ] Verify "Last upload" timestamp restored correctly
- [ ] Check logs for "Reloaded last upload from SharedPreferences"

## State Restoration Tests

### App Lifecycle
- [ ] Start tracking
- [ ] Press home button (app goes to background)
- [ ] Wait a few seconds
- [ ] Reopen app
- [ ] Verify tracking state preserved
- [ ] Verify stats up-to-date

### Battery Saver Mode
- [ ] Enable battery saver mode
- [ ] Start tracking
- [ ] Let app run in background
- [ ] Verify service keeps running
- [ ] Verify uploads still happen

### Device Reboot
- [ ] Start tracking
- [ ] Note current session ID
- [ ] Reboot device
- [ ] Relaunch app
- [ ] Verify session was closed (reason: 'service_stopped')
- [ ] Verify stats preserved
- [ ] Verify can start new session

## Event System Tests

### Upload Success Event
- [ ] Listen to UploadSuccessEvent in UI
- [ ] Start tracking
- [ ] Wait for upload
- [ ] Verify event fired
- [ ] Verify UI updates reactively
- [ ] Verify snackbar/notification shown

### Tracking State Changed Event
- [ ] Listen to TrackingStateChangedEvent
- [ ] Start tracking
- [ ] Verify event fired with isTracking=true
- [ ] Stop tracking
- [ ] Verify event fired with isTracking=false

### Daily Pot Updated Event
- [ ] Listen to DailyPotUpdatedEvent
- [ ] Upload data
- [ ] Verify event fired with updated pot
- [ ] Verify UI progress bar updates

### Daily Pot Claimed Event
- [ ] Unlock daily pot (5 uploads)
- [ ] Claim pot
- [ ] Verify DailyPotClaimedEvent fired
- [ ] Verify celebration animation shown (if implemented)

## Database Maintenance Tests

### Auto-Cleanup
- [ ] Insert old contributions (90+ days ago)
- [ ] Launch app
- [ ] Check logs for "Cleaned X old contributions"
- [ ] Verify old contributions deleted
- [ ] Verify recent contributions preserved

### Database Stats
- [ ] Upload some data
- [ ] Get database stats
- [ ] Verify counts accurate:
  - [ ] Contributions count matches uploads
  - [ ] Sessions count matches tracking starts
  - [ ] Queued uploads count is 0 (no failures)

## State Export Tests

### Export State
- [ ] Use AppStateManager.exportStateJson()
- [ ] Verify JSON output is valid
- [ ] Verify all key data present:
  - [ ] Summary statistics
  - [ ] Recent sessions
  - [ ] App state values
- [ ] Save to file
- [ ] Verify can be used for debugging

## Performance Tests

### Database Query Speed
- [ ] Insert 1000 contributions
- [ ] Query total count
- [ ] Verify query completes < 100ms
- [ ] Query today's count
- [ ] Verify query completes < 50ms
- [ ] Query streak
- [ ] Verify query completes < 100ms

### App Launch Speed
- [ ] Force-close app
- [ ] Relaunch app with stopwatch
- [ ] Verify app launches < 2 seconds
- [ ] Verify UI shows immediately
- [ ] Verify cached data loads instantly

### Memory Usage
- [ ] Start tracking
- [ ] Let app run for 30 minutes
- [ ] Check memory usage (Android Profiler)
- [ ] Verify no memory leaks
- [ ] Verify memory usage stable

## Edge Cases

### Rapid Start/Stop
- [ ] Start tracking
- [ ] Immediately stop
- [ ] Start again
- [ ] Stop again
- [ ] Repeat 5 times
- [ ] Verify all sessions recorded
- [ ] Verify no crashes

### Concurrent Operations
- [ ] Start upload
- [ ] Immediately stop tracking
- [ ] Verify both operations complete
- [ ] Verify no database locks
- [ ] Verify state consistent

### Large Session
- [ ] Start tracking
- [ ] Let run for several hours
- [ ] Upload multiple batches
- [ ] Stop tracking
- [ ] Verify session stats accurate
- [ ] Verify no performance degradation

## Regression Tests

### Existing Features Still Work
- [ ] Sign in/out works
- [ ] Onboarding works
- [ ] Settings work
- [ ] Preferences sync works
- [ ] Location sharing works
- [ ] Battery optimization prompt works
- [ ] Notifications work
- [ ] Sensors display correctly

## Known Issues Verification

### Issue: State lost on restart
- [ ] Verify FIXED: State persists across restarts

### Issue: Daily pot requires backend call
- [ ] Verify FIXED: Loads from cache first

### Issue: No tracking history
- [ ] Verify FIXED: All sessions recorded

### Issue: Upload stats not persisted
- [ ] Verify FIXED: Stats in database

## Final Checks

- [ ] No errors in logs
- [ ] No crashes during testing
- [ ] All features working as expected
- [ ] Performance acceptable
- [ ] Battery usage acceptable
- [ ] Database size reasonable

## Test Results Summary

**Date**: _______________
**Tester**: _______________
**Device**: _______________
**Android Version**: _______________

**Total Tests**: _____ / _____
**Passed**: _____
**Failed**: _____
**Skipped**: _____

**Critical Issues Found**: _____

**Notes**:
_______________________________________________________________
_______________________________________________________________
_______________________________________________________________

## Next Steps After Testing

If all tests pass:
- [ ] Create pull request
- [ ] Request code review
- [ ] Deploy to beta testers
- [ ] Monitor crash reports
- [ ] Gather user feedback

If tests fail:
- [ ] Document failures in detail
- [ ] Create bug tickets
- [ ] Fix critical issues
- [ ] Re-test
- [ ] Repeat until all pass
