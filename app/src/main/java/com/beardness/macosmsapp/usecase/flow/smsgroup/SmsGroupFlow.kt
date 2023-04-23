package com.beardness.macosmsapp.usecase.flow.smsgroup

import com.beardness.macosmsapp.source.repo.smscache.SmsCacheProxyRepoProtocol
import com.beardness.macosmsapp.source.repo.smscache.dto.SmsCacheProxyRepoDto
import com.beardness.macosmsapp.usecase.common.helpers.avatarcolorgenerator.AvatarColorGeneratorProtocol
import com.beardness.macosmsapp.usecase.common.helpers.datetime.DateTimeFormatterProtocol
import com.beardness.macosmsapp.usecase.flow.smsgroup.dto.SmsGroupDto
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SmsGroupFlow @Inject constructor(
    private val smsCacheRepo: SmsCacheProxyRepoProtocol,
    private val dateTimeManager: DateTimeFormatterProtocol,
    private val avatarColorGenerator: AvatarColorGeneratorProtocol,
) : SmsGroupFlowProtocol {

    override val smsGroups =
        smsCacheRepo
            .flow
            .map { smsCollection ->
                smsCollection
                    .groupBy { smsRepoDto -> smsRepoDto.author }
                    .mapNotNull { entry -> entry.value.firstOrNull() }
                    .map { smsRepoDto -> smsRepoDto.repoDtoToGroupDto() }
            }

    private fun SmsCacheProxyRepoDto.repoDtoToGroupDto(): SmsGroupDto {
        val author = this.author
        val body = this.body

        val datetime =
            dateTimeManager
                .format(datetime = this.date)

        val avatarColor =
            avatarColorGenerator
                .generate(
                    author = this.author,
                )

        return SmsGroupDto(
            author = author,
            body = body,
            date = datetime.date,
            time = datetime.time,
            avatarColor = avatarColor,
        )
    }
}