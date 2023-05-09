package com.beardness.macosmsapp.screen.smsgroup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.TextFields
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.ui.compose.component.fab.Fab
import com.beardness.macosmsapp.ui.compose.widget.smsbygrouplist.SmsByGroupListWidget
import com.beardness.macosmsapp.ui.compose.widget.toolbar.ToolbarWidget
import com.beardness.macosmsapp.ui.theme.dimen.Dimens

@Composable
fun SmsGroupScreen(
    viewModel: SmsGroupScreenViewModelProtocol,
    navigateToBodyTranslate: () -> Unit,
    navigateToAuthorScreen: (author: String) -> Unit,
) {
    val mainToolbarText = stringResource(id = R.string.main_toolbar_text)
    val noInternetToolbarText = stringResource(id = R.string.no_connection)

    val sms by viewModel.sms.collectAsState(initial = emptyList())
    val internet by viewModel.internet.collectAsState(initial = true)

    val toolbarIcon =
        if (internet) {
            Icons.Rounded.Translate
        } else {
            Icons.Rounded.Close
        }

    val toolbarTitle =
        if (internet) {
            mainToolbarText
        } else {
            noInternetToolbarText
        }

    val haptic: () -> Unit = {
        viewModel.haptic()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ToolbarWidget(
                navigationIcon = toolbarIcon,
                onNavigationClick = null,
                title = toolbarTitle,
                actionIcon = Icons.Rounded.Refresh,
                onActionClick = {
                    haptic()
                    viewModel.refreshSmsList()
                },
            )

            SmsByGroupListWidget(
                sms = sms,
                onClickAuthor = { author ->
                    navigateToAuthorScreen(author)
                    haptic()
                },
            )
        }

        Fab(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(all = Dimens.dp32),
            action = {
                navigateToBodyTranslate()
                haptic()
            },
            icon = Icons.Rounded.TextFields,
        )
    }
}