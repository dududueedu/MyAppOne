package com.example.myappone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myappone.data.MovieRepository
import com.example.myappone.model.movieEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    // List de filmes
    val movies: StateFlow<List<movieEntity>> =
        repository.getAllMovies().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Filme selecionado para visualizar os detalhes
    private val _selectedMovie = MutableStateFlow<movieEntity?>(null)
    val selectedMovie: StateFlow<movieEntity?> =
        _selectedMovie.asStateFlow()

    // Cadastrar filme
    fun insertMovie(filme: movieEntity) {
        viewModelScope.launch {
            repository.insertMovie(filme)
        }
    }

    // Buscar filme pelo ID
    fun getMovieById(id: Int) {
        viewModelScope.launch {
            _selectedMovie.value = repository.getMovieById(id)
        }
    }

    // Excluir filme
    fun deleteMovie(filme: movieEntity) {
        viewModelScope.launch {
            repository.deleteMovie(filme)
        }
    }

    // Limpar filme selecionado
    fun clearSelectedMovie() {
        _selectedMovie.value = null
    }
}