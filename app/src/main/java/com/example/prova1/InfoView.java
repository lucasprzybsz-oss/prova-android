package com.example.prova1;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class InfoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String nome = getIntent().getStringExtra("nome");
        String campus = getIntent().getStringExtra("campus");
        String grau = getIntent().getStringExtra("grau");
        String turno = getIntent().getStringExtra("turno");
        String descricao = getIntent().getStringExtra("descricao");
        String imagem = getIntent().getStringExtra("imagem");
        String site = getIntent().getStringExtra("site");

        ((TextView) findViewById(R.id.tituloCurso)).setText(nome);
        ((TextView) findViewById(R.id.textTipoCurso)).setText(grau);
        ((TextView) findViewById(R.id.textCidade)).setText(campus);
        ((TextView) findViewById(R.id.textHorario)).setText(turno);
        ((TextView) findViewById(R.id.textDescricao)).setText(descricao);

        ImageView imagemCurso = findViewById(R.id.imagemCurso);
        Glide.with(imagemCurso)
                .load(imagem)
                .into(imagemCurso);

        Button buttonPaginaCurso = findViewById(R.id.buttonPaginaCurso);
        buttonPaginaCurso.setOnClickListener(v -> {
            Intent abrirPagina = new Intent(Intent.ACTION_VIEW, Uri.parse(site));
            startActivity(abrirPagina);
        });

        Button buttonCompartilhar = findViewById(R.id.buttonCompartilhar);
        buttonCompartilhar.setOnClickListener(v -> {
            String mensagem = nome + " (" + grau + ")\n"
                    + "Campus: " + campus + "\n"
                    + "Turno: " + turno + "\n"
                    + (site == null ? "" : site);

            Intent compartilhar = new Intent(Intent.ACTION_SEND);
            compartilhar.setType("text/plain");
            compartilhar.putExtra(Intent.EXTRA_TEXT, mensagem);
            startActivity(Intent.createChooser(compartilhar, "Compartilhar curso"));
        });
    }
}
