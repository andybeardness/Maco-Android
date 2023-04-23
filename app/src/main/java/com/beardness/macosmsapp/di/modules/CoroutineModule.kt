package com.beardness.macosmsapp.di.modules

import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Singleton
    @IoCoroutineScope
    fun provideIoCoroutineScope(): CoroutineScope =
        CoroutineScope(context = SupervisorJob() + Dispatchers.IO)
}