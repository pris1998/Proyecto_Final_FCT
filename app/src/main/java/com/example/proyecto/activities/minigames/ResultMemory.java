package com.example.proyecto.activities.minigames;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyecto.R;

public class ResultMemory extends Dialog {

        private final String mensaje;
        private final MemoryActivity memoryActivity;

        public ResultMemory(@NonNull Context context, String mensaje, MemoryActivity memoryActivity) {
            super(context);
            this.mensaje = mensaje;
            this.memoryActivity = memoryActivity;
        }

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
