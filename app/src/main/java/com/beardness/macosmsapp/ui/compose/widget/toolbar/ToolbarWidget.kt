package com.beardness.macosmsapp.ui.compose.widget.toolbar

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.beardness.macosmsapp.extensions.AnimatedNullVisibility
import com.beardness.macosmsapp.ui.theme.animation.MacoAnimations
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun ToolbarWidget(
    title: String,
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
                .height(height = Dimens.dp64)
                .padding(horizontal = Dimens.dp4),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(size = Dimens.dp48),
                contentAlignment = Alignment.Center,
            ) {
                navigation()
            }

            Text(
                modifier = Modifier
                    .weight(weight = 1f),
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
            )

            Box(
                modifier = Modifier
                    .size(size = Dimens.dp48),
                contentAlignment = Alignment.Center,
            ) {
                action?.let { current -> current() }
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = Dimens.dp1)
                .background(color = MaterialTheme.colorScheme.onBackground.copy(alpha = .3f)),
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ToolbarWidget(
    navigationIcon: ImageVector?,
    onNavigationClick: (() -> Unit)?,
    title: String,
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
        navigation = {
            navigationIcon?.let { icon ->
                TopAppBarIcon(
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
                    TopAppBarIcon(
                        imageVector = icon,
                        tint = MaterialTheme.colorScheme.onBackground,
                        onClick = onActionClick,
                    )
                },
            )
        },
    )
}
