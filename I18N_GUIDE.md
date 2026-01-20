# Internationalization (i18n) Setup Guide

Flutter Intl is now configured for GreenGains! This guide shows you how to use it.

## What's Set Up

✅ **Dependencies:** `flutter_localizations` and `intl` added
✅ **Config:** `l10n.yaml` created
✅ **English strings:** `lib/l10n/app_en.arb` with 80+ strings
✅ **French template:** `lib/l10n/app_fr.arb` with "TODO: Translate" placeholders
✅ **Generated code:** Flutter automatically generates `AppLocalizations` class

---

## Step 1: Translate French Strings (YOUR TODO)

Open `lib/l10n/app_fr.arb` and replace all "TODO: Translate" with French translations.

**Example:**
```json
{
  "onboardingWelcomeTitle": "Bienvenue sur GreenGains",
  "onboardingWelcomeSubtitle": "Aidez à créer des villes plus vertes en collectant passivement des données de capteurs environnementaux.",
  "startTracking": "Démarrer le suivi",
  "stopTracking": "Arrêter le suivi",
  "buttonNext": "Suivant",
  "buttonPrevious": "Précédent"
}
```

**Time estimate:** 1-2 hours for all 80 strings

---

## Step 2: Update main.dart

Add localization delegates to your app:

**File:** `lib/main.dart`

```dart
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart'; // Generated file

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      // Add these 3 lines:
      localizationsDelegates: const [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [
        Locale('en'), // English
        Locale('fr'), // French
      ],

      // Rest of your MaterialApp config...
      title: 'GreenGains',
      theme: AppTheme.lightTheme,
      darkTheme: AppTheme.darkTheme,
      home: SplashScreen(),
    );
  }
}
```

---

## Step 3: Use Translations in Your Code

Replace hardcoded strings with translated versions:

### Before:
```dart
Text('Welcome to GreenGains')
```

### After:
```dart
Text(AppLocalizations.of(context)!.onboardingWelcomeTitle)
```

### Short Syntax (Optional):
Create a getter in your widget:
```dart
AppLocalizations get l10n => AppLocalizations.of(context)!;

// Then use:
Text(l10n.onboardingWelcomeTitle)
```

---

## Step 4: Add Language Selector to Settings

Create a language picker in Settings screen:

**File:** `lib/screens/settings_screen.dart`

```dart
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class SettingsScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;
    final currentLocale = Localizations.localeOf(context);

    return Scaffold(
      appBar: AppBar(title: Text(l10n.settingsTitle)),
      body: ListView(
        children: [
          ListTile(
            leading: Icon(Icons.language),
            title: Text(l10n.settingsLanguage),
            subtitle: Text(currentLocale.languageCode == 'fr' ? 'Français' : 'English'),
            onTap: () => _showLanguagePicker(context),
          ),
          // Other settings...
        ],
      ),
    );
  }

  void _showLanguagePicker(BuildContext context) {
    final l10n = AppLocalizations.of(context)!;

    showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: Text(l10n.settingsLanguage),
        content: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            ListTile(
              title: Text('English'),
              onTap: () {
                // Save preference and restart app
                // You'll need to implement locale switching
              },
            ),
            ListTile(
              title: Text('Français'),
              onTap: () {
                // Save preference and restart app
              },
            ),
          ],
        ),
      ),
    );
  }
}
```

---

## Step 5: Persist Language Choice

Add language preference to SharedPreferences:

**Create:** `lib/services/locale_service.dart`

```dart
import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LocaleService extends ChangeNotifier {
  static const String _key = 'app_locale';
  Locale _locale = const Locale('en');

  Locale get locale => _locale;

  Future<void> loadLocale() async {
    final prefs = await SharedPreferences.getInstance();
    final languageCode = prefs.getString(_key) ?? 'en';
    _locale = Locale(languageCode);
    notifyListeners();
  }

  Future<void> setLocale(Locale locale) async {
    if (locale == _locale) return;
    _locale = locale;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(_key, locale.languageCode);
    notifyListeners();
  }
}
```

**Update main.dart:**
```dart
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final localeService = LocaleService();
  await localeService.loadLocale();

  runApp(
    ChangeNotifierProvider.value(
      value: localeService,
      child: MyApp(),
    ),
  );
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Consumer<LocaleService>(
      builder: (context, localeService, child) {
        return MaterialApp(
          locale: localeService.locale, // Use saved locale
          localizationsDelegates: const [
            AppLocalizations.delegate,
            GlobalMaterialLocalizations.delegate,
            GlobalWidgetsLocalizations.delegate,
            GlobalCupertinoLocalizations.delegate,
          ],
          supportedLocales: const [Locale('en'), Locale('fr')],
          // Rest of config...
        );
      },
    );
  }
}
```

---

## Step 6: Adding New Strings Later

When you add new features:

1. Add English string to `lib/l10n/app_en.arb`:
```json
"newFeatureName": "My New Feature"
```

2. Add French translation to `lib/l10n/app_fr.arb`:
```json
"newFeatureName": "Ma Nouvelle Fonctionnalité"
```

3. Run `flutter pub get` to regenerate code

4. Use in code:
```dart
Text(AppLocalizations.of(context)!.newFeatureName)
```

---

## Quick Migration Checklist

To update existing screens:

- [ ] Onboarding Screen (`lib/screens/onboarding_screen.dart`)
- [ ] Home Screen (`lib/screens/home_screen.dart`)
- [ ] Profile Screen (`lib/screens/profile_screen.dart`)
- [ ] Settings Screen (`lib/screens/settings_screen.dart`)
- [ ] Daily Pot Widget (`lib/widgets/daily_pot_icon.dart`)
- [ ] Credits Display (`lib/widgets/credits_display.dart`)
- [ ] Tracking Status Banner (`lib/widgets/tracking_status_banner.dart`)

**Search pattern:** Look for `Text('...')` and `AppSnackbars.show*('...')` with hardcoded strings

---

## Testing

1. **English (default):**
   - App should work as before

2. **French:**
   - Change device language to French
   - Or add language picker and select French
   - Verify all screens show French text

3. **Hot Reload:**
   - After changing .arb files, run `flutter pub get`
   - Then hot reload works (no full restart needed)

---

## Upgrade Path to Crowdin (Later)

When you're ready for professional translation management:

1. Export ARB files to Crowdin
2. Invite translators
3. Set up GitHub Action for auto-sync
4. Add more languages (German, Spanish, etc.)

Cost: $40/month for private projects, free for open source

---

## Common Issues

**Q: I added a string but it's not available in code**
A: Run `flutter pub get` to regenerate the AppLocalizations class

**Q: App crashes with "Localizations.localeOf" error**
A: Make sure you added `localizationsDelegates` to MaterialApp

**Q: French not showing up**
A: Verify `supportedLocales` includes `Locale('fr')`

**Q: Translations not updating on hot reload**
A: After changing .arb files, you must run `flutter pub get` first

---

## Summary

You now have:
- Professional i18n setup (same as Duolingo, Revolut, etc.)
- 80+ English strings ready
- French template ready for translation (1-2 hours of work)
- Easy to add more languages later
- Upgrade path to Crowdin when you have budget

**Next step:** Translate `lib/l10n/app_fr.arb` (~1 hour), then update main.dart (5 minutes).
