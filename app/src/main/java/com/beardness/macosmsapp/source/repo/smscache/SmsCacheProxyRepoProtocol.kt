package com.beardness.macosmsapp.source.repo.smscache

import com.beardness.macosmsapp.source.repo.smscache.dto.SmsCacheProxyRepoDto
import kotlinx.coroutines.flow.Flow

interface SmsCacheProxyRepoProtocol {
    val flow: Flow<List<SmsCacheProxyRepoDto>>

    suspend fun update()
}
