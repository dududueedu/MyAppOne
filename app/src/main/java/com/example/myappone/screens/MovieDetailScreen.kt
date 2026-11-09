
package com.example.myappone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myappone.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieViewModel,
    onBackClick: () -> Unit
) {

    // Solicita ao ViewModel os dados do filme selecionado
    LaunchedEffect(movieId) {
        viewModel.getMovieById(movieId)
    }

    // Observa o filme selecionado
    val filme by viewModel.selectedMovie.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detalhes do Filme")
                }
            )
        }
    ) { innerPadding ->

        if (filme == null) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // Título
                Text(
                    text = filme!!.titulo,
                    style = MaterialTheme.typography.headlineMedium
                )

                // Gênero
                Text(
                    text = "Gênero: ${filme!!.genero}",
                    style = MaterialTheme.typography.titleMedium
                )

                HorizontalDivider()

                // Descrição
                Text(
                    text = "Descrição",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = filme!!.descricao,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.weight(1f))

                // Excluir
                Button(
                    onClick = {
                        viewModel.deleteMovie(filme!!)
                        onBackClick()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text(text = "Excluir filme")
                }

                // Voltar
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}
