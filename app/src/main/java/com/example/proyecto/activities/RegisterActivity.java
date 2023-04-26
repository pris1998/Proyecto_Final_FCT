package com.example.proyecto.activities;

import static com.example.proyecto.activities.LoginActivity.auth;
import static com.example.proyecto.activities.LoginActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {

    EditText txtEEmail;
    EditText txtEPassword;
    EditText txtE2Password;
    Button btnRegistrar;

    String email ;
    String password;
    String password_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEEmail = findViewById(R.id.txtEEmail);
        txtEPassword = findViewById(R.id.txtEPassword);
        txtE2Password = findViewById(R.id.txtE2Password);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEEmail.getText().toString().trim();
                password = txtEPassword.getText().toString().trim();
                password_2 = txtE2Password.getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
                    //Si no estan vacios los rellena y los crea
                    auth.createUserWithEmailAndPassword( email,  password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            //Manda un email al correo de registrado para comprobar que está bien hecho
                            user.sendEmailVerification();
                            myToast("Registro aceptado");
                            //Si no están vacíos , dirige al usuario a la pantalla de Login para que
                            //vuelva a meter la informacion
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class );
                            //alias al intent
                            intent.putExtra("Register","mensaje");
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            myToast("Error al registrarse");

                        }
                    });
                }else {
                    //Si  estan vacío los campos (email y contraseña)manda el mensaje "Debe completar los campos"
                    myToast( "Debe completar los campos");
                }
            }
        });

    }

    public void myToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}