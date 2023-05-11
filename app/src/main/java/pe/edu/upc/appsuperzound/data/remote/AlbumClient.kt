package pe.edu.upc.appsuperzound.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AlbumClient {
    private const val BASE_URL = "https://theaudiodb.com/api/v1/json/523532/"
    fun albumService(): AlbumService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(AlbumService::class.java)
    }
}