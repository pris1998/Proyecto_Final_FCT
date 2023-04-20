package com.example.proyecto.modelos.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.proyecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth = FirebaseAuth.getInstance();
    //TextViews
    TextView txtEmailUser;
    TextView txtPasswordUser;
    String email ;
    String contraseña;
    //Coge el usuario usado
    FirebaseUser user = auth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializacion de las variables
        txtEmailUser = (TextView) findViewById(R.id.txtEmailUser);
        txtPasswordUser = (TextView) findViewById(R.id.txtPasswordUser);

        String email = String.valueOf(txtPasswordUser);
        String password = String.valueOf(txtPasswordUser);



        auth.createUserWithEmailAndPassword( email,  password);
        //Manda un email al correo de registrado
        user.sendEmailVerification();
        //Comprobar si esta verificado
        user.isEmailVerified();
        //recordar contraseña
        auth.sendPasswordResetEmail("Email logeado ");
    }
}