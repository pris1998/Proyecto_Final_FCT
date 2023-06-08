package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;

import com.example.proyecto.R;
import com.example.proyecto.activities.minigames.AdivinaNumberActivity;
import com.example.proyecto.activities.minigames.MemoryActivity;
import com.example.proyecto.activities.minigames.PizarraActivity;
import com.example.proyecto.activities.minigames.TresEnRayaActivity;

public class PacienteActivity extends AppCompatActivity {
    private ScrollView linearLayout;
    CardView memoryCard, pizarra , tres_en_rayaCard, adivina_numCard;

    /**
     * Método que se llama cuando se crea la actividad.
     *
     * @param savedInstanceState Los datos guardados del estado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);
        linearLayout = (ScrollView) findViewById(R.id.root_paciente) ;

        memoryCard = findViewById(R.id.memoryCard);

        tres_en_rayaCard = findViewById(R.id.tres_en_rayaCard);

        pizarra = findViewById(R.id.pizarraCard);

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
                    //builder.setView(R.layout.progress_layout);
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

        pizarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PacienteActivity.this, PizarraActivity.class);
                startActivity(intent);
            }
        });

        adivina_numCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PacienteActivity.this, AdivinaNumberActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Crea el menú en la actividad.
     *
     * @param menu El menú a inflar.
     * @return true si se crea el menú correctamente, false en caso contrario.
     */    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Construye la vista donde se va a colocar el menu
        getMenuInflater().inflate(R.menu.menu_simple,menu);
        return true;
    }
    /**
     * Maneja los eventos del menú seleccionados.
     *
     * @param item El ítem de menú seleccionado.
     * @return true si el evento del menú se maneja correctamente, false en caso contrario.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){
            case R.id.Item_menusalir:
                Intent intent_salir = new Intent(PacienteActivity.this, LoginActivity.class);
                startActivity(intent_salir);
                break;
        }
        return true;
    }
}