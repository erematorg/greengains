import 'dart:async';

import 'package:flutter/foundation.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:intl/intl.dart' as intl;

import 'app_localizations_en.dart';
import 'app_localizations_fr.dart';

// ignore_for_file: type=lint

/// Callers can lookup localized strings with an instance of AppLocalizations
/// returned by `AppLocalizations.of(context)`.
///
/// Applications need to include `AppLocalizations.delegate()` in their app's
/// `localizationDelegates` list, and the locales they support in the app's
/// `supportedLocales` list. For example:
///
/// ```dart
/// import 'l10n/app_localizations.dart';
///
/// return MaterialApp(
///   localizationsDelegates: AppLocalizations.localizationsDelegates,
///   supportedLocales: AppLocalizations.supportedLocales,
///   home: MyApplicationHome(),
/// );
/// ```
///
/// ## Update pubspec.yaml
///
/// Please make sure to update your pubspec.yaml to include the following
/// packages:
///
/// ```yaml
/// dependencies:
///   # Internationalization support.
///   flutter_localizations:
///     sdk: flutter
///   intl: any # Use the pinned version from flutter_localizations
///
///   # Rest of dependencies
/// ```
///
/// ## iOS Applications
///
/// iOS applications define key application metadata, including supported
/// locales, in an Info.plist file that is built into the application bundle.
/// To configure the locales supported by your app, you’ll need to edit this
/// file.
///
/// First, open your project’s ios/Runner.xcworkspace Xcode workspace file.
/// Then, in the Project Navigator, open the Info.plist file under the Runner
/// project’s Runner folder.
///
/// Next, select the Information Property List item, select Add Item from the
/// Editor menu, then select Localizations from the pop-up menu.
///
/// Select and expand the newly-created Localizations item then, for each
/// locale your application supports, add a new item and select the locale
/// you wish to add from the pop-up menu in the Value field. This list should
/// be consistent with the languages listed in the AppLocalizations.supportedLocales
/// property.
abstract class AppLocalizations {
  AppLocalizations(String locale)
      : localeName = intl.Intl.canonicalizedLocale(locale.toString());

  final String localeName;

  static AppLocalizations? of(BuildContext context) {
    return Localizations.of<AppLocalizations>(context, AppLocalizations);
  }

  static const LocalizationsDelegate<AppLocalizations> delegate =
      _AppLocalizationsDelegate();

  /// A list of this localizations delegate along with the default localizations
  /// delegates.
  ///
  /// Returns a list of localizations delegates containing this delegate along with
  /// GlobalMaterialLocalizations.delegate, GlobalCupertinoLocalizations.delegate,
  /// and GlobalWidgetsLocalizations.delegate.
  ///
  /// Additional delegates can be added by appending to this list in
  /// MaterialApp. This list does not have to be used at all if a custom list
  /// of delegates is preferred or required.
  static const List<LocalizationsDelegate<dynamic>> localizationsDelegates =
      <LocalizationsDelegate<dynamic>>[
    delegate,
    GlobalMaterialLocalizations.delegate,
    GlobalCupertinoLocalizations.delegate,
    GlobalWidgetsLocalizations.delegate,
  ];

  /// A list of this localizations delegate's supported locales.
  static const List<Locale> supportedLocales = <Locale>[
    Locale('en'),
    Locale('fr')
  ];

  /// No description provided for @onboardingWelcomeTitle.
  ///
  /// In en, this message translates to:
  /// **'Welcome to GreenGains'**
  String get onboardingWelcomeTitle;

  /// No description provided for @onboardingWelcomeSubtitle.
  ///
  /// In en, this message translates to:
  /// **'Help create greener cities by passively collecting environmental sensor data.'**
  String get onboardingWelcomeSubtitle;

  /// No description provided for @onboardingFeature1Title.
  ///
  /// In en, this message translates to:
  /// **'Passive Collection'**
  String get onboardingFeature1Title;

  /// No description provided for @onboardingFeature1Description.
  ///
  /// In en, this message translates to:
  /// **'Runs in the background while you go about your day. No interaction needed.'**
  String get onboardingFeature1Description;

