package com.example.proyecto.activities.minigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto.R;

import java.util.Arrays;
import java.util.Random;

public class TresEnRayaActivity extends AppCompatActivity {

    private Integer[] casillas;
    int[] tablero = new int[]{
            0,0,0,
            0,0,0,
            0,0,0
    };

    int estado = 0;
    int conteoFichas = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);

        cargarCasillas();


    }

    private void cargarCasillas(){
        casillas = new Integer[]{
                R.id.btn00,
                R.id.btn01,
                R.id.btn02,
                R.id.btn03,
                R.id.btn04,
                R.id.btn05,
                R.id.btn06,
                R.id.btn07,
                R.id.btn08,
        };
    }
    private void ponerFicha(View view){
        if (estado == 0) {
            int numBtn = Arrays.asList(casillas).indexOf(view.getId());
            if (tablero[numBtn] == 0) {
                view.setBackgroundResource(R.drawable.cruz);
                tablero[numBtn] = 1;
                conteoFichas += 1;
                if (estado == 0) {
                    generarPosicionAleatoria();
                    conteoFichas +=1;
                }
            }
        }
    }
    public void generarPosicionAleatoria(){
        Random random = new Random();
        int pos = random.nextInt(tablero.length);
        while(tablero[pos] != 0){
            pos = random.nextInt(tablero.length);
        }
        Button btn = (Button) findViewById(casillas[pos]);
        btn.setOnClickListener(R.drawable.circulo);
        tablero[pos] = -1;
    }

    private int comprobarEstadoCasillas(){
        if (conteoFichas < 9) {
            return 0;
        }else{
            return 2;
        }
    }
}