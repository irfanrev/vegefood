package id.irfanrev.vegefood.ui.navigation

const val MEALS_ID = "meals_id"

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Home: Screen("home")
    object Detail: Screen("detail/{$MEALS_ID}") {
        fun createRoute(mealsId: String): String = "detail/$mealsId"
    }
    object Search: Screen("search")
    object Favorite: Screen("favorite")
    object Profile: Screen("profile")
}