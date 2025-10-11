import 'package:flutter/material.dart';
import '../providers/sensor_provider.dart';
import '../widgets/sensor_tile.dart';
import '../widgets/accelerometer_tile.dart';
import '../widgets/gyroscope_tile.dart';
import '../widgets/magnetometer_tile.dart';
import 'settings_screen.dart';
import '../core/themes.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  late final SensorProvider _provider;

  @override
  void initState() {
    super.initState();
    _provider = SensorProvider();
    _provider.start();
    // Note: Integrate sensor collection here (other sensors) when ready
    // Note: Add data upload to server here when backend is ready
  }

  @override
  void dispose() {
    _provider.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        automaticallyImplyLeading: false,
        title: null,
        actions: [
          if (Theme.of(context).platform == TargetPlatform.android ||
              Theme.of(context).platform == TargetPlatform.iOS)
            IconButton(
              icon: const Icon(Icons.settings_outlined),
              tooltip: 'Settings',
              onPressed: () => Navigator.of(context).push(
                MaterialPageRoute(builder: (_) => const SettingsScreen()),
              ),
            ),
        ],
      ),
      body: SafeArea(
        child: AnimatedBuilder(
          animation: _provider,
          builder: (context, _) {
            return ListView(
              padding: const EdgeInsets.symmetric(
                vertical: AppTheme.spaceXs,
                horizontal: AppTheme.spaceSm,
              ),
              children: [
                const _SectionTitle('Environment'),
                Card(
                  child: SensorTile(
                    title: 'Light',
                    unit: 'lux',
                    value: _provider.ambientLux,
                  ),
                ),
                const Divider(height: AppTheme.spaceMd),
                const _SectionTitle('Motion'),
                Card(child: AccelerometerTile(values: _provider.accelerometer)),
                Card(child: GyroscopeTile(values: _provider.gyroscope)),
                Card(child: MagnetometerTile(values: _provider.magnetometer)),
              ],
            );
          },
        ),
      ),
      // Note: Rewards system entry point
    );
  }
}

class _SectionTitle extends StatelessWidget {
  final String text;
  const _SectionTitle(this.text);

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: AppTheme.spaceXs),
      child: Text(
        text,
        style: Theme.of(context).textTheme.titleLarge?.copyWith(fontWeight: FontWeight.w700),
      ),
    );
  }
}
