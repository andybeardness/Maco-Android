package com.beardness.macosmsapp.di.modules.usecase

import android.content.Context
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
object UseCaseCommonModule {

    @Provides
    @Singleton
    fun provideDateTimeManager( // TODO ????
        @ApplicationContext context: Context,
    ): DateTimeFormatterProtocol =
        DateTimeFormatter(
            context = context,
        )

}