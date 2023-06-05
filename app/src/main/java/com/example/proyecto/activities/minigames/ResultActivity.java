package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;

/**
 * Clase ResultActivity.
 * Un diálogo personalizado que muestra el resultado de un juego de Tres en Raya.
 */
public class ResultActivity extends Dialog {

    private final String mensaje;
    private final TresEnRayaActivity tresEnRayaActivity;

    /**
     * Constructor de la clase ResultActivity.
     * @param context El contexto de la actividad.
     * @param mensaje El mensaje a mostrar en el diálogo.
     * @param tresEnRayaActivity La actividad de Tres en Raya a la que pertenece el diálogo.
     */
    public ResultActivity(@NonNull Context context, String mensaje, TresEnRayaActivity tresEnRayaActivity) {
        super(context);
        this.mensaje = mensaje;
        this.tresEnRayaActivity = tresEnRayaActivity;
    }
    /**
     * Método onCreate.
     * Se ejecuta cuando se crea la actividad.
     * Inicializa el diseño del diálogo y configura los elementos de la interfaz de usuario.
     * @param savedInstanceState El estado guardado de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView messageText = findViewById(R.id.msgText);
        Button starGame = findViewById(R.id.btnStartAgain);

        messageText.setText(mensaje);

        starGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tresEnRayaActivity.recreate();
                dismiss();
            }
        });
    }
}