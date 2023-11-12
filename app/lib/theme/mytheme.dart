import 'package:flutter/material.dart';

class AppTheme {
  static const double fontSizeSmall = 14.0;
  static const double fontSizeMedium = 16.0;
  static const double fontSizeLarge = 18.0;

  static const String fontFamilyDefault = 'Dosis';

  static const Color primaryColor = Color(0xFFE33C64);
  static const Color secondaryColor = Color(0xFF3D1010);
  static const Color textColor = Colors.black;

  static const TextTheme textTheme = TextTheme(
    bodySmall: TextStyle(fontSize: fontSizeSmall),
    bodyMedium: TextStyle(fontSize: fontSizeMedium,color: secondaryColor),
    bodyLarge: TextStyle(fontSize: fontSizeLarge),
    titleMedium: TextStyle(fontSize: fontSizeMedium,color: textColor)
    // Add more text styles as needed
  );

  static ThemeData getTheme() {
    return ThemeData(
      primaryColor: primaryColor,
      textTheme: textTheme,
      fontFamily: fontFamilyDefault,
      // Add more theme properties as needed
    );
  }
}
