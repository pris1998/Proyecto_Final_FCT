package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.proyecto.R;
import com.example.proyecto.activities.LoginActivity;
import com.example.proyecto.activities.PacienteActivity;


import java.util.Arrays;
import java.util.Random;
/**
 * Clase TresEnRayaActivity.
 * Representa la actividad del juego Tres en Raya.
 */
public class TresEnRayaActivity extends AppCompatActivity {
    private RelativeLayout constraintLayout;

    Button btn00, btn01 , btn02 ,btn03 , btn04 , btn05 ,btn06 , btn07 , btn08;
    private Integer[] casillas;
    int[] tablero = new int[]{
            0,0,0,
            0,0,0,
            0,0,0
    };

    int estado = 0;
    int conteoFichas = 0;
    //indica quien está poniendo ficha
    int turnoJugadorUno = 1;
    //posiciones ganadoras
    //cuando sea -1 aun no hay ganador
    int[] posGana = new int[]{-1};

    /**
     * Método onCreate.
     * Se ejecuta cuando se crea la actividad.
     * Inicializa la interfaz de usuario y configura los eventos de los botones.
     * @param savedInstanceState El estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_en_raya);
        constraintLayout = (RelativeLayout) findViewById(R.id.root_tresenraya);

        cargarCasillas();
        inicializarCasillas();

    }
    /**
     * Método para cargar las casillas del tablero.
     */
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
    /**
     * Método para inicializar las casillas del tablero.
     * Configura los eventos de clic para cada casilla.
     */
    private void inicializarCasillas(){
        btn00 = findViewById(R.id.btn00);
        btn01 = findViewById(R.id.btn01);
        btn02 = findViewById(R.id.btn02);
        btn03 = findViewById(R.id.btn03);
        btn04 = findViewById(R.id.btn04);
        btn05 = findViewById(R.id.btn05);
        btn06 = findViewById(R.id.btn06);
        btn07 = findViewById(R.id.btn07);
        btn08 = findViewById(R.id.btn08);


        btn00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });
        btn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ponerFicha(v);
            }
        });


    }

    /**
     * Método para colocar una ficha en una casilla.
     * @param view La vista del botón en el que se hizo clic.
     */

    private void ponerFicha(View view){
        //estamos jugando
        if (estado == 0) {
            turnoJugadorUno = 1;
            int numBtn = Arrays.asList(casillas).indexOf(view.getId());
            //si la ficha ya está colocada no se pone ahi (no se superpone)
            if (tablero[numBtn] == 0) {
                view.setBackgroundResource(R.drawable.cruz);
                tablero[numBtn] = 1;
                conteoFichas += 1;
                estado = comprobarEstadoCasillas();
                terminarPartida();
                if (estado == 0) {
                    turnoJugadorUno = -1;
                    generarPosicionAleatoria();
                    conteoFichas +=1;
                    estado = comprobarEstadoCasillas();
                    //cuando haya puesto ficha la maquina
                    terminarPartida();
                }
            }
        }
    }
    /**
     * Método para generar una posición aleatoria en el tablero y colocar una ficha.
     */
    public void generarPosicionAleatoria(){
        Random random = new Random();
        int pos = random.nextInt(tablero.length);
        while(tablero[pos] != 0){
            pos = random.nextInt(tablero.length);
        }
        Button btn = (Button) findViewById(casillas[pos]);
        btn.setBackgroundResource(R.drawable.background_turn);
        btn.setBackgroundResource(R.drawable.circulo);
        tablero[pos] = -1;
    }
    /**
     * Método para finalizar la partida y mostrar el resultado.
     */
    private void terminarPartida(){
        //habara terminado yo o la maquina
        if (estado == 1 || estado == -1) {
            if (estado == 1) {
                //texto de has ganado , cambiar con cuadro personalizado
                ResultActivity result = new ResultActivity(TresEnRayaActivity.this,"Has ganado",TresEnRayaActivity.this);
                result.show();
            }else{
                //texto de has perdido, cambiar con cuadro personalizado
                ResultActivity result = new ResultActivity(TresEnRayaActivity.this,"Has perdido",TresEnRayaActivity.this);
                result.show();
            }
        }else if (estado == 2) {
            //hemos empatado
            ResultActivity result = new ResultActivity(TresEnRayaActivity.this,"Empate",TresEnRayaActivity.this);
            result.show();
        }

    }
    /**
     * Método para comprobar el estado de las casillas y determinar si hay un ganador o empate.
     * @return El estado actual del juego (0: en progreso, 1: ganador, -1: perdedor, 2: empate).
     */
    private int comprobarEstadoCasillas(){
        int newEstado = 0;
        //primera posicion seria todas igaul a 3 es que ha gandado pq estado = 1
        if (Math.abs(tablero[0]+tablero[1]+tablero[2]) ==3) {
            //siempre que gane
            posGana = new int[]{0,1,2};
            //gana tanto yo como la maquina
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[3]+tablero[4]+tablero[5]) ==3) {
            posGana = new int[]{3,4,5};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[6]+tablero[7]+tablero[8]) ==3) {
            posGana = new int[]{6,7,8};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[0]+tablero[3]+tablero[6]) ==3) {
            posGana = new int[]{0,3,6};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[1]+tablero[4]+tablero[7]) ==3) {
            posGana = new int[]{1,4,7};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[2]+tablero[5]+tablero[8]) ==3) {
            posGana = new int[]{2,5,8};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[0]+tablero[4]+tablero[8]) ==3) {
            posGana = new int[]{0,4,8};
            newEstado = 1*turnoJugadorUno;
        }else if (Math.abs(tablero[2]+tablero[4]+tablero[6]) ==3) {
            posGana = new int[]{2,4,6};
            newEstado = 1*turnoJugadorUno;
        }else if(conteoFichas == 9){ //Caso de empate
            newEstado = 2;
        }
        return newEstado;
    }


    //Código del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Construye la vista donde se va a colocar el menu
        getMenuInflater().inflate(R.menu.navigator_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId){
            case R.id.Item_home:
                Intent intent_home = new Intent(TresEnRayaActivity.this, PacienteActivity.class);
                startActivity(intent_home);
                break;
            case R.id.Item_menusalir:
                Intent intent_salir = new Intent(TresEnRayaActivity.this, LoginActivity.class);
                startActivity(intent_salir);
                break;
        }
        return true;
    }

}