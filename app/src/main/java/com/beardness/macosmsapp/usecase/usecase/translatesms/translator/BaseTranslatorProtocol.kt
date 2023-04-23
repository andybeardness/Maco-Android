package com.beardness.macosmsapp.usecase.usecase.translatesms.translator

interface BaseTranslatorProtocol {
    suspend fun translate(text: String): String?
}