import 'package:flutter/material.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';

/// Compact horizontal contribution stats bar
class ContributionStatsCard extends StatefulWidget {
  const ContributionStatsCard({super.key});

  @override
  State<ContributionStatsCard> createState() => _ContributionStatsCardState();
}

class _ContributionStatsCardState extends State<ContributionStatsCard> {
  final _repository = ContributionRepository();
  ContributionStats _stats = ContributionStats.empty;
  bool _loading = true;

  @override
  void initState() {
    super.initState();
    _loadStats();
  }

  Future<void> _loadStats() async {
    setState(() => _loading = true);
    try {
      final stats = await _repository.getStats();
      if (mounted) {
        setState(() {
          _stats = stats;
          _loading = false;
        });
      }
    } catch (e) {
      if (mounted) {
        setState(() => _loading = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    if (_loading) {
      return Container(
        padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 16),
        child: const Center(
          child: SizedBox(
            width: 16,
            height: 16,
            child: CircularProgressIndicator(strokeWidth: 2),
          ),
        ),
      );
    }

    return Container(
      padding: const EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: const Color(0xFFFAFAFA), // Neutral surface (hsl(0, 0%, 98%))
        borderRadius: BorderRadius.circular(12),
        boxShadow: [
          // Inner light shadow (top) - simulate light from above
          BoxShadow(
            color: Colors.white.withOpacity(0.8),
            offset: const Offset(0, -1),
            blurRadius: 1,
            spreadRadius: 0,
          ),
          // Outer darker shadow (bottom) - depth
          BoxShadow(
            color: Colors.black.withOpacity(0.08),
            offset: const Offset(0, 2),
            blurRadius: 8,
            spreadRadius: 0,
          ),
        ],
      ),
      child: Row(
        children: [
          // Icon
          Container(
            padding: const EdgeInsets.all(8),
            decoration: BoxDecoration(
              color: const Color.fromRGBO(76, 175, 80, 0.1), // Primary green with low opacity
              borderRadius: BorderRadius.circular(8),
            ),
            child: Icon(
              Icons.eco,
              color: const Color(0xFF4CAF50), // Primary green
              size: 20,
            ),
          ),
          const SizedBox(width: 16),

          // Stats in horizontal row
          Expanded(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                _buildCompactStat(
                  theme,
                  label: 'Total',
                  value: '${_stats.totalUploads}',
                ),
                _buildDivider(),
                _buildCompactStat(
                  theme,
                  label: 'Today',
                  value: '${_stats.uploadsToday}',
                ),
                if (_stats.currentStreak > 0) ...[
                  _buildDivider(),
                  _buildCompactStat(
                    theme,
                    label: 'Streak',
                    value: '${_stats.currentStreak}d',
                    icon: Icons.local_fire_department,
                    iconColor: const Color(0xFFFF9800), // Semantic warning/orange
                  ),
                ],
              ],
            ),
          ),

          // Refresh button
          const SizedBox(width: 8),
          IconButton(
            icon: const Icon(Icons.refresh, size: 18),
            onPressed: _loadStats,
            color: Colors.grey.shade600,
            padding: EdgeInsets.zero,
            constraints: const BoxConstraints(
              minWidth: 32,
              minHeight: 32,
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildDivider() {
    return Container(
      width: 1,
      height: 24,
      color: Colors.grey.shade300,
    );
  }

  Widget _buildCompactStat(
    ThemeData theme, {
    required String label,
    required String value,
    IconData? icon,
    Color? iconColor,
  }) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        if (icon != null)
          Icon(icon, size: 14, color: iconColor ?? const Color(0xFF4CAF50)),
        Text(
          value,
          style: theme.textTheme.titleMedium?.copyWith(
            fontWeight: FontWeight.bold,
            color: Colors.grey.shade800, // High contrast for primary info
            height: 1.2,
          ),
        ),
        const SizedBox(height: 2),
        Text(
          label,
          style: theme.textTheme.bodySmall?.copyWith(
            color: Colors.grey.shade600, // Softer gray for secondary info
            fontSize: 11,
          ),
        ),
      ],
    );
  }
}
