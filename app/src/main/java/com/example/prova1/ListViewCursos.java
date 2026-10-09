package com.example.prova1;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListViewCursos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.list_view_activity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Os filtros foram enviados pela MainActivity.
        String campus = getIntent().getStringExtra("campus");
        String grau = getIntent().getStringExtra("grau");
        boolean somenteNoturnos = getIntent().getBooleanExtra("ehNoturno", false);

        ArrayList<Curso> cursosFiltrados = CursosData.filtrarCursos(campus, grau, somenteNoturnos);

        RecyclerView recyclerViewCursos = findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCursos.setHasFixedSize(true);
        recyclerViewCursos.setAdapter(new CursoAdapter(cursosFiltrados));

        TextView textQuantidade = findViewById(R.id.textQuantidade);
        textQuantidade.setText(getResources().getQuantityString(
                R.plurals.quantidade_cursos, cursosFiltrados.size(), cursosFiltrados.size()));

        TextView textSemCursos = findViewById(R.id.textSemCursos);
        boolean semResultados = cursosFiltrados.isEmpty();
        textSemCursos.setVisibility(semResultados ? View.VISIBLE : View.GONE);
        recyclerViewCursos.setVisibility(semResultados ? View.GONE : View.VISIBLE);
    }
}
