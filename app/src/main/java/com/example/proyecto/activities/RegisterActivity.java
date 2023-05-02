package com.example.proyecto.activities;

import static com.example.proyecto.activities.LoginActivity.auth;
import static com.example.proyecto.activities.LoginActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    Button btnVerPassword;

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
        btnVerPassword = findViewById(R.id.btnVerPassword);

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
            }
        });

    }

    public void myToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    //Metodo a parte para crear usuarios y evitar repetir codigo
    private void createUsers(String email , String password){
        //validar email y contraseña
        if (validateEmailPassword(email,password)) {
            //Si no estan vacios los rellena y los crea
            auth.createUserWithEmailAndPassword( email,  password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    /*// si todo esta bien ocurre esto :
                    user = auth.getCurrentUser();
                    if () {

                    }*/
                    //Manda un email al correo de registrado para comprobar que está bien hecho
                    user.sendEmailVerification();
                    //alerta para decirle al usuario que se ha mandado un menseje
                    // para verificar que el registro ha sido existoso
                    alertDialog("Aviso usuario creado ",
                            "Se ha enviado un enlace a su " +
                                    " email para su verificacion.Compruebe su usuario");
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
    //donde meterlo
    private boolean validateEmailPassword(String email , String password){
        if(email.isEmpty() || password.length() < 8  && maxIntento >= conteo){
            //comprobar que la contraseña coincida
            if (password_2 != password) {
            conteo++;
            return false;
            }
        }else{
            myToast("Te has equivocado , reseteo de contraseña");
            //Alerta para confirmar que quiera resetear la contraseña
            alertDialog("Reinicio de contraseña", "¿Está seguro de resetear la contraseña?");
            auth.sendPasswordResetEmail(email);
            return false;
        }
        return true;
    }

    public AlertDialog alertDialog(String title, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", null);
        builder.setNegativeButton("Cancelar",null);
        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;

    }
}