package com.beardness.macosmsapp.usecase.usecase.haptic

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class HapticUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) : HapticUseCaseProtocol {

    private val vibrator = context.getSystemService(Vibrator::class.java)

    override suspend fun haptic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(
                VibrationEffect.createPredefined(
                    VibrationEffect.EFFECT_TICK
                )
            )
        }
    }
}