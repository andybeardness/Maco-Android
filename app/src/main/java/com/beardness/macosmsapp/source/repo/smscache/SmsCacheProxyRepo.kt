package com.beardness.macosmsapp.source.repo.smscache

import android.util.Log
import com.beardness.macosmsapp.db.smscache.SmsCacheDao
import com.beardness.macosmsapp.db.smscache.dbEntity
import com.beardness.macosmsapp.source.repo.sms.SmsRepoProtocol
import com.beardness.macosmsapp.source.repo.smscache.dto.SmsCacheProxyRepoDto
import com.beardness.macosmsapp.source.repo.smscache.dto.proxyRepoDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SmsCacheProxyRepo @Inject constructor(
    private val smsRepo: SmsRepoProtocol,
    private val smsCacheDao: SmsCacheDao,
) : SmsCacheProxyRepoProtocol {

    private val _flow = MutableStateFlow<List<SmsCacheProxyRepoDto>>(value = emptyList())
    override val flow = _flow.asStateFlow()

    override suspend fun update() {
        emitFromDatabase()
        readFromDeviceAndSaveInDatabase()
        emitFromDatabase()
    }

    private suspend fun emitFromDatabase() {
        val smsCacheFromDb =
            smsCacheDao
                .all()
                .map { smsCacheDbEntity -> smsCacheDbEntity.proxyRepoDto() }

        Log.d("BUGBUGBUG", "smsCacheFromDb = $smsCacheFromDb")

        _flow.emit(value = smsCacheFromDb)
    }

    private suspend fun readFromDeviceAndSaveInDatabase() {
        val smsFromDevice =
            smsRepo
                .smsFromDevice()
                .map { smsRepoDto -> smsRepoDto.dbEntity() }

        Log.d("BUGBUGBUG", "smsFromDevice = $smsFromDevice")

        smsCacheDao.clearAll()
        smsCacheDao.addAll(elements = smsFromDevice)
    }
}