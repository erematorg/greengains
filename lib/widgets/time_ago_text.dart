import 'dart:async';
import 'package:flutter/material.dart';

/// A widget that displays "time ago" text and automatically updates every 30 seconds.
/// Only rebuilds itself, not parent widgets - optimized for performance.
class TimeAgoText extends StatefulWidget {
  const TimeAgoText({
    super.key,
    required this.timestamp,
    this.style,
    this.prefix = '',
  });

  final DateTime timestamp;
  final TextStyle? style;
  final String prefix;

  @override
  State<TimeAgoText> createState() => _TimeAgoTextState();
}

class _TimeAgoTextState extends State<TimeAgoText> {
  Timer? _updateTimer;

  @override
  void initState() {
    super.initState();
    // Update every 30 seconds to keep time-ago text accurate
    _updateTimer = Timer.periodic(const Duration(seconds: 30), (_) {
      if (mounted) setState(() {});
    });
  }

  @override
  void dispose() {
    _updateTimer?.cancel();
    super.dispose();
  }

  String _getTimeAgo() {
    final now = DateTime.now();
    final diff = now.difference(widget.timestamp);

    if (diff.inMinutes < 1) {
      return 'just now';
    } else if (diff.inMinutes < 60) {
      return '${diff.inMinutes}m ago';
    } else if (diff.inHours < 24) {
      return '${diff.inHours}h ago';
    } else {
      return '${diff.inDays}d ago';
    }
  }

  @override
  Widget build(BuildContext context) {
    return Text(
      '${widget.prefix}${_getTimeAgo()}',
      style: widget.style,
    );
  }
}
