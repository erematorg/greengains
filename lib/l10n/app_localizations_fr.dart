// ignore: unused_import
import 'package:intl/intl.dart' as intl;
import 'app_localizations.dart';

// ignore_for_file: type=lint

/// The translations for French (`fr`).
class AppLocalizationsFr extends AppLocalizations {
  AppLocalizationsFr([String locale = 'fr']) : super(locale);

  @override
  String get onboardingWelcomeTitle => 'Bienvenue sur GreenGains';

  @override
  String get onboardingWelcomeSubtitle =>
      'Aidez à créer des villes plus vertes en collectant passivement des données environnementales.';

  @override
  String get onboardingFeature1Title => 'Collection passive';

  @override
  String get onboardingFeature1Description =>
      'Fonctionne en arrière-plan pendant vos activités. Aucune interaction nécessaire.';

  @override
  String get onboardingFeature2Title => 'Confidentialité d\'abord';

  @override
  String get onboardingFeature2Description =>
      'Le partage de position est optionnel et utilise uniquement une localisation approximative.';

  @override
  String get onboardingFeature3Title => 'Suivez votre impact';

  @override
  String get onboardingFeature3Description =>
      'Visualisez vos contributions et aidez à cartographier les données environnementales de votre ville.';

  @override
  String get onboardingSignInTitle => 'Débloquez des récompenses';

  @override
  String get onboardingSignInSubtitle =>
      'Connectez-vous pour accéder aux récompenses quotidiennes et synchroniser vos données.';

  @override
  String get onboardingDailyPotRewards => 'Pot quotidien';

  @override
  String get onboardingDailyPotDescription =>
      'Gagnez 10 à 100 crédits chaque jour';

  @override
  String get onboardingCloudSync => 'Synchronisation cloud';

  @override
  String get onboardingCloudSyncDescription =>
      'Accédez à vos données depuis n\'importe quel appareil';

  @override
  String get onboardingFutureFeatures => 'Fonctionnalités futures';

  @override
  String get onboardingFutureDescription => 'Classements et compétitions';

  @override
  String onboardingPrivacyNotice(String privacyPolicy, String termsOfService) {
    return 'En continuant, vous acceptez notre $privacyPolicy et nos $termsOfService.';
  }

  @override
  String get privacyPolicy => 'Politique de confidentialité';

  @override
  String get termsOfService => 'Conditions d\'utilisation';

  @override
  String get buttonPrevious => 'Précédent';

  @override
  String get buttonNext => 'Suivant';

  @override
  String get signInSuccess => 'Connexion réussie';

  @override
  String get signInError => 'Connexion annulée ou échouée';

  @override
  String get navHome => 'Accueil';

  @override
  String get navProfile => 'Profil';

  @override
  String get navSettings => 'Paramètres';

  @override
  String get homeTitle => 'GreenGains';

  @override
  String get startTracking => 'Démarrer le suivi';

  @override
  String get stopTracking => 'Arrêter le suivi';

  @override
  String get trackingActive => 'Suivi actif';

  @override
  String get trackingPaused => 'Suivi en pause';

  @override
  String get trackingStopped => 'Suivi arrêté';

  @override
  String get uploadSuccess => 'Envoi réussi';

  @override
  String get uploadFailed => 'Échec de l\'envoi';

  @override
  String lastUpload(String time) {
    return 'Dernier envoi : $time';
  }

  @override
  String get noUploadYet => 'Aucun envoi pour le moment';

  @override
  String get dailyPotTitle => 'Pot quotidien';

  @override
  String dailyPotClaimButton(int amount) {
    return 'Récupérer $amount crédits';
  }

  @override
  String dailyPotClaimed(int amount) {
    return '+$amount crédits ! 🍯';
  }

  @override
  String get dailyPotAlreadyClaimed =>
      'Déjà récupéré aujourd\'hui ! Revenez demain';

  @override
  String dailyPotNeedMoreUploads(int count, String s) {
    return 'Encore $count envoi$s pour débloquer';
  }

  @override
  String dailyPotProgress(int current, int required) {
    return '$current / $required envois';
  }

  @override
  String credits(int count) {
    return '$count crédits';
  }

