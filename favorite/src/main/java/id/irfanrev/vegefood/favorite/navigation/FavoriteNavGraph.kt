package id.irfanrev.vegefood.favorite.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.irfanrev.vegefood.favorite.FavoriteView
import id.irfanrev.vegefood.ui.feature.detail.MealsDetailView
import id.irfanrev.vegefood.ui.navigation.MEALS_ID
import id.irfanrev.vegefood.ui.navigation.Screen

@Composable
fun FavoriteNavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Favorite.route)
    {
        composable(Screen.Favorite.route) {
            FavoriteView(
                navController = navController
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(MEALS_ID) {
                    type = NavType.StringType
                }
            )
        ) {
            val mealsId = it.arguments?.getString(MEALS_ID)
            MealsDetailView(
                navController = navController,
                mealsId = mealsId ?: ""
            )
        }
    }
}
