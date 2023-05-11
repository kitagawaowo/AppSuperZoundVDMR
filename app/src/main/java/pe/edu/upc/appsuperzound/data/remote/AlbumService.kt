package pe.edu.upc.appsuperzound.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumService {
    @GET("mostloved.php")
    fun fetchAlbums(@Query("format") format: String) : Call<AlbumResponse>
}