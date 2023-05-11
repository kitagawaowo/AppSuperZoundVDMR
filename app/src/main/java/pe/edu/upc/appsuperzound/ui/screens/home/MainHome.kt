package pe.edu.upc.appsuperzound.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import pe.edu.upc.appsuperzound.ui.screens.album.AlbumScreen
import pe.edu.upc.appsuperzound.ui.screens.album.AlbumViewModel
import pe.edu.upc.appsuperzound.ui.screens.album.FavoriteAlbumScreen

@Composable
fun HomeNavigation (viewModel: AlbumViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeScreen") {
        composable("HomeScreen") {
            HomeScreen(
                navigateFind = { navController.navigate("AlbumScreen") },
                navigateFavorites = { navController.navigate("FavoritesScreen") }
            )
        }
        composable("AlbumScreen") {
            AlbumScreen(viewModel = viewModel)
        }
        composable("FavoritesScreen") {
            FavoriteAlbumScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun HomeScreen(
    navigateFind: () -> Unit = {},
    navigateFavorites: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = "App List Product",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(16.dp)
        )

        BrandingImage()
        Row(
            modifier = modifier
                .padding(horizontal = 6.dp)
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                onClick = { navigateFind() }
            ) {
                Text(text = "List Album")
            }

            Button(
                modifier = modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp),
                onClick = { navigateFavorites() }
            ) {
                Text(text = "Favourite Album")
            }
        }
    }
}
@Composable
fun BrandingImage(modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://www.womenseday.org/wp-content/uploads/2017/11/UPCMini-1.jpg",
        contentDescription = null,
        modifier = modifier
            .size(500.dp)
            .clip(shape = RoundedCornerShape(4.dp)),
        contentScale = ContentScale.Crop
    )
}