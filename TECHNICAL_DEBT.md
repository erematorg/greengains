# Technical Debt & Band-Aids to Fix

This document tracks temporary solutions ("band-aids") that need proper implementation for production scalability.

---

## 🗺️ **Coverage Map & H3 Tiles**

### Current Band-Aid
- **Circular overlays** instead of proper H3 hexagons
- **Rounded lat/lon grouping** (3 decimal places) instead of proper H3 indices
- **Temporary tile IDs**: `tile_48.078_7.343` instead of real H3 indices

### Proper Fix Needed
1. **Backend**: Install `h3-js` library
   ```bash
   npm install h3-js
   ```
2. **Backend**: Compute H3 indices from lat/lon in upload handler
   ```typescript
   import { latLngToCell } from 'h3-js';
   const h3Index = latLngToCell(lat, lon, 9); // Resolution 9 = ~170m hexagons
   ```
3. **Backend**: Store H3 index in `batch_json.location.h3Index`
4. **Backend**: Use H3 library to compute hexagon boundaries
   ```typescript
   import { cellToBoundary } from 'h3-js';
   const boundary = cellToBoundary(h3Index);
   ```
5. **Flutter**: Remove circular boundary generation
6. **Flutter**: Use proper H3 boundaries from backend

**Files to update:**
- `backend/src/routes/upload.ts` - Add H3 computation on upload
- `backend/src/routes/user.ts` - Return proper H3 boundaries
- `lib/screens/home_screen.dart` - Remove circular boundary hack

---

## 📡 **Location Upload & Storage**

### Current Band-Aid
- Location data is **averaged** across entire 5-minute batch
- Only one lat/lon per batch instead of per-reading granularity

### Proper Fix Needed
Decision required: Do we want:
- **Option A**: Keep averaged location (simpler, more privacy-friendly)
- **Option B**: Per-reading location for fine-grained mapping

If keeping averaged (recommended):
- Document this as intentional design
- Add comments explaining privacy/efficiency trade-off

**Files to review:**
- `android/app/src/main/kotlin/com/eremat/greengains/service/NativeBackendUploader.kt`

---

## 🔐 **Authentication & API Keys**

### Current Band-Aid
- Backend API key defaults to empty string
- Warning logs: "Backend API key not provided!"

### Proper Fix Needed
1. **Create `dart_defines.json`** in project root:
   ```json
   {
     "BACKEND_API_KEY": "your-production-key-here"
   }
   ```
2. **Generate proper API key** on backend deployment
3. **Update build commands** to use dart-defines
4. **Document** in README how to set up API keys

**Files to update:**
- `README.md` - Add API key setup instructions
- `.gitignore` - Ensure dart_defines.json is ignored
- `lib/services/network/backend_client.dart` - Keep current implementation

---

## 🎯 **Daily Pot Interaction**

### ✅ Fixed (2026-01-19)
**Issue 1 - No feedback when locked:**
- Tapping locked pot did nothing (felt broken)
- **Fixed**: Added helpful feedback messages:
  - "Need X more uploads to unlock"
  - "Already claimed today! Come back tomorrow"

**Issue 2 - Uploads not counting properly (CRITICAL BUG):**
- Upload counter didn't reset on new days
- Continued incrementing from previous day's count
- Caused pot to never unlock at expected 5 uploads
- **Root cause**: Backend logic used `last_claim_date` to determine new day, but this doesn't work if user never claims
- **Fixed**: Proper daily reset logic using `updated_at` timestamp to detect new days

**Files fixed:**
- `lib/widgets/daily_pot_icon.dart` - Added tap feedback
- `backend/src/routes/daily-pot.ts` - Fixed daily reset logic

---

## 🔄 **Auto-Deploy & CI/CD**

### Current Band-Aid
- Manual PR merges to trigger Render deploys
- No automated testing before deployment

### Proper Fix Needed
1. **Set up GitHub Actions** for automated tests
2. **Configure Render** to auto-deploy on merge to main
3. **Add pre-commit hooks** for linting/formatting
4. **Set up staging environment** for testing before production

