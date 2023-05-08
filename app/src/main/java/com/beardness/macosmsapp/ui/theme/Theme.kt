package com.beardness.macosmsapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.beardness.macosmsapp.ui.theme.color.DarkThemeColors
import com.beardness.macosmsapp.ui.theme.color.LightThemeColors
import com.beardness.macosmsapp.ui.theme.shape.Shapes
import com.beardness.macosmsapp.ui.theme.typography.AppTypography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MacoSMSAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()

    val dynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val colorScheme = when {
        dynamicColor && darkTheme -> dynamicDarkColorScheme(context = LocalContext.current)
        dynamicColor && !darkTheme -> dynamicLightColorScheme(context = LocalContext.current)
        darkTheme -> DarkThemeColors
        else -> LightThemeColors
    }

    systemUiController.setSystemBarsColor(
        color = colorScheme.background,
    )

    val typography = AppTypography

    val shapes = Shapes

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        shapes = shapes,
        content = content
    )
}