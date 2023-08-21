package id.irfanrev.vegefood.ui.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel
import id.irfanrev.vegefood.ui.feature.detail.model.MealsDetailUiState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsDetailView(
    navController: NavController,
    mealsId: String,
    mealsDetailViewModel: MealsDetailViewModel = koinViewModel(),
) {
    val lifecycle : Lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
                mealsDetailViewModel.getMealsDetail(mealsId)
                mealsDetailViewModel.getFavoriteById(mealsId)
            }
        }
    }

    Scaffold() {

        val response = mealsDetailViewModel.response.value

        when(response) {
            is MealsDetailUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
            is MealsDetailUiState.Success -> {
                val data = response.data
                Column(
                    modifier = Modifier.padding(it)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        item {
                            MealsDetailContent(mealsDetailModel = data, navController = navController, mealsDetailViewModel = mealsDetailViewModel)
                        }
                    }
                }
            }
            is MealsDetailUiState.Error -> {
                Text(text = response.error)
            }
            else -> {
                Text(text = "Data is Empty")
            }
        }
    }
}

@Composable
fun MealsDetailContent(
    mealsDetailModel: MealsDetailModel,
    navController: NavController,
    mealsDetailViewModel: MealsDetailViewModel,
) {
    val isFavorite = mealsDetailViewModel.isFavorite.value
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ThumbnailImage(
            isFavorite = isFavorite,
            image = mealsDetailModel.strMealThumb,
            onBack = {
                navController.popBackStack()
            },
            onFavorite = {
                if (isFavorite) {
                    mealsDetailViewModel.deleteFavoriteMeals(mealsDetailModel.idMeal)
                } else {
                    mealsDetailViewModel.setFavoriteMeals(
                        id = mealsDetailModel.idMeal,
                        name = mealsDetailModel.strMeal,
                        img = mealsDetailModel.strMealThumb,
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = mealsDetailModel.strMeal,  fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ) {
                Text(text = mealsDetailModel.strCategory, color = Color.White, modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ingredients", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = mealsDetailModel.strIngredient1, style = MaterialTheme.typography.bodyMedium)
            Text(text = mealsDetailModel.strIngredient2, style = MaterialTheme.typography.bodyMedium)
            Text(text = mealsDetailModel.strIngredient3, style = MaterialTheme.typography.bodyMedium)
            Text(text = mealsDetailModel.strIngredient4, style = MaterialTheme.typography.bodyMedium)
            Text(text = mealsDetailModel.strIngredient5, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Instructions", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = mealsDetailModel.strInstructions, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun ThumbnailImage(
    isFavorite: Boolean,
    image: String,
    onBack: () -> Unit,
    onFavorite: () -> Unit,
) {
    Box() {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            IconButton(onClick = { onFavorite() }) {
                if (isFavorite) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}

