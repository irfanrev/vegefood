package id.irfanrev.vegefood.ui.feature.home

import android.content.Intent
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
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
    val context = LocalContext.current
    val intent = remember { Intent(context, Class.forName("id.irfanrev.vegefood.favorite.FavoriteActivity")) }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Vegefood", style = MaterialTheme.typography.headlineSmall)
                IconButton(onClick = {
                    context.startActivity(intent)
                }) {
                    Icon(Icons.Outlined.Favorite, contentDescription = null)
                }
            }
        }
    ) {
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