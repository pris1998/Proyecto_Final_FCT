package com.example.proyecto.activities;

import static com.example.proyecto.activities.LoginActivity.auth;
import static com.example.proyecto.activities.LoginActivity.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    ImageView imageViewRegister;
    TextView txtVEmailR, txtnewUser;

    TextInputEditText txtEEmail, txtEPassword, confirmarPassword;

    TextInputEditText txtName;

    Button btnInicioS;

    String email ;
    String password;
    String confirmPassword;
    String nombre_Usuario;

    private FirebaseAuth Fauth = FirebaseAuth.getInstance();


    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEEmail = findViewById(R.id.txtEEmail);
        txtEPassword = findViewById(R.id.txtEPassword);
        confirmarPassword = findViewById(R.id.confirmarPassword);

        btnInicioS = findViewById(R.id.btnInicioS);
        txtnewUser = findViewById(R.id.txtnewUser);

        txtnewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vuelve al pulsar el texto al inicio
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class );
                intent.putExtra("Login","mensaje");
                startActivity(intent);
            }
        });

        btnInicioS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    public void myToast(String mensaje){
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }

    //Metodo a parte para crear usuarios y evitar repetir codigo
    private void createUsers(String email , String password){
            //Si no estan vacios los rellena y los crea
            auth.createUserWithEmailAndPassword( email,  password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
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

    //PARTE AÑADIDA NUEVA
    public void validate() {

        email = txtEEmail.getText().toString().trim();
        password = txtEPassword.getText().toString().trim();
        confirmPassword = confirmarPassword.getText().toString().trim();

        //esto lo que pone en rojo el email sino existe
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEEmail.setError("Correo invalido");
            return;
        } else {
            txtEEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            txtEPassword.setError("Se necesitan 8 caracteres");
            return;
            //tiene q contener unnúmero la contraseña pero no me funciona asi q nada
            //si no es la contraseña se manda como nulo
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            txtEPassword.setError("Introduzca algún número");
            return;
        } else {
            txtEPassword.setError(null);
        }
        if (!confirmPassword.equals(password)) {
                confirmarPassword.setError("Deben ser iguales");
                return;
        } else {
            registroBD(nombre_Usuario,email, password);
            registro(email, password);
        }
    }

    public void registro(String email,String password){
        Fauth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(RegisterActivity.this,ChooseUserActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            myToast("Error al registrarse");
                        }
                    }
                });
    }


    //registro con la base de datos
    public void registroBD(String name, String email,String password){
        //Uso un HashMap porque es más facil ya que tiene un clave por (String)
        // y un valor (Object) son los objetos que se guardan al ecribirlo mediante la pantalla de Registro
        Map<String,Object> mapDatos = new HashMap<>();
        mapDatos.put("name",name);
        mapDatos.put("email",email);
        mapDatos.put("password",password);
        firebaseFirestore.collection("users").add(mapDatos).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //Salga bien
                myToast("Usuario creado en BD");
                //finaliza la activity
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                myToast("Error al registrar usuario");
            }
        });
    }





    //Para volver hacia atrás
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

}