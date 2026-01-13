import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/foundation.dart';
import '../data/models/daily_pot.dart';
import '../data/local/database_helper.dart';
import 'network/backend_client.dart';
import '../core/events/app_events.dart';

/// Daily pot service - Honeygain-style reward system with offline-first caching
/// Upload data → unlock pot → claim random credits (10-100)
///
/// Enhanced with:
/// - Local caching for offline resilience
/// - Optimistic updates for instant UI feedback
/// - Automatic background sync
class DailyPotService {
  static final DailyPotService _instance = DailyPotService._();
  static DailyPotService get instance => _instance;

  DailyPotService._() {
    // Listen to auth state changes - re-initialize when user signs in/out
    FirebaseAuth.instance.authStateChanges().listen((user) {
      _isInitialized = false; // Reset flag
      initialize(); // Re-initialize with new auth state
    });
  }

  final _db = DatabaseHelper.instance;
  final _potNotifier = ValueNotifier<DailyPot?>(null);
  ValueListenable<DailyPot?> get pot => _potNotifier;

  bool _isInitialized = false;
  bool _isSyncing = false;

  /// Initialize and fetch current pot state
  /// All users must be authenticated (no anonymous users)
  Future<void> initialize() async {
    if (_isInitialized) return;

    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        debugPrint('DailyPot: User not signed in');
        _potNotifier.value = null;
        return;
      }

      // Try to load from cache first for instant UI
      final cached = await _db.getCachedDailyPot(user.uid);
      if (cached != null) {
        _potNotifier.value = cached;
        debugPrint('DailyPot: Loaded from cache (${cached.uploadsToday}/${cached.uploadsRequired})');
      }

      // Then fetch fresh data from backend
      await fetchPotState();
      _isInitialized = true;
    } catch (e) {
      debugPrint('DailyPot: Failed to initialize: $e');
      _potNotifier.value = null;
    }
  }

  /// Fetch current pot state from backend and cache it
  Future<void> fetchPotState() async {
    if (_isSyncing) return; // Prevent concurrent syncs
    _isSyncing = true;

    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        _potNotifier.value = null;
        return;
      }

      final response = await BackendClient.get('/daily-pot');

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        final pot = DailyPot.fromJson(data);

        // Update both cache and UI
        await _db.cacheDailyPot(pot);
        _potNotifier.value = pot;

        debugPrint('DailyPot: Synced with backend (${pot.uploadsToday}/${pot.uploadsRequired})');
      } else {
        debugPrint('DailyPot: Failed to fetch state: ${response.statusCode}');
      }
    } catch (e) {
      debugPrint('DailyPot: Error fetching pot state: $e');
    } finally {
      _isSyncing = false;
    }
  }

  /// Record an upload (called by upload service)
  /// Increments progress toward unlocking pot
  /// Uses optimistic updates for instant UI feedback
  Future<void> recordUpload() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        return; // No user signed in
      }

      final current = _potNotifier.value;

      // Optimistic update - instant UI feedback
      if (current != null && !current.hasClaimedToday) {
        final optimistic = current.copyWith(
          uploadsToday: current.uploadsToday + 1,
          isUnlocked: (current.uploadsToday + 1) >= current.uploadsRequired,
        );

        _potNotifier.value = optimistic;
        await _db.cacheDailyPot(optimistic);

        // Emit event for UI
        AppEventBus.instance.emit(DailyPotUpdatedEvent(optimistic));

        if (optimistic.isUnlocked && !current.isUnlocked) {
          debugPrint('🍯 Daily pot unlocked! ${optimistic.uploadsToday}/${optimistic.uploadsRequired} uploads');
        }
      }

      // Background sync with backend to confirm state
      final response = await BackendClient.post('/daily-pot/upload', {});

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        final confirmed = DailyPot.fromJson(data);

        // Update with confirmed state
        await _db.cacheDailyPot(confirmed);
        _potNotifier.value = confirmed;

        // Emit event if state changed
        if (current == null ||
            current.uploadsToday != confirmed.uploadsToday ||
            current.isUnlocked != confirmed.isUnlocked) {
          AppEventBus.instance.emit(DailyPotUpdatedEvent(confirmed));
        }
      }
    } catch (e) {
      debugPrint('DailyPot: Error recording upload: $e');
      // Optimistic update is kept even if backend sync fails
    }
  }

  /// Claim pot (if unlocked)
  /// Returns claimed amount or 0 if already claimed/not unlocked
  Future<int> claimPot() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null) {
        throw Exception('Sign in required to claim rewards');
      }

      final current = _potNotifier.value;
      if (current == null || !current.canClaim) {
        debugPrint('DailyPot: Cannot claim - not unlocked or already claimed');
        return 0;
      }

      final response = await BackendClient.post('/daily-pot/claim', {});

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        final updated = DailyPot.fromJson(data);
        final claimedAmount = data['claimed_amount'] as int;

        // Update cache and UI
        await _db.cacheDailyPot(updated);
        _potNotifier.value = updated;

        // Emit events
        AppEventBus.instance.emit(DailyPotUpdatedEvent(updated));
        AppEventBus.instance.emit(DailyPotClaimedEvent(
          amount: claimedAmount,
          timestamp: DateTime.now(),
        ));

        debugPrint('🍯 Pot claimed! +$claimedAmount credits');
        return claimedAmount;
      } else if (response.statusCode == 409) {
        // Already claimed today (race condition)
        debugPrint('DailyPot: Already claimed (race condition)');
        await fetchPotState();
        return 0;
      } else {
        debugPrint('DailyPot: Failed to claim: ${response.statusCode}');
        return 0;
      }
    } catch (e) {
      debugPrint('DailyPot: Error claiming pot: $e');
      return 0;
    }
  }

  /// Get cached pot state (for offline access)
  Future<DailyPot?> getCachedPot() async {
    final user = FirebaseAuth.instance.currentUser;
    if (user == null) return null;

    return await _db.getCachedDailyPot(user.uid);
  }

  /// Clear cache (e.g., on sign out)
  Future<void> clearCache() async {
    await _db.clearDailyPotCache();
    _potNotifier.value = null;
    debugPrint('DailyPot: Cache cleared');
  }

  void dispose() {
    _potNotifier.dispose();
  }
}
