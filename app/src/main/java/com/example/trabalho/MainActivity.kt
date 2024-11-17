package com.example.trabalho

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho.ui.ListarAlunosActivity
import com.example.trabalho.ui.CadastrarAlunoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botão para listar alunos
        val btnListarAlunos = findViewById<Button>(R.id.btnListarAlunos)
        btnListarAlunos.setOnClickListener {
            val intent = Intent(this, ListarAlunosActivity::class.java)
            startActivity(intent)
        }

        // Botão para cadastrar um novo aluno
        val btnCadastrarAluno = findViewById<Button>(R.id.btnCadastrarAluno)
        btnCadastrarAluno.setOnClickListener {
            val intent = Intent(this, CadastrarAlunoActivity::class.java)
            startActivity(intent)
        }
    }
}
