package com.beardness.macosmsapp.usecase.usecase.translatesms

import com.beardness.macosmsapp.usecase.common.types.LanguageCode

interface TranslateSmsUseCaseProtocol {
    suspend fun translate(smsId: Int, languageCode: LanguageCode)
}