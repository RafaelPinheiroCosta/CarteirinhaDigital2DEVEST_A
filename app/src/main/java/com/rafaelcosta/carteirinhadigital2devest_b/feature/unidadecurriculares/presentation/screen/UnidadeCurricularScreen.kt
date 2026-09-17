package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.dataSource
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.UnidadeCurricularViewModel
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.presentation.component.UnidadeCurricularCard

@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier,
    viewModel: UnidadeCurricularViewModel = viewModel(),
    token: String
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(token) {
        viewModel.carregar(token)
    }

    val errorMensage = uiState.errorMensage

    when{
        uiState.isLoading ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        errorMensage != null ->{
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = errorMensage,
                    color = MaterialTheme.colorScheme.error
                )
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {viewModel.carregar(token)}
                ) {
                    Text(text = "Tentar Novamente")
                }
            }
        }
        uiState.listaUnidades.isEmpty() ->{
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    "Nenhuma unidade curricular foi encontrada"
                )
            }
        }
        else ->{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.listaUnidades) { unidadeCurricular ->
                    UnidadeCurricularCard(unidadeCurricular = unidadeCurricular)
                }
            }
        }
    }


}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun UnidadeCurricularScreenPreview() {
    UnidadeCurricularScreen(
        token = "fake"
    )
}