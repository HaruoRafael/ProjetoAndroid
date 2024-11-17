package com.example.trabalho.model

data class Aluno(
    val id: String,        // ID único gerado pela API
    val name: String,      // Nome do aluno
    val lastname: String,  // Sobrenome do aluno
    val idade: String,     // Idade (como string para simplificar)
    val curso: String      // Curso do aluno
)
