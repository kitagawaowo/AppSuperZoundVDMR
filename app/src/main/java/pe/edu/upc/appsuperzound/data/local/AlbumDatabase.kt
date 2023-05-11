package pe.edu.upc.appsuperzound.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.upc.appsuperzound.data.model.Album

@Database(entities = [Album::class], version = 1)
abstract class AlbumDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        private var INSTANCE: AlbumDatabase? = null
        fun getInstance(context: Context): AlbumDatabase {
            INSTANCE = Room.databaseBuilder(
                context,
                AlbumDatabase::class.java,
                "album2.db"
            )
                .allowMainThreadQueries()
                .build()
            return INSTANCE as AlbumDatabase
        }
    }

}