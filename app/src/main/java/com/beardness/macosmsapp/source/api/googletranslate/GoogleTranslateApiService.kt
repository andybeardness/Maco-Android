package com.beardness.macosmsapp.source.api.googletranslate

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface GoogleTranslateApiService {

    @POST("translate/v2")
    suspend fun translate(
        @Query("key") key: String,
        @Body request: RequestDto,
    ): ResponseDto
}