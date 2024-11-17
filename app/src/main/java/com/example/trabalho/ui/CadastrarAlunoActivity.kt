package com.example.trabalho.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho.R
import com.example.trabalho.api.RetrofitClient
import com.example.trabalho.model.Aluno
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastrarAlunoActivity : AppCompatActivity() {

    // Declaração das variáveis para os campos e botões
    private lateinit var nameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var idadeInput: EditText
    private lateinit var cursoInput: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnVoltar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_aluno)

        // Inicializar os campos e os botões
        nameInput = findViewById(R.id.editTextNome)
        lastNameInput = findViewById(R.id.editTextSobrenome)
        idadeInput = findViewById(R.id.editTextIdade)
        cursoInput = findViewById(R.id.editTextCurso)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnVoltar = findViewById(R.id.btnVoltar)

        // Configurar o clique no botão de salvar
        btnSalvar.setOnClickListener {
            salvarAluno()
        }

        // Configurar o clique no botão de voltar
        btnVoltar.setOnClickListener {
            finish() // Finaliza a Activity e volta para a anterior
        }
    }

    private fun salvarAluno() {
        // Obter os valores dos campos de entrada
        val name = nameInput.text.toString().trim()
        val lastName = lastNameInput.text.toString().trim()
        val idade = idadeInput.text.toString().trim()
        val curso = cursoInput.text.toString().trim()

        // Validar os campos
        if (name.isEmpty() || lastName.isEmpty() || idade.isEmpty() || curso.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar um objeto de aluno
        val novoAluno = Aluno(
            id = "", // ID será gerado automaticamente pela API
            name = name,
            lastname = lastName,
            idade = idade,
            curso = curso
        )

        // Fazer a requisição para criar o aluno
        RetrofitClient.instance.createAluno(novoAluno).enqueue(object : Callback<Aluno> {
            override fun onResponse(call: Call<Aluno>, response: Response<Aluno>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@CadastrarAlunoActivity,
                        "Aluno cadastrado com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish() // Finaliza a activity após o cadastro bem-sucedido
                } else {
                    Toast.makeText(
                        this@CadastrarAlunoActivity,
                        "Erro ao cadastrar aluno. Tente novamente!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Aluno>, t: Throwable) {
                Toast.makeText(
                    this@CadastrarAlunoActivity,
                    "Erro de conexão: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
