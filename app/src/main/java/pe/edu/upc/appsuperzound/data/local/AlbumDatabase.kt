package pe.edu.upc.appsuperzound.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AlbumEntity::class], version = 1)
abstract class AlbumDatabase: RoomDatabase(){
    abstract fun albumDao(): AlbumDao

    companion object {
        private var INSTANCE: AlbumDatabase? = null
        fun getInstance(context: Context): AlbumDatabase {
            INSTANCE = Room.databaseBuilder(
                context,
                AlbumDatabase::class.java,
                "album3.db"
            )
                .allowMainThreadQueries()
                .build()
            return INSTANCE as AlbumDatabase
        }
    }

}