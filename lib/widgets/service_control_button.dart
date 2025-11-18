import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import '../core/themes.dart';
import '../services/location/foreground_location_service.dart';
import '../utils/app_snackbars.dart';

/// Service control button with loading state and scale animation
/// Extracted to reduce rebuilds in parent widget
class ServiceControlButton extends StatefulWidget {
  const ServiceControlButton({super.key});

  @override
  State<ServiceControlButton> createState() => _ServiceControlButtonState();
}

class _ServiceControlButtonState extends State<ServiceControlButton>
    with SingleTickerProviderStateMixin {
  final _locationService = ForegroundLocationService.instance;
  bool _isTogglingService = false;
  late AnimationController _buttonAnimController;
  late Animation<double> _buttonScale;

  @override
  void initState() {
    super.initState();
    _buttonAnimController = AnimationController(
      duration: const Duration(milliseconds: 150),
      vsync: this,
    );
    _buttonScale = Tween<double>(begin: 1.0, end: 0.95).animate(
      CurvedAnimation(parent: _buttonAnimController, curve: Curves.easeInOut),
    );
  }

  @override
  void dispose() {
    _buttonAnimController.dispose();
    super.dispose();
  }

  Future<void> _toggleService() async {
    if (_isTogglingService) return; // Prevent double-tap

    setState(() {
      _isTogglingService = true;
    });

    // Trigger button scale animation
    _buttonAnimController.forward().then((_) => _buttonAnimController.reverse());

    final isRunning = _locationService.isRunning.value;
    try {
      if (isRunning) {
        await _locationService.stop();
      } else {
        HapticFeedback.mediumImpact();
        // TODO: surface a branded battery-optimization prompt (similar to Honeygain)
        // once we have a reliable detection hook so users know how to keep the service alive.
        await _locationService.start();
      }
    } catch (e) {
      if (!mounted) return;
      AppSnackbars.showError(
        context,
        'Failed to ${isRunning ? 'stop' : 'start'} tracking: $e',
      );
    } finally {
      if (mounted) {
        setState(() {
          _isTogglingService = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return ValueListenableBuilder<bool>(
      valueListenable: _locationService.isRunning,
      builder: (context, isRunning, _) {
        return AnimatedBuilder(
          animation: _buttonScale,
          builder: (context, child) {
            return Transform.scale(
              scale: _buttonScale.value,
              child: child,
            );
          },
          child: FilledButton.icon(
            onPressed: _isTogglingService ? null : _toggleService,
            icon: _isTogglingService
                ? SizedBox(
                    width: 20,
                    height: 20,
                    child: CircularProgressIndicator(
                      strokeWidth: 2.5,
                      valueColor: AlwaysStoppedAnimation(
                        isRunning ? Colors.white : theme.colorScheme.onPrimary,
                      ),
                    ),
                  )
                : Icon(
                    isRunning ? Icons.stop_circle_outlined : Icons.play_circle_filled,
                    size: 24,
                  ),
            label: Text(
              _isTogglingService
                  ? 'Processing...'
                  : (isRunning ? 'Stop Tracking' : 'Start Tracking'),
            ),
            style: FilledButton.styleFrom(
              backgroundColor: isRunning ? AppColors.error : AppColors.primary,
              foregroundColor: Colors.white,
              minimumSize: const Size.fromHeight(56),
              padding: const EdgeInsets.symmetric(
                vertical: AppTheme.spaceMd,
                horizontal: AppTheme.spaceLg,
              ),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(AppTheme.radiusLg),
              ),
              elevation: 0,
              shadowColor: Colors.transparent,
              textStyle: theme.textTheme.titleMedium?.copyWith(
                fontWeight: FontWeight.w700,
                letterSpacing: 0.5,
              ),
            ),
          ),
        );
      },
    );
  }
}
