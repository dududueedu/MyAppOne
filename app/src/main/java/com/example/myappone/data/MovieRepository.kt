package com.example.myappone.data

import com.example.myappone.model.movieEntity
import kotlinx.coroutines.flow.Flow

class MovieRepository (
        private val movieDao: MovieDAO
    ) {

    // Buscar todos os filmes cadastrados
    fun getAllMovies(): Flow<List<movieEntity>> {
        return movieDao.getAllMovies()
    }

    // Buscar filme pelo ID
    suspend fun getMovieById(id: Int): movieEntity? {
        return movieDao.getMovieById(id)
    }

    // Cadastrar um novo filme
    suspend fun insertMovie(filme: movieEntity) {
        movieDao.insertMovie(filme)
    }

    // Excluir um filme
    suspend fun deleteMovie(filme: movieEntity) {
        movieDao.deleteMovie(filme)
    }
}