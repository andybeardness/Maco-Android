package com.beardness.macosmsapp.ui.component.spacer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun SpacerV(dp: Dp) {
    Spacer(modifier = Modifier.width(width = dp))

    MaterialTheme.colorScheme
}

@Composable
fun SpacerH(dp: Dp) {
    Spacer(modifier = Modifier.height(height = dp))
}