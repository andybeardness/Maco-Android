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
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus

@Composable
fun SmsByAuthorScreen(
    viewModel: SmsByAuthorScreenViewModelProtocol
) {
    val author by viewModel.authorFlow.collectAsState()
    val authorAvatarColor by viewModel.authorAvatarColorFlow.collectAsState(initial = Color.Unspecified)
    val sms by viewModel.smsByAuthorFlow.collectAsState(initial = listOf())
    val internetStatus by viewModel.internetConnectionFlow.collectAsState(initial = InternetStatus.Lost)
    val smsProcessing by viewModel.smsProcessingCollectionFlow.collectAsState(initial = emptySet())

    val onClickTranslate: (smsId: Int) -> Unit =
        when (internetStatus) {
            InternetStatus.Available -> { smsId ->
                viewModel.translate(
                    smsId = smsId,
                )
            }

            InternetStatus.Lost -> { _ -> }
        }

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
            onClickAuto = onClickTranslate,
            smsProcessing = smsProcessing,
        )
    }
}

