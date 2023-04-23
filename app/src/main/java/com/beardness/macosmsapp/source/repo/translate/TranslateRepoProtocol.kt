package com.beardness.macosmsapp.source.repo.translate

import com.beardness.macosmsapp.source.repo.translate.dto.TranslateRepoDto
import kotlinx.coroutines.flow.Flow

interface TranslateRepoProtocol {
    val flow: Flow<List<TranslateRepoDto>>
    suspend fun save(translate: TranslateRepoDto)
    suspend fun read(smsId: Int): List<TranslateRepoDto>
}
