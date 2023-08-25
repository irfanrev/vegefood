package id.irfanrev.vegefood

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.irfanrev.vegefood.ui.component.BottomBar
import id.irfanrev.vegefood.ui.feature.detail.MealsDetailView
import id.irfanrev.vegefood.ui.feature.home.HomeView
import id.irfanrev.vegefood.ui.feature.home.SearchView
import id.irfanrev.vegefood.ui.feature.profile.ProfileView
import id.irfanrev.vegefood.ui.feature.splash.SplashView
import id.irfanrev.vegefood.ui.navigation.MEALS_ID
import id.irfanrev.vegefood.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            val excludedRoutes = listOf(
                Screen.Splash.route,
                Screen.Detail.route,
                Screen.Search.route,
            )
            if (currentRoute !in excludedRoutes) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Splash.route) {
                SplashView(
                    navController = navController,
                )
            }
            composable(Screen.Home.route) {
                HomeView(
                    navController = navController,
                )
            }
            composable(Screen.Search.route) {
                SearchView(
                    navController = navController,
                )
            }
//            composable(Screen.Favorite.route) {
//               FavoriteView(
//                   navController = navController,
//               )
//            }
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
            composable(Screen.Profile.route) {
                ProfileView()
            }
        }
    }
}
