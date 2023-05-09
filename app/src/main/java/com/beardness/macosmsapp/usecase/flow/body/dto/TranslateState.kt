package com.beardness.macosmsapp.usecase.flow.body.dto

sealed class TranslateState {
    object Initial : TranslateState()
    object NoTranslate : TranslateState()
    class Translate(val translation: BodyTranslatedDto) : TranslateState()
}