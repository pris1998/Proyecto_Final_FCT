package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    //Todo. Lo he creado statico para poder llamarlo en otras actividades
    //Todo. en vez de crear todo el tiempo uno nuevo (se llama RegisterActivity)
    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    //TextViews
    private TextInputEditText txtEmailUser;
    private TextInputEditText txtPasswordUser;

    Button btnInicioSesion;
    Button btnRegistro;

    //Coge el usuario usado
    public static FirebaseUser user ;
    String email = "";
    String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializacion de las variables
        txtEmailUser =  findViewById(R.id.txtEmailUser);
        btnInicioSesion = (Button) findViewById(R.id.btnEntrar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtPasswordUser = findViewById(R.id.txtPasswordUser);


        btnInicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //No esta verificado te reedirige a la pagina del Registro para registrar el usuario
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class );
                startActivity(intent);
            }
        });

    }

    public void myToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    //parte nueva
    public void validate() {
        email = txtEmailUser.getText().toString().trim();
        password = txtPasswordUser.getText().toString().trim();

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmailUser.setError("Correo inválido");
            return;
        } else {
            txtEmailUser.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            txtPasswordUser.setError("Se necesitan 8 caracteres");
            return;
            //tiene q contener unnúmero la contraseña pero no me funciona asi q nada
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            txtPasswordUser.setError("Introduzca algún número");
            return;
        }else{
            txtPasswordUser.setError(null);
        }

        iniciarSesion(email,password);
    }

    public void iniciarSesion(String email, String password){
        auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this,ChooseUserActivity.class );
                            startActivity(intent);
                            finish();
                        }else{
                            myToast("Las credenciales no son correctas, inténtelo de nuevo");
                        }
                    }
                });
     }

    }