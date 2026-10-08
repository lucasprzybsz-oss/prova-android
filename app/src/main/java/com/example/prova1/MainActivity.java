package com.example.prova1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCampus;
    RadioGroup radioGroupGrau;
    CheckBox checkNoturno;
    Button buttonVerCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerCampus = findViewById(R.id.spinnerCampus);
        radioGroupGrau = findViewById(R.id.radioGroupGrau);
        checkNoturno = findViewById(R.id.checkNoturno);
        buttonVerCursos = findViewById(R.id.buttonVerCursos);


        String campus = spinnerCampus.getSelectedItem().toString();

        int selectedId = radioGroupGrau.getCheckedRadioButtonId();
        RadioButton radioselecionado = findViewById(selectedId);

        String grau = radioselecionado.getText().toString();

        Boolean ehNoturno = checkNoturno.isSelected();

        buttonVerCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.class, ListView.class);

                intent.putExtra("campus", campus);
                intent.putExtra("grau", grau);
                intent.putExtra("ehNoturno", ehNoturno);
                startActivity(intent);

            }
        });











    }
}