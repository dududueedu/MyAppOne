package com.example.myappone.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myappone.model.movieEntity
import com.example.myappone.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFormScreen(
    viewModel: MovieViewModel,
    onBackClick: () -> Unit
) {

    // Estados dos campos
    var titulo by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Cadastrar um Filme")
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Campo título
            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = {
                    Text(text = "Título")
                },
                placeholder = {
                    Text(text = "Digite o título")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo gênero
            OutlinedTextField(
                value = genero,
                onValueChange = { genero = it },
                label = {
                    Text(text = "Gênero")
                },
                placeholder = {
                    Text(text = "Ex: Ação, Comédia, Drama")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            // Campo descrição
            OutlinedTextField(
                value = descricao,
                onValueChange = { descricao = it },
                label = {
                    Text(text = "Descrição")
                },
                placeholder = {
                    Text(text = "Digite uma descrição")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                minLines = 4
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botão salvar
            Button(
                onClick = {

                    if (
                        titulo.isNotBlank() &&
                        genero.isNotBlank() &&
                        descricao.isNotBlank()
                    ) {

                        val filme = movieEntity(
                            titulo = titulo.trim(),
                            genero = genero.trim(),
                            descricao = descricao.trim()
                        )

                        viewModel.insertMovie(filme)

                        onBackClick()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = titulo.isNotBlank() &&
                        genero.isNotBlank() &&
                        descricao.isNotBlank()
            ) {
                Text(text = "Salvar")
            }

            // Botão voltar
            OutlinedButton(
                onClick = onBackClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Voltar")
            }
        }
    }
}
