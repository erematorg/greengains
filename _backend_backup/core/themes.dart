import 'package:flutter/material.dart';

class AppTheme {
  // Spacing scale (can be reused across screens)
  static const double spaceXs = 8;
  static const double spaceSm = 12;
  static const double spaceMd = 16;
  static const double spaceLg = 24;
  static const double spaceXl = 32;

  // Common layout tokens
  static const double ctaGapLink = 4; // gap between primary CTA and link below
  static const double bottomSafe = 16; // minimal space to bottom edges
  static const EdgeInsets pagePadding =
      EdgeInsets.fromLTRB(24, 16, 24, bottomSafe);
  static const double onboardingLinkBottomGap =
      28; // space from Login link to bottom

  // Radii
  static const double radiusLg = 24;
  // Onboarding hero sizing
  static const double onboardingHeroMin = 280;
  static const double onboardingHeroMax = 460;
  static const double onboardingHeroIconRatio = 0.46; // icon = ratio * heroH

  // Common component sizes
  static const double tileTrailingButtonWidth = 120;
  static const double tileTrailingButtonHeight = 40;
  static const double authButtonHeight = 56;

  static String googleButtonAsset(Brightness b) => b == Brightness.dark
      ? 'assets/brands/google_button_dark.svg'
      : 'assets/brands/google_button_light.svg';

