# Reactive Architecture - Test Plan

## Test Environment Setup
- Device: Android phone (real device preferred)
- Build: Debug build with logging enabled
- Prerequisites: Location permission granted, signed in

## Test Suite 1: TimeAgoText Auto-Updates

### Test 1.1: Basic Time Update
**Steps:**
1. Start tracking on Home screen
2. Wait for first upload to complete
3. Note "Last upload: just now" appears
4. Wait 1 minute without touching app
5. Verify text updates to "Last upload: 1m ago"
6. Wait another minute
7. Verify text updates to "Last upload: 2m ago"

**Expected:**
- Text auto-updates every 60 seconds
- No manual refresh needed
- Format is correct (just now → Xm ago → Xh ago → Xd ago)

**Console logs:**
```
[TimeAgoService] Started centralized timer
```

---

### Test 1.2: Multiple TimeAgoText Widgets
**Steps:**
1. Start tracking
2. Wait for upload
3. Navigate to Statistics screen (keep tracking)
4. Wait for another upload
5. Go back to Home screen
6. Observe both screens update timestamps

**Expected:**
- All timestamps update simultaneously every minute
- Only 1 timer running (check console logs)
- No performance degradation

---

### Test 1.3: App Backgrounding
**Steps:**
1. Start tracking
2. Wait for upload
3. Note timestamp "Last upload: 1m ago"
4. Background app for 5 minutes
5. Resume app
6. Verify timestamp shows "Last upload: 6m ago" (not frozen at 1m)

**Expected:**
- Timestamp catches up when app resumes
- TimeAgoService continues in background
- No crashes

---

## Test Suite 2: StatisticsScreen Reactivity

### Test 2.1: Stats Update on Upload
**Steps:**
1. Open Statistics screen
2. Note current "Total" count (e.g., 50)
3. Return to Home screen
4. Start tracking
5. Wait for upload to complete
6. Navigate back to Statistics screen

**Expected:**
- Total count increments without manual refresh
- Today count increments
- Chart updates

**Console logs:**
```
[EventBus] UploadSuccessEvent: samples=100, geohash=abc123
[EventBus] StatsUpdatedEvent: uploads=51, today=5, streak=3
```

---

### Test 2.2: Real-time Updates While Viewing
**Steps:**
1. Start tracking on Home screen
2. Navigate to Statistics screen
3. Leave Statistics screen open
4. Wait for upload to complete

**Expected:**
- Statistics screen updates in real-time
- No pull-to-refresh needed
- Smooth transition (no jarring reloads)

---

### Test 2.3: Multiple Rapid Uploads
**Steps:**
1. Start tracking
2. Open Statistics screen
3. Leave open for 5 minutes (multiple uploads)
4. Observe stats incrementing

**Expected:**
- Each upload triggers stats refresh
- No missed updates
- No excessive CPU usage

---

## Test Suite 3: ContributionStatsCard Reactivity

### Test 3.1: Home Screen Stats Update
**Steps:**
1. Note stats card on Home screen (Total: 50, Today: 5)
2. Start tracking
3. Wait for upload
4. Observe stats card

**Expected:**
- Pulse animation on update
- Total increments immediately
- Today increments immediately
- No manual refresh needed

---

### Test 3.2: Cross-Screen Synchronization
**Steps:**
1. View Home screen stats (Total: 50)
2. Navigate to Statistics screen
3. Pull-to-refresh Statistics
4. Navigate back to Home
5. Verify Home stats updated

**Expected:**
- Home screen shows updated stats
- StatsUpdatedEvent propagated
- Consistent data across screens

---

## Test Suite 4: Event Bus

### Test 4.1: Upload Success Event
**Steps:**
1. Clear app logs
2. Start tracking
3. Wait for upload
4. Check console logs

**Expected Console:**
```
[EventBus] UploadSuccessEvent: samples=100, geohash=abc123
[EventBus] StatsUpdatedEvent: uploads=51, today=6, streak=3
```

---

### Test 4.2: Upload Failure Event
**Steps:**
1. Enable airplane mode
2. Start tracking
3. Wait for upload attempt
4. Check console logs

