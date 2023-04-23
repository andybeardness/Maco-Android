package com.beardness.macosmsapp.source.api.googletranslate

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName(value = "data") val data: Data?,
)

data class Data(
    @SerializedName(value = "translations") val translations: List<Translations>
)

data class Translations(
    @SerializedName(value = "translatedText") val translatedText: String,
    @SerializedName(value = "detectedSourceLanguage") val detectedSourceLanguage: String,
)

val ResponseDto.translatedBody: String?
    get() = this.data?.translations?.getOrNull(index = 0)?.translatedText