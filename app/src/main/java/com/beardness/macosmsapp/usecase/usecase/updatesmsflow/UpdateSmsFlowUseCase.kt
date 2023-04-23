package com.beardness.macosmsapp.usecase.usecase.updatesmsflow

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import javax.inject.Inject

class UpdateSmsFlowUseCase @Inject constructor(
    private val smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
): UpdateSmsFlowUseCaseProtocol {
    override suspend fun update() {
        smsCacheProxyRepo.update()
    }
}