  @override
  String get totalCredits => 'Total de crédits';

  @override
  String get creditsEarned => 'Crédits gagnés';

  @override
  String get statsTitle => 'Votre impact';

  @override
  String get totalUploads => 'Total d\'envois';

  @override
  String get todayUploads => 'Envois du jour';

  @override
  String get coverageTiles => 'Tuiles couvertes';

  @override
  String get dataCollected => 'Données collectées';

  @override
  String timesContributed(int count) {
    return '$count contributions';
  }

  @override
  String get mapTitle => 'Carte de couverture';

  @override
  String get mapRecenter => 'Recentrer';

  @override
  String get mapZoomIn => 'Zoomer';

  @override
  String get mapZoomOut => 'Dézoomer';

  @override
  String get mapYourLocation => 'Votre position';

  @override
  String get mapCoverageLegend => 'Couverture';

  @override
  String get profileTitle => 'Profil';

  @override
  String get profileSignOut => 'Se déconnecter';

  @override
  String get profileSignedInAs => 'Connecté en tant que';

  @override
  String profileMemberSince(String date) {
    return 'Membre depuis le $date';
  }

  @override
  String get profileDeleteAccount => 'Supprimer le compte';

  @override
  String get profileDeleteConfirm =>
      'Êtes-vous sûr ? Cette action est irréversible.';

  @override
  String get settingsTitle => 'Paramètres';

  @override
  String get settingsGeneral => 'Général';

  @override
  String get settingsPrivacy => 'Confidentialité et données';

  @override
  String get settingsAbout => 'À propos';

  @override
  String get settingsLanguage => 'Langue';

  @override
  String get settingsLanguageSystem => 'Système';

  @override
  String get settingsLanguageEnglish => 'English';

  @override
  String get settingsLanguageFrench => 'Français';

  @override
  String get settingsTheme => 'Thème';

  @override
  String get settingsNotifications => 'Notifications';

  @override
  String get settingsLocationSharing => 'Partage de position';

  @override
  String get settingsLocationEnabled => 'Partage de position activé';

  @override
  String get settingsLocationDisabled => 'Partage de position désactivé';

  @override
  String get settingsMobileData => 'Envoi sur données mobiles';

  @override
  String get settingsMobileDataEnabled => 'Envoyer sur données mobiles';

  @override
  String get settingsMobileDataDisabled => 'Envoyer uniquement en WiFi';

  @override
  String settingsVersion(String version) {
    return 'Version $version';
  }

  @override
  String get permissionLocationTitle => 'Autorisation de localisation';

  @override
  String get permissionLocationMessage =>
      'GreenGains a besoin d\'accéder à votre position pour collecter des données environnementales.';

  @override
  String get permissionLocationButton => 'Autoriser';

  @override
  String get permissionBatteryTitle => 'Optimisation de la batterie';

  @override
  String get permissionBatteryMessage =>
      'Veuillez désactiver l\'optimisation de la batterie pour un suivi en arrière-plan fiable.';

  @override
  String get permissionBatteryButton => 'Ouvrir les paramètres';

  @override
  String get errorGeneric => 'Une erreur s\'est produite. Veuillez réessayer.';

  @override
  String get errorNetwork => 'Pas de connexion internet';

  @override
  String get errorLocationUnavailable => 'Position indisponible';

  @override
  String get errorUploadFailed =>
      'Échec de l\'envoi. Nouvelle tentative plus tard.';

  @override
  String get errorSignInRequired => 'Veuillez vous connecter pour continuer';

  @override
  String get buttonOk => 'OK';

  @override
  String get buttonCancel => 'Annuler';

  @override
  String get buttonYes => 'Oui';

  @override
  String get buttonNo => 'Non';

  @override
  String get buttonSave => 'Enregistrer';

  @override
  String get buttonDelete => 'Supprimer';

  @override
  String get buttonClose => 'Fermer';

  @override
  String get buttonRetry => 'Réessayer';

  @override
  String get loading => 'Chargement...';

  @override
  String get saving => 'Enregistrement...';

  @override
  String get success => 'Succès';

  @override
  String get error => 'Erreur';
}
