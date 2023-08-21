package id.irfanrev.vegefood.ui.feature.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.irfanrev.vegefood.ui.component.MealsItemCard
import id.irfanrev.vegefood.ui.feature.favorite.model.FavoriteUiState
import id.irfanrev.vegefood.ui.feature.home.HomeViewModel
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState
import id.irfanrev.vegefood.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteView(
    favoriteViewModel: FavoriteViewModel = koinViewModel(),
    navController: NavController,
) {
    HomeViewContent(
        favoriteViewModel = favoriteViewModel,
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewContent(
    favoriteViewModel: FavoriteViewModel,
    navController: NavController,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Favorite", style = MaterialTheme.typography.headlineSmall)
            }
        }
    ) {
        val response = favoriteViewModel.response.value

        when (response) {
            is FavoriteUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is FavoriteUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .padding(it)
                ) {
                    items(response.data) { meals ->
                        MealsItemCard(
                            meals = meals,
                            onClick = {
                                navController.navigate(Screen.Detail.createRoute(meals.id))
                            }
                        )
                    }
                }
            }

            is FavoriteUiState.Error -> {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Something went wrong")
                }
            }

            else -> {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Favorite is empty")
                }
            }
        }
    }
}