package com.beardness.macosmsapp.extensions

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.clickableIfNotNull(onClick: (() -> Unit)?) =
    clickable(onClick = onClick ?: {}, enabled = onClick != null)