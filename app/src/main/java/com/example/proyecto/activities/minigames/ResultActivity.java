package com.example.proyecto.activities.minigames;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;

public class ResultActivity extends Dialog {

    private final String mensaje;
    private final TresEnRayaActivity tresEnRayaActivity;

    public ResultActivity(@NonNull Context context, String mensaje, TresEnRayaActivity tresEnRayaActivity) {
        super(context);
        this.mensaje = mensaje;
        this.tresEnRayaActivity = tresEnRayaActivity;
    }

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
                tresEnRayaActivity.recreate();//restartMatch()
                dismiss();
            }
        });
    }
}