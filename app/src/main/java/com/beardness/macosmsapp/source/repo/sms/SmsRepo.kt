package com.beardness.macosmsapp.source.repo.sms

import android.content.Context
import android.database.Cursor
import android.provider.Telephony
import com.beardness.macosmsapp.source.repo.sms.dto.SmsRepoDto
import javax.inject.Inject

class SmsRepo @Inject constructor(
    context: Context,
) : SmsRepoProtocol {

    private val contentResolver = context.contentResolver

    private val uri = Telephony.Sms.CONTENT_URI
    private val projection = arrayOf(
        Telephony.Sms._ID,
        Telephony.Sms.ADDRESS,
        Telephony.Sms.BODY,
        Telephony.Sms.DATE
    )
    private val selection: String? = null
    private val selectionArgs: Array<String>? = null
    private val sortOrder: String = Telephony.Sms.DATE + " DESC"

    override suspend fun smsFromDevice(): List<SmsRepoDto> =
        try {
            val cursor: Cursor? =
                contentResolver
                    .query(
                        uri,
                        projection,
                        selection,
                        selectionArgs,
                        sortOrder,
                    )

            val smsFromDevice = mutableListOf<SmsRepoDto>()

            cursor?.use { cr ->
                while (cr.moveToNext()) {
                    val smsId: Int = cr.getInt(cr.getColumnIndexOrThrow(Telephony.Sms._ID))
                    val address: String =
                        cr.getString(cr.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body: String = cr.getString(cr.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val date: Long = cr.getLong(cr.getColumnIndexOrThrow(Telephony.Sms.DATE))

                    val entity = SmsRepoDto(
                        id = smsId,
                        address = address,
                        body = body,
                        date = date,
                    )

                    smsFromDevice.add(element = entity)
                }
            }

            smsFromDevice
        } catch (t: Throwable) {
            emptyList()
        }

    override suspend fun smsFromDevice(smsId: Int): SmsRepoDto? {
        return smsFromDevice().firstOrNull { entry -> entry.id == smsId }
    }
}