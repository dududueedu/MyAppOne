package com.example.myappone.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myappone.screens.MovieListScreen
import com.example.myappone.screens.MovieFormScreen
import com.example.myappone.screens.MovieDetailScreen
import com.example.myappone.viewmodel.MovieViewModel

@Composable
fun NavGraph(
    viewModel: MovieViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "movie_list"
    ) {

        // Tela de listagem
        composable("movie_list") {

            MovieListScreen(
                viewModel = viewModel,
                onAddClick = {
                    navController.navigate("movie_form")
                },
                onMovieClick = { movieId ->
                    navController.navigate("movie_detail/$movieId")
                }
            )
        }

        // Tela de cadastro
        composable("movie_form") {

            MovieFormScreen(
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Tela de detalhes
        composable(
            route = "movie_detail/{movieId}",
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments
                ?.getInt("movieId") ?: 0

            MovieDetailScreen(
                movieId = movieId,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}