package com.example.proyecto.activities.minigames;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyecto.R;
/**
 * Clase ResultNumeros.
 * Un diálogo personalizado que muestra el resultado del juego de adivinar números.
 */
public class ResultNumeros  extends Dialog {

    private final Object mensaje;
    private final AdivinaNumberActivity adivinaNumberActivity;
    /**
     * Constructor de la clase ResultNumeros.
     * @param context El contexto de la actividad.
     * @param mensaje El mensaje a mostrar en el diálogo.
     * @param adivinaNumberActivity La actividad de adivinar números a la que pertenece el diálogo.
     */
    public ResultNumeros(@NonNull Context context, Object mensaje, AdivinaNumberActivity adivinaNumberActivity) {
        super(context);
        this.mensaje = mensaje;
        this.adivinaNumberActivity = adivinaNumberActivity;
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
        setContentView(R.layout.resultmemory);

        TextView messageText = findViewById(R.id.msgText);
        Button starGame = findViewById(R.id.btnOK);

        messageText.setText((CharSequence) mensaje);

        starGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adivinaNumberActivity.recreate();
                dismiss();
            }
        });
    }
}
