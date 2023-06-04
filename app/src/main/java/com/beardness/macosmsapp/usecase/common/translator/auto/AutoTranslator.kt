package com.beardness.macosmsapp.usecase.common.translator.auto

import com.beardness.macosmsapp.BuildConfig
import com.beardness.macosmsapp.source.api.googletranslate.GoogleTranslateApiService
import com.beardness.macosmsapp.source.api.googletranslate.RequestDto
import com.beardness.macosmsapp.source.api.googletranslate.translatedBody
import com.beardness.macosmsapp.usecase.common.translator.BaseTranslatorProtocol
import javax.inject.Inject

class AutoTranslator @Inject constructor(
    private val googleTranslateApiService: GoogleTranslateApiService,
) : BaseTranslatorProtocol {

    override suspend fun translate(text: String): String? {
        val request = RequestDto.request(
            q = text,
            target = "ru",
        )

        val response = googleTranslateApiService.translate(
            key = BuildConfig.GOOGLE_TRANSLATE_API_KEY,
            request = request,
        )

        return response.translatedBody
    }
}