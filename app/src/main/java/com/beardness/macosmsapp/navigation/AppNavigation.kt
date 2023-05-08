package com.beardness.macosmsapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beardness.macosmsapp.screen.bodytranslate.BodyTranslateScreen
import com.beardness.macosmsapp.screen.bodytranslate.BodyTranslateViewModel
import com.beardness.macosmsapp.screen.bodytranslate.BodyTranslateViewModelProtocol
import com.beardness.macosmsapp.screen.smsbyauthor.SmsByAuthorScreen
import com.beardness.macosmsapp.screen.smsbyauthor.SmsByAuthorScreenViewModel
import com.beardness.macosmsapp.screen.smsbygroup.SmsByGroup
import com.beardness.macosmsapp.screen.smsbygroup.SmsByGroupScreenViewModel

@ExperimentalAnimationApi
@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val navigateBack: () -> Unit = {
        navController.navigateUp()
    }

    val navigateToBodyTranslate: () -> Unit = {
        navController.navigate(
            route = Route.BodyTranslated.route,
        )
    }

    val navigateToSmsByAuthor: (author: String) -> Unit = { author ->
        navController.navigate(
            route = Route.SmsByAuthor(author = author).route,
        )
    }

    val argumentAuthor = navArgument(name = "author") { type = NavType.StringType }

    NavHost(
        navController = navController,
        startDestination = Route.SmsGroups.route,
    ) {

        composable(route = Route.SmsGroups.route) {
            val viewModel = hiltViewModel<SmsByGroupScreenViewModel>()

            SmsByGroup(
                viewModel = viewModel,
                navigateToBodyTranslate = navigateToBodyTranslate,
                navigateToSmsByAuthor = navigateToSmsByAuthor,
            )
        }

        composable(
            route = Route.SmsByAuthor().route,
            arguments = listOf(argumentAuthor),
        ) { backStackEntry ->
            val viewModel = hiltViewModel<SmsByAuthorScreenViewModel>()
            val author = backStackEntry.arguments?.getString(argumentAuthor.name) ?: ""
            viewModel.setup(author = author)

            SmsByAuthorScreen(
                viewModel = viewModel,
                navigateBack = navigateBack,
            )
        }

        composable(route = Route.BodyTranslated.route) {
            val viewModel = hiltViewModel<BodyTranslateViewModel>()
            val initialInput = viewModel.input()

            BodyTranslateScreen(
                viewModel = viewModel,
                initialInput = initialInput,
            )
        }
    }
}