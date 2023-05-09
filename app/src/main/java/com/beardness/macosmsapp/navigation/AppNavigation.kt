package com.beardness.macosmsapp.navigation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beardness.macosmsapp.navigation.sms.NavHostSms
import com.beardness.macosmsapp.navigation.sms.NavHostSmsController
import com.beardness.macosmsapp.navigation.sms.NavHostSmsControllerProtocol
import com.beardness.macosmsapp.screen.bodytranslate.BodyTranslateScreen
import com.beardness.macosmsapp.screen.bodytranslate.BodyTranslateViewModel
import com.beardness.macosmsapp.screen.common.PermissionScreen
import com.beardness.macosmsapp.screen.common.SmsPermissionScreen
import com.beardness.macosmsapp.screen.smsbyauthor.SmsByAuthorScreen
import com.beardness.macosmsapp.screen.smsbyauthor.SmsByAuthorScreenViewModel
import com.beardness.macosmsapp.screen.smsbygroup.SmsByGroup
import com.beardness.macosmsapp.screen.smsbygroup.SmsByGroupScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun AppNavigation() {
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }

    val navigateHome: () -> Unit = {
        navController.navigate(
            route = Route.Home.route,
        )
    }

    val navigateToBodyTranslate: () -> Unit = {
        navController.navigate(
            route = Route.Body.route,
        )
    }

    NavHost(
        navController = navController,
        startDestination = Route.Permissions.route,
    ) {
        composable(
            route = Route.Permissions.route,
        ) {
            PermissionScreen(
                permission = Manifest.permission.READ_SMS,
                granted = { },
                denied = { request ->
                    SmsPermissionScreen(
                        onClick = { request() },
                    )
                },
                whenGranted = navigateHome,
            )
        }

        composable(
            route = Route.Home.route,
        ) {
            val smsGroupViewModel = hiltViewModel<SmsByGroupScreenViewModel>()
            val smsByAuthorViewModel = hiltViewModel<SmsByAuthorScreenViewModel>()

            val navHostSmsController: NavHostSmsControllerProtocol =
                NavHostSmsController()

            val navigateToAuthorScreen: (author: String) -> Unit = { author ->
                scope.launch {
                    navHostSmsController.slide(state = true)
                }

                smsByAuthorViewModel.setup(author = author)
            }

            val navigateOutAuthorScreen: () -> Unit = {
                scope.launch {
                    navHostSmsController.slide(state = false)
                }
            }

            NavHostSms(
                controller = navHostSmsController,
                mainScreen = {
                    SmsByGroup(
                        viewModel = smsGroupViewModel,
                        navigateToBodyTranslate = navigateToBodyTranslate,
                        navigateToAuthorScreen = navigateToAuthorScreen,
                    )
                },
                slideScreen = {
                    SmsByAuthorScreen(
                        viewModel = smsByAuthorViewModel,
                        navigateBack = navigateOutAuthorScreen,
                    )
                },
            )
        }

        composable(route = Route.Body.route) {
            val viewModel = hiltViewModel<BodyTranslateViewModel>()
            val initial = viewModel.input()

            BodyTranslateScreen(
                viewModel = viewModel,
                initial = initial,
            )
        }
    }
}