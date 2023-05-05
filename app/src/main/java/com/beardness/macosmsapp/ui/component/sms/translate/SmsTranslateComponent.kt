package com.beardness.macosmsapp.ui.component.sms.translate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsTranslateViewDto
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun SmsTranslateComponent(
    sms: SmsViewDto,
    onClickTranslate: () -> Unit,
    isSmsProcessing: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Dimen.dp8,
                horizontal = Dimen.dp16,
            )
    ) {
        IdDateTimeLine(
            id = sms.id,
            datetime = "${sms.date} ${sms.time}",
        )

        Progress(
            visibility = isSmsProcessing,
        )

        ContentBlock(
            body = sms.body,
            translates = sms.translates,
            onClickTranslate = onClickTranslate,
        )
    }
}

@Composable
private fun IdDateTimeLine(id: Int, datetime: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = Dimen.dp8,
                bottom = Dimen.dp2,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(weight = 1f),
            text = "# $id",
            fontSize = 12.sp,
            fontWeight = FontWeight.Thin,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Text(
            text = datetime,
            fontSize = 14.sp,
            fontWeight = FontWeight.Thin,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Composable
private fun Progress(
    visibility: Boolean,
) {
    AnimatedVisibility(visible = visibility) {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun ContentBlock(
    body: String,
    translates: SmsTranslateViewDto,
    onClickTranslate: () -> Unit,
) {
    val translate = translates.auto ?: ""
    val isTranslateExist = translate.isNotEmpty()

    Column {
        Text(
            text = body,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Light,
        )

        Translation(
            visibility = isTranslateExist,
            text = translate
        )

        TranslateButton(
            visibility = !isTranslateExist,
            onClick = onClickTranslate
        )
    }
}

@Composable
private fun TranslateButton(
    visibility: Boolean,
    onClick: () -> Unit,
) {
    AnimatedVisibility(visible = visibility) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .background(color = MaterialTheme.colorScheme.background)
                .padding(vertical = Dimen.dp16, horizontal = Dimen.dp8),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Translate ${SpecificChars.EMOJI_EARTH}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun Translation(
    visibility: Boolean,
    text: String,
) {
    AnimatedVisibility(visible = visibility) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimen.dp8)
        ) {
            Text(
                text = "Translation ${SpecificChars.EMOJI_EARTH}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}