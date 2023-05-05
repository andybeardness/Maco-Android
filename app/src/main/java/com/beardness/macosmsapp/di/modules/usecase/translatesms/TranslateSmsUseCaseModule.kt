package com.beardness.macosmsapp.di.modules.usecase.translatesms

import com.beardness.macosmsapp.di.qualifiers.QAutoTranslator
import com.beardness.macosmsapp.di.qualifiers.QGeTranslator
import com.beardness.macosmsapp.source.api.googletranslate.GoogleTranslateApiService
import com.beardness.macosmsapp.source.repo.sms.SmsRepoProtocol
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepo
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.usecase.usecase.translatesms.TranslateSmsUseCase
import com.beardness.macosmsapp.usecase.usecase.translatesms.TranslateSmsUseCaseProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlow
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlowProtocol
import com.beardness.macosmsapp.usecase.common.translator.BaseTranslatorProtocol
import com.beardness.macosmsapp.usecase.common.translator.auto.AutoTranslator
import com.beardness.macosmsapp.usecase.common.translator.ge.GeTranslator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslateSmsUseCaseModule {

    @Provides
    @Singleton
    fun provideTranslateSmsUseCase(
        smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
        translateRepo: TranslateRepoProtocol,
        @QAutoTranslator autoTranslator: BaseTranslatorProtocol,
        @QGeTranslator geTranslator: BaseTranslatorProtocol,
    ): TranslateSmsUseCaseProtocol =
        TranslateSmsUseCase(
            smsCacheProxyRepo = smsCacheProxyRepo,
            translateRepo = translateRepo,
            autoTranslator = autoTranslator,
            geTranslator = geTranslator,
        )

    @Provides
    @Singleton
    @QAutoTranslator
    fun provideAutoTranslator(
        googleTranslateApiService: GoogleTranslateApiService,
    ): BaseTranslatorProtocol =
        AutoTranslator(
            googleTranslateApiService = googleTranslateApiService,
        )

    @Provides
    @Singleton
    @QGeTranslator
    fun provideGeTranslator(
        @QAutoTranslator autoTranslator: BaseTranslatorProtocol,
    ): BaseTranslatorProtocol =
        GeTranslator(autoTranslator = autoTranslator)
}