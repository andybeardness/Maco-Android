package com.beardness.macosmsapp.ui.widget.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun TopAppBar(
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

@Composable
fun TopAppBar(
    navigationIcon: ImageVector,
    onNavigationClick: (() -> Unit)?,
    title: String,
    actionIcon: ImageVector?,
    onActionClick: (() -> Unit)?,
) {
    TopAppBar(
        title = title,
        navigation = {
            TopAppBarIcon(
                imageVector = navigationIcon,
                tint = MaterialTheme.colorScheme.onBackground,
                onClick = onNavigationClick,
            )
        },
        action = {
            when (val action = doesShowAction(icon = actionIcon, action = onActionClick)) {
                Action.Hide -> { /* Do nothing */
                }

                is Action.Show -> {
                    TopAppBarIcon(
                        imageVector = action.icon,
                        tint = MaterialTheme.colorScheme.onBackground,
                        onClick = action.action,
                    )
                }
            }
        },
    )
}

private sealed class Action {
    object Hide : Action()
    class Show(val icon: ImageVector, val action: () -> Unit) : Action()
}

private fun doesShowAction(icon: ImageVector?, action: (() -> Unit)?): Action =
    if (icon != null && action != null) Action.Show(icon = icon, action = action)
    else Action.Hide
