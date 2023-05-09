package com.beardness.macosmsapp.screen.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.ui.compose.widget.input.InputWidget
import com.beardness.macosmsapp.ui.compose.widget.toolbar.ToolbarWidget
import com.beardness.macosmsapp.ui.compose.widget.translate.TranslateWidget

@Composable
fun BodyTranslateScreen(
    viewModel: BodyTranslateViewModelProtocol,
    initial: String,
    navigateBack: () -> Unit,
) {
    val haptic: () -> Unit = {
        viewModel.haptic()
    }

    val toolbarText = stringResource(id = R.string.body_toolbar_text)
    val inputHereText = stringResource(id = R.string.input_text)
    val noInternetText = stringResource(id = R.string.no_connection)

    val focusManager = LocalFocusManager.current

    var input by remember { mutableStateOf(initial) }

    val onInputChanged: (new: String) -> Unit = { new ->
        input = new
        viewModel.input(updated = new)
    }

    val clearInput: () -> Unit = {
        onInputChanged("")
        haptic()
    }

    val translated by viewModel.translated.collectAsState(initial = null)
    val progress by viewModel.inProgress.collectAsState(initial = false)
    val internet by viewModel.internet.collectAsState(initial = false)

    val toolbarTitle = if (internet) toolbarText else noInternetText

    val backspaceIcon = if (translated != null) Icons.Rounded.Delete else null

    val backspaceAction: (() -> Unit)? =
        if (translated != null) {
            { viewModel.clearTranslate() }
        } else {
            null
        }

    val translate: (text: String) -> Unit = { text ->
        viewModel.translate(text = text)
        focusManager.clearFocus()
    }

    val copyTranslated: (text: String) -> Unit = { text ->
        viewModel.copyToClipboard(text = text)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        ToolbarWidget(
            navigationIcon = Icons.Rounded.ArrowBackIos,
            onNavigationClick = {
                navigateBack()
                haptic()
            },
            title = toolbarTitle,
            actionIcon = backspaceIcon,
            onActionClick = {
                backspaceAction?.invoke()
                haptic()
            },
        )

        TranslateWidget(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f)
                .imePadding(),
            translated = translated,
            onClickText = { text ->
                copyTranslated(text)
                haptic()
            },
        )

        InputWidget(
            enable = internet,
            loading = progress,
            input = input,
            onInputChanged = onInputChanged,
            clearInput = clearInput,
            placeholder = inputHereText,
            onClickAction = {
                translate(input)
                haptic()
            },
        )
    }
}