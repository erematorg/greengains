import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:firebase_auth/firebase_auth.dart';
import '../core/extensions/context_extensions.dart';
import '../core/themes.dart';
import '../services/auth/auth_service.dart';
import '../utils/app_snackbars.dart';
import '../widgets/referral_invite_card.dart';
import 'settings_screen.dart';
import 'statistics_screen.dart';

/// Profile screen showing user information and quick stats
/// REDESIGNED: Compact layout that fits without scrolling
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
        actions: [
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
      body: user == null ? _buildSignedOutState(theme, isDark) : _buildSignedInState(user, theme, isDark),
    );
  }

  /// Signed-out state (keep existing design)
  Widget _buildSignedOutState(ThemeData theme, bool isDark) {
    return Padding(
      padding: AppTheme.pagePadding,
      child: Card(
        child: Padding(
          padding: const EdgeInsets.all(AppTheme.spaceMd),
          child: Column(
            mainAxisSize: MainAxisSize.min,
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
    );
  }

  /// REDESIGNED: Compact signed-in state (no scrolling required)
  Widget _buildSignedInState(User user, ThemeData theme, bool isDark) {
    return Padding(
      padding: AppTheme.pagePadding,
      child: Column(
        children: [
          // COMPACT USER HEADER (~100px)
          _buildCompactUserHeader(user, theme, isDark),

          const SizedBox(height: AppTheme.spaceMd),

          // REFERRAL CARD (critical for monetization)
          ReferralInviteCard(user: user),

          const SizedBox(height: AppTheme.spaceMd),

          // COMPACT STATISTICS NAVIGATION (~70px)
          _buildCompactStatsCard(theme, isDark),

          const Spacer(), // Pushes sign out to bottom if screen is taller

          const SizedBox(height: AppTheme.spaceMd),

          // SIGN OUT BUTTON (bottom pinned)
          // SIGN OUT / CREATE ACCOUNT BUTTON
          if (user.isAnonymous)
            // GUEST MODE: Show Official Google Sign-In Button
            Center(
              child: InkWell(
                onTap: _handleGoogleSignIn,
                borderRadius: BorderRadius.circular(4), // Match Google's border radius
                child: Image.asset(
                  'assets/brands/google_signin_button.png',
                  height: 48, // Standard height
                ),
              ),
            )
          else
            // LOGGED IN MODE: Show standard "Sign Out"
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
      ),
    );
  }

  /// COMPACT USER HEADER: Horizontal layout, smaller avatar, inline member badge
  Widget _buildCompactUserHeader(User user, ThemeData theme, bool isDark) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        child: Row(
          children: [
            // Smaller avatar (40px radius instead of 48px)
            CircleAvatar(
              radius: 40,
              backgroundImage: user.photoURL != null
                  ? NetworkImage(user.photoURL!)
                  : null,
              onBackgroundImageError: user.photoURL != null
                  ? (exception, stackTrace) {
                      // Log error silently, fallback to icon child
                    }
                  : null,
              child: user.photoURL == null
                  ? const Icon(Icons.person, size: 40)
                  : null,
            ),
            const SizedBox(width: AppTheme.spaceMd),

            // Compact info column
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Name (single line)
                  Text(
                    user.displayName ?? 'User',
                    style: theme.textTheme.titleLarge?.copyWith(
                      fontWeight: FontWeight.w600,
                    ),
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                  ),
                  const SizedBox(height: AppTheme.spaceXxs),

                  // Inline member since (no container, just text)
                  if (user.metadata.creationTime != null)
                    Text(
                      'Member since ${_formatDate(user.metadata.creationTime!)}',
                      style: theme.textTheme.bodySmall?.copyWith(
                        color: theme.colorScheme.onSurfaceVariant,
                      ),
                    ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  /// COMPACT STATISTICS NAVIGATION: Single-line ListTile instead of multi-line card
  Widget _buildCompactStatsCard(ThemeData theme, bool isDark) {
    return Card(
      child: ListTile(
        onTap: () {
          HapticFeedback.lightImpact();
          Navigator.of(context).push(
            MaterialPageRoute(
              builder: (context) => const StatisticsScreen(),
            ),
          );
        },
        leading: Container(
          padding: const EdgeInsets.all(8),
          decoration: BoxDecoration(
            color: AppColors.primaryAlpha(0.12),
            borderRadius: BorderRadius.circular(8),
          ),
          child: Icon(
            Icons.bar_chart,
            color: AppColors.primary,
            size: 24,
          ),
        ),
        title: Text(
          'View Statistics',
          style: theme.textTheme.titleMedium?.copyWith(
            fontWeight: FontWeight.w600,
          ),
        ),
        subtitle: Text(
          'Track your contributions',
          style: theme.textTheme.bodySmall?.copyWith(
            color: AppColors.textSecondary(isDark),
          ),
        ),
        trailing: Icon(
          Icons.chevron_right,
          color: AppColors.textTertiary(isDark),
        ),
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
