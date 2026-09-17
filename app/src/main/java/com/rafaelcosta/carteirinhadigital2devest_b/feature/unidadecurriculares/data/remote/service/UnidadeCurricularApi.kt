package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.remote.service

import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.remote.dto.UnidadeCurricularDTO
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular
import retrofit2.http.GET
import retrofit2.http.Header

interface UnidadeCurricularApi {
    @GET("unidades-curriculares")
    suspend fun listar(
        @Header("Authorization")
        authorization:String
    ): List<UnidadeCurricularDTO>
}