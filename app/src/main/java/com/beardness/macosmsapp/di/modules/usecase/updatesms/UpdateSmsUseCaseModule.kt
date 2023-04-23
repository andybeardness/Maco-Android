package com.beardness.macosmsapp.di.modules.usecase.updatesms

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsUseCase
import com.beardness.macosmsapp.usecase.usecase.updatesmsflow.UpdateSmsUseCaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UpdateSmsUseCaseModule {

    @Provides
    @Singleton
    fun provideUpdateSmsUseCase(
        smsCacheProxyRepo: SmsCacheProxyRepoProtocol
    ): UpdateSmsUseCaseProtocol =
        UpdateSmsUseCase(
            smsCacheProxyRepo = smsCacheProxyRepo,
        )
}