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

class EditarAlunoActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var idadeInput: EditText
    private lateinit var cursoInput: EditText
    private lateinit var btnSalvar: Button
    private lateinit var btnVoltar: Button
    private var alunoId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_aluno)

        // Inicializar os campos
        nameInput = findViewById(R.id.editTextNome)
        lastNameInput = findViewById(R.id.editTextSobrenome)
        idadeInput = findViewById(R.id.editTextIdade)
        cursoInput = findViewById(R.id.editTextCurso)
        btnSalvar = findViewById(R.id.btnSalvar)
        btnVoltar = findViewById(R.id.btnVoltar)

        // Carregar os dados enviados pela Intent
        alunoId = intent.getStringExtra("alunoId")
        nameInput.setText(intent.getStringExtra("alunoNome"))
        lastNameInput.setText(intent.getStringExtra("alunoSobrenome"))
        idadeInput.setText(intent.getStringExtra("alunoIdade"))
        cursoInput.setText(intent.getStringExtra("alunoCurso"))

        // Configurar o botão de salvar
        btnSalvar.setOnClickListener {
            salvarAlteracoes()
        }

        // Configurar o botão de voltar
        btnVoltar.setOnClickListener {
            finish() // Finaliza a Activity e volta para a anterior
        }
    }

    private fun salvarAlteracoes() {
        val name = nameInput.text.toString().trim()
        val lastName = lastNameInput.text.toString().trim()
        val idade = idadeInput.text.toString().trim()
        val curso = cursoInput.text.toString().trim()

        if (name.isEmpty() || lastName.isEmpty() || idade.isEmpty() || curso.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show()
            return
        }

        // Criar o objeto Aluno com os novos dados
        val alunoAtualizado = Aluno(
            id = alunoId ?: "",
            name = name,
            lastname = lastName,
            idade = idade,
            curso = curso
        )

        // Fazer a requisição para atualizar o aluno
        RetrofitClient.instance.updateAluno(alunoId ?: "", alunoAtualizado).enqueue(object : Callback<Aluno> {
            override fun onResponse(call: Call<Aluno>, response: Response<Aluno>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@EditarAlunoActivity, "Aluno atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@EditarAlunoActivity, "Erro ao atualizar aluno!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Aluno>, t: Throwable) {
                Toast.makeText(this@EditarAlunoActivity, "Erro de conexão: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
