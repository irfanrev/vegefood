package id.irfanrev.vegefood.ui.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.irfanrev.vegefood.R

@Composable
fun ProfileView() {
    ProfileViewContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileViewContent() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Profile", style = MaterialTheme.typography.headlineSmall)

            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ProfilePict()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Irfan Maulana", style = MaterialTheme.typography.titleMedium)
            Text(text = "irfan.resimen2018@gmail.com")
        }
    }
}


@Composable
fun ProfilePict() {
    Image(
        painter = painterResource(id = R.drawable.irfan),
        contentDescription = null,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(CircleShape)
    )
}