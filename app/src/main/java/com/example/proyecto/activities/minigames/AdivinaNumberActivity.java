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

/**
 Clase que representa la actividad del juego de adivinar un número.
 */
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

        // Inicialización de los elementos de la interfaz
        textNumSelect = findViewById(R.id.textNumSelect);
        txtNumber = findViewById(R.id.txtNumber);
        txtMensaje = findViewById(R.id.txtMensaje);
        txtMensaje2 = findViewById(R.id.txtMensaje2);

        btnComprobar = findViewById(R.id.btnComprobar);
        btnReinicio = findViewById(R.id.btnReinicio);

        btnReinicio.setEnabled(false);

        // Generación del número aleatorio
        numAleatorio = crearNumAleatorio();

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el número ingresado por el usuario
                int numUser;

                numUser = Integer.parseInt(txtNumber.getText().toString());

                // Comprobar si el número ingresado es válido
                if (numUser <0 || numUser > 50 ) {
                    txtMensaje.setText("No es un número correcto");
                }else if (numUser > numAleatorio) {
                    txtMensaje.setText("Introduzca un número menor");

                }else{
                    txtMensaje.setText("Introduzca un número mayor");
                }
                // Comprobar si el número ingresado es el número aleatorio
                if (numUser == numAleatorio) {
                    //desactiva
                    btnComprobar.setEnabled(false);
                    //activa para empezar de nuevo
                    btnReinicio.setEnabled(true);
                    ResultNumeros result = new ResultNumeros(AdivinaNumberActivity.this,"Has acertado. El número ganador es:" + numAleatorio,AdivinaNumberActivity.this);
                    result.show();

                }
                // Actualizar la puntuación y comprobar si se ha agotado el número de intentos
                intento = intento -1 ;
                txtMensaje2.setText("Le quedan " + intento + " intentos restantes");
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
                // Generar un nuevo número aleatorio y reiniciar la interfaz
                numAleatorio = crearNumAleatorio();
                btnComprobar.setEnabled(true);
                btnReinicio.setEnabled(false);
                txtNumber.setText("");
                txtMensaje.setText("");

            }
        });
    }

    /**
     Genera un número aleatorio entre 1 y 50.
     @return El número aleatorio generado.
     */
    private int crearNumAleatorio(){
        return (int) (Math.random()*50+1);
    }

    /**
     Crea el menú de opciones en la barra de acción.
     @param menu El menú en el que se añadirán las opciones.
     @return true si se ha creado el menú correctamente, false en caso contrario.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigator_menu,menu);
        return true;
    }

    /**
     Maneja la selección de una opción del menú de la barra de acción.
     @param item El elemento del menú seleccionado.
     @return true si se ha manejado la selección correctamente, false en caso contrario.
     */
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