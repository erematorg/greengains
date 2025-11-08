/// Stub for BatteryService - will be properly implemented in Phase 2
/// This allows the codebase to compile while sensors are not yet configured
class BatteryService {
  BatteryService._();
  static final BatteryService instance = BatteryService._();

  Future<Map<String, dynamic>?> getBatteryInfo() async {
    // Stub - will be implemented with battery_plus in Phase 2
    return null;
  }
}
