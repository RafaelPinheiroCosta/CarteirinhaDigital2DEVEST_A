package com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.data.repository.LoginRepository
import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.data.repository.LoginRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryProvider.provide()
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.OnUsuarioChange ->{
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        errorMessage = null
                    )
                }
            }
            is LoginEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        errorMessage = null
                    )
                }
            }
            LoginEvent.OnEntrarClick -> fazerLogin()

            LoginEvent.OnNavegacaoRealizada -> {
                _uiState.update {
                    it.copy(
                        usuarioLogado = null
                    )
                }
            }
        }

    }

    private fun fazerLogin() {
        val state = _uiState.value

        if(state.usuario.isBlank() || state.senha.isBlank()){
            _uiState.update {
                it.copy(
                    errorMessage = "Preencha login e senha"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = null,
                    usuarioLogado = null
                )
            }
            val result = repository.login(
                state.usuario.trim(),
                state.senha.trim()
            )

            result
                .onSuccess { usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = null,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message?:"Erro ao Fazer Login."
                        )
                    }

                }
        }
    }
}
