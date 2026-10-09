package com.example.prova1;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private final List<Curso> listaCursos;

    public CursoAdapter(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageCurso;
        final TextView textCurso;
        final TextView textGrau;
        final TextView textLocal;
        final TextView textTurno;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCurso = itemView.findViewById(R.id.image_curso);
            textCurso = itemView.findViewById(R.id.txt_curso);
            textGrau = itemView.findViewById(R.id.txt_grau);
            textLocal = itemView.findViewById(R.id.txt_local);
            textTurno = itemView.findViewById(R.id.txt_turno);
        }
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.curso_layout, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = listaCursos.get(position);
        holder.textCurso.setText(curso.getNome());
        holder.textGrau.setText(curso.getGrau());
        holder.textLocal.setText(curso.getCampus());
        holder.textTurno.setText(curso.getTurno());

        Glide.with(holder.imageCurso)
                .load(curso.getImagem())
                .into(holder.imageCurso);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), InfoView.class);
            intent.putExtra("nome", curso.getNome());
            intent.putExtra("campus", curso.getCampus());
            intent.putExtra("grau", curso.getGrau());
            intent.putExtra("turno", curso.getTurno());
            intent.putExtra("descricao", curso.getDescricao());
            intent.putExtra("imagem", curso.getImagem());
            intent.putExtra("site", curso.getSite());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }
}
