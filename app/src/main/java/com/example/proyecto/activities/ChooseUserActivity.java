package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyecto.R;

public class ChooseUserActivity extends AppCompatActivity {
    Button btnDoctor;
    Button btnPaciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        btnDoctor = findViewById(R.id.btnDoctor);
        btnPaciente = findViewById(R.id.btnPaciente);

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_doctor = new Intent(ChooseUserActivity.this,DoctorActivity.class );
                //alias al intent
                intent_doctor.putExtra("Doctor usuario","mensaje");
                startActivity(intent_doctor);
            }
        });

        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_paciente = new Intent(ChooseUserActivity.this,PacienteActivity.class );
                //alias al intent
                intent_paciente.putExtra("Paciente usuario","mensaje");
                startActivity(intent_paciente);
            }
        });


    }
}