**Files to create:**
- `.github/workflows/test.yml`
- `.github/workflows/deploy.yml`
- `package.json` - Add lint/test scripts

---

## 📊 **Database Schema & Indexing**

### ✅ Fixed (2026-01-19)
- **Created migration**: `backend/db/20260119_add_user_query_indexes.sql`
- **Composite index**: `idx_sensor_batches_user_time` on (user_id, timestamp_utc DESC)
- **Location GIN index**: `idx_sensor_batches_location` for JSON location queries
- These indexes optimize the tiles endpoint and coverage map queries

### Future Considerations (if implementing H3)
- Consider extracting h3_index to a stored generated column when implementing proper H3
- For now, rounded lat/lon grouping with GIN indexes is sufficient

---

## 🧪 **Testing & Quality**

### Current Band-Aid
- No automated tests
- Manual testing only

### Proper Fix Needed
1. **Backend tests**: Jest + Supertest
2. **Flutter tests**: Widget tests for main screens
3. **Integration tests**: End-to-end upload flow
4. **Load testing**: Simulate 1000+ concurrent users

**Directories to create:**
- `backend/tests/`
- `test/` (Flutter)

---

## 🏗️ **Code Quality**

### Current Technical Debt
- Debug logging left in production code
- Magic numbers (e.g., `0.001` for radius, `3` for decimal rounding)
- Commented-out code blocks
- TODO comments throughout

### Cleanup Needed
1. **Remove debug logs** or gate behind `DEBUG` flag
2. **Extract magic numbers** to named constants
3. **Delete commented code** (it's in git history)
4. **Convert TODOs to GitHub issues**

**Files needing cleanup:**
- `backend/src/routes/user.ts` - Remove TODO comments
- `lib/screens/home_screen.dart` - Remove debug prints
- `android/.../NativeBackendUploader.kt` - Clean up debug logs

---

## 📈 **Scalability Concerns**

### Current Limitations
1. **No pagination** on tiles endpoint - will break with 1000+ tiles
2. **No caching** - every refresh hits database
3. **No rate limiting** on API endpoints
4. **Synchronous uploads** - could bottleneck

### Future Improvements
1. Add pagination/infinite scroll for tiles
2. Implement Redis caching for frequently-accessed data
3. Add rate limiting with `fastify-rate-limit`
4. Consider async job queue for upload processing

---

## 🎨 **UI/UX Polish**

### Current Issues
1. Map loads default Colmar location before user tiles load
2. No loading skeleton for map widget
3. No error state if tiles fail to load
4. Circular tiles look temporary (because they are!)

### Improvements Needed
1. Show loading skeleton during tile fetch
2. Better empty state messaging
3. Proper H3 hexagons (see top section)
4. Smooth transitions when tiles load

---

## 🔧 **Build & Dependencies**

### Current Warnings
```
Warning: Flutter AGP 8.2.1 will be deprecated
Warning: Kotlin 1.8.22 will be deprecated
```

### Fix Needed
Update `android/build.gradle`:
```gradle
buildscript {
  ext.kotlin_version = '2.1.0'
  dependencies {
    classpath 'com.android.tools.build:gradle:8.6.0'
  }
}
```

---

## ✅ **Priority Order (Recommended)**

1. **HIGH - Production Critical**
   - [ ] Add H3 library and compute proper hexagons
   - [ ] Set up API keys properly
   - [x] Add database indexes for performance
   - [x] Fix daily pot interaction

2. **MEDIUM - Important for Scale**
   - [ ] Add pagination to tiles endpoint
   - [ ] Set up automated testing
   - [ ] Configure proper CI/CD
   - [ ] Add caching layer

3. **LOW - Quality of Life**
   - [ ] Clean up debug logs
   - [ ] Update Gradle/Kotlin versions
   - [ ] Add loading states
   - [ ] UI polish

---

**Last Updated**: 2026-01-19
**Session**: Code Cleanup + Database Performance Optimization
