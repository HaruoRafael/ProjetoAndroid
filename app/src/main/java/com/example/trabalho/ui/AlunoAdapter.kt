package com.example.trabalho.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho.R
import com.example.trabalho.model.Aluno

class AlunoAdapter(
    private var alunos: List<Aluno>,
    private val context: Context
) : RecyclerView.Adapter<AlunoAdapter.AlunoViewHolder>() {

    class AlunoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomeTextView: TextView = view.findViewById(R.id.textNome)
        val cursoTextView: TextView = view.findViewById(R.id.textCurso)
        val btnEditar: Button = view.findViewById(R.id.btnEditar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlunoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_aluno, parent, false)
        return AlunoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlunoViewHolder, position: Int) {
        val aluno = alunos[position]
        holder.nomeTextView.text = aluno.name
        holder.cursoTextView.text = aluno.curso

        // Configurar o botão de editar
        holder.btnEditar.setOnClickListener {
            val intent = Intent(context, EditarAlunoActivity::class.java)
            intent.putExtra("alunoId", aluno.id)
            intent.putExtra("alunoNome", aluno.name)
            intent.putExtra("alunoSobrenome", aluno.lastname)
            intent.putExtra("alunoIdade", aluno.idade)
            intent.putExtra("alunoCurso", aluno.curso)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = alunos.size

    fun atualizarLista(novaLista: List<Aluno>) {
        alunos = novaLista
        notifyDataSetChanged()
    }
}
