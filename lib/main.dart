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
import 'services/auth/auth_service.dart';
import 'utils/app_snackbars.dart';

// MINIMAL main - start UI immediately
void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  bool _isInitialized = false;

  @override
  void initState() {
    super.initState();
    _initializeApp();
  }

  // Background initialization - doesn't block UI
  Future<void> _initializeApp() async {
    try {
      // Firebase is required for auth
      await Firebase.initializeApp(
        options: DefaultFirebaseOptions.currentPlatform,
      );

      // Setup auth listener (non-blocking background work)
      _setupAuthTokenSync();

      // Sign in anonymously (if needed)
      if (FirebaseAuth.instance.currentUser == null) {
        await FirebaseAuth.instance.signInAnonymously();
      }

      // Load preferences
      await AppPreferences.instance.init();

      // Save backend config
      await _persistBackendConfig();

      // Load theme
      await ThemeController.instance.load();

      if (mounted) {
        setState(() {
          _isInitialized = true;
        });
      }
    } catch (e) {
      print('❌ Initialization error: $e');
      // Still mark as initialized to show UI (with potential error state)
      if (mounted) {
        setState(() {
          _isInitialized = true;
        });
      }
    }
  }

  void _setupAuthTokenSync() {
    FirebaseAuth.instance.idTokenChanges().listen((User? user) async {
      if (user == null) {
        print('User is currently signed out!');
      } else {
        print('User is signed in: ${user.uid}');
        try {
          final token = await user.getIdToken();
          if (token != null) {
            final prefs = await SharedPreferences.getInstance();
            await prefs.setString('firebase_auth_token', token);
            print('Synced Firebase Token to SharedPreferences');

            // Device registration - FIRE AND FORGET (non-blocking)
            AuthService.registerDevice(user).catchError((e) {
              print('Device registration failed (non-critical): $e');
            });
          }
        } catch (e) {
          print('Error syncing token: $e');
        }
      }
    });
  }

  Future<void> _persistBackendConfig() async {
    if (kBackendApiKey.isEmpty) {
      throw Exception(
        'Backend API key not provided!\n\n'
        'Run with: flutter run --dart-define-from-file=dart_defines.json',
      );
    }

    final prefs = await SharedPreferences.getInstance();
    await prefs.setString('backend_api_key', kBackendApiKey);
    await prefs.setString('backend_url', kBackendBaseUrl);
  }

  @override
  Widget build(BuildContext context) {
    // Show app immediately, even if not fully initialized
    return ListenableBuilder(
      listenable: ThemeController.instance,
      builder: (context, _) {
        return MaterialApp(
          title: 'GreenGains',
          debugShowCheckedModeBanner: false,
          theme: AppTheme.theme(),
          darkTheme: AppTheme.themeDark(),
          themeMode: ThemeController.instance.mode,
          themeAnimationDuration: Duration.zero,
          // Show loading indicator if not ready, otherwise show onboarding
          home: _isInitialized
              ? const OnboardingWrapper()
              : const _InitializingScreen(),
        );
      },
    );
  }
}

/// Simple loading screen shown during initialization
class _InitializingScreen extends StatelessWidget {
  const _InitializingScreen();

  @override
  Widget build(BuildContext context) {
    final isDark = Theme.of(context).brightness == Brightness.dark;
    return Scaffold(
      backgroundColor: AppColors.surface(isDark),
      body: Center(
        child: CircularProgressIndicator(
          valueColor: AlwaysStoppedAnimation(AppColors.primary),
        ),
      ),
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
  bool _authPromptChecked = false;

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
    if (completed) {
      WidgetsBinding.instance.addPostFrameCallback((_) {
        _maybePromptPostOnboardingAuth();
      });
    }
  }

  Future<void> _handleOnboardingComplete() async {
    await AppPreferences.instance.setOnboardingComplete(true);
    if (!mounted) return;
    setState(() {
      _showOnboarding = false;
    });
    await _maybePromptPostOnboardingAuth();
  }

  Future<void> _maybePromptPostOnboardingAuth() async {
    if (!mounted || _authPromptChecked) return;
    _authPromptChecked = true;

    await AppPreferences.instance.ensureInitialized();
    if (AppPreferences.instance.postOnboardingAuthPrompted) {
      return;
    }

    final user = FirebaseAuth.instance.currentUser;
    if (user != null && !user.isAnonymous) {
      await AppPreferences.instance.setPostOnboardingAuthPrompted(true);
      return;
    }

    await AppPreferences.instance.setPostOnboardingAuthPrompted(true);
    if (!mounted) return;

    await showModalBottomSheet<bool>(
      context: context,
      isScrollControlled: true,
      showDragHandle: true,
      builder: (_) => const PostOnboardingSignInSheet(),
    );
  }

  @override
  Widget build(BuildContext context) {
    if (_showOnboarding) {
      return OnboardingScreen(
        onComplete: () {
          _handleOnboardingComplete();
        },
      );
    }

    return const AppShell();
  }
}

class PostOnboardingSignInSheet extends StatefulWidget {
  const PostOnboardingSignInSheet({super.key});

