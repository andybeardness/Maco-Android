package com.beardness.macosmsapp.screen.smsbyauthor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beardness.macosmsapp.ui.widget.smstranslateslist.SmsTranslatesWidget
import com.beardness.macosmsapp.ui.widget.toolbar.smsbyauthor.ToolbarSmsByAuthorWidget
import com.beardness.macosmsapp.usecase.common.types.LanguageCode
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun SmsByAuthorScreen(
    viewModel: SmsByAuthorScreenViewModelProtocol
) {
    val author by viewModel.authorFlow.collectAsState()
    val authorAvatarColor by viewModel.authorAvatarColorFlow.collectAsState(initial = Color.Unspecified)
    val sms by viewModel.smsByAuthorFlow.collectAsState(initial = listOf())
    val internetStatus by viewModel.internetConnectionFlow.collectAsState(initial = InternetStatus.Lost)
    val smsProcessing by viewModel.smsProcessingCollectionFlow.collectAsState(initial = emptySet())

    val onClickGe: (smsId: Int) -> Unit =
        when (internetStatus) {
            InternetStatus.Available -> { smsId ->
                viewModel.translate(
                    smsId = smsId,
                    languageCode = LanguageCode.GE,
                )
            }

            InternetStatus.Lost -> { _ -> }
        }

    val onClickAuto: (smsId: Int) -> Unit =
        when (internetStatus) {
            InternetStatus.Available -> { smsId ->
                viewModel.translate(
                    smsId = smsId,
                    languageCode = LanguageCode.AUTO,
                )
            }

            InternetStatus.Lost -> { _ -> }
        }

    val isButtonsAvailable = internetStatus == InternetStatus.Available

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ToolbarSmsByAuthorWidget(
            avatarColor = authorAvatarColor,
            author = author,
        )

        SmsTranslatesWidget(
            smsCollection = sms,
            onClickGe = onClickGe,
            onClickAuto = onClickAuto,
            isButtonsAvailable = isButtonsAvailable,
            smsProcessing = smsProcessing,
        )
    }
}

