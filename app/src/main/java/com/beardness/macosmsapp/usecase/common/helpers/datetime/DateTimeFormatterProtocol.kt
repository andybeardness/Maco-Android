package com.beardness.macosmsapp.usecase.common.helpers.datetime

import com.beardness.macosmsapp.usecase.common.helpers.datetime.dto.DateTimeFormatterDto

interface DateTimeFormatterProtocol {
    fun format(datetime: Long): DateTimeFormatterDto
}
