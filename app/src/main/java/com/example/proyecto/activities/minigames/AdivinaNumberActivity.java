package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.activities.LoginActivity;
import com.example.proyecto.activities.PacienteActivity;

public class AdivinaNumberActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    TextView textNumSelect, txtMensaje, txtMensaje2;
    EditText txtNumber;
    Button btnComprobar, btnReinicio;
    private int numAleatorio;
    private int intento  = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adivina_number);
        constraintLayout = (ConstraintLayout) findViewById(R.id.root_adivinanumero);


        textNumSelect = findViewById(R.id.textNumSelect);
        txtNumber = findViewById(R.id.txtNumber);
        txtMensaje = findViewById(R.id.txtMensaje);
        txtMensaje2 = findViewById(R.id.txtMensaje2);

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
                if (numUser <0 || numUser > 50 ) {
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
                    ResultNumeros result = new ResultNumeros(AdivinaNumberActivity.this,"Has acertado. El número ganador es:" + numAleatorio,AdivinaNumberActivity.this);
                    result.show();

                }

                //la parte de la puntuacion
                intento = intento -1 ;
                txtMensaje2.setText("Le quedan " + intento + " intentos restantes");

                //si los intentdo acaban = PIERDES
                if (intento == 0 && numAleatorio != numUser) {
                    btnComprobar.setEnabled(false);
                    btnReinicio.setEnabled(true);
                    txtMensaje.setText("Perdiste");
                    ResultNumeros result = new ResultNumeros(AdivinaNumberActivity.this,"El número correcto :" + numAleatorio,AdivinaNumberActivity.this);
                    result.show();

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

            }
        });
    }

    private int crearNumAleatorio(){
        //devuelve un número generado aleatoriamente entre (1 y 100)
        return (int) (Math.random()*50+1);
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
                Intent intent_home = new Intent(AdivinaNumberActivity.this, PacienteActivity.class);
                startActivity(intent_home);
                break;
            case R.id.Item_menusalir:
                Intent intent_salir = new Intent(AdivinaNumberActivity.this, LoginActivity.class);
                startActivity(intent_salir);
                break;
        }
        return true;
    }

}