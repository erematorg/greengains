import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'firebase_options.dart';
import 'core/app_preferences.dart';
import 'core/theme_controller.dart';
import 'core/themes.dart';
import 'app_shell.dart';
import 'screens/onboarding_screen.dart';
import 'services/network/backend_client.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  // Initialize Firebase
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );

  // Sign in anonymously (Honeygain Model: Frictionless Auth)
  try {
    final userCredential = await FirebaseAuth.instance.signInAnonymously();
    print('Signed in anonymously: ${userCredential.user?.uid}');
  } catch (e) {
    print('Failed to sign in anonymously: $e');
    // We continue anyway; the backend might reject uploads but the app should open.
  }

  // Initialize preferences
  await AppPreferences.instance.init();

  // Save API key to SharedPreferences for native code access
  if (kBackendApiKey.isEmpty) {
    throw Exception(
      'Backend API key not provided!\n\n'
      'Run with: flutter run --dart-define-from-file=dart_defines.json\n'
      'Or use: .\\run-debug.ps1\n\n'
      'See SETUP_DEV.md for setup instructions.',
    );
  }

  final prefs = await SharedPreferences.getInstance();
  await prefs.setString('backend_api_key', kBackendApiKey);
  await prefs.setString('backend_url', kBackendBaseUrl);

  // Load theme preference
  await ThemeController.instance.load();

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ListenableBuilder(
      listenable: ThemeController.instance,
      builder: (context, _) {
        return MaterialApp(
          title: 'GreenGains',
          debugShowCheckedModeBanner: false,
          theme: AppTheme.theme(),
          darkTheme: AppTheme.themeDark(),
          themeMode: ThemeController.instance.mode,
          themeAnimationDuration: Duration.zero, // Instant theme switching
          home: const OnboardingWrapper(),
        );
      },
    );
  }
}

/// Wrapper that shows onboarding for first-time users
class OnboardingWrapper extends StatefulWidget {
  const OnboardingWrapper({super.key});

  @override
  State<OnboardingWrapper> createState() => _OnboardingWrapperState();
}

class _OnboardingWrapperState extends State<OnboardingWrapper> {
  bool _showOnboarding = true;

  @override
  void initState() {
    super.initState();
    _checkOnboarding();
  }

  Future<void> _checkOnboarding() async {
    final completed = AppPreferences.instance.onboardingComplete;
    if (mounted) {
      setState(() {
        _showOnboarding = !completed;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    if (_showOnboarding) {
      return OnboardingScreen(
        onComplete: () {
          setState(() {
            _showOnboarding = false;
          });
        },
      );
    }

    return const AppShell();
  }
}
