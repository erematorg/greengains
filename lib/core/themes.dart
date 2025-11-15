import 'package:flutter/material.dart';

/// Centralized color system following UI/UX design guide
class AppColors {
  AppColors._();

  // Primary brand color (green)
  static const Color primary = Color(0xFF4CAF50);
  static const Color primaryLight = Color(0xFF66BB6A);
  static const Color primaryDark = Color(0xFF388E3C);

  // Semantic colors
  static const Color success = Color(0xFF4CAF50);
  static const Color warning = Color(0xFFFF9800);
  static const Color error = Color(0xFFE53935);
  static const Color errorDark = Color(0xFFD32F2F);

  // Light mode neutrals (HSL-based)
  static const Color lightBackground = Color(0xFFFFFFFF);
  static const Color lightSurface = Color(0xFFFAFAFA);
  static const Color lightSurfaceElevated = Color(0xFFF0F0F0);
  static const Color lightSurfaceActive = Color(0xFFF1F8F4);

  static const Color lightTextPrimary = Color(0xFF212121);
  static const Color lightTextSecondary = Color(0xFF757575);
  static const Color lightTextTertiary = Color(0xFF8A8A8A); // Improved contrast: 3.8:1 ratio (WCAG AA compliant)
  static const Color lightBorder = Color(0xFFE0E0E0);
  static const Color lightDivider = Color(0xFFBDBDBD);

  // Dark mode neutrals (HSL-based)
  static const Color darkBackground = Color(0xFF000000);
  static const Color darkSurface = Color(0xFF0D0D0D);
  static const Color darkSurfaceElevated = Color(0xFF1A1A1A);
  static const Color darkSurfaceActive = Color(0xFF0D2015);

  static const Color darkTextPrimary = Color(0xFFE0E0E0);
  static const Color darkTextSecondary = Color(0xFF9E9E9E);
  static const Color darkTextTertiary = Color(0xFF757575);
  static const Color darkBorder = Color(0xFF424242);
  static const Color darkDivider = Color(0xFF616161);

  // Helpers
  static Color shadowLight(double opacity) =>
      Colors.white.withValues(alpha: opacity);
  static Color shadowDark(double opacity) =>
      Colors.black.withValues(alpha: opacity);
  static Color primaryAlpha(double opacity) =>
      primary.withValues(alpha: opacity);

  static const List<Color> gradientGreen = [primaryLight, primary];
  static const List<Color> gradientRed = [error, errorDark];

  // Data Visualization & Mapping colors (for heatmaps, tiles, charts)
  static const Color accentTeal = Color(0xFF20B2AA); // Secondary brand color
  static const Color accentBlue = Color(0xFF2196F3); // For data viz variety
  static const Color accentPurple = Color(0xFF9C27B0); // Charts/graphs accent

  // Heatmap gradient (cold → neutral → hot) for intensity visualization
  static const Color heatmapCold = Color(0xFF3B82F6); // Blue (low intensity)
  static const Color heatmapMedium = Color(0xFFFFA726); // Orange (medium)
  static const Color heatmapHot = Color(0xFFEF4444); // Red (high intensity)

  // Coverage tile states (for map tiles showing visit frequency)
  static const Color tileUncovered = Color(0xFF9E9E9E); // Gray (not visited)
  static const Color tileLowCoverage = Color(0xFF81C784); // Light green (1-2 visits)
  static const Color tileMediumCoverage = Color(0xFF66BB6A); // Medium green (3-5 visits)
  static const Color tileHighCoverage = Color(0xFF4CAF50); // Dark green (6+ visits)

  // Achievement/reward tier colors
  static const Color tierBronze = Color(0xFFCD7F32);
  static const Color tierSilver = Color(0xFFC0C0C0);
  static const Color tierGold = Color(0xFFFFD700);
  static const Color tierPlatinum = Color(0xFFE5E4E2);

