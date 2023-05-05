package com.beardness.macosmsapp.usecase.usecase.translatesms

interface TranslateSmsUseCaseProtocol {
    suspend fun translate(smsId: Int)
}