package com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafaelcosta.carteirinhadigital2devest_b.app.navigation.Routes
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation.LoginEvent
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation.LoginUiState
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation.LoginViewModel

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    viewModel: LoginViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(10.dp, Alignment.CenterVertically),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Login",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = uiState.usuario,
            onValueChange = { value ->
                viewModel.onEvent(LoginEvent.OnUsuarioChange(value))
            },
            label = {
                Text(
                    text = "Email"
                )
            },
            isError = uiState.errorMessage != null
        )
        OutlinedTextField(
            value = uiState.senha,
            onValueChange = { value ->
                viewModel.onEvent(LoginEvent.OnSenhaChange(value))
            },
            label = {
                Text(
                    text = "Senha"
                )
            },
            isError = uiState.errorMessage != null
        )

        uiState.errorMessage?.let{ error ->
            Text(
                text = error,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth(0.85f)
            )
        }

        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.OnEntrarClick)
            },
            shape = RoundedCornerShape(size = 4.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color.Black
            ),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.secondary
            ),
            modifier = Modifier
                .fillMaxWidth(.6f)
        ) {
            if (uiState.isLoading){
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth(0.60f)
                        .height(5.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.35f)
                )
            }else {
                Text(
                    text = "Entrar",
                    color = Color.White
                )
            }
        }
    }
}