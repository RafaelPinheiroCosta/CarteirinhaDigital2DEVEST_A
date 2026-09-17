package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.repository

import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listarUnidades(
        token: String
    ): Result<List<UnidadeCurricular>>
}