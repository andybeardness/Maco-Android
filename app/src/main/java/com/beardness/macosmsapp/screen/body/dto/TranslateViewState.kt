package com.beardness.macosmsapp.screen.body.dto

import com.beardness.macosmsapp.usecase.flow.body.dto.TranslateState

sealed class TranslateViewState {
    object Initial : TranslateViewState()
    object NoTranslate : TranslateViewState()
    class Translate(val translation: BodyTranslatedViewDto) : TranslateViewState()
}

fun TranslateState.viewState(): TranslateViewState =
    when (this) {
        TranslateState.Initial -> TranslateViewState.Initial

        TranslateState.NoTranslate -> TranslateViewState.NoTranslate

        is TranslateState.Translate -> TranslateViewState.Translate(
            translation = BodyTranslatedViewDto(
                translatedAuto = this.translation.translatedAuto,
                translatedGe = this.translation.translatedGe,
            )
        )
    }
