package com.beardness.macosmsapp.usecase.common.translator

interface BaseTranslatorProtocol {
    suspend fun translate(text: String): String?
}