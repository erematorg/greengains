import 'package:flutter/material.dart';
import '../core/themes.dart';

/// Reusable sensor data display card with live values
/// Shows icon, title, current value, and unit
/// Enhanced with subtle animations and better visual feedback
class SensorDataCard extends StatefulWidget {
  final IconData icon;
  final String title;
  final String? value;
  final String unit;
  final bool enabled;
  final String statusLabel;
  final DateTime? updatedAt;

  const SensorDataCard({
    super.key,
    required this.icon,
    required this.title,
    required this.value,
    required this.unit,
    required this.enabled,
    required this.statusLabel,
    this.updatedAt,
  });

  @override
  State<SensorDataCard> createState() => _SensorDataCardState();
}

class _SensorDataCardState extends State<SensorDataCard>
    with SingleTickerProviderStateMixin {
  late AnimationController _pulseController;
  late Animation<double> _pulseAnimation;
  String? _previousValue;

  @override
  void initState() {
    super.initState();
    _pulseController = AnimationController(
      duration: const Duration(milliseconds: 600),
      vsync: this,
    );
    _pulseAnimation = Tween<double>(begin: 1.0, end: 1.0).animate(
      CurvedAnimation(parent: _pulseController, curve: Curves.easeInOut),
    );
  }

  @override
  void didUpdateWidget(SensorDataCard oldWidget) {
    super.didUpdateWidget(oldWidget);
    // Trigger subtle pulse when value changes
    if (widget.value != null &&
        widget.value != _previousValue &&
        _previousValue != null) {
      _pulseController.forward().then((_) => _pulseController.reverse());
    }
    _previousValue = widget.value;
  }

  @override
  void dispose() {
    _pulseController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;
    final isActive = widget.enabled && widget.value != null;

    return AnimatedBuilder(
      animation: _pulseAnimation,
      builder: (context, child) {
        return Transform.scale(
          scale: _pulseAnimation.value,
          child: child,
        );
      },
      child: Container(
        margin: const EdgeInsets.only(bottom: AppTheme.spaceSm),
        padding: const EdgeInsets.all(AppTheme.spaceMd),
        decoration: BoxDecoration(
          color: AppColors.surface(isDark),
          borderRadius: BorderRadius.circular(AppTheme.radiusMd),
          border: Border.all(
            color: isActive
                ? AppColors.primary.withValues(alpha: 0.2)
                : AppColors.border(isDark),
            width: isActive ? 1.5 : 1,
          ),
          boxShadow: isActive
              ? [
                  ...AppColors.glowEffect(AppColors.primary, opacity: 0.1),
                  ...(isDark
                      ? AppColors.elevationDark(active: true)
                      : AppColors.elevationLight(active: true)),
                ]
              : (isDark
                  ? AppColors.elevationDark(active: false)
                  : AppColors.elevationLight(active: false)),
        ),
        child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                padding: const EdgeInsets.all(AppTheme.spaceSm),
                decoration: BoxDecoration(
                  gradient: isActive
                      ? LinearGradient(
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                          colors: [
                            AppColors.primary.withValues(alpha: 0.15),
                            AppColors.primaryLight.withValues(alpha: 0.20),
                          ],
                        )
                      : null,
                  color: isActive ? null : AppColors.surfaceElevated(isDark),
                  borderRadius: BorderRadius.circular(AppTheme.radiusSm),
                ),
                child: Icon(
                  widget.icon,
                  size: 20,
                  color: isActive
                      ? AppColors.primary
                      : AppColors.textTertiary(isDark),
                ),
              ),
              const SizedBox(width: AppTheme.spaceMd),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Expanded(
                          child: Text(
                            widget.title,
                            style: theme.textTheme.titleSmall?.copyWith(
                              fontWeight: FontWeight.w700,
                              letterSpacing: 0.2,
                            ),
                          ),
                        ),
                        const SizedBox(width: AppTheme.spaceSm),
                        _StatusBadge(
                          label: widget.statusLabel,
                          active: isActive,
                        ),
                      ],
                    ),
                    const SizedBox(height: AppTheme.spaceXxs),
                    AnimatedDefaultTextStyle(
                      duration: const Duration(milliseconds: 300),
                      style: theme.textTheme.bodyLarge?.copyWith(
                            color: isActive
                                ? AppColors.textPrimary(isDark)
                                : AppColors.textSecondary(isDark),
                            fontWeight:
                                isActive ? FontWeight.w600 : FontWeight.normal,
                          ) ??
                          const TextStyle(),
                      child: Text(
                        widget.value ?? 'Waiting for data',
                      ),
                    ),
                    const SizedBox(height: 2),
                    Text(
                      widget.unit,
                      style: theme.textTheme.bodySmall?.copyWith(
                        color: AppColors.textSecondary(isDark),
                        fontSize: 12,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
        ],
        ),
      ),
    );
  }
}

class _StatusBadge extends StatelessWidget {
  const _StatusBadge({required this.label, required this.active});

  final String label;
  final bool active;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final isDark = theme.brightness == Brightness.dark;
    return Container(
      padding: const EdgeInsets.symmetric(
        horizontal: AppTheme.spaceSm,
        vertical: AppTheme.spaceXxs,
      ),
      decoration: BoxDecoration(
        color: active
            ? AppColors.primaryAlpha(0.15)
            : AppColors.border(isDark),
        borderRadius: BorderRadius.circular(AppTheme.radiusSm),
      ),
      child: Text(
        label,
        style: theme.textTheme.labelSmall?.copyWith(
          color: active ? AppColors.primary : theme.colorScheme.outline,
          fontWeight: FontWeight.w600,
        ),
      ),
    );
  }
}
