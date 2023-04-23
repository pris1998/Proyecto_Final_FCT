package com.example.proyecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.proyecto.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*
        if (!email.isEmpty() && !password.isEmpty()) {
            //Si no estan vacios los rellena y los crea
            auth.createUserWithEmailAndPassword( email,  password);
            Log.d("Comprobacion de usuario","El email y la contraseña han sido creadas");
            myToast("Usuario registrado");

        }else{
            //Si es esta vacío los campos (email y contraseña)manda el mensaje "Debe completar los campos"
            Toast.makeText(this, "Debe completar los campos",Toast.LENGTH_LONG).show();

        }

        /*
        //Cumple con los limites del email y de la contraseña
        if(!email.isEmpty() && !password.isEmpty()){
            //Manda un email al correo de registrado
            user.sendEmailVerification();
        }*/
    }
}