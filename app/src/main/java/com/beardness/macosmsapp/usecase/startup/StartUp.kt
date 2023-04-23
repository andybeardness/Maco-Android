package com.beardness.macosmsapp.usecase.startup

import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.usecase.flow.internet.InternetFlowProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartUp @Inject constructor(
    private val smsCacheProxyRepo: SmsCacheProxyRepoProtocol,
    private val internetFlow: InternetFlowProtocol,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope
) : StartUpProtocol {
    override fun onStartUp() {
        updateSmsCache()
        connectNetworkCallback()
    }

    private fun updateSmsCache() {
        ioCoroutineScope.launch {
            smsCacheProxyRepo.update()
        }
    }

    private fun connectNetworkCallback() {
        ioCoroutineScope.launch {
            internetFlow.connect()
        }
    }
}