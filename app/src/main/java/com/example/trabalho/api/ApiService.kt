package com.example.trabalho.api

import com.example.trabalho.model.Aluno
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    // Listar todos os alunos
    @GET("aluno")
    fun getAlunos(): Call<List<Aluno>>

    // Criar um novo aluno
    @POST("aluno")
    fun createAluno(@Body aluno: Aluno): Call<Aluno>

    // Atualizar um aluno existente
    @PUT("aluno/{id}")
    fun updateAluno(@Path("id") id: String, @Body aluno: Aluno): Call<Aluno>


    // Deletar um aluno
    @DELETE("aluno/{id}")
    fun deleteAluno(@Path("id") id: String): Call<Void>
}
