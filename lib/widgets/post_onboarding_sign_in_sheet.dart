import 'package:flutter/material.dart';
import '../core/themes.dart';
import '../services/auth/auth_service.dart';
import '../utils/app_snackbars.dart';

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
    } catch (e) {
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
                onPressed: _loading ? null : () => Navigator.of(context).pop(false),
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
