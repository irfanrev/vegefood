package id.irfanrev.vegefood.ui.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.ui.component.MealsItemCard
import id.irfanrev.vegefood.ui.feature.home.model.HomeUiState
import id.irfanrev.vegefood.ui.navigation.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeView(
    mealsViewModel: HomeViewModel = koinViewModel(),
    navController: NavController,
) {
    HomeViewContent(
        mealsViewModel = mealsViewModel,
        navController = navController,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeViewContent(
    mealsViewModel: HomeViewModel,
    navController: NavController,
) {
    Scaffold() {
       val response = mealsViewModel.response.value

        when (response) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }

            is HomeUiState.Success -> {
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

            is HomeUiState.Error -> {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Something went wrong")
                }
            }

            else -> {
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Data not found")
                }
            }
    }
    }
}