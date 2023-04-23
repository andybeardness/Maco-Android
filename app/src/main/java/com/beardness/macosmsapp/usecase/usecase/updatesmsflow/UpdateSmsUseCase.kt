package com.beardness.macosmsapp.usecase.usecase.updatesmsflow

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import javax.inject.Inject

class UpdateSmsUseCase @Inject constructor(
    private val smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
): UpdateSmsUseCaseProtocol {
    override suspend fun update() {
        smsCacheProxyRepo.update()
    }
}