import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/app_preferences.dart';
import '../core/extensions/context_extensions.dart';
import '../core/themes.dart';
import '../services/auth/auth_service.dart';
import '../utils/app_snackbars.dart';
import 'settings_screen.dart';

/// Profile screen showing user information and quick stats
class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  State<ProfileScreen> createState() => _ProfileScreenState();
}

class _ProfileScreenState extends State<ProfileScreen> {
  final _prefs = AppPreferences.instance;

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
                        color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
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
                      child: user.photoURL == null
                          ? const Icon(Icons.person, size: 48)
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
                    const SizedBox(height: AppTheme.spaceSm),

                    // Email
                    Text(
                      user.email ?? '',
                      style: theme.textTheme.bodyMedium?.copyWith(
                        color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
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
            Card(
              child: Padding(
                padding: const EdgeInsets.all(AppTheme.spaceMd),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      'Account Status',
                      style: theme.textTheme.titleLarge,
                    ),
                    const SizedBox(height: AppTheme.spaceMd),
                    _buildStatusRow(
                      context,
                      icon: Icons.email_outlined,
                      label: 'Email verified',
                      value: user.emailVerified ? 'Yes' : 'No',
                      isVerified: user.emailVerified,
                    ),
                    _buildStatusRow(
                      context,
                      icon: Icons.location_on_outlined,
                      label: 'Location sharing',
                      value: _prefs.shareLocation ? 'Enabled' : 'Disabled',
                      isVerified: _prefs.shareLocation,
                    ),
                    _buildStatusRow(
                      context,
                      icon: Icons.data_usage_outlined,
                      label: 'Mobile data uploads',
                      value: _prefs.useMobileUploads ? 'Enabled' : 'Disabled',
                      isVerified: _prefs.useMobileUploads,
                    ),
                  ],
                ),
              ),
            ),

            const SizedBox(height: AppTheme.spaceLg),

            // Sign out button
            OutlinedButton.icon(
              onPressed: _handleSignOut,
              icon: const Icon(Icons.logout),
              label: const Text('Sign out'),
              style: OutlinedButton.styleFrom(
                foregroundColor: AppColors.error,
                side: BorderSide(color: AppColors.error.withValues(alpha: 0.5)),
              ),
            ),
          ],
        ],
      ),
    );
  }

  Widget _buildStatusRow(
    BuildContext context, {
    required IconData icon,
    required String label,
    required String value,
    required bool isVerified,
  }) {
    final theme = context.theme;
    final isDark = context.isDarkMode;

    return Padding(
      padding: const EdgeInsets.only(bottom: AppTheme.spaceSm),
      child: Row(
        children: [
          Icon(
            icon,
            size: 20,
            color: isDark ? AppColors.darkTextSecondary : AppColors.lightTextSecondary,
          ),
          const SizedBox(width: AppTheme.spaceSm),
          Expanded(
            child: Text(
              label,
              style: theme.textTheme.bodyMedium,
            ),
          ),
          Text(
            value,
            style: theme.textTheme.bodyMedium?.copyWith(
              color: isVerified ? AppColors.success : AppColors.warning,
              fontWeight: FontWeight.w600,
            ),
          ),
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
