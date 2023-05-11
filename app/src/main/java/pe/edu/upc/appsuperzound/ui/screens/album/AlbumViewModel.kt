package pe.edu.upc.appsuperzound.ui.screens.album

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pe.edu.upc.appsuperzound.data.local.AlbumDatabase
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.appsuperzound.data.remote.AlbumClient
import pe.edu.upc.appsuperzound.repository.AlbumRepository

class AlbumViewModel (application: Application) : AndroidViewModel(application) {
    private val albumService = AlbumClient.albumService()
    private val albumDao = AlbumDatabase.getInstance(application).albumDao()
    private val albumRepository = AlbumRepository(albumDao, albumService)

    private var _albums = albumRepository.albums
    val albums get() = _albums

    private var _favoriteAlbums = albumRepository.favoriteAlbums
    val favoriteAlbums get() = _favoriteAlbums

    fun fetchAlbums() {
        albumRepository.fetchAlbums()
        _albums.value = albumRepository.albums.value
    }

    fun fetchFavorites() {
        albumRepository.fetchFavoriteAlbums()
        _favoriteAlbums.value = albumRepository.favoriteAlbums.value
    }

    fun insert(album: Album) {
        albumRepository.insert(album)
    }

    fun delete(album: Album) {
        albumRepository.delete(album)
        fetchFavorites()
    }
}