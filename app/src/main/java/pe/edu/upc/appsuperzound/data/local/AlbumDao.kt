package pe.edu.upc.appsuperzound.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

interface AlbumDao {
    @Query("SELECT * FROM albums WHERE id=:id")
    fun fetchById(id: String): List<AlbumEntity>
    @Query("SELECT * FROM albums")
    fun fetchAll(): List<AlbumEntity>

    @Insert
    fun insert(albumEntity: AlbumEntity)

    @Delete
    fun delete(albumEntity: AlbumEntity)
}