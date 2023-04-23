package com.beardness.macosmsapp.usecase.common.helpers.datetime

import android.content.Context
import android.icu.text.SimpleDateFormat
import com.beardness.macosmsapp.usecase.common.helpers.datetime.dto.DateTimeFormatterDto
import java.util.*
import javax.inject.Inject

class DateTimeFormatter @Inject constructor(
    private val context: Context,
) : DateTimeFormatterProtocol {

    companion object {
        private const val PATTERN = "yyyy.MM.dd HH:mm"
        private const val INDEX_OF_DATE = 0
        private const val INDEX_OF_TIME = 1
    }

    private val locale = context.resources.configuration.locales[0] ?: Locale.getDefault()

    private val format = SimpleDateFormat(PATTERN, locale)

    override fun format(datetime: Long): DateTimeFormatterDto {
        val formatted = format.format(Date(datetime))
        val split = formatted.split(' ')

        val date = split.getOrNull(index = INDEX_OF_DATE) ?: ""
        val time = split.getOrNull(index = INDEX_OF_TIME) ?: ""

        return DateTimeFormatterDto(
            date = date,
            time = time,
        )
    }
}