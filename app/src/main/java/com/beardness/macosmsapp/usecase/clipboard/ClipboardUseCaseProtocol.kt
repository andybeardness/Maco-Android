package com.beardness.macosmsapp.usecase.clipboard

interface ClipboardUseCaseProtocol {
    suspend fun copyToClipboard(text: String)
}
