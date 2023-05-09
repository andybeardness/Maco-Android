package com.beardness.macosmsapp.navigation.sms

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.beardness.macosmsapp.ui.theme.animation.MacoAnimations
import com.beardness.macosmsapp.ui.theme.dimen.Dimens
import kotlinx.coroutines.launch

@Composable
fun NavHostSms(
    controller: NavHostSmsControllerProtocol,
    mainScreen: @Composable () -> Unit,
    slideScreen: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()

    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp

    val slide by controller.slide.collectAsState()

    val y by animateDpAsState(
        targetValue = if (slide) Dimens.dp0 else screenHeight,
        animationSpec = MacoAnimations.normal()
    )

    BackHandler(
        enabled = slide,
        onBack = {
            scope.launch {
                controller.slide(state = false)
            }
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            mainScreen()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = y)
        ) {
            slideScreen()
        }
    }
}