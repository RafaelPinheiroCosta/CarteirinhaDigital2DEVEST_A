package com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.repository

import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.rafaelcosta.carteirinhadigital2devest_b.feature.unidadecurriculares.domain.model.UnidadeCurricular
import okio.IOException
import retrofit2.HttpException

class ApiUnidadeCurricularRepositoryImpl(
    private val api: UnidadeCurricularApi
): UnidadeCurricularRepository {
    override suspend fun listarUnidades(
        token: String
    ): Result<List<UnidadeCurricular>> {
       return runCatching {
           api.listar(
               authorization = "Bearer $token"
           ).map {
               it.toDomain()
           }
       }.recoverCatching { throwable ->

           throw when(throwable){
               is HttpException -> {
                   if(throwable.code() == 401){
                       IllegalStateException("Sua sessão expirou. Faça login novamente")
                   }else{
                       IllegalStateException("Erro ao carregar unidades curriculares(${throwable.code()})")
                   }
               }
               is IOException ->
                   IllegalStateException("Não foi possivel conectar a API")
               else ->
                   IllegalStateException(throwable.message ?: "Erro ao carregar unidades curriculares")
           }
       }
    }
}