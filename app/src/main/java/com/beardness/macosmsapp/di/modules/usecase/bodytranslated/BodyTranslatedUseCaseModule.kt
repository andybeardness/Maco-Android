package com.beardness.macosmsapp.di.modules.usecase.bodytranslated

import com.beardness.macosmsapp.di.qualifiers.QAutoTranslator
import com.beardness.macosmsapp.di.qualifiers.QGeTranslator
import com.beardness.macosmsapp.usecase.common.translator.BaseTranslatorProtocol
import com.beardness.macosmsapp.usecase.flow.body.BodyTranslateUseCase
import com.beardness.macosmsapp.usecase.flow.body.BodyTranslateUseCaseProtocol
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BodyTranslatedUseCaseModule {

    @Provides
    @Singleton
    fun provideBodyTranslateUseCase(
        @QAutoTranslator autoTranslator: BaseTranslatorProtocol,
        @QGeTranslator geTranslator: BaseTranslatorProtocol,
    ): BodyTranslateUseCaseProtocol =
        BodyTranslateUseCase(
            autoTranslator = autoTranslator,
            geTranslator = geTranslator,
        )
}