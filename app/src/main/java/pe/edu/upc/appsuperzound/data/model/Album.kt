package pe.edu.upc.appsuperzound.data.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("idAlbum")
    val id: String,
    @SerializedName("strAlbum")
    val name: String,
    @SerializedName("strArtist")
    val artist: String,
    @SerializedName("strAlbumThumb")
    val image: String,
    @SerializedName("intYearReleased")
    val year: String,
    var favorite: Boolean
)