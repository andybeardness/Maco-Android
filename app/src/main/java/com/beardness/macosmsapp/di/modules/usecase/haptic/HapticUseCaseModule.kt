package com.beardness.macosmsapp.di.modules.usecase.haptic

import android.content.Context
import com.beardness.macosmsapp.usecase.usecase.haptic.HapticUseCase
import com.beardness.macosmsapp.usecase.usecase.haptic.HapticUseCaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HapticUseCaseModule {

    @Provides
    @Singleton
    fun provideHapticUseCaseModule(
        @ApplicationContext context: Context,
    ): HapticUseCaseProtocol =
        HapticUseCase(
            context = context,
        )
}