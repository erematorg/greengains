import 'package:flutter/material.dart';

/// Centralized color system for the app
/// Following UI/UX design guide: minimal palette with neutrals, primary, and semantic colors
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
  static const Color lightBackground = Color(0xFFFFFFFF); // hsl(0, 0%, 100%)
  static const Color lightSurface = Color(0xFFFAFAFA); // hsl(0, 0%, 98%)
  static const Color lightSurfaceElevated = Color(0xFFF0F0F0); // hsl(0, 0%, 94%)
  static const Color lightSurfaceActive = Color(0xFFF1F8F4); // hsl(140, 30%, 96%) - green tint

  static const Color lightTextPrimary = Color(0xFF212121); // grey[900] - high contrast
  static const Color lightTextSecondary = Color(0xFF757575); // grey[600] - softer
  static const Color lightTextTertiary = Color(0xFF9E9E9E); // grey[500]
  static const Color lightBorder = Color(0xFFE0E0E0); // grey[300]
  static const Color lightDivider = Color(0xFFBDBDBD); // grey[400]

  // Dark mode neutrals (HSL-based)
  static const Color darkBackground = Color(0xFF000000); // hsl(0, 0%, 0%)
  static const Color darkSurface = Color(0xFF0D0D0D); // hsl(0, 0%, 5%)
  static const Color darkSurfaceElevated = Color(0xFF1A1A1A); // hsl(0, 0%, 10%)
  static const Color darkSurfaceActive = Color(0xFF0D2015); // hsl(140, 30%, 8%) - green tint

  static const Color darkTextPrimary = Color(0xFFE0E0E0); // grey[300] - high contrast for dark
  static const Color darkTextSecondary = Color(0xFF9E9E9E); // grey[500] - softer
  static const Color darkTextTertiary = Color(0xFF757575); // grey[600]
  static const Color darkBorder = Color(0xFF424242); // grey[800]
  static const Color darkDivider = Color(0xFF616161); // grey[700]

  // Shadow colors with opacity
  static Color shadowLight(double opacity) => Colors.white.withOpacity(opacity);
  static Color shadowDark(double opacity) => Colors.black.withOpacity(opacity);

  // Primary with alpha for backgrounds
  static Color primaryAlpha(double opacity) => primary.withOpacity(opacity);

  // Gradient colors
  static const List<Color> gradientGreen = [primaryLight, primary];
  static const List<Color> gradientRed = [error, errorDark];

  // Box shadows for depth (following UI/UX guide)
  static List<BoxShadow> elevationLight({
    required bool active,
  }) {
    return [
      // Inner light shadow (top)
      BoxShadow(
        color: shadowLight(0.8),
        offset: const Offset(0, -1),
        blurRadius: 1,
        spreadRadius: 0,
      ),
      // Outer dark shadow (bottom)
      BoxShadow(
        color: shadowDark(active ? 0.12 : 0.06),
        offset: const Offset(0, 2),
        blurRadius: active ? 10 : 6,
        spreadRadius: 0,
      ),
    ];
  }

  static List<BoxShadow> elevationDark({
    required bool active,
  }) {
    return [
      // Inner light shadow (top) - more subtle in dark mode
      BoxShadow(
        color: shadowLight(0.05),
        offset: const Offset(0, -1),
        blurRadius: 1,
        spreadRadius: 0,
      ),
      // Outer dark shadow (bottom)
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
      // Top light
      BoxShadow(
        color: isDark ? shadowLight(0.05) : shadowLight(0.2),
        offset: const Offset(0, -1),
        blurRadius: 0,
      ),
      // Bottom shadow
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
        color: color.withOpacity(opacity),
        blurRadius: 4,
        spreadRadius: 1,
      )
    ];
  }
}
