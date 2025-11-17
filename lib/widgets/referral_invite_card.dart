import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import '../core/themes.dart';
import '../utils/app_snackbars.dart';

class ReferralInviteCard extends StatelessWidget {
  const ReferralInviteCard({
    super.key,
    required this.user,
  });

  final User user;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final referralCode = _buildReferralCode();
    final referralLink = 'https://greengains.eremat.org/invite/$referralCode';

    return Card(
      child: Padding(
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
                CircleAvatar(
                  radius: 20,
                  backgroundColor: theme.colorScheme.primary.withValues(alpha: 0.12),
                  child: Icon(Icons.diversity_3, color: theme.colorScheme.primary),
                ),
                const SizedBox(width: 12),
                Expanded(
                  child: Text(
                    'Invite a friend',
                    style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
                  ),
                ),
              ],
            ),
            const SizedBox(height: AppTheme.spaceSm),
            Text(
              'Earn bonus credits when friends contribute data.',
              style: theme.textTheme.bodyMedium,
            ),
            const SizedBox(height: AppTheme.spaceSm),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 12),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(12),
                color: theme.colorScheme.surfaceContainerHighest,
              ),
              child: Row(
                children: [
                  Text(
                    referralCode,
                    style: theme.textTheme.titleMedium?.copyWith(
                      fontFeatures: const [FontFeature.tabularFigures()],
                    ),
                  ),
                  const Spacer(),
                  TextButton.icon(
                    onPressed: () async {
                      await Clipboard.setData(ClipboardData(text: referralLink));
                      if (context.mounted) {
                        AppSnackbars.showInfo(context, 'Referral link copied');
                      }
                    },
                    icon: const Icon(Icons.copy, size: 16),
                    label: const Text('Copy link'),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  String _buildReferralCode() {
    final uid = user.uid;
    final seed = uid.hashCode.abs().toRadixString(36).toUpperCase();
    return 'GG-${seed.padLeft(5, '0').substring(0, 5)}';
  }
}