  /// No description provided for @onboardingFeature2Title.
  ///
  /// In en, this message translates to:
  /// **'Privacy First'**
  String get onboardingFeature2Title;

  /// No description provided for @onboardingFeature2Description.
  ///
  /// In en, this message translates to:
  /// **'Location sharing is optional and uses coarse positioning only.'**
  String get onboardingFeature2Description;

  /// No description provided for @onboardingFeature3Title.
  ///
  /// In en, this message translates to:
  /// **'Track Your Impact'**
  String get onboardingFeature3Title;

  /// No description provided for @onboardingFeature3Description.
  ///
  /// In en, this message translates to:
  /// **'See your contributions and help map environmental data for your city.'**
  String get onboardingFeature3Description;

  /// No description provided for @onboardingSignInTitle.
  ///
  /// In en, this message translates to:
  /// **'Unlock Rewards'**
  String get onboardingSignInTitle;

  /// No description provided for @onboardingSignInSubtitle.
  ///
  /// In en, this message translates to:
  /// **'Sign in to access daily pot rewards and sync your data across devices.'**
  String get onboardingSignInSubtitle;

  /// No description provided for @onboardingDailyPotRewards.
  ///
  /// In en, this message translates to:
  /// **'Daily Pot Rewards'**
  String get onboardingDailyPotRewards;

  /// No description provided for @onboardingDailyPotDescription.
  ///
  /// In en, this message translates to:
  /// **'Earn 10-100 credits every day'**
  String get onboardingDailyPotDescription;

  /// No description provided for @onboardingCloudSync.
  ///
  /// In en, this message translates to:
  /// **'Cloud Sync'**
  String get onboardingCloudSync;

  /// No description provided for @onboardingCloudSyncDescription.
  ///
  /// In en, this message translates to:
  /// **'Access your data on any device'**
  String get onboardingCloudSyncDescription;

  /// No description provided for @onboardingFutureFeatures.
  ///
  /// In en, this message translates to:
  /// **'Future Features'**
  String get onboardingFutureFeatures;

  /// No description provided for @onboardingFutureDescription.
  ///
  /// In en, this message translates to:
  /// **'Leaderboards and competitions'**
  String get onboardingFutureDescription;

  /// No description provided for @onboardingPrivacyNotice.
  ///
  /// In en, this message translates to:
  /// **'By continuing, you agree to our {privacyPolicy} and {termsOfService}.'**
  String onboardingPrivacyNotice(String privacyPolicy, String termsOfService);

  /// No description provided for @privacyPolicy.
  ///
  /// In en, this message translates to:
  /// **'Privacy Policy'**
  String get privacyPolicy;

  /// No description provided for @termsOfService.
  ///
  /// In en, this message translates to:
  /// **'Terms of Service'**
  String get termsOfService;

  /// No description provided for @buttonPrevious.
  ///
  /// In en, this message translates to:
  /// **'Previous'**
  String get buttonPrevious;

  /// No description provided for @buttonNext.
  ///
  /// In en, this message translates to:
  /// **'Next'**
  String get buttonNext;

  /// No description provided for @signInSuccess.
  ///
  /// In en, this message translates to:
  /// **'Signed in successfully'**
  String get signInSuccess;

  /// No description provided for @signInError.
  ///
  /// In en, this message translates to:
  /// **'Sign-in cancelled or failed'**
  String get signInError;

  /// No description provided for @navHome.
  ///
  /// In en, this message translates to:
  /// **'Home'**
  String get navHome;

  /// No description provided for @navProfile.
  ///
  /// In en, this message translates to:
  /// **'Profile'**
  String get navProfile;

  /// No description provided for @navSettings.
  ///
  /// In en, this message translates to:
  /// **'Settings'**
  String get navSettings;

  /// No description provided for @homeTitle.
  ///
  /// In en, this message translates to:
  /// **'GreenGains'**
  String get homeTitle;

  /// No description provided for @startTracking.
  ///
  /// In en, this message translates to:
  /// **'Start Tracking'**
  String get startTracking;

