package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.proyecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseUserActivity extends AppCompatActivity {



    Button btnDoctor;
    Button btnPaciente;
    Button btnSalir ;

    TextView bienvenidolabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);

        btnDoctor = findViewById(R.id.btnDoctor);
        btnPaciente = findViewById(R.id.btnPaciente);
        btnSalir = findViewById(R.id.btnSalir);
        bienvenidolabel = findViewById(R.id.bienvenidolabel);
        //Si ya hay un usuario con inicio de sesion abierto
        // se dirige directamente aqui



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            bienvenidolabel.setText(user.getDisplayName());
        }

        btnDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_doctor = new Intent(ChooseUserActivity.this,DoctorActivity.class );
                //alias al intent
                intent_doctor.putExtra("Doctor usuario","mensaje");
                startActivity(intent_doctor);
                finish();
            }
        });

        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_paciente = new Intent(ChooseUserActivity.this,PacienteActivity.class );
                //alias al intent
                intent_paciente.putExtra("Paciente usuario","mensaje");
                startActivity(intent_paciente);
                finish();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cierre de sesion de google
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ChooseUserActivity.this,LoginActivity.class );
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ChooseUserActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}