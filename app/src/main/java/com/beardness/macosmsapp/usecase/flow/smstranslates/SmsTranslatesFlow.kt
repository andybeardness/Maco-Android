package com.beardness.macosmsapp.usecase.flow.smstranslates

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.SmsTranslateDto
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.TranslatesDto
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SmsTranslatesFlow @Inject constructor(
    smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
    translateRepo: TranslateRepoProtocol,
) : SmsTranslatesFlowProtocol {

    override val flow = combine(
        smsCacheProxyRepo.flow,
        translateRepo.flow,
    ) { smsList, translatesList ->

        smsList.map { smsCacheProxyRepoDto ->
            val translateForSms =
                translatesList
                    .firstOrNull { translateRepoDto ->
                        translateRepoDto.smsId == smsCacheProxyRepoDto.smsId
                    }

            SmsTranslateDto(
                id = smsCacheProxyRepoDto.smsId,
                author = smsCacheProxyRepoDto.author,
                body = smsCacheProxyRepoDto.body,
                date = smsCacheProxyRepoDto.date,
                translates = TranslatesDto(
                    auto = translateForSms?.translatedAuto,
                    georgian = translateForSms?.translatedGe,
                )
            )
        }
    }
}