  /// No description provided for @stopTracking.
  ///
  /// In en, this message translates to:
  /// **'Stop Tracking'**
  String get stopTracking;

  /// No description provided for @trackingActive.
  ///
  /// In en, this message translates to:
  /// **'Tracking Active'**
  String get trackingActive;

  /// No description provided for @trackingPaused.
  ///
  /// In en, this message translates to:
  /// **'Tracking Paused'**
  String get trackingPaused;

  /// No description provided for @trackingStopped.
  ///
  /// In en, this message translates to:
  /// **'Tracking Stopped'**
  String get trackingStopped;

  /// No description provided for @uploadSuccess.
  ///
  /// In en, this message translates to:
  /// **'Upload successful'**
  String get uploadSuccess;

  /// No description provided for @uploadFailed.
  ///
  /// In en, this message translates to:
  /// **'Upload failed'**
  String get uploadFailed;

  /// No description provided for @lastUpload.
  ///
  /// In en, this message translates to:
  /// **'Last upload: {time}'**
  String lastUpload(String time);

  /// No description provided for @noUploadYet.
  ///
  /// In en, this message translates to:
  /// **'No upload yet'**
  String get noUploadYet;

  /// No description provided for @dailyPotTitle.
  ///
  /// In en, this message translates to:
  /// **'Daily Pot'**
  String get dailyPotTitle;

  /// No description provided for @dailyPotClaimButton.
  ///
  /// In en, this message translates to:
  /// **'Claim {amount} Credits'**
  String dailyPotClaimButton(int amount);

  /// No description provided for @dailyPotClaimed.
  ///
  /// In en, this message translates to:
  /// **'+{amount} credits! 🍯'**
  String dailyPotClaimed(int amount);

  /// No description provided for @dailyPotAlreadyClaimed.
  ///
  /// In en, this message translates to:
  /// **'Already claimed today! Come back tomorrow'**
  String get dailyPotAlreadyClaimed;

  /// No description provided for @dailyPotNeedMoreUploads.
  ///
  /// In en, this message translates to:
  /// **'Need {count} more upload{s} to unlock'**
  String dailyPotNeedMoreUploads(int count, String s);

  /// No description provided for @dailyPotProgress.
  ///
  /// In en, this message translates to:
  /// **'{current} / {required} uploads'**
  String dailyPotProgress(int current, int required);

  /// No description provided for @credits.
  ///
  /// In en, this message translates to:
  /// **'{count} credits'**
  String credits(int count);

  /// No description provided for @totalCredits.
  ///
  /// In en, this message translates to:
  /// **'Total Credits'**
  String get totalCredits;

  /// No description provided for @creditsEarned.
  ///
  /// In en, this message translates to:
  /// **'Credits Earned'**
  String get creditsEarned;

  /// No description provided for @statsTitle.
  ///
  /// In en, this message translates to:
  /// **'Your Impact'**
  String get statsTitle;

  /// No description provided for @totalUploads.
  ///
  /// In en, this message translates to:
  /// **'Total Uploads'**
  String get totalUploads;

  /// No description provided for @todayUploads.
  ///
  /// In en, this message translates to:
  /// **'Today\'s Uploads'**
  String get todayUploads;

  /// No description provided for @coverageTiles.
  ///
  /// In en, this message translates to:
  /// **'Coverage Tiles'**
  String get coverageTiles;

  /// No description provided for @dataCollected.
  ///
  /// In en, this message translates to:
  /// **'Data Collected'**
  String get dataCollected;

  /// No description provided for @timesContributed.
  ///
  /// In en, this message translates to:
  /// **'{count} times contributed'**
  String timesContributed(int count);

  /// No description provided for @mapTitle.
  ///
  /// In en, this message translates to:
  /// **'Coverage Map'**
  String get mapTitle;

  /// No description provided for @mapRecenter.
  ///
  /// In en, this message translates to:
  /// **'Recenter'**
  String get mapRecenter;

  /// No description provided for @mapZoomIn.
  ///
  /// In en, this message translates to:
  /// **'Zoom In'**
  String get mapZoomIn;

