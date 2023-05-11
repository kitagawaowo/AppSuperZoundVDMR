package pe.edu.upc.appsuperzound.ui.screens.album

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.superherocompose.ui.screens.product.ProductCard

@Composable
fun FavoriteAlbumList(viewModel: AlbumViewModel) {
    val favorites by viewModel.favoriteAlbums.observeAsState(listOf())
    viewModel.fetchFavorites()

    LazyColumn {
        items(favorites) { album ->
            AlbumCard(
                true,
                album,
                insertAlbum = {
                    viewModel.insert(album)
                },
                deleteAlbum = {
                    viewModel.delete(album)
                }
            )
        }
    }
}
@Composable
fun AlbumList(viewModel: AlbumViewModel) {
    val albums by viewModel.albums.observeAsState(listOf())
    LazyColumn {
        items(albums) { album ->
            AlbumCard(
                false,
                album,
                insertAlbum = {
                    viewModel.insert(album)
                },
                deleteAlbum = {
                    viewModel.delete(album)
                }
            )
        }
    }

}
@Composable
fun AlbumCard(
    isFavoriteCard: Boolean,
    album: Album,
    insertAlbum: () -> Unit,
    deleteAlbum: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(8.dp)
        ) {
            AlbumImage(album, modifier)
            if (isFavoriteCard) {
                FavoriteAlbumItem(album, deleteAlbum, modifier)
            } else {
                AlbumItem(album, insertAlbum, deleteAlbum, modifier)
            }
        }

    }
}
@Composable
fun AlbumItem(album: Album, insertAlbum: () -> Unit, deleteAlbum: () -> Unit, modifier: Modifier = Modifier) {
    val isFavorite = remember { mutableStateOf(false) }
    isFavorite.value = album.favorite
    Spacer(modifier = modifier.size(8.dp))
    Row {
        Column(modifier = modifier.weight(0.8f)) {
            Text(text = album.name)
            Text(text = album.artist)
        }
        IconButton(
            modifier = modifier
                .weight(0.2f)
                .padding(end = 8.dp),
            onClick = {
                if (isFavorite.value) {
                    deleteAlbum()
                } else {
                    insertAlbum()
                }
                isFavorite.value = !isFavorite.value
            }
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = if (isFavorite.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }

    AlbumImage(album, modifier)
}

@Composable
fun FavoriteAlbumItem(album: Album, deleteAlbum: () -> Unit, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.size(8.dp))
    Row {
        Column(modifier = modifier.weight(0.8f)) {
            Text(text = album.name)
            Text(text = album.artist)
        }
        IconButton(
            modifier = modifier
                .weight(0.2f)
                .padding(end = 8.dp),
            onClick = {
                deleteAlbum()
            }
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }

    AlbumImage(album, modifier)
}

@Composable
fun AlbumImage(album: Album, modifier: Modifier = Modifier) {
    AsyncImage(
        model = album.image,
        contentDescription = null,
        modifier = modifier
            .size(92.dp)
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(8.dp)),
        contentScale = ContentScale.Crop
    )
}