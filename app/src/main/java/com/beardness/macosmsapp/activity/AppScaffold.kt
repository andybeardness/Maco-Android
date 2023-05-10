package com.beardness.macosmsapp.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.beardness.macosmsapp.ui.theme.additional.MacoDimens

@Composable
fun AppScaffold(
    content: @Composable () -> Unit,
    bottom: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        content = { paddings ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues = paddings)
            ) {
                content()
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = MacoDimens.dp56),
                contentAlignment = Alignment.Center,
            ) {
                bottom()
            }
        },
    )
}