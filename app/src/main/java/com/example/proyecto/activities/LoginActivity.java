package com.example.proyecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //Todo. Lo he creado statico para poder llamarlo en otras actividades
    //Todo. en vez de crear todo el tiempo uno nuevo (se llama RegisterActivity)
    public static FirebaseAuth auth = FirebaseAuth.getInstance();
    //TextViews
    TextView txtEmailUser;
    TextView txtPasswordUser;

    //Coge el usuario usado
    public static FirebaseUser user ;
    String email = "";
    String password = "";
    Button btnEntrar;
    Button btnRegistro;
    int conteo  = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Inicializacion de las variables
        txtEmailUser = (TextView) findViewById(R.id.txtEmailUser);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtPasswordUser = (TextView) findViewById(R.id.txtPasswordUser);




        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = String.valueOf(txtEmailUser.getText());
                password = String.valueOf(txtPasswordUser.getText());

                if (!email.isEmpty() && !password.isEmpty()) {

                    //Si no estan vacios los rellena y los crea
                    auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            user = auth.getCurrentUser();
                            if(user.isEmailVerified()){
                                myToast("Sesión iniciada");
                                //Si no están vacíos , dirige al usuario a la pantalla de "Elegir el usuario"
                                Intent intent = new Intent(LoginActivity.this,ChooseUserActivity.class );
                                //alias al intent
                                //Si quiero poner bienvendio no se q lo añado donde pone mensaje eso "Bienvendio" + el email
                                intent.putExtra("Login","mensaje");
                                startActivity(intent);
                            }else{
                                myToast("No está verificado, debe registrarse");
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            myToast("Las credenciales no son correctas");
                            conteo++;
                            if(conteo >= 3){
                               myToast("Te has equivocado , reseteo de contraseña");
                                //recordar contraseña
                                auth.sendPasswordResetEmail("Contraseña reseteada");
                                conteo = 0;
                            }
                        }
                    });

                }else{
                    //Si es esta vacío los campos (email y contraseña)manda el mensaje "Debe completar los campos"
                    Toast.makeText(LoginActivity.this, "Debe completar los campos",Toast.LENGTH_LONG).show();
                }
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


}