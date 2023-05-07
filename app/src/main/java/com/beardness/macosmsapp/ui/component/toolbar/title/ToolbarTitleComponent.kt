package com.beardness.macosmsapp.ui.component.toolbar.title

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun ToolbarTitleComponent(
    modifier: Modifier,
    title: String,
    titleColor: Color,
) {
    Text(
        modifier = modifier,
        text = title,
        fontSize = 24.sp,
        color = titleColor,
    )
}