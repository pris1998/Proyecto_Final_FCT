package com.example.proyecto.activities.minigames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;

public class AdivinaNumberActivity extends AppCompatActivity {
    TextView textNumSelect, txtMensaje, txtMensaje2, txtTitle;
    EditText txtNumber;
    Button btnComprobar, btnReinicio;
    private int numAleatorio;
    private int intento  = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina_number);

        textNumSelect = findViewById(R.id.textNumSelect);
        txtNumber = findViewById(R.id.txtNumber);
        txtMensaje = findViewById(R.id.txtMensaje);
        txtMensaje2 = findViewById(R.id.txtMensaje2);
        txtTitle = findViewById(R.id.txtTitle);
        btnComprobar = findViewById(R.id.btnComprobar);
        btnReinicio = findViewById(R.id.btnReinicio);


        btnReinicio.setEnabled(false);

        //metodo que crea el numer aleatorio
        numAleatorio = crearNumAleatorio();

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //numero q mete el usuario
                int numUser;
                //convierte a entero
                numUser = Integer.parseInt(txtNumber.getText().toString());
                //se comprueba que este el numero entre los rangos y no sean cosas distintas
                if (numUser <0 || numUser > 100 ) {
                    txtMensaje.setText("No es un número correcto");
                //ayudas para el usuario
                }else if (numUser > numAleatorio) {
                    txtMensaje.setText("Introduzca un número menor");
                }else{
                    txtMensaje.setText("Introduzca un número mayor");
                }
                if (numUser == numAleatorio) {
                    //desactiva
                    btnComprobar.setEnabled(false);
                    //activa para empezar de nuevo
                    btnReinicio.setEnabled(true);
                    txtTitle.setText("Has acertado. El número ganador es:" + numAleatorio);
                }

                //la parte de la puntuacion
                intento = intento -1 ;
                txtMensaje2.setText("Le quedan " + intento + " vidas restantes");

                //si los intentdo acaban = PIERDES
                if (intento == 0 && numAleatorio != numUser) {
                    btnComprobar.setEnabled(false);
                    btnReinicio.setEnabled(true);
                    txtMensaje.setText("Perdiste");
                    txtTitle.setText("El número correcto :" + numAleatorio);
                }
            }
        });

        btnReinicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numAleatorio = crearNumAleatorio();
                //lo vaciamos todo
                btnComprobar.setEnabled(true);
                btnReinicio.setEnabled(false);
                txtNumber.setText("");
                txtMensaje.setText("");
                txtTitle.setText("");
            }
        });
    }

    private int crearNumAleatorio(){
        //devuelve un número generado aleatoriamente entre (1 y 100)
        return (int) (Math.random()*100+1);
    }

}