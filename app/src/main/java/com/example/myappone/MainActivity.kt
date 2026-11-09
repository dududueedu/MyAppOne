package com.example.myappone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myappone.data.MovieDataBase
import com.example.myappone.data.MovieRepository
import com.example.myappone.navigation.NavGraph
import com.example.myappone.viewmodel.MovieViewModel
import com.example.myappone.viewmodel.MovieViewModelFactory
import androidx.compose.material3.MaterialTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o banco de dados
        val database = MovieDataBase.getDatabase(applicationContext)

        // Cria o Repository
        val repository = MovieRepository(database.movieDao())

        // Cria a Factory do ViewModel
        val factory = MovieViewModelFactory(repository)

        setContent {

            MaterialTheme {

                // Instancia o ViewModel
                val movieViewModel: MovieViewModel = viewModel(
                    factory = factory
                )

                // Inicia a navegação
                NavGraph(
                    viewModel = movieViewModel
                )
            }
        }
    }
}