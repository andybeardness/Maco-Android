package com.beardness.macosmsapp.ui.compose.widget.input

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.beardness.macosmsapp.ui.compose.component.button.ButtonComponent
import com.beardness.macosmsapp.ui.compose.component.spacer.SpacerRow
import com.beardness.macosmsapp.ui.compose.component.textinput.LoadingInputComponent
import com.beardness.macosmsapp.ui.theme.animation.MacoAnimations
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun InputWidget(
    enable: Boolean,
    loading: Boolean,
    input: String,
    onInputChanged: (new: String) -> Unit,
    clearInput: () -> Unit,
    placeholder: String,
    onClickAction: () -> Unit,
) {
    val animationSpec = MacoAnimations.normal<Float>()

    AnimatedVisibility(
        visible = enable,
        enter = fadeIn(
            animationSpec = animationSpec,
        ),
        exit = fadeOut(
            animationSpec = animationSpec,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.dp4, horizontal = Dimens.dp16)
                .heightIn(min = Dimens.dp64, max = Dp.Infinity),
            verticalAlignment = Alignment.Top,
        ) {
            LoadingInputComponent(
                modifier = Modifier
                    .weight(weight = 1f),
                loading = loading,
                input = input,
                onInputChanged = onInputChanged,
                clearInput = clearInput,
                placeholder = placeholder,
                onClickIme = onClickAction,
            )

            SpacerRow(dp = Dimens.dp8)

            ButtonComponent(
                icon = Icons.Rounded.Translate,
                onClick = onClickAction,
            )
        }
    }
}