  @override
  State<PostOnboardingSignInSheet> createState() =>
      _PostOnboardingSignInSheetState();
}

class _PostOnboardingSignInSheetState extends State<PostOnboardingSignInSheet> {
  bool _loading = false;

  Future<void> _handleSignIn() async {
    if (_loading) return;
    setState(() => _loading = true);
    try {
      await AuthService.signInWithGoogleUniversal();
      if (!mounted) return;
      AppSnackbars.showSuccess(context, 'Signed in successfully');
      Navigator.of(context).pop(true);
    } catch (_) {
      if (!mounted) return;
      AppSnackbars.showError(context, 'Sign-in cancelled');
    } finally {
      if (mounted) {
        setState(() => _loading = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    return SafeArea(
      top: false,
      child: Padding(
        padding: const EdgeInsets.fromLTRB(
          AppTheme.spaceLg,
          AppTheme.spaceLg,
          AppTheme.spaceLg,
          AppTheme.spaceXl,
        ),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              width: 44,
              height: 4,
              decoration: BoxDecoration(
                color: AppColors.textTertiary(isDark),
                borderRadius: BorderRadius.circular(999),
              ),
            ),
            const SizedBox(height: AppTheme.spaceLg),
            Text(
              'Save your impact',
              style: theme.textTheme.titleLarge?.copyWith(
                fontWeight: FontWeight.w700,
              ),
            ),
            const SizedBox(height: AppTheme.spaceSm),
            Text(
              'Sign in to sync your contributions across devices, unlock rewards, and keep your tiles credited.',
              style: theme.textTheme.bodyMedium?.copyWith(
                color: AppColors.textSecondary(isDark),
              ),
            ),
            const SizedBox(height: AppTheme.spaceLg),
            Container(
              padding: const EdgeInsets.all(AppTheme.spaceMd),
              decoration: BoxDecoration(
                color: AppColors.surfaceElevated(isDark),
                borderRadius: BorderRadius.circular(AppTheme.radiusMd),
                border: Border.all(
                  color: AppColors.border(isDark),
                ),
              ),
              child: Column(
                children: [
                  _buildBenefitRow(
                    icon: Icons.verified_user_outlined,
                    label: 'Verified impact history',
                    isDark: isDark,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  _buildBenefitRow(
                    icon: Icons.card_giftcard_outlined,
                    label: 'Rewards eligibility',
                    isDark: isDark,
                  ),
                  const SizedBox(height: AppTheme.spaceSm),
                  _buildBenefitRow(
                    icon: Icons.devices_outlined,
                    label: 'Sync across devices',
                    isDark: isDark,
                  ),
                ],
              ),
            ),
            const SizedBox(height: AppTheme.spaceLg),
            SizedBox(
              width: double.infinity,
              child: FilledButton.icon(
                onPressed: _loading ? null : _handleSignIn,
                icon: _loading
                    ? const SizedBox(
                        width: 16,
                        height: 16,
                        child: CircularProgressIndicator(strokeWidth: 2),
                      )
                    : const Icon(Icons.login),
                label: Text(_loading ? 'Signing in...' : 'Sign in with Google'),
              ),
            ),
            const SizedBox(height: AppTheme.spaceSm),
            SizedBox(
              width: double.infinity,
              child: TextButton(
                onPressed:
                    _loading ? null : () => Navigator.of(context).pop(false),
                child: const Text('Maybe later'),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget _buildBenefitRow({
    required IconData icon,
    required String label,
    required bool isDark,
  }) {
    return Row(
      children: [
        Icon(
          icon,
          color: AppColors.primary,
          size: 20,
        ),
        const SizedBox(width: AppTheme.spaceSm),
        Expanded(
          child: Text(
            label,
            style: TextStyle(
              color: AppColors.textSecondary(isDark),
              fontWeight: FontWeight.w500,
            ),
          ),
        ),
      ],
    );
  }
}
