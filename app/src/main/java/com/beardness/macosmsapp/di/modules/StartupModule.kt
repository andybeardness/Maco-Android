package com.beardness.macosmsapp.di.modules

import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlow
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
object StartupModule {

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