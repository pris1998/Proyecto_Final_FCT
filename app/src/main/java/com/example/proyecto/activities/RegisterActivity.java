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
    int conteo  = 0;
    int maxIntento = 0;


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
                //llamada a la función private
                createUsers(email,password);

                //Mensaje en toast para saber que el registro ha sido existoso
                myToast("Registro existoso");

                //Si no están vacíos , dirige al usuario a la pantalla de Login para que
                //vuelva a meter la informacion
                //Intent intent = new Intent(RegisterActivity.this,LoginActivity.class );
                //alias al intent
                //intent.putExtra("Register","mensaje");
                //startActivity(intent);

            }
        });

    }

    public void myToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    //Metodo a parte para crear usuarios y evitar repetir codigo
    private void createUsers(String email , String password){
        if (!email.isEmpty() && !password.isEmpty()) {
            //Si no estan vacios los rellena y los crea
            auth.createUserWithEmailAndPassword( email,  password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    //Manda un email al correo de registrado para comprobar que está bien hecho
                    user.sendEmailVerification();
                    //alerta para decirle al usuario que se ha mandado un menseje
                    // de verificacion para que no se asuste

                    myToast("Registro aceptado");
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

    private int validateEmailPassword(String email , String password){
        if(email.isEmpty() || password.length() < 8  && maxIntento >= conteo){
            //comprobar que la contraseña coincida
            if (password_2 != password) {
            conteo++;
            return -1 ;
            }
        }else{
            myToast("Te has equivocado , reseteo de contraseña");
            auth.sendPasswordResetEmail(email);
            //alerta para reiniciar la contraseña

        }
        return 0;
    }
}