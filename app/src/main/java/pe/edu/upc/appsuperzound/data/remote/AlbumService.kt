package pe.edu.upc.appsuperzound.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface AlbumService {
    @GET()
    fun fetchAlbums() : Call<AlbumResponse>
}