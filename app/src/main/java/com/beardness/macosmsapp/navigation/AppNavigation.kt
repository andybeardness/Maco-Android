package com.beardness.macosmsapp.navigation

import android.Manifest
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.beardness.macosmsapp.navigation.sms.NavHostSms
import com.beardness.macosmsapp.navigation.sms.NavHostSmsController
import com.beardness.macosmsapp.navigation.sms.NavHostSmsControllerProtocol
import com.beardness.macosmsapp.screen.body.BodyTranslateScreen
import com.beardness.macosmsapp.screen.body.BodyTranslateViewModel
import com.beardness.macosmsapp.screen.permission.Permission
import com.beardness.macosmsapp.screen.permission.sms.SmsPermissionScreen
import com.beardness.macosmsapp.screen.permission.sms.SmsPermissionScreenViewModel
import com.beardness.macosmsapp.screen.smsauthor.SmsByAuthorScreen
import com.beardness.macosmsapp.screen.smsauthor.SmsAuthorScreenViewModel
import com.beardness.macosmsapp.screen.smsgroup.SmsGroupScreen
import com.beardness.macosmsapp.screen.smsgroup.SmsGroupScreenViewModel
import com.beardness.macosmsapp.ui.theme.additional.MacoAnimations
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation() {
    val scope = rememberCoroutineScope()

    val navController = rememberAnimatedNavController()

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToBodyScreen: () -> Unit = {
        navController.navigate(
            route = Route.Body.route,
        )
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = Route.Home.route,
    ) {
        composable(
            route = Route.Home.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { offset -> - offset },
                    animationSpec = MacoAnimations.normal(),
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { offset -> - offset },
                    animationSpec = MacoAnimations.normal(),
                )
            },
        ) {
            val navHostSmsController: NavHostSmsControllerProtocol =
                NavHostSmsController()

            Permission(
                permission = Manifest.permission.READ_SMS,
                granted = {
                    val smsGroupViewModel = hiltViewModel<SmsGroupScreenViewModel>()
                    val smsByAuthorViewModel = hiltViewModel<SmsAuthorScreenViewModel>()

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
                            SmsGroupScreen(
                                viewModel = smsGroupViewModel,
                                navigateToBodyTranslate = navigateToBodyScreen,
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
                },
                denied = { request ->
                    val smsPermissionScreenViewModel = hiltViewModel<SmsPermissionScreenViewModel>()

                    SmsPermissionScreen(
                        viewModel = smsPermissionScreenViewModel,
                        permissionDialog = request,
                    )
                }
            )

        }

        composable(
            route = Route.Body.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { offset -> offset },
                    animationSpec = MacoAnimations.normal(),
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { offset -> offset },
                    animationSpec = MacoAnimations.normal(),
                )
            },
        ) {
            val viewModel = hiltViewModel<BodyTranslateViewModel>()
            val initial = viewModel.input()

            BodyTranslateScreen(
                viewModel = viewModel,
                initial = initial,
                navigateBack = navigateBack,
            )
        }
    }
}