**Expected Console:**
```
[EventBus] UploadFailedEvent: reason=Network error
```

**Expected UI:**
- Error message displays on Home screen
- "Last upload" timestamp unchanged
- Upload count unchanged

---

### Test 4.3: Memory Leak Check
**Steps:**
1. Navigate Home → Statistics → Home (10 times)
2. Check memory usage (Android Studio Profiler)

**Expected:**
- No increasing memory usage
- Event subscriptions properly cancelled
- No orphaned listeners

---

## Test Suite 5: Performance

### Test 5.1: Timer Efficiency
**Steps:**
1. Start app
2. Open Android Studio Profiler
3. Check CPU timeline
4. Look for timer ticks

**Expected:**
- Single timer running at 60s intervals
- Minimal CPU spikes
- No redundant timers

---

### Test 5.2: Event Overhead
**Steps:**
1. Start tracking
2. Leave running for 1 hour
3. Check battery usage
4. Check event log volume

**Expected:**
- Reasonable battery drain
- No excessive event spam
- Smooth UI throughout

---

## Test Suite 6: Edge Cases

### Test 6.1: First-Time User
**Steps:**
1. Fresh install
2. Complete onboarding
3. Don't start tracking yet
4. Check Home screen

**Expected:**
- No crashes
- "Ready to contribute?" message
- No "Last upload" shown (since none)

---

### Test 6.2: Service Crash Recovery
**Steps:**
1. Start tracking
2. Wait for upload
3. Force-stop app (Android Settings → Force Stop)
4. Reopen app

**Expected:**
- "Last upload: Xm ago" shows correct time
- Service auto-restarts
- Stats persist

---

### Test 6.3: Timestamp Far in Past
**Steps:**
1. Manually set last upload to 30 days ago (via preferences)
2. Open app
3. Check timestamp display

**Expected:**
- Shows "30d ago" (not crash)
- Updates correctly if new upload happens

---

## Test Suite 7: Integration

### Test 7.1: End-to-End Flow
**Steps:**
1. Fresh app state
2. Sign in
3. Start tracking
4. Wait for upload
5. Check Home screen stats
6. Check Statistics screen
7. Stop tracking
8. Check timestamps still update

**Expected:**
- Seamless experience
- All reactive elements work
- No manual refreshes needed

---

## Regression Tests

### Regression 1: Daily Pot Still Works
**Steps:**
1. Complete upload
2. Verify daily pot progress increments
3. Claim pot when available

**Expected:**
- Daily pot unaffected by reactive changes

---

### Regression 2: Sensor Data Display
**Steps:**
1. Start tracking
2. Expand sensor section
3. Verify sensor values updating

**Expected:**
- Sensors still stream data
- No interference from event system

---

## Automated Testing (Future)

```dart
// Unit test example for TimeAgoService
testWidgets('TimeAgoText updates every minute', (tester) async {
  final service = TimeAgoService.instance;
  service.start();

  final timestamp = DateTime.now().subtract(Duration(minutes: 5));

  await tester.pumpWidget(
    MaterialApp(
      home: Scaffold(
        body: TimeAgoText(timestamp: timestamp),
      ),
    ),
  );

  expect(find.text('5m ago'), findsOneWidget);

  // Advance time by 60 seconds
  await tester.pump(Duration(seconds: 60));

  expect(find.text('6m ago'), findsOneWidget);
});
```

## Pass Criteria

All tests must pass with:
- No crashes
- No memory leaks
- Consistent behavior
- Correct timing
- Proper event propagation

## Known Limitations

1. Timer updates every 60s, so "just now" may show for up to 59s
2. Background apps may have delayed updates (OS throttling)
3. Event history not persisted (lost on app restart)

## Performance Benchmarks

| Metric | Target | Current |
|--------|--------|---------|
| Timers running | 1 | 1 ✓ |
| Memory per listener | <1KB | TBD |
| Event latency | <100ms | TBD |
| CPU usage (idle) | <1% | TBD |
| Battery drain/hour | <5% | TBD |

---

## Post-Testing

After all tests pass:
1. Document any issues found
2. Update reactive architecture docs
3. Add comments to tricky code
4. Create example widgets for future reference
5. Consider adding automated tests
