import 'package:flutter/material.dart';
import '../core/themes.dart';
import '../services/auth/auth_service.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter_svg/flutter_svg.dart';
import '../shell/app_shell.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  bool _googleBusy = false;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    return Scaffold(
      body: SafeArea(
        child: LayoutBuilder(
          builder: (_, constraints) {
            return SingleChildScrollView(
              padding: AppTheme.pagePadding,
              child: ConstrainedBox(
                constraints: BoxConstraints(minHeight: constraints.maxHeight),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        const SizedBox(height: AppTheme.spaceXl),
                        Text('Sign in', style: theme.textTheme.headlineMedium, textAlign: TextAlign.center),
                        const SizedBox(height: AppTheme.spaceLg),
                        GestureDetector(
                          onTap: _googleBusy
                              ? null
                              : () async {
                                  setState(() => _googleBusy = true);
                                  try {
                                    await AuthService.signInWithGoogleUniversal();
                                    // Ensure currentUser is fresh, then navigate as a fallback
                                    await Future.delayed(const Duration(milliseconds: 100));
                                    await FirebaseAuth.instance.currentUser?.reload();
                                    if (!context.mounted) return;
                                    if (FirebaseAuth.instance.currentUser != null) {
                                      Navigator.of(context).pushReplacement(
                                        MaterialPageRoute(builder: (_) => const AppShell()),
                                      );
                                    }
                                  } catch (e) {
                                    if (!context.mounted) return;
                                    ScaffoldMessenger.of(context).showSnackBar(
                                      SnackBar(content: Text('Google sign-in failed: $e')),
                                    );
                                  } finally {
                                    if (mounted) setState(() => _googleBusy = false);
                                  }
                                },
                          child: SizedBox(
                            height: AppTheme.authButtonHeight,
                            width: double.infinity,
                            child: Stack(
                              alignment: Alignment.center,
                              children: [
                                SvgPicture.asset(
                                  AppTheme.googleButtonAsset(Theme.of(context).brightness),
                                  fit: BoxFit.fitWidth,
                                  height: AppTheme.authButtonHeight,
                                  semanticsLabel: 'Sign in with Google',
                                ),
                                if (_googleBusy)
                                  const SizedBox(
                                    width: 22,
                                    height: 22,
                                    child: CircularProgressIndicator(strokeWidth: 2),
                                  ),
                              ],
                            ),
                          ),
                        ),
                        const SizedBox(height: AppTheme.spaceSm),
                        Row(
                          children: [
                            const Expanded(child: Divider()),
                            Padding(
                              padding: const EdgeInsets.symmetric(horizontal: 12),
                              child: Text('or', style: theme.textTheme.bodyMedium),
                            ),
                            const Expanded(child: Divider()),
                          ],
                        ),
                        const SizedBox(height: AppTheme.spaceSm),
                        const TextField(
                          decoration: InputDecoration(
                            labelText: 'Email',
                          ),
                          keyboardType: TextInputType.emailAddress,
                        ),
                        const SizedBox(height: AppTheme.spaceSm),
                        const TextField(
                          decoration: InputDecoration(
                            labelText: 'Password',
                          ),
                          obscureText: true,
                        ),
                      ],
                    ),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        FilledButton(
                          onPressed: () {}, // Note: email/password (placeholder)
                          child: const Text('Log in'),
                        ),
                        const SizedBox(height: AppTheme.bottomSafe),
                      ],
                    ),
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
