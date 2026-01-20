// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for English (`en`).
class AppLocalizationsEn extends AppLocalizations {
  AppLocalizationsEn([String locale = 'en']) : super(locale);

  @override
  String get onboardingWelcomeTitle => 'Welcome to GreenGains';

  @override
  String get onboardingWelcomeSubtitle =>
      'Help create greener cities by passively collecting environmental sensor data.';

  @override
  String get onboardingFeature1Title => 'Passive Collection';

  @override
  String get onboardingFeature1Description =>
      'Runs in the background while you go about your day. No interaction needed.';

  @override
  String get onboardingFeature2Title => 'Privacy First';

  @override
  String get onboardingFeature2Description =>
      'Location sharing is optional and uses coarse positioning only.';

  @override
  String get onboardingFeature3Title => 'Track Your Impact';

  @override
  String get onboardingFeature3Description =>
      'See your contributions and help map environmental data for your city.';

  @override
  String get onboardingSignInTitle => 'Unlock Rewards';

  @override
  String get onboardingSignInSubtitle =>
      'Sign in to access daily pot rewards and sync your data across devices.';

  @override
  String get onboardingDailyPotRewards => 'Daily Pot Rewards';

  @override
  String get onboardingDailyPotDescription => 'Earn 10-100 credits every day';

  @override
  String get onboardingCloudSync => 'Cloud Sync';

  @override
  String get onboardingCloudSyncDescription => 'Access your data on any device';

  @override
  String get onboardingFutureFeatures => 'Future Features';

  @override
  String get onboardingFutureDescription => 'Leaderboards and competitions';

  @override
  String onboardingPrivacyNotice(String privacyPolicy, String termsOfService) {
    return 'By continuing, you agree to our $privacyPolicy and $termsOfService.';
  }

  @override
  String get privacyPolicy => 'Privacy Policy';

  @override
  String get termsOfService => 'Terms of Service';

  @override
  String get buttonPrevious => 'Previous';

  @override
  String get buttonNext => 'Next';

  @override
  String get signInSuccess => 'Signed in successfully';

  @override
  String get signInError => 'Sign-in cancelled or failed';

  @override
  String get navHome => 'Home';

  @override
  String get navProfile => 'Profile';

  @override
  String get navSettings => 'Settings';

  @override
  String get homeTitle => 'GreenGains';

  @override
  String get startTracking => 'Start Tracking';

  @override
  String get stopTracking => 'Stop Tracking';

  @override
  String get trackingActive => 'Tracking Active';

  @override
  String get trackingPaused => 'Tracking Paused';

  @override
  String get trackingStopped => 'Tracking Stopped';

  @override
  String get uploadSuccess => 'Upload successful';

  @override
  String get uploadFailed => 'Upload failed';

  @override
  String lastUpload(String time) {
    return 'Last upload: $time';
  }

  @override
  String get noUploadYet => 'No upload yet';

  @override
  String get dailyPotTitle => 'Daily Pot';

  @override
  String dailyPotClaimButton(int amount) {
    return 'Claim $amount Credits';
  }

  @override
  String dailyPotClaimed(int amount) {
    return '+$amount credits! 🍯';
  }

  @override
  String get dailyPotAlreadyClaimed =>
      'Already claimed today! Come back tomorrow';

  @override
  String dailyPotNeedMoreUploads(int count, String s) {
    return 'Need $count more upload$s to unlock';
  }

  @override
  String dailyPotProgress(int current, int required) {
    return '$current / $required uploads';
  }

  @override
  String credits(int count) {
    return '$count credits';
  }

  @override
  String get totalCredits => 'Total Credits';

  @override
  String get creditsEarned => 'Credits Earned';

  @override
  String get statsTitle => 'Your Impact';

  @override
  String get totalUploads => 'Total Uploads';

  @override
  String get todayUploads => 'Today\'s Uploads';

  @override
  String get coverageTiles => 'Coverage Tiles';

  @override
  String get dataCollected => 'Data Collected';

  @override
  String timesContributed(int count) {
    return '$count times contributed';
  }

  @override
  String get mapTitle => 'Coverage Map';

  @override
  String get mapRecenter => 'Recenter';

  @override
  String get mapZoomIn => 'Zoom In';

  @override
  String get mapZoomOut => 'Zoom Out';

  @override
  String get mapYourLocation => 'Your Location';

  @override
  String get mapCoverageLegend => 'Coverage';

  @override
  String get profileTitle => 'Profile';

  @override
  String get profileSignOut => 'Sign Out';

  @override
  String get profileSignedInAs => 'Signed in as';

  @override
  String profileMemberSince(String date) {
    return 'Member since $date';
  }

  @override
  String get profileDeleteAccount => 'Delete Account';

  @override
  String get profileDeleteConfirm => 'Are you sure? This cannot be undone.';

  @override
  String get settingsTitle => 'Settings';

  @override
  String get settingsGeneral => 'General';

  @override
  String get settingsPrivacy => 'Privacy & Data';

  @override
  String get settingsAbout => 'About';

  @override
  String get settingsLanguage => 'Language';

  @override
  String get settingsTheme => 'Theme';

  @override
  String get settingsNotifications => 'Notifications';

  @override
  String get settingsLocationSharing => 'Location Sharing';

  @override
  String get settingsLocationEnabled => 'Location sharing enabled';

  @override
  String get settingsLocationDisabled => 'Location sharing disabled';

  @override
  String get settingsMobileData => 'Mobile Data Upload';

  @override
  String get settingsMobileDataEnabled => 'Upload on mobile data';

  @override
  String get settingsMobileDataDisabled => 'Upload on WiFi only';

  @override
  String settingsVersion(String version) {
    return 'Version $version';
  }

  @override
  String get permissionLocationTitle => 'Location Permission';

  @override
  String get permissionLocationMessage =>
      'GreenGains needs location access to collect environmental data.';

  @override
  String get permissionLocationButton => 'Grant Permission';

  @override
  String get permissionBatteryTitle => 'Battery Optimization';

  @override
  String get permissionBatteryMessage =>
      'Please disable battery optimization for reliable background tracking.';

  @override
  String get permissionBatteryButton => 'Open Settings';

  @override
  String get errorGeneric => 'Something went wrong. Please try again.';

  @override
  String get errorNetwork => 'No internet connection';

  @override
  String get errorLocationUnavailable => 'Location unavailable';

  @override
  String get errorUploadFailed => 'Upload failed. Will retry later.';

  @override
  String get errorSignInRequired => 'Please sign in to continue';

  @override
  String get buttonOk => 'OK';

  @override
  String get buttonCancel => 'Cancel';

  @override
  String get buttonYes => 'Yes';

  @override
  String get buttonNo => 'No';

  @override
  String get buttonSave => 'Save';

  @override
  String get buttonDelete => 'Delete';

  @override
  String get buttonClose => 'Close';

  @override
  String get buttonRetry => 'Retry';

  @override
  String get loading => 'Loading...';

  @override
  String get saving => 'Saving...';

  @override
  String get success => 'Success';

  @override
  String get error => 'Error';
}
