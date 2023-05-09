package com.beardness.macosmsapp.screen.permission.sms

import androidx.lifecycle.ViewModel
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.usecase.usecase.haptic.HapticUseCaseProtocol
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SmsPermissionScreenViewModel @Inject constructor(
    private val hapticUseCase: HapticUseCaseProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : ViewModel(), SmsPermissionScreenViewModelProtocol {

    override fun haptic() {
        ioCoroutineScope.launch {
            hapticUseCase.haptic()
        }
    }
}