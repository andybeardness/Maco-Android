package com.beardness.macosmsapp.ui.theme.animation

import androidx.compose.animation.core.EaseOutQuad
import androidx.compose.animation.core.tween

object MacoAnimations {
    enum class DURATION(val millis: Int) {
        NORMAL(millis = 350),
        FASTER(millis = 250),
    }

    private val easing = EaseOutQuad

    fun <T> normal(
        delayMillis: Int = 0,
    ) = tween<T>(
        durationMillis = DURATION.NORMAL.millis,
        easing = easing,
        delayMillis = delayMillis,
    )

    fun <T> faster(
        delayMillis: Int = 0,
    ) = tween<T>(
        durationMillis = DURATION.FASTER.millis,
        easing = EaseOutQuad,
        delayMillis = delayMillis,
    )
}