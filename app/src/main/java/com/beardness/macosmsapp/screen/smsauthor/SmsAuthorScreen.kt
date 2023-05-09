package com.beardness.macosmsapp.screen.smsauthor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.extensions.ColorExtensions
import com.beardness.macosmsapp.ui.compose.component.avatar.AvatarComponent
import com.beardness.macosmsapp.ui.compose.widget.smstranslateslist.SmsTranslatesListWidget
import com.beardness.macosmsapp.ui.compose.widget.toolbar.ToolbarWidget
import com.beardness.macosmsapp.ui.compose.widget.toolbar.TopAppBarIcon
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus

@Composable
fun SmsByAuthorScreen(
    viewModel: SmsAuthorScreenViewModelProtocol,
    navigateBack: () -> Unit,
) {
    val haptic: () -> Unit = {
        viewModel.haptic()
    }

    val author by viewModel.authorFlow.collectAsState()
    val sms by viewModel.smsByAuthorFlow.collectAsState(initial = listOf())
    val internetStatus by viewModel.internetConnectionFlow.collectAsState(initial = InternetStatus.Lost)
    val smsProcessing by viewModel.smsProcessingCollectionFlow.collectAsState(initial = emptySet())

    val onClickTranslate: (smsId: Int) -> Unit =
        when (internetStatus) {
            InternetStatus.Available -> { smsId ->
                viewModel.translate(smsId = smsId)
            }

            InternetStatus.Lost -> { _ -> }
        }

    val onClickTranslatedText: (text: String) -> Unit = { text ->
        viewModel.copyToClipboard(text = text)
    }

    val avatarColor = ColorExtensions.avatarColor(token = author)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        ToolbarWidget(
            title = author,
            navigation = {
                AvatarComponent(color = avatarColor)
            },
            action = {
                TopAppBarIcon(
                    imageVector = Icons.Rounded.Close,
                    tint = MaterialTheme.colorScheme.onBackground,
                    onClick = {
                        navigateBack()
                        haptic()
                    }
                )
            },
        )

        SmsTranslatesListWidget(
            smsCollection = sms,
            onClickTranslate = { smsId ->
                onClickTranslate(smsId)
                haptic()
            },
            smsProcessing = smsProcessing,
            onClickTranslatedText = { text ->
                onClickTranslatedText(text)
                haptic()
            },
        )
    }
}

