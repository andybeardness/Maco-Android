package com.beardness.macosmsapp.usecase.flow.smstranslates

import android.util.Log
import com.beardness.macosmsapp.source.repo.sms.SmsRepoProtocol
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepo
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.usecase.common.types.LanguageCode
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.SmsTranslateDto
import com.beardness.macosmsapp.usecase.flow.smstranslates.dto.TranslatesDto
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SmsTranslatesFlow @Inject constructor(
    private val smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
    private val translateRepo: TranslateRepoProtocol,
) : SmsTranslatesFlowProtocol {

    override val flow = combine(
        smsCacheProxyRepo.flow,
        translateRepo.flow,
    ) { smsList, translatesList ->

        smsList.map { smsCacheProxyRepoDto ->
            val translatesForSms =
                translatesList.filter { translateRepoDto ->
                    translateRepoDto.smsId == smsCacheProxyRepoDto.smsId
                }

            val autoTranslate =
                translatesForSms.firstOrNull { translateRepoDto ->
                    translateRepoDto.languageCode == LanguageCode.AUTO.sign
                }?.translatedBody


            val georgianTranslate =
                translatesForSms.firstOrNull { translateRepoDto ->
                    translateRepoDto.languageCode == LanguageCode.GE.sign
                }?.translatedBody

            SmsTranslateDto(
                id = smsCacheProxyRepoDto.smsId,
                author = smsCacheProxyRepoDto.author,
                body = smsCacheProxyRepoDto.body,
                date = smsCacheProxyRepoDto.date,
                translates = TranslatesDto(
                    auto = autoTranslate,
                    georgian = georgianTranslate,
                )
            )
        }
    }
}