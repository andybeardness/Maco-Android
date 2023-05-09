package com.beardness.macosmsapp.ui.compose.component.sms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsTranslateBlockComponent(
    translatedAuto: String?,
    translatedGe: String?,
    onClickTranslatedText: (text: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        translatedAuto?.let { translated ->
            val autoTitleText = stringResource(id = R.string.translation_auto)

            TranslateComponent(
                title = autoTitleText,
                text = translated,
                onClickText = { onClickTranslatedText(translated) }
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.dp12)
                .height(height = Dimens.dp1)
                .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = .1f)),
        )

        translatedGe?.let { translated ->
            val geTitleText = stringResource(id = R.string.translation_ge)

            TranslateComponent(
                title = geTitleText,
                text = translated,
                onClickText = { onClickTranslatedText(translated) }
            )
        }
    }
}