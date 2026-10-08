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

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder>{

    private ArrayList<Curso> listaCurso;
    public CursoAdapter(ArrayList<Curso> listaCurso){
        this.listaCurso = listaCurso;
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder{
        ImageView image_curso;
        TextView txt_curso;
        TextView txt_grau;
        TextView txt_local;

        public CursoViewHolder(
                @NonNull View itemView
        ){
            super(itemView);
            image_curso = itemView.findViewById(R.id.image_curso);
            txt_curso = itemView.findViewById(R.id.txt_curso);
            txt_grau = itemView.findViewById(R.id.txt_grau);
            txt_local = itemView.findViewById(R.id.txt_local);
        }

    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.curso_layout,parent,false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CursoViewHolder holder, int position){
        Curso curso = listaCurso.get(position);
        holder.txt_curso.setText(curso.getNome());
        holder.txt_local.setText(curso.getCampus());
        holder.txt_grau.setText(curso.getGrau());

        Glide.with(holder.itemView
                .getContext()).load(curso.getImagem()).into(holder.image_curso);

        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), InfoViewCursos.class);
        });

    }

    @Override
    public int getItemCount(){
        return listaCurso.size();
    }


}


