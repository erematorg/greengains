import 'package:flutter/material.dart';
import '../core/themes.dart';
import '../services/tracking/contribution_tracker.dart';

/// Displays today's tile contribution in a gamified way
class ContributionCard extends StatefulWidget {
  const ContributionCard({super.key});

  @override
  State<ContributionCard> createState() => _ContributionCardState();
}

class _ContributionCardState extends State<ContributionCard> {
  int _tilesToday = 0;
  int _dayTiles = 0;
  int _nightTiles = 0;
  int _lifetimeTiles = 0;
  bool _isLoading = true;

  @override
  void initState() {
    super.initState();
    // Use post-frame callback to avoid blocking build
    WidgetsBinding.instance.addPostFrameCallback((_) {
      if (mounted) {
        _loadStats();
      }
    });
  }

  Future<void> _loadStats() async {
    try {
      await ContributionTracker.instance.initialize();
      if (!mounted) return;

      setState(() {
        _tilesToday = ContributionTracker.instance.tilesToday;
        _dayTiles = ContributionTracker.instance.dayTiles;
        _nightTiles = ContributionTracker.instance.nightTiles;
        _lifetimeTiles = ContributionTracker.instance.lifetimeTiles;
        _isLoading = false;
      });
    } catch (e) {
      // Graceful fallback if SharedPreferences fails
      if (mounted) {
        setState(() {
          _isLoading = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    // Show loading state while initializing
    if (_isLoading) {
      return Card(
        elevation: 2,
        child: Container(
          height: 180,
          padding: const EdgeInsets.all(AppTheme.spaceMd),
          decoration: BoxDecoration(
            gradient: LinearGradient(
              begin: Alignment.topLeft,
              end: Alignment.bottomRight,
              colors: [
                theme.colorScheme.primaryContainer,
                theme.colorScheme.secondaryContainer.withOpacity(0.5),
              ],
            ),
            borderRadius: BorderRadius.circular(12),
          ),
          child: const Center(
            child: CircularProgressIndicator(),
          ),
        ),
      );
    }

    return Card(
      elevation: 2,
      child: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [
              theme.colorScheme.primaryContainer,
              theme.colorScheme.secondaryContainer.withOpacity(0.5),
            ],
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        child: Padding(
          padding: const EdgeInsets.all(AppTheme.spaceMd),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              // Header
              Row(
                children: [
                  Container(
                    padding: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      color: theme.colorScheme.primary.withOpacity(0.1),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Icon(
                      Icons.grid_on_outlined,
                      color: theme.colorScheme.primary,
                      size: 24,
                    ),
                  ),
                  const SizedBox(width: AppTheme.spaceSm),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'TODAY\'S CONTRIBUTION',
                          style: theme.textTheme.labelMedium?.copyWith(
                            color: theme.colorScheme.onPrimaryContainer,
                            fontWeight: FontWeight.w600,
                            letterSpacing: 0.5,
                          ),
                        ),
                        const SizedBox(height: 2),
                        Text(
                          'Tiles mapped while you walk',
                          style: theme.textTheme.bodySmall?.copyWith(
                            color: theme.colorScheme.onPrimaryContainer.withOpacity(0.7),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              const SizedBox(height: AppTheme.spaceMd),

              // Big number
              Row(
                crossAxisAlignment: CrossAxisAlignment.end,
                children: [
                  Text(
                    '+$_tilesToday',
                    style: theme.textTheme.displayLarge?.copyWith(
                      fontWeight: FontWeight.bold,
                      color: theme.colorScheme.primary,
                      height: 1.0,
                    ),
                  ),
                  const SizedBox(width: AppTheme.spaceXs),
                  Padding(
                    padding: const EdgeInsets.only(bottom: 8.0),
                    child: Text(
                      'TILES',
                      style: theme.textTheme.titleMedium?.copyWith(
                        fontWeight: FontWeight.w600,
                        color: theme.colorScheme.primary.withOpacity(0.7),
                      ),
                    ),
                  ),
                ],
              ),
              const SizedBox(height: AppTheme.spaceSm),

              // Day/Night split
              Container(
                padding: const EdgeInsets.symmetric(
                  horizontal: AppTheme.spaceSm,
                  vertical: AppTheme.spaceXs,
                ),
                decoration: BoxDecoration(
                  color: theme.colorScheme.surface.withOpacity(0.5),
                  borderRadius: BorderRadius.circular(8),
                ),
                child: Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    _buildSplitChip(
                      context,
                      icon: '🌞',
                      label: '$_dayTiles day',
                      theme: theme,
                    ),
                    const SizedBox(width: AppTheme.spaceSm),
                    Container(
                      width: 1,
                      height: 20,
                      color: theme.colorScheme.outline.withOpacity(0.3),
                    ),
                    const SizedBox(width: AppTheme.spaceSm),
                    _buildSplitChip(
                      context,
                      icon: '🌙',
                      label: '$_nightTiles night',
                      theme: theme,
                    ),
                  ],
                ),
              ),
              const SizedBox(height: AppTheme.spaceSm),

              // Lifetime total (small text)
              Text(
                'Total: $_lifetimeTiles tiles',
                style: theme.textTheme.bodySmall?.copyWith(
                  color: theme.colorScheme.onPrimaryContainer.withOpacity(0.6),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildSplitChip(
    BuildContext context, {
    required String icon,
    required String label,
    required ThemeData theme,
  }) {
    return Row(
      mainAxisSize: MainAxisSize.min,
      children: [
        Text(
          icon,
          style: const TextStyle(fontSize: 16),
        ),
        const SizedBox(width: 4),
        Text(
          label,
          style: theme.textTheme.labelMedium?.copyWith(
            fontWeight: FontWeight.w600,
            color: theme.colorScheme.onSurface,
          ),
        ),
      ],
    );
  }
}
