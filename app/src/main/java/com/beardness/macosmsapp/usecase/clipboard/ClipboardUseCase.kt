package com.beardness.macosmsapp.usecase.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

private const val LABEL = "copied"

class ClipboardUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
) : ClipboardUseCaseProtocol {

    private val clipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager

    override suspend fun copyToClipboard(text: String) {
        val clip = ClipData.newPlainText(LABEL, text)

        clipboardManager?.setPrimaryClip(clip)
    }
}