package com.example.superpoderes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.superpoderes.ui.superherodetail.SuperheroDetailScreen
import com.example.superpoderes.ui.superherodetail.SuperheroDetailViewModel
import com.example.superpoderes.ui.superherolist.SuperHeroListScreen
import com.example.superpoderes.ui.superherolist.SuperHeroListViewModel

@Composable
fun NavigationGraph(superHeroListViewModel: SuperHeroListViewModel, superheroDetailViewModel: SuperheroDetailViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SuperheroesScreen.route) {
        composable(Screens.SuperheroesScreen.route) {
            SuperHeroListScreen(superHeroListViewModel) { id ->
                navController.navigate(Screens.SuperheroDetailScreen.createRouteWithArgs(id))
            }
        }

        composable(
            Screens.SuperheroDetailScreen.route, arguments = listOf(
                navArgument(Screens.SuperheroDetailScreen.ARG_ID) {
                    type = NavType.StringType
                })
        ) {
            val id = it.arguments?.getString(Screens.SuperheroDetailScreen.ARG_ID) ?: ""
            SuperheroDetailScreen(id, superheroDetailViewModel){
                navController.navigateUp()
            }
        }
    }
}