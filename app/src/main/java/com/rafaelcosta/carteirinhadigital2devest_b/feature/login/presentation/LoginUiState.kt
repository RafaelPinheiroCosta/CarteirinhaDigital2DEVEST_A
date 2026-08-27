package com.rafaelcosta.carteirinhadigital2devest_b.feature.login.presentation

import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.domain.model.UsuarioLogado

data class LoginUiState(
    val login: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val usuarioLogado: UsuarioLogado? = null
) {
    val loginRealizado: Boolean
        get() = usuarioLogado != null
}