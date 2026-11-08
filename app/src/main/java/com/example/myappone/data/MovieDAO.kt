package com.example.myappone.data

import androidx.room.*
import com.example.myappone.model.movieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Query("SELECT * FROM filmes ORDER BY id DESC")
    fun getAllMovies(): Flow<List<movieEntity>>

    @Query("SELECT * FROM filmes WHERE id = :id")
    suspend fun getMovieById(id: Int): movieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(filme: movieEntity)

    @Delete
    suspend fun deleteMovie(filme: movieEntity)
}