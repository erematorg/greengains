import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/extensions/context_extensions.dart';
import '../core/themes.dart';
import '../services/auth/auth_service.dart';
import '../utils/app_snackbars.dart';
import '../widgets/referral_invite_card.dart';
import 'settings_screen.dart';

/// Profile screen showing user information and quick stats
class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;
    final theme = context.theme;
    final isDark = context.isDarkMode;

    return Scaffold(
      appBar: AppBar(
        title: const Text('Profile'),
        actions: [
          // Settings cog icon (top right)
          IconButton(
            icon: const Icon(Icons.settings_outlined),
            onPressed: () {
              Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => const SettingsScreen(),
                ),
              );
            },
            tooltip: 'Settings',
          ),
        ],
      ),
      body: ListView(
        padding: AppTheme.pagePadding,
        children: [
          // Account section
          if (user == null) ...[
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Center(
                      child: Icon(
                        Icons.account_circle_outlined,
                        size: 80,
                        color: theme.colorScheme.onSurfaceVariant,
                      ),
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    Text(
                      'Sign in to get started',
                      style: theme.textTheme.titleLarge,
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceSm),
                    Text(
                      'Sync your contributions across devices and earn rewards',
                      style: theme.textTheme.bodyMedium,
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceLg),
                    FilledButton.icon(
                      onPressed: _handleGoogleSignIn,
                      icon: const Icon(Icons.login),
                      label: const Text('Sign in with Google'),
                    ),
                  ],
                ),
              ),
            ),
          ] else ...[
            // User profile card
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceLg),
                child: Column(
                  children: [
                    // Profile picture
                    CircleAvatar(
                      radius: 48,
                      backgroundImage: user.photoURL != null
                          ? NetworkImage(user.photoURL!)
                          : null,
                      onBackgroundImageError: user.photoURL != null
                          ? (exception, stackTrace) {
                              // Log error silently, fallback to icon child
                            }
                          : null,
                      child: user.photoURL == null
                          ? const Icon(Icons.person, size: 56)
                          : null,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),

                    // Name
                    Text(
                      user.displayName ?? 'User',
                      style: theme.textTheme.headlineMedium?.copyWith(
                        fontWeight: FontWeight.w600,
                      ),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceXs),
                    Text(
                      'Mapping greener cities together',
                      style: theme.textTheme.bodyMedium?.copyWith(
                        color: theme.colorScheme.primary,
                        fontWeight: FontWeight.w600,
                      ),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceSm),

                    // Email
                    Text(
                      user.email ?? '',
                      style: theme.textTheme.bodyMedium?.copyWith(
                        color: theme.colorScheme.onSurfaceVariant,
                      ),
                      textAlign: TextAlign.center,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),

                    // Member since (if available)
                    if (user.metadata.creationTime != null)
                      Container(
                        padding: const EdgeInsets.symmetric(
                          horizontal: AppTheme.spaceMd,
                          vertical: AppTheme.spaceSm,
                        ),
                        decoration: BoxDecoration(
                          color: isDark ? AppColors.darkSurfaceElevated : AppColors.lightSurface,
                          borderRadius: BorderRadius.circular(8),
                        ),
                        child: Text(
                          'Member since ${_formatDate(user.metadata.creationTime!)}',
                          style: theme.textTheme.bodySmall,
                        ),
                      ),
                  ],
                ),
              ),
            ),

            const SizedBox(height: AppTheme.spaceLg),

            // Quick stats (if useful)
            ReferralInviteCard(user: user),

            const SizedBox(height: AppTheme.spaceLg),

            // Sign out button
            OutlinedButton.icon(
              onPressed: _handleSignOut,
              icon: const Icon(Icons.logout),
              label: const Text('Sign out'),
              style: OutlinedButton.styleFrom(
                foregroundColor: AppColors.error,
                side: BorderSide(color: AppColors.error.withValues(alpha: 0.5)),
                minimumSize: const Size.fromHeight(56),
              ),
            ),
          ],
        ],
      ),
    );
  }

  String _formatDate(DateTime date) {
    final months = [
      'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
      'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
    ];
    return '${months[date.month - 1]} ${date.year}';
  }

  Future<void> _handleGoogleSignIn() async {
    HapticFeedback.mediumImpact();
    try {
      await AuthService.signInWithGoogleUniversal();
      if (mounted) {
        setState(() {});
        AppSnackbars.showSuccess(context, 'Signed in successfully');
      }
    } catch (e) {
      if (mounted) {
        AppSnackbars.showError(context, 'Sign-in failed: $e');
      }
    }
  }

  Future<void> _handleSignOut() async {
    HapticFeedback.lightImpact();
    try {
      await FirebaseAuth.instance.signOut();
      if (mounted) {
        setState(() {});
        AppSnackbars.showSuccess(context, 'Signed out');
      }
    } catch (e) {
      if (mounted) {
        AppSnackbars.showError(context, 'Sign-out failed: $e');
      }
    }
  }
}
