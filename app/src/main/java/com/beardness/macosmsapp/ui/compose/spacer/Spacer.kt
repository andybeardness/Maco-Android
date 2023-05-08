package com.beardness.macosmsapp.ui.compose.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerRow(dp: Dp) {
    Spacer(modifier = Modifier.width(width = dp))
}

@Composable
fun SpacerColumn(dp: Dp) {
    Spacer(modifier = Modifier.height(height = dp))
}