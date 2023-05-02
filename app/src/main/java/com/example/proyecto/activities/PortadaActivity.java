package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.proyecto.R;

public class PortadaActivity extends AppCompatActivity {

    ImageView imageViewIcono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        imageViewIcono = findViewById(R.id.imageViewIcono);
        imageViewIcono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PortadaActivity.this,LoginActivity.class );
                startActivity(intent);
            }
        });
    }
}