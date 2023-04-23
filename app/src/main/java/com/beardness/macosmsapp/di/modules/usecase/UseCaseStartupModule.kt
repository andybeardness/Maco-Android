package com.beardness.macosmsapp.di.modules.usecase

import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import com.beardness.macosmsapp.usecase.startup.StartUp
import com.beardness.macosmsapp.usecase.startup.StartUpProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseStartupModule {

    @Provides
    @Singleton
    fun provideStartup(
        smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
        internetFlow: InternetFlowProtocol,
        @IoCoroutineScope ioCoroutineScope: CoroutineScope,
    ): StartUpProtocol = StartUp(
        smsCacheProxyRepo = smsCacheProxyRepo,
        internetFlow = internetFlow,
        ioCoroutineScope = ioCoroutineScope,
    )
}