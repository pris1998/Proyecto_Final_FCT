package com.example.proyecto.activities.minigames;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.proyecto.R;

public class ResultNumeros  extends Dialog {

    private final Object mensaje;
    private final AdivinaNumberActivity adivinaNumberActivity;

    public ResultNumeros(@NonNull Context context, Object mensaje, AdivinaNumberActivity adivinaNumberActivity) {
        super(context);
        this.mensaje = mensaje;
        this.adivinaNumberActivity = adivinaNumberActivity;
    }

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