  /// No description provided for @mapZoomOut.
  ///
  /// In en, this message translates to:
  /// **'Zoom Out'**
  String get mapZoomOut;

  /// No description provided for @mapYourLocation.
  ///
  /// In en, this message translates to:
  /// **'Your Location'**
  String get mapYourLocation;

  /// No description provided for @mapCoverageLegend.
  ///
  /// In en, this message translates to:
  /// **'Coverage'**
  String get mapCoverageLegend;

  /// No description provided for @profileTitle.
  ///
  /// In en, this message translates to:
  /// **'Profile'**
  String get profileTitle;

  /// No description provided for @profileSignOut.
  ///
  /// In en, this message translates to:
  /// **'Sign Out'**
  String get profileSignOut;

  /// No description provided for @profileSignedInAs.
  ///
  /// In en, this message translates to:
  /// **'Signed in as'**
  String get profileSignedInAs;

  /// No description provided for @profileMemberSince.
  ///
  /// In en, this message translates to:
  /// **'Member since {date}'**
  String profileMemberSince(String date);

  /// No description provided for @profileDeleteAccount.
  ///
  /// In en, this message translates to:
  /// **'Delete Account'**
  String get profileDeleteAccount;

  /// No description provided for @profileDeleteConfirm.
  ///
  /// In en, this message translates to:
  /// **'Are you sure? This cannot be undone.'**
  String get profileDeleteConfirm;

  /// No description provided for @settingsTitle.
  ///
  /// In en, this message translates to:
  /// **'Settings'**
  String get settingsTitle;

  /// No description provided for @settingsGeneral.
  ///
  /// In en, this message translates to:
  /// **'General'**
  String get settingsGeneral;

  /// No description provided for @settingsPrivacy.
  ///
  /// In en, this message translates to:
  /// **'Privacy & Data'**
  String get settingsPrivacy;

  /// No description provided for @settingsAbout.
  ///
  /// In en, this message translates to:
  /// **'About'**
  String get settingsAbout;

  /// No description provided for @settingsLanguage.
  ///
  /// In en, this message translates to:
  /// **'Language'**
  String get settingsLanguage;

  /// No description provided for @settingsTheme.
  ///
  /// In en, this message translates to:
  /// **'Theme'**
  String get settingsTheme;

  /// No description provided for @settingsNotifications.
  ///
  /// In en, this message translates to:
  /// **'Notifications'**
  String get settingsNotifications;

  /// No description provided for @settingsLocationSharing.
  ///
  /// In en, this message translates to:
  /// **'Location Sharing'**
  String get settingsLocationSharing;

  /// No description provided for @settingsLocationEnabled.
  ///
  /// In en, this message translates to:
  /// **'Location sharing enabled'**
  String get settingsLocationEnabled;

  /// No description provided for @settingsLocationDisabled.
  ///
  /// In en, this message translates to:
  /// **'Location sharing disabled'**
  String get settingsLocationDisabled;

  /// No description provided for @settingsMobileData.
  ///
  /// In en, this message translates to:
  /// **'Mobile Data Upload'**
  String get settingsMobileData;

  /// No description provided for @settingsMobileDataEnabled.
  ///
  /// In en, this message translates to:
  /// **'Upload on mobile data'**
  String get settingsMobileDataEnabled;

  /// No description provided for @settingsMobileDataDisabled.
  ///
  /// In en, this message translates to:
  /// **'Upload on WiFi only'**
  String get settingsMobileDataDisabled;

  /// No description provided for @settingsVersion.
  ///
  /// In en, this message translates to:
  /// **'Version {version}'**
  String settingsVersion(String version);

  /// No description provided for @permissionLocationTitle.
  ///
  /// In en, this message translates to:
  /// **'Location Permission'**
  String get permissionLocationTitle;

  /// No description provided for @permissionLocationMessage.
  ///
  /// In en, this message translates to:
  /// **'GreenGains needs location access to collect environmental data.'**
  String get permissionLocationMessage;

