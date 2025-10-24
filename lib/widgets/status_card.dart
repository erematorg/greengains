import 'package:flutter/material.dart';

import '../core/themes.dart';

class StatusCard extends StatefulWidget {
  final bool isCollecting;
  final bool isUploading;
  final Future<void> Function() onToggle;

  const StatusCard({
    super.key,
    required this.isCollecting,
    required this.isUploading,
    required this.onToggle,
  });

  @override
  State<StatusCard> createState() => _StatusCardState();
}

class _StatusCardState extends State<StatusCard> {
  bool _isToggling = false;

  Future<void> _toggleCollection() async {
    if (_isToggling) return;

    setState(() => _isToggling = true);
    try {
      await widget.onToggle();
    } finally {
      if (mounted) {
        setState(() => _isToggling = false);
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final statusColor = widget.isCollecting
        ? Colors.green
        : theme.colorScheme.onSurfaceVariant;
    final statusText = widget.isCollecting ? 'Collecting data' : 'Paused';
    final statusIcon = widget.isCollecting ? Icons.sensors : Icons.pause_circle_outline;

    return Card(
      child: InkWell(
        onTap: _isToggling ? null : _toggleCollection,
        borderRadius: BorderRadius.circular(12),
        child: Padding(
          padding: const EdgeInsets.all(AppTheme.spaceSm),
          child: Column(
            children: [
              Row(
                children: [
                  Icon(statusIcon, color: statusColor),
                  const SizedBox(width: AppTheme.spaceXs),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          statusText,
                          style: theme.textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.w600,
                            color: statusColor,
                          ),
                        ),
                        const SizedBox(height: 2),
                        Text(
                          widget.isCollecting
                              ? 'Tap to pause data collection'
                              : 'Tap to resume data collection',
                          style: theme.textTheme.bodySmall?.copyWith(
                            color: theme.colorScheme.onSurfaceVariant,
                          ),
                        ),
                      ],
                    ),
                  ),
                  if (_isToggling)
                    const SizedBox(
                      width: 20,
                      height: 20,
                      child: CircularProgressIndicator(strokeWidth: 2),
                    ),
                ],
              ),
              if (widget.isCollecting && widget.isUploading) ...[
                const SizedBox(height: AppTheme.spaceXs),
                Row(
                  children: [
                    SizedBox(
                      width: 16,
                      height: 16,
                      child: CircularProgressIndicator(
                        strokeWidth: 2,
                        valueColor: AlwaysStoppedAnimation<Color>(
                          theme.colorScheme.primary,
                        ),
                      ),
                    ),
                    const SizedBox(width: AppTheme.spaceXs),
                    Text(
                      'Uploading...',
                      style: theme.textTheme.bodySmall?.copyWith(
                        color: theme.colorScheme.primary,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                  ],
                ),
              ],
            ],
          ),
        ),
      ),
    );
  }
}
