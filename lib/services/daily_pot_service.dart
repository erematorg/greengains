import 'dart:convert';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/foundation.dart';
import '../data/models/daily_pot.dart';
import 'network/backend_client.dart';

/// Daily pot service - Honeygain-style reward system
/// Upload data → unlock pot → claim random credits (10-100)
class DailyPotService {
  static final DailyPotService _instance = DailyPotService._();
  static DailyPotService get instance => _instance;
  DailyPotService._();

  final _potNotifier = ValueNotifier<DailyPot?>(null);
  ValueListenable<DailyPot?> get pot => _potNotifier;

  bool _isInitialized = false;

  /// Initialize and fetch current pot state
  /// Only available for signed-in users (not anonymous)
  Future<void> initialize() async {
    if (_isInitialized) return;

    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null || user.isAnonymous) {
        debugPrint('DailyPot: Only available for signed-in users (not anonymous)');
        _potNotifier.value = null; // Hide daily pot for anonymous users
        return;
      }

      await fetchPotState();
      _isInitialized = true;
    } catch (e) {
      debugPrint('DailyPot: Failed to initialize: $e');
      _potNotifier.value = null;
    }
  }

  /// Fetch current pot state from backend
  /// Only available for signed-in users (not anonymous)
  Future<void> fetchPotState() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null || user.isAnonymous) {
        _potNotifier.value = null;
        return;
      }

      final response = await BackendClient.get('/daily-pot');

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        _potNotifier.value = DailyPot.fromJson(data);
      } else {
        debugPrint('DailyPot: Failed to fetch state: ${response.statusCode}');
      }
    } catch (e) {
      debugPrint('DailyPot: Error fetching pot state: $e');
    }
  }

  /// Record an upload (called by upload service)
  /// Increments progress toward unlocking pot
  /// Silently skips for anonymous users
  Future<void> recordUpload() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null || user.isAnonymous) {
        return; // Silently skip for anonymous users
      }

      final response = await BackendClient.post('/daily-pot/upload', {});

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body) as Map<String, dynamic>;
        _potNotifier.value = DailyPot.fromJson(data);

        // Log unlock event
        final pot = _potNotifier.value;
        if (pot != null && pot.isUnlocked && !pot.hasClaimedToday) {
          debugPrint('🍯 Daily pot unlocked! ${pot.uploadsToday}/${pot.uploadsRequired} uploads');
        }
      }
    } catch (e) {
      debugPrint('DailyPot: Error recording upload: $e');
    }
  }

  /// Claim pot (if unlocked)
  /// Returns claimed amount or 0 if already claimed/not unlocked
  /// Throws exception for anonymous users
  Future<int> claimPot() async {
    try {
      final user = FirebaseAuth.instance.currentUser;
      if (user == null || user.isAnonymous) {
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
        _potNotifier.value = DailyPot.fromJson(data);
        final claimedAmount = data['claimed_amount'] as int;
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

  void dispose() {
    _potNotifier.dispose();
  }
}