  /// No description provided for @permissionLocationButton.
  ///
  /// In en, this message translates to:
  /// **'Grant Permission'**
  String get permissionLocationButton;

  /// No description provided for @permissionBatteryTitle.
  ///
  /// In en, this message translates to:
  /// **'Battery Optimization'**
  String get permissionBatteryTitle;

  /// No description provided for @permissionBatteryMessage.
  ///
  /// In en, this message translates to:
  /// **'Please disable battery optimization for reliable background tracking.'**
  String get permissionBatteryMessage;

  /// No description provided for @permissionBatteryButton.
  ///
  /// In en, this message translates to:
  /// **'Open Settings'**
  String get permissionBatteryButton;

  /// No description provided for @errorGeneric.
  ///
  /// In en, this message translates to:
  /// **'Something went wrong. Please try again.'**
  String get errorGeneric;

  /// No description provided for @errorNetwork.
  ///
  /// In en, this message translates to:
  /// **'No internet connection'**
  String get errorNetwork;

  /// No description provided for @errorLocationUnavailable.
  ///
  /// In en, this message translates to:
  /// **'Location unavailable'**
  String get errorLocationUnavailable;

  /// No description provided for @errorUploadFailed.
  ///
  /// In en, this message translates to:
  /// **'Upload failed. Will retry later.'**
  String get errorUploadFailed;

  /// No description provided for @errorSignInRequired.
  ///
  /// In en, this message translates to:
  /// **'Please sign in to continue'**
  String get errorSignInRequired;

  /// No description provided for @buttonOk.
  ///
  /// In en, this message translates to:
  /// **'OK'**
  String get buttonOk;

  /// No description provided for @buttonCancel.
  ///
  /// In en, this message translates to:
  /// **'Cancel'**
  String get buttonCancel;

  /// No description provided for @buttonYes.
  ///
  /// In en, this message translates to:
  /// **'Yes'**
  String get buttonYes;

  /// No description provided for @buttonNo.
  ///
  /// In en, this message translates to:
  /// **'No'**
  String get buttonNo;

  /// No description provided for @buttonSave.
  ///
  /// In en, this message translates to:
  /// **'Save'**
  String get buttonSave;

  /// No description provided for @buttonDelete.
  ///
  /// In en, this message translates to:
  /// **'Delete'**
  String get buttonDelete;

  /// No description provided for @buttonClose.
  ///
  /// In en, this message translates to:
  /// **'Close'**
  String get buttonClose;

  /// No description provided for @buttonRetry.
  ///
  /// In en, this message translates to:
  /// **'Retry'**
  String get buttonRetry;

  /// No description provided for @loading.
  ///
  /// In en, this message translates to:
  /// **'Loading...'**
  String get loading;

  /// No description provided for @saving.
  ///
  /// In en, this message translates to:
  /// **'Saving...'**
  String get saving;

  /// No description provided for @success.
  ///
  /// In en, this message translates to:
  /// **'Success'**
  String get success;

  /// No description provided for @error.
  ///
  /// In en, this message translates to:
  /// **'Error'**
  String get error;
}

class _AppLocalizationsDelegate
    extends LocalizationsDelegate<AppLocalizations> {
  const _AppLocalizationsDelegate();

  @override
  Future<AppLocalizations> load(Locale locale) {
    return SynchronousFuture<AppLocalizations>(lookupAppLocalizations(locale));
  }

  @override
  bool isSupported(Locale locale) =>
      <String>['en', 'fr'].contains(locale.languageCode);

  @override
  bool shouldReload(_AppLocalizationsDelegate old) => false;
}

AppLocalizations lookupAppLocalizations(Locale locale) {
  // Lookup logic when only language code is specified.
  switch (locale.languageCode) {
    case 'en':
      return AppLocalizationsEn();
    case 'fr':
      return AppLocalizationsFr();
  }

  throw FlutterError(
      'AppLocalizations.delegate failed to load unsupported locale "$locale". This is likely '
      'an issue with the localizations generation tool. Please file an issue '
      'on GitHub with a reproducible sample app and the gen-l10n configuration '
      'that was used.');
}
