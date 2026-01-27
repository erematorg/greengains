import 'package:flutter/material.dart';
import '../core/themes.dart';
import '../services/daily_pot_service.dart';
import '../data/models/daily_pot.dart';

/// Credits display widget - shows total lifetime credits earned
/// Positioned below the daily pot icon for visibility
class CreditsDisplay extends StatelessWidget {
  const CreditsDisplay({super.key});

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;
    final service = DailyPotService.instance;

    return ValueListenableBuilder<DailyPot?>(
      valueListenable: service.pot,
      builder: (context, pot, _) {
        if (pot == null) {
          return const SizedBox.shrink();
        }

        return Container(
          padding: const EdgeInsets.symmetric(
            horizontal: 12,
            vertical: 8,
          ),
          decoration: BoxDecoration(
            color: isDark ? Colors.grey[850] : Colors.white,
            borderRadius: BorderRadius.circular(20),
            border: Border.all(
              color: AppColors.primary.withValues(alpha: 0.2),
              width: 1,
            ),
            boxShadow: isDark
                ? AppColors.elevationDark(active: false)
                : AppColors.elevationLight(active: false),
          ),
          child: Row(
            mainAxisSize: MainAxisSize.min,
            children: [
              // Coin icon
              Icon(
                Icons.monetization_on,
                color: AppColors.primary,
                size: 20,
              ),
              const SizedBox(width: 6),
              // Credits amount
              Text(
                pot.totalCredits.toString(),
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: AppColors.primary,
                ),
              ),
            ],
          ),
        );
      },
    );
  }
}
