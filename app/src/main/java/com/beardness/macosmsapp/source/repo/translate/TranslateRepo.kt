package com.beardness.macosmsapp.source.repo.translate

import com.beardness.macosmsapp.db.translate.TranslateDao
import com.beardness.macosmsapp.db.translate.dbEntity
import com.beardness.macosmsapp.source.repo.translate.dto.TranslateRepoDto
import com.beardness.macosmsapp.source.repo.translate.dto.repoDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TranslateRepo @Inject constructor(
    private val translateDao: TranslateDao,
) : TranslateRepoProtocol {

    override val flow: Flow<List<TranslateRepoDto>> =
        translateDao
            .flow()
            .map { list ->
                list.map { entity -> entity.repoDto() }
            }

    override suspend fun save(translate: TranslateRepoDto) {
        val translateDbEntity = translate.dbEntity()

        translateDao.create(entity = translateDbEntity)
    }

    override suspend fun read(smsId: Int): List<TranslateRepoDto> =
        translateDao.read(
            smsId = smsId
        ).map { dbEntity ->
            TranslateRepoDto(
                id = dbEntity.id.toInt(),
                smsId = dbEntity.smsId.toInt(),
                languageCode = dbEntity.langCode,
                translatedBody = dbEntity.translatedBody,
            )
        }
}