import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BatteryOptimizationDialog extends StatelessWidget {
  const BatteryOptimizationDialog({super.key});

  static const platform = MethodChannel('greengains/foreground');

  Future<void> _openSettings(BuildContext context) async {
    try {
      await platform.invokeMethod('requestIgnoreBatteryOptimizations');
      if (context.mounted) {
        Navigator.of(context).pop();
      }
    } on PlatformException catch (e) {
      print("Failed to open settings: '${e.message}'.");
    }
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text('Maximize Your Earnings'),
      content: const SingleChildScrollView(
        child: ListBody(
          children: <Widget>[
            Text(
              'To earn 24/7, GreenGains needs to run in the background without being killed by the system.',
            ),
            SizedBox(height: 10),
            Text(
              'Please disable "Battery Optimization" for GreenGains in the next screen.',
              style: TextStyle(fontWeight: FontWeight.bold),
            ),
          ],
        ),
      ),
      actions: <Widget>[
        TextButton(
          child: const Text('Later'),
          onPressed: () {
            Navigator.of(context).pop();
          },
        ),
        FilledButton(
          onPressed: () => _openSettings(context),
          child: const Text('Allow Background Run'),
        ),
      ],
    );
  }
}
