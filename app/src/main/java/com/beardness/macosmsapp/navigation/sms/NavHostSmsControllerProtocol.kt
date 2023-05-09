package com.beardness.macosmsapp.navigation.sms

import kotlinx.coroutines.flow.StateFlow

interface NavHostSmsControllerProtocol {
    val slide: StateFlow<Boolean>
    suspend fun slide(state: Boolean)
}