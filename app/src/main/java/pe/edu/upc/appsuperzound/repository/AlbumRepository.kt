package pe.edu.upc.appsuperzound.repository

import androidx.lifecycle.MutableLiveData
import pe.edu.upc.appsuperzound.data.local.AlbumDao
import pe.edu.upc.appsuperzound.data.local.AlbumEntity
import pe.edu.upc.appsuperzound.data.model.Album
import pe.edu.upc.appsuperzound.data.remote.AlbumResponse
import pe.edu.upc.appsuperzound.data.remote.AlbumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumRepository(
    private val albumService: AlbumService,
    private val albumDao: AlbumDao
) {
    private val _albums = MutableLiveData<List<Album>>(emptyList())
    val albums get() = _albums

    private val _favoriteAlbums = MutableLiveData<List<Album>>(emptyList())
    val favoriteAlbums get() = _favoriteAlbums

    fun fetchAlbums() {
        val fetchAlbums = albumService.fetchAlbums("album")
        fetchAlbums.enqueue (object : Callback<AlbumResponse> {
            override fun onResponse(call: Call<AlbumResponse>, response: Response<AlbumResponse>) {
                if (response.isSuccessful) {
                    if (response.body()!!.loved == null) {
                        _albums.value = emptyList()
                    } else {
                        _albums.value = response.body()!!.loved!!
                        for (album in _albums.value!!) {
                            album.favorite = albumDao.fetchById(album.id).isNotEmpty()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<AlbumResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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