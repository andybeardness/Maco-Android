package com.beardness.macosmsapp.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val appTypography: Typography = Typography(
    headlineSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    headlineLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Light,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light,
    ),
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.ExtraLight,
    ),
    labelMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.ExtraLight,
    ),
    labelLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.ExtraLight,
    ),
)