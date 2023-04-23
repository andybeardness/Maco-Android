package com.beardness.macosmsapp.usecase.flow.internet.type

sealed class InternetStatus {
    object Available: InternetStatus()
    object Lost: InternetStatus()
}