  // Box shadows for depth
  static List<BoxShadow> elevationLight({required bool active}) {
    return [
      BoxShadow(
        color: shadowLight(0.8),
        offset: const Offset(0, -1),
        blurRadius: 1,
        spreadRadius: 0,
      ),
      BoxShadow(
        color: shadowDark(active ? 0.12 : 0.06),
        offset: const Offset(0, 2),
        blurRadius: active ? 10 : 6,
        spreadRadius: 0,
      ),
    ];
  }

  static List<BoxShadow> elevationDark({required bool active}) {
    return [
      BoxShadow(
        color: shadowLight(0.05),
        offset: const Offset(0, -1),
        blurRadius: 1,
        spreadRadius: 0,
      ),
      BoxShadow(
        color: shadowDark(active ? 0.4 : 0.3),
        offset: const Offset(0, 2),
        blurRadius: active ? 10 : 6,
        spreadRadius: 0,
      ),
    ];
  }

  static List<BoxShadow> buttonShadow(bool isDark) {
    return [
      BoxShadow(
        color: isDark ? shadowLight(0.05) : shadowLight(0.2),
        offset: const Offset(0, -1),
        blurRadius: 0,
      ),
      BoxShadow(
        color: isDark ? shadowDark(0.6) : shadowDark(0.2),
        offset: const Offset(0, 3),
        blurRadius: 8,
        spreadRadius: 0,
      ),
    ];
  }

  static List<BoxShadow> glowEffect(Color color, {double opacity = 0.4}) {
    return [
      BoxShadow(
        color: color.withValues(alpha: opacity),
        blurRadius: 4,
        spreadRadius: 1,
      )
    ];
  }
}

class AppTheme {
  // Spacing scale (comprehensive)
  static const double spaceXxs = 4;
  static const double spaceXs = 8;
  static const double spaceSm = 12;
  static const double spaceMd = 16;
  static const double spaceLg = 24;
  static const double spaceXl = 32;
  static const double spaceXxl = 48;

  // Common layout tokens
  static const double ctaGapLink = 4;
  static const double bottomSafe = 16;
  static const EdgeInsets pagePadding = EdgeInsets.fromLTRB(24, 16, 24, bottomSafe);
  static const double onboardingLinkBottomGap = 28;

  // Radii
  static const double radiusSm = 8;
  static const double radiusMd = 12;
  static const double radiusLg = 16;
  static const double radiusXl = 24;

  // Touch targets (accessibility)
  static const double minTouchTarget = 48;

  // Onboarding hero sizing
  static const double onboardingHeroMin = 280;
  static const double onboardingHeroMax = 460;
  static const double onboardingHeroIconRatio = 0.46;

  // Common component sizes
  static const double tileTrailingButtonWidth = 120;
  static const double tileTrailingButtonHeight = 40;
  static const double authButtonHeight = 56;

  static String googleButtonAsset(Brightness b) => b == Brightness.dark
      ? 'assets/brands/google_button_dark.svg'
      : 'assets/brands/google_button_light.svg';

