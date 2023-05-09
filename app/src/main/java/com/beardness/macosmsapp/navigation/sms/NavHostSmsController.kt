package com.beardness.macosmsapp.navigation.sms

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NavHostSmsController @Inject constructor(
) : NavHostSmsControllerProtocol {

    private val _slide = MutableStateFlow<Boolean>(value = false)
    override val slide = _slide.asStateFlow()

    override suspend fun slide(state: Boolean) {
        _slide.emit(value = state)
    }
}