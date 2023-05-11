package pe.edu.upc.appsuperzound.repository

import androidx.lifecycle.MutableLiveData
import pe.edu.upc.appsuperzound.data.local.AlbumDao
import pe.edu.upc.appsuperzound.data.local.AlbumEntity
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.appsuperzound.data.remote.AlbumService

class AlbumRepository(
    private val albumDao: AlbumDao,
    private val albumService: AlbumService
) {
    private val _albums = MutableLiveData<List<Album>>(emptyList())
    val albums get() = _albums

    private val _favoriteAlbums = MutableLiveData<List<Album>>(emptyList())
    val favoriteAlbums get() = _favoriteAlbums

    fun fetchAlbums() {
        val response = albumService.fetchAlbums().execute()
        if (response.isSuccessful) {
            val albums = response.body()?.albums ?: emptyList()
            _albums.postValue(albums)
        }
    }

    fun fetchFavoriteAlbums() {
        albumDao.fetchAll().let {
            _favoriteAlbums.value = it.map { albumEntity ->
                Album(
                    albumEntity.id,
                    albumEntity.name,
                    albumEntity.artist,
                    albumEntity.image,
                    albumEntity.year,
                    albumEntity.favorite
                )
            }
        }
    }

    fun insert(album: Album) {
        val albumEntity = AlbumEntity(
            album.id,
            album.name,
            album.artist,
            album.image,
            album.year,
            album.favorite
        )
        album.favorite = true
        albumDao.insert(albumEntity)
    }

    fun delete(album: Album) {
        val albumEntity = AlbumEntity(
            album.id,
            album.name,
            album.artist,
            album.image,
            album.year,
            album.favorite
        )
        album.favorite = false
        albumDao.delete(albumEntity)
    }
}