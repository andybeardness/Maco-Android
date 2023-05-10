package com.beardness.macosmsapp.ui.compose.component.title

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun ToolbarTitleClickableComponent(
    modifier: Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Text(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .clickable(onClick = onClick)
            .padding(horizontal = MacoDimens.dp16, vertical = MacoDimens.dp8),
        text = title,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
    )
}