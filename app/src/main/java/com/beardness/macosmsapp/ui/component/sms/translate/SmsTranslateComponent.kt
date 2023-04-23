package com.beardness.macosmsapp.ui.component.sms.translate

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsTranslateViewDto
import com.beardness.macosmsapp.screen.smsbyauthor.dto.SmsViewDto
import com.beardness.macosmsapp.ui.theme.dimen.Dimen
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun SmsTranslateComponent(
    sms: SmsViewDto,
    onClickGe: () -> Unit,
    onClickAuto: () -> Unit,
    isButtonsAvailable: Boolean,
    isSmsProcessing: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimen.dp8, horizontal = Dimen.dp16)
    ) {
        IdDateTimeLine(
            id = sms.id,
            datetime = "${sms.date} ${sms.time}",
        )

        ContentBlock(
            body = sms.body,
            translates = sms.translates,
            onClickGe = onClickGe,
            onClickAuto = onClickAuto,
            isButtonsAvailable = isButtonsAvailable,
            isSmsProcessing = isSmsProcessing,
        )
    }
}

@Composable
private fun ContentBlock(
    body: String,
    translates: SmsTranslateViewDto,
    onClickGe: () -> Unit,
    onClickAuto: () -> Unit,
    isButtonsAvailable: Boolean,
    isSmsProcessing: Boolean,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(size = Dimen.dp16))
            .background(color = MaterialTheme.colorScheme.primaryContainer),
    ) {
        BodyLine(
            body = body,
            isSmsProcessing = isSmsProcessing,
        )

        TranslationsLine(
            translates = translates,
        )

        AnimatedVisibility(visible = isButtonsAvailable) {
            ButtonsLine(
                onClickGoogle = { onClickAuto() },
                onClickGe = { onClickGe() },
            )
        }
    }
}

@Composable
private fun BodyLine(
    body: String,
    isSmsProcessing: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.dp16, vertical = Dimen.dp8)
    ) {
        Text(
            modifier = Modifier.weight(weight = 1f),
            text = body,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Light
        )

        LoadingIcon(isSmsProcessing = isSmsProcessing)
    }
}

@Composable
private fun LoadingIcon(
    isSmsProcessing: Boolean,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val rotation by  infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )

    Box(
        modifier = Modifier.size(size = Dimen.dp16)
    ) {
        if (isSmsProcessing) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(degrees = rotation),
                imageVector = Icons.Rounded.Refresh,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
private fun IdDateTimeLine(id: Int, datetime: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimen.dp16,
                end = Dimen.dp16,
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
private fun ButtonsLine(
    onClickGoogle: () -> Unit,
    onClickGe: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Button(
            modifier = Modifier
                .weight(weight = 1f),
            title = "AUTO ${SpecificChars.EMOJI_EARTH}",
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary,
            onClick = { onClickGoogle() },
        )

        Button(
            modifier = Modifier
                .weight(weight = 1f),
            title = "GE ${SpecificChars.EMOJI_GEORGIA}",
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary,
            onClick = { onClickGe() },
        )
    }
}

@Composable
private fun Button(
    modifier: Modifier,
    title: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
            .background(color = backgroundColor)
            .padding(vertical = Dimen.dp16, horizontal = Dimen.dp8),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = textColor,
        )
    }
}

@Composable
private fun TranslationsLine(translates: SmsTranslateViewDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        var autoTranslateVisibility = false
        var autoTranslateText = ""
        var geTranslateVisibility = false
        var geTranslateText = ""

        translates.auto?.let { text ->
            autoTranslateVisibility = true
            autoTranslateText = text
        }

        translates.georgian?.let { text ->
            geTranslateVisibility = true
            geTranslateText = text
        }

        AnimatedVisibility(visible = autoTranslateVisibility) {
            Translation(title = "AUTO ${SpecificChars.EMOJI_EARTH}", text = autoTranslateText)
        }

        AnimatedVisibility(visible = geTranslateVisibility) {
            Translation(title = "GE ${SpecificChars.EMOJI_GEORGIA}", text = geTranslateText)
        }
    }
}

@Composable
private fun Translation(title: String, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimen.dp16, vertical = Dimen.dp8)
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Text(
            text = text,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    }
}