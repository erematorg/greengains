import 'package:flutter/material.dart';
import 'package:flutter/gestures.dart';
import '../core/app_preferences.dart';
import '../core/themes.dart';
import 'webview_screen.dart';

/// Simple one-screen onboarding for first-time users
/// Optimized with minimal animations for fast load
class OnboardingScreen extends StatefulWidget {
  const OnboardingScreen({super.key, required this.onComplete});

  final VoidCallback onComplete;

  @override
  State<OnboardingScreen> createState() => _OnboardingScreenState();
}

class _OnboardingScreenState extends State<OnboardingScreen> with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<double> _fadeAnimation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(milliseconds: 400),
      vsync: this,
    );

    _fadeAnimation = Tween<double>(begin: 0.0, end: 1.0).animate(
      CurvedAnimation(parent: _controller, curve: Curves.easeOut),
    );

    _controller.forward();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;

    return Scaffold(
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(AppTheme.spaceLg),
          child: FadeTransition(
            opacity: _fadeAnimation,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Spacer(),

                // App Icon/Logo - simple, no elastic animation
                Container(
                  padding: const EdgeInsets.all(AppTheme.spaceXl),
                  decoration: BoxDecoration(
                    color: AppColors.primaryAlpha(0.12),
                    shape: BoxShape.circle,
                    boxShadow: [
                      BoxShadow(
                        color: AppColors.primary.withValues(alpha: 0.2),
                        blurRadius: 20,
                        spreadRadius: 5,
                      ),
                    ],
                  ),
                  child: const Icon(
                    Icons.eco,
                    size: 72,
                    color: AppColors.primary,
                  ),
                ),

                const SizedBox(height: AppTheme.spaceXl),

                // Title
                Text(
                  'Welcome to GreenGains',
                  style: theme.textTheme.headlineMedium?.copyWith(
                    fontWeight: FontWeight.bold,
                  ),
                  textAlign: TextAlign.center,
                ),

                const SizedBox(height: AppTheme.spaceMd),

                // Description
                Text(
                  'Help create greener cities by passively collecting environmental sensor data.',
                  style: theme.textTheme.bodyLarge?.copyWith(
                    color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                  ),
                  textAlign: TextAlign.center,
                ),

                const SizedBox(height: AppTheme.spaceXl),

                // Features - simple layout, no staggered animation
                _buildFeature(
                  icon: Icons.sensors,
                  title: 'Passive Collection',
                  description: 'Runs in the background while you go about your day',
                  isDark: isDark,
                  theme: theme,
                ),
                const SizedBox(height: AppTheme.spaceMd),
                _buildFeature(
                  icon: Icons.privacy_tip,
                  title: 'Privacy First',
                  description: 'Location sharing is optional and uses coarse positioning only',
                  isDark: isDark,
                  theme: theme,
                ),
                const SizedBox(height: AppTheme.spaceMd),
                _buildFeature(
                  icon: Icons.show_chart,
                  title: 'Track Your Impact',
                  description: 'See your contributions and help map environmental data',
                  isDark: isDark,
                  theme: theme,
                ),

                const Spacer(),

                // Privacy Policy link (Play Store requirement)
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: AppTheme.spaceSm),
                  child: RichText(
                    textAlign: TextAlign.center,
                    text: TextSpan(
                      style: theme.textTheme.bodySmall?.copyWith(
                        color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                      ),
                      children: [
                        const TextSpan(text: 'By continuing, you agree to our '),
                        TextSpan(
                          text: 'Privacy Policy',
                          style: TextStyle(
                            color: AppColors.primary,
                            decoration: TextDecoration.underline,
                            fontWeight: FontWeight.w500,
                          ),
                          recognizer: TapGestureRecognizer()
                            ..onTap = () {
                              Navigator.of(context).push(
                                MaterialPageRoute(
                                  builder: (context) => const WebViewScreen(
                                    url: 'https://greengains.eremat.org/privacy-policy',
                                    title: 'Privacy Policy',
                                  ),
                                ),
                              );
                            },
                        ),
                        const TextSpan(text: ' and '),
                        TextSpan(
                          text: 'Terms of Service',
                          style: TextStyle(
                            color: AppColors.primary,
                            decoration: TextDecoration.underline,
                            fontWeight: FontWeight.w500,
                          ),
                          recognizer: TapGestureRecognizer()
                            ..onTap = () {
                              Navigator.of(context).push(
                                MaterialPageRoute(
                                  builder: (context) => const WebViewScreen(
                                    url: 'https://greengains.eremat.org/terms-of-service',
                                    title: 'Terms of Service',
                                  ),
                                ),
                              );
                            },
                        ),
                        const TextSpan(text: '.'),
                      ],
                    ),
                  ),
                ),

                const SizedBox(height: AppTheme.spaceMd),

                // Start Button - no animation, instant
                Container(
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(100),
                    gradient: const LinearGradient(
                      colors: AppColors.gradientGreen,
                      begin: Alignment.topLeft,
                      end: Alignment.bottomRight,
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: AppColors.primary.withValues(alpha: 0.3),
                        blurRadius: 12,
                        offset: const Offset(0, 4),
                      ),
                    ],
                  ),
                  child: FilledButton(
                    onPressed: () async {
                      await AppPreferences.instance.setOnboardingComplete(true);
                      widget.onComplete();
                    },
                    style: FilledButton.styleFrom(
                      backgroundColor: Colors.transparent,
                      shadowColor: Colors.transparent,
                      minimumSize: const Size.fromHeight(AppTheme.authButtonHeight),
                    ),
                    child: const Text(
                      'Get Started',
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.w700,
                        letterSpacing: 0.5,
                      ),
                    ),
                  ),
                ),

                const SizedBox(height: AppTheme.spaceLg),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildFeature({
    required IconData icon,
    required String title,
    required String description,
    required bool isDark,
    required ThemeData theme,
  }) {
    return Row(
      children: [
        Container(
          padding: const EdgeInsets.all(10),
          decoration: BoxDecoration(
            color: AppColors.primaryAlpha(0.12),
            borderRadius: BorderRadius.circular(10),
          ),
          child: Icon(
            icon,
            color: AppColors.primary,
            size: 22,
          ),
        ),
        const SizedBox(width: AppTheme.spaceMd),
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                title,
                style: theme.textTheme.titleSmall?.copyWith(
                  fontWeight: FontWeight.w600,
                ),
              ),
              const SizedBox(height: 4),
              Text(
                description,
                style: theme.textTheme.bodySmall?.copyWith(
                  color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}
