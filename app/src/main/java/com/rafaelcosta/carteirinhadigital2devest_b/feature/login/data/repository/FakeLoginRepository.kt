package com.rafaelcosta.carteirinhadigital2devest_b.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest_b.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeAuthRepository : LoginRepository {
    override suspend fun login(login: String, senha: String): Result<UsuarioLogado> {
        delay(1500)

        return if (login.equals( "aluno") && senha.equals("123")) {
            Result.success(
                UsuarioLogado(
                    id = "1",
                    nome = "Rafael Costa",
                    curso = "Desenvolvedor de Sistemas",
                    turma = "2DEVEST-A",
                    token = "token-fake-para-aula"
                )
            )
        } else {
            Result.failure(IllegalArgumentException("Login ou senha inválidos. Use aluno / 123."))
        }
    }
}