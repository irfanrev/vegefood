package id.irfanrev.vegefood.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import id.irfanrev.vegefood.core.domain.model.Meals
import id.irfanrev.vegefood.core.domain.model.MealsDetailModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealsItemCard(
    meals: Meals,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(
                8.dp,
            )
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meals.image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Text(
                text = meals.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ) {
                Text(text = "Vegetarian", color = Color.White, modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMealsItemCard(
    meals: MealsDetailModel,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(
                8.dp,
            )
            .height(150.dp)
            .clickable { onClick() }
    ) {
        Card(
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meals.strMealThumb)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Text(
                text = meals.strMeal,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Gray.copy(alpha = 0.5f))
            ) {
                Text(text = "Vegetarian", color = Color.White, modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp))
            }
        }
    }
}