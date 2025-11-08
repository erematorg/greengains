import '../../core/app_preferences.dart';

/// Handles upload policy decisions before hitting the network.
class UploadManager {
  /// Returns true when uploads are permitted on the current connection.
  ///
  /// Uploads are always allowed when the device is on Wi-Fi. If the device is
  /// on mobile data we respect the persisted preference exposed in settings.
  static Future<bool> canUploadOnConnection({
    required bool isWifiConnected,
  }) async {
    if (isWifiConnected) {
      return true;
    }
    await AppPreferences.instance.ensureInitialized();
    return AppPreferences.instance.useMobileUploads;
  }

  /// Executes [uploader] only when uploads are permitted given the current
  /// connectivity state.
  ///
  /// Provide a callback that performs the actual upload work. The callback
  /// will be skipped when mobile uploads are disabled and Wi-Fi is
  /// unavailable. The method returns `true` when the upload ran.
  static Future<bool> uploadPendingBatches({
    required bool isWifiConnected,
    required Future<void> Function() uploader,
  }) async {
    final allowed =
        await canUploadOnConnection(isWifiConnected: isWifiConnected);
    if (!allowed) {
      return false;
    }
    await uploader();
    return true;
  }
}
