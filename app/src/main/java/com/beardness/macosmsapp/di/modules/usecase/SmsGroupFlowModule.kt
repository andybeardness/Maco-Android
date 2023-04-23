package com.beardness.macosmsapp.di.modules.usecase

import android.content.Context
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlow
import com.beardness.macosmsapp.usecase.flow.smsgroup.SmsGroupFlowProtocol
import com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator.AvatarColorGenerator
import com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator.AvatarColorGeneratorProtocol
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatter
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatterProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SmsGroupFlowModule {

    @Provides
    @Singleton
    fun provideSmsGroupFlow(
        smsCacheRepo: SmsCacheProxyRepoProtocol,
        dateTimeManager: DateTimeFormatterProtocol,
        avatarColorGenerator: AvatarColorGeneratorProtocol,
    ): SmsGroupFlowProtocol =
        SmsGroupFlow(
            smsCacheRepo = smsCacheRepo,
            dateTimeManager = dateTimeManager,
            avatarColorGenerator = avatarColorGenerator,
        )

    @Provides
    @Singleton
    fun provideDateTimeManager(
        @ApplicationContext context: Context,
    ): DateTimeFormatterProtocol =
        DateTimeFormatter(
            context = context,
        )

    @Provides
    @Singleton
    fun provideAvatarColorGenerator(
    ): AvatarColorGeneratorProtocol =
        AvatarColorGenerator()
}