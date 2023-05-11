package pe.edu.upc.appsuperzound.data.remote

import pe.edu.upc.appsuperzound.data.model.Album

data class AlbumResponse(
    val albums: List<Album>?
)