  static const _seed = Color(0xFF2F6D5F);

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
        elevation: 0,
        labelBehavior: NavigationDestinationLabelBehavior.alwaysShow,
        labelTextStyle: WidgetStatePropertyAll(
          base.textTheme.labelMedium?.copyWith(
            fontWeight: FontWeight.w600,
            fontSize: 12,
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
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          visualDensity: VisualDensity.standard,
        ),
      ),
      textTheme: base.textTheme.copyWith(
        headlineLarge: base.textTheme.headlineLarge?.copyWith(fontWeight: FontWeight.w700),
        headlineMedium: base.textTheme.headlineMedium?.copyWith(fontWeight: FontWeight.w700),
        headlineSmall: base.textTheme.headlineSmall?.copyWith(fontWeight: FontWeight.w700),
        titleLarge: base.textTheme.titleLarge?.copyWith(fontWeight: FontWeight.w700),
        titleMedium: base.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.w600),
        titleSmall: base.textTheme.titleSmall?.copyWith(fontWeight: FontWeight.w600),
        bodyLarge: base.textTheme.bodyLarge?.copyWith(fontSize: 16),
        bodyMedium: base.textTheme.bodyMedium?.copyWith(fontSize: 16),
        bodySmall: base.textTheme.bodySmall?.copyWith(fontSize: 14),
        labelLarge: base.textTheme.labelLarge?.copyWith(fontWeight: FontWeight.w600),
        labelMedium: base.textTheme.labelMedium?.copyWith(fontWeight: FontWeight.w500),
      ),
    );
  }

  static ThemeData themeDark() {
    final base = ThemeData(useMaterial3: true);
    final scheme = ColorScheme.fromSeed(seedColor: _seed, brightness: Brightness.dark)
        .copyWith(
      onSurfaceVariant: const Color(0xFF9DA7A2),
      // Force all text to be white/readable in dark mode
      onSurface: AppColors.darkTextPrimary,
      onPrimary: Colors.white,
      onSecondary: Colors.white,
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
        backgroundColor: const Color(0xFF0F1115),
        indicatorColor: scheme.primary.withValues(alpha: 0.18),
        elevation: 0,
        labelBehavior: NavigationDestinationLabelBehavior.alwaysShow,
        labelTextStyle: WidgetStatePropertyAll(
          base.textTheme.labelMedium?.copyWith(
            fontWeight: FontWeight.w600,
            fontSize: 12,
            color: AppColors.darkTextPrimary,
          ),
        ),
      ),
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
        iconColor: AppColors.darkTextPrimary,
        textColor: AppColors.darkTextPrimary,
        titleTextStyle: base.textTheme.titleMedium?.copyWith(
          color: AppColors.darkTextPrimary,
          fontWeight: FontWeight.w600,
        ),
        subtitleTextStyle: base.textTheme.bodyMedium?.copyWith(
          color: AppColors.darkTextSecondary,
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
          shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
          visualDensity: VisualDensity.standard,
        ),
      ),
      textTheme: base.textTheme.copyWith(
        // ALL text white/readable in dark mode
        headlineLarge: base.textTheme.headlineLarge?.copyWith(
          fontWeight: FontWeight.w700,
          color: AppColors.darkTextPrimary,
        ),
        headlineMedium: base.textTheme.headlineMedium?.copyWith(
          fontWeight: FontWeight.w700,
          color: AppColors.darkTextPrimary,
        ),
        headlineSmall: base.textTheme.headlineSmall?.copyWith(
          fontWeight: FontWeight.w700,
          color: AppColors.darkTextPrimary,
        ),
        titleLarge: base.textTheme.titleLarge?.copyWith(
          fontWeight: FontWeight.w700,
          color: AppColors.darkTextPrimary,
        ),
        titleMedium: base.textTheme.titleMedium?.copyWith(
          fontWeight: FontWeight.w600,
          color: AppColors.darkTextPrimary,
        ),
        titleSmall: base.textTheme.titleSmall?.copyWith(
          fontWeight: FontWeight.w600,
          color: AppColors.darkTextPrimary,
        ),
        bodyLarge: base.textTheme.bodyLarge?.copyWith(
          fontSize: 16,
          color: AppColors.darkTextPrimary,
        ),
        bodyMedium: base.textTheme.bodyMedium?.copyWith(
          fontSize: 16,
          color: AppColors.darkTextPrimary,
        ),
        bodySmall: base.textTheme.bodySmall?.copyWith(
          fontSize: 14,
          color: AppColors.darkTextSecondary,
        ),
        labelLarge: base.textTheme.labelLarge?.copyWith(
          fontWeight: FontWeight.w600,
          color: AppColors.darkTextPrimary,
        ),
        labelMedium: base.textTheme.labelMedium?.copyWith(
          fontWeight: FontWeight.w500,
          color: AppColors.darkTextPrimary,
        ),
      ),
      brightness: Brightness.dark,
    );
  }
}
