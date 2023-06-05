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
 * Clase ResultMemory.
 * Un diálogo personalizado que muestra el resultado de un juego de memoria.
 */
public class ResultMemory extends Dialog {

        private final String mensaje;
        private final MemoryActivity memoryActivity;
    /**
     * Constructor de la clase ResultMemory.
     * @param context El contexto de la actividad.
     * @param mensaje El mensaje a mostrar en el diálogo.
     * @param memoryActivity La actividad de memoria a la que pertenece el diálogo.
     */
        public ResultMemory(@NonNull Context context, String mensaje, MemoryActivity memoryActivity) {
            super(context);
            this.mensaje = mensaje;
            this.memoryActivity = memoryActivity;
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

            messageText.setText(mensaje);

            starGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    memoryActivity.closeOptionsMenu();//restartMatch()
                    memoryActivity.recreate();
                    dismiss();
                }
            });
        }

}
