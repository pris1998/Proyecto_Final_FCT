package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.activities.LoginActivity;
import com.example.proyecto.activities.PacienteActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MemoryActivity extends AppCompatActivity {
    //Menu cerrar sesión
    private LinearLayout constraintLayout;
    //Fila 1
    ImageButton btn00, btn0A ,btn0B ,btn0C;
    //Fila 2
    ImageButton btn004, btn0A5, btn0B6, btn0C7;
    //Fila 3
    ImageButton btn008, btn0A9, btn0B10, btn0C11;
    //Fila 4
    ImageButton btn0012, btn0A13, btn0B14, btn0C15;
    //El tablero es 4x4
    ImageButton[] tablero = new ImageButton[16];

    //Botones
    Button btnReiniciar ;

    public int aciertos;

    //Array para las imagenes son 7 y el fondo
    int[] imagenes;
    int fondo;

    //Variables de la logica del juego
    ArrayList<Integer> arrayAleatorio;
    ImageButton first;
    //se compara para ver si son iguales y si no se vuelven a tapar
    int num1, num2;
    //variable en el caso que se equivoque por ello se bloquea todo
    boolean bloqueado = false;
    //tiempo de las cartas
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        constraintLayout = (LinearLayout) findViewById(R.id.root_memory);
        init();
    }

    private void cargarTablero(){
        btn00 = findViewById(R.id.btn00);
        btn0A = findViewById(R.id.btn0A);
        btn0B = findViewById(R.id.btn0B);
        btn0C = findViewById(R.id.btn0C);

        btn004 = findViewById(R.id.btn004);
        btn0A5 = findViewById(R.id.btn0A5);
        btn0B6 = findViewById(R.id.btn0B6);
        btn0C7 = findViewById(R.id.btn0C7);

        btn008 = findViewById(R.id.btn008);
        btn0A9 = findViewById(R.id.btn0A9);
        btn0B10 = findViewById(R.id.btn0B10);
        btn0C11 = findViewById(R.id.btn0C11);

        btn0012 = findViewById(R.id.btn0012);
        btn0A13 = findViewById(R.id.btn0A13);
        btn0B14 = findViewById(R.id.btn0B14);
        btn0C15 = findViewById(R.id.btn0C15);

        tablero[0] = btn00;
        tablero[1] = btn0A;
        tablero[2] = btn0B;
        tablero[3] = btn0C;

        tablero[4] = btn004;
        tablero[5] = btn0A5;
        tablero[6] = btn0B6;
        tablero[7] = btn0C7;

        tablero[8] = btn008;
        tablero[9] = btn0A9;
        tablero[10] = btn0B10;
        tablero[11] = btn0C11;

        tablero[12] = btn0012;
        tablero[13] = btn0A13;
        tablero[14] = btn0B14;
        tablero[15] = btn0C15;





    }

    public void cargarBotones(){
        btnReiniciar = findViewById(R.id.btnReiniciar);


        //Reinicia el tablero
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

    }


    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.elefante,
                R.drawable.gato,
                R.drawable.girafa,
                R.drawable.leon,
                R.drawable.leopardo,
                R.drawable.mapache,
                R.drawable.tucan,
                R.drawable.zebra,
        };
        fondo = R.drawable.fondo;
    }

    private ArrayList<Integer> arrayBarajar(int tamanio){
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < tamanio*2 ; i++) {
            //llegaría hasta 7
            //el resultado va a ser entre (0 y 8) -1  = 7
            result.add(i % tamanio);
        }
        //Se desordena así:
        Collections.shuffle(result);
        //lo saco por pantalla y luego lo quito
        System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    //final es pq cada boton se pasa como parametro
    public void comprobarCelda(int num , final ImageButton imgbtn){
        //comprobar la imagen es el que se ha pulsado y se convierte en el primero
        if (first == null) {
            first = imgbtn;
            //Se pasa la imagen
            first.setScaleType(ImageView.ScaleType.CENTER_CROP);
            first.setImageResource(imagenes[arrayAleatorio.get(num)]);
            //al ser destapado el boton se inabilita
            first.setEnabled(false);
            //número asignado es el que se le pasa al array
            num1 = arrayAleatorio.get(num);
        }else{
            //cuando estén dos destapadas no se pueden pulsar más
            bloqueado = true;
            //Se pasa la imagen
            imgbtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgbtn.setImageResource(imagenes[arrayAleatorio.get(num)]);
            //al ser destapado el boton se inabilita
            imgbtn.setEnabled(false);
            num2 = arrayAleatorio.get(num);
            //ambos números están guardados
            //comprueba que sean iguales
            if (num1 == num2) {
                //se ha acertado
                first = null;
                bloqueado = false;//ya se puede seguir pulsado los demás números
                aciertos++;
                //comprobar si hemos ganado al hacertar todas las imagenes
                if (aciertos == imagenes.length) {
                    ResultMemory result = new ResultMemory(MemoryActivity.this,"Has completado el panel",MemoryActivity.this);
                    result.show();
                }
            }else{
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       first.setScaleType(ImageView.ScaleType.CENTER_CROP);
                       first.setImageResource(fondo);
                       first.setEnabled(true);
                       //imagen
                       imgbtn.setScaleType(ImageView.ScaleType.CENTER_CROP);
                       imgbtn.setImageResource(fondo);
                       imgbtn.setEnabled(true);
                       bloqueado = false;
                       //vacía
                       first = null;

                   }
               },1000);
            }

        }

    }

    private void init(){
        cargarTablero();
        cargarBotones();

        cargarImagenes();
        //CREACIÓN DEL ARRAY DESORDENADO
        arrayAleatorio = arrayBarajar(imagenes.length);
        //cargar las imagenes
        for (int i = 0; i < tablero.length; i++) {
            //Imagen quede centrada a escala
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            //Asignar imagenes
            tablero[i].setImageResource(imagenes[arrayAleatorio.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        //Mostrar las parejas durante un tiempo limitado para recordar algunas
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tablero.length; i++) {
                    //Imagen quede centrada a escala
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //Asignar imagenes
                    //tablero[i].setImageResource(imagenes[arrayAleatorio.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        },500);

        for (int i = 0; i <tablero.length ; i++) {
            //para que no de problema el método comprobarCelda ya que tiene un parametro final
            final int j = i ;
            //Habilita todos los botones del tablero (ImageButton)
            tablero[i].setEnabled(true);
            //Metemos un listener por cada boton permitirá que se muestre lo q
            //hay debajo del fondo
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //si el boton no esta bloqueado
                    if (!bloqueado) {
                        //llama a un metodo de comprobacion
                        comprobarCelda(j, tablero[j]);
                    }
                }
            });
        }
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
                Intent intent_home = new Intent(MemoryActivity.this, PacienteActivity.class);
                startActivity(intent_home);
                break;
            case R.id.Item_menusalir:
                Intent intent_salir = new Intent(MemoryActivity.this, LoginActivity.class);
                startActivity(intent_salir);
                break;
        }
        return true;
    }
}