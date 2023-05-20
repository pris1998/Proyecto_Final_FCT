package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.R;
import com.example.proyecto.activities.minigames.MemoryActivity;
import com.example.proyecto.activities.minigames.TresEnRayaActivity;

public class PacienteActivity extends AppCompatActivity {

    CardView memoryCard, quizCard , tres_en_rayaCard, adivina_numCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        memoryCard = findViewById(R.id.memoryCard);

        tres_en_rayaCard = findViewById(R.id.tres_en_rayaCard);

        quizCard = findViewById(R.id.quizCard);

        adivina_numCard = findViewById(R.id.adivina_numCard);


        memoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Poner el progressbar cuando se esté cargando el minijuego pero con condiciones
                //Mensaje del AlertDialog = Iniciando juego ...
                if (view.isShown()) {
                    Intent intent = new Intent(PacienteActivity.this, MemoryActivity.class);
                    startActivity(intent);
                }else{
                    //esto no me sale
                    AlertDialog.Builder builder = new AlertDialog.Builder(PacienteActivity.this);
                    builder.setCancelable(false);
                    builder.setView(R.layout.progress_layout);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }


            }
        });

        tres_en_rayaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isShown()) {
                    Intent intent = new Intent(PacienteActivity.this, TresEnRayaActivity.class);
                    startActivity(intent);
                }
            }
        });

        quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        adivina_numCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}