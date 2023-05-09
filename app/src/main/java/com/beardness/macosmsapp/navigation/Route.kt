package com.beardness.macosmsapp.navigation

sealed class Route(val route: String) {
    object Permissions : Route(route = "permissions")
    object Home : Route(route = "home")
    object Body : Route(route = "body")
}