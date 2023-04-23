package com.beardness.macosmsapp.source.api.googletranslate

import com.google.gson.annotations.SerializedName

data class RequestDto(
    @SerializedName(value = "q") val q: String,
    @SerializedName(value = "target") val target: String,
    @SerializedName(value = "format") val format: String,
) {
    companion object {
        private const val FORMAT = "text"

        fun request(
            q: String,
            target: String
        ): RequestDto =
            RequestDto(
                q = q,
                target = target,
                format = FORMAT,
            )
    }
}