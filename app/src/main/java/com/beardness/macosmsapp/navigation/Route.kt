package com.beardness.macosmsapp.navigation

sealed class Route(val route: String) {
    object Home: Route(route = "home")
    object SmsGroups: Route(route = "sms-groups")
    class SmsByAuthor(author: String = "{author}"): Route(route = "sms-by-author/$author")
}