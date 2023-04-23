package com.beardness.macosmsapp.di.modules.usecase.updatesmsflow

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsFlowUseCase
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsFlowUseCaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpdateSmsFlowUseCaseModule {

    @Provides
    @Singleton
    fun provideUpdateSmsFlowUseCase(
        smsCacheProxyRepo: SmsCacheProxyRepoProtocol
    ): UpdateSmsFlowUseCaseProtocol =
        UpdateSmsFlowUseCase(
            smsCacheProxyRepo = smsCacheProxyRepo,
        )
}