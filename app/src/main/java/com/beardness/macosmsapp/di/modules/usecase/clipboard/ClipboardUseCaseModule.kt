package com.beardness.macosmsapp.di.modules.usecase.clipboard

import android.content.Context
import com.beardness.macosmsapp.usecase.clipboard.ClipboardUseCase
import com.beardness.macosmsapp.usecase.clipboard.ClipboardUseCaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ClipboardUseCaseModule {

    @Provides
    @Singleton
    fun provideClipboardUseCase(
        @ApplicationContext context: Context,
    ): ClipboardUseCaseProtocol =
        ClipboardUseCase(
            context = context,
        )
}