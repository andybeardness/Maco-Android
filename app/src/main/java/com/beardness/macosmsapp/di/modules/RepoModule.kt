package com.beardness.macosmsapp.di.modules

import android.content.Context
import com.beardness.macosmsapp.db.smscache.SmsCacheDao
import com.beardness.macosmsapp.db.translate.TranslateDao
import com.beardness.macosmsapp.source.repo.sms.SmsRepo
import com.beardness.macosmsapp.source.repo.sms.SmsRepoProtocol
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepo
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.translate.TranslateRepo
import com.beardness.macosmsapp.source.repo.translate.TranslateRepoProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideSmsRepo(
        @ApplicationContext context: Context,
    ): SmsRepoProtocol =
        SmsRepo(
            context = context,
        )

    @Provides
    @Singleton
    fun provideSmsCacheProxyRepo(
        smsRepo: SmsRepoProtocol,
        smsCacheDao: SmsCacheDao,
    ): SmsCacheProxyRepoProtocol =
        SmsCacheProxyRepo(
            smsRepo = smsRepo,
            smsCacheDao = smsCacheDao,
        )

    @Provides
    @Singleton
    fun provideTranslateRepo(
        translateDao: TranslateDao,
    ): TranslateRepoProtocol =
        TranslateRepo(
            translateDao = translateDao,
        )
}