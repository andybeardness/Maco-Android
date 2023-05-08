package com.beardness.macosmsapp.extensions

import android.graphics.Color.HSVToColor
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlin.math.abs

object ColorExtensions {

    private const val SATURATION: Float = 1f
    private const val LIGHTNESS_LIGHT: Float = .8f
    private const val LIGHTNESS_DARK: Float = .6f
    private const val COLOR_VALUES_COUNT = 256

    @Composable
    fun avatarColor(token: String): Color {
        val hue = (abs(token.hashCode()) % COLOR_VALUES_COUNT).toFloat()
        val lightness = if (isSystemInDarkTheme()) LIGHTNESS_DARK else LIGHTNESS_LIGHT
        val androidColor = HSVToColor(floatArrayOf(hue, SATURATION, lightness))

        return Color(androidColor)
    }
}