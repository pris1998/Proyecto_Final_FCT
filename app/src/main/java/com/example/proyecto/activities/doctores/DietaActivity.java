package com.example.proyecto.activities.doctores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyecto.R;
import com.example.proyecto.activities.FirebaseController;

public class DietaActivity extends AppCompatActivity {
    FirebaseController fc;
    TextView txtTipoD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dieta);

        txtTipoD = findViewById(R.id.txtTipoD);
        fc.addDieta();
    }
}