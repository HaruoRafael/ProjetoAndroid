package com.example.trabalho.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho.R
import com.example.trabalho.api.RetrofitClient
import com.example.trabalho.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarAlunosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlunoAdapter
    private lateinit var btnCadastrar: Button
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_alunos)

        recyclerView = findViewById(R.id.recyclerViewAlunos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar o adapter com uma lista vazia
        adapter = AlunoAdapter(listOf(), this)
        recyclerView.adapter = adapter

        // Inicializar botões
        btnCadastrar = findViewById(R.id.btnCadastrar)
        btnVoltar = findViewById(R.id.btnVoltar)

        // Configurar ações dos botões
        btnCadastrar.setOnClickListener {
            // Navegar para a Activity de cadastro
            val intent = Intent(this, CadastrarAlunoActivity::class.java)
            startActivity(intent)
        }

        btnVoltar.setOnClickListener {
            // Voltar para a Activity anterior
            finish()
        }

        carregarAlunos()
    }

    // Recarregar a lista ao retornar para esta Activity
    override fun onResume() {
        super.onResume()
        carregarAlunos() // Recarrega os dados da API
    }

    private fun carregarAlunos() {
        RetrofitClient.instance.getAlunos().enqueue(object : Callback<List<Aluno>> {
            override fun onResponse(call: Call<List<Aluno>>, response: Response<List<Aluno>>) {
                if (response.isSuccessful) {
                    val alunos = response.body() ?: emptyList()
                    adapter.atualizarLista(alunos) // Atualizar o adapter com os novos dados
                } else {
                    Toast.makeText(this@ListarAlunosActivity, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Aluno>>, t: Throwable) {
                Toast.makeText(this@ListarAlunosActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e("ListarAlunosActivity", "Erro: ${t.message}")
            }
        })
    }
}
