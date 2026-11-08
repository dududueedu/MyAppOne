package com.example.myappone.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myappone.model.movieEntity

@Database(
    entities = [movieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase(){
    abstract fun movieDao(): MovieDAO

    companion object {

        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getDatabase(context: Context): MovieDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDataBase::class.java,
                    "movie_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}