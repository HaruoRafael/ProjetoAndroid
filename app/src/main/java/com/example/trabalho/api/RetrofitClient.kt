package com.example.trabalho.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://6738ec80a3a36b5a62ed89db.mockapi.io/api/v1/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) // URL base da API
            .addConverterFactory(GsonConverterFactory.create()) // Conversor para JSON
            .build()

        retrofit.create(ApiService::class.java) // Criação da implementação da interface
    }
}