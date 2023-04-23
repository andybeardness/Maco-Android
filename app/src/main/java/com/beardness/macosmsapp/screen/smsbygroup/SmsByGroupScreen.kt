package com.beardness.macosmsapp.screen.smsbygroup

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.screen.common.PermissionScreen
import com.beardness.macosmsapp.screen.common.SmsPermissionScreen
import com.beardness.macosmsapp.ui.widget.smsbygrouplist.SmsByGroupListWidget
import com.beardness.macosmsapp.ui.widget.toolbar.smsbygroup.ToolbarSmsByGroupWidget
import com.beardness.macosmsapp.usecase.flow.internet.type.InternetStatus
import com.beardness.macosmsapp.utils.SpecificChars

@Composable
fun SmsByGroup(viewModel: SmsByGroupScreenViewModelProtocol) {

    val sms by viewModel.sms.collectAsState(initial = emptyList())

    val toolbarTitle by viewModel.toolbarTitle.collectAsState(initial = "")

    val navigateToAuthor: (author: String) -> Unit = { author ->
        viewModel.navigateToSmsByAuthorScreen(author)
    }

    PermissionScreen(
        permission = Manifest.permission.READ_SMS,
        granted = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ToolbarSmsByGroupWidget(
                    title = toolbarTitle,
                    onClickRefresh = { viewModel.refreshSmsList() }
                )

                SmsByGroupListWidget(
                    sms = sms,
                    onClickAuthor = navigateToAuthor
                )
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