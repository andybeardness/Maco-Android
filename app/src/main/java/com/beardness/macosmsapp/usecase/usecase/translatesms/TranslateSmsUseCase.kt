package com.beardness.macosmsapp.usecase.usecase.translatesms

import com.beardness.macosmsapp.di.qualifiers.QAutoTranslator
import com.beardness.macosmsapp.di.qualifiers.QGeTranslator
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.dto.TranslateRepoDto
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

    override suspend fun translate(smsId: Int) {
        val sms =
            smsCacheProxyRepo
                .flow
                .map { collection ->
                    collection
                        .firstOrNull { smsCacheProxyRepoDto ->
                            smsCacheProxyRepoDto.smsId == smsId
                        }
                }
                .firstOrNull()
                ?: return

        val existed = translateRepo.read(
            smsId = smsId,
        )

        existed.forEach { translateRepoDto ->
            if (translateRepoDto.smsId == smsId) {
                return
            }
        }

        val translatedAuto = autoTranslator.translate(text = sms.body) ?: ""
        val translatedGe = geTranslator.translate(text = sms.body) ?: "" // TODO

        val translateRepoDto = TranslateRepoDto(
            id = 0,
            smsId = smsId,
            translatedAuto = translatedAuto,
            translatedGe = translatedGe,
        )

        translateRepo.save(
            translate = translateRepoDto
        )
    }
}