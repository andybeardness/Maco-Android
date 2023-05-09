package com.beardness.macosmsapp.ui.compose.widget.toolbar

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.beardness.macosmsapp.extensions.AnimatedNullVisibility
import com.beardness.macosmsapp.ui.compose.component.icon.ToolbarIconComponent
import com.beardness.macosmsapp.ui.compose.component.title.ToolbarTitleClickableComponent
import com.beardness.macosmsapp.ui.compose.component.title.ToolbarTitleComponent
import com.beardness.macosmsapp.ui.theme.additional.MacoAnimations
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun ToolbarWidget(
    title: String,
    onTitleClick: (() -> Unit)?,
    navigation: @Composable BoxScope.() -> Unit,
    action: @Composable (BoxScope.() -> Unit)?,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = MacoDimens.dp64)
                .padding(horizontal = MacoDimens.dp4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(size = MacoDimens.dp48),
                contentAlignment = Alignment.Center,
            ) {
                navigation()
            }

            if (onTitleClick == null) {
                ToolbarTitleComponent(
                    modifier = Modifier
                        .weight(weight = 1f),
                    title = title,
                )
            } else {
                Spacer(modifier = Modifier.weight(weight = 1f))

                ToolbarTitleClickableComponent(
                    modifier = Modifier,
                    title = title,
                    onClick = onTitleClick
                )

                Spacer(modifier = Modifier.weight(weight = 1f))
            }

            Box(
                modifier = Modifier
                    .size(size = MacoDimens.dp48),
                contentAlignment = Alignment.Center,
            ) {
                action?.let { current -> current() }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = MacoDimens.dp1)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .2f)),
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToolbarWidget(
    navigationIcon: ImageVector?,
    onNavigationClick: (() -> Unit)?,
    title: String,
    onTitleClick: (() -> Unit)?,
    actionIcon: ImageVector?,
    onActionClick: (() -> Unit)?,
) {
    val animationSpec =
        MacoAnimations
            .faster<Float>(
                delayMillis = MacoAnimations
                    .DURATION
                    .FASTER
                    .millis
            )

    ToolbarWidget(
        title = title,
        onTitleClick = onTitleClick,
        navigation = {
            navigationIcon?.let { icon ->
                ToolbarIconComponent(
                    imageVector = icon,
                    tint = MaterialTheme.colorScheme.onBackground,
                    onClick = onNavigationClick,
                )
            }
        },
        action = {
            AnimatedNullVisibility(
                nullable = actionIcon,
                enter = scaleIn(animationSpec = animationSpec),
                exit = scaleOut(animationSpec = animationSpec),
                content = { icon ->
                    ToolbarIconComponent(
                        imageVector = icon,
                        tint = MaterialTheme.colorScheme.onBackground,
                        onClick = onActionClick,
                    )
                },
            )
        },
    )
}
