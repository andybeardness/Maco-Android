package com.beardness.macosmsapp.di.modules.usecase

import android.content.Context
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatterProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlow
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlow
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smsprocessing.SmsProcessingFlow
import com.beardness.macosmsapp.usecase.flow.smsprocessing.SmsProcessingFlowProtocol
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlow
import com.beardness.macosmsapp.usecase.flow.smstranslates.SmsTranslatesFlowProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseFlowModule {

    @Provides
    @Singleton
    fun provideSmsGroupFlow(
        smsCacheRepo: SmsCacheProxyRepoProtocol,
        dateTimeManager: DateTimeFormatterProtocol,
    ): SmsGroupFlowProtocol =
        SmsGroupFlow(
            smsCacheRepo = smsCacheRepo,
            dateTimeManager = dateTimeManager,
        )

    @Provides
    @Singleton
    fun provideSmsTranslateFlow(
        smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
        translateRepo: TranslateRepoProtocol,
    ): SmsTranslatesFlowProtocol =
        SmsTranslatesFlow(
            smsCacheProxyRepo = smsCacheProxyRepo,
            translateRepo = translateRepo,
        )

    @Provides
    @Singleton
    fun provideInternetFlow(
        @ApplicationContext context: Context,
        @IoCoroutineScope ioCoroutineScope: CoroutineScope,
    ): InternetFlowProtocol =
        InternetFlow(
            context = context,
            ioCoroutineScope = ioCoroutineScope,
        )

    @Provides
    @Singleton
    fun provideSmsProcessingFlow(
    ): SmsProcessingFlowProtocol =
        SmsProcessingFlow()
}