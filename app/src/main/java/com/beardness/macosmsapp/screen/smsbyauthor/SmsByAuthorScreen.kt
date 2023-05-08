package com.beardness.macosmsapp.screen.smsbyauthor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.beardness.macosmsapp.extensions.ColorExtensions
import com.beardness.macosmsapp.ui.component.sms.AvatarComponent
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.theme.shape.CustomShapes
import com.beardness.macosmsapp.ui.widget.smstranslateslist.SmsTranslatesWidget
import com.beardness.macosmsapp.ui.widget.toolbar.TopAppBar
import com.beardness.macosmsapp.ui.widget.toolbar.TopAppBarIcon
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus

@Composable
fun SmsByAuthorScreen(
    viewModel: SmsByAuthorScreenViewModelProtocol,
    navigateBack: () -> Unit,
) {
    val author by viewModel.authorFlow.collectAsState()
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

    val onClickTranslatedText: (text: String) -> Unit = { text ->
        viewModel.copyToClipboard(text = text)
    }

    val avatarColor = ColorExtensions.avatarColor(token = author)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = author,
            navigation = {
                AvatarComponent(
                    modifier = Modifier
                        .size(size = Dimens.dp40)
                        .clip(shape = CustomShapes.circle)
                        .background(color = avatarColor),
                )
            },
            action = {
                 TopAppBarIcon(
                     imageVector = Icons.Rounded.Close,
                     tint = MaterialTheme.colorScheme.onBackground,
                     onClick = navigateBack
                 )
            },
        )

        SmsTranslatesWidget(
            smsCollection = sms,
            onClickAuto = onClickTranslate,
            smsProcessing = smsProcessing,
            onClickTranslatedText = onClickTranslatedText
        )
    }
}

