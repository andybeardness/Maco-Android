package com.beardness.macosmsapp.usecase.usecase.translatesms

import com.beardness.macosmsapp.di.qualifiers.QAutoTranslator
import com.beardness.macosmsapp.di.qualifiers.QGeTranslator
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.dto.TranslateRepoDto
import com.beardness.macosmsapp.usecase.common.types.LanguageCode
import com.beardness.macosmsapp.usecase.usecase.translatesms.translator.BaseTranslatorProtocol
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TranslateSmsUseCase @Inject constructor(
    private val smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
    private val translateRepo: TranslateRepoProtocol,
    @QAutoTranslator private val autoTranslator: BaseTranslatorProtocol,
    @QGeTranslator private val geTranslator: BaseTranslatorProtocol,
) : TranslateSmsUseCaseProtocol {

    override suspend fun translate(smsId: Int, languageCode: LanguageCode) {
        val sms =
            smsCacheProxyRepo
                .flow
                .map { collection ->
                    collection
                        .firstOrNull { smsCacheProxyRepoDto -> smsCacheProxyRepoDto.smsId == smsId }
                }
                .firstOrNull()
                ?: return

        val existed = translateRepo.read(
            smsId = smsId,
        )

        existed.forEach { translateRepoDto ->
            if (translateRepoDto.languageCode == languageCode.sign) {
                return
            }
        }

        val translator = when (languageCode) {
            LanguageCode.GE -> geTranslator
            LanguageCode.AUTO -> autoTranslator
        }

        val translated = translator.translate(
            text = sms.body
        ) ?: return

        val translateRepoDto = TranslateRepoDto(
            id = 0,
            smsId = smsId,
            languageCode = languageCode.sign,
            translatedBody = translated,
        )

        translateRepo.save(
            translate = translateRepoDto
        )
    }
}