import 'package:flutter/material.dart';
import '../data/models/contribution_stats.dart';
import '../data/repositories/contribution_repository.dart';

/// Card displaying contribution statistics
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
        setState(() {
          _loading = false;
        });
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    if (_loading) {
      return const Card(
        margin: EdgeInsets.all(16),
        child: Padding(
          padding: EdgeInsets.all(20),
          child: Center(
            child: CircularProgressIndicator(),
          ),
        ),
      );
    }

    return Card(
      margin: const EdgeInsets.all(16),
      elevation: 4,
      shadowColor: Colors.green.withOpacity(0.3),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(12),
          gradient: LinearGradient(
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
            colors: [
              Colors.green.shade50,
              Colors.white,
            ],
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.all(20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                children: [
                  Container(
                    padding: const EdgeInsets.all(8),
                    decoration: BoxDecoration(
                      color: Colors.green.shade100,
                      borderRadius: BorderRadius.circular(8),
                    ),
                    child: Icon(
                      Icons.eco,
                      color: Colors.green.shade700,
                      size: 24,
                    ),
                  ),
                  const SizedBox(width: 12),
                  Text(
                    'Your Impact',
                    style: theme.textTheme.titleLarge?.copyWith(
                      fontWeight: FontWeight.bold,
                      color: Colors.green.shade800,
                    ),
                  ),
                ],
              ),
            const SizedBox(height: 20),

            // Total contributions
            Row(
              children: [
                Expanded(
                  child: _buildStatItem(
                    theme,
                    icon: Icons.upload,
                    label: 'Total',
                    value: _stats.totalUploads.toString(),
                  ),
                ),
                const SizedBox(width: 16),
                Expanded(
                  child: _buildStatItem(
                    theme,
                    icon: Icons.today,
                    label: 'Today',
                    value: _stats.uploadsToday.toString(),
                  ),
                ),
              ],
            ),

            const SizedBox(height: 16),

            // Streak
            if (_stats.currentStreak > 0)
              _buildStatItem(
                theme,
                icon: Icons.local_fire_department,
                label: 'Current Streak',
                value: '${_stats.currentStreak} day${_stats.currentStreak != 1 ? 's' : ''}',
                iconColor: Colors.orange.shade600,
              ),

            // Refresh button
            const SizedBox(height: 12),
            Center(
              child: TextButton.icon(
                onPressed: _loadStats,
                icon: Icon(Icons.refresh, size: 16, color: Colors.green.shade700),
                label: Text('Refresh', style: TextStyle(color: Colors.green.shade700)),
              ),
            ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildStatItem(
    ThemeData theme, {
    required IconData icon,
    required String label,
    required String value,
    Color? iconColor,
  }) {
    return Container(
      padding: const EdgeInsets.all(12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(8),
        border: Border.all(color: Colors.green.shade100),
      ),
      child: Row(
        children: [
          Container(
            padding: const EdgeInsets.all(6),
            decoration: BoxDecoration(
              color: (iconColor ?? Colors.green.shade600).withOpacity(0.1),
              borderRadius: BorderRadius.circular(6),
            ),
            child: Icon(
              icon,
              size: 20,
              color: iconColor ?? Colors.green.shade600,
            ),
          ),
          const SizedBox(width: 12),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(
                  label,
                  style: theme.textTheme.bodySmall?.copyWith(
                    color: Colors.grey.shade600,
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(height: 2),
                Text(
                  value,
                  style: theme.textTheme.titleMedium?.copyWith(
                    fontWeight: FontWeight.bold,
                    color: Colors.green.shade800,
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
