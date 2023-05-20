package com.example.proyecto.activities.minigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto.R;

import org.checkerframework.common.returnsreceiver.qual.This;

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
    //indica quien está poniendo ficha
    int turno = 1;
    //l¡posiciones ganadoras
    //cuando sea -1 aun no hay ganador
    int[] posGana = new int[]{-1};

    //formas de victoria se puede hacer con bucle anidado

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
        //estamos jugando
        if (estado == 0) {
            turno = 1;
            int numBtn = Arrays.asList(casillas).indexOf(view.getId());
            //si la ficha ya está colocada no se pone ahi (no se superpone)
            if (tablero[numBtn] == 0) {
                view.setBackgroundResource(R.drawable.cruz);
                tablero[numBtn] = 1;
                conteoFichas += 1;
                estado = comprobarEstadoCasillas();
                if (estado == 0) {
                    turno = -1;
                    generarPosicionAleatoria();
                    conteoFichas +=1;
                    estado = comprobarEstadoCasillas();
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
        btn.setBackgroundResource(R.drawable.circulo);
        tablero[pos] = -1;
    }

    private void terminarPartida(){
        //habara terminado yo o la maquina
        if (estado == 1 || estado == -1) {
            if (estado == 1) {
                //texto de has ganado

            }
        }else if (estado == 2) {
            //hemos empatado
            ResultActivity resultDialog = new ResultActivity(TresEnRayaActivity.this,"Empate",TresEnRayaActivity.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
        }

    }

    private int comprobarEstadoCasillas(){
        int newEstado = 0;
        //primera posicion seria todas igaul a 3 es que ha gandado pq estado = 1
        if (Math.abs(tablero[0]+tablero[1]+tablero[2]) ==3) {
            //siempre que gane
            posGana = new int[]{0,1,2};
            //gana tanto yo como la maquina
            newEstado = 1*turno;
        }else if (Math.abs(tablero[3]+tablero[4]+tablero[5]) ==3) {
            posGana = new int[]{3,4,5};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[6]+tablero[7]+tablero[8]) ==3) {
            posGana = new int[]{6,7,8};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[0]+tablero[3]+tablero[6]) ==3) {
            posGana = new int[]{0,3,6};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[1]+tablero[4]+tablero[7]) ==3) {
            posGana = new int[]{1,4,7};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[2]+tablero[5]+tablero[8]) ==3) {
            posGana = new int[]{2,5,8};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[0]+tablero[4]+tablero[8]) ==3) {
            posGana = new int[]{0,4,8};
            newEstado = 1*turno;
        }else if (Math.abs(tablero[2]+tablero[4]+tablero[6]) ==3) {
            posGana = new int[]{2,4,6};
            newEstado = 1*turno;
        }else if(conteoFichas == 9){ //Caso de empate
            newEstado = 2;
        }

        return newEstado;
    }
}