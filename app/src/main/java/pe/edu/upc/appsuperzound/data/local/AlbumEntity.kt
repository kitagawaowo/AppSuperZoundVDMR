package pe.edu.upc.appsuperzound.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Album")
class AlbumEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val artist: String,
    val image: String,
    val year: String,
    var favorite: Boolean
)