  static ThemeData theme() {
    final base = ThemeData(useMaterial3: true);
    final scheme = ColorScheme.fromSeed(
      seedColor: _seed,
      brightness: Brightness.light,
    ).copyWith(
      onSurfaceVariant: const Color(0xFF4E5A55),
    );
    return base.copyWith(
      colorScheme: scheme,
      pageTransitionsTheme: const PageTransitionsTheme(builders: {
        TargetPlatform.android: ZoomPageTransitionsBuilder(),
        TargetPlatform.iOS: ZoomPageTransitionsBuilder(),
        TargetPlatform.windows: ZoomPageTransitionsBuilder(),
        TargetPlatform.macOS: ZoomPageTransitionsBuilder(),
        TargetPlatform.linux: ZoomPageTransitionsBuilder(),
      }),
      navigationBarTheme: NavigationBarThemeData(
        backgroundColor: const Color(0xFFF7F9F8),
        indicatorColor: scheme.primary.withValues(alpha: 0.12),
        elevation: 1,
        labelBehavior: NavigationDestinationLabelBehavior.alwaysShow,
        labelTextStyle: WidgetStatePropertyAll(
          base.textTheme.labelMedium?.copyWith(
            fontWeight: FontWeight.w600,
            color: scheme.onSurface,
          ),
        ),
      ),
      scaffoldBackgroundColor: const Color(0xFFF7F9F8),
      appBarTheme: AppBarTheme(
        backgroundColor: const Color(0xFFF7F9F8),
        foregroundColor: Colors.black,
        elevation: 0,
        scrolledUnderElevation: 0,
        titleTextStyle: base.textTheme.titleLarge?.copyWith(
          color: Colors.black,
          fontWeight: FontWeight.w700,
        ),
      ),
      cardTheme: CardTheme(
        color: scheme.surface,
        elevation: 0,
        surfaceTintColor: Colors.transparent,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
      ),
      dialogTheme: DialogTheme(
        backgroundColor: scheme.surface,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      ),
      bottomSheetTheme: BottomSheetThemeData(
        backgroundColor: scheme.surface,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
        ),
      ),
      snackBarTheme: SnackBarThemeData(
        behavior: SnackBarBehavior.floating,
        backgroundColor: scheme.inverseSurface,
        contentTextStyle:
            base.textTheme.bodyMedium?.copyWith(color: scheme.onInverseSurface),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      ),
      listTileTheme: ListTileThemeData(
        iconColor: scheme.onSurface,
        textColor: scheme.onSurface,
        titleTextStyle: base.textTheme.titleMedium?.copyWith(
          color: scheme.onSurface,
          fontWeight: FontWeight.w600,
        ),
        subtitleTextStyle: base.textTheme.bodyMedium?.copyWith(
          color: scheme.onSurface.withValues(alpha: 0.75),
        ),
      ),
      dividerTheme: DividerThemeData(
        color: scheme.outlineVariant,
        thickness: 1,
        space: 1,
      ),
      inputDecorationTheme: const InputDecorationTheme(
        filled: true,
        fillColor: Color(0xFFF2F6F4),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFFD9E1DE)),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFFD9E1DE)),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFF8EA5A1), width: 1.2),
        ),
        contentPadding: EdgeInsets.symmetric(vertical: 14, horizontal: 16),
      ),
      filledButtonTheme: FilledButtonThemeData(
        style: FilledButton.styleFrom(
          minimumSize: const Size.fromHeight(56),
          textStyle: const TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
          backgroundColor: scheme.primary,
          foregroundColor: Colors.white,
        ),
      ),
      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          foregroundColor: scheme.primary,
          textStyle: const TextStyle(fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
        ),
      ),
      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          minimumSize: const Size.fromHeight(48),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
          side: const BorderSide(color: Color(0xFFD9E1DE)),
          foregroundColor: Colors.black87,
          backgroundColor: Colors.white,
        ),
      ),
      iconButtonTheme: IconButtonThemeData(
        style: IconButton.styleFrom(
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          visualDensity: VisualDensity.standard,
        ),
      ),
      textTheme: base.textTheme.copyWith(
        // Headlines - bold and prominent
        headlineLarge:
            base.textTheme.headlineLarge?.copyWith(fontWeight: FontWeight.w700),
        headlineMedium: base.textTheme.headlineMedium
            ?.copyWith(fontWeight: FontWeight.w700),
        headlineSmall:
            base.textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w700),
        // Titles - strong hierarchy
        titleLarge:
            base.textTheme.titleLarge?.copyWith(fontWeight: FontWeight.w700),
        titleMedium:
            base.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
        titleSmall:
            base.textTheme.titleSmall?.copyWith(fontWeight: FontWeight.w600),
        // Body text - minimum 16sp for readability
        bodyLarge: base.textTheme.bodyLarge?.copyWith(fontSize: 16),
        bodyMedium: base.textTheme.bodyMedium?.copyWith(fontSize: 16),
        bodySmall: base.textTheme.bodySmall?.copyWith(fontSize: 14),
        // Labels
        labelLarge:
            base.textTheme.labelLarge?.copyWith(fontWeight: FontWeight.w600),
        labelMedium:
            base.textTheme.labelMedium?.copyWith(fontWeight: FontWeight.w500),
      ),
    );
  }

  static const _seed = Color(0xFF2F6D5F);

  static ThemeData themeDark() {
    final base = ThemeData(useMaterial3: true);
    final scheme =
        ColorScheme.fromSeed(seedColor: _seed, brightness: Brightness.dark)
            .copyWith(onSurfaceVariant: const Color(0xFF9DA7A2));
    return base.copyWith(
      colorScheme: scheme,
      pageTransitionsTheme: const PageTransitionsTheme(builders: {
        TargetPlatform.android: ZoomPageTransitionsBuilder(),
        TargetPlatform.iOS: ZoomPageTransitionsBuilder(),
        TargetPlatform.windows: ZoomPageTransitionsBuilder(),
        TargetPlatform.macOS: ZoomPageTransitionsBuilder(),
        TargetPlatform.linux: ZoomPageTransitionsBuilder(),
      }),
      navigationBarTheme: NavigationBarThemeData(
        backgroundColor: const Color(0xFF0F1115),
        indicatorColor: scheme.primary.withValues(alpha: 0.18),
        elevation: 1,
        labelBehavior: NavigationDestinationLabelBehavior.alwaysShow,
        labelTextStyle: WidgetStatePropertyAll(
          base.textTheme.labelMedium?.copyWith(
            fontWeight: FontWeight.w600,
            color: scheme.onSurface,
          ),
        ),
      ),
      // Slight green-tinted dark surfaces
      scaffoldBackgroundColor: const Color(0xFF0B1412),
      appBarTheme: AppBarTheme(
        backgroundColor: const Color(0xFF0B1412),
        foregroundColor: Colors.white,
        elevation: 0,
        scrolledUnderElevation: 0,
        titleTextStyle: base.textTheme.titleLarge?.copyWith(
          color: Colors.white,
          fontWeight: FontWeight.w700,
        ),
      ),
      cardTheme: CardTheme(
        color: scheme.surface,
        elevation: 0,
        surfaceTintColor: Colors.transparent,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(14)),
      ),
      dialogTheme: DialogTheme(
        backgroundColor: scheme.surface,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      ),
      bottomSheetTheme: BottomSheetThemeData(
        backgroundColor: scheme.surface,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
        ),
      ),
      snackBarTheme: SnackBarThemeData(
        behavior: SnackBarBehavior.floating,
        backgroundColor: scheme.inverseSurface,
        contentTextStyle:
            base.textTheme.bodyMedium?.copyWith(color: scheme.onInverseSurface),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      ),
      listTileTheme: ListTileThemeData(
        iconColor: scheme.onSurface,
        textColor: scheme.onSurface,
        titleTextStyle: base.textTheme.titleMedium?.copyWith(
          color: scheme.onSurface,
          fontWeight: FontWeight.w600,
        ),
        subtitleTextStyle: base.textTheme.bodyMedium?.copyWith(
          color: scheme.onSurface.withValues(alpha: 0.75),
        ),
      ),
      dividerTheme: DividerThemeData(
        color: scheme.outlineVariant,
        thickness: 1,
        space: 1,
      ),
      inputDecorationTheme: const InputDecorationTheme(
        filled: true,
        fillColor: Color(0xFF111A17),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFF21302A)),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFF21302A)),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.all(Radius.circular(14)),
          borderSide: BorderSide(color: Color(0xFF7FA392), width: 1.2),
        ),
        contentPadding: EdgeInsets.symmetric(vertical: 14, horizontal: 16),
      ),
      filledButtonTheme: FilledButtonThemeData(
        style: FilledButton.styleFrom(
          minimumSize: const Size.fromHeight(56),
          textStyle: const TextStyle(fontSize: 18, fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
          backgroundColor: scheme.primary,
          foregroundColor: Colors.white,
        ),
      ),
      textButtonTheme: TextButtonThemeData(
        style: TextButton.styleFrom(
          foregroundColor: scheme.primary,
          textStyle: const TextStyle(fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
        ),
      ),
      outlinedButtonTheme: OutlinedButtonThemeData(
        style: OutlinedButton.styleFrom(
          minimumSize: const Size.fromHeight(48),
          textStyle: const TextStyle(fontSize: 16, fontWeight: FontWeight.w600),
          shape: const StadiumBorder(),
          side: const BorderSide(color: Color(0xFF21302A)),
          foregroundColor: Colors.white,
          backgroundColor: const Color(0xFF0E1613),
        ),
      ),
      iconButtonTheme: IconButtonThemeData(
        style: IconButton.styleFrom(
          shape:
              RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          visualDensity: VisualDensity.standard,
        ),
      ),
      textTheme: base.textTheme.copyWith(
        // Headlines - bold and prominent
        headlineLarge:
            base.textTheme.headlineLarge?.copyWith(fontWeight: FontWeight.w700),
        headlineMedium: base.textTheme.headlineMedium
            ?.copyWith(fontWeight: FontWeight.w700),
        headlineSmall:
            base.textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w700),
        // Titles - strong hierarchy
        titleLarge:
            base.textTheme.titleLarge?.copyWith(fontWeight: FontWeight.w700),
        titleMedium:
            base.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
        titleSmall:
            base.textTheme.titleSmall?.copyWith(fontWeight: FontWeight.w600),
        // Body text - minimum 16sp for readability
        bodyLarge: base.textTheme.bodyLarge?.copyWith(fontSize: 16),
        bodyMedium: base.textTheme.bodyMedium?.copyWith(fontSize: 16),
        bodySmall: base.textTheme.bodySmall?.copyWith(fontSize: 14),
        // Labels
        labelLarge:
            base.textTheme.labelLarge?.copyWith(fontWeight: FontWeight.w600),
        labelMedium:
            base.textTheme.labelMedium?.copyWith(fontWeight: FontWeight.w500),
      ),
      brightness: Brightness.dark,
    );
  }
}

