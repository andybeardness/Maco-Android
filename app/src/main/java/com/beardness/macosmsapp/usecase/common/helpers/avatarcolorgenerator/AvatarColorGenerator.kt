package com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator

import androidx.compose.ui.graphics.Color
import javax.inject.Inject
import kotlin.math.abs
import android.graphics.Color as AndroidColor

class AvatarColorGenerator @Inject constructor(
) : AvatarColorGeneratorProtocol {

    companion object {
        private const val SATURATION: Float = 1f
        private const val LIGHTNESS: Float = .55f
        private const val COLOR_VALUES_COUNT = 256
    }

    override fun generate(author: String): Color {
        val hue = author.generateHue().toFloat()
        val androidColor = AndroidColor.HSVToColor(floatArrayOf(hue, SATURATION, LIGHTNESS))
        return Color(androidColor)
    }

    private fun String.generateHue(): Int {
        return abs(this.hashCode()) % COLOR_VALUES_COUNT
    }
}