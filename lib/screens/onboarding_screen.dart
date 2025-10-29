import 'package:flutter/material.dart';
import '../core/themes.dart';

class OnboardingScreen extends StatefulWidget {
  final Future<void> Function() onDone;
  const OnboardingScreen({super.key, required this.onDone});

  @override
  State<OnboardingScreen> createState() => _OnboardingScreenState();
}

class _OnboardingScreenState extends State<OnboardingScreen> {
  bool _agree = false;
  bool _busy = false;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: SafeArea(
        child: LayoutBuilder(
          builder: (context, constraints) {
            // Responsive hero sizing (bigger, but bounded for various screens)
            final double heroH = (constraints.maxHeight * 0.38)
                .clamp(AppTheme.onboardingHeroMin, AppTheme.onboardingHeroMax)
                .toDouble();
            final double iconSize = (heroH * AppTheme.onboardingHeroIconRatio)
                .clamp(110.0, 160.0)
                .toDouble();
            return SingleChildScrollView(
              padding: AppTheme.pagePadding,
              child: ConstrainedBox(
                constraints: BoxConstraints(minHeight: constraints.maxHeight),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  mainAxisSize: MainAxisSize.min,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    // Hero header (placeholder illustration)
                    Container(
                      height: heroH,
                      decoration: BoxDecoration(
                        gradient: LinearGradient(
                          colors: [
                            Colors.amber.shade400,
                            Colors.orange.shade400
                          ],
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                        ),
                        borderRadius: BorderRadius.circular(AppTheme.radiusLg),
                      ),
                      alignment: Alignment.center,
                      child:
                          Icon(Icons.eco, size: iconSize, color: Colors.white),
                    ),
                    const SizedBox(height: AppTheme.spaceLg),
                    Text(
                      'Welcome to GreenGains',
                      style: theme.textTheme.headlineMedium
                          ?.copyWith(fontWeight: FontWeight.w700),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceXs),
                    Text(
                      'Understand your surroundings with passive sensors - simple, private, and battery-friendly.',
                      style: theme.textTheme.bodyLarge,
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    CheckboxListTile(
                      value: _agree,
                      onChanged: _busy
                          ? null
                          : (v) => setState(() => _agree = v ?? false),
                      controlAffinity: ListTileControlAffinity.leading,
                      contentPadding: EdgeInsets.zero,
                      title: const Text(
                          'I agree to the Terms and Privacy Policy.'),
                    ),
                    // Primary CTA placed low for reachability
                    FilledButton(
                      onPressed: (!_agree || _busy)
                          ? null
                          : () async {
                              setState(() => _busy = true);
                              await widget.onDone();
                              if (mounted) setState(() => _busy = false);
                              // No navigation here; RootDecider rebuilds to AuthGate -> Home/Login.
                            },
                      child:
                          Text(_busy ? 'Please wait...' : 'Create an account'),
                    ),
                    const SizedBox(height: AppTheme.ctaGapLink),
                    Center(
                      child: TextButton(
                        style: TextButton.styleFrom(
                          padding: EdgeInsets.zero,
                          textStyle: const TextStyle(
                              fontSize: 16, fontWeight: FontWeight.w600),
                        ),
                        onPressed: _busy
                            ? null
                            : () async {
                                await widget.onDone();
                                // RootDecider will swap to AuthGate on completion.
                              },
                        child: const Text('Log in'),
                      ),
                    ),
                    const SizedBox(height: AppTheme.onboardingLinkBottomGap),
                  ],
                ),
              ),
            );
          },
        ),
      ),
    );
  }
}
