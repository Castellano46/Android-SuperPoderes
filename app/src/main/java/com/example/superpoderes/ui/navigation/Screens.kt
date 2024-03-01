package com.example.superpoderes.ui.navigation

import com.example.superpoderes.ui.navigation.Screens.SuperheroDetailScreen.ARG_ID

sealed class Screens(val route: String) {
    object SuperheroesScreen : Screens(SCREEN2_BASE_ROUTE)
    object SuperheroDetailScreen : Screens(SCREEN3_ROUTE_TEMPLATE){
        const val ARG_ID = "heroID"
        fun createRouteWithArgs(id: String): String {
            return SCREEN3_ROUTE_TO_FORMAT.format(id)
        }
    }

    companion object {
        private const val SCREEN2_BASE_ROUTE = "SuperheroesScreen"
        private const val SCREEN3_BASE_ROUTE = "SuperheroDetailScreen"
        private const val SCREEN3_ROUTE_TEMPLATE = "$SCREEN3_BASE_ROUTE/{$ARG_ID}?"
        private const val SCREEN3_ROUTE_TO_FORMAT = "$SCREEN3_BASE_ROUTE/%s"
    }
}