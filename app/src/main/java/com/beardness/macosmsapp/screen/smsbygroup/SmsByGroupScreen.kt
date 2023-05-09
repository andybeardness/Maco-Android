package com.beardness.macosmsapp.screen.smsbygroup

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Translate
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.beardness.macosmsapp.R
import com.beardness.macosmsapp.screen.common.PermissionScreen
import com.beardness.macosmsapp.screen.common.SmsPermissionScreen
import com.beardness.macosmsapp.ui.compose.fab.Fab
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import com.beardness.macosmsapp.ui.widget.smsbygrouplist.SmsByGroupListWidget
import com.beardness.macosmsapp.ui.widget.toolbar.TopAppBar

@Composable
fun SmsByGroup(
    viewModel: SmsByGroupScreenViewModelProtocol,
    navigateToBodyTranslate: () -> Unit,
    navigateToAuthorScreen: (author: String) -> Unit,
) {
    val mainToolbarText = stringResource(id = R.string.main_toolbar_text)
    val noInternetToolbarText = stringResource(id = R.string.main_toolbar_text_no_connection)

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

    PermissionScreen(
        permission = Manifest.permission.READ_SMS,
        granted = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TopAppBar(
                        navigationIcon = toolbarIcon,
                        onNavigationClick = null,
                        title = toolbarTitle,
                        actionIcon = Icons.Rounded.Refresh,
                        onActionClick = { viewModel.refreshSmsList() },
                    )

                    SmsByGroupListWidget(
                        sms = sms,
                        onClickAuthor = navigateToAuthorScreen
                    )
                }

                Fab(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(all = Dimens.dp32),
                    action = navigateToBodyTranslate,
                )

                FloatingActionButton(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(all = Dimens.dp32),
                    onClick = navigateToBodyTranslate,
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit ,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                }
            }
        },
        denied = { request ->
            SmsPermissionScreen(
                onClick = { request() },
            )
        },
        whenGranted = {
            viewModel.refreshSmsList()
        },
    )
}