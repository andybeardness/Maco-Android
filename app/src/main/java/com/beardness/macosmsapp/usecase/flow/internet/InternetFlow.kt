package com.beardness.macosmsapp.usecase.flow.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.beardness.macosmsapp.di.qualifiers.IoCoroutineScope
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class InternetFlow @Inject constructor(
    private val context: Context,
    @IoCoroutineScope private val ioCoroutineScope: CoroutineScope,
) : InternetFlowProtocol {

    private val _flow = MutableStateFlow<InternetStatus>(value = InternetStatus.Lost)
    override val flow = _flow.asStateFlow()

    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            emit(status = InternetStatus.Available)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            emit(status = InternetStatus.Lost)
        }
    }

    override suspend fun connect() {
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java)
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    private fun emit(status: InternetStatus) {
        ioCoroutineScope.launch {
            _flow.emit(value = status)
        }
    }
}