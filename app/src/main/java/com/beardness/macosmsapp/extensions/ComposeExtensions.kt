package com.beardness.macosmsapp.extensions

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.Ref

fun Modifier.clickableIfNotNull(onClick: (() -> Unit)?) =
    clickable(onClick = onClick ?: {}, enabled = onClick != null)

@Composable
fun <T> AnimatedNullVisibility(
    nullable: T?,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = shrinkOut() + fadeOut(),
    label: String = "AnimatedNullVisibility",
    content: @Composable AnimatedVisibilityScope.(item: T) -> Unit,
) {
    val reference = remember { Ref<T>() }

    reference.value = nullable ?: reference.value

    AnimatedVisibility(
        visible = nullable != null,
        modifier = modifier,
        enter = enter,
        exit = exit,
        label = label,
        content = { reference.value?.let { value -> content(value) } },
    )
}