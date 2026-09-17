package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.repository

import com.rafaelcosta.carteirinhadigital2devest_b.app.network.NetworkFactory

object UnidadeCurricularRepositoryProvider {
    fun provide(): UnidadeCurricularRepository{
        return ApiUnidadeCurricularRepositoryImpl(
            NetworkFactory.createUnidadeCurricularApi()